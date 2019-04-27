package com.example.dragon.team4_project;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Login extends Activity {
    private TextView textAppName;
    private TextView textLogin;

    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String ENGLISH_FONT = "font/englishfont1.TTF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById();
        setTypeface();
    }

    private void findViewById(){
        textAppName = findViewById(R.id.textAppName);
        textLogin = findViewById(R.id.textLogin);
    }

    private void setTypeface(){
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), KOREAN_FONT);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), ENGLISH_FONT);
        textAppName.setTypeface(typeface1);
        textLogin.setTypeface(typeface2);
    }
}
