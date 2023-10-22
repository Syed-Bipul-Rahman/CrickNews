package com.crickenews.userapp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.crickenews.userapp.model.CricketMatch;
import com.crickenews.userapp.model.LiveUrl;
import com.crickenews.userapp.network.ApiClient2;
import com.crickenews.userapp.network.ApiService;
import com.crickenews.userapp.view.LiveView;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivePresenterImpl implements LivePresenter {

    private LiveView view;
    private ApiService apiService;
    private static final long REFRESH_INTERVAL = 10000;
    private Handler handler = new Handler();
    private Runnable refreshRunnable;
    private String liveUrl;
    private Context context;

    public LivePresenterImpl(LiveView view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = ApiClient2.getClient().create(ApiService.class);
    }


    @Override
    public void fetchLiveUrl() {
        Call<LiveUrl> cal1 = apiService.getLiveUrl();
        cal1.enqueue(new Callback<LiveUrl>() {
            @Override
            public void onResponse(Call<LiveUrl> call, Response<LiveUrl> response) {
                liveUrl = response.body().getLink();
                refreshRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // Refresh data
                        fetchCricketMatchData("cri.php?url=https://www.cricbuzz.com/" + liveUrl);
                        // Schedule the next refresh
                        handler.postDelayed(this, REFRESH_INTERVAL);
                    }
                };

                // Start periodic data refresh
                handler.post(refreshRunnable);
            }

            @Override
            public void onFailure(Call<LiveUrl> call, Throwable t) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void fetchCricketMatchData(String liveMatchURL) {
        Call<CricketMatch> call = apiService.getCricketMatch(liveMatchURL);

        call.enqueue(new Callback<CricketMatch>() {
            @Override
            public void onResponse(Call<CricketMatch> call, Response<CricketMatch> response) {
                if (response.isSuccessful()) {
                    CricketMatch cricketMatch = response.body();
                    view.updateUpdateMessage(cricketMatch.getLiveScore().getUpdate());

                    String matchTitleget = cricketMatch.getLiveScore().getTitle();
                    String[] split = matchTitleget.split(",");

                    if (split.length >= 2) {
                        String firstWord = split[0];
                        view.updateMatchTitle(firstWord);
                    } else {
                        view.updateMatchTitle(matchTitleget);
                    }

                    if (response.body().getLiveScore().getCurrent().equals("Data Not Found") && response.body().getLiveScore().getRecentballs().equals("Data Not Found")) {
                        view.showHideState(false);
                    } else {
                        view.updateRecentBalls(response.body().getLiveScore().getRecentballs());
                        view.updateCurrentPosition(response.body().getLiveScore().getCurrent());
                    }
                } else {
                    showAlertDialog("Error", "Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<CricketMatch> call, Throwable t) {
                showAlertDialog("Error", "Failed to Fetch data from Server\n Please check your Internet connection.");
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
}
