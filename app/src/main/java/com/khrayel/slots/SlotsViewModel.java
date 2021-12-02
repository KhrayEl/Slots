package com.khrayel.slots;

import android.view.View;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
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

        public void BetIncrease (View view)
            {
                this.slotsModel.increaseBet_model();
//                notifyPropertyChanged(BR.currentBet);
                notifyChange();
            }

        public void BetDecrease (View view)
            {
                slotsModel.decreaseBet_model();
//                notifyPropertyChanged(BR.currentBet);
                notifyChange();
            }

        public void OnClickRestart (View view)
            {
            }

        ;

        public void OnClickSpin (View view)
            {

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

    }
