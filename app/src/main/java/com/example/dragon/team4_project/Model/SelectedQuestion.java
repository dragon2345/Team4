package com.example.dragon.team4_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SelectedQuestion implements Parcelable {

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

    @SerializedName("Islike")
    @Expose
    private String islike;

    protected SelectedQuestion(Parcel in) {
        questionid = in.readString();
        title = in.readString();
        questtioncontent = in.readString();
        listid = in.readString();
        link = in.readString();
        likenumber = in.readString();
        userid = in.readString();
        picture = in.readString();
        islike = in.readString();
    }

    public static final Creator<SelectedQuestion> CREATOR = new Creator<SelectedQuestion>() {
        @Override
        public SelectedQuestion createFromParcel(Parcel in) {
            return new SelectedQuestion(in);
        }

        @Override
        public SelectedQuestion[] newArray(int size) {
            return new SelectedQuestion[size];
        }
    };

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

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionid);
        dest.writeString(title);
        dest.writeString(questtioncontent);
        dest.writeString(listid);
        dest.writeString(link);
        dest.writeString(likenumber);
        dest.writeString(userid);
        dest.writeString(picture);
        dest.writeString(islike);
    }
}