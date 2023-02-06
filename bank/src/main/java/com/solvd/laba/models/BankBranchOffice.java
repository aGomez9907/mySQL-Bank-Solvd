package com.solvd.laba.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Bank_Branch_Office")

public class BankBranchOffice {
    @JsonProperty("Office_Id")


    private int id;
    @JsonProperty("General_Balance")

    private double generalBalance;
    @JsonProperty("Address")

    private String address;
    @JsonProperty("Country")

    private String country;

    @JsonManagedReference

    private ArrayList<ATM> atms;


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

    @JsonProperty("Office_Id")
    @XmlAttribute(name = "Office_Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("Atms")
    @XmlElementWrapper(name = "Atms")
    @XmlElement(name = "Atm")
    public ArrayList<ATM> getAtms() {
        return atms;
    }

    public void setAtms(ArrayList<ATM> atms) {
        this.atms = atms;
    }

    @JsonProperty("General_Balance")
    @XmlElement(name = "General_Balance")
    public double getGeneralBalance() {
        return generalBalance;
    }

    public void setGeneralBalance(double generalBalance) {
        this.generalBalance = generalBalance;
    }

    @JsonProperty("Address")
    @XmlElement(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("Country")
    @XmlElement(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "BankBranchOffice{" + "id=" + id + ", generalBalance=" + generalBalance + ", address='" + address + '\'' + ", country='" + country + '\'' + ", atms=" + atms + '}';
    }
}
