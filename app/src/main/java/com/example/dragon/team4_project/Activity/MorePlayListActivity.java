package com.example.dragon.team4_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.dragon.team4_project.Adapter.MorePlayListAdapter;
import com.example.dragon.team4_project.Model.PlayList;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MorePlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MorePlayListAdapter morePlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_play_list);
        findId();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> callback = dataservice.GetMorePlayList();

        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> moreplaylist = (ArrayList<PlayList>) response.body();

                morePlayListAdapter = new MorePlayListAdapter(MorePlayListActivity.this, moreplaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(MorePlayListActivity.this, 2));
                recyclerView.setAdapter(morePlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("장르");
        toolbar.setTitleTextColor(getResources().getColor(R.color.title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarmoreplaylist);
        recyclerView = findViewById(R.id.recycleviewmoreplaylist);

    }
}
