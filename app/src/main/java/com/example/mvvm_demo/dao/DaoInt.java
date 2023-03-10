package com.example.mvvm_demo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoInt {

    @Query("SELECT * FROM 'users'")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM 'orders'")
    LiveData<List<OrdersEntity>> getAllOrders();

    @Transaction
    @Query("SELECT * FROM 'orders'")
    LiveData<List<OrdersWithUsersEntity>> getAllOrdersWithUsers();

    @Insert
    void insertUser(List<UserEntity> data);

    //вставка одного Юзера с возвратом  его id для последующей вставки данных по этому id
    @Insert
    long insertOneUser(UserEntity data);

    @Insert
    void insertOrder(List<OrdersEntity> data);

    @Delete
    void deleteUser(List<UserEntity> data);

    @Delete
    void deleteOrder(List<OrdersEntity> data);


}
