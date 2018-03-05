package com.a_team.studentlife.Server.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static volatile Retrofit retrofit = null;

    private RetrofitClient() {}

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null)
            synchronized (RetrofitClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        return retrofit;
    }
}
