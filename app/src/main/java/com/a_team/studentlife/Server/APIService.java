package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.TestResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("api")
    Call<ServerResponse> sendMessage(@Query("message") String message);

    @FormUrlEncoded
    @POST("api/")
    Call<ServerResponse> sendMessagePOST(@Field("message") String message);
}
