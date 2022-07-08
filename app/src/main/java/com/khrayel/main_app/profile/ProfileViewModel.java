package com.khrayel.main_app.profile;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel implements Observable
    {
        private final ProfileModel profileModel = new ProfileModel();

        private final ObservableField<String> name = new ObservableField<>();

        public String getName()
            {
                return profileModel.getName();
            }

        public void setName(String text)
            {
                profileModel.setName(text);
            }

        public boolean checkIfRemoteProfileExists()
            {
                //TODO check if remote profile exists
                if (true)
                    {
                        return true;
                    }
                return false;

            }

        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback)
            {

            }

        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback)
            {

            }


        private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

        void notifyPropertyChanged(int fieldId)
            {
                callbacks.notifyCallbacks(this, fieldId, null);
            }

    }
