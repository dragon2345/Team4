package com.example.dragon.team4_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedQuestion {

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
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("Likenumber")
    @Expose
    private String likenumber;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("Picture")
    @Expose
    private String picture;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLikenumber() {
        return likenumber;
    }

    public void setLikenumber(String likenumber) {
        this.likenumber = likenumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}