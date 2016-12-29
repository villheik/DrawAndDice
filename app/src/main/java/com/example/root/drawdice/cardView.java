package com.example.root.drawdice;

import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by keios on 28.12.2016.
 */

public class cardView extends AppCompatActivity implements View.OnClickListener {

    private ImageButton drawButton;
    private ImageView currentCard;
    private ImageView previousCard;
    private boolean firstDraw = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view);

        drawButton      = (ImageButton)findViewById(R.id.notFlipped);
        currentCard     = (ImageView)findViewById(R.id.flipped);
        previousCard    = (ImageView)findViewById(R.id.previous);

        drawButton.setOnClickListener(this);

        Toolbar cardViewBar = (Toolbar) findViewById(R.id.cardBar);
        setSupportActionBar(cardViewBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);

       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(this, cardSettings.class);
                cardView.this.startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private final static Random random = new Random();

    private final static int[] deck = new int[] {
            R.drawable.ic_ca,
            R.drawable.ic_c2,
            R.drawable.ic_c3,
            R.drawable.ic_c4,
            R.drawable.ic_c5,
            R.drawable.ic_c6,
            R.drawable.ic_c7,
            R.drawable.ic_c8,
            R.drawable.ic_c9,
            R.drawable.ic_c10,
            R.drawable.ic_cj,
            R.drawable.ic_cq,
            R.drawable.ic_ck};

    
    @Override
    public void onClick(View v) {
        drawCard();
    }

    int next = 0;

    //Otetaan pakasta kortti, ja jos ei kyseessä ensimmäien veto, niin näytetään myös edellinen kortti
    private void drawCard() {
        if (!firstDraw){
            previousCard.setImageResource(deck[next]);
        }
        else firstDraw = false;
        next = random.nextInt(deck.length);
        currentCard.setImageResource(deck[next]);

    }

}
