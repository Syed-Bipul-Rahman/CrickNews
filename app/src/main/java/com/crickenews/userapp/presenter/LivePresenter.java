package com.crickenews.userapp.presenter;

public interface LivePresenter {

    void fetchLiveUrl();
    void fetchCricketMatchData(String liveMatchURL);
}
