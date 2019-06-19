package com.example.dragon.team4_project.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dragon.team4_project.Model.Comment;
import com.example.dragon.team4_project.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    Context context;
    ArrayList<Comment> arrayList;

    public CommentAdapter(Context context, ArrayList<Comment> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_comment_question, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Comment comment = arrayList.get(i);
        viewHolder.textviewusernamecomment.setText(comment.getUsername());
        viewHolder.textviewcontentcomment.setText(comment.getContent());
        viewHolder.textviewindex.setText(i + 1 + "");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textviewindex, textviewusernamecomment, textviewcontentcomment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewindex = itemView.findViewById(R.id.textviewcommentindex);
            textviewusernamecomment = itemView.findViewById(R.id.textviewusernamecomment);
            textviewcontentcomment = itemView.findViewById(R.id.textviewcontentcomment);
        }
    }
}
