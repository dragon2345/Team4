package com.example.dragon.team4_project.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragon.team4_project.Model.SelectedQuestion;
import com.example.dragon.team4_project.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder>{
    Context context;
    ArrayList<SelectedQuestion> questionarray;

    public QuestionListAdapter(Context context, ArrayList<SelectedQuestion> questionarray) {
        this.context = context;
        this.questionarray = questionarray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_select_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SelectedQuestion selectedQuestion = (SelectedQuestion) questionarray.get(i);
        viewHolder.txttitle.setText(selectedQuestion.getTitle());
        viewHolder.txtcontent.setText(selectedQuestion.getQuesttioncontent());
        viewHolder.txtindex.setText(i + 1 + "");
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
        }
    }
}
