package com.example.dragon.team4_project.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.example.dragon.team4_project.Adapter.QuestionListAdapter;
import com.example.dragon.team4_project.Model.LikedQuestion;
import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.Model.SelectedQuestion;
import com.example.dragon.team4_project.MusicPlay;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    // question object
    Question question;

    // playlist object
    PlayList playList;

    // likedQuestion object
    LikedQuestion likedQuestion;

    String selectedQuestionId;
    String selectedPlayListid;

    // 1
    CoordinatorLayout coordinatorLayout;

    // 1.1
    CollapsingToolbarLayout collapsingToolbarLayout;
    // 1.1.2 + 1.1.3
    Toolbar toolbar;
    ImageView imgviewlistview;

    //1.2
    FloatingActionButton floatingActionButton;

    //1.3
    RecyclerView recyclerView;

    // Local variables arrayList with ArrayList type 'selectedQuestion'
    ArrayList<SelectedQuestion> arrayList;

    // create adapter
    QuestionListAdapter questionListAdapter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DataIntent();
        findViewById();
        init();
        sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE);

        if(question !=null && !question.getTitle().equals("")){
             // if have question object

             getDataQuestion(question.getQuestionid());
        }

        if(playList !=null && !playList.getListname().equals("")){
            // if have playlist object
            // Toast.makeText(ListActivity.this, playList.getListid() , Toast.LENGTH_LONG).show();
            getDataPlaylist(playList.getListid());
        }

        if(likedQuestion != null && !likedQuestion.getTitle().equals("")){
            getDataSelectedQuestion(likedQuestion.getQuestionid());
        }


    }

    private void getDataQuestion(String idquestion) {
        Dataservice dataservice = APIService.getService();
        Call<List<SelectedQuestion>> callback = dataservice.GetSelectedQuestion(idquestion, sharedPreferences.getString("userid",""));
        callback.enqueue(new Callback<List<SelectedQuestion>>() {
            @Override
            public void onResponse(Call<List<SelectedQuestion>> call, Response<List<SelectedQuestion>> response) {
                arrayList = (ArrayList<SelectedQuestion>) response.body();

                // set data in adapter
                questionListAdapter = new QuestionListAdapter(ListActivity.this, arrayList, "1");

                // add adapter to recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                recyclerView.setAdapter(questionListAdapter);

                // set image for coolapsingToolbarLayout and imgviewlistview
                if(arrayList.get(0).getListid().equals("1")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.classic);
                    imgviewlistview.setBackgroundResource(R.drawable.classic);
                }else if(arrayList.get(0).getListid().equals("2")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.kpop);
                    imgviewlistview.setBackgroundResource(R.drawable.kpop);
                }else if(arrayList.get(0).getListid().equals("3")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.rap);
                    imgviewlistview.setBackgroundResource(R.drawable.rap);
                }else if(arrayList.get(0).getListid().equals("4")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.electronic);
                    imgviewlistview.setBackgroundResource(R.drawable.electronic);
                }else if(arrayList.get(0).getListid().equals("5")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.hiphop);
                    imgviewlistview.setBackgroundResource(R.drawable.hiphop);
                }else if(arrayList.get(0).getListid().equals("6")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.dance);
                    imgviewlistview.setBackgroundResource(R.drawable.dance);
                }

                collapsingToolbarLayout.setTitle(arrayList.get(0).getTitle());
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SelectedQuestion>> call, Throwable t) {

            }
        });

    }

    private void getDataPlaylist(final String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<SelectedQuestion>> callback = dataservice.GetSelectedQuestionwithPlaylist(idplaylist, sharedPreferences.getString("userid",""));
        callback.enqueue(new Callback<List<SelectedQuestion>>() {
            @Override
            public void onResponse(Call<List<SelectedQuestion>> call, Response<List<SelectedQuestion>> response) {
                arrayList = (ArrayList<SelectedQuestion>) response.body();
                // Toast.makeText(ListActivity.this, arrayList.toString() , Toast.LENGTH_LONG).show();
                // set data in adapter
                questionListAdapter = new QuestionListAdapter(ListActivity.this, arrayList, "2");
                // add adapter to recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                recyclerView.setAdapter(questionListAdapter);

                // set picture
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
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SelectedQuestion>> call, Throwable t) {

            }
        });
    }

    private void getDataSelectedQuestion(String questionid) {
        Dataservice dataservice = APIService.getService();
        Call<List<SelectedQuestion>> callback = dataservice.GetSelectedQuestion(questionid, sharedPreferences.getString("userid",""));
        callback.enqueue(new Callback<List<SelectedQuestion>>() {
            @Override
            public void onResponse(Call<List<SelectedQuestion>> call, Response<List<SelectedQuestion>> response) {
                arrayList = (ArrayList<SelectedQuestion>) response.body();

                // set data in adapter
                questionListAdapter = new QuestionListAdapter(ListActivity.this, arrayList, "2");

                // add adapter to recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                recyclerView.setAdapter(questionListAdapter);

                // set image for coolapsingToolbarLayout and imgviewlistview
                if(arrayList.get(0).getListid().equals("1")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.classic);
                    imgviewlistview.setBackgroundResource(R.drawable.classic);
                }else if(arrayList.get(0).getListid().equals("2")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.kpop);
                    imgviewlistview.setBackgroundResource(R.drawable.kpop);
                }else if(arrayList.get(0).getListid().equals("3")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.rap);
                    imgviewlistview.setBackgroundResource(R.drawable.rap);
                }else if(arrayList.get(0).getListid().equals("4")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.electronic);
                    imgviewlistview.setBackgroundResource(R.drawable.electronic);
                }else if(arrayList.get(0).getListid().equals("5")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.hiphop);
                    imgviewlistview.setBackgroundResource(R.drawable.hiphop);
                }else if(arrayList.get(0).getListid().equals("6")){
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.dance);
                    imgviewlistview.setBackgroundResource(R.drawable.dance);
                }
                collapsingToolbarLayout.setTitle(arrayList.get(0).getTitle());
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SelectedQuestion>> call, Throwable t) {

            }
        });
    }

    private void init() {
        // set 'come back' button in toolbar
        setSupportActionBar(toolbar);

        // set a 'Come back Home' button in the right of toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // set color and location for title collapsingToolbarLayout when scroll up and down
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

    }

    private void findViewById() {
        // 1
        coordinatorLayout = findViewById(R.id.coordinatorlayout);

        // 1.1
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);

        // 1.1.1 + 1.1.2
        toolbar = findViewById(R.id.toolbarlist);
        imgviewlistview = findViewById(R.id.imageviewlistview);

        // 1.2
        floatingActionButton = findViewById(R.id.floatingactionbutton);

        // 1.3
        recyclerView = findViewById(R.id.recyclerviewlist);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                // from BannerAdapter
                question = (Question) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemplaylist")){
                // from Fragment_Play_List
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("likedquestion")){
                // from Fragment_Play_List
                likedQuestion = (LikedQuestion) intent.getSerializableExtra("likedquestion");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MusicPlay.class);
                intent.putExtra("questionarraylist", arrayList);
                startActivity(intent);
                // Toast.makeText(ListActivity.this, arrayList.get(0).getTitle() , Toast.LENGTH_LONG).show();
            }
        });
    }
}
