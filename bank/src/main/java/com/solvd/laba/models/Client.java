package com.solvd.laba.models;

public class Client {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String country;
    private String address;

    public Client() {

    }

    public Client(String name, String surname, int age, String country, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientID=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
