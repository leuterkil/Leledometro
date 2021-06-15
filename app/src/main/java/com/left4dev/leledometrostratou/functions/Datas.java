package com.left4dev.leledometrostratou.functions;

import android.content.Context;
import android.os.Build;
import android.util.Xml;
import android.widget.Toast;

import com.left4dev.leledometrostratou.spinners.penalty.Penalty;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import androidx.annotation.RequiresApi;

public class Datas {

    public void SaveChanges(Context context, String name, String dateOfEnlistment, String dateOfDismissal
            , String esso, String series, String Image, String corpTitle,int position ) {

        try
        {
            FileOutputStream fileos = context.openFileOutput("personalData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");
            xmlSerializer.startTag(null,"Name");
            xmlSerializer.text(name);
            xmlSerializer.endTag(null,"Name");
            xmlSerializer.startTag(null,"Enlistment");
            xmlSerializer.text(dateOfEnlistment);
            xmlSerializer.endTag(null,"Enlistment");
            xmlSerializer.startTag(null,"Dismissal");
            xmlSerializer.text(dateOfDismissal);
            xmlSerializer.endTag(null,"Dismissal");
            xmlSerializer.startTag(null,"Esso");
            xmlSerializer.text(esso);
            xmlSerializer.endTag(null,"Esso");
            xmlSerializer.startTag(null,"Series");
            xmlSerializer.text(series);
            xmlSerializer.endTag(null,"Series");
            xmlSerializer.startTag(null,"CorpImage");
            xmlSerializer.text(Image);
            xmlSerializer.endTag(null,"CorpImage");
            xmlSerializer.startTag(null,"CorpTitle");
            xmlSerializer.text(corpTitle);
            xmlSerializer.endTag(null,"CorpTitle");
            xmlSerializer.startTag(null,"Position");
            xmlSerializer.text(Integer.toString(position));
            xmlSerializer.endTag(null,"Position");
            xmlSerializer.endTag(null, "userData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }

    }

    public ArrayList<String> loadXML(Context context)
    {

        ArrayList<String> userData = new ArrayList();


        try
        {
            FileInputStream fis = context.openFileInput("personalData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("personalData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {
                System.out.println("Start tag " + xpp.getName());
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
                System.out.println("End tag " + xpp.getName());
            }
            else if (eventType == XmlPullParser.TEXT)
            {
                userData.add(xpp.getText());
            }

            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return userData;
    }


    public void CreateServicesFile(Context context,String Type,String Number,String Date)
    {

        try
        {
            FileOutputStream fileos = context.openFileOutput("ServicesData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"service");
            xmlSerializer.startTag(null,"Type");
            xmlSerializer.text(Type);
            xmlSerializer.endTag(null,"Type");
            xmlSerializer.startTag(null,"Number");
            xmlSerializer.text(Number);
            xmlSerializer.endTag(null,"Number");
            xmlSerializer.startTag(null,"Date");
            xmlSerializer.text(Date);
            xmlSerializer.endTag(null,"Date");
            xmlSerializer.endTag(null,"service");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }
    }

    public void SaveServices(Context context,String Type,String Number,String Date) throws IOException {

//            First Load The XML And Save It to a String
           FileInputStream fis = context.openFileInput("ServicesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();


//          After the Loading Save a new tag in the XML and compose it with your string

        FileOutputStream fileos = context.openFileOutput("ServicesData.xml", Context.MODE_PRIVATE);
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.startTag(null,"service");
        xmlSerializer.startTag(null,"Type");
        xmlSerializer.text(Type);
        xmlSerializer.endTag(null,"Type");
        xmlSerializer.startTag(null,"Number");
        xmlSerializer.text(Number);
        xmlSerializer.endTag(null,"Number");
        xmlSerializer.startTag(null,"Date");
        xmlSerializer.text(Date);
        xmlSerializer.endTag(null,"Date");
        xmlSerializer.endTag(null,"service");
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        String dataWrite = writer.toString();
        String test = dataWrite.replace("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>","");
        fileos.write((data+test).getBytes());
        fileos.close();
        Toast.makeText(context,"Τα δεδομένα Ενημερώθηκαν",Toast.LENGTH_LONG).show();

    }

    public ArrayList<ServiceDatas> loadServices(Context context) throws IOException, XmlPullParserException {

        ArrayList<ServiceDatas> serviceDatas = new ArrayList();
        String Type="",Number="",Date="";

        try
        {
            FileInputStream fis = context.openFileInput("ServicesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("ServicesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        int eventTemp = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {
                if (xpp.getName().equals("Type"))
                {
                    eventTemp=xpp.next();
                    Type = xpp.getText();
                }
                else if (xpp.getName().equals("Number"))
                {
                    eventTemp=xpp.next();
                    Number = xpp.getText();
                }
                else if (xpp.getName().equals("Date"))
                {
                    eventTemp=xpp.next();
                    Date = xpp.getText();
                }
            }
            if(!Type.isEmpty()&&!Number.isEmpty()&&!Date.isEmpty())
            {
                serviceDatas.add(new ServiceDatas(Type,Number,Date));
                Type="";
                Number="";
                Date="";
            }

            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return serviceDatas;
    }

    public void CreateVacationsFile(Context context,String Type,String startDate,String endDate)
    {
        try
        {
            FileOutputStream fileos = context.openFileOutput("VacationsData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"vacation");
            xmlSerializer.startTag(null,"Type");
            xmlSerializer.text(Type);
            xmlSerializer.endTag(null,"Type");
            xmlSerializer.startTag(null,"startDate");
            xmlSerializer.text(startDate);
            xmlSerializer.endTag(null,"startDate");
            xmlSerializer.startTag(null,"endDate");
            xmlSerializer.text(endDate);
            xmlSerializer.endTag(null,"endDate");
            xmlSerializer.endTag(null,"vacation");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }
    }

    public void SaveChangesVacations(Context context,String Type,String startDate,String endDate) throws IOException {


        FileInputStream fis = context.openFileInput("VacationsData.xml");
        InputStreamReader isr = new InputStreamReader(fis);
        char[] inputBuffer = new char[fis.available()];
        isr.read(inputBuffer);
        String data = new String(inputBuffer);
        isr.close();
        fis.close();



        FileOutputStream fileos = context.openFileOutput("VacationsData.xml", Context.MODE_PRIVATE);
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.startTag(null,"vacation");
        xmlSerializer.startTag(null,"Type");
        xmlSerializer.text(Type);
        xmlSerializer.endTag(null,"Type");
        xmlSerializer.startTag(null,"startDate");
        xmlSerializer.text(startDate);
        xmlSerializer.endTag(null,"startDate");
        xmlSerializer.startTag(null,"endDate");
        xmlSerializer.text(endDate);
        xmlSerializer.endTag(null,"endDate");
        xmlSerializer.endTag(null,"vacation");
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        String dataWrite = writer.toString();
        String test = dataWrite.replace("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>","");
        fileos.write((data+test).getBytes());
        fileos.close();
        Toast.makeText(context,"Τα δεδομένα Ενημερώθηκαν",Toast.LENGTH_LONG).show();

    }

    public ArrayList<VacationDatas> loadVacations(Context context) throws IOException, XmlPullParserException {
        ArrayList<VacationDatas> vacationDatas = new ArrayList();
        String Type="",startDate="",endDate="";

        try
        {
            FileInputStream fis = context.openFileInput("VacationsData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("VacationsData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        int eventTemp = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {
                if (xpp.getName().equals("Type"))
                {
                    eventTemp=xpp.next();
                    Type = xpp.getText();
                }
                else if (xpp.getName().equals("startDate"))
                {
                    eventTemp=xpp.next();
                    startDate = xpp.getText();
                }
                else if (xpp.getName().equals("endDate"))
                {
                    eventTemp=xpp.next();
                    endDate = xpp.getText();
                }
            }
            if(!Type.isEmpty()&&!startDate.isEmpty()&&!endDate.isEmpty())
            {
                vacationDatas.add(new VacationDatas(Type,startDate,endDate));
                Type="";
                startDate="";
                endDate="";
            }

            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return vacationDatas;
    }

    public void CreatePenaltiesFile(Context context,String Type,String Days)
    {
        try
        {
            FileOutputStream fileos = context.openFileOutput("PenaltiesData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"penalty");
            xmlSerializer.startTag(null,"Type");
            xmlSerializer.text(Type);
            xmlSerializer.endTag(null,"Type");
            xmlSerializer.startTag(null,"Days");
            xmlSerializer.text(Days);
            xmlSerializer.endTag(null,"Days");
            xmlSerializer.endTag(null,"penalty");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }
    }

    public void SavePenalties(Context context,String Type,String Days) throws IOException {


        FileInputStream fis = context.openFileInput("PenaltiesData.xml");
        InputStreamReader isr = new InputStreamReader(fis);
        char[] inputBuffer = new char[fis.available()];
        isr.read(inputBuffer);
        String data = new String(inputBuffer);
        isr.close();
        fis.close();



        FileOutputStream fileos = context.openFileOutput("PenaltiesData.xml", Context.MODE_PRIVATE);
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.startTag(null,"penalty");
        xmlSerializer.startTag(null,"Type");
        xmlSerializer.text(Type);
        xmlSerializer.endTag(null,"Type");
        xmlSerializer.startTag(null,"Days");
        xmlSerializer.text(Days);
        xmlSerializer.endTag(null,"Days");
        xmlSerializer.endTag(null,"penalty");
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        String dataWrite = writer.toString();
        String test = dataWrite.replace("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>","");
        fileos.write((data+test).getBytes());
        fileos.close();
        Toast.makeText(context,"Τα δεδομένα Ενημερώθηκαν",Toast.LENGTH_LONG).show();

    }

    public ArrayList<PenaltyDatas> loadPenalties(Context context) throws IOException, XmlPullParserException {
        ArrayList<PenaltyDatas> penaltyDatas = new ArrayList();
        String Type="",Days="";

        try
        {
            FileInputStream fis = context.openFileInput("PenaltiesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("PenaltiesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        int eventTemp = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {
                if (xpp.getName().equals("Type"))
                {
                    eventTemp=xpp.next();
                    Type = xpp.getText();
                }
                else if (xpp.getName().equals("Days"))
                {
                    eventTemp=xpp.next();
                    Days = xpp.getText();
                }
            }
            if(!Type.isEmpty()&&!Days.isEmpty())
            {
                penaltyDatas.add(new PenaltyDatas(Type,Days));
                Type="";
                Days="";
            }

            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return penaltyDatas;
    }

    public void CreateOutsFile(Context context,String date)
    {
        try
        {
            FileOutputStream fileos = context.openFileOutput("OutsData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"out");
            xmlSerializer.startTag(null,"Date");
            xmlSerializer.text(date);
            xmlSerializer.endTag(null,"Date");
            xmlSerializer.endTag(null,"out");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }
    }

    public void SaveOuts(Context context,String date) throws IOException {


        FileInputStream fis = context.openFileInput("OutsData.xml");
        InputStreamReader isr = new InputStreamReader(fis);
        char[] inputBuffer = new char[fis.available()];
        isr.read(inputBuffer);
        String data = new String(inputBuffer);
        isr.close();
        fis.close();



        FileOutputStream fileos = context.openFileOutput("OutsData.xml", Context.MODE_PRIVATE);
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.startTag(null,"out");
        xmlSerializer.startTag(null,"Date");
        xmlSerializer.text(date);
        xmlSerializer.endTag(null,"Date");;
        xmlSerializer.endTag(null,"out");
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        String dataWrite = writer.toString();
        String test = dataWrite.replace("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>","");
        fileos.write((data+test).getBytes());
        fileos.close();
        Toast.makeText(context,"Τα δεδομένα Ενημερώθηκαν",Toast.LENGTH_LONG).show();

    }

    public ArrayList<String> loadOuts(Context context) throws IOException, XmlPullParserException {
        ArrayList<String> outsDates = new ArrayList();
        try
        {
            FileInputStream fis = context.openFileInput("OutsData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("OutsData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        int eventTemp = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {

            }
            else if (eventType == XmlPullParser.TEXT)
            {
                outsDates.add(xpp.getText());
            }
            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return outsDates;
    }

    public void SaveNotes(Context context, String ASM, String IdNum, String gunNum
            , String knifeNum) {

        try
        {
            FileOutputStream fileos = context.openFileOutput("NotesData.xml", Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "notesData");
            xmlSerializer.startTag(null,"ASM");
            xmlSerializer.text(ASM);
            xmlSerializer.endTag(null,"ASM");
            xmlSerializer.startTag(null,"IdNum");
            xmlSerializer.text(IdNum);
            xmlSerializer.endTag(null,"IdNum");
            xmlSerializer.startTag(null,"gunNum");
            xmlSerializer.text(gunNum);
            xmlSerializer.endTag(null,"gunNum");
            xmlSerializer.startTag(null,"knifeNum");
            xmlSerializer.text(knifeNum);
            xmlSerializer.endTag(null,"knifeNum");
            xmlSerializer.endTag(null, "notesData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Toast.makeText(context,"Τα δεδομένα Αποθηκεύτηκαν",Toast.LENGTH_LONG).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(context,"Υπήρξε Πρόβλημα κατά την αποθήκευση Προσπαθήστε Ξανά!!",Toast.LENGTH_LONG).show();

        }

    }

    public ArrayList<String> loadNotes(Context context)
    {

        ArrayList<String> userData = new ArrayList();


        try
        {
            FileInputStream fis = context.openFileInput("NotesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            isr.close();
            fis.close();
        }
        catch (IOException e3)
        {
            e3.printStackTrace();
        }


        XmlPullParserFactory factory = null;


        try
        {
            factory = XmlPullParserFactory.newInstance();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }
        factory.setNamespaceAware(true);
        XmlPullParser xpp = null;


        try
        {
            xpp = factory.newPullParser();
        }
        catch (XmlPullParserException e2)
        {
            e2.printStackTrace();
        }


        try
        {
            FileInputStream fis = context.openFileInput("NotesData.xml");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            String data = new String(inputBuffer);
            xpp.setInput(new StringReader(data));
        }
        catch (IOException | XmlPullParserException e3)
        {
            e3.printStackTrace();
        }
        int eventType = 0;
        try
        {
            eventType = xpp.getEventType();
        }
        catch (XmlPullParserException e1)
        {
            e1.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT)
        {

            if (eventType == XmlPullParser.START_DOCUMENT)
            {
                System.out.println("Start document");
            }
            else if (eventType == XmlPullParser.START_TAG)
            {
                System.out.println("Start tag " + xpp.getName());
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
                System.out.println("End tag " + xpp.getName());
            }
            else if (eventType == XmlPullParser.TEXT)
            {
                userData.add(xpp.getText());
            }

            try
            {
                eventType = xpp.next();
            }
            catch (XmlPullParserException | IOException e)
            {
                e.printStackTrace();
            }
        }

        return userData;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <E, K> Map<K, List<E>> groupBy(List<E> list, Function<E, K> keyFunction) {
        return Optional.ofNullable(list)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(Collectors.groupingBy(keyFunction));
    }
}
