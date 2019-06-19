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

import com.example.dragon.team4_project.Model.SelectedQuestion;
import com.example.dragon.team4_project.MusicPlay;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder>{
    // context is object where to set layout with data is arraylist object
    Context context;
    ArrayList<SelectedQuestion> questionarray;
    String pi;

    SharedPreferences sharedPreferences;

    public QuestionListAdapter(Context context, ArrayList<SelectedQuestion> questionarray, String pi) {
        this.context = context;
        this.questionarray = questionarray;
        this.pi = pi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // set view in   context
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_select_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SelectedQuestion selectedQuestion = questionarray.get(i);
        viewHolder.txttitle.setText(selectedQuestion.getTitle());
        viewHolder.txtcontent.setText(selectedQuestion.getQuesttioncontent());
        viewHolder.txtindex.setText(i + 1 + "");

        if(selectedQuestion.getIslike().equals("liked")){
            viewHolder.imglikenumberquestion.setImageResource(R.drawable.iconloved);
        }else if(selectedQuestion.getIslike().equals("unliked")){
            viewHolder.imglikenumberquestion.setImageResource(R.drawable.iconlove);
        }
    }

    @Override
    public int getItemCount() {
        return questionarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttitle, txtcontent;
        ImageView imglikenumberquestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewquestionindex);
            txttitle = itemView.findViewById(R.id.textviewtitlequestion);
            txtcontent = itemView.findViewById(R.id.textviewcontentquestion);
            imglikenumberquestion = itemView.findViewById(R.id.imageviewlikenumberquestion);

            imglikenumberquestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);

                    if(questionarray.get(getPosition()).getIslike().equals("liked")){
                        imglikenumberquestion.setImageResource(R.drawable.iconlove);
                        Dataservice dataservice = APIService.getService();
                        Call<String> callback = dataservice.UpdateLike("dislike",sharedPreferences.getString("userid",""), questionarray.get(getPosition()).getQuestionid());
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
                    }else if(questionarray.get(getPosition()).getIslike().equals("unliked")){
                        imglikenumberquestion.setImageResource(R.drawable.iconloved);
                        Dataservice dataservice = APIService.getService();
                        Call<String> callback = dataservice.UpdateLike("like",sharedPreferences.getString("userid",""), questionarray.get(getPosition()).getQuestionid());
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
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicPlay.class);
                    intent.putExtra("question", questionarray.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }


    }
}
