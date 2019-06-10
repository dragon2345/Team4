package com.example.dragon.team4_project.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dragon.team4_project.Adapter.MainViewPagerAdapter;
import com.example.dragon.team4_project.AddQuestion;
import com.example.dragon.team4_project.FindPassword;
import com.example.dragon.team4_project.Fragment.Fragment_Find;
import com.example.dragon.team4_project.Fragment.Fragment_Home;
import com.example.dragon.team4_project.Login;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Register;

public class Main extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        init();

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Home(), "Home");
        mainViewPagerAdapter.addFragment(new Fragment_Find(), "Find");
        viewPager.setAdapter((mainViewPagerAdapter));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void findViewById() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
