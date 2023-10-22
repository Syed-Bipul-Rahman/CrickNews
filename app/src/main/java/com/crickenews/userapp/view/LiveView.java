package com.crickenews.userapp.view;

public interface LiveView {

    void updateMatchTitle(String title);

    void updateUpdateMessage(String message);

    void updateCurrentPosition(String currentPosition);

    void updateRecentBalls(String recentBalls);

    void showHideState(boolean visible);
}
