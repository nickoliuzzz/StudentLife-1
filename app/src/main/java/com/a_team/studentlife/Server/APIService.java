package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.ServerResponse.CreateLeagueResponse;
import com.a_team.studentlife.Server.ServerResponse.ListAllUserNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeagueNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;
import com.a_team.studentlife.Server.ServerResponse.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("api/")
    Call<ServerResponse> sendMessagePOST(@Field("message") String message);

    @FormUrlEncoded
    @POST("api/getLeagues/")
    Call<ListLeaguesResponse> getListOfLeagues(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("api/getListOfEvent/")
    Call<ListLeagueNewsResponse> getListOfLeagueNews(@Field("leagueId") Integer leagueId);

    @FormUrlEncoded
    @POST("api/userEvents/")
    Call<ListAllUserNewsResponse> getAllUserNews(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("api/createChildLeague/")
    Call<CreateLeagueResponse> createChildLeague(@Field("parentLeagueId") Integer parentLeagueId,
                                                 @Field("leagueName") String leagueName,
                                                 @Field("description") String description);
}
