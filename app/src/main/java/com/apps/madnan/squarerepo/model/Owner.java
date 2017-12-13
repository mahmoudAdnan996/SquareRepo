package com.apps.madnan.squarerepo.model;

import java.io.Serializable;

/**
 * Created by mahmoud adnan on 12/13/2017.
 */

public class Owner implements Serializable{

    private String login;
    private String html_url;

    public Owner() {
    }

    public Owner(String login, String html_url) {
        this.login = login;
        this.html_url = html_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
