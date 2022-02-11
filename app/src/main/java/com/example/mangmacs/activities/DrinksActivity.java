package com.example.mangmacs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mangmacs.adapter.DrinksAdapter;
import com.example.mangmacs.model.DrinksListModel;
import com.example.mangmacs.R;
import com.example.mangmacs.api.RetrofitInstance;
import com.example.mangmacs.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinksActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DrinksListModel> drinkList;
    private ApiInterface apiInterface;
    private DrinksAdapter drinksAdapter;
    private TextView btnArrowBack;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        btnArrowBack = findViewById(R.id.arrow_back);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        ShowDrinkLists();
        Back();
    }

    private void Back() {
        btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrinksActivity.this,home_activity.class));
            }
        });
    }

    private void ShowDrinkLists() {
        Call<List<DrinksListModel>> call= apiInterface.getDrinks();
        call.enqueue(new Callback<List<DrinksListModel>>() {
            @Override
            public void onResponse(Call<List<DrinksListModel>> call, Response<List<DrinksListModel>> response) {
                drinkList = response.body();
                drinksAdapter = new DrinksAdapter(DrinksActivity.this,drinkList);
                recyclerView.setAdapter(drinksAdapter);
                refresh();
            }

            @Override
            public void onFailure(Call<List<DrinksListModel>> call, Throwable t) {

            }
        });
    }

    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<DrinksListModel>> call= apiInterface.getDrinks();
                call.enqueue(new Callback<List<DrinksListModel>>() {
                    @Override
                    public void onResponse(Call<List<DrinksListModel>> call, Response<List<DrinksListModel>> response) {
                        drinkList = response.body();
                        drinksAdapter = new DrinksAdapter(DrinksActivity.this,drinkList);
                        recyclerView.setAdapter(drinksAdapter);
                        refresh();
                    }

                    @Override
                    public void onFailure(Call<List<DrinksListModel>> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}