package com.solvd.laba.patterns.builder;

import com.solvd.laba.models.CreditCard;

public class CreditCardBuilder implements IBuilder {

    private CreditCard cc = new CreditCard();

    public void reset() {
        this.cc = new CreditCard();
    }

    public void setId(int id) {
        cc.setId(id);
    }

    public void setLimitInOneDue(int limitInOneDue) {
        cc.setLimitInOneDue(limitInOneDue);
    }

    public void setLimitInDues(int limitInDues) {
        cc.setLimitInDues(limitInDues);
    }

    public void setExpirationDate(String expirationDate) {
        cc.setExpirationDate(expirationDate);
    }


    public void setSecurityCode(int securityCode) {
        cc.setSecurityCode(securityCode);
    }


    public void setProvider(String provider) {
        cc.setProvider(provider);
    }

    public CreditCard getCard() {
        CreditCard creditCard = cc;
        this.reset();
        return creditCard;
    }
}
