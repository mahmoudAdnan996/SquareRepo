package com.apps.madnan.squarerepo.model;

import java.io.Serializable;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class Repo implements Serializable{
    private String name;
    private String description;
    private boolean fork;
    private Owner owner;
    private String html_url;
    private String id;

    public Repo() {
    }

    public Repo(String name, String description, String html_url, boolean fork, String id) {
        this.name = name;
        this.description = description;
        this.fork = fork;
        this.html_url = html_url;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", repoDescription='" + description + '\'' +
                '}';

    }

}
