package com.khrayel.slots;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import android.view.ViewGroup;
import android.widget.LinearLayout;


public class SlotsClickHandler
    {


        public static void HandleOnClick (@NonNull View v, SlotsViewModel slotsViewModel)
            {

                switch (v.getId())
                    {
                        case R.id.slots_button_bet_plus:
                        {
                            slotsViewModel.BetIncrease();
                            break;
                        }
                        case R.id.slots_button_bet_minus:
                        {
                            slotsViewModel.BetDecrease();
                            break;
                        }
                        case R.id.slots_button_spin:
                        {
                            HandleSpin(v, slotsViewModel);
                            break;
                        }
                        case R.id.slots_button_restart:
                        {
                            HandleRestart(v, slotsViewModel);
                            break;
                        }
                    }
            }

        private static void HandleSpin (@NonNull View v, @NonNull SlotsViewModel slotsViewModel)
            {
                final Handler handler = new Handler();


                slotsViewModel.getNewRolls();

                ViewGroup parent = (ViewGroup) v.getParent().getParent().getParent();

//                TextView tv1 = parent.findViewById(R.id.slots_text_reel1);
                //TextView tv2 = parent.findViewById(R.id.slots_text_reel2);
                //TextView tv3 = parent.findViewById(R.id.slots_text_reel3);

//                tv1.setTextColor(v.getResources().getColor(R.color.transparent));
//                tv2.setTextColor(v.getResources().getColor(R.color.transparent));
//                tv3.setTextColor(v.getResources().getColor(R.color.transparent));

                //                handler.postDelayed(new Runnable()
//                    {
//                        public void run ()
//                            {
//                                for (int i = 0; i < linearLayout_reel.getChildCount()  -children_in_reel; i++)
//                                    {
//                                        linearLayout_reel.removeViewAt(i);
//                                    }
//                            }
//            },reel_anim_delay*20);


                Button btn_spin = parent.findViewById(R.id.slots_button_spin);
                Button btn_plus = parent.findViewById(R.id.slots_button_bet_plus);
                Button btn_minus = parent.findViewById(R.id.slots_button_bet_minus);
                TextView tv_score_change = parent.findViewById(R.id.slots_textWinLoss);
                tv_score_change.setVisibility(View.INVISIBLE);


                btn_spin.setClickable(false);
                btn_plus.setClickable(false);
                btn_minus.setClickable(false);

                View bet_layout = parent.findViewById(R.id.slots_layout_bets);
                View restart_layout = parent.findViewById(R.id.slots_layout_restart);


                LinearLayout linearLayout_reel = parent.findViewById(R.id.slots_layout_reel_1);
                ScrollView scroll = parent.findViewById(R.id.slots_layout_scroll_1);
//                        (ScrollView) linearLayout_reel.getParent();
                //View view = parent.findViewById(R.id.slots_text_reel1);


                StartReel(parent.findViewById(R.id.slots_layout_scroll_1), parent.findViewById(R.id.slots_layout_reel_1), slotsViewModel.getRoll1(), handler, 1);
                StartReel(parent.findViewById(R.id.slots_layout_scroll_2), parent.findViewById(R.id.slots_layout_reel_2),slotsViewModel.getRoll2(), handler, 2);
                StartReel(parent.findViewById(R.id.slots_layout_scroll_3), parent.findViewById(R.id.slots_layout_reel_3),slotsViewModel.getRoll3(), handler, 3);


                //  focusOnView((ScrollView) scroll, linearLayout_reel.getChildAt(2), handler, 0);


                handler.postDelayed(new Runnable()
                    {
                        public void run ()
                            {
                                btn_spin.setClickable(true);
                                btn_plus.setClickable(true);
                                btn_minus.setClickable(true);
                                tv_score_change.setVisibility(View.VISIBLE);
                                if (slotsViewModel.getGameOver())
                                    {
                                        bet_layout.setVisibility(View.GONE);
                                        restart_layout.setVisibility(View.VISIBLE);
                                    }

                            }
                    }, 1700);

            }

        private static void AddChildrenToReel (ViewGroup parent, int number_of_children_to_add, String rolled_value, Handler handler)
            {


                for (int i = 0; i < number_of_children_to_add; i++)
                    {
                        TextView tv = new TextView(parent.getContext());
                        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        tv.setTextColor(parent.getResources().getColor(R.color.black));
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, parent.getResources().getDimension(R.dimen.slots_roll_size));
                        tv.setGravity(Gravity.CENTER);


                        if (i != number_of_children_to_add - 3)
                            {
                                tv.setText(SlotsRollsValues.getRandomRoll().string_as_html_entity);
                            } else
                            {

                                tv.setText(rolled_value);
                                tv.setBackgroundColor(parent.getResources().getColor(R.color.green));
                            }

                        parent.addView(tv);
                    }
            }

        private static void focusOnView (final ScrollView scroll, final View view, Handler handler, int scrolling_duration)
            {
                // Get duration scale from the global settings.
                float durationScale = Settings.Global.getFloat(scroll.getContext().getContentResolver(),
                        Settings.Global.ANIMATOR_DURATION_SCALE, 0);

// If global duration scale is not 1 (default), try to override it
// for the current application.
                if (durationScale != 1)
                    {
                        try
                            {
                                ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 1f);
                                durationScale = 1f;
                            } catch (Throwable t)
                            {
                                // It means something bad happened, and animations are still
                                // altered by the global settings. You should warn the user and
                                // exit application.
                            }
                    }

                handler.post(new Runnable()
                    {
                        @Override
                        public void run ()
                            {
                                int vTop = view.getTop();
                                int vBottom = view.getBottom();
                                int sHeight = scroll.getBottom();
//                                scroll.smoothScrollBy(0, ((vTop + vBottom - sHeight) / 2));
                                ObjectAnimator.ofInt(scroll, "scrollY", (vTop + vBottom - sHeight) / 2)
                                        .setDuration(scrolling_duration)
                                        .start();

                            }
                    });
            }

        private static void HandleRestart (View v, SlotsViewModel slotsViewModel)
            {
                View parent = (View) v.getParent().getParent().getParent();
                View bet_layout = parent.findViewById(R.id.slots_layout_bets);
                View restart_layout = parent.findViewById(R.id.slots_layout_restart);

                bet_layout.setVisibility(View.VISIBLE);
                restart_layout.setVisibility(View.GONE);

                slotsViewModel.Restart();
            }

        private static void StartReel (ScrollView scrollView,LinearLayout linearLayout_reel , String rolled_value, Handler handler, int reel_multiplier)
            {
                //LinearLayout linearLayout_reel = (LinearLayout) scrollView.getChildAt(0);
                int childs_to_add = 100;

                int reel_anim_delay = 50;

                int children_in_reel = linearLayout_reel.getChildCount();
                for (int i = childs_to_add * reel_multiplier; i >0 /*linearLayout_reel.getChildCount() - 5*/
                         && children_in_reel > 5; i--)
                    {
                        try
                            {
                                linearLayout_reel.removeViewAt(i);
                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                    }
                children_in_reel = linearLayout_reel.getChildCount();
                focusOnView((ScrollView) scrollView, linearLayout_reel.getChildAt(children_in_reel - 3), handler, 0);
//                scroll.scrollTo(0, (
//                        (linearLayout_reel.getChildAt(index_of_child_to_focus).getTop() +
//                                linearLayout_reel.getChildAt(index_of_child_to_focus).getBottom() -
//                                scroll.getBottom())
//                 / 2));
                AddChildrenToReel(linearLayout_reel, childs_to_add * reel_multiplier, rolled_value, handler);

                int index_of_child_to_focus = linearLayout_reel.getChildCount() - 3;

//
                focusOnView((ScrollView) scrollView, linearLayout_reel.getChildAt(index_of_child_to_focus), handler, reel_anim_delay * 10 * reel_multiplier);
                ;
//                handler.postDelayed(new Runnable()
//                    {
//                        public void run ()
//                            {

//                            }
//                    },0);
            }
    }