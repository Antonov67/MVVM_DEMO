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
import com.example.mvvm_demo.viewModel.MainViewModel;
import com.example.mvvm_demo.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mvvm";

    private MainViewModel mainViewModel;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider( this).get(MainViewModel.class);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click");
                mainViewModel.getAllUsers().observe(MainActivity.this, new Observer<List<UserEntity>>() {
                    @Override
                    public void onChanged(List<UserEntity> list) {
                        for (UserEntity user: list) {
                            Log.d(TAG, user.getId() + user.name + user.pswrd);
                        }
                    }
                });
            }
        });





    }
}