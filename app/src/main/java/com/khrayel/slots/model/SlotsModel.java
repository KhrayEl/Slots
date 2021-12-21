package com.khrayel.slots.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableLong;

import com.khrayel.slots.model.SlotsDefaultValues;
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


        private SlotsRollsValues slotsRollsValues;


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

        public int getRoll1_drawable_id (SlotsRollsValues.DrawableType type)
            {
                switch (type)
                    {
                        case DRAWABLE_TYPE_EMOJI:
                            return Objects.requireNonNull(roll1.get()).drawable_emoji;
                        case DRAWABLE_TYPE_GEM:
                            return Objects.requireNonNull(roll1.get()).drawable_gem;
                    }
                return 0;
            }

        public int getRoll2_drawable_id (SlotsRollsValues.DrawableType type)
            {
                switch (type)
                    {
                        case DRAWABLE_TYPE_EMOJI:
                            return Objects.requireNonNull(roll2.get()).drawable_emoji;
                        case DRAWABLE_TYPE_GEM:
                            return Objects.requireNonNull(roll2.get()).drawable_gem;
                    }
                return 0;            }

        public int getRoll3_drawable_id (SlotsRollsValues.DrawableType type)
            {
                switch (type)
                    {
                        case DRAWABLE_TYPE_EMOJI:
                            return Objects.requireNonNull(roll3.get()).drawable_emoji;
                        case DRAWABLE_TYPE_GEM:
                            return Objects.requireNonNull(roll3.get()).drawable_gem;
                    }
                return 0;            }


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


                roll1.set(SlotsRollsValues.getRandomRoll(upto));
                roll2.set(SlotsRollsValues.getRandomRoll(upto));
                roll3.set(SlotsRollsValues.getRandomRoll(upto));
//                roll1.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll2.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));
//                roll3.set(ThreadLocalRandom.current().nextInt(0, 9 + 1));

                int mult = GetResultMultiplier(
                        Objects.requireNonNull(roll1.get()).integer,
                        Objects.requireNonNull(roll2.get()).integer,
                        Objects.requireNonNull(roll3.get()).integer);
                if (mult > 0)
                    {
                        Win(mult);
                    } else Loss();

            }

        public void ResetValues ()
            {
                score.set(SlotsDefaultValues.default_score.getValue());
                current_bet.set(SlotsDefaultValues.default_bet.getValue());
                roll1.set(SlotsRollsValues.getDefaultRoll());
                roll2.set(SlotsRollsValues.getDefaultRoll());
                roll3.set(SlotsRollsValues.getDefaultRoll());
                change_in_score.set(SlotsDefaultValues.default_change_in_score.getValue());
            }
    }
