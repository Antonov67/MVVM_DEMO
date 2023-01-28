package com.example.mvvm_demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.UserEntity;
import com.example.mvvm_demo.response.PoetryDBService;
import com.example.mvvm_demo.response.RandomPoem;
import com.example.mvvm_demo.response.RetrofitConnection;
import com.example.mvvm_demo.viewModel.MainViewModel;
import com.example.mvvm_demo.R;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mvvm";

    private MainViewModel mainViewModel;

    private Button dbButton, responseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider( this).get(MainViewModel.class);

        dbButton = findViewById(R.id.button);
        responseButton = findViewById(R.id.response_button);

        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "users");
                mainViewModel.getAllUsers().observe(MainActivity.this, new Observer<List<UserEntity>>() {
                    @Override
                    public void onChanged(List<UserEntity> list) {
                        for (UserEntity user: list) {
                            Log.d(TAG, user.getId() + user.name + user.pswrd);
                        }
                    }
                });

                Log.d(TAG, "orders");
                mainViewModel.getAllOrders().observe(MainActivity.this, new Observer<List<OrdersEntity>>() {
                    @Override
                    public void onChanged(List<OrdersEntity> list) {
                        for (OrdersEntity orders: list) {
                            Log.d(TAG, orders.orderInfo + " " + orders.userId + " " + orders.date);
                        }
                    }
                });
            }
        });

        responseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getPoems().observe(MainActivity.this, new Observer<List<RandomPoem>>() {
                    @Override
                    public void onChanged(List<RandomPoem> list) {
                        for (RandomPoem randomPoem : list) {
                            Log.d(TAG,randomPoem.author + " " +  randomPoem.title + " " + randomPoem.getPoemText());
                        }
                    }
                });
            }
        });





    }
}