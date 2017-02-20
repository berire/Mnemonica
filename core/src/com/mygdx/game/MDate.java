package com.mygdx.game;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 26.1.2017.
 */
public class MDate {
    Date date;
    Calendar calendar;
    int year,season,month,week,day,hour,minute,second;

    public MDate()
    {
        date=new Date();
        year=0;
        season=0;
        month=0;
        week=0;
        day=0;
        hour=0;
        minute=0;
        second=0;
    }
}

