package com.a_team.studentlife.UserInformation;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class User {
    private static final String FILE_NAME = "UserInformation";
    private static volatile User user = null;
    private Integer id;
    private Integer sex;
    private Integer tempLeagueMoney;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        if (sex > 1 || sex < 0)
            this.sex = 1;
        else
            this.sex = sex;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTempLeagueMoney() {
        return tempLeagueMoney;
    }

    public void setTempLeagueMoney(Integer tempLeagueMoney) {
        this.tempLeagueMoney = tempLeagueMoney;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean saveUserInformation(Context context) {
        if (User.user == null || User.getUserInstance().getId() == null ||
                User.getUserInstance().getSex() == null || User.getUserInstance().getLogin() == null ||
                User.getUserInstance().getPassword() == null || User.getUserInstance().getId() == null ||
                User.getUserInstance().getEmail() == null || User.getUserInstance().getFirstName() == null ||
                User.getUserInstance().getLastName() == null)
            return false;
        try {
            BufferedWriter bw = new BufferedWriter(new
                    OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)));
            bw.write(getId().toString());
            bw.newLine();
            bw.write(getSex().toString());
            bw.newLine();
            bw.write(getLogin());
            bw.newLine();
            bw.write(getPassword());
            bw.newLine();
            bw.write(getEmail());
            bw.newLine();
            bw.write(getFirstName());
            bw.newLine();
            bw.write(getLastName());
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean readUserInformation(Context context) {
        try {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(context.openFileInput(FILE_NAME)));
            String tempUserId;
            if ((tempUserId = br.readLine()) == null)
                return false;
            setId(Integer.parseInt(tempUserId));
            setSex(Integer.parseInt(br.readLine()));
            setLogin(br.readLine());
            setPassword(br.readLine());
            setEmail(br.readLine());
            setFirstName(br.readLine());
            setLastName(br.readLine());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void cleanUserInformation(Context context) {
        try {
            BufferedWriter bw = new BufferedWriter(new
                    OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
