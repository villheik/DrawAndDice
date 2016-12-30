package com.example.root.drawdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


//Näkymä, jossa näkyy sovelluksen tiedot ja tekijät
public class aboutView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Toolbar aboutBar = (Toolbar) findViewById(R.id.aboutBar);
        setSupportActionBar(aboutBar);

    }
}
