package com.khrayel.slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.khrayel.slots.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    {

        @Override
        protected void onCreate (Bundle savedInstanceState)
            {

                super.onCreate(savedInstanceState);
//                setContentView(R.layout.activity_main);
                if (getSupportActionBar() != null)
                    {
                        getSupportActionBar().hide();
                    }

                SlotsViewModel slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);

                ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
                binding.setLifecycleOwner(this);
                binding.setSlotsviewmodel(slotsviewmodel);
//                binding.setLifecycleOwner(this);

            }


//
//        TextView record = findViewById(R.id.text_record);
//        String text=LoadDataFromFile();
//        record.setText(text);
    }