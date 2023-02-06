package com.solvd.laba.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "Atm")
@XmlAccessorType(XmlAccessType.NONE)
public class ATM {
    @JsonProperty("Atm_Id")
    @XmlAttribute(name = "Atm_Id")
    private int id;
    @JsonProperty("Balance")
    @XmlElement(name = "Balance")
    private double balance;
    @JsonBackReference
    @XmlTransient
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

    @JsonProperty("Atm_Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("Balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @JsonProperty("Office")
    @XmlTransient
    public BankBranchOffice getOffice() {
        return office;
    }

    public void setOffice(BankBranchOffice office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "ATM{" + "id=" + id + ", balance=" + balance + '}';
    }
}
