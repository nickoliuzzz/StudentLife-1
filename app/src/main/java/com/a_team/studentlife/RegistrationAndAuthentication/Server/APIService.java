package com.studentlife.studentlife.RegistrationAndAuthentication.Server;

import com.studentlife.studentlife.RegistrationAndAuthentication.Server.TestResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("api/register")
    Call<ServerResponse> sendFirstName(@Query("firstname") String firstname,
                                       @Query("lastname") String lastname,
                                       @Query("username") String username,
                                       @Query("password") String password,
                                       @Query("email") String email,
                                       @Query("sex") Boolean sex,
                                       @Query("birthday") String birthday);
}
