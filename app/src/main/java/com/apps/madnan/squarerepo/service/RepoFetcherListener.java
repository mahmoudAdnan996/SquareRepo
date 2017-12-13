package com.apps.madnan.squarerepo.service;

import com.apps.madnan.squarerepo.model.Repo;

import java.util.ArrayList;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public interface RepoFetcherListener {

    void onDeliverAllRepos(ArrayList<Repo> repo);

    void onDeliverRepo(Repo flower);

}
