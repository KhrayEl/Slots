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

public class SlotsModel extends BaseObservable
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
        private long default_bet = 10;
        private ObservableLong current_bet = new ObservableLong(default_bet);

        public long getBet ()
            {
                return current_bet.get();
            }

        private void setBet (long new_bet)
            {
                current_bet.set(new_bet);
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


        private void Win ()
            {
                long new_score = score.get() + current_bet.get();
                score.set(new_score);
                setRecord(new_score);
            }

        private void Loss ()
            {
                long new_score = score.get() - current_bet.get();
                score.set(new_score);
            }

        public int[] GetRolls ()
            {
                int roll1 = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                int roll2 = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                int roll3 = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                return (new int[]{roll1, roll2, roll3});
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
    }
