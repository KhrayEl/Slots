package com.khrayel.main_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.khrayel.slots.R;
import com.khrayel.slots.SlotsRollsValues;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestsFragment extends Fragment
    {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
private Random random;
        public TestsFragment ()
            {
                // Required empty public constructor
            }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestsFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static TestsFragment newInstance (String param1, String param2)
            {
                TestsFragment fragment = new TestsFragment();
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
                if (getArguments() != null)
                    {
                        mParam1 = getArguments().getString(ARG_PARAM1);
                        mParam2 = getArguments().getString(ARG_PARAM2);
                    }
            }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState)
            {

                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.tests_fragment, container, false);

                LinearLayout linearLayout1 = view.findViewById(R.id.tests_linear_layout_1);
                Populate(linearLayout1);

                LinearLayout linearLayout2 = view.findViewById(R.id.tests_linear_layout_2);
                Populate(linearLayout2);


                return view;
            }

        private final void ScrollToView (View view)
            {
//                View your_scrollview = view.getRootView();
//                //Button your_EditBox = your_scrollview.findViewById()
//
//                your_scrollview.post(new Runnable()
//                    {
//                        @Override
//                        public void run ()
//                            {
//                                your_scrollview.scrollTo(0, your_EditBox.getBottom());
//                            }
//                    });
            }

        private final void focusOnView (final ScrollView scroll, final View view)
            {
                new Handler().post(new Runnable()
                    {
                        @Override
                        public void run ()
                            {
                                int vTop = view.getTop();
                                int vBottom = view.getBottom();
                                int sHeight = scroll.getBottom();
                                scroll.smoothScrollTo(0, ((vTop + vBottom - sHeight) / 2));
                            }
                    });
            }

        public void onClick (View v)
            {
                ViewGroup parent = (ViewGroup) v.getParent().getParent();
                switch (parent.getId())
                    {
                        case R.id.tests_scroll_1:
                        {

                            ScrollView scroll = parent.findViewById(R.id.tests_scroll_1);
                            View view = parent.findViewById(Integer.valueOf(50));
                            focusOnView((ScrollView) parent, view);
                            break;
                        }
                        case R.id.tests_scroll_2:
                        {
                            int child_count = parent.getChildCount();
                            ViewGroup linearLayout=(ViewGroup) parent.getChildAt(0);
                            Button button = new Button(this.getContext());

                            button.setText(SlotsRollsValues.getRandomRoll().string_as_html_entity);
                            button.setTextSize(R.dimen.main_text_size);
                            button.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                            button.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            button.setTextColor(getResources().getColor(R.color.black));
//                        button.setBackgroundColor(getResources().getColor(R.color.teal_200));
                            button.setTextSize(25);
                            button.setOnClickListener(this::onClick);

                            linearLayout.removeViewAt(0);
                            linearLayout.addView(button);
                            break;
                        }
                    }
            }

        void Populate (ViewGroup viewGroup)
            {
                for (int i = 1; i <= 100; i++)
                    {
                        Button button = new Button(this.getContext());
                        button.setText(Integer.toString(i));
//                        button.setText("TEST");
                        button.setId(i);
                        button.setTextSize(R.dimen.main_text_size);
                        button.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        button.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        button.setTextColor(getResources().getColor(R.color.black));
//                        button.setBackgroundColor(getResources().getColor(R.color.teal_200));
                        button.setTextSize(25);
                        button.setOnClickListener(this::onClick);
                        viewGroup.addView(button);
                    }
            }
    }