package com.project.chatapp.Model;

import androidx.annotation.NonNull;

public class User {

    private String uid;
    private String name;
    private String imageURL;
    private String status;
    private String search;
    private String email;

    public User(String uid, String name, String imageURL, String status, String search,String email) {
        this.uid = uid;
        this.name = name;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.email = email;
    }

    public User() {

    }

    public String getId() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @NonNull
    @Override
    public String toString() {
        return "User(" + "uid='" + uid + '\'' + ", name ='" + name + '\'' + ", email ='" + email + '\'' + '}';
    }
}
