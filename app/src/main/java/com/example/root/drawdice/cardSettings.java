package com.example.root.drawdice;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;


//Aktiviteetti korttien asetuksille (pakkojen määrä, jokerit ja palautetaanko vedetty kortti pakkaan)
public class cardSettings extends AppCompatActivity implements View.OnClickListener{
    NumberPicker deckAmount;
    Switch removeOrRetain; Switch jokers;
    TextView deckAmountToolTip; TextView jokersToolTip; TextView returnToolTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_options);

        //Haetaan tallennetut asetukset
        SharedPreferences prefs = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        deckAmount = (NumberPicker) findViewById(R.id.deckAmountChooser);
        removeOrRetain = (Switch) findViewById(R.id.returnChooser);
        jokers = (Switch) findViewById(R.id.jokerChooser);

        //Asetetaan min ja max arvot pakkojen lukumäärän valitsijalle
        deckAmount.setMinValue(1);
        deckAmount.setMaxValue(5);
        deckAmount.setWrapSelectorWheel(false);

        //Haetaan ja asetetaan tallennetut tai oletusarvot asetuksille
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

        //Kuuntelija pakkojen lukumäärien valitsijalle (NumberPicker)
        deckAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editor.putInt("deckAmount", newVal);
                editor.apply();
            }
        });

        //Kuuntelija jokerien valitsijalle (Switch)
        jokers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("jokers", isChecked);
                editor.apply();
            }
        });

        //Kuuntelija korttien palautuksen valinnalle (Switch)
        removeOrRetain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("returnCards", isChecked);
                editor.apply();
            }
        });

        Toolbar cardViewBar = (Toolbar) findViewById(R.id.cardOptionsBar);
        setSupportActionBar(cardViewBar);
    }

    //Ylikuormitetaan onClick tukemaan tooltippien avausta
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
