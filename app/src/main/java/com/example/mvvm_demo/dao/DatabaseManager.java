package com.example.mvvm_demo.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class DatabaseManager {

    private DatabaseHelper db;
    private static DatabaseManager instance;

    private DatabaseManager(Context context) {
        db = Room.databaseBuilder(context,
                DatabaseHelper.class, DatabaseHelper.DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                initData(context);
                            }
                        });
                    }
                })
                .build();
    }

    public static DatabaseManager getInstance(Context context) {
        if (instance == null){
            instance = new DatabaseManager(context.getApplicationContext());
        }
        return instance;
    }

    public DaoInt getHseDao(){
        return db.daoInt();
    }

    private void initData(Context context){
        List<UserEntity> users = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.name = "Иван";
        users.add(user);

        user = new UserEntity();
        user.name = "Егор";
        users.add(user);

        user = new UserEntity();
        user.name = "Степан";
        users.add(user);

        DatabaseManager.getInstance(context).getHseDao().insertUser(users);

        List<OrdersEntity> orders = new ArrayList<>();

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.date = dateFromString("2023-01-28");
        ordersEntity.orderInfo = "информация о первом заказе";
        ordersEntity.userId = 1;

        ordersEntity = new OrdersEntity();
        ordersEntity.date = dateFromString("2023-01-29");
        ordersEntity.orderInfo = "информация о втором заказе";
        ordersEntity.userId = 1;

        ordersEntity = new OrdersEntity();
        ordersEntity.date = dateFromString("2023-02-20");
        ordersEntity.orderInfo = "информация о первом заказе второго юзера";
        ordersEntity.userId = 2;

        ordersEntity = new OrdersEntity();
        ordersEntity.date = dateFromString("2020-02-20");
        ordersEntity.orderInfo = "информация о первом заказе третьего юзера";
        ordersEntity.userId = 3;


        orders.add(ordersEntity);
        DatabaseManager.getInstance(context).getHseDao().insertOrder(orders);

    }

    private Date dateFromString(String val){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        try {
            return simpleDateFormat.parse(val);
        }catch (ParseException e){

        }
        return null;
    }
}
