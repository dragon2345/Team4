package com.example.dragon.team4_project.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragon.team4_project.Adapter.CommentAdapter;
import com.example.dragon.team4_project.Model.Comment;
import com.example.dragon.team4_project.Model.SelectedQuestion;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentActivity extends AppCompatActivity {
    SelectedQuestion question;
    TextView textviewquestion_title, textviewquestion_content, textviewquestion_username;

    RecyclerView recyclerView;

    EditText editTextComment;
    ImageView imageViewComment;

    ArrayList<Comment> commentArrayList;

    CommentAdapter commentAdapter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        findViewById();
        Intent intent = getIntent();
        if(intent !=null){
            if(intent.hasExtra("question")){
                question = intent.getParcelableExtra("question");
            }
        }
        sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);

        if(question !=null && !question.getTitle().equals("")){
            // if have question object
            // Toast.makeText(CommentActivity.this, question.getQuestionid() , Toast.LENGTH_SHORT).show();
            getDataQuestion(question.getQuestionid());
            textviewquestion_title.setText(question.getTitle());
            textviewquestion_content.setText(question.getQuesttioncontent());
            textviewquestion_username.setText(question.getLikenumber() + " like");
        }

        imageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send = editTextComment.getText().toString();
                editTextComment.setText("");

                Dataservice dataservice = APIService.getService();
                Call<String> callback = dataservice.SendComment(question.getQuestionid().toString(), sharedPreferences.getString("userid",""), send);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if(result.equals("Success")){
                            Intent intent3 = new Intent(CommentActivity.this, CommentActivity.class);
                            intent3.putExtra("question", question);
                            finish();
                            startActivity(intent3);
                        }else if(result.equals("Fail")){
                            Toast.makeText(CommentActivity.this, "Fail Connection", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

    }



    private void getDataQuestion(String questionid) {
        Dataservice dataservice = APIService.getService();
        Call<List<Comment>> callback = dataservice.GetCommentList(questionid);

        callback.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                commentArrayList = (ArrayList<Comment>) response.body();

                commentAdapter = new CommentAdapter(CommentActivity.this, commentArrayList);
                //Toast.makeText(CommentActivity.this, commentApdapter.getItemCount() + "" , Toast.LENGTH_SHORT).show();

                recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
                recyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void findViewById() {
        textviewquestion_title = findViewById(R.id.textviewquestion_title);
        textviewquestion_content = findViewById(R.id.textviewquestion_content);
        textviewquestion_username = findViewById(R.id.textviewquestion_username);

        recyclerView = findViewById(R.id.recyclerviewcommentlist);

        editTextComment = findViewById(R.id.editTextComment);
        imageViewComment = findViewById(R.id.imageViewCommentEnter);
    }


}
