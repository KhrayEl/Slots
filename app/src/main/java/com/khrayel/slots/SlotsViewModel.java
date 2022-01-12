package com.khrayel.slots;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Objects;

public class SlotsViewModel extends ViewModel implements Observable
    {

        private final SlotsModel slotsModel = new SlotsModel();


        public String getSlots_data_filename ()
            {
                return this.slotsModel.getSlots_data_filename();
            }
        public String getSlots_options_filename(){
            return "slots_options";
        }

        // CURRENT SCORE
        @Bindable
        public long getScore ()
            {
                return this.slotsModel.getScore();
            }

        //        public void setScore (long new_score)
//            {
//                this.slotsModel.setScore(new_score);
//            }
        public long getChangeInScore ()
            {
                return this.slotsModel.getChangeInScore();
            }


        // RECORD SCORE
        @Bindable
        public long getRecord ()
            {
                return this.slotsModel.getRecord();
            }


        // BET
        @Bindable
        public long getBet ()
            {
                return slotsModel.getBet();
            }


        // DRAWABLE STYLE
        private SlotsRollsValues.DrawableType selected_drawable_type = SlotsRollsValues.DrawableType.DRAWABLE_TYPE_EMOJI;

        public SlotsRollsValues.DrawableType getSelected_drawable_type ()
            {
                return selected_drawable_type;
            }

        public void setSelected_drawable_type (SlotsRollsValues.DrawableType selected_drawable_type)
            {
                this.selected_drawable_type = selected_drawable_type;
            }

        // ROLLS
        @Bindable
        public int getRoll1 ()
            {
                //return slotsModel.getRoll1_string();
                return slotsModel.getRoll1();
            }

        @Bindable
        public int getRoll2 ()
            {
                return slotsModel.getRoll2();
            }

        @Bindable
        public int getRoll3 ()
            {
                return slotsModel.getRoll3();
            }


        public int getWinFlag ()
            {
                return slotsModel.getWinFlag();
            }

        /**
         * @return true if gameover
         */
        @Bindable
        public boolean getGameOver () //
            {
                boolean gameover = false;
                if (slotsModel.getScore() <= 0)
                    {
                        gameover = true;
                    }
                notifyChange();
                return gameover;
            }

        private MediaPlayer background_music_player = new MediaPlayer();

        private boolean sound_enabled = true;

        private float sound_volume=0f;

        public float getSoundVolume ()
            {
                return sound_volume;
            }

        public void setSoundVolume (float sound_volume)
            {
                this.sound_volume = sound_volume;
                background_music_player.setVolume(sound_volume, sound_volume);
                notifyChange();
            }

        public void CreateBackgroundMusicPlayer (Context context)
            {
                background_music_player = MediaPlayer.create(context, R.raw.music_1);
                background_music_player.setLooping(true);
                background_music_player.setVolume(sound_volume, sound_volume);
                background_music_player.start();
            }


        public boolean getSoundEnabled ()
            {
                return sound_enabled;
            }

        public void setSoundEnabled (boolean sound_enabled)
            {
                this.sound_enabled = sound_enabled;
            }

        public void ToggleSound ()
            {
                if (sound_enabled)
                    {
                        background_music_player.pause();
                    } else
                    {
                        background_music_player.start();
                    }
                sound_enabled = !sound_enabled;
            }


        public void setFieldsFromString (String string)
            {
                slotsModel.setFieldsFromString(string);
            }

        public void loadOptionsFromString (String string)
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
                                float volume;
                                volume = BigDecimal.valueOf(Objects.requireNonNull(json).getDouble("volume")).floatValue();
                                setSoundVolume(volume);

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        try
                            {
                                boolean sound_enabled;
                                sound_enabled = Objects.requireNonNull(json).getBoolean("sound_enabled");
                                setSoundEnabled(sound_enabled);

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }


                    }
            }




        private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

        @Override
        public void addOnPropertyChangedCallback (
                Observable.OnPropertyChangedCallback callback)
            {
                callbacks.add(callback);
            }

        @Override
        public void removeOnPropertyChangedCallback (
                Observable.OnPropertyChangedCallback callback)
            {
                callbacks.remove(callback);
            }

        /**
         * Notifies observers that all properties of this instance have changed.
         */
        void notifyChange ()
            {
                callbacks.notifyCallbacks(this, 0, null);
                //    WriteFieldsToJSON();
            }

        /**
         * Notifies observers that a specific property has changed. The getter for the
         * property that changes should be marked with the @Bindable annotation to
         * generate a field in the BR class to be used as the fieldId parameter.
         *
         * @param fieldId The generated BR id for the Bindable field.
         */
        void notifyPropertyChanged (int fieldId)
            {
                callbacks.notifyCallbacks(this, fieldId, null);
            }


        String WriteFieldsToJsonString ()
            {
                //  JSONArray jsonArray = new JSONArray();
                //jsonArray.put(record);


                JSONObject json = new JSONObject(); //creates main json
                try
                    {
                        json.put("record", slotsModel.getRecord());
                        json.put("score", slotsModel.getScore());
                        json.put("bet", slotsModel.getBet());
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

//                JSONObject valuesJson = new JSONObject(); //another object
//                valuesJson.put("Car", "Maruti");
//
//                json.put("Values", valuesJson); //puts a json inside another


                //next, saves the file:
                // slotsModel.getRepo().WriteJsonStringToFile(jsonString);
                return json.toString();
            }

        String WriteOptionsToJsonString ()
            {
                //  JSONArray jsonArray = new JSONArray();
                //jsonArray.put(record);


                JSONObject json = new JSONObject(); //creates main json
                try
                    {
                        json.put("volume", getSoundVolume());
                        json.put("sound_enabled", getSoundEnabled());

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

//                JSONObject valuesJson = new JSONObject(); //another object
//                valuesJson.put("Car", "Maruti");
//
//                json.put("Values", valuesJson); //puts a json inside another


                //next, saves the file:
                // slotsModel.getRepo().WriteJsonStringToFile(jsonString);

                return json.toString();
            }


//        void ReadData ()
//            {
//                JSONArray json = slotsModel.getRepo().GetJsonFromFile();
//                if (json != null)
//                    {
//                        slotsModel.setFieldsFromJSON(json);
//                    }
//            }


        public void BetIncrease ()
            {
                this.slotsModel.increaseBet_model();
//                notifyPropertyChanged(BR.currentBet);
                notifyChange();
            }

        public void BetDecrease ()
            {
                slotsModel.decreaseBet_model();
//                notifyPropertyChanged(BR.currentBet);
                notifyChange();
            }

        public void getNewRolls ()
            {
                slotsModel.GetNewRolls();
                notifyChange();
            }

        public void Restart ()
            {
                slotsModel.ResetValues();
            }
    }
