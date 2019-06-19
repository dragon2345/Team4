package com.example.dragon.team4_project.Service;

import com.example.dragon.team4_project.Model.Comment;
import com.example.dragon.team4_project.Model.LikedQuestion;
import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.Model.SelectedQuestion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("question.php")
    Call<List<Question>> GetDataBanner();

    @GET("playlist.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("getallplaylist.php")
    Call<List<PlayList>> GetMorePlayList();

    @FormUrlEncoded
    @POST("likedquestion.php")
    Call<List<LikedQuestion>> GetLikedQuestion(@Field("username") String username);

    @FormUrlEncoded
    // send with value 'idquestion'
    @POST("idquestionlist.php")
    Call<List<SelectedQuestion>> GetSelectedQuestion(@Field("idquestion") String idquestion, @Field("username") String username);

    @FormUrlEncoded
    // send with value 'idlist'
    @POST("idquestionlist.php")
    Call<List<SelectedQuestion>> GetSelectedQuestionwithPlaylist(@Field("idlist") String idlist, @Field("username") String username);

    @FormUrlEncoded
    // update like and dislike
    @POST("updatelike.php")
    Call<String> UpdateLike(@Field("islike") String islike , @Field("username") String username , @Field("questionid") String questionid);

    @FormUrlEncoded
    // update like and dislike
    @POST("searchquestion.php")
    Call<List<Question>> GetSearchQuestion(@Field("search") String search);

    @FormUrlEncoded
    // update like and dislike
    @POST("comment.php")
    Call<List<Comment>> GetCommentList(@Field("questionid") String questionid);

    @FormUrlEncoded
    // update like and dislike
    @POST("sendcomment.php")
    Call<String> SendComment(@Field("questionid") String questionid, @Field("username") String username, @Field("content") String content);
}
