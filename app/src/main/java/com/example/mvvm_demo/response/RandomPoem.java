package com.example.mvvm_demo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomPoem {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("lines")
    @Expose
    private List<String> lines = null;
    @SerializedName("linecount")
    @Expose
    public String linecount;

    public String getPoemText(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : lines) {
            stringBuilder.append(str).append("\n");
        }
        return stringBuilder.toString();
    }
}
