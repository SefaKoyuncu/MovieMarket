package com.example.recyclerviewkullanimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmAdapter adapter;
    private FloatingActionButton faButtonSepet;
    public LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        faButtonSepet=findViewById(R.id.faButtonSepet);
        lottieAnimationView=findViewById(R.id.lottieanim);


        Filmler f1=new Filmler(1,"Django","django",12.99,"Quentin Tarantino","2012",0);
        Filmler f2=new Filmler(2,"Inception","inception",10.99,"Christopher Nolan","2010",0);
        Filmler f3=new Filmler(3,"Interstellar","interstellar",29.99,"Christopher Nolan","2010",0);
        Filmler f4=new Filmler(4,"The Hateful Eight","thehatefuleight",5.99,"Quentin Tarantino","2015",0);
        Filmler f5=new Filmler(5,"The Pianist","thepianist",11.99,"Roman Polanski","2002",0);
        Filmler f6=new Filmler(6,"Anadoluda","birzamanlaranadoluda"
                ,12.99,"Nuri Bilge Ceylan","2010",0);

        filmlerArrayList=new ArrayList<>();

        filmlerArrayList.add(f1);
        filmlerArrayList.add(f2);
        filmlerArrayList.add(f3);
        filmlerArrayList.add(f4);
        filmlerArrayList.add(f5);
        filmlerArrayList.add(f6);

        adapter=new FilmAdapter(this,filmlerArrayList);
        rv.setAdapter(adapter);

        faButtonSepet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment=new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
            }
        });
        FilmAdapter.lottieAnim=lottieAnimationView;




        }


    }







