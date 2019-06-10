package com.example.dragon.team4_project.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.dragon.team4_project.R.*;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    public PlaylistAdapter(@NonNull Context context, int resource,@NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtnameplaylist;
        ImageView imgbackground, imgplaylist;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtnameplaylist = convertView.findViewById(R.id.textviewnameplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getPicturelink()).into(viewHolder.imgplaylist);
        viewHolder.txtnameplaylist.setText(playList.getListname());

        if(position==1){
            viewHolder.imgbackground.setBackgroundResource(R.drawable.banner2);
        }else if(position==2){
            viewHolder.imgbackground.setBackgroundResource(R.drawable.banner3);
        }
        return convertView;
    }
}
