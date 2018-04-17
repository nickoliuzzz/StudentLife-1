package com.a_team.studentlife.UserInformation;

import android.content.ContextWrapper;

import com.a_team.studentlife.RegAndAuth.LogInActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserTest {

    @Mock
    private ContextWrapper context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = new ContextWrapper(new LogInActivity());
        User.getUserInstance().setId(5);
        User.getUserInstance().setSex(1);
        User.getUserInstance().setEmail("ferfrfrf@mail.ru");
        User.getUserInstance().setLogin("5alex");
        User.getUserInstance().setPassword("12345aa12345");
        User.getUserInstance().setFirstName("Алексей");
        User.getUserInstance().setLastName("Некузьменко");
    }

    @Test
    public void setSex() {
//        User.getUserInstance().setSex(20);
//        User.getUserInstance().setSex(-5);
        User.getUserInstance().setSex(1);
        Assert.assertEquals(User.getUserInstance().getSex().intValue(), 1);
        User.getUserInstance().setSex(0);
        Assert.assertEquals(User.getUserInstance().getSex().intValue(), 0);
    }
}