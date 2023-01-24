package com.solvd.laba.models;

import java.time.LocalDate;

public class CreditCard {
    private int id;
    private int limitInOneDue;
    private int limitInDues;
    private LocalDate expirationDate;
    private int securityCode;
    private String provider;
    private AccountsMain account;

    public CreditCard() {
    }

    public CreditCard(int id, int limitInOneDue, int limitInDues, LocalDate expirationDate, int securityCode, String provider, AccountsMain account) {
        this.id = id;
        this.limitInOneDue = limitInOneDue;
        this.limitInDues = limitInDues;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;
        this.account = account;
    }

    public CreditCard(int limitInOneDue, int limitInDues, LocalDate expirationDate, int securityCode, String provider, AccountsMain account) {
        this.limitInOneDue = limitInOneDue;
        this.limitInDues = limitInDues;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimitInOneDue() {
        return limitInOneDue;
    }

    public void setLimitInOneDue(int limitInOneDue) {
        this.limitInOneDue = limitInOneDue;
    }

    public int getLimitInDues() {
        return limitInDues;
    }

    public void setLimitInDues(int limitInDues) {
        this.limitInDues = limitInDues;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public AccountsMain getAccount() {
        return account;
    }

    public void setAccount(AccountsMain account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", limitInOneDue=" + limitInOneDue +
                ", limitInDues=" + limitInDues +
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", provider='" + provider + '\'' +
                ", account=" + account +
                '}';
    }
}
