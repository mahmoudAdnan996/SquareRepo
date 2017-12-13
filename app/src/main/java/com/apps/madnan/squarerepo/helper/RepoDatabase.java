package com.apps.madnan.squarerepo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.apps.madnan.squarerepo.model.Owner;
import com.apps.madnan.squarerepo.model.Repo;
import com.apps.madnan.squarerepo.service.Constants;
import com.apps.madnan.squarerepo.service.RepoFetcherListener;

import java.util.ArrayList;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class RepoDatabase extends SQLiteOpenHelper {

    private static final String TAG = RepoDatabase.class.getSimpleName();

    public RepoDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
        }catch (SQLException e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.DROP_QUERY);
        this.onCreate(db);
    }

    public void addRepo(Repo repo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.REPO_ID, repo.getId());
        values.put(Constants.DATABASE.NAME, repo.getName());
        values.put(Constants.DATABASE.DESCRIPTION, repo.getDescription());
        values.put(Constants.DATABASE.LOGIN, repo.getOwner().getLogin());

        try {
            db.insert(Constants.DATABASE.TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();

    }

    public ArrayList<Repo> getRepos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_REPOS_QUERY, null);

        final ArrayList<Repo> repoList = new ArrayList<>();
        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {

                do {
                    Repo repo = new Repo();
                    Owner owner = new Owner();
                    repo.setName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.NAME)));
                    repo.setDescription(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DESCRIPTION)));
                    owner.setLogin(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.LOGIN)));
                    repo.setId(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.REPO_ID)));

                    repoList.add(repo);

                } while (cursor.moveToNext());
            }
            }
        return repoList;
    }

}