package com.left4dev.leledometrostratou.functions;

import android.content.Context;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

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
}
