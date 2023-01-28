package com.example.mvvm_demo.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvvm_demo.dao.DaoInt;
import com.example.mvvm_demo.dao.DatabaseManager;
import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.OrdersWithUsersEntity;
import com.example.mvvm_demo.dao.UserEntity;

import java.util.List;

public class Repository {
    private DatabaseManager databaseManager;
    private DaoInt dao;

    public Repository(Context context) {
        databaseManager = DatabaseManager.getInstance(context);
        dao = databaseManager.getHseDao();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
      return dao.getAllUsers();
    }

    public LiveData<List<OrdersEntity>> getAllOrders(){
        return dao.getAllOrders();
    };

    public LiveData<List<OrdersWithUsersEntity>> getAllOrdersWithUsers(){
        return dao.getAllOrdersWithUsers();
    };

}
