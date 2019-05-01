package com.example.dragon.team4_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {
    private TextView textAppName, textLogin, textResult;
    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private TextView btnRegister, btnFindPassword;

    String urlLogin = "http://203.234.62.86/mucon/login.php";

    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String KOREAN_FONT_2 = "font/koreanfont2.TTF";
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
        textResult = findViewById(R.id.textResult);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername.setText("");
        editTextPassword.setText("");

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnFindPassword = findViewById(R.id.btnFindPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if(username.isEmpty()){
                    editTextUsername.setHintTextColor(getResources().getColor(R.color.red));
                    editTextUsername.setHint("username 입력하세요!");
                }else if(password.isEmpty()){
                    editTextPassword.setHintTextColor(getResources().getColor(R.color.red));
                    editTextPassword.setHint("password 입력하세요!");
                }else{
                    Login(urlLogin);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        btnFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, FindPassword.class));
            }
        });
    }

    private void setTypeface(){
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), KOREAN_FONT);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), KOREAN_FONT_2);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), ENGLISH_FONT);
        textAppName.setTypeface(typeface1);
        textResult.setTypeface(typeface2);
        textLogin.setTypeface(typeface3);
    }

    private void Login(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            startActivity(new Intent(Login.this, Home.class));
                        }else if(response.trim().equals("error")){
                            textResult.setText("계정의 정보가 안 맞습니다!");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "로그인이 실패합니다!", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", editTextUsername.getText().toString().trim());
                params.put("password", editTextPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
