package com.example.mvvm_demo.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OrdersWithUsersEntity {
    @Embedded
    public OrdersEntity ordersEntity;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "id"
    )
    public UserEntity usersEntity;
}
