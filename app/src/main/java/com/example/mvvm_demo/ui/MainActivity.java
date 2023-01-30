package com.example.mvvm_demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.OrdersWithUsersEntity;
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
    private TextView resulText;

    private Button dbButton, responseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider( this).get(MainViewModel.class);

        dbButton = findViewById(R.id.button);
        responseButton = findViewById(R.id.response_button);
        resulText = findViewById(R.id.result_text);

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
                mainViewModel.getAllOrdersWithUsers().observe(MainActivity.this, new Observer<List<OrdersWithUsersEntity>>() {
                    @Override
                    public void onChanged(List<OrdersWithUsersEntity> list) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (OrdersWithUsersEntity orders: list) {
                            Log.d(TAG, orders.usersEntity.name + " " + orders.ordersEntity.orderInfo + " " + orders.ordersEntity.userId + " " + orders.ordersEntity.date);
                            stringBuilder
                                    .append(orders.usersEntity.name + ": " + orders.ordersEntity.orderInfo + ", " + orders.ordersEntity.userId + ", " + orders.ordersEntity.date)
                                    .append("\n\n");
                        }
                        resulText.setText(stringBuilder);
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
                        StringBuilder stringBuilder = new StringBuilder();
                        for (RandomPoem randomPoem : list) {
                            stringBuilder
                                    .append("автор:").append("\n\n")
                                    .append(randomPoem.author)
                                    .append("\n\n")
                                    .append("название:").append("\n\n")
                                    .append(randomPoem.title)
                                    .append("\n\n")
                                    .append("количество строк: ")
                                    .append(randomPoem.linecount)
                                    .append("\n\n")
                                    .append("поэма:").append("\n\n")
                                    .append(randomPoem.getPoemText());
                            Log.d(TAG,randomPoem.author + " " +  randomPoem.title + " " + randomPoem.getPoemText());
                            resulText.setText(stringBuilder.toString());
                        }
                    }
                });
            }
        });





    }
}