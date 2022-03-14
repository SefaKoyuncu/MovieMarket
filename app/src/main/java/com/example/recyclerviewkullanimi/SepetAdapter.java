package com.example.recyclerviewkullanimi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.CardViewTasarimNesneleriniTutucu>
{
    private Context mContext;
    //static ArrayList<Filmler> sepetList= (ArrayList<Filmler>) ListTut.sepeteEkleLer.clone();
    static ArrayList<Filmler> sepetList= new ArrayList<>();
    //static double toplMikt=0;
    private double urunToplFiyat;
    SepetAdapter sepetAdapter;

    public SepetAdapter() {
    }

    public SepetAdapter(Context mContext)
    {
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tasarim_bottomsheet,parent,false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position)
    {
        final Filmler film=sepetList.get(position);

        //toplMikt=toplMikt+(film.getFilm_adet()*film.getFilm_fiyat());

        holder.textViewSepetAd.setText(film.getFilm_ad());
        holder.textViewSepetFiyat.setText((film.getFilm_fiyat()+" TL"));
        holder.imageViewSepetResim.setImageResource(mContext.getResources()
                .getIdentifier(film.getFilm_resim_ad(),"drawable",mContext.getPackageName()));

        holder.imageButtonSepetArti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(SepetAdapter.sepetList.isEmpty())
                {   Log.e("İf çalıştı","çalıştı.");

                    for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                    {
                        FilmAdapter.filmlerList.get(i).setFilm_adet(0);
                    }
                }


                film.setFilm_adet(film.getFilm_adet()+1);
                Log.e("nesne id : ",film.getFilm_id()+"");
                Log.e("nesne adet : ",film.getFilm_adet()+"");

                holder.textViewSepetAdet.setText(film.getFilm_adet()+"");
               // holder.textViewSepetFiyat.setText((film.getFilm_fiyat()+" TL"));


                //Ürünün kendi kısmındaki toplam fiyat.

                Log.e("SptAdapter içi adet",film.getFilm_adet()+" id:"+film.getFilm_id());
                //ToplTutar.ToplamTutar=(((film.getFilm_adet())*(film.getFilm_fiyat())));

                //ToplTutar.ToplamTutar=((ToplTutar.ToplamTutar)+((film.getFilm_adet())*(film.getFilm_fiyat())));

                double geciciToplam=0;
                for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                {
                    geciciToplam=geciciToplam+((FilmAdapter.filmlerList.get(i).getFilm_fiyat())*(FilmAdapter.filmlerList.get(i).getFilm_adet()));
                }

                ToplTutar.ToplamTutar=geciciToplam;

                Log.e("Toplam Tutar",ToplTutar.ToplamTutar+"");
                BottomSheetFragment.textViewToplTutarMikt.setText(new DecimalFormat("##.## tL").format(ToplTutar.ToplamTutar));

                /*BottomSheetFragment bottomSheetFragment=new BottomSheetFragment();
                bottomSheetFragment.setToplamTutarMikt(bottomSheetFragment.getToplamTutarMikt()
                        +((film.getFilm_adet())*(film.getFilm_fiyat())));
                //En yukarıda olan sepetin tüm toplam fiyatı.*/

            }
        });

        holder.imageButtonSepetEksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(SepetAdapter.sepetList.isEmpty())
                {   Log.e("İf çalıştı","çalıştı.");

                    for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                    {
                        FilmAdapter.filmlerList.get(i).setFilm_adet(0);
                    }
                }

                film.setFilm_adet(film.getFilm_adet()-1);

                Log.e("nesne id : ",film.getFilm_id()+"");
                Log.e("nesne adet : ",film.getFilm_adet()+"");
                ListCalistir(sepetList,"sepetList");
                //ListCalistir(FilmAdapter.filmlerList,"filmlerList");

                if (film.getFilm_adet()==0)
                {
                    //BURDA KALDIM getSupportFragmentManager'ı düzeltiyordum.
                   /* Activity activity = (Activity) v.getContext();
                    FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.commit();
                    */


                    SepetAdapter.sepetList.remove(film);

                    BottomSheetFragment.rv_bottom.setHasFixedSize(true);
                    BottomSheetFragment.rv_bottom.setLayoutManager(new StaggeredGridLayoutManager(1
                            ,StaggeredGridLayoutManager.VERTICAL));
                    sepetAdapter=new SepetAdapter(mContext);
                    BottomSheetFragment.rv_bottom.setAdapter(sepetAdapter);

                    if(SepetAdapter.sepetList.isEmpty())
                    {
                        BottomSheetFragment.buttonSepetBosUrunEkle.setVisibility(View.GONE);
                        BottomSheetFragment.textViewSepetBos.setVisibility(View.VISIBLE);
                        BottomSheetFragment.constraintLayoutBottom.setMaxHeight(800);
                        BottomSheetFragment.constraintLayoutUst.setMaxHeight(130);
                        BottomSheetFragment.lottieEmpty.setVisibility(View.VISIBLE);

                        BottomSheetFragment.textViewToplTutarYazisi.setVisibility(View.GONE);
                        BottomSheetFragment.textViewToplTutarMikt.setVisibility(View.GONE);
                        BottomSheetFragment.buttonSatinAl.setVisibility(View.GONE);
                        BottomSheetFragment.animationViewCOP.setVisibility(View.INVISIBLE);

                        ToplTutar.ToplamTutar=0;
                        Log.e("Çalıştı","Buraya kadar çalıştı. görsel nesnelerde setvisiblity ");

                        BottomSheetFragment.buttonSepetBosUrunEkle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {

                                //((Activity)mContext).finish();




                            }
                        });


                    }

                    double geciciToplam=0;

                    for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                    {
                        geciciToplam=geciciToplam+((FilmAdapter.filmlerList.get(i).getFilm_fiyat())*(FilmAdapter.filmlerList.get(i).getFilm_adet()));
                    }

                    ToplTutar.ToplamTutar=geciciToplam;

                    Log.e("Toplam Tutar",ToplTutar.ToplamTutar+"");
                    BottomSheetFragment.textViewToplTutarMikt.setText(new DecimalFormat("##.## tL").format(ToplTutar.ToplamTutar));



                }
                else//eksi olamaz sıfırdan geçmesi lazım eksi için yukarda sıfıra takılcak, burası >0 1,2,... gibi sayılar.
                {
                    holder.textViewSepetAdet.setText(film.getFilm_adet()+"");
                    //holder.textViewSepetFiyat.setText(((film.getFilm_adet())*(film.getFilm_fiyat()))+ " TL");
                    //Ürünün kendi kısmındaki toplam fiyat.

                    Log.e("SptAdapter içi adet",film.getFilm_adet()+" id:"+film.getFilm_id());

                    double geciciToplam=0;
                    for (int i=0;i<FilmAdapter.filmlerList.size();i++)
                    {
                        geciciToplam=geciciToplam+((FilmAdapter.filmlerList.get(i).getFilm_fiyat())*(FilmAdapter.filmlerList.get(i).getFilm_adet()));
                    }

                    ToplTutar.ToplamTutar=geciciToplam;

                    Log.e("Toplam Tutar",ToplTutar.ToplamTutar+"");
                    BottomSheetFragment.textViewToplTutarMikt.setText(new DecimalFormat("##.## tL").format(ToplTutar.ToplamTutar));
                }


            }
        });

        holder.textViewSepetAdet.setText(film.getFilm_adet()+"");



    }

    @Override
    public int getItemCount()
    {
        return sepetList.size();
    }

    public static class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder
    {
        public ImageView imageViewSepetResim;
        public static TextView textViewSepetFiyat;
        public TextView textViewSepetAd,textViewSepetAdet;
        public ImageButton imageButtonSepetArti, imageButtonSepetEksi;


        public CardViewTasarimNesneleriniTutucu(@NonNull View itemView)
        {
            super(itemView);

            imageViewSepetResim= itemView.findViewById(R.id.imageViewSepetResim);
            textViewSepetAd = itemView.findViewById(R.id.textViewSepetAd);
            textViewSepetAdet = itemView.findViewById(R.id.textViewSepetAdet);
            textViewSepetFiyat = itemView.findViewById(R.id.textViewSepetFiyat);
            imageButtonSepetArti= itemView.findViewById(R.id.imageButtonSepetArti);
            imageButtonSepetEksi= itemView.findViewById(R.id.imageButtonSepetEksi);
        }



        public static void TextAtama(double d)
        {
            textViewSepetFiyat.setText(d+" TL");
        }


    }
    public static void ListCalistir(List<Filmler> liste,String listeAdi)
    {
        for (int i=0;i<liste.size();i++)
        {
            Log.e("listeAdi",listeAdi);
            Log.e("listfilm id",String.valueOf(liste.get(i).getFilm_id()));
            Log.e("listfilm ad",String.valueOf(liste.get(i).getFilm_ad()));
            Log.e("listfilm resim ad",String.valueOf(liste.get(i).getFilm_resim_ad()));
            Log.e("listfilm fiyat",String.valueOf(liste.get(i).getFilm_fiyat()));
            Log.e("listfilm yonetmen",String.valueOf(liste.get(i).getFilm_yonetmen()));
            Log.e("listfilm yil",String.valueOf(liste.get(i).getFilm_yapim_yili()));
            Log.e("listfilm adet",String.valueOf(liste.get(i).getFilm_adet()));

        }
    }



}

