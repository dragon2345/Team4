package com.example.dragon.team4_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

    @SerializedName("Questionid")
    @Expose
    private String questionid;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Questtioncontent")
    @Expose
    private String questtioncontent;
    @SerializedName("Listid")
    @Expose
    private String listid;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("Username")
    @Expose
    private String username;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuesttioncontent() {
        return questtioncontent;
    }

    public void setQuesttioncontent(String questtioncontent) {
        this.questtioncontent = questtioncontent;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}