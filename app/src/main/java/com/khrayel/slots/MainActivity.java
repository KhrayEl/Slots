package com.khrayel.slots;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Logic logic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
MainFragment mainFragment;


        int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int width = screen_width / 25;

        Space sp1 = findViewById(R.id.space_roll_1);
        sp1.setMinimumWidth(width);
        Space sp2 = findViewById(R.id.space_roll_2);
        sp2.setMinimumWidth(width);
        Space sp3 = findViewById(R.id.space_roll_3);
        sp3.setMinimumWidth(width);
        Space sp4 = findViewById(R.id.space_roll_4);
        sp4.setMinimumWidth(width);

        TextView tv1 = findViewById(R.id.text_roll1);
        tv1.setWidth(width * 7);
        TextView tv2 = findViewById(R.id.text_roll2);
        tv2.setWidth(width * 7);
        TextView tv3 = findViewById(R.id.text_roll3);
        tv3.setWidth(width * 7);

        TextView record = findViewById(R.id.text_record);
        String text = SaveLoad.LoadDataFromFile(this);
        record.setText(text);
    }

    public void OnClickSpinButton(View spinbutton_view) {

    }


    @Override
    public void onClick(View v) {

    }



    public void OnClickPlusButton(View view) {

        findViewById(R.id.button_bet_plus).setClickable(false);
        findViewById(R.id.button_bet_minus).setClickable(false);

        TextView tv = findViewById(R.id.text_bet_number);

        int bet = Integer.parseInt(tv.getText().toString());
        bet = Logic.IncreaseBet(bet);
        tv.setText(Integer.toString(bet));

        findViewById(R.id.button_bet_plus).setClickable(true);
        findViewById(R.id.button_bet_minus).setClickable(true);

    }



    public void OnClickMinusButton(View view) {

        findViewById(R.id.button_bet_plus).setClickable(false);
        findViewById(R.id.button_bet_minus).setClickable(false);

        TextView tv = findViewById(R.id.text_bet_number);

        int bet = Integer.parseInt(tv.getText().toString());
        bet = Logic.DecreaseBet(bet);
        tv.setText(Integer.toString(bet));


        findViewById(R.id.button_bet_plus).setClickable(true);
        findViewById(R.id.button_bet_minus).setClickable(true);

    }



    public void OnClickRestart(View view) {
        TextView tv_bet = findViewById(R.id.text_bet_number);
        tv_bet.setText(R.string.default_bet);

        TextView tv_score = findViewById(R.id.text_Score);
        tv_score.setText(R.string.default_score);

        findViewById(R.id.layout_bet).setVisibility(View.VISIBLE);
        findViewById(R.id.layout_restart).setVisibility(View.GONE);

    }



}
