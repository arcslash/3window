package com.masholdings.pixel.ishara.modelloader.util.xml;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.masholdings.pixel.ishara.modelloader.model.entities.Camera;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * MAS PIXEL
 * Developed by Ishara Abeykoon.
 * Contact isharaAb@masholdings.com / isharaux@gmail.com
 */

/*
* XMLParser will read the model file and divide nodes to joints and vertices as necessary
* default location for the model fil will be in the assets folder, it will need to add explicitly
* if it's other than the assets folder
* Parser will breakdown the model file to key nodes, in geometry, animations and controllers
*
* */
public class XmlParser {

    String modelFileName = "model.dae";//Filename with the default filename
    BufferedReader reader;
    boolean isExtracting = false;

    Context context;

    String TAG = "XMLParser";

    //Data Objects an parameters to be extracted
    private Camera camera;

    private String[] camera_params = {"xfov", "aspect_ratio", "znear", "zfar"};
    private Float[] camera_values = {0f, 0f, 0f, 0f};



    public XmlParser(Context context){
        this.context = context;
        TAG = context.getClass().getEnclosingClass().getName();
        readModelFile();

    }


    public XmlParser(String fileName, Context context){
        this.modelFileName = fileName;
        this.context = context;
        readModelFile();

    }

    /*
    * This function will read the model file in the asset folder,constructor must first initialize with
    * the model filename or the default filename will be used.
    * Model extracts all information from the model file and stores as the object
    * */

    public void readModelFile(){

        try{
            String library_track = "COLLADA";
            String attribute_track = "null";

            //make objects for the extractions
            camera = new Camera();



            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new BufferedReader(new InputStreamReader(context.getAssets().open(modelFileName))));
            int eventType = xpp.getEventType();
            Log.i(TAG, "Reading Model File");
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {




                    if (isExtracting) {
                        attribute_track = xpp.getName();

                        if(eventType == XmlPullParser.TEXT) {
                            Log.i(TAG, "Extracting Information");


                        }


                    } else {
                        if (xpp.getName().contains("library_")) {
                            Log.i(TAG, "Starting Information Extraction");
                            library_track = xpp.getName();
                            isExtracting = true;
                        }


                    }

                }else if (eventType == XmlPullParser.END_TAG) {

                    if (isExtracting) {
                        if (xpp.getName().contains("library_")) {
                            Log.i(TAG, "Information Extraction Complete");

                            switch (library_track){
                                case "library_cameras":
                                    camera = new Camera("Main Camera");
                                    camera.setOptics(camera_values[0], camera_values[1], camera_values[2], camera_values[3]);
                                    break;

                            }
                            isExtracting = false;
                        }

                    } else {
                        attribute_track = "";

                    }
                }else if (eventType == XmlPullParser.TEXT){
                    System.out.print(library_track + ":" + attribute_track + ":" + xpp.getText());


                    switch (library_track){
                        case "library_cameras":
                            System.out.println("Camera Param"+attribute_track + xpp.getText());
                            for (int i = 0;i < camera_params.length; i++) {
                                if(camera_params[i].equalsIgnoreCase(attribute_track)){
                                    camera_values[i] = Float.valueOf(xpp.getText());
                                    attribute_track = "";
                                }

                            }


                            break;

                    }
                }
                eventType = xpp.next();
            }
//
//
//
//
//
//
//                if(eventType == XmlPullParser.START_TAG && !isExtracting){
//                    if (xpp.getName().contains("library") ){
//                        Log.i(TAG,"Found a Library file:" + xpp.getName());
//                        isExtracting = true;
//
//                        switch(xpp.getName().replace("library_","")){
//                            case "cameras":
//                                buildCamera();
//
//                                break;
//                        }
//
//
//                    }
//
//                }

//                if(eventType == XmlPullParser.START_DOCUMENT) {
//                    System.out.println("Start document");
//                } else if(eventType == XmlPullParser.START_TAG) {
//                    System.out.println("Start tag "+xpp.getName());
//                } else if(eventType == XmlPullParser.END_TAG) {
//                    System.out.println("End tag "+xpp.getName());
//                } else if(eventType == XmlPullParser.TEXT) {
//                    System.out.println("Text "+xpp.getText());
////                }
//                eventType = xpp.next();
//            }

//            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(modelFileName)));
            Log.i(TAG,"File Read Complete");

        }catch (IOException ioexception){
            Log.d(TAG,"Exception :" + ioexception.getMessage());
        }catch (XmlPullParserException ex){
            Log.d(TAG,"Exception :" + ex.getMessage());

        }




    }

    private void buildCamera(){

    }

}
