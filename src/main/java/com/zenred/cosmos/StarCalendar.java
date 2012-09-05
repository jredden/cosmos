package com.zenred.cosmos;

import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Locale;

public class StarCalendar extends GregorianCalendar{
    public StarCalendar(){
        super();
    }

    public long getStarTimeInMillis(){
        return getTimeInMillis();
    }
}

