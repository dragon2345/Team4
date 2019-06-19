package com.example.dragon.team4_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragon.team4_project.Activity.ListActivity;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.R;

import java.util.ArrayList;

public class SearchQuestionAdapter extends RecyclerView.Adapter<SearchQuestionAdapter.ViewHolder>{
    Context context;
    ArrayList<Question> arrayList;

    public SearchQuestionAdapter(Context context, ArrayList<Question> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_search_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Question question = arrayList.get(i);
        viewHolder.textViewTitle.setText(question.getTitle());
        viewHolder.textViewContent.setText(question.getQuesttioncontent());
        viewHolder.textViewUsername.setText(question.getUsername());

        if(arrayList.get(i).getListid().equals("1")){
            viewHolder.imageView.setBackgroundResource(R.drawable.classic);
        }else if(arrayList.get(i).getListid().equals("2")){
            viewHolder.imageView.setBackgroundResource(R.drawable.kpop);
        }else if(arrayList.get(i).getListid().equals("3")){
            viewHolder.imageView.setBackgroundResource(R.drawable.rap);
        }else if(arrayList.get(i).getListid().equals("4")){
            viewHolder.imageView.setBackgroundResource(R.drawable.electronic);
        }else if(arrayList.get(i).getListid().equals("5")){
            viewHolder.imageView.setBackgroundResource(R.drawable.hiphop);
        }else if(arrayList.get(i).getListid().equals("6")){
            viewHolder.imageView.setBackgroundResource(R.drawable.dance);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle, textViewContent, textViewUsername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewdongsearchquestion);
            textViewTitle = itemView.findViewById(R.id.textviewsearchquestiontitle);
            textViewContent = itemView.findViewById(R.id.textviewsearchquestioncontent);
            textViewUsername = itemView.findViewById(R.id.textviewsearchquestionusername);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("banner", arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
