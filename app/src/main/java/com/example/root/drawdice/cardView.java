package com.example.root.drawdice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class cardView extends AppCompatActivity implements View.OnClickListener {

    private ImageButton drawButton;
    private ImageView currentCard;
    private ImageView previousCard;

    //Korttipakka
    private final static int[] deck = new int[] {
            R.drawable.ic_ca, R.drawable.ic_c2, R.drawable.ic_c3, R.drawable.ic_c4, R.drawable.ic_c5, R.drawable.ic_c6, R.drawable.ic_c7, R.drawable.ic_c8, R.drawable.ic_c9, R.drawable.ic_c10, R.drawable.ic_cj, R.drawable.ic_cq, R.drawable.ic_ck,
            R.drawable.ic_ha, R.drawable.ic_h2, R.drawable.ic_h3, R.drawable.ic_h4, R.drawable.ic_h5, R.drawable.ic_h6, R.drawable.ic_h7, R.drawable.ic_h8, R.drawable.ic_h9, R.drawable.ic_h10, R.drawable.ic_hj, R.drawable.ic_hq, R.drawable.ic_hk,
            R.drawable.ic_sa, R.drawable.ic_s2, R.drawable.ic_s3, R.drawable.ic_s4, R.drawable.ic_s5, R.drawable.ic_s6, R.drawable.ic_s7, R.drawable.ic_s8, R.drawable.ic_s9, R.drawable.ic_s10, R.drawable.ic_sj, R.drawable.ic_sq, R.drawable.ic_sk,
            R.drawable.ic_da, R.drawable.ic_d2, R.drawable.ic_d3, R.drawable.ic_d4, R.drawable.ic_d5, R.drawable.ic_d6, R.drawable.ic_d7, R.drawable.ic_d8, R.drawable.ic_d9, R.drawable.ic_d10, R.drawable.ic_dj, R.drawable.ic_dq, R.drawable.ic_dk
    };

    //Arraylist useammalle korttipakalle
    private static List<Integer> decks = new ArrayList<>();

    //Montako korttipakkaa
    private int getDeckAmount(){
        SharedPreferences pref = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);

        return pref.getInt("deckAmount", 1);
    }

    //Käytetäänkö jokereita
    private boolean getJokerBool(){
        SharedPreferences pref = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        return pref.getBoolean("jokers", true);
    }

    //Palautetaanko vedetty kortti pakkaan
    private boolean getReturnBool(){
        SharedPreferences pref = getSharedPreferences(getString(R.string.appPref), MODE_PRIVATE);
        return pref.getBoolean("returnCards", true);
    }

    //Rakennetaan pakka edellä asetettujen arvojen perusteella
    private static void deckBuilder(int deck_amount, boolean joker) {
        for (int i=1; i<=deck_amount; i++){
            for (int j=0; j<deck.length; j++)
                decks.add(deck[j]);
            if (joker){
                decks.add(R.drawable.ic_j1);
                decks.add(R.drawable.ic_j2);
            }
        }
    }

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

        deckBuilder(getDeckAmount(), getJokerBool());
    }

    //Yläpalkki korttinäkymään
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarcards, menu);

       return true;
    }

    //Yläpalkin toiminnot
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Korttipakan sekoitus
            case R.id.action_refresh:
                resetDeck();
                Toast.makeText(getApplicationContext(), "Deck reshuffled", Toast.LENGTH_SHORT).show();
                return true;
            //Asetukset
            case R.id.action_settings:
                Intent intentSettings = new Intent(this, cardSettings.class);
                cardView.this.startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Seuraava kortti, joka vedetään satunnaisesti pakasta.  Sekoittaessa pakka myös seuraava kortti nollataan
    int next = -1;
    private boolean firstDraw = true;
    private void resetDeck(){
        next = -1;
        firstDraw = true;
        decks.clear();
        deckBuilder(getDeckAmount(), getJokerBool());
        drawButton.setImageResource(R.drawable.ic_background);
        if (!drawButton.isClickable()) drawButton.setClickable(true);

        previousCard.setImageResource(R.drawable.ic_previouscard);
        currentCard.setImageResource(R.drawable.ic_draw_empty);
    }

    private final static Random random = new Random();

    @Override
    public void onClick(View v) {
        //Jos pakassa yli yksi kortti jäljellä
        if (decks.size() > 1)  drawCard(false);
        //Vain yksi kortti jäljellä: asetetaan nykyiseksi kortiksi viimeinen kortti. Asetetaan korttien vetämisnappula epäaktiiviseksi
        else {
            currentCard.setImageResource(decks.get(0));
            int imageTag = (Integer)currentCard.getTag();
            previousCard.setImageResource(imageTag);
            drawButton.setImageResource(R.drawable.ic_empty);
            drawButton.setClickable(false);
        }
    }



    //Otetaan pakasta kortti, jos pakka ei tyhjä. Jos ei kyseessä ensimmäinen veto, niin näytetään myös edellinen kortti
    private void drawCard(boolean lastCard) {
        if (!firstDraw){
            int imageTag = (Integer)currentCard.getTag();
            previousCard.setImageResource(imageTag);
        }
        else firstDraw = false;

            next = random.nextInt(decks.size());

        currentCard.setImageResource(decks.get(next));
        currentCard.setTag(decks.get(next));

        //Jos korttia ei palauteta pakkaan
        if (!getReturnBool())
            decks.remove(next);
    }
}
