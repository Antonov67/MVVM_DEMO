package com.example.mvvm_demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.UserEntity;
import com.example.mvvm_demo.viewModel.MainViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;


public class AddDbRecord extends AppCompatActivity {

    private static final String TAG = "mvvm";

    private EditText userName, pswrd, orderInfo, date;
    private Button addButton;

    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_db_record);

        userName = findViewById(R.id.user_name_field);
        pswrd = findViewById(R.id.user_pswrd_field);
        orderInfo = findViewById(R.id.order_info);
        date = findViewById(R.id.date);

        addButton = findViewById(R.id.add_button);

        mainViewModel = new ViewModelProvider( this).get(MainViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //новый поток для работы с БД
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = new UserEntity();
                        userEntity.name = userName.getText().toString();
                        userEntity.pswrd = pswrd.getText().toString();
                        //вставим юзера и поучим его id
                        long userId = mainViewModel.insertOneUser(userEntity);
                        Log.d(TAG,"id user: " + userId);

                        //вставим заказ для этого юзера, зная его id
                        OrdersEntity ordersEntity = new OrdersEntity();
                        ordersEntity.userId = (int) userId;
                        ordersEntity.orderInfo = orderInfo.getText().toString();
                        ordersEntity.date = dateFromString(date.getText().toString());

                        List<OrdersEntity> ordersEntityList = new ArrayList<>();
                        ordersEntityList.add(ordersEntity);
                        mainViewModel.insertOrder(ordersEntityList);

                    }
                });

            }
        });
    }

    private Date dateFromString(String val){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return simpleDateFormat.parse(val);
        }catch (ParseException e){

        }
        return null;
    }
}