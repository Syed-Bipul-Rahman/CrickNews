package com.crickenews.userapp.presenter;

import com.crickenews.userapp.model.Matches;
import com.crickenews.userapp.network.ApiService;
import com.crickenews.userapp.view.MatchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchPresenterImpl implements MatchPresenter {
    private MatchView view;
    private ApiService apiService;

    public MatchPresenterImpl(MatchView view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void fetchMatches() {
        Call<List<Matches>> call = apiService.getAllMatches();
        call.enqueue(new Callback<List<Matches>>() {
            @Override
            public void onResponse(Call<List<Matches>> call, Response<List<Matches>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Matches> matches = response.body();
                    view.showMatches(matches);
                } else {
                    // Handle API call errors
                }
            }

            @Override
            public void onFailure(Call<List<Matches>> call, Throwable t) {
                // Handle network errors
            }
        });
    }
}