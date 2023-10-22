package com.crickenews.userapp.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.crickenews.userapp.R;
import com.crickenews.userapp.model.CricketMatch;
import com.crickenews.userapp.network.ApiClient2;
import com.crickenews.userapp.network.ApiService;
import com.crickenews.userapp.presenter.LivePresenter;
import com.crickenews.userapp.presenter.LivePresenterImpl;
import com.crickenews.userapp.view.LiveView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveFragment extends Fragment implements LiveView {

    private ApiService apiService;
    TextView matchTitle, updateMesssege, currentposition, recentball;
    LinearLayout showHideState;

    private LivePresenter presenter;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_states, container, false);
//        //TODO: code;

        presenter = new LivePresenterImpl(this, getContext());
        presenter.fetchLiveUrl();
        matchTitle = view.findViewById(R.id.titlemathc);

        updateMesssege = view.findViewById(R.id.scoreUpdate);
        currentposition = view.findViewById(R.id.currentposition);
        recentball = view.findViewById(R.id.recentballs);
        showHideState = view.findViewById(R.id.linarShowState);


        // Initialize Retrofit service
        apiService = ApiClient2.getClient().create(ApiService.class);


        return view;
    }




    @Override
    public void updateMatchTitle(String title) {
        matchTitle.setText(title);
    }

    @Override
    public void updateUpdateMessage(String message) {
        updateMesssege.setText(message);
    }

    @Override
    public void updateCurrentPosition(String currentPosition) {
        currentposition.setText(currentPosition);
    }

    @Override
    public void updateRecentBalls(String recentBalls) {
        recentball.setText(recentBalls);
    }

    @Override
    public void showHideState(boolean visible) {
        if (visible) {
            showHideState.setVisibility(View.VISIBLE);
        } else {
            showHideState.setVisibility(View.GONE);
        }
    }
}