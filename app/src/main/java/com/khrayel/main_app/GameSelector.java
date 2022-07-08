package com.khrayel.main_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.khrayel.main_app.profile.ProfileViewModel;
import com.khrayel.slots.R;
import com.khrayel.slots.SlotsFragment;
import com.khrayel.slots.databinding.MainGameSelectorFragmentBindingImpl;

public class GameSelector extends Fragment implements View.OnClickListener
    {
        ProfileViewModel profileViewModel;

        public GameSelector()
            {
                super(R.layout.main_game_selector_fragment);

            }

        public void ChangeToSlots()
            {
                FragmentManager fm = getParentFragmentManager();

                fm.beginTransaction()
                  .replace(R.id.fragment_container_view, new SlotsFragment())
                  .addToBackStack(null)  // uncomment this line if you want to be able to return to the prev. fragment with "back" button
                  .commit()
                ;
            }

        public void ChangeToTests()
            {
                FragmentManager fm = getParentFragmentManager();

                fm.beginTransaction()
                  .replace(R.id.fragment_container_view, new TestsFragment())
                  .addToBackStack(null)  // uncomment this line if you want to be able to return to the prev. fragment with "back" button
                  .commit()
                ;
            }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
            {

               // View v = inflater.inflate(R.layout.main_game_selector_fragment, container, false);

                profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

                MainGameSelectorFragmentBindingImpl binding = DataBindingUtil.inflate(
                        inflater, R.layout.main_game_selector_fragment, container, false);
                binding.setProfileviewmodel(profileViewModel);
                View v = binding.getRoot();


                Button slots_StartButton = (Button) v.findViewById(R.id.slots_StartButton);
                slots_StartButton.setOnClickListener(this);

                Button profile_button = (Button) v.findViewById(R.id.profile_Button);
                profile_button.setOnClickListener(this);

                Button profile_bad_pass_window_overlay_button = (Button) v.findViewById(R.id.profile_bad_pass_window_overlay_button);
                profile_bad_pass_window_overlay_button.setOnClickListener(this);

                Button profile_show_window_overlay_button = (Button) v.findViewById(R.id.profile_show_window_overlay_button);
                profile_show_window_overlay_button.setOnClickListener(this);

                Button profile_show_window_change_button = (Button) v.findViewById(R.id.profile_show_window_change_button);
                profile_show_window_change_button.setOnClickListener(this);

                Button profile_change_window_overlay_button = (Button) v.findViewById(R.id.profile_change_window_overlay_button);
                profile_change_window_overlay_button.setOnClickListener(this);

                Button profile_change_window_button_next = (Button) v.findViewById(R.id.profile_change_window_button_next);
                profile_change_window_button_next.setOnClickListener(this);

                Button profile_enter_pass_window_overlay_button = (Button) v.findViewById(R.id.profile_enter_pass_window_overlay_button);
                profile_enter_pass_window_overlay_button.setOnClickListener(this);

                Button profile_enter_pass_window_button_next = (Button) v.findViewById(R.id.profile_enter_pass_window_button_next);
                profile_enter_pass_window_button_next.setOnClickListener(this);

                Button profile_wrong_pass_window_overlay_button = (Button) v.findViewById(R.id.profile_wrong_pass_window_overlay_button);
                profile_wrong_pass_window_overlay_button.setOnClickListener(this);


//                Button tests_StartButton = (Button) v.findViewById(R.id.tests_StartButton);
//                tests_StartButton.setOnClickListener(this);


                return v;
            }

        @Override
        public void onClick(View v)
            {
                switch (v.getId())
                    {
                        case R.id.slots_StartButton:
                        {
                            ChangeToSlots();
                            break;
                        }
                        case R.id.profile_Button:{

                            View profile_popup = (v.getRootView()).findViewById(R.id.profile_overlay_popup);
                            profile_popup.setVisibility(View.VISIBLE);
                            break;

                        }
                        case R.id.profile_bad_pass_window_overlay_button:
                        {
                            View current_window = (v.getRootView()).findViewById(R.id.profile_bad_pass_window);
                            current_window.setVisibility(View.GONE);
                            break;
                        }

                        case R.id.profile_show_window_overlay_button:
                        {
                            View current_window = (v.getRootView()).findViewById(R.id.profile_show_window);
                            current_window.setVisibility(View.GONE);
                            break;

                        }
                        case R.id.profile_show_window_change_button:
                        {

                            break;

                        }
                        case R.id.profile_change_window_overlay_button:
                        {
                            View current_window = (v.getRootView()).findViewById(R.id.profile_change_window);
                            current_window.setVisibility(View.GONE);

                            break;

                        }
                        case R.id.profile_change_window_button_next:
                        {

                            break;

                        }
                        case R.id.profile_enter_pass_window_overlay_button:
                        {
                            View current_window = (v.getRootView()).findViewById(R.id.profile_enter_pass_window);
                            current_window.setVisibility(View.GONE);
                            break;

                        }
                        case R.id.profile_enter_pass_window_button_next:
                        {

                            break;

                        }
                        case R.id.profile_wrong_pass_window_overlay_button:
                        {
                            View current_window = (v.getRootView()).findViewById(R.id.profile_wrong_pass_window);
                            current_window.setVisibility(View.GONE);
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



