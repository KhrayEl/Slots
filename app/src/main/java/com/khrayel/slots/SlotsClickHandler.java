package com.khrayel.slots;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;


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
                            View parent = v.getRootView();
                            TextView tv_bet_number = parent.findViewById(R.id.slots_text_bet_number);
                            tv_bet_number.setText(String.format(Long.toString(slotsViewModel.getBet())));
                            break;
                        }
                        case R.id.slots_button_bet_minus:
                        {
                            slotsViewModel.BetDecrease();
                            View parent = v.getRootView();
                            TextView tv_bet_number = parent.findViewById(R.id.slots_text_bet_number);
                            tv_bet_number.setText(String.format(Long.toString(slotsViewModel.getBet())));
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

                ScrollView scroll_1 = parent.findViewById(R.id.slots_layout_scroll_1);
                ScrollView scroll_2 = parent.findViewById(R.id.slots_layout_scroll_2);
                ScrollView scroll_3 = parent.findViewById(R.id.slots_layout_scroll_3);

                scroll_1.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_grey));
                scroll_2.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_grey));
                scroll_3.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_grey));


                TextView tv_score_change = parent.findViewById(R.id.slots_textWinLoss);
                tv_score_change.setVisibility(View.INVISIBLE);
                TextView tv_score = parent.findViewById(R.id.slots_text_Score);
                TextView tv_record = parent.findViewById(R.id.slots_text_record);

                btn_spin.setClickable(false);
                btn_plus.setClickable(false);
                btn_minus.setClickable(false);

                View bet_layout = parent.findViewById(R.id.slots_layout_bets);
                View restart_layout = parent.findViewById(R.id.slots_layout_restart);

                TextView tv_bet_number = parent.findViewById(R.id.slots_text_bet_number);


                LinearLayout linearLayout_reel = parent.findViewById(R.id.slots_layout_reel_1);
//                ScrollView scroll = parent.findViewById(R.id.slots_layout_scroll_1);
//                        (ScrollView) linearLayout_reel.getParent();
                //View view = parent.findViewById(R.id.slots_text_reel1);


                StartReel(parent.findViewById(R.id.slots_layout_scroll_1), parent.findViewById(R.id.slots_layout_reel_1), slotsViewModel.getRoll1(), handler, 1, slotsViewModel.selected_drawable_type);
                StartReel(parent.findViewById(R.id.slots_layout_scroll_2), parent.findViewById(R.id.slots_layout_reel_2), slotsViewModel.getRoll2(), handler, 2, slotsViewModel.selected_drawable_type);
                StartReel(parent.findViewById(R.id.slots_layout_scroll_3), parent.findViewById(R.id.slots_layout_reel_3), slotsViewModel.getRoll3(), handler, 3, slotsViewModel.selected_drawable_type);


                //  focusOnView((ScrollView) scroll, linearLayout_reel.getChildAt(2), handler, 0);


                handler.postDelayed(new Runnable()
                    {
                        public void run ()
                            {
                                btn_spin.setClickable(true);
                                btn_plus.setClickable(true);
                                btn_minus.setClickable(true);
                                tv_score_change.setVisibility(View.VISIBLE);
                                if (slotsViewModel.getChangeInScore() > 0)
                                    {
                                        scroll_1.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_green));
                                        scroll_2.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_green));
                                        scroll_3.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_green));
                                    } else
                                    {
                                        scroll_1.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_red));
                                        scroll_2.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_red));
                                        scroll_3.setBackground(AppCompatResources.getDrawable(parent.getContext(), R.drawable.reel_background_red));
                                    }
                                if (slotsViewModel.getGameOver())
                                    {
                                        bet_layout.setVisibility(View.GONE);
                                        restart_layout.setVisibility(View.VISIBLE);
                                    }
                                tv_score.setText(String.format(Long.toString(slotsViewModel.getScore())));
                                tv_record.setText(String.format(Long.toString(slotsViewModel.getRecord())));


                                tv_bet_number.setText(String.format(Long.toString(slotsViewModel.getBet())));
                            }
                    }, 1700);

            }

        public static void AddChildrenToReel (ViewGroup parent, int number_of_children_to_add, int rolled_value_drawable, SlotsRollsValues.DrawableType drawableType)
            {
                for (int i = 0; i < number_of_children_to_add; i++)
                    {
                        ImageView imageView = new ImageView(parent.getContext());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                //ViewGroup.LayoutParams.WRAP_CONTENT
//                                (int)  ( parent.getContext().getResources().getDimension(R.dimen.slots_roll_size)/*/parent.getContext().getResources().getDisplayMetrics().density*/)
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (parent.getContext().getResources().getDimension(R.dimen.slots_roll_size)/*/parent.getContext().getResources().getDisplayMetrics().density*/)


                                //50, 50 // - this works

                        );
                        params.setMargins(0, 15, 0, 15);
                        params.gravity = Gravity.CENTER;
                        imageView.setLayoutParams(params);

//                        tv.setTextColor(parent.getResources().getColor(R.color.black));
//                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, parent.getResources().getDimension(R.dimen.slots_roll_size));
//                        tv.setGravity(Gravity.CENTER);
                        //tv.setBackground(parent.getResources().getDrawable(R.drawable._1f60d));

                        if (i != number_of_children_to_add - 3)
                            {
                                //  tv.setText(SlotsRollsValues.getRandomRoll().string_as_html_entity);
//                                tv.setBackground(AppCompatResources.getDrawable(parent.getContext(),SlotsRollsValues.getRandomRollDrawable(drawableType)));
                                imageView.setImageDrawable(AppCompatResources.getDrawable(parent.getContext(), SlotsRollsValues.getRandomRollDrawable(drawableType)));
//                                imageView.setBackgroundColor(parent.getResources().getColor(R.color.red));
                            } else
                            {

                                //tv.setText(rolled_value);
//                                tv.setBackground(AppCompatResources.getDrawable(parent.getContext(),rolled_value_drawable));
                                imageView.setImageDrawable(AppCompatResources.getDrawable(parent.getContext(), rolled_value_drawable));

//                                imageView.setBackgroundColor(parent.getResources().getColor(R.color.green));

                            }

                        parent.addView(imageView);
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
                TextView tv_score = parent.findViewById(R.id.slots_text_Score);
                tv_score.setText(String.format(Long.toString(slotsViewModel.getScore())));

                TextView tv_score_change = parent.findViewById(R.id.slots_textWinLoss);
                tv_score_change.setVisibility(View.INVISIBLE);

                TextView tv_bet_number = parent.findViewById(R.id.slots_text_bet_number);
                tv_bet_number.setText(String.format(Long.toString(slotsViewModel.getBet())));
            }

        private static void StartReel (ScrollView scrollView, LinearLayout linearLayout_reel, int rolled_value_drawable, Handler handler, int reel_multiplier, SlotsRollsValues.DrawableType drawableType)
            {
                //LinearLayout linearLayout_reel = (LinearLayout) scrollView.getChildAt(0);
                int childs_to_add = 100;

                int reel_anim_delay = 50;
                scrollView.setBackground(AppCompatResources.getDrawable(scrollView.getContext(), R.drawable.reel_background_grey));
                int children_in_reel = linearLayout_reel.getChildCount();
                for (int i = childs_to_add * reel_multiplier; i > 0 /*linearLayout_reel.getChildCount() - 5*/
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
                AddChildrenToReel(linearLayout_reel, childs_to_add * reel_multiplier, rolled_value_drawable, drawableType);

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