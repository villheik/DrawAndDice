package com.example.root.drawdice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

/**
 * Created by keios on 29.12.2016.
 */

//Aktiviteetti korttien asetuksille (pakkojen määrä, jokerit ja palautetaanko kortti vedettyä pakkaan)
public class cardSettings extends Activity {
    NumberPicker deckAmount;
    Switch removeOrRetain; Switch jokers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_options);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        deckAmount = (NumberPicker) findViewById(R.id.deckAmountChooser);
        removeOrRetain = (Switch) findViewById(R.id.returnChooser);
        jokers = (Switch) findViewById(R.id.jokerChooser);

        deckAmount.setMinValue(1);
        deckAmount.setMaxValue(5);
        deckAmount.setWrapSelectorWheel(true);

        int deckAmountPref = prefs.getInt("deckAmount", 1);
        boolean jokerPref = prefs.getBoolean("jokers", true);
        boolean returnPref = prefs.getBoolean("returnCards", true);

        deckAmount.setValue(deckAmountPref);
        jokers.setChecked(jokerPref);
        removeOrRetain.setChecked(returnPref);


        deckAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editor.putInt("deckAmount", newVal);
                editor.apply();
            }
        });

        jokers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("jokers", isChecked);
                editor.apply();
            }
        });

        removeOrRetain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("returnCards", isChecked);
                editor.apply();
            }
        });


    }

}
