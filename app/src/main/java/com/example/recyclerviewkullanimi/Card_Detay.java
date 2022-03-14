package com.example.recyclerviewkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Card_Detay extends AppCompatActivity
{
    private ImageView imageViewDetayResim;
    private TextView textViewDetayBaslik,textViewDetayFiyat,textViewDetayYonetmen,textViewDetayYil;
    private Button buttonGeriDon;
    private LottieAnimationView lottieSepeteEkle,lottieAnimEklendi;
    private LinearLayout linearBilgiler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detay);
        Filmler gelenFilm=(Filmler)getIntent().getSerializableExtra("nesne");

        imageViewDetayResim=findViewById(R.id.imageViewDetayResim);
        textViewDetayBaslik=findViewById(R.id.textViewDetayBaslik);
        textViewDetayFiyat=findViewById(R.id.textViewDetayFiyat);
        textViewDetayYonetmen=findViewById(R.id.textViewDetayYonetmen);
        textViewDetayYil=findViewById(R.id.textViewDetayYil);
        buttonGeriDon=findViewById(R.id.buttonGeriDon);
        lottieSepeteEkle =findViewById(R.id.lottieSepeteEkle);
        lottieAnimEklendi=findViewById(R.id.lottieanimeklendi);
        linearBilgiler=findViewById(R.id.linearBilgiler);


        textViewDetayBaslik.setText(gelenFilm.getFilm_ad());
        textViewDetayFiyat.setText(gelenFilm.getFilm_fiyat()+" TL");
        imageViewDetayResim.setImageResource(getResources()
                .getIdentifier(gelenFilm.getFilm_resim_ad(),"drawable",getPackageName()));
        textViewDetayYonetmen.setText(gelenFilm.getFilm_yonetmen());
        textViewDetayYil.setText(gelenFilm.getFilm_yapim_yili());


        buttonGeriDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Card_Detay.this,MainActivity.class));
                finish();


            }
        });

        /*lottieSepeteEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.e("sepetList-size",SepetAdapter.sepetList.size()+"");
                Log.e("nesne id-",gelenFilm.getFilm_id()+" adet:"+gelenFilm.getFilm_adet());

                if(SepetAdapter.sepetList.isEmpty())
                {   Log.e("İf çalıştı","çalıştı.");

                    for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                    {
                        FilmAdapter.filmlerList.get(i).setFilm_adet(0);
                    }
                }
                gelenFilm.setFilm_adet(gelenFilm.getFilm_adet()+1);
                FilmAdapter.filmlerList.get(FilmAdapter.kacinci).setFilm_adet(FilmAdapter.filmlerList.get(FilmAdapter.kacinci).getFilm_adet()+1);

                //SepetAdapter.sepetList.add(gelenFilm);
                Log.e("kacinci",FilmAdapter.kacinci+"");
                FilmAdapter.filmlerList.get(FilmAdapter.kacinci).setFilm_adet(FilmAdapter.filmlerList.get(FilmAdapter.kacinci).getFilm_adet()+1);

                Log.e("nesne id-",gelenFilm.getFilm_id()+" adet:"+gelenFilm.getFilm_adet());

                if(SepetAdapter.sepetList.contains(gelenFilm))
                {
                }
                else
                {
                    SepetAdapter.sepetList.add(gelenFilm);
                }

                Log.e("adet:",gelenFilm.getFilm_adet()+" id: "+gelenFilm.getFilm_id());
                Log.e("Toplam tutar",ToplTutar.ToplamTutar+" ");

                double geciciToplam=0;

                for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                {
                    geciciToplam=geciciToplam+((FilmAdapter.filmlerList.get(i).getFilm_fiyat())*(FilmAdapter.filmlerList.get(i).getFilm_adet()));
                }

                ToplTutar.ToplamTutar=geciciToplam;
                //ToplTutar.ToplamTutar=ToplTutar.ToplamTutar+geciciToplam;

                Log.e("id: ",gelenFilm.getFilm_id()+" adet: "+gelenFilm.getFilm_adet());
                Log.e("Toplam Tutar: ",ToplTutar.ToplamTutar+ " TL");
                //BottomSheetFragment.textViewToplTutarMikt.setText(ToplTutar.ToplamTutar+" TeLe");


                    //gelenFilm.setFilm_adet(gelenFilm.getFilm_adet()+1);
                lottieAnimEklendi.bringToFront();

                lottieAnimEklendi.setVisibility(View.VISIBLE);
                lottieAnimEklendi.playAnimation();


                Looper l=Looper.getMainLooper();
                Handler h=new Handler(l);
                h.postDelayed(new Runnable(){
                                  @Override public void run(){

                                      lottieAnimEklendi.setVisibility(View.INVISIBLE);
                                      lottieAnimEklendi.pauseAnimation();
                                  }
                              }
                        ,2000);


            }
        });*/


    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(Card_Detay.this,MainActivity.class));
        finish();
    }
}