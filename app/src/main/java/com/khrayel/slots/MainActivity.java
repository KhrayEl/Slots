package com.khrayel.slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity
    {

        @Override
        protected void onCreate (Bundle savedInstanceState)
            {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                if (getSupportActionBar() != null)
                    {
                        getSupportActionBar().hide();
                    }

                if (savedInstanceState == null)
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setReorderingAllowed(true);
                        ft.replace(R.id.fragment_container_view, new GameSelector());
//                                ft.add(R.id.fragment_container_view, SlotsFragment.class, null);
//                                ft.addToBackStack(null);
                        ft.commit();
                    }
            }


//
//        TextView record = findViewById(R.id.text_record);
//        String text=LoadDataFromFile();
//        record.setText(text);
    }