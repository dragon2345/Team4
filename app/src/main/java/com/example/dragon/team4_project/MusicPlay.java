package com.example.dragon.team4_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dragon.team4_project.Activity.CommentActivity;
import com.example.dragon.team4_project.Model.SelectedQuestion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicPlay extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTitle , txtTimeNow, txtTimetotal;
    SeekBar skSong;
    ImageButton btnPrev, btnPlay, btnReplay, btnNext;
    ImageButton btnComment;
    ImageView imageView;

    public static final String KOREAN_FONT_2 = "font/koreanfont2.TTF";

    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplay);
        findViewById();
        setTypeface();

        AddSong();

        LoadMusic();

        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageView.clearAnimation();
                    btnPlay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    imageView.startAnimation(animation);
                    btnPlay.setImageResource(R.drawable.pause);
                }
                SetTimeTotal();
                SetTimeNow();
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release(); // giai phong bo nho
                btnPlay.setImageResource(R.drawable.play);
                LoadMusic();
                imageView.clearAnimation();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position >arraySong.size() - 1 ){
                    position = 0;
                    // stop when finish song
                    // mediaPlayer.stop();
                    // mediaPlayer.release();
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                btnPlay.setImageResource(R.drawable.pause);
                LoadMusic();


                // xac nhan xem da nhan het du lieu nhac chua
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        SetTimeTotal();
                        SetTimeNow();
                    }
                });
                imageView.startAnimation(animation);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0 ){
                    position = arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                btnPlay.setImageResource(R.drawable.pause);
                LoadMusic();

                // xac nhan xem da nhan het du lieu nhac chua
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        SetTimeTotal();
                        SetTimeNow();
                    }
                });
                imageView.startAnimation(animation);
            }
        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // keo thi khong thay doi, sau khi buong chuot thi thoi gian bai hat moi thay doi
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.clear();
        Intent intent = getIntent();
        if(intent !=null){
            if(intent.hasExtra("question")){
                final SelectedQuestion question = intent.getParcelableExtra("question");
                arraySong.add(new Song(question.getTitle() , question.getLink()));
                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MusicPlay.this, CommentActivity.class);
                        intent1.putExtra("question", question);
                        startActivity(intent1);
                    }
                });
            }
            if(intent.hasExtra("questionarraylist")){
                final ArrayList<SelectedQuestion> arrayList = intent.getParcelableArrayListExtra("questionarraylist");
                //SelectedQuestion question = intent.getParcelableExtra("question");
                //arraySong.add(new Song(question.getTitle() , question.getLink()));

                for (int i=0; i<arrayList.size(); i++){
                    arraySong.add(new Song(arrayList.get(i).getTitle() , arrayList.get(i).getLink()));
                }

                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MusicPlay.this, CommentActivity.class);
                        intent1.putExtra("question", arrayList.get(position));
                        startActivity(intent1);
                    }
                });
            }
        }
    }

    private void findViewById(){
        txtTitle = findViewById(R.id.textViewTitle);
        txtTimeNow = findViewById(R.id.textViewTimeNow);
        txtTimetotal = findViewById(R.id.textViewTimeTotal);

        skSong = findViewById(R.id.seekBar);

        btnPrev = findViewById(R.id.buttonPrev);
        btnPlay = findViewById(R.id.buttonPlay);
        btnReplay = findViewById(R.id.buttonReplay);
        btnNext = findViewById(R.id.buttonNext);

        btnComment = findViewById(R.id.buttonComment);

        imageView = findViewById(R.id.imageView);

        toolbar = findViewById(R.id.toolbarmusicplay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                finish();
            }
        });

    }

    private void setTypeface() {
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), KOREAN_FONT_2);
        //txtTitle.setTypeface(typeface2);
    }

    private void LoadMusic(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            txtTitle.setText(arraySong.get(position).getTitle());
            mediaPlayer.setDataSource(arraySong.get(position).getFile());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetTimeTotal(){
        SimpleDateFormat setTimeTotal = new SimpleDateFormat("mm:ss");
        txtTimetotal.setText(setTimeTotal.format(mediaPlayer.getDuration()));

        // gan max cho seekbar
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void SetTimeNow(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat setTimeNow = new SimpleDateFormat("mm:ss");
                txtTimeNow.setText(setTimeNow.format(mediaPlayer.getCurrentPosition()));

                if(mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()){
                    imageView.clearAnimation();
                    btnPlay.setImageResource(R.drawable.play);
                }

                // update cho seekbar
                skSong.setProgress(mediaPlayer.getCurrentPosition());

                // kiểm tra thời gian bài hát -> nếu kết thúc thì next bài mới
                /*
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position >arraySong.size() - 1 ){
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        btnPlay.setImageResource(R.drawable.pause);
                        LoadMusic();

                        // xac nhan xem da nhan het du lieu nhac chua
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                SetTimeTotal();
                                SetTimeNow();
                            }
                        });
                    }
                });
                */

                handler.postDelayed(this,500);
            }
        },100);
    }
}
