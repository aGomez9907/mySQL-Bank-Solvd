package com.solvd.laba.models;

public class HomeBanking {
    private int id;
    private Client client;
    private String username;
    private String password;

    public HomeBanking() {
    }

    public HomeBanking(Client client, String username, String password) {
        this.client = client;
        this.username = username;
        this.password = password;
    }

    public HomeBanking(int id, Client client, String username, String password) {
        this.id = id;
        this.client = client;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getPerson() {
        return client;
    }

    public void setPerson(Client client) {
        this.client = client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "HomeBanking{" +
                "id=" + id +
                ", client=" + client +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
