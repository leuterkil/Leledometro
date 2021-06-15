package com.left4dev.leledometrostratou.tablists;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.functions.ServiceDatas;
import com.left4dev.leledometrostratou.functions.VacationDatas;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private CalendarViewModel mViewModel;
    private TextView type;
    private final Datas datas = new Datas();
    private ArrayList<String> userData,outsData;
    private ArrayList<VacationDatas> vacationDatas;
    private ArrayList<ServiceDatas> serviceDatas;
    private HashMap<String ,String> datesList = new HashMap<>();

    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.calendar_fragment_2, container, false);

        try {
            InitializeComponents(fragmentView);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return fragmentView;
    }

    private void InitializeComponents(View fragmentView) throws IOException, XmlPullParserException {
        type = fragmentView.findViewById(R.id.textViewType);
        type.setVisibility(View.INVISIBLE);
        File filePersonal,fileVacations,fileOuts,fileServices;
        fileOuts = new File(getString(R.string.outs_path));
        filePersonal = new File(getString(R.string.personal_info_path));
        fileServices = new File(getString(R.string.services_path));
        fileVacations = new File(getString(R.string.vacations_path));
        MCalendarView calendarView = ((MCalendarView) fragmentView.findViewById(R.id.calendar));
        if (fileOuts.exists())
        {
            outsData = datas.loadOuts(getActivity());
            for (int i = 0;i<outsData.size();i++) {
                String[] date = outsData.get(i).split(" ");
                String[] dateNew = new String[]{date[0],date[2],date[4]};
                int month = checkMonth(dateNew[1]);
                int year = Integer.parseInt(dateNew[2]);
                int day = Integer.parseInt(dateNew[0]);
                datesList.put(outsData.get(i),"Έξοδος");
                calendarView.markDate(new DateData(year, month, day).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.GREEN)));
            }

        }
        if (filePersonal.exists())
        {
            String dateType = "";
            userData = datas.loadXML(getActivity());
            for (int i = 1;i<=2;i++) {
                String[] date = userData.get(i).split(" ");
                if (i==1)
                {
                    dateType = "Ημέρα Παρουσίασης";
                }
                else
                {
                    dateType = "Ημέρα Απολύσεως";
                }
                String[] dateNew = new String[]{date[0],date[2],date[4]};
                int month = checkMonth(dateNew[1]);
                int year = Integer.parseInt(dateNew[2]);
                int day = Integer.parseInt(dateNew[0]);
                datesList.put(userData.get(i),dateType);
                calendarView.markDate(new DateData(year, month, day).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.parseColor("#F68B24"))));
            }

        }
        if (fileServices.exists())
        {
            serviceDatas = datas.loadServices(getActivity());
            for (int i = 0;i<serviceDatas.size();i++) {
                String[] date = serviceDatas.get(i).getDate().split(" ");
                String[] dateNew = new String[]{date[0],date[2],date[4]};
                int month = checkMonth(dateNew[1]);
                int year = Integer.parseInt(dateNew[2]);
                int day = Integer.parseInt(dateNew[0]);
                datesList.put(serviceDatas.get(i).getDate(),serviceDatas.get(i).getType()+" "+serviceDatas.get(i).getTime());
                calendarView.markDate(new DateData(year, month, day).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.parseColor("#ff0000"))));
            }

        }
        if (fileVacations.exists())
        {
            vacationDatas = datas.loadVacations(getActivity());
            String Format = "dd MMM yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(Format,Locale.US);

            for (int i = 0;i<vacationDatas.size();i++) {

                ArrayList<Date> dates = getDates(vacationDatas.get(i).getStartDate(),vacationDatas.get(i).getEndDate());

                for (int j = 0;j<dates.size();j++) {
                    String dateToString = sdf.format(dates.get(j));
                    String[] date = dateToString.split(" ");
                    int month = checkMonth(date[1]);
                    int year = Integer.parseInt(date[2]);
                    int day = Integer.parseInt(date[0]);
                    datesList.put(dateToString.replace(" ","  "),vacationDatas.get(i).getType());
                    calendarView.markDate(new DateData(year, month, day).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.parseColor("#F7B205"))));
                }
            }

        }


        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                String month = checkMonthReverse(date.getMonthString());
                String Date = date.getDayString()+"  "+month+"  "+date.getYear();
                if (datesList.containsKey(Date))
                {
                    String Type = datesList.get(Date);
                    type.setText(Type);
                    type.setVisibility(View.VISIBLE);

                }
                else
                {
                    type.setText("");
                    type.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private int checkMonth(String Month)
    {
        int month = 0;
        switch (Month)
        {
            case "Jan":
                month= 1;
                break;
            case "Feb":
                month =  2;
            break;
            case "Mar":
                month = 3;
            break;
            case "Apr":
                month = 4;
            break;
            case "May":
                month =5;
            break;
            case "Jun":
                month =6;
            break;
            case "Jul":
                month =7;
            break;
            case "Aug":
                month =8;
            break;
            case "Sep":
                month =9;
            break;
            case "Oct":
                month =10;
            break;
            case "Nov":
                month =11;
            break;
            case "Dec":
                month =12;
            break;
        }
        return month;
    }

    private String checkMonthReverse(String Month)
    {
        String month = "0";
        switch (Month)
        {
            case "01":
                month = "Jan";
                break;
            case "02":
                month =  "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month ="Jun";
                break;
            case "07":
                month ="Jul";
                break;
            case "08":
                month ="Aug";
                break;
            case "09":
                month ="Sep";
                break;
            case "10":
                month ="Oct";
                break;
            case "11":
                month ="Nov";
                break;
            case "12":
                month ="Dec";
                break;
        }
        return month;
    }
    private static ArrayList<Date> getDates(String dateString1, String dateString2)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        SimpleDateFormat df1 = new SimpleDateFormat("dd  MMM  yyyy",Locale.US);

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        // TODO: Use the ViewModel
    }
}