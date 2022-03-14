package com.example.recyclerviewkullanimi;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.Objects;

public class BottomSheetFragment extends BottomSheetDialogFragment
{
    static RecyclerView rv_bottom;
    private SepetAdapter adapter;
    static Button buttonSepetBosUrunEkle,buttonSatinAl;
    static TextView textViewSepetBos,textViewToplTutarYazisi,textViewSepetDetayYazisi;
    static ConstraintLayout constraintLayoutBottom, constraintLayoutUst;
    static LottieAnimationView animationViewCOP,lottieEmpty;
    static TextView textViewToplTutarMikt;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        rv_bottom=view.findViewById(R.id.rv_bottom);
        buttonSepetBosUrunEkle=view.findViewById(R.id.buttonSepetBosUrunEkle);
        textViewSepetBos=view.findViewById(R.id.textViewSepetBos);
        constraintLayoutBottom=view.findViewById(R.id.constriantLayoutBottom);
        textViewToplTutarMikt=view.findViewById(R.id.textViewToplTutarMikt);
        textViewToplTutarYazisi=view.findViewById(R.id.textViewToplTutarYazisi);
        buttonSatinAl=view.findViewById(R.id.buttonSatinAl);
        animationViewCOP=view.findViewById(R.id.lottieCOP);
        constraintLayoutUst =view.findViewById(R.id.constraintLayoutUst);
        textViewSepetDetayYazisi =view.findViewById(R.id.textViewSepetDetayYazisi);
        lottieEmpty =view.findViewById(R.id.lottieAcilis);

        TextAta(textViewToplTutarMikt);//textViewToplTutarMikt a toplam fiyatı atama.

        rv_bottom.setHasFixedSize(true);
        rv_bottom.setLayoutManager(new StaggeredGridLayoutManager(1
                ,StaggeredGridLayoutManager.VERTICAL));

        adapter=new SepetAdapter(view.getContext());
        rv_bottom.setAdapter(adapter);

        if (SepetAdapter.sepetList.isEmpty())//Sepet boşsa
        {
            buttonSepetBosUrunEkle.setVisibility(View.VISIBLE);
            textViewSepetBos.setVisibility(View.VISIBLE);
            constraintLayoutBottom.setMaxHeight(800);
            constraintLayoutUst.setMaxHeight(130);
            lottieEmpty.setVisibility(View.VISIBLE);

            textViewToplTutarYazisi.setVisibility(View.GONE);
            textViewToplTutarMikt.setVisibility(View.GONE);
            buttonSatinAl.setVisibility(View.GONE);
            animationViewCOP.setVisibility(View.INVISIBLE);

            buttonSepetBosUrunEkle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        else//Sepet doluysa
        {
            lottieEmpty.setVisibility(View.INVISIBLE);

           /* if (SepetAdapter.toplMikt!=0)
            {

                textViewToplTutarMikt.setText(new DecimalFormat("##.##").format(ToplTutar.ToplamTutar));
            }*/

            animationViewCOP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Snackbar snackbar = Snackbar.make(Objects.requireNonNull(getDialog()).getWindow().getDecorView()
                            ,"Sepeti silmeyi onaylıyor musunuz?",Snackbar.LENGTH_LONG)
                            .setAction("EVET", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    SepetAdapter.sepetList.clear();
                                    ToplTutar.ToplamTutar=0;

                                    Log.e("ListeSil-SPT FilmEKLE", String.valueOf(FilmAdapter.sepeteEklenenler.size()));
                                    Log.e("ListeSil-SPT LİSTE", String.valueOf(SepetAdapter.sepetList.size()));

                                    dismiss();
                                    BottomSheetFragment bottomSheetFragment=new BottomSheetFragment();
                                    bottomSheetFragment.show(getParentFragmentManager(),getTag());
                                }
                            });
                    snackbar.setActionTextColor(getResources().getColor(R.color.acikSari));
                    snackbar.setTextColor(getResources().getColor(R.color.acikKirmizi));

                    snackbar.show();

                }
            });


        }


        return view;
    }

    public  void TextAta(TextView t)
    {
     //t.setText(ToplTutar.ToplamTutar+" tl");
     t.setText(new DecimalFormat("##.## tL").format(ToplTutar.ToplamTutar));
    }
    public static void dismissCalistir()
    {
        BottomSheetFragment bottomSheetFragment=new BottomSheetFragment();


    }


}