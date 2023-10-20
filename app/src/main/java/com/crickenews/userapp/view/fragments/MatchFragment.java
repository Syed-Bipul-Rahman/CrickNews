package com.crickenews.userapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.crickenews.userapp.R;
import com.crickenews.userapp.adapter.RecyclerAdapter;
import com.crickenews.userapp.model.Matches;
import com.crickenews.userapp.network.ApiClient;
import com.crickenews.userapp.network.ApiService;
import com.crickenews.userapp.presenter.MatchPresenter;
import com.crickenews.userapp.presenter.MatchPresenterImpl;
import com.crickenews.userapp.view.MatchView;

import java.util.ArrayList;
import java.util.List;


public class MatchFragment extends Fragment  implements MatchView {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<Matches> matchesList;
    private ApiService apiService;
    private MatchPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        matchesList = new ArrayList<>();
        adapter = new RecyclerAdapter(getContext(), (ArrayList<Matches>) matchesList);
        recyclerView.setAdapter(adapter);

        apiService = ApiClient.getClient().create(ApiService.class);

        // Initialize the presenter
        presenter = new MatchPresenterImpl(this, apiService);
        presenter.fetchMatches();

        return view;
    }

    @Override
    public void showMatches(List<Matches> matches) {
        matchesList.clear();
        matchesList.addAll(matches);
        adapter.notifyDataSetChanged();
    }
}