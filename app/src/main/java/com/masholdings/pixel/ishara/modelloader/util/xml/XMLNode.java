package com.masholdings.pixel.ishara.modelloader.util.xml;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * MAS PIXEL
 * Developed by Ishara Abeykoon.
 * Contact isharaAb@masholdings.com / isharaux@gmail.com
 */
public class XMLNode {
    String TAG = "XMLNode";

    String nodeName;
    List<XMLNode> children;
    static XmlPullParser xpp;

    Float[] dataf;
    Integer[] datai;
    String dataType = "null";

    BufferedReader reader;


    public XMLNode(String name){
        this.nodeName = name;



    }

    public void setData(String type, Float[] data){
        dataType = type;
    }
    public void setData(String type, Integer[] data){
        dataType = type;
    }

    public void init(BufferedReader reader){
        this.reader = reader;

    }

    public void buildNode(){


        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            xpp.setInput(reader);
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if(eventType == XmlPullParser.START_TAG){
                    children.add(new XMLNode(xpp.getName()));
                }
                eventType = xpp.next();
            }

        }catch (XmlPullParserException ex){
            Log.d(TAG, ex.getMessage());
        }catch (IOException ex){

        }

    }
}
