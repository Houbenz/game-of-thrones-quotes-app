package com.houbenz.gameofthronesqutotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {



    private static final String BASE_URL="https://got-quotes.herokuapp.com/";


    private final static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static Retrofit getInstance(){

        return  retrofit;
    }

    public static Gson getGson() {
        return gson;
    }
}
