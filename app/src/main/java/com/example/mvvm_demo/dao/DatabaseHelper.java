package com.example.mvvm_demo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {UserEntity.class, OrdersEntity.class},
version = 1,
exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DatabaseHelper extends RoomDatabase {
    public static final String DATABASE_NAME = "db2";

    public abstract DaoInt daoInt();
}
