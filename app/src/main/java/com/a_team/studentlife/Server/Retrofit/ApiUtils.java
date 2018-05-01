package com.a_team.studentlife.Server.Retrofit;


import com.a_team.studentlife.Server.APIService;

public class ApiUtils {
    private ApiUtils() {}
    private static final String BASE_URL =
//            "http://172.20.10.3/";
            "http://82.209.228.129/";
//    "http://192.168.43.26/";
    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
