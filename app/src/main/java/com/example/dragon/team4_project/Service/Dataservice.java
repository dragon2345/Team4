package com.example.dragon.team4_project.Service;

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
    @GET("question_list.php")
    Call<List<Question>> GetDataBanner();

    @GET("list.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("likedquestion.php")
    Call<List<LikedQuestion>> GetLikedQuestion();

    @FormUrlEncoded
    @POST("question.php")
    Call<List<SelectedQuestion>> GetSelectedQuestion(@Field("idquestion") String idquestion);

    @FormUrlEncoded
    @POST("question.php")
    Call<List<SelectedQuestion>> GetSelectedQuestionwithPlaylist(@Field("idlist") String idlist);
}
