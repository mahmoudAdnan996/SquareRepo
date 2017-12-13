package com.apps.madnan.squarerepo.service;

import com.apps.madnan.squarerepo.model.Repo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public interface RepoService {

    @GET("repos?page=1&per_page=10")
    Call<ArrayList<Repo>> getRepos(@Query("page") int page);
}
