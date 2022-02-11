package com.example.mangmacs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mangmacs.R;
import com.example.mangmacs.adapter.RiceMealAdapter;
import com.example.mangmacs.api.ApiInterface;
import com.example.mangmacs.api.RetrofitInstance;
import com.example.mangmacs.model.RiceListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiceMealActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RiceListModel> riceMealList;
    private ApiInterface apiInterface;
    private RiceMealAdapter riceMealAdapter;
    private TextView btnArrowBack;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_meal);
        btnArrowBack = findViewById(R.id.arrow_back);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        ShowRiceMealLists();
        Back();
    }

    private void Back() {
        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RiceMealActivity.this,home_activity.class));
            }
        });
    }

    private void ShowRiceMealLists() {
        Call<List<RiceListModel>> call= apiInterface.getRiceMeal();
        call.enqueue(new Callback<List<RiceListModel>>() {
            @Override
            public void onResponse(Call<List<RiceListModel>> call, Response<List<RiceListModel>> response) {
                riceMealList = response.body();
                riceMealAdapter = new RiceMealAdapter(RiceMealActivity.this,riceMealList);
                recyclerView.setAdapter(riceMealAdapter);
                refresh();
            }

            @Override
            public void onFailure(Call<List<RiceListModel>> call, Throwable t) {

            }
        });
    }

    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<RiceListModel>> call= apiInterface.getRiceMeal();
                call.enqueue(new Callback<List<RiceListModel>>() {
                    @Override
                    public void onResponse(Call<List<RiceListModel>> call, Response<List<RiceListModel>> response) {
                        riceMealList = response.body();
                        riceMealAdapter = new RiceMealAdapter(RiceMealActivity.this,riceMealList);
                        recyclerView.setAdapter(riceMealAdapter);
                        refresh();
                    }

                    @Override
                    public void onFailure(Call<List<RiceListModel>> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}