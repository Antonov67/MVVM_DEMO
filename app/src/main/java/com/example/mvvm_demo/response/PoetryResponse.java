package com.example.mvvm_demo.response;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoetryResponse implements PoetryResponseInt{
    MutableLiveData<List<RandomPoem>> poems = new MutableLiveData<>();
    @Override
    public LiveData<List<RandomPoem>> getPoems() {
        PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
        Call<List<RandomPoem>> call = service.getRandomPoem();
        call.enqueue(new Callback<List<RandomPoem>>() {
            @Override
            public void onResponse(Call<List<RandomPoem>> call, Response<List<RandomPoem>> response) {
                poems.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RandomPoem>> call, Throwable t) {
                Log.d("mvvm", t.getMessage());
            }
        });
        return poems;
    }
}
