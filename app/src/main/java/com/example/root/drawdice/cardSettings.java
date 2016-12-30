package com.example.root.drawdice;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by keios on 29.12.2016.
 */

//Aktiviteetti korttien asetuksille (pakkojen määrä, jokerit ja palautetaanko vedetty kortti pakkaan)
public class cardSettings extends Activity implements View.OnClickListener{
    NumberPicker deckAmount;
    Switch removeOrRetain; Switch jokers;
    TextView deckAmountToolTip; TextView jokersToolTip; TextView returnToolTip;

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
        deckAmount.setWrapSelectorWheel(false);

        int deckAmountPref = prefs.getInt("deckAmount", 1);
        boolean jokerPref = prefs.getBoolean("jokers", true);
        boolean returnPref = prefs.getBoolean("returnCards", true);

        deckAmount.setValue(deckAmountPref);
        jokers.setChecked(jokerPref);
        removeOrRetain.setChecked(returnPref);

        deckAmountToolTip   =(TextView) findViewById(R.id.info_deck_amount);
        jokersToolTip       =(TextView) findViewById(R.id.info_jokers);
        returnToolTip       =(TextView) findViewById(R.id.info_returnretain);

        deckAmountToolTip.setOnClickListener(this);
        jokersToolTip.setOnClickListener(this);
        returnToolTip.setOnClickListener(this);

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

    @Override
    public void onClick(View v){
        DialogFragment deckAmountInfo = new deckAmountDialog();
        DialogFragment jokerInfo = new jokerDialog();
        DialogFragment returnInfo = new returnDialog();
        switch (v.getId()){
            case (R.id.info_deck_amount):
                deckAmountInfo.show(getFragmentManager(), "deckAmountInfo");
                return;
            case (R.id.info_jokers):
                jokerInfo.show(getFragmentManager(), "jokerInfo");
                return;
            case (R.id.info_returnretain):
                returnInfo.show(getFragmentManager(), "returnInfo");
                return;
            default:
                return;
        }

    }

}
