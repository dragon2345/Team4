package com.example.dragon.team4_project.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dragon.team4_project.Adapter.PlaylistAdapter;
import com.example.dragon.team4_project.Adapter.QuestionListAdapter;
import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.Model.SelectedQuestion;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    Question question;

    PlayList playList;

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imgviewlistview;

    ArrayList<SelectedQuestion> arrayList;

    QuestionListAdapter questionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DataIntent();
        findViewById();
        init();

        if(question !=null && !question.getTitle().equals("")){

             getDataQuestion(question.getQuestionid());
             // setValueInView(question.getTitle(), "http://203.234.62.86/mucon/images/list/classic.jpg");
        }

        if(playList !=null && !playList.getListname().equals("")){

            getDataPlaylist(playList.getListid());
        }

    }

    private void getDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<SelectedQuestion>> callback = dataservice.GetSelectedQuestionwithPlaylist(idplaylist);
        callback.enqueue(new Callback<List<SelectedQuestion>>() {
            @Override
            public void onResponse(Call<List<SelectedQuestion>> call, Response<List<SelectedQuestion>> response) {
                arrayList = (ArrayList<SelectedQuestion>) response.body();
                // Xet du lieu vao trong adapter
                questionListAdapter = new QuestionListAdapter(ListActivity.this, arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                recyclerView.setAdapter(questionListAdapter);
            }

            @Override
            public void onFailure(Call<List<SelectedQuestion>> call, Throwable t) {

            }
        });

        // tra lai anh nen va ten phia tren
        if(playList.getListid().equals("1")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.classic);
            imgviewlistview.setBackgroundResource(R.drawable.classic);
        }else if(playList.getListid().equals("2")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.kpop);
            imgviewlistview.setBackgroundResource(R.drawable.kpop);
        }else if(playList.getListid().equals("3")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.rap);
            imgviewlistview.setBackgroundResource(R.drawable.rap);
        }else if(playList.getListid().equals("4")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.electronic);
            imgviewlistview.setBackgroundResource(R.drawable.electronic);
        }else if(playList.getListid().equals("5")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.hiphop);
            imgviewlistview.setBackgroundResource(R.drawable.hiphop);
        }else if(playList.getListid().equals("6")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.dance);
            imgviewlistview.setBackgroundResource(R.drawable.dance);
        }

        collapsingToolbarLayout.setTitle(playList.getListname());
    }

    /* Chua thuc hien dk
    private void setValueInView(String name, String picture) {
        collapsingToolbarLayout.setTitle(name);
        try{
            URL url = new URL(picture);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(picture).into(imgviewlistview);
    }
    */

    private void getDataQuestion(String idquestion) {
        Dataservice dataservice = APIService.getService();
        Call<List<SelectedQuestion>> callback = dataservice.GetSelectedQuestion(idquestion);
        callback.enqueue(new Callback<List<SelectedQuestion>>() {
            @Override
            public void onResponse(Call<List<SelectedQuestion>> call, Response<List<SelectedQuestion>> response) {
                arrayList = (ArrayList<SelectedQuestion>) response.body();
                // Xet du lieu vao trong adapter
                questionListAdapter = new QuestionListAdapter(ListActivity.this, arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                recyclerView.setAdapter(questionListAdapter);
            }

            @Override
            public void onFailure(Call<List<SelectedQuestion>> call, Throwable t) {

            }
        });

        // tra lai anh nen va ten phia tren
        if(question.getListid().equals("1")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.classic);
            imgviewlistview.setBackgroundResource(R.drawable.classic);
        }else if(question.getListid().equals("2")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.kpop);
            imgviewlistview.setBackgroundResource(R.drawable.kpop);
        }else if(question.getListid().equals("3")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.rap);
            imgviewlistview.setBackgroundResource(R.drawable.rap);
        }else if(question.getListid().equals("4")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.electronic);
            imgviewlistview.setBackgroundResource(R.drawable.electronic);
        }else if(question.getListid().equals("5")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.hiphop);
            imgviewlistview.setBackgroundResource(R.drawable.hiphop);
        }else if(question.getListid().equals("6")){
            collapsingToolbarLayout.setBackgroundResource(R.drawable.dance);
            imgviewlistview.setBackgroundResource(R.drawable.dance);
        }

        collapsingToolbarLayout.setTitle(question.getTitle());
    }



    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void findViewById() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarlist);
        recyclerView = findViewById(R.id.recyclerviewlist);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgviewlistview = findViewById(R.id.imageviewlistview);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                question = (Question) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
        }

    }
}
