package com.khrayel.slots;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends FragmentActivity
    {

        @Override
        protected void onCreate (Bundle savedInstanceState)
            {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.main_activity);

                if (getActionBar() != null)
                    {
                        getActionBar().hide();
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


        @Override
        protected void onDestroy ()
            {
                super.onDestroy();

            }
    }