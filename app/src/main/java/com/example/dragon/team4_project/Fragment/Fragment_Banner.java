package com.example.dragon.team4_project.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dragon.team4_project.Adapter.BannerAdapter;
import com.example.dragon.team4_project.Model.Question;
import com.example.dragon.team4_project.R;
import com.example.dragon.team4_project.Service.APIService;
import com.example.dragon.team4_project.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Thiet lap banner de hien thi nhung cau hoi moi nhat
public class Fragment_Banner extends Fragment {
    View view;

    ViewPager viewPager;                // thanh phan pager
    CircleIndicator circleIndicator;    // thanh phan de doi giua cac pager trong banner
    BannerAdapter bannerAdapter;

    // Tao hander de tu dong thay doi banner sau 3s
    Runnable runnable;
    Handler handler;

    // bien thiet lap chi so theo tung pager
    int currentItem;

    public static final String KOREAN_FONT = "font/koreanfont1.TTF";
    public static final String KOREAN_FONT_2 = "font/koreanfont2.TTF";
    public static final String ENGLISH_FONT = "font/englishfont1.TTF";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container, false);
        findId();

        getData();
        return view;
    }

    private void findId() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }



    // lay du lieu de gan vao banner
    private void getData() {
        // Chi dinh phuong thuc goi - Tuc la chi dinh goi lenh truy cap voi url nao va nhan ket qua tra ve vao mang response
        Dataservice dataservice = APIService.getService();
        Call<List<Question>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                ArrayList<Question> banners = (ArrayList<Question>) response.body();

                // Lay du lieu tu tren web ve luu vao mang banners
                bannerAdapter = new BannerAdapter(getActivity(), banners);  // tao banneradapter, trong do se thiet lap thong tin cho tung pager cua banner
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                            currentItem = viewPager.getCurrentItem();
                            currentItem++;
                            if(currentItem>=viewPager.getAdapter().getCount()){
                                currentItem=0;
                            }
                            viewPager.setCurrentItem(currentItem);
                            handler.postDelayed(runnable, 3000);
                    }
                };
                handler.postDelayed(runnable,3000);

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }
}
