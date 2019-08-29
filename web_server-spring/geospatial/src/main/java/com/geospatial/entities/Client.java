package com.geospatial.entities;

import org.springframework.data.annotation.Id;

public class Client {

    @Id
    public String id;

    public String name;

    public String password;

    public String[] roles;

    public String email;

    public String[] torrents;

    // Constructors
    public Client() {
    }

    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Functions
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getname() {
        return this.name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return this.roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getTorrents() {
        return this.torrents;
    }
    public void setTorrents(String[] torrents) {
        this.torrents = torrents;
    }

    @Override
    public String toString() {
        return String.format(name);
    }

}