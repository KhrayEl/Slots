package com.khrayel.main_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.khrayel.slots.R;
import com.khrayel.slots.SlotsFragment;

public class GameSelector extends Fragment implements View.OnClickListener
    {
        public GameSelector ()
            {
                super(R.layout.main_game_selector_fragment);

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

        public void ChangeToTests ()
            {
                FragmentManager fm = getParentFragmentManager();

                fm.beginTransaction()
                        .replace(R.id.fragment_container_view, new TestsFragment())
                        .addToBackStack(null)  // uncomment this line if you want to be able to return to the prev. fragment with "back" button
                        .commit()
                ;
            }


        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState)
            {

                View v = inflater.inflate(R.layout.main_game_selector_fragment, container, false);

                Button slots_StartButton = (Button) v.findViewById(R.id.slots_StartButton);
                slots_StartButton.setOnClickListener(this);

//                Button tests_StartButton = (Button) v.findViewById(R.id.tests_StartButton);
//                tests_StartButton.setOnClickListener(this);


                return v;
            }

        @Override
        public void onClick (View v)
            {
                switch (v.getId())
                    {
                        case R.id.slots_StartButton:
                        {
                            ChangeToSlots();
                            break;
                        }
//                        case R.id.tests_StartButton:
//                        {
//                            ChangeToTests();
//                            break;
//                        }
                    }

            }
    }



