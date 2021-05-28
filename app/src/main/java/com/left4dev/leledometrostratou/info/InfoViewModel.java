package com.left4dev.leledometrostratou.info;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.lifecycle.ViewModel;

public class InfoViewModel extends ViewModel {

    public String UpdateDateLabel(Calendar calendar)
    {
        String myFormat = "dd  MMM  yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return  sdf.format(calendar.getTime());
    }

}