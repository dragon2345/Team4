package com.example.dragon.team4_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity {
    private TextView textAppNameHome, textHome;
    private Button btnUploadMusic, btnRequestMusic, btnMusicList, btnMyMusic;

    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String ENGLISH_FONT = "font/englishfont1.TTF";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        findViewById();
        setTypeface();

        btnMyMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,MusicPlay.class));
            }
        });
    }

    private void findViewById(){
        textAppNameHome = findViewById(R.id.textAppNameHome);
        textHome = findViewById(R.id.textHome);

        btnUploadMusic = findViewById(R.id.btnUploadMusic);
        btnMusicList = findViewById(R.id.btnMusicList);
        btnRequestMusic = findViewById(R.id.btnRequestMusic);
        btnMyMusic = findViewById(R.id.btnMyMusic);

    }

    private void setTypeface(){
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), KOREAN_FONT);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), ENGLISH_FONT);
        textAppNameHome.setTypeface(typeface1);
        textHome.setTypeface(typeface2);
    }
}
