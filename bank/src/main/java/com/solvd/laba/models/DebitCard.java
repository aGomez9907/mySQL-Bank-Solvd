package com.solvd.laba.models;

import java.time.LocalDate;

public class DebitCard {
    private int id;
    private LocalDate expirationDate;
    private int securityCode;
    private String provider;
    private AccountsMain account;

    public DebitCard() {
    }

    public DebitCard(int id, int limitInOneDue, int limitInDues, LocalDate expirationDate, int securityCode, String provider, AccountsMain account) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;
        this.account = account;
    }

    public DebitCard(int limitInOneDue, int limitInDues, LocalDate expirationDate, int securityCode, String provider, AccountsMain account) {
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
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", provider='" + provider + '\'' +
                ", account=" + account +
                '}';
    }
}

