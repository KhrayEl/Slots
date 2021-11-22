package com.khrayel.slots;

import android.view.View;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModel;

public class SlotsViewModel extends ViewModel implements Observable
    {
        private SlotsModel slotsModel = new SlotsModel();


        // CURRENT SCORE
        @Bindable
        public long getScore ()
            {
                return this.slotsModel.getScore();
            }

        public void setScore (long new_score)
            {
                this.slotsModel.setScore(new_score);
            }


        // RECORD SCORE
        @Bindable
        public long getRecord ()
            {
                return this.slotsModel.getRecord();
            }



        // BET
        @Bindable
        public long getCurrentBet ()
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


    }
