package com.solvd.laba.patterns.builder;

public class Director {
    public void constructVisa(IBuilder builder) {
        builder.reset();
        builder.setId(1);
        builder.setExpirationDate("10/10/2035");
        builder.setProvider("Visa");
        builder.setSecurityCode(123);
        builder.setLimitInDues(10000);
        builder.setLimitInOneDue(100000);
    }

    public void constructMasterCard(IBuilder builder) {
        builder.reset();
        builder.setId(1);
        builder.setExpirationDate("10/10/2035");
        builder.setProvider("MasterCard");
        builder.setSecurityCode(123);
        builder.setLimitInDues(10000);
        builder.setLimitInOneDue(100000);
    }
}
