package com.khrayel.slots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int width = screen_width / 25;

        Space sp1 = (Space) findViewById(R.id.space_roll_1);
        sp1.setMinimumWidth(width);
        Space sp2 = (Space) findViewById(R.id.space_roll_2);
        sp2.setMinimumWidth(width);
        Space sp3 = (Space) findViewById(R.id.space_roll_3);
        sp3.setMinimumWidth(width);
        Space sp4 = (Space) findViewById(R.id.space_roll_4);
        sp4.setMinimumWidth(width);

        TextView tv1 = (TextView) findViewById(R.id.text_roll1);
        tv1.setWidth(width * 7);
        TextView tv2 = (TextView) findViewById(R.id.text_roll2);
        tv2.setWidth(width * 7);
        TextView tv3 = (TextView) findViewById(R.id.text_roll3);
        tv3.setWidth(width * 7);
    }

    public void On_SpinButtonClick(View view) {

        view.setClickable(false);

        final Handler handler1 = new Handler();
        final Handler handler2 = new Handler();
        final Handler handler3 = new Handler();
        final Handler handler4 = new Handler();


        TextView tv1 = (TextView) findViewById(R.id.text_roll1);
        tv1.setText("");
        TextView tv2 = (TextView) findViewById(R.id.text_roll2);
        tv2.setText("");
        TextView tv3 = (TextView) findViewById(R.id.text_roll3);
        tv3.setText("");
        TextView score_view = (TextView) findViewById(R.id.text_Score);

        TextView bet_view = findViewById(R.id.text_bet_number);
//        int bet=Integer.parseInt(String.valueOf(bet_view.getText()));

        int bet = Integer.parseInt(bet_view.getText().toString());
        int score = Integer.parseInt(score_view.getText().toString());
        int new_score = score - bet;
        score_view.setText(Integer.toString(new_score));

        int delay = 200;
        int num1 = ThreadLocalRandom.current().nextInt(0, 9 + 1);

        int num2 = ThreadLocalRandom.current().nextInt(0, 9 + 1);

        int num3 = ThreadLocalRandom.current().nextInt(0, 9 + 1);


        handler1.postDelayed(new Runnable() {

            public void run() {
                tv1.setText(Integer.toString(num1));

            }
        }, delay * 1);


        handler2.postDelayed(new Runnable() {

            public void run() {
                tv2.setText(Integer.toString(num2));

            }
        }, delay * 2);

        handler3.postDelayed(new Runnable() {

            public void run() {
                tv3.setText(Integer.toString(num3));

                view.setClickable(true);
            }
        }, delay * 3);
        handler4.postDelayed(new Runnable() {


            public void run() {
                score_view.setText(Integer.toString((new_score + Get_reward(bet, num1, num2, num3))));

            }
        }, delay * 4);
    }


    @Override
    public void onClick(View v) {

    }

    private int Get_reward(int bet, int roll1, int roll2, int roll3) {
        if (roll1 == roll2 && roll2 == roll3) {
            if (roll1 == 7) {
                return bet * 1000;
            } else {
                return bet * 100;
            }
        }
        else         {
            if (roll1 == roll2 || roll2 == roll3 || roll1==roll3) {
                return bet * 10;}

                return 0;
            }
        }
}
