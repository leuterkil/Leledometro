package com.left4dev.leledometrostratou.floatbuttonclasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ServiceActivityFunctions {

    public String UpdateDateLabel(Calendar calendar)
    {
        String myFormat = "dd  MMM  yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return  sdf.format(calendar.getTime());
    }
}
