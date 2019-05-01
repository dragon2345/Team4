package com.example.dragon.team4_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class Register extends Activity {
    private TextView textRegister, textResultRegister, btnLoginRegister, btnFindPasswordRegister;
    private EditText editTextUserNameRegister, editTextPasswordRegister, editTextRePasswordRegister, editTextYourNameRegister, editTextEmailRegister;
    private Button btnRegister;

    String urlRegister = "http://203.234.62.86/mucon/signup.php";

    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String KOREAN_FONT_2 = "font/koreanfont2.TTF";
    public static final String ENGLISH_FONT = "font/englishfont1.TTF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findViewById();
        setTypeface();
    }

    private void findViewById(){
        textRegister = findViewById(R.id.textRegister);
        textResultRegister = findViewById(R.id.textResultRegister);

        btnLoginRegister = findViewById(R.id.btnLoginRegister);
        btnFindPasswordRegister = findViewById(R.id.btnFindPasswordRegister);

        editTextUserNameRegister = findViewById(R.id.editTextUserNameRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        editTextRePasswordRegister = findViewById(R.id.editTextRePasswordRegister);
        editTextYourNameRegister = findViewById(R.id.editTextYourNameRegister);
        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);

        btnRegister = findViewById(R.id.btnRegister);

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        btnFindPasswordRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, FindPassword.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String username = editTextUserNameRegister.getText().toString().trim();
                String password = editTextPasswordRegister.getText().toString().trim();
                String repassword = editTextRePasswordRegister.getText().toString().trim();
                String yourname = editTextYourNameRegister.getText().toString().trim();
                String email = editTextEmailRegister.getText().toString().trim();

                if(username.isEmpty()){
                    editTextUserNameRegister.setHintTextColor(getResources().getColor(R.color.red));
                    editTextUserNameRegister.setHint("Username 입력하세요!");
                }else if(password.isEmpty()){
                    editTextPasswordRegister.setHintTextColor(getResources().getColor(R.color.red));
                    editTextPasswordRegister.setHint("Password 입력하세요!");
                }else if(repassword.isEmpty()){
                    editTextRePasswordRegister.setHintTextColor(getResources().getColor(R.color.red));
                    editTextRePasswordRegister.setHint("RePassword 입력하세요!");
                }else if(yourname.isEmpty()){
                    editTextYourNameRegister.setHintTextColor(getResources().getColor(R.color.red));
                    editTextYourNameRegister.setHint("Yourname 입력하세요!");
                }else if(email.isEmpty()){
                    editTextEmailRegister.setHintTextColor(getResources().getColor(R.color.red));
                    editTextEmailRegister.setHint("email 입력하세요!");
                }else{
                    Register(urlRegister);
                }
            }
        });
    }

    private void Register(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("username")){
                            textResultRegister.setText("Username가 이미 존재했습니다!");
                        }else if(response.trim().equals("email1")){
                            textResultRegister.setText("이 이메일이 사용되었습니다!");
                        }else if(response.trim().equals("email2")){
                            textResultRegister.setText("이메일이 안 맞습니다!");
                        }else if(response.trim().equals("repassword")){
                            textResultRegister.setText("다시 password를 잘 못 입력합니다!");
                        }else if(response.trim().equals("success")){
                            textResultRegister.setText("화원가입이 성공되었습니다!");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "회원가입이 실패합니다!", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", editTextUserNameRegister.getText().toString().trim());
                params.put("password", editTextPasswordRegister.getText().toString().trim());
                params.put("repassword", editTextRePasswordRegister.getText().toString().trim());
                params.put("name", editTextYourNameRegister.getText().toString().trim());
                params.put("email", editTextEmailRegister.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void setTypeface(){
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), KOREAN_FONT);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), KOREAN_FONT_2);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), ENGLISH_FONT);
        textRegister.setTypeface(typeface1);
        textResultRegister.setTypeface(typeface2);
    }
}
