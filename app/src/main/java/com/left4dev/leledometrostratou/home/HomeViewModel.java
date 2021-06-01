package com.left4dev.leledometrostratou.home;

import com.left4dev.leledometrostratou.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public int CheckCorp(String corp)
    {
        int id;
        switch (corp)
        {
            case "Τεθωρακισμένα":
                id= R.drawable.tethorakismena;
                break;
            case "Καταδρομείς":
                id = R.drawable.eidikesdynameis;
                break;
            case "Προεδρική Φρουρά":
                id = R.drawable.peziko;
                break;
            case "Πολεμική Αεροπορεία":
                id = R.drawable.aeroporeia;
                break;
            case "71η Αερομεταφερόμενοι":
                id = R.drawable.aerometaferomenos;
                break;
            default:
                id = R.drawable.peziko2;
                break;

        }
        return id;
    }

    //1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
    public String KS(Date startDate, Date endDate) {
        //milliseconds
        String finalString;
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        finalString = Long.toString(elapsedDays);
        return finalString;

    }

    public int CheckRank(float percent)
    {
        int imageID = R.drawable.psarakas;
        if (percent>=0.0 && percent<5.3)
        {
            imageID = R.drawable.psarakas;
        }
        else if (percent>=5.3 && percent<10.6)
        {
            imageID = R.drawable.arrouri;
        }
        else if(percent>=10.6&&percent<15.9)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_ypodekaneas;
        }
        else if(percent>=15.9&&percent<21.2)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_dekaneas;
        }
        else if (percent>=21.2&&percent<26.5)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_loxias;
        }
        else if (percent>=26.5&&percent<31.8)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_epiloxias;
        }
        else if (percent>=31.8&&percent<37.1)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_arxiloxias;
        }
        else if (percent>=37.1&&percent<42.4)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_antipaspistis;
        }
        else if (percent>=42.4&&percent<47.7)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_dea;
        }
        else if (percent>=47.7&&percent<53)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_anthipoloxagos;
        }
        else if (percent>=53&&percent<58.3)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_ypoloxagos;
        }
        else if (percent>=58.3&&percent<63.6)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_loxagos;
        }
        else if (percent>=63.6&&percent<68.9)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_tagmatarxis;
        }
        else if (percent>=68.9&&percent<74.2)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_antisintagmatarxis;
        }
        else if (percent>=74.2&&percent<79.5)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_sintagmatarxis;
        }
        else if (percent>=79.5&&percent<84.8)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_taxiarxos;
        }
        else if (percent>=84.8&&percent<90.1)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_ypostratigos;
        }
        else if (percent>=90.1&&percent<95.4)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_antistratigos;
        }
        else if (percent>=95.4&&percent<100.0)
        {
            imageID = R.drawable.diakritiko_stratos_xiras_stratigos;
        }
        else if (percent>=100.0)
        {
            imageID = R.drawable.lelele;
        }
        else if (percent<0)
        {
            imageID = R.drawable.psarakas;
        }

        return imageID;
    }

    public String CheckRankName(float percent,String Corp)
    {
        String Name = "Ψάρακας";
        if (percent>=0.0 && percent<5.3)
        {
            Name = "Ψάρακας";
        }
        else if (percent>=5.3 && percent<10.6)
        {
            Name="Αρούρι";
        }
        else if(percent>=10.6&&percent<15.9)
        {
            Name = "Υποδεκανέας";
        }
        else if(percent>=15.9&&percent<21.2)
        {
            Name = "Δεκανέας";
        }
        else if (percent>=21.2&&percent<26.5)
        {
            Name = "Λοχίας";
        }
        else if (percent>=26.5&&percent<31.8)
        {
            Name = "Επιλοχίας";
        }
        else if (percent>=31.8&&percent<37.1)
        {
            Name = "Αρχιλοχίας";
        }
        else if (percent>=37.1&&percent<42.4)
        {
            Name = "Ανθυπασπιστής";
        }
        else if (percent>=42.4&&percent<47.7)
        {
            Name = "Δ.Ε.Α";
        }
        else if (percent>=47.7&&percent<53)
        {
            if (Corp.equals("Τεθωρακισμένα"))
            {
                Name = "Ανθυπίλαρχος";
            }
            else if (Corp.equals("Υγειονομικό"))
            {
                Name = "Ανθυπίατρος";
            }
            else
            {
                Name = "Ανθυπολοχαγός";
            }
        }
        else if (percent>=53&&percent<58.3)
        {
            if (Corp.equals("Τεθωρακισμένα"))
            {
                Name = "Υπίλαρχος";
            }
            else if (Corp.equals("Υγειονομικό"))
            {
                Name = "Υπίατρος";
            }
            else
            Name = "Υπολοχαγός";
        }
        else if (percent>=58.3&&percent<63.6)
        {
            if (Corp.equals("Τεθωρακισμένα"))
            {
                Name = "Ίλαρχος";
            }
            else if (Corp.equals("Υγειονομικό"))
            {
                Name = "Ιατρός";
            }
            else
            Name = "Λοχαγός";
        }
        else if (percent>=63.6&&percent<68.9)
        {
            if (Corp.equals("Τεθωρακισμένα"))
            {
                Name = "Επίλαρχος";
            }
            else if (Corp.equals("Υγειονομικό"))
            {
                Name = "Επίατρος";
            }
            else
            Name = "Ταγματάρχης";
        }
        else if (percent>=68.9&&percent<74.2)
        {
             if (Corp.equals("Υγειονομικό"))
            {
                Name = "Αρχίατρος";
            }
             else
            Name = "Αντισυνταγματάρχης";
        }
        else if (percent>=74.2&&percent<79.5)
        {
            if (Corp.equals("Υγειονομικό"))
            {
                Name = "Γενικός Αρχίατρος";
            }
            else
            Name = "Συνταγματάρχης";
        }
        else if (percent>=79.5&&percent<84.8)
        {
            Name = "Ταξίαρχος";
        }
        else if (percent>=84.8&&percent<90.1)
        {
            Name = "Υποστράτηγος";
        }
        else if (percent>=90.1&&percent<95.4)
        {
            Name = "Αντιστράτηγος";
        }
        else if (percent>=95.4&&percent<100.0)
        {
            Name = "Στρατηγός";
        }
        else if (percent>=100.0)
        {
            Name = "Λέλαρχος";
        }
        else if (percent<0)
        {
            Name = "Ψάρακας";
        }

        return Name;
    }
}