package com.studentlife.studentlife.RegistrationAndAuthentication.Server.Retrofit;


import com.studentlife.studentlife.RegistrationAndAuthentication.Server.APIService;

public class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "http://82.209.228.129/"/*http://172.20.10.3/*/;

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
