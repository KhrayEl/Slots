package com.khrayel.slots;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class SpinButtonFragment extends Fragment {

    public SpinButtonFragment(){
        super(R.layout.fragment_spin_button);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button spinButton = (Button) view.findViewById(R.id.button_spin);
        //TextView updateBox = (TextView) view.findViewById(R.id.dateTextView);

        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setClickable(false);

                final Handler handler1 = new Handler();
                final Handler handler2 = new Handler();
                final Handler handler3 = new Handler();
                final Handler handler4 = new Handler();


                TextView tv1 = findViewById(R.id.text_roll1);
                tv1.setText("");
                TextView tv2 = findViewById(R.id.text_roll2);
                tv2.setText("");
                TextView tv3 = findViewById(R.id.text_roll3);
                tv3.setText("");
                TextView score_view = findViewById(R.id.text_Score);

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

                final int new_score_final = new_score + Logic.Get_reward(bet, num1, num2, num3);

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

                    }
                }, delay * 3);


                handler4.postDelayed(new Runnable() {
                    public void run() {
                        score_view.setText(Integer.toString(new_score_final));
                        int new_bet = Logic.UpdateBet(new_score_final, bet);
                        bet_view.setText(Integer.toString(new_bet));
                        Logic.UpdateRecord(new_score_final);
                        v.setClickable(true);
                    }
                }, delay * 4);
            }
        });
    }
}