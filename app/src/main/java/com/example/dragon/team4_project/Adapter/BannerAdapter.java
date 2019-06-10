package com.example.dragon.team4_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragon.team4_project.Activity.ListActivity;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.R;


import java.util.ArrayList;

// Doc du lieu nhan duoc ra banner
public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Question> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Question> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);
        TextView txtquestiontitle = view.findViewById(R.id.textviewquestiontitle);
        TextView txtquestioncontent = view.findViewById(R.id.textviewquestioncontent);
        TextView txtquestionusername = view.findViewById(R.id.textviewquestionusername);

        // thay doi mau cho cac panner
        if(position==1){
            imgbackgroundbanner.setBackgroundResource(R.drawable.background_banner2);
        }else if(position==2){
            imgbackgroundbanner.setBackgroundResource(R.drawable.background_banner3);
        }
        txtquestiontitle.setText(arrayListbanner.get(position).getTitle());
        txtquestioncontent.setText(arrayListbanner.get(position).getQuesttioncontent());
        txtquestionusername.setText(arrayListbanner.get(position).getUsername());

        // Su kien
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("banner", arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });

        // xac nhan them vao view
        container.addView(view);

        return view;
    }

    @Override // Xoa nhung view banner da hien thi de hien thi view moi
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
