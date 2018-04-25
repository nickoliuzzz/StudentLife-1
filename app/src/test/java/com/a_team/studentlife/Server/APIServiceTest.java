package com.a_team.studentlife.Server;

import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ChangeUserInformationResponse;
import com.a_team.studentlife.Server.ServerResponse.LoginResponse;
import com.a_team.studentlife.Server.ServerResponse.RegistrationResponse;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class APIServiceTest {

    @Test
    public void Positive_test_login_request_on_the_server_should_return_ok() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.login("nikamon12345", "12345").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    assertEquals("Позитивный тест на авторизацию пользователя", "ok", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_login_request_on_the_server_should_return_error() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.login("nikamon123456", "123456").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    assertEquals("Негативный тест на авторизацию пользователя", "error", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Positive_test_login_on_response_from_the_server_should_return_true() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.login("nikamon12345", "12345").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assertTrue("Позитивный тест на ответ сервера вызова контроллера авторизация", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_login_on_response_from_the_server_if_server_is_turn_off_or_the_internet_on_the_phone_is_turn_off_should_return_false() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.login("nikamon12345", "12345").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assertFalse("Негативный тест на ответ сервера вызова контроллера авторизация", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Positive_test_registration_request_on_the_server_should_return_ok() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.sendFirstName("testRegistrationFirstName", "testRegistrationSecondName", "testRegistrationUserName",
                "testRegistrationPassword", "testRegistrationEmail", 1, "1997-09-28").enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    assertEquals("Позитивный тест на регистрацию пользователя", "ok", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_registration_request_on_the_server_should_return_error() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.sendFirstName("testRegistrationFirstName", "testRegistrationSecondName", "testRegistrationUserName",
                "testRegistrationPassword", "testRegistrationEmail", 1, "1997-09-28").enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().getError());
                    assertEquals("Негативный тест на регистрацию пользователя", "error", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                assertEquals(123,235);
            }
        });
    }

    @Test
    public void Positive_test_registration_on_response_from_the_server_should_return_true() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.sendFirstName("testRegistrationFirstName", "testRegistrationSecondName", "testRegistrationUserName",
                "testRegistrationPassword", "testRegistrationEmail", 1, "1997-09-28").enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                assertTrue("Позитивный тест на регистрацию пользователя", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_registration_on_response_from_the_server_if_server_is_turn_off_or_the_internet_on_the_phone_is_turn_off_should_return_false() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.sendFirstName("testRegistrationFirstName", "testRegistrationSecondName", "testRegistrationUserName",
                "testRegistrationPassword", "testRegistrationEmail", 1, "1997-09-28").enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                assertFalse("Негативный тест на ответ сервера вызова контроллера регистрации", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Positive_test_change_user_information_request_on_the_server_should_return_ok() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.changeUserInformation("10", "testChangeMail@gmail.com", "testChangeFirstName", "testChangeSecondName", "testChangePassword").enqueue(new Callback<ChangeUserInformationResponse>() {
            @Override
            public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                if (response.isSuccessful()) {
                    assertEquals("Позитивный тест на изменение данных пользователя", "ok", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_change_user_information_request_on_the_server_should_return_error() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.changeUserInformation("10", "nikitabytsko@gmail.com", "testChangeFirstName", "testChangeSecondName", "testChangePassword").enqueue(new Callback<ChangeUserInformationResponse>() {
            @Override
            public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                if (response.isSuccessful()) {
                    assertEquals("Негативный тест на изменение данных пользователя", "error", response.body().getError());
                }
            }

            @Override
            public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Positive_test_change_user_information_on_response_from_the_server_should_return_true() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.changeUserInformation("10", "positiveTestChangeMail@gmail.com", "positiveTestChangeFirstName", "positiveTestChangeSecondName", "positiveTestChangePassword").enqueue(new Callback<ChangeUserInformationResponse>() {
            @Override
            public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                assertTrue("Позитивный тест на регистрацию пользователя", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
            }
        });
    }

    @Test
    public void Negative_test_change_user_information_on_response_from_the_server_if_server_is_turn_off_or_the_internet_on_the_phone_is_turn_off_should_return_false() throws Exception {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.changeUserInformation("10", "testChangeMail@gmail.com", "testChangeFirstName", "testChangeSecondName", "testChangePassword").enqueue(new Callback<ChangeUserInformationResponse>() {
            @Override
            public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                assertFalse("Негативный тест на ответ сервера вызова контроллера регистрации", response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
            }
        });
    }
}