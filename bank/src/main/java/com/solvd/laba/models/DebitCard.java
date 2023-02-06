package com.solvd.laba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.models.interfaces.ICard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "DEBIT_CARD")
@XmlAccessorType(XmlAccessType.FIELD)
public class DebitCard implements ICard {
    @JsonProperty("DEBIT_CARD_ID")
    @XmlElement(name = "DEBIT_CARD_ID")
    private int id;
    @JsonProperty("EXPIRATION_DATE")
    @XmlElement(name = "EXPIRATION_DATE")
    private String expirationDate;
    @JsonProperty("SECURITY_CODE")
    @XmlElement(name = "SECURITY_CODE")
    private int securityCode;
    @JsonProperty("PROVIDER")
    @XmlElement(name = "PROVIDER")
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

    @JsonProperty("DEBIT_CARD_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("EXPIRATION_DATE")
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("SECURITY_CODE")
    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @JsonProperty("PROVIDER")
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


    @Override
    public String toString() {
        return "DebitCard{" +
                "id=" + id +
                ", expirationDate=" + expirationDate +
                ", securityCode=" + securityCode +
                ", provider='" + provider + '\'' +
                '}';
    }
}

