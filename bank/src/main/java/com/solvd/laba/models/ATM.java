package com.solvd.laba.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ATM")
public class ATM {
    @JsonProperty("ATM_ID")
    @XmlElement(name = "ATM_ID")
    private int id;
    @JsonProperty("BALANCE")
    @XmlElement(name = "BALANCE")
    private double balance;
    @JsonBackReference
    @XmlElement(name = "OFFICE")
    private BankBranchOffice office;

    public ATM(double balance, BankBranchOffice office) {
        this.balance = balance;
        this.office = office;
    }

    public ATM(int id, double balance, BankBranchOffice office) {
        this.id = id;
        this.balance = balance;
        this.office = office;
    }

    public ATM(double balance) {
        this.balance = balance;
    }

    public ATM(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public ATM() {
    }

    @JsonProperty("ATM_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("BALANCE")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @JsonProperty("OFFICE")
    public BankBranchOffice getOffice() {
        return office;
    }

    public void setOffice(BankBranchOffice office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "ATM{" + "id=" + id + ", balance=" + balance + ", office=" + office + '}';
    }
}
