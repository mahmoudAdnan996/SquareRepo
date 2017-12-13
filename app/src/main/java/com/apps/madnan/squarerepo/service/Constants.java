package com.apps.madnan.squarerepo.service;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class Constants {

    public static final String BASE_URL = "https://api.github.com/users/square/";





    // ==============================

    public static final class DATABASE {

        public static final String DB_NAME = "repos";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "repo";

        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_REPOS_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String REPO_ID = "repoId";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String LOGIN = "login";



        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
                "(" + REPO_ID + " INTEGER PRIMARY KEY not null," +
                NAME + " TEXT not null," +
                DESCRIPTION + " TEXT not null," +
                LOGIN + " TEXT not null,)";
    }
    // ==============================


}
