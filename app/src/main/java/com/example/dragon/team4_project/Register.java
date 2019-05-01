package com.example.dragon.team4_project;

import android.app.Activity;
import android.os.Bundle;

public class Register extends Activity {


    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String KOREAN_FONT_2 = "font/koreanfont2.TTF";
    public static final String ENGLISH_FONT = "font/englishfont1.TTF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

}
