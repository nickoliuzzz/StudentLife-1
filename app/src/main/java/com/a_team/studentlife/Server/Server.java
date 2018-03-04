package com.a_team.studentlife.Server;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Server {
    @POST("user/addemail")
    Call<List<AddEmailResult>> addEmail(@Body List<String> emails);
}
