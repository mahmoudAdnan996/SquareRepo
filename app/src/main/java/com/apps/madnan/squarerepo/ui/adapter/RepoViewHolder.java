package com.apps.madnan.squarerepo.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apps.madnan.squarerepo.R;
import com.apps.madnan.squarerepo.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repo_name) TextView repoName;
    @BindView(R.id.repo_owner) TextView repoOwner;
    @BindView(R.id.repo_desc) TextView repoDesc;
    @BindView(R.id.card) CardView cardView;

    public RepoViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void setRepoView(Repo repoView){

        String desc = repoView.getDescription();
        boolean fork = repoView.isFork();
        if (fork == false){
            cardView.setCardBackgroundColor(Color.parseColor("#98FB98"));
        }else{
            cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }
        repoName.setText(repoView.getName());
        repoOwner.setText(repoView.getOwner().getLogin());
        repoDesc.setText(desc);

        final String repoUrl = repoView.getHtml_url();
        final String ownerUrl = repoView.getOwner().getHtml_url();

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            final CharSequence[] items = {
                    "html_url of repository","html_url of owner"};
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(itemView.getContext());
                dialog.setTitle("Open Url?");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("html_url of repository")){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(repoUrl));
                            itemView.getContext().startActivity(intent);

                        }else if (items[which].equals("html_url of owner")){
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(ownerUrl));
                            itemView.getContext().startActivity(i);
                        }
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
                return true;
            }
        });
    }
}
