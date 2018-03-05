package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.TestResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("api")
    @FormUrlEncoded
    Call<ServerResponse> sendMessage(@Field("message") String message);
}
