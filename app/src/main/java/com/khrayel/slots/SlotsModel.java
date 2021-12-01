package com.khrayel.slots;

import android.content.Context;

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

public class SlotsModel extends BaseObservable implements DataOperations
    {
        public SlotsModel ()
            {
                ReadData();
            }

        // SAVED FILE WITH MODEL DATA
        private String slots_data_filename = "slots_data";


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


        void setFieldsFromJSON (JSONArray json)
            {
                try
                    {
                        long record;
                        record = json.getLong(0); // TODO get RECORD value from json
                        setRecord(record);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }


                try
                    {
                        long score;
                        score = json.getLong(1); // TODO get SCORE value from json
                        setRecord(score);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }


                try
                    {
                        long bet;
                        bet = json.getInt(2); // TODO get BET value from json
                        setRecord(bet);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

            }

        void WriteFieldsToJSON ()
            {
                //  JSONArray jsonArray = new JSONArray();
                //jsonArray.put(record);


                JSONObject json = new JSONObject(); //creates main json
                try
                    {
                        json.put("record", record);
                        json.put("score", score);
                        json.put("bet", current_bet);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

//                JSONObject valuesJson = new JSONObject(); //another object
//                valuesJson.put("Car", "Maruti");
//
//                json.put("Values", valuesJson); //puts a json inside another

                String jsonString = json.toString();


                //next, saves the file:

                writeStringToFile(jsonString, slots_data_filename);

            }

        JSONArray GetJSONFromFile ()
            {
                String read_string = readStringFromFile(slots_data_filename);
                JSONArray jsonArray = null;
                JSONObject jsonObject = null;
                if (!read_string.equals("")){

                    try
                    {
                        jsonObject = new JSONObject(read_string);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                return jsonArray;

            }


        void ReadData ()
            {
                JSONArray json = GetJSONFromFile();
                if (json != null)
                    {
                        setFieldsFromJSON(json);
                    }
            }


    }
