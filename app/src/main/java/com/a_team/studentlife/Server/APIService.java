package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.ServerResponse.ChangeUserInformationResponse;
import com.a_team.studentlife.Server.ServerResponse.CheckQuizResponse;
import com.a_team.studentlife.Server.ServerResponse.CheckSubAndNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.CreateLeagueResponse;
import com.a_team.studentlife.Server.ServerResponse.LikeMonipulationResponse;
import com.a_team.studentlife.Server.ServerResponse.ListAllUserNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeagueNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;
import com.a_team.studentlife.Server.ServerResponse.LoginResponse;
import com.a_team.studentlife.Server.ServerResponse.OwnEventsResponse;
import com.a_team.studentlife.Server.ServerResponse.RegistrationResponse;
import com.a_team.studentlife.Server.ServerResponse.SendReviewResponse;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.BuyingProductResponse;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.ListLeagueProductsResponse;
import com.a_team.studentlife.Server.ServerResponse.SubscribeLeagueResponse;
import com.a_team.studentlife.Server.ServerResponse.UnsubscribeLeagueResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("api/getLeagues/")
    Call<ListLeaguesResponse> getListOfLeagues(@Field("userId") Integer userId);

    @GET("api/getListOfEvent")
    Call<ListLeagueNewsResponse> getListOfLeagueNews(@Query("userId") Integer userId,
                                                     @Query("leagueId") Integer leagueId);

    @FormUrlEncoded
    @POST("api/userEvents/")
    Call<ListAllUserNewsResponse> getAllUserNews(@Field("userId") Integer userId);

    @FormUrlEncoded
    @POST("api/createChildLeague/")
    Call<CreateLeagueResponse> createChildLeague(@Field("parentLeagueId") Integer parentLeagueId,
                                                 @Field("leagueName") String leagueName,
                                                 @Field("description") String description);

    @GET("api/login")
    Call<LoginResponse> login(@Query("username") String userName,
                              @Query("password") String password);

    @GET("api/register")
    Call<RegistrationResponse> register(@Query("firstname") String firstname,
                                        @Query("lastname") String lastname,
                                        @Query("username") String username,
                                        @Query("password") String password,
                                        @Query("email") String email,
                                        @Query("sex") int sex,
                                        @Query("birthday") String birthday);

    @GET("api/getListOfLeagueShopProducts")
    Call<ListLeagueProductsResponse> getListOfLeagueShopProducts(@Query("userId") int userId,
                                                                  @Query("leagueId") int leagueId);

    @FormUrlEncoded
    @POST("api/buyingItem/")
    Call<BuyingProductResponse> buyProduct(@Field("userId") int userId,
                                           @Field("itemId") int productId);

    @POST("/api/user/edit")
    Call<ChangeUserInformationResponse> changeUserInformation(@Query("id") String id,
                                                              @Query("email") String email,
                                                              @Query("firstname") String firstname,
                                                              @Query("lastname") String secondname,
                                                              @Query("password") String newpassword);

    @GET("api/checkQuiz")
    Call<CheckQuizResponse> checkQuiz(@Query("userId") int userId);

    @GET("api/checkSubAndNews")
    Call<CheckSubAndNewsResponse> checkSubAndNews(@Query("userId") int userId);

    @FormUrlEncoded
    @POST("api/sendReview/")
    Call<SendReviewResponse> sendReview(@Field("userId") int userId,
                                        @Field("likeEvent") String likeEvent,
                                        @Field("connectionWithLearning") String connectionWithLearning,
                                        @Field("organizationLevel") String organizationLevel,
                                        @Field("doingAgain") String doingAgain,
                                        @Field("PSReview") String PSReview);
    @FormUrlEncoded
    @POST("/api/subscribeLeague")
    Call<SubscribeLeagueResponse> subscribeLeague(@Field("userId") Integer userId,
                                                  @Field("leagueId") Integer leagueId);
    @FormUrlEncoded
    @POST("/api/unsubscribeLeague")
    Call<UnsubscribeLeagueResponse> unsubscribeLeague(@Field("userId") Integer userId,
                                                      @Field("leagueId") Integer leagueId);

    @FormUrlEncoded
    @POST("/api/likeIncrement")
    Call<LikeMonipulationResponse> likeIncrement(@Field("userId") Integer userId,
                                                 @Field("eventId") Integer eventId);

    @FormUrlEncoded
    @POST("/api/likeDecrement")
    Call<LikeMonipulationResponse> likeDecrement(@Field("userId") Integer userId,
                                                 @Field("eventId") Integer eventId);

    @GET("api/getOwnEvents")
    Call<OwnEventsResponse> getOwnEvents(@Query("userId") int userId);

}
