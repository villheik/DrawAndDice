package com.example.root.drawdice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by root on 25.12.2016.
 */

public class mainView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);
        Toolbar mainMenuBar = (Toolbar) findViewById(R.id.menuBar);
        setSupportActionBar(mainMenuBar);

        //Luodaan asetukset
        SharedPreferences pref = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("deckAmount", 1);
        editor.putBoolean("jokers", true);
        editor.putBoolean("returnCards", true);
        editor.putInt("diceType", 6);
        editor.putInt("diceAmount", 1);
        editor.putInt("diceTypeSpinnerPos", 1);

        editor.apply();

        //Siirtyminen korttinäkymään
        final Button cardButton = (Button) findViewById(R.id.cards_button);
        cardButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(mainView.this, cardView.class);

                mainView.this.startActivity(intent);
            }
        });

        //Siirtyminen noppanäkymään
        final Button diceButton = (Button) findViewById(R.id.dice_button);
        diceButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(mainView.this, diceView.class);

                mainView.this.startActivity(intent);
            }
        });

        //Siirtyminen kolikkonäkymään
        final Button coinButton = (Button) findViewById(R.id.coin_button);
        coinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(mainView.this, coinView.class);

                mainView.this.startActivity(intent);
            }
        });

        //Siirtyminen about-näkymään
        final Button aboutButton = (Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(mainView.this, aboutView.class);

                mainView.this.startActivity(intent);
            }
        });
    }
}