package com.example.projectandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitclient {
    private MyAPI myapiObj;
    private static MyRetrofitclient instance =null;

    private MyRetrofitclient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myapiObj = retrofit.create(MyAPI.class);
    }
    public static synchronized MyRetrofitclient getInstance(){
        if (instance == null){
            instance = new MyRetrofitclient();
        }
        return instance;
    }
    public MyAPI getApi(){
        return myapiObj;
    }
}
