package com.example.dragon.team4_project.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dragon.team4_project.Adapter.LikedQuestionAdapter;
import com.example.dragon.team4_project.Model.LikedQuestion;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Liked_Question extends Fragment{
    /*View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liked_question, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    */
    View view;

    RecyclerView recyclerView ;

    LikedQuestionAdapter likedQuestionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liked_question, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewlikedquestion);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<LikedQuestion>> callback = dataservice.GetLikedQuestion();
        callback.enqueue(new Callback<List<LikedQuestion>>() {
            @Override
            public void onResponse(Call<List<LikedQuestion>> call, Response<List<LikedQuestion>> response) {
                ArrayList<LikedQuestion> likedQuestionsList = (ArrayList<LikedQuestion>) response.body();

                likedQuestionAdapter = new LikedQuestionAdapter(getActivity(), likedQuestionsList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(likedQuestionAdapter);


            }

            @Override
            public void onFailure(Call<List<LikedQuestion>> call, Throwable t) {

            }
        });
    }


}
