package com.example.mvvm_demo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_demo.dao.OrdersEntity;
import com.example.mvvm_demo.dao.OrdersWithUsersEntity;
import com.example.mvvm_demo.dao.UserEntity;
import com.example.mvvm_demo.response.RandomPoem;

import java.util.List;



public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public LiveData<List<UserEntity>> getAllUsers() {
        return repository.getAllUsers();
    }

    public LiveData<List<OrdersEntity>> getAllOrders(){
        return repository.getAllOrders();
    };

    public LiveData<List<OrdersWithUsersEntity>> getAllOrdersWithUsers(){
        return repository.getAllOrdersWithUsers();
    };

    //вставка одного Юзера с возвратом  его id для последующей вставки данных по этому id
    public LiveData<Long> insertOneUser(UserEntity data){
        return repository.insertOneUser(data);
    };

    public LiveData<List<RandomPoem>> getPoems(){
        return repository.getPoems();
    }
}
