package com.khrayel.slots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GameSelector extends Fragment implements View.OnClickListener
    {
        public GameSelector ()
            {
                super(R.layout.fragment_game_selector);

            }

        public void ChangeToSlots ()
            {
                FragmentManager fm = getParentFragmentManager();

                fm.beginTransaction()
                        .replace(R.id.fragment_container_view, new SlotsFragment())
                        .addToBackStack(null)  // uncomment this line if you want to be able to return to the prev. fragment with "back" button
                        .commit()
                ;
            }


        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState)
            {

                View v = inflater.inflate(R.layout.fragment_game_selector, container, false);

                Button b = (Button) v.findViewById(R.id.StartButton);
                b.setOnClickListener(this);
                return v;
            }

        @Override
        public void onClick (View v)
            {
                switch (v.getId())
                    {
                        case R.id.StartButton:
                            ChangeToSlots();
                            break;
                    }
            }
    }



