package com.a_team.studentlife.Server.Retrofit;


import com.a_team.studentlife.Server.APIService;

public class ApiUtils {
    private ApiUtils() {}
    private static final String BASE_URL = "http://82.209.228.129/";
    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
