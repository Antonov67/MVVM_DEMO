package com.example.mvvm_demo.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.example.mvvm_demo.dao.DaoInt;
import com.example.mvvm_demo.dao.DatabaseManager;
import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.OrdersWithUsersEntity;
import com.example.mvvm_demo.dao.UserEntity;
import com.example.mvvm_demo.response.PoetryResponse;
import com.example.mvvm_demo.response.PoetryResponseInt;
import com.example.mvvm_demo.response.RandomPoem;

import java.util.List;

public class Repository {
    private DatabaseManager databaseManager;
    private DaoInt dao;
    private PoetryResponseInt poetryResponse;

    public Repository(Context context) {
        databaseManager = DatabaseManager.getInstance(context);
        dao = databaseManager.getHseDao();
        poetryResponse = new PoetryResponse();
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

    //вставка одного Юзера с возвратом  его id для последующей вставки данных по этому id
    public LiveData<Long> insertOneUser(UserEntity data){
        return dao.insertOneUser(data);
    };

    public LiveData<List<RandomPoem>> getPoems(){
        return poetryResponse.getPoems();
    }

}
