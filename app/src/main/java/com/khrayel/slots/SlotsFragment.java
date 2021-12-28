package com.khrayel.slots;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.khrayel.slots.databinding.SlotsFragmentBinding;

import java.util.Objects;
import java.util.Random;

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
        public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState)
            {

                slotsviewmodel = new ViewModelProvider(this).get(SlotsViewModel.class);


                if (slotsviewmodel.getSoundEnabled())
                    {
                        slotsviewmodel.CreateBackgroundMusicPlayer(this.getContext());
                    }

                SlotsFragmentBinding binding = DataBindingUtil.inflate(
                        inflater, R.layout.slots_fragment, container, false);
                View view = binding.getRoot();

                binding.setSlotsviewmodel(slotsviewmodel);


                slotsviewmodel.setFieldsFromString(readStringFromFile(slotsviewmodel.getSlots_data_filename(), Objects.requireNonNull(this.getContext())));
                slotsviewmodel.loadOptionsFromString(readStringFromFile(slotsviewmodel.getSlots_options_filename(), Objects.requireNonNull(this.getContext())));

                SetListeners(view);

                TextView tv_score = (TextView) view.findViewById(R.id.slots_score_value);
                tv_score.setText(String.format(Long.toString(slotsviewmodel.getScore())));
                TextView tv_record = (TextView) view.findViewById(R.id.slots_record_value);
                tv_record.setText(String.format(Long.toString(slotsviewmodel.getRecord())));

                TextView tv_bet_number = view.findViewById(R.id.slots_text_bet_number);
                tv_bet_number.setText(String.format(Long.toString(slotsviewmodel.getBet())));


                SetReelCentered(view.findViewById(R.id.slots_layout_scroll_1));
                SetReelCentered(view.findViewById(R.id.slots_layout_scroll_2));
                SetReelCentered(view.findViewById(R.id.slots_layout_scroll_3));


                switch (slotsviewmodel.getSelected_drawable_type())
                    {
                        case DRAWABLE_TYPE_EMOJI:
                        {
                            view.findViewById(R.id.slots_options_drawstyle_button).setBackground(AppCompatResources.getDrawable(view.getContext(), R.drawable.gem_26));

                            break;
                        }
                        case DRAWABLE_TYPE_GEM:
                        {
                            view.findViewById(R.id.slots_options_drawstyle_button).setBackground(AppCompatResources.getDrawable(view.getContext(), R.drawable._1f601));
                            break;
                        }
                    }


                View sound_button = (view.getRootView()).findViewById(R.id.slots_options_sound_button_toggle);


                if (slotsviewmodel.getSoundEnabled())
                    {
                        sound_button.setBackground(AppCompatResources.getDrawable(sound_button.getContext(), R.drawable._1f3b5));
                    } else
                    {
                        sound_button.setBackground(AppCompatResources.getDrawable(sound_button.getContext(), R.drawable.options_sound_disabled));
                    }


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
                slotsviewmodel.ToggleSound();
                super.onDestroy();
            }

        @Override
        public void onStop ()
            {
                super.onStop();

                // write data to file
                writeStringToFile(slotsviewmodel.WriteFieldsToJsonString(), slotsviewmodel.getSlots_data_filename(), Objects.requireNonNull(this.getContext()));
                writeStringToFile(slotsviewmodel.WriteOptionsToJsonString(), slotsviewmodel.getSlots_options_filename(), Objects.requireNonNull(this.getContext()));

            }


        private void SetListeners (View view)
            {
                Button slots_button_bet_plus = (Button) view.findViewById(R.id.slots_button_bet_plus);
                slots_button_bet_plus.setOnClickListener(this);

                Button slots_button_bet_minus = (Button) view.findViewById(R.id.slots_button_bet_minus);
                slots_button_bet_minus.setOnClickListener(this);

                Button slots_button_spin = (Button) view.findViewById(R.id.slots_button_spin);
                slots_button_spin.setOnClickListener(this);

                Button slots_button_restart = (Button) view.findViewById(R.id.slots_button_restart);
                slots_button_restart.setOnClickListener(this);

                Button drawable_style_changer_button = view.findViewById(R.id.slots_options_button);
                drawable_style_changer_button.setOnClickListener(this);

                (view.findViewById(R.id.slots_options_drawstyle_button)).setOnClickListener(this);
                (view.findViewById(R.id.slots_options_sound_button_toggle)).setOnClickListener(this);
                (view.findViewById(R.id.slots_options_overlay_button)).setOnClickListener(this);


                SeekBar sound_seekBar = (SeekBar) view.findViewById(R.id.slots_options_sound_seekbar);
                sound_seekBar.setProgress((int) (slotsviewmodel.getSoundVolume() * 100));
                sound_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                    {

                        @Override
                        public void onStopTrackingTouch (SeekBar seekBar)
                            {
                                // TODO Auto-generated method stub
                            }

                        @Override
                        public void onStartTrackingTouch (SeekBar seekBar)
                            {
                                // TODO Auto-generated method stub
                            }

                        @Override
                        public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
                            {
                                slotsviewmodel.setSoundVolume(progress / 100f);
                            }
                    });

            }

        private void SetReelCentered (ScrollView scroll)
            {
                scroll.setOnTouchListener(new View.OnTouchListener()
                    {
                        @Override
                        public boolean onTouch (View v, MotionEvent event)
                            {
                                return true;
                            }
                    });

                PopulateReelOnStart(scroll);

                new Handler().post(new Runnable()
                    {
                        @Override
                        public void run ()
                            {
                                ViewGroup viewGroup = (ViewGroup) scroll.getChildAt(0);
                                View view = viewGroup.getChildAt(2);
                                int vTop = view.getTop();
                                int vBottom = view.getBottom();
                                int sHeight = scroll.getBottom();
                                scroll.scrollTo(0, ((vTop + vBottom - sHeight) / 2));
                            }
                    });
            }

        @Override
        public void onClick (View v)
            {
                SlotsClickHandler.HandleOnClick(v, slotsviewmodel);
            }

        private void PopulateReelOnStart (ViewGroup view)
            {
                Random random = new Random();

                LinearLayout linearLayout = (LinearLayout) view.getChildAt(0);

                for (int i = 0; i < 5; i++)
                    {
                        ImageView imageView = new ImageView(view.getContext());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (view.getContext().getResources().getDimension(R.dimen.slots_roll_size)));
                        params.setMargins(0, 15, 0, 15);
                        params.gravity = Gravity.CENTER;
                        imageView.setLayoutParams(params);

//                        tv.setTextColor(view.getResources().getColor(R.color.black));
//                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, view.getResources().getDimension(R.dimen.slots_roll_size));
//                        tv.setGravity(Gravity.CENTER);

//                        tv.setBackground(AppCompatResources.getDrawable(view.getContext(),SlotsRollsValues.getRandomRollDrawable(slotsviewmodel.selected_drawable_type)));
                        int rnd = random.nextInt(SlotsRollsValues.values().length);
                        imageView.setImageDrawable(AppCompatResources.getDrawable(view.getContext(), SlotsRollsValues.getDrawable(rnd, slotsviewmodel.getSelected_drawable_type())));
                        imageView.setTag(rnd);

                        linearLayout.addView(imageView);

                    }
            }

    }