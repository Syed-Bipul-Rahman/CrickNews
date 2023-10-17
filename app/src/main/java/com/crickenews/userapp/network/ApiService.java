package com.crickenews.userapp.network;


import com.crickenews.userapp.model.CricketMatch;
import com.crickenews.userapp.model.LiveUrl;
import com.crickenews.userapp.model.Matches;
import com.crickenews.userapp.model.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET("matches.php")
    Call<List<Matches>> getAllMatches();

    @GET("researchBySyedBipul/json.php")
    Call<LiveUrl> getLiveUrl();

    @GET("stats.php")
    Call<List<Table>> getAllStats();

    @GET()
    Call<CricketMatch> getCricketMatch(@Url String url);


}
