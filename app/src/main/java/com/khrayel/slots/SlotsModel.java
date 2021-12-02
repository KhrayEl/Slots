package com.khrayel.slots;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.databinding.library.baseAdapters.BR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SlotsModel extends BaseObservable implements SlotsWinConditions
    {

        public SlotsModel (
                          )
            {
                // ReadData();
            }

        private String slots_data_filename = "slots_data";

        public String getSlots_data_filename ()
            {
                return slots_data_filename;
            }


        // CURRENT SCORE
        private long default_score = 100;
        private ObservableLong score = new ObservableLong(default_score);

        public long getScore ()
            {
                return score.get();
            }

        public void setScore (long new_score)
            {
                score.set(new_score);
                setRecord(new_score);
            }


        // RECORD SCORE
        private long default_record = 100;
        private ObservableLong record = new ObservableLong(0);

        public long getRecord ()
            {
                return record.get();
            }

        public void setRecord (long new_record)
            {
                if (new_record > default_score &&
                        record.get() < new_record)
                    {
                        record.set(new_record);
                    }
            }


        // BET
        private long min_bet = 1;
        private long default_bet = 10;
        private ObservableLong current_bet = new ObservableLong(default_bet);

        public long getBet ()
            {
                return current_bet.get();
            }

        private void setBet (long new_bet)
            {
                if (new_bet >= min_bet &&
                        new_bet <= score.get()
                ) current_bet.set(new_bet);
            }




        private int default_roll = 7;
        ObservableInt roll1 = new ObservableInt(default_roll);
        ObservableInt roll2 = new ObservableInt(default_roll);
        ObservableInt roll3 = new ObservableInt(default_roll);

        public int getRoll1 ()
            {
                return roll1.get();
            }

        public int getRoll2 ()
            {
                return roll2.get();
            }

        public int getRoll3 ()
            {
                return roll3.get();
            }




        public void setFieldsFromString (String string)
            {
                JSONObject json = null;
                if (!string.equals(""))
                    {

                        try
                            {
                                json = new JSONObject(string);
                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        try
                            {
                                long record;
                                record = json.getLong("record"); // TODO get RECORD value from json
                                setRecord(record);

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }


                        try
                            {
                                long score;
                                score = json.getLong("score"); // TODO get SCORE value from json
                                setScore(score);

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }


                        try
                            {
                                long bet;
                                bet = json.getInt("bet"); // TODO get BET value from json
                                setBet(bet);

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                    }

/*
        void ReadData ()
            {
                JSONObject json = repo.GetJsonFromFile();
                String string=
                if (json != null)
                    {
                        setFieldsFromString(json);
                    }
            }
*/


            }

        public void increaseBet_model ()
            {
                long new_bet = current_bet.get() + default_bet;
                setBet(new_bet);
            }


        public void decreaseBet_model ()
            {
                long new_bet = current_bet.get() - default_bet;
                setBet(new_bet);
            }


        private void Win (int multiplier)
            {
                long new_score = score.get() + current_bet.get()*multiplier;
                setScore(new_score);
            }

        private void Loss ()
            {
                long new_score = score.get() - current_bet.get();
                setScore(new_score);
            }


        public void GetNewRolls ()
            {
                roll1.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
                roll2.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
                roll3.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));

                int mult=GetResultMultiplier(roll1.get(), roll2.get(), roll3.get());
                if (mult>0)
                    {
                        Win(mult);
                    } else Loss();

            }
    }
