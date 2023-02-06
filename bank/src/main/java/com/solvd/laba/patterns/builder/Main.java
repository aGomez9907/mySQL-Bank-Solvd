package com.solvd.laba.patterns.builder;

import com.solvd.laba.models.CreditCard;
import com.solvd.laba.models.DebitCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Director director = new Director();
        DebitCardBuilder dcb = new DebitCardBuilder();
        director.constructVisa(dcb);
        DebitCard dc = dcb.getCard();

        LOGGER.info(dc.toString());

        CreditCardBuilder ccb = new CreditCardBuilder();
        director.constructMasterCard(ccb);
        CreditCard cc = ccb.getCard();

        LOGGER.info(cc.toString());


    }
}
