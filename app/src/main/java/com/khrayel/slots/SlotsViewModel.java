package com.khrayel.slots;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SlotsViewModel extends ViewModel implements Observable
    {

        private SlotsModel slotsModel = new SlotsModel();


        public String getSlots_data_filename ()
            {
                return this.slotsModel.getSlots_data_filename();
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


        public void setFieldsFromString (String string)
            {
                slotsModel.setFieldsFromString(string);
            }


        private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

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

                String jsonString = json.toString();


                //next, saves the file:
                // slotsModel.getRepo().WriteJsonStringToFile(jsonString);
                return jsonString;
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
