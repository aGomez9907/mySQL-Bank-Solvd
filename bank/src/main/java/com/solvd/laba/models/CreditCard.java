package com.solvd.laba.models;

public class CreditCard {
    private int id;
    private int limitInOneDue;
    private int limitInDues;
    private String expirationDate;
    private int securityCode;
    private String provider;


    public CreditCard() {
    }

    public CreditCard(int id, int limitInOneDue, int limitInDues, String expirationDate, int securityCode, String provider) {
        this.id = id;
        this.limitInOneDue = limitInOneDue;
        this.limitInDues = limitInDues;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;

    }

    public CreditCard(int limitInOneDue, int limitInDues, String expirationDate, int securityCode, String provider) {
        this.limitInOneDue = limitInOneDue;
        this.limitInDues = limitInDues;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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


    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", limitInOneDue=" + limitInOneDue +
                ", limitInDues=" + limitInDues +
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", provider='" + provider + '\'' +
                '}';
    }
}

