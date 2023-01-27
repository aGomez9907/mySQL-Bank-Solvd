package com.solvd.laba.models;

public class BankBranchOffice {
    private int id;
    private double generalBalance;
    private String address;
    private String country;

    public BankBranchOffice() {
    }

    public BankBranchOffice(double generalBalance, String address, String country) {

        this.generalBalance = generalBalance;
        this.address = address;
        this.country = country;
    }

    public BankBranchOffice(int id, double generalBalance, String address, String country) {
        this.id = id;
        this.generalBalance = generalBalance;
        this.address = address;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGeneralBalance() {
        return generalBalance;
    }

    public void setGeneralBalance(double generalBalance) {
        this.generalBalance = generalBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        return "BankBranchOffice{" +
                "id=" + id +
                ", generalBalance=" + generalBalance +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
