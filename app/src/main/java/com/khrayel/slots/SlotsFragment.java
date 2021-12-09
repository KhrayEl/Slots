package com.khrayel.slots;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.khrayel.slots.databinding.SlotsFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlotsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlotsFragment extends Fragment implements DataOperations, View.OnClickListener
    {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        private SlotsViewModel slotsviewmodel;
        private final Handler delay_handler = new Handler();

        private int reel_anim_delay=100;

        public SlotsFragment ()
            {
                super(R.layout.slots_fragment);

                // Required empty public constructor
            }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SlotsFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static SlotsFragment newInstance (String param1, String param2)
            {
                SlotsFragment fragment = new SlotsFragment();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, param1);
                args.putString(ARG_PARAM2, param2);
                fragment.setArguments(args);
                return fragment;
            }

        @Override
        public void onCreate (Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
//                if (getArguments() != null)
//                    {
//                        mParam1 = getArguments().getString(ARG_PARAM1);
//                        mParam2 = getArguments().getString(ARG_PARAM2);
//                    }
//                setContentView(R.layout.fragment_slots);

            }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState)
            {
                // Inflate the layout for this fragment
//                return inflater.inflate(R.layout.fragment_slots, container, false);

                slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);

                SlotsFragmentBinding binding = DataBindingUtil.inflate(
                        inflater, R.layout.slots_fragment, container, false);
                View view = binding.getRoot();
                //here data must be an instance of the class MarsDataProvider
                binding.setSlotsviewmodel(slotsviewmodel);


                slotsviewmodel.setFieldsFromString(readStringFromFile(slotsviewmodel.getSlots_data_filename(), this.getContext()));

                SetOnClickListeners(view);


                return view;


//                SlotsViewModel slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);
//
//                ActivityMainBinding binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_slots);
//                binding.setLifecycleOwner(this);
//                binding.setSlotsviewmodel(slotsviewmodel);
//                binding.setLifecycleOwner(this);
            }

        @Override
        public void onDestroy ()
            {
                super.onDestroy();

                // write data to file
                //writeStringToFile(slotsviewmodel.WriteFieldsToJsonString(), slotsviewmodel.getSlots_data_filename(), this.getContext());
            }

        @Override
        public void onStop ()
            {
                super.onStop();

                // write data to file
                writeStringToFile(slotsviewmodel.WriteFieldsToJsonString(), slotsviewmodel.getSlots_data_filename(), this.getContext());

            }


        void SetOnClickListeners (View view)
            {
                Button slots_button_bet_plus = (Button) view.findViewById(R.id.slots_button_bet_plus);
                slots_button_bet_plus.setOnClickListener(this);

                Button slots_button_bet_minus = (Button) view.findViewById(R.id.slots_button_bet_minus);
                slots_button_bet_minus.setOnClickListener(this);

                Button slots_button_spin = (Button) view.findViewById(R.id.slots_button_spin);
                slots_button_spin.setOnClickListener(this);

                Button slots_button_restart = (Button) view.findViewById(R.id.slots_button_restart);
                slots_button_restart.setOnClickListener(this);
            }

        @Override
        public void onClick (View v)
            {
                switch (v.getId())
                    {
                        case R.id.slots_button_bet_plus:
                        {
                            slotsviewmodel.BetIncrease();
                            break;
                        }
                        case R.id.slots_button_bet_minus:
                        {
                            slotsviewmodel.BetDecrease();
                            break;
                        }
                        case R.id.slots_button_spin:
                        {
                            slotsviewmodel.getNewRolls();

                            int delay = reel_anim_delay;

                            View parent = (View) v.getParent().getParent().getParent();
                            TextView tv1 = parent.findViewById(R.id.slots_text_reel1);
                            TextView tv2 = parent.findViewById(R.id.slots_text_reel2);
                            TextView tv3 = parent.findViewById(R.id.slots_text_reel3);


                            tv1.setTextColor(getResources().getColor(R.color.transparent));
                            tv2.setTextColor(getResources().getColor(R.color.transparent));
                            tv3.setTextColor(getResources().getColor(R.color.transparent));

                            Button btn_spin = parent.findViewById(R.id.slots_button_spin);
                            Button btn_plus = parent.findViewById(R.id.slots_button_bet_plus);
                            Button btn_minus = parent.findViewById(R.id.slots_button_bet_minus);

                            btn_spin.setClickable(false);
                            btn_plus.setClickable(false);
                            btn_minus.setClickable(false);

                            View bet_layout = parent.findViewById(R.id.slots_layout_bets);
                            View restart_layout = parent.findViewById(R.id.slots_layout_restart);


                            delay_handler.postDelayed(new Runnable()
                                {

                                    public void run ()
                                        {
                                            tv1.setTextColor(getResources().getColor(R.color.black));

                                        }
                                }, delay * 1L);


                            delay_handler.postDelayed(new Runnable()
                                {

                                    public void run ()
                                        {
                                            tv2.setTextColor(getResources().getColor(R.color.black));

                                        }
                                }, delay * 2L);

                            delay_handler.postDelayed(new Runnable()
                                {

                                    public void run ()
                                        {
                                            tv3.setTextColor(getResources().getColor(R.color.black));

                                        }
                                }, delay * 3L);


                            delay_handler.postDelayed(new Runnable()
                                {
                                    public void run ()
                                        {
                                            btn_spin.setClickable(true);
                                            btn_plus.setClickable(true);
                                            btn_minus.setClickable(true);

                                            if (slotsviewmodel.getGameOver())
                                                {
                                                    bet_layout.setVisibility(View.GONE);
                                                    restart_layout.setVisibility(View.VISIBLE);
                                                }
                                        }
                                }, delay * 4L);


                            break;
                        }
                        case R.id.slots_button_restart:
                        {
                            View parent = (View) v.getParent().getParent().getParent();
                            View bet_layout = parent.findViewById(R.id.slots_layout_bets);
                            View restart_layout = parent.findViewById(R.id.slots_layout_restart);

                            bet_layout.setVisibility(View.VISIBLE);
                            restart_layout.setVisibility(View.GONE);

                            slotsviewmodel.Restart();
                            break;
                        }

                    }
            }

    }