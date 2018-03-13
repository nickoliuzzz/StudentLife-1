package com.a_team.studentlife.card_view_filling;

import android.widget.ImageView;

import com.a_team.studentlife.R;

import java.util.ArrayList;
import java.util.List;

public class NewsPost {
    private Integer participants;
    private Integer likes;
    private Integer postImageId;
    private Integer userImageId;
    private String userName;
    private String postText;

    public NewsPost(Integer participants, Integer likes, Integer postImageId, Integer userImageId, String userName, String postText) {
        this.participants = participants;
        this.likes = likes;
        this.postImageId = postImageId;
        this.userImageId = userImageId;
        this.userName = userName;
        this.postText = postText;
    }

    public Integer getParticipants() {
        return participants;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getPostImageId() {
        return postImageId;
    }

    public Integer getUserImageId() {
        return userImageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostText() {
        return postText;
    }

    public static List<NewsPost> getPostItems() {
        ArrayList<NewsPost> postItems = new ArrayList<>();
        postItems.add(new NewsPost(50,23, R.drawable.postpicture, R.drawable.user_avatar,
                "УНИВЕР", "Ректор " +
                "университета БГУиР рассказал о значимости бога в образовательном процессе. 25.02.2018 (17:00) во 2-м корпусе " +
                "в актовом зале состоится мероприятие, посвященное данному событию."));
        postItems.add(new NewsPost(65,11, R.drawable.postpicture1, R.drawable.user_avatar1,
                "ТРиТПО", "Меропрятие " +
                "посвящено прекрасному предмету ТРиТПО. Именно на нем вы можете получить дополниельные баллы к лабораторным " +
                "и кучу положительных эмоций. Ждем вас! Место встречи 5й корпус аудитория - 512."));
        postItems.add(new NewsPost(70,34, R.drawable.postpicture2, R.drawable.user_avatar2,
                "ОБЩАГА", "Студенты из " +
                "общежития №1 жалуются на проведение ремонта в соседних блоках, что влечет за собой большое скопление " +
                "мусора на коридорах. Обсуждение данного вопроса состоится в комнате отдыха общежития №1 27.02.2018 (18:45)."));
        postItems.add(new NewsPost(50,23, R.drawable.postpicture, R.drawable.user_avatar,
                "УНИВЕР", "Ректор " +
                "университета БГУиР рассказал о значимости бога в образовательном процессе. 25.02.2018 (17:00) во 2-м корпусе " +
                "в актовом зале состоится мероприятие, посвященное данному событию."));
        postItems.add(new NewsPost(65,11, R.drawable.postpicture1, R.drawable.user_avatar1,
                "ТРиТПО", "Меропрятие " +
                "посвящено прекрасному предмету ТРиТПО. Именно на нем вы можете получить дополниельные баллы к лабораторным " +
                "и кучу положительных эмоций. Ждем вас! Место встречи 5й корпус аудитория - 512."));
        postItems.add(new NewsPost(70,34, R.drawable.postpicture2, R.drawable.user_avatar2,
                "ОБЩАГА", "Студенты из " +
                "общежития №1 жалуются на проведение ремонта в соседних блоках, что влечет за собой большое скопление " +
                "мусора на коридорах. Обсуждение данного вопроса состоится в комнате отдыха общежития №1 27.02.2018 (18:45)."));
        postItems.add(new NewsPost(50,23, R.drawable.postpicture, R.drawable.user_avatar,
                "УНИВЕР", "Ректор " +
                "университета БГУиР рассказал о значимости бога в образовательном процессе. 25.02.2018 (17:00) во 2-м корпусе " +
                "в актовом зале состоится мероприятие, посвященное данному событию."));
        postItems.add(new NewsPost(65,11, R.drawable.postpicture1, R.drawable.user_avatar1,
                "ТРиТПО", "Меропрятие " +
                "посвящено прекрасному предмету ТРиТПО. Именно на нем вы можете получить дополниельные баллы к лабораторным " +
                "и кучу положительных эмоций. Ждем вас! Место встречи 5й корпус аудитория - 512."));
        postItems.add(new NewsPost(70,34, R.drawable.postpicture2, R.drawable.user_avatar2,
                "ОБЩАГА", "Студенты из " +
                "общежития №1 жалуются на проведение ремонта в соседних блоках, что влечет за собой большое скопление " +
                "мусора на коридорах. Обсуждение данного вопроса состоится в комнате отдыха общежития №1 27.02.2018 (18:45)."));
        postItems.add(new NewsPost(50,23, R.drawable.postpicture, R.drawable.user_avatar,
                "УНИВЕР", "Ректор " +
                "университета БГУиР рассказал о значимости бога в образовательном процессе. 25.02.2018 (17:00) во 2-м корпусе " +
                "в актовом зале состоится мероприятие, посвященное данному событию."));
        postItems.add(new NewsPost(65,11, R.drawable.postpicture1, R.drawable.user_avatar1,
                "ТРиТПО", "Меропрятие " +
                "посвящено прекрасному предмету ТРиТПО. Именно на нем вы можете получить дополниельные баллы к лабораторным " +
                "и кучу положительных эмоций. Ждем вас! Место встречи 5й корпус аудитория - 512."));
        postItems.add(new NewsPost(70,34, R.drawable.postpicture2, R.drawable.user_avatar2,
                "ОБЩАГА", "Студенты из " +
                "общежития №1 жалуются на проведение ремонта в соседних блоках, что влечет за собой большое скопление " +
                "мусора на коридорах. Обсуждение данного вопроса состоится в комнате отдыха общежития №1 27.02.2018 (18:45)."));
        return postItems;
    }
}
