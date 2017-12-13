package com.apps.madnan.squarerepo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.madnan.squarerepo.R;
import com.apps.madnan.squarerepo.model.Repo;

import java.util.ArrayList;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    ArrayList<Repo> repos;
    Context context;

    public RepoAdapter(ArrayList<Repo> repos) {
        this.repos = repos;
    }

    public RepoAdapter(ArrayList<Repo> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.setRepoView(repos.get(position));

    }


    // =============================
    public void addRepo(Repo repo) {
        repos.add(repo);
        notifyDataSetChanged();
    }

    public void reset() {
        repos.clear();
        notifyDataSetChanged();
    }

    // ================================
    @Override
    public int getItemCount() {
        return repos.size();
    }
}
