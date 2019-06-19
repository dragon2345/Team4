package com.example.dragon.team4_project.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragon.team4_project.Activity.ListActivity;
import com.example.dragon.team4_project.Adapter.SearchQuestionAdapter;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Find extends Fragment {

    View view;

    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView textView;
    SearchQuestionAdapter searchQuestionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        toolbar = view.findViewById(R.id.toolbarsearchquestion);
        recyclerView = view.findViewById(R.id.recyclerviewsearchquestion);
        textView = view.findViewById(R.id.textviewsearchquesiton);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("");

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // when find by keyword
                SearchQuestion(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchQuestion(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchQuestion(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<Question>> callback = dataservice.GetSearchQuestion(query);
        callback.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                ArrayList<Question> arrayList = (ArrayList<Question>)response.body();
                if(arrayList.size()>0){
                    searchQuestionAdapter = new SearchQuestionAdapter(getActivity(), arrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchQuestionAdapter);
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else{
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }
}
