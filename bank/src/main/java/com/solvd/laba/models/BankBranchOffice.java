package com.solvd.laba.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "BANK_BRANCH_OFFICE")
public class BankBranchOffice {
    @JsonProperty("OFFICE_ID")
    @XmlElement(name = "OFFICE_ID")
    private int id;
    @JsonProperty("GENERAL_BALANCE")
    @XmlElement(name = "GENERAL_BALANCE")
    private double generalBalance;
    @JsonProperty("ADDRESS")
    @XmlElement(name = "ADDRESS")
    private String address;
    @JsonProperty("COUNTRY")
    @XmlElement(name = "COUNTRY")
    private String country;

    @JsonManagedReference
    @XmlElementWrapper(name = "ATMS")
    @XmlElement(name = "ATM")
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

    @JsonProperty("OFFICE_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("ATMS")
    public ArrayList<ATM> getAtms() {
        return atms;
    }

    public void setAtms(ArrayList<ATM> atms) {
        this.atms = atms;
    }

    @JsonProperty("GENERAL_BALANCE")
    public double getGeneralBalance() {
        return generalBalance;
    }

    public void setGeneralBalance(double generalBalance) {
        this.generalBalance = generalBalance;
    }

    @JsonProperty("ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("COUNTRY")
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
                ", atms=" + atms +
                '}';
    }
}
