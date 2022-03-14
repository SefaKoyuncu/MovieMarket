package com.example.recyclerviewkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class AcilisEkrani extends AppCompatActivity {
    private LottieAnimationView lottieAcilis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis_ekrani);



        lottieAcilis=findViewById(R.id.lottieAcilis);
        lottieAcilis.setVisibility(View.VISIBLE);

        Looper l=Looper.getMainLooper();
        Handler h=new Handler(l);
        h.postDelayed(new Runnable()
                      {
                          @Override public void run(){
                              Intent intent=new Intent(AcilisEkrani.this,MainActivity.class);
                              startActivity(intent);
                              finish();
                          }
                      }
                ,2000);

        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_FULLSCREEN);




    }
}