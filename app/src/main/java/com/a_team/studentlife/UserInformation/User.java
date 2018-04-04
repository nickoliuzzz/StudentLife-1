package com.a_team.studentlife.UserInformation;

public class User {
    private static volatile User user = null;
    private int id;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private User () {}

    public static User getUserInstance() {
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
