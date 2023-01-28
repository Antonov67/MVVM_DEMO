package com.example.mvvm_demo.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = {"name"},unique = true)})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name = "";

    @ColumnInfo(name = "password")
    @NonNull
    public String pswrd = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
