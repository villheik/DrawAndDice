package com.example.root.drawdice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

//Näkymä nopanheitolle
public class diceView extends AppCompatActivity implements View.OnClickListener {


    private ImageButton throwDice;
    private TextView showResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_view);

        Toolbar diceViewBar = (Toolbar) findViewById(R.id.diceBar);
        setSupportActionBar(diceViewBar);

        throwDice = (ImageButton) findViewById(R.id.dice_button);
        throwDice.setOnClickListener(this);


    }
    //Luodaan appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbardice, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(this, diceSettings.class);
                diceView.this.startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private final static Random random = new Random();

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        TextView results0 = (TextView) findViewById(R.id.dice_results0);
        TextView results1 = (TextView) findViewById(R.id.dice_results1);
        TextView results2 = (TextView) findViewById(R.id.dice_results2);
        TextView results3 = (TextView) findViewById(R.id.dice_results3);
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i=0; i <pref.getInt("diceAmount", 1); i++ ){
            //Random arpoo luvun väliltä 0-n. Jos nopan tyyli d6, otetaan väliksi 0-5 ja lisätään lopussa 1
            results.add(random.nextInt(pref.getInt("diceType", 6)) + 1);
        }

        String results_string = "";
        int finalResult= 0;

        //Jos useampi kuin yksi noppa (tulokseen noppa x, noppa y = summa z)
        if (results.size() > 1) {
            for (int i = 0; i < results.size(); i++) {
                results_string += results.get(i).toString();
                if (i < results.size() - 1) results_string += ", ";
                finalResult += results.get(i);
            }
            results_string += " = " + finalResult;
        }
        //Jos vain yksi noppa (tulokseen noppa x)
        else results_string += results.get(0);

        results3.setText(results2.getText());
        results2.setText(results1.getText());
        results1.setText(results0.getText());
        results0.setText(String.valueOf(results_string));

    }

}
