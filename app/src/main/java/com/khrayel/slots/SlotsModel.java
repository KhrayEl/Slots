package com.khrayel.slots;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.databinding.ObservableParcelable;
import androidx.databinding.library.baseAdapters.BR;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
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


        private SlotsRollsValues slotsRollsValues;


        private int default_roll = 7;

//        private final ObservableField <String> roll1_string = new ObservableField<>(SlotsRollsValues.ZERO.string_as_html_entity);
//        private final ObservableField <String> roll2_string = new ObservableField<>(SlotsRollsValues.ZERO.string_as_html_entity);
//        private final ObservableField <String> roll3_string = new ObservableField<>(SlotsRollsValues.ZERO.string_as_html_entity);

        public String getRoll1_string ()
            {
                return Objects.requireNonNull(roll1.get()).string_as_html_entity;
            }

        public String getRoll2_string ()
            {
                return Objects.requireNonNull(roll2.get()).string_as_html_entity;
            }

        public String getRoll3_string ()
            {
                return Objects.requireNonNull(roll3.get()).string_as_html_entity;
            }


        private final ObservableField<SlotsRollsValues> roll1 = new ObservableField<>(SlotsRollsValues.getDefaultRoll());
        private final ObservableField<SlotsRollsValues> roll2 = new ObservableField<>(SlotsRollsValues.getDefaultRoll());
        private final ObservableField<SlotsRollsValues> roll3 = new ObservableField<>(SlotsRollsValues.getDefaultRoll());

//        //private int roll1 = SlotsRollsValues.ZERO.numeric;
//        private ObservableInt roll2 = new ObservableInt(default_roll);
//        private ObservableInt roll3 = new ObservableInt(default_roll);


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
                //long new_bet = current_bet.get() + default_bet;
                long new_bet = current_bet.get();


                long max = score.get();
                int step = 1;

                while (new_bet / 10 >= step)
                    {
                        step = step * 10;
                    }
                if (max >= new_bet + step)
                    {
                        new_bet = new_bet + step;
                    }

                setBet(new_bet);
            }


        public void decreaseBet_model ()
            {
                //long new_bet = current_bet.get();
                //setBet(new_bet);

                if (current_bet.get() == 1 || current_bet.get() == 0)
                    {
                        //current_bet.set(new_bet);
                        return;
                    }

                int step = 1;

                while (current_bet.get() / 10 > step)
                    {
                        step = step * 10;
                    }
                current_bet.set(current_bet.get() - step);

            }


        private void Win (int multiplier)
            {
                long new_score = score.get() + current_bet.get() * multiplier;
                setScore(new_score);
            }

        private void Loss ()
            {
                long new_score = score.get() - current_bet.get();
                if (new_score != 0)
                    {
                        while (new_score < current_bet.get())
                            {
                                decreaseBet_model();
                            }
                    }
                setScore(new_score);
            }


        public void GetNewRolls ()
            {
                roll1.set(SlotsRollsValues.getRandomRoll());
                roll2.set(SlotsRollsValues.getRandomRoll());
                roll3.set(SlotsRollsValues.getRandomRoll());
//                roll1.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll2.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll3.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));

                int mult = GetResultMultiplier(
                        Objects.requireNonNull(roll1.get()).numeric,
                        Objects.requireNonNull(roll2.get()).numeric,
                        Objects.requireNonNull(roll3.get()).numeric);
                if (mult > 0)
                    {
                        Win(mult);
                    } else Loss();

            }

        public void ResetValues ()
            {
                score.set(default_score);
                current_bet.set(default_bet);
                roll1.set(SlotsRollsValues.getDefaultRoll());
                roll2.set(SlotsRollsValues.getDefaultRoll());
                roll3.set(SlotsRollsValues.getDefaultRoll());
            }
    }
