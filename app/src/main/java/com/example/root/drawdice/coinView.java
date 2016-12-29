package com.example.root.drawdice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by keios on 29.12.2016.
 */

public class coinView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_view);
        Toolbar coinViewBar = (Toolbar) findViewById(R.id.coinBar);
        setSupportActionBar(coinViewBar);

    }
}
