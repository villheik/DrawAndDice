package com.example.root.drawdice;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

//Noppa-asetukset
public class diceSettings extends Activity implements View.OnClickListener{
    NumberPicker diceAmount; Spinner diceType;
    TextView diceAmountToolTip; TextView diceTypeToolTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_options);

        //Haetaan talletetut- tai oletusasetukset
        final SharedPreferences prefs = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        diceAmount = (NumberPicker) findViewById(R.id.diceAmountChooser);
        diceAmount.setMinValue(1);
        diceAmount.setMaxValue(10);
        diceAmount.setWrapSelectorWheel(false);

        diceType = (Spinner) findViewById(R.id.diceTypeChooser);
        Integer[] diceTypes = new Integer[]{4, 6, 8, 10, 12, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, diceTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        diceType.setAdapter(adapter);

        int diceAmountPref = prefs.getInt("diceAmount", 1);
        int diceTypeSpinnerPos = prefs.getInt("diceTypeSpinnerPos", 1);

        diceAmount.setValue(diceAmountPref);
        diceType.setSelection(diceTypeSpinnerPos);

        //Kuuntelija noppien lukumäärän valinnalle (NumberPicker)
        diceAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editor.putInt("diceAmount", newVal);
                editor.apply();
            }
        });

        //Kuuntelija noppien tyypin valinnalle (Spinner)
        diceType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
                int value = (Integer) parent.getItemAtPosition(pos);
                //Nopan tyyppi (4, 6, 8, etc.)
                editor.putInt("diceType", value);
                //Sijainti Spinner arrayssa (4=0, 6=1, ...)
                editor.putInt("diceTypeSpinnerPos", pos);
                editor.apply();

                diceType.setSelection(pos);
            }
                //Mitään ei valittu, asetetaan oletusarvo (d6)
            public void onNothingSelected(AdapterView<?> parent){
                diceType.setSelection(prefs.getInt("diceTypeSpinnerPos", 1));
            }

        });

        diceAmountToolTip = (TextView)findViewById(R.id.info_diceAmount);
        diceTypeToolTip = (TextView)findViewById(R.id.info_diceType);

        diceAmountToolTip.setOnClickListener(this);
        diceTypeToolTip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        DialogFragment diceAmountInfo = new diceAmountDialog();
        DialogFragment diceTypeInfo = new diceTypeDialog();
        switch (v.getId()){
            case (R.id.info_diceAmount):
                diceAmountInfo.show(getFragmentManager(), "diceAmountInfo");
                return;
            case (R.id.info_diceType):
                diceTypeInfo.show(getFragmentManager(), "diceTypeInfo");
                return;
            default:
                return;
        }

    }


}
