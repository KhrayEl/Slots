package com.khrayel.slots;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;

import com.khrayel.slots.SlotsDefaultValues;
import com.khrayel.slots.SlotsRollsValues;
import com.khrayel.slots.SlotsWinConditions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class SlotsModel extends BaseObservable implements SlotsWinConditions
    {

        public SlotsModel ()
            {
            }


        private String slots_data_filename = "slots_data";


        public String getSlots_data_filename ()
            {
                return slots_data_filename;
            }


        // CURRENT SCORE
        private ObservableLong score = new ObservableLong(SlotsDefaultValues.default_score.getValue());


        public long getScore ()
            {
                return score.get();
            }

        public void setScore (long new_score)
            {
                score.set(new_score);
                setRecord(new_score);
            }

        private ObservableLong change_in_score = new ObservableLong(SlotsDefaultValues.default_change_in_score.getValue());

        public long getChangeInScore ()
            {
                return this.change_in_score.get();
            }

        // RECORD SCORE
        private ObservableLong record = new ObservableLong(SlotsDefaultValues.default_record.getValue());

        public long getRecord ()
            {
                return record.get();
            }

        public void setRecord (long new_record)
            {
                if (new_record > SlotsDefaultValues.default_score.getValue() &&
                        record.get() < new_record)
                    {
                        record.set(new_record);
                    }
            }


        // BET
        private ObservableLong current_bet = new ObservableLong(SlotsDefaultValues.default_bet.getValue());

        public long getBet ()
            {
                return current_bet.get();
            }

        private void setBet (long new_bet)
            {
                if (new_bet >= SlotsDefaultValues.min_bet.getValue() &&
                        new_bet <= score.get()
                ) current_bet.set(new_bet);
            }


        public int getWinFlag ()
            {
                return GetFlagWinningRolls(roll1.get(), roll2.get(), roll3.get());
            }

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


        private final ObservableInt roll1 = new ObservableInt(SlotsRollsValues.getDefaultRoll().integer);
        private final ObservableInt roll2 = new ObservableInt(SlotsRollsValues.getDefaultRoll().integer);
        private final ObservableInt roll3 = new ObservableInt(SlotsRollsValues.getDefaultRoll().integer);


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

                if (current_bet.get() == SlotsDefaultValues.min_bet.getValue() || current_bet.get() == 0)
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
                change_in_score.set(new_score - score.get());
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
                change_in_score.set(new_score - score.get());
                setScore(new_score);
            }


        public void GetNewRolls ()
            {
                long temp_score = score.get();

                int upto = SlotsDefaultValues.DEFAULT_ROLL_MAX.getValue();
                int divisor = SlotsDefaultValues.SCORE_DIVISOR_FOR_ROLL_INCREMENT.getValue();
                while (temp_score / divisor >= divisor)
                    {
                        temp_score = temp_score / divisor;
                        upto = upto + SlotsDefaultValues.ROLL_INCREMENT.getValue();
                    }


                roll1.set(SlotsRollsValues.getRandomRoll(upto).integer);
                roll2.set(SlotsRollsValues.getRandomRoll(upto).integer);
                roll3.set(SlotsRollsValues.getRandomRoll(upto).integer);
//                roll1.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll2.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll3.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));

                int mult = GetResultMultiplier(
                        (roll1.get()),
                        (roll2.get()),
                        (roll3.get()));
                if (mult > 0)
                    {
                        Win(mult);
                    } else Loss();

            }

        public void ResetValues ()
            {
                score.set(SlotsDefaultValues.default_score.getValue());
                current_bet.set(SlotsDefaultValues.default_bet.getValue());
                roll1.set(SlotsRollsValues.getDefaultRoll().integer);
                roll2.set(SlotsRollsValues.getDefaultRoll().integer);
                roll3.set(SlotsRollsValues.getDefaultRoll().integer);
                change_in_score.set(SlotsDefaultValues.default_change_in_score.getValue());
            }
    }
