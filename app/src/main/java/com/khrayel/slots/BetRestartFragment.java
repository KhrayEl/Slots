package com.khrayel.slots;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BetRestartFragment extends Fragment {

    private BetRestartViewModel mViewModel;

    public static BetRestartFragment newInstance() {
        return new BetRestartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bet_restart_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BetRestartViewModel.class);
        // TODO: Use the ViewModel
    }
     void GameOver()
    {
        findViewById(R.id.layout_bet).setVisibility(View.GONE);
        findViewById(R.id.layout_restart).setVisibility(View.VISIBLE);
    }

}