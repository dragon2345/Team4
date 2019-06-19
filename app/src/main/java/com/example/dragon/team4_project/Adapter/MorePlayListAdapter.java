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
import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MorePlayListAdapter extends RecyclerView.Adapter<MorePlayListAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> moreplaylist;

    public MorePlayListAdapter(Context context, ArrayList<PlayList> moreplaylist) {
        this.context = context;
        this.moreplaylist = moreplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_more_play_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PlayList playList = moreplaylist.get(i);
        Picasso.with(context).load(playList.getPicturelink()).into(viewHolder.imageView);
        viewHolder.textView.setText(playList.getListname());

    }

    @Override
    public int getItemCount() {
        return moreplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewmoreplaylist);
            textView = itemView.findViewById(R.id.textviewmoreplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("itemplaylist", moreplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
