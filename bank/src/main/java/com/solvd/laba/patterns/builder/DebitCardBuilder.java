package com.solvd.laba.patterns.builder;

import com.solvd.laba.models.DebitCard;

public class DebitCardBuilder implements IBuilder {

    private DebitCard dc = new DebitCard();

    public void reset() {
        this.dc = new DebitCard();
    }

    public void setId(int id) {
        dc.setId(id);
    }

    public void setLimitInOneDue(int limitInOneDue) {
    }

    public void setLimitInDues(int limitInDues) {
    }

    public void setExpirationDate(String expirationDate) {
        dc.setExpirationDate(expirationDate);
    }


    public void setSecurityCode(int securityCode) {
        dc.setSecurityCode(securityCode);
    }


    public void setProvider(String provider) {
        dc.setProvider(provider);
    }

    public DebitCard getCard() {
        DebitCard debitCard = dc;
        this.reset();
        return debitCard;
    }

}
