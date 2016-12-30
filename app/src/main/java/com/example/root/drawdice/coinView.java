package com.example.root.drawdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

//Näkymä kolikonheitolle
public class coinView extends AppCompatActivity implements View.OnClickListener{
    ImageButton flipCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_view);

        Toolbar coinViewBar = (Toolbar) findViewById(R.id.coinBar);
        setSupportActionBar(coinViewBar);

        flipCoin = (ImageButton) findViewById(R.id.coin_button);
        flipCoin.setOnClickListener(this);
    }

    private final static Random random = new Random();

    //Uusin heitto siirtyy näkymässä ylimmäksi. Edelliset heitot siirtyvät listassa alaspäin
    @Override
    public void onClick(View v){
        TextView results0 = (TextView) findViewById(R.id.coin_results0);
        TextView results1 = (TextView) findViewById(R.id.coin_results1);
        TextView results2 = (TextView) findViewById(R.id.coin_results2);
        TextView results3 = (TextView) findViewById(R.id.coin_results3);
        int result = 0;

        result = random.nextInt(2);

        results3.setText(results2.getText());
        results2.setText(results1.getText());
        results1.setText(results0.getText());
        switch (result){
            case 0:
                results0.setText("Heads");
                return;
            case 1:
                results0.setText("Tails");
                return;
            default:
                results0.setText("coin got lost in space");
        }

    }
}
