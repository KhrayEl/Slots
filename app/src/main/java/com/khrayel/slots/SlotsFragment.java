package com.khrayel.slots;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.khrayel.slots.databinding.FragmentSlotsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlotsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlotsFragment extends Fragment
    {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public SlotsFragment ()
            {
                super(R.layout.fragment_slots);

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

                SlotsViewModel slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);

                FragmentSlotsBinding binding = DataBindingUtil.inflate(
                        inflater, R.layout.fragment_slots, container, false);
                View view = binding.getRoot();
                //here data must be an instance of the class MarsDataProvider
                binding.setSlotsviewmodel(slotsviewmodel);
                return view;



//                SlotsViewModel slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);
//
//                ActivityMainBinding binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_slots);
//                binding.setLifecycleOwner(this);
//                binding.setSlotsviewmodel(slotsviewmodel);
//                binding.setLifecycleOwner(this);
            }
    }