package com.example.mvvm_demo.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.mvvm_demo.dao.UserEntity;

import java.util.Date;

@Entity(tableName = "orders", foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE)})
public class OrdersEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "order_info")
    @NonNull
    public String orderInfo;

    @ColumnInfo(name = "user_id", index = true)
    public int userId;

    @ColumnInfo(name = "date")
    @NonNull
    public Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
