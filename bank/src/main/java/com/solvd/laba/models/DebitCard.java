package com.solvd.laba.models;

import java.sql.Date;


public class DebitCard {
    private int id;
    private String expirationDate;
    private int securityCode;
    private String provider;


    public DebitCard() {
    }

    public DebitCard(String expirationDate, int securityCode, String provider) {
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.provider = provider;
    }

    public DebitCard(int id, String expirationDate, int securityCode, String provider) {
        this.id = id;
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
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", provider='" + provider + '\'' +
                '}';
    }
}

