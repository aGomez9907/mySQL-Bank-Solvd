package com.solvd.laba.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {


    @Override
    public Date unmarshal(String date) throws Exception {
        return new SimpleDateFormat("MM - dd - yyyy").parse(date);
    }

    @Override
    public String marshal(Date d) throws Exception {
        return new SimpleDateFormat("dd-MM-yyyy").format(d);
    }
}

