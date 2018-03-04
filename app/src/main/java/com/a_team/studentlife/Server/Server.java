package com.a_team.studentlife.Server;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Server {
    @POST("api")
    Call<String> sendMessage(@Query("message") String message);
}
