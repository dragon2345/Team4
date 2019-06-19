package com.example.dragon.team4_project.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragon.team4_project.Activity.ListActivity;
import com.example.dragon.team4_project.Activity.Main;
import com.example.dragon.team4_project.Model.LikedQuestion;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedQuestionAdapter extends RecyclerView.Adapter<LikedQuestionAdapter.ViewHolder> {
    Context context;
    ArrayList<LikedQuestion> likedQuestionsList;
    SharedPreferences sharedPreferences;

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

        if(likedQuestion.getIslike().equals("liked")){
            viewHolder.imglove.setImageResource(R.drawable.iconloved);
        }else if(likedQuestion.getIslike().equals("unliked")){
            viewHolder.imglove.setImageResource(R.drawable.iconlove);
        }

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

            imglove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);
                    // Toast.makeText(context, sharedPreferences.getString("userid","") , Toast.LENGTH_LONG).show();

                    if(likedQuestionsList.get(getPosition()).getIslike().equals("liked")){
                        imglove.setImageResource(R.drawable.iconlove);
                        Dataservice dataservice = APIService.getService();
                        Call<String> callback = dataservice.UpdateLike("dislike",sharedPreferences.getString("userid",""), likedQuestionsList.get(getPosition()).getQuestionid());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if(result.equals("Liked")){
                                    // Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                }else if(result.equals("Unliked")){
                                    // Toast.makeText(context, "Unliked", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }else if(likedQuestionsList.get(getPosition()).getIslike().equals("unliked")){
                        imglove.setImageResource(R.drawable.iconloved);
                        Dataservice dataservice = APIService.getService();
                        Call<String> callback = dataservice.UpdateLike("like",sharedPreferences.getString("userid",""), likedQuestionsList.get(getPosition()).getQuestionid());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if(result.equals("Liked")){
                                    // Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                }else if(result.equals("Unliked")){
                                    // Toast.makeText(context, "Unliked", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                    Intent intent = new Intent(context, Main.class);
                    context.startActivity(intent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("likedquestion", likedQuestionsList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

