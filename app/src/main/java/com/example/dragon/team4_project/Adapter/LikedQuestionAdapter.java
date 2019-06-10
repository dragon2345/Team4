package com.example.dragon.team4_project.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragon.team4_project.Model.LikedQuestion;
import com.example.dragon.team4_project.R;

import java.util.ArrayList;

public class LikedQuestionAdapter extends RecyclerView.Adapter<LikedQuestionAdapter.ViewHolder> {
    Context context;
    ArrayList<LikedQuestion> likedQuestionsList;

    public LikedQuestionAdapter(Context context, ArrayList<LikedQuestion> likedQuestionsList) {
        this.context = context;
        this.likedQuestionsList = likedQuestionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_liked_question, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LikedQuestion likedQuestion = likedQuestionsList.get(i);
        viewHolder.txttitle.setText(likedQuestion.getTitle());
        viewHolder.txtcontent.setText(likedQuestion.getQuesttioncontent());

        if(i==0){
            viewHolder.imgrank.setBackgroundResource(R.drawable.rank1);
        }else if(i==1){
            viewHolder.imgrank.setBackgroundResource(R.drawable.rank2);
        }else if(i==2){
            viewHolder.imgrank.setBackgroundResource(R.drawable.rank3);
        }

    }

    @Override
    public int getItemCount() {
        return likedQuestionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttitle, txtcontent;
        ImageView imgrank, imglove;
        public ViewHolder(View itemView){
            super(itemView);
            txttitle = itemView.findViewById(R.id.textviewlikedquestiontitle);
            txtcontent = itemView.findViewById(R.id.textviewlikedquestioncontent);
            imgrank = itemView.findViewById(R.id.imageviewlikedquestion);
            imglove = itemView.findViewById(R.id.imageviewlove);
        }
    }
}

