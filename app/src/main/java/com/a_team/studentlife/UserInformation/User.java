package com.a_team.studentlife.UserInformation;

public class User {
    private static volatile User user = null;
    private int id;

    private User () {}

    public static User getUserInstanse() {
        if (user == null)
            synchronized (User.class) {
                if (user == null) {
                    user = new User();
                }
            }
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
