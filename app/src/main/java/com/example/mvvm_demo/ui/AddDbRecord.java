package com.example.mvvm_demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.UserEntity;
import com.example.mvvm_demo.viewModel.MainViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;


public class AddDbRecord extends AppCompatActivity {

    private static final String TAG = "mvvm";

    private EditText userName, pswrd, orderInfo;
    private Button addButton;
    private TextView dateField;

    private MainViewModel mainViewModel;

    final Calendar myCalendar= Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_db_record);

        userName = findViewById(R.id.user_name_field);
        pswrd = findViewById(R.id.user_pswrd_field);
        orderInfo = findViewById(R.id.order_info);
        dateField = findViewById(R.id.date);

        addButton = findViewById(R.id.add_button);

        mainViewModel = new ViewModelProvider( this).get(MainViewModel.class);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
                dateField.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddDbRecord.this,date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });


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
                        ordersEntity.date = dateFromString(dateField.getText().toString());

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