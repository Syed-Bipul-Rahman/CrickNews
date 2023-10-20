package com.crickenews.userapp.presenter;

import com.crickenews.userapp.model.Matches;
import com.crickenews.userapp.model.Table;
import com.crickenews.userapp.network.ApiService;
import com.crickenews.userapp.view.TableView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablePresenterImpl implements TablePresenter{
    TableView view;
    ApiService apiService;

    public TablePresenterImpl(TableView view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void fetchTable() {
        Call<List<Table>> call=apiService.getAllStats();

        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Table> tables = response.body();
                    view.showTables(tables);
                } else {
                    // Handle API call errors
                }
            }

            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {

            }
        });
    }
}
