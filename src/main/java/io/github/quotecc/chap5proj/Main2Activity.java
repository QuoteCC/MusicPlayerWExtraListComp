package io.github.quotecc.chap5proj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    Button b;
    ImageView i;
    String name;
    String pic;
    MediaPlayer mpMadeon,mpShaggy;
    int timestmp;
    int playing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = getIntent().getExtras().getString("name");
        pic = getIntent().getExtras().getString("pic");
        i = (ImageView) findViewById(R.id.pic);
        b = (Button) findViewById(R.id.but);

        //Log.d("DEBUGp", pic);
        //gLog.d("DEBUGn", name);
        b.setText(name);
        i.setImageResource(getResources().getIdentifier(pic, "drawable", getPackageName()));

        if (!name.equals("Sweet Tunes")){

            //means is standard pic w/ url
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getExtras().getString("url"))));
                }
            });
        }
        else{  //may as well not create the dual listeners and the media players if we dont have to
            //These are called madeon and shaggy, but really shaggy is next and madeon is play/pause current song
            b.setText("Next");
            i.setOnClickListener(bMadeon);
            b.setOnClickListener(bShaggy);
            timestmp = 0;
            playing = 0; //0 is madeon, 1 is shaggy, could possibly add more, but tbh is a pretty garb framework
            /*

            The way this really should be done is with one media player, continually resetting the source then preparing it,
            this prevents a number of the stupid edge case errors that I've had to solve here

             */
            i.setImageResource(R.drawable.madeonimg);
            mpMadeon = new MediaPlayer();
            mpMadeon = MediaPlayer.create(this,R.raw.madeonpaynomind);
            mpMadeon.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playing = 1;
                    timestmp = 0;
                    try {
                        mpMadeon.prepare();
                    }
                    catch (Exception e){

                    }
                    mpShaggy.seekTo(timestmp);
                    mpShaggy.start();
                }
            });
            mpShaggy = new MediaPlayer();
            mpShaggy = MediaPlayer.create(this,R.raw.wasntmetrop);
            mpShaggy.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playing = 0;
                    timestmp = 0;
                    try {
                        mpShaggy.prepare();
                    }
                    catch (Exception e){

                    }
                    mpMadeon.seekTo(timestmp);
                    mpMadeon.start();
                }
            });


        }

    }
    Button.OnClickListener bMadeon = new View.OnClickListener() {
        //Reminder that this starts/pauses current song
        @Override
        public void onClick(View v) {
            switch (playing){
                case 0:

                    if(mpMadeon.isPlaying()){
                        mpMadeon.pause();
                        //timestmp = mpMadeon.getCurrentPosition();
                    }
                    else{
                        //mpMadeon.seekTo(timestmp);
                        mpMadeon.start();
                    }

                    break;
                case 1:
                    if(mpShaggy.isPlaying()){
                        mpShaggy.pause();
                        //timestmp = mpShaggy.getCurrentPosition();
                    }
                    else{
                        //mpShaggy.seekTo(timestmp);
                        mpShaggy.start();
                    }

                    break;

            }
        }
    };

    Button.OnClickListener bShaggy = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (playing){
                case 0:
                    if (mpMadeon.isPlaying()){
                        mpMadeon.pause();
                    }
                    i.setImageResource(R.drawable.lebouf);
                    playing = 1;

                    mpShaggy.seekTo(0);
                    mpShaggy.start();
                    break;
                case 1:
                    if(mpShaggy.isPlaying()){
                        mpShaggy.pause();

                    }
                    playing = 0;
                    i.setImageResource(R.drawable.madeonimg);
                    mpMadeon.seekTo(0);
                    mpMadeon.start();
                    break;
            }
        }
    };
}
