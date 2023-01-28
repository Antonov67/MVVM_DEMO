package com.example.mvvm_demo.response;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface PoetryResponseInt {
    LiveData<List<RandomPoem>> getPoems();
}
