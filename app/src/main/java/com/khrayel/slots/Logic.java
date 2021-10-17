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

public class Logic {
    static int UpdateBet(int new_score_final, int bet)
    {
        if (new_score_final <= 0) {
            GameOver();
            return 0;
        }

        if (bet > new_score_final) {
            bet = new_score_final;
        }
        {
            return bet;
        }
    }


    static void UpdateRecord(int max, int number, Context context) {

        if (number > max) {
            SaveLoad.SaveDataToFIle(number, context);
        }
    }


    static int DecreaseBet(int bet)
    {
        if (bet == 1 || bet == 0) {
            return bet;
        }

        int step = 1;

        while (bet / 10 > step) {
            step = step * 10;
        }
        bet = bet - step;

        return bet;

    }

    static int IncreaseBet(int bet)
    {
        TextView score_view = findViewById(R.id.text_Score);
        int max = Integer.parseInt(score_view.getText().toString());

        int step = 1;

        while (bet / 10 >= step) {
            step = step * 10;
        }
        if (max >= bet + step) {
            bet = bet + step;
        }

        return bet;
    }


    static int Get_reward(int bet, int roll1, int roll2, int roll3) {
        if (roll1 == roll2 && roll2 == roll3) {
            if (roll1 == 7) {
                return bet * 1000;
            } else {
                return bet * 100;
            }
        } else {
            if (roll1 == roll2 || roll2 == roll3 || roll1 == roll3) {
                return bet * 10;
            }

            return 0;
        }
    }

}