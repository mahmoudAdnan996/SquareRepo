package com.apps.madnan.squarerepo.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.madnan.squarerepo.R;
import com.apps.madnan.squarerepo.helper.RepoDatabase;
import com.apps.madnan.squarerepo.model.Repo;
import com.apps.madnan.squarerepo.service.RepoFetcherListener;
import com.apps.madnan.squarerepo.service.RepoService;
import com.apps.madnan.squarerepo.ui.adapter.RepoAdapter;
import com.apps.madnan.squarerepo.utils.EndlessRecyclerViewScrollListener;
import com.apps.madnan.squarerepo.utils.UtilsConnect;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.apps.madnan.squarerepo.service.Constants.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepoFragment extends Fragment implements RepoFetcherListener{

    @BindView(R.id.repoRV) RecyclerView repoRecycler;
    RepoAdapter repoAdapter;

    int dataPage = 1;
    ArrayList<Repo> repoArrayList;

    // ===========================
    RepoDatabase repoDatabase;
    //==============================

    public RepoFragment() {
    }

    public static RepoFragment newInstance() {
        return new RepoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repo, container, false);
        ButterKnife.bind(this, view);

        // =====================================
        repoDatabase = new RepoDatabase(getActivity());
        // =====================================
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repoArrayList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        repoRecycler.setLayoutManager(linearLayoutManager);
        repoRecycler.setItemAnimator(new DefaultItemAnimator());

        repoAdapter = new RepoAdapter(repoArrayList, getActivity());
        repoRecycler.setAdapter(repoAdapter);

        // ==============================
        repoAdapter.reset();
        // ==============================

        if (UtilsConnect.isNetworkAvailable(getActivity())) {

            repoRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    dataPage++;
                    getRepoFrom(dataPage);
                }
            });
            getRepoFrom(dataPage);
        }else {
            // ==============================
            getRepoFromDB();
            // ==============================

        }

    }

    private void getRepoFrom(final int pageNum) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RepoService productService = retrofit.create(RepoService.class);
        Call<ArrayList<Repo>> connection = productService.getRepos(pageNum);
        connection.enqueue(new Callback<ArrayList<Repo>>() {
            @Override
            public void onResponse(Call<ArrayList<Repo>> call, Response<ArrayList<Repo>> response) {
                repoArrayList.addAll(response.body());
                repoAdapter.notifyItemRangeInserted(repoAdapter.getItemCount(), repoArrayList.size() -1);

            }

            @Override
            public void onFailure(Call<ArrayList<Repo>> call, Throwable t) {
                Log.e("Error is", t.getMessage());
            }
        });
    }

    // ==============================
    private void getRepoFromDB(){

        ArrayList<Repo> repoArrayList = repoDatabase.getRepos();
        for (int i =0; i< repoArrayList.size(); i++){
            Repo repo = repoArrayList.get(i);
            repoAdapter.addRepo(repo);
        }
    }
    // ==============================

    @Override
    public void onDeliverAllRepos(ArrayList<Repo> repo) {

    }

    @Override
    public void onDeliverRepo(Repo repo) {
        repoAdapter.addRepo(repo);
    }
    // ==============================


}
