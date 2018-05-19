package com.masholdings.pixel.ishara.modelloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.masholdings.pixel.ishara.modelloader.util.xml.XmlParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlParser xmlParser = new XmlParser("cowboy.dae", getApplicationContext());
    }
}
