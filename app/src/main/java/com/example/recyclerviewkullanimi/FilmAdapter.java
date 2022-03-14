package com.example.recyclerviewkullanimi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.CardViewTasarimNesneleriniTutucu>
{
    private Context mContext;
    static List<Filmler> filmlerList;
    static public ArrayList<Filmler> sepeteEklenenler=new ArrayList<>();
    static public LottieAnimationView lottieAnim;


    public FilmAdapter(Context mContext, List<Filmler> filmlerList)
    {
        this.mContext = mContext;
        this.filmlerList = filmlerList;
    }

    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_film_tasarim,parent,false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position)
    {
        final Filmler film=filmlerList.get(position);

        holder.textViewFilmBaslik.setText(film.getFilm_ad());
        holder.textViewFilmFiyat.setText(film.getFilm_fiyat()+" TL");
        holder.imageViewFilmResim.setImageResource(mContext.getResources()
                .getIdentifier(film.getFilm_resim_ad(),"drawable",mContext.getPackageName()));

        holder.buttonSepeteEkle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(SepetAdapter.sepetList.isEmpty())
                {   Log.e("İf çalıştı","çalıştı.");

                    for (int i=0;i<filmlerList.size();i++)
                    {
                        filmlerList.get(i).setFilm_adet(0);
                    }
                }

                film.setFilm_adet(film.getFilm_adet()+1);

                if(SepetAdapter.sepetList.contains(film))
                {

                }
                else
                {
                    SepetAdapter.sepetList.add(film);

                }

                ListCalistir(FilmAdapter.sepeteEklenenler,"sepeteEklenenler");
                ListCalistir(SepetAdapter.sepetList,"SepetList");

                Log.e("adet:",film.getFilm_adet()+" id: "+film.getFilm_id());
                Log.e("Toplam tutar",ToplTutar.ToplamTutar+" ");

                double geciciToplam=0;
                for (int i=0;i<filmlerList.size();i++)
                {

                    geciciToplam=geciciToplam+((filmlerList.get(i).getFilm_fiyat())*(filmlerList.get(i).getFilm_adet()));
                }
                ToplTutar.ToplamTutar=geciciToplam;

                //BottomSheetFragment.textViewToplTutarMikt.setText(ToplTutar.ToplamTutar+" tili");
                //BottomSheetFragment.textViewToplTutarMikt.setText(ToplTutar.ToplamTutar+" TiLi");

                /*View myView = LayoutInflater.from(mContext)
                        .inflate(R.layout.fragment_bottom_sheet,null);
                TextView myTextView = (TextView)myView.findViewById(R.id.textViewToplTutarMikt);
                myTextView.setText(ToplTutar.ToplamTutar+" TL");*/


               /* FilmAdapter filmAdapter = (FilmAdapter) mContext.
                View headerView = navigationView.GetHeaderView(0);
                TextView navUsername = (TextView)headerView.FindViewById(Resource.Id.edituser);
                navUsername.Text=("Your Text Here");*/


                Log.e("id: ",film.getFilm_id()+" adet: "+film.getFilm_adet());
                Log.e("Toplam Tutar: ",ToplTutar.ToplamTutar+ " TL");

                lottieAnim.setVisibility(View.VISIBLE);
                lottieAnim.playAnimation();

                Looper l=Looper.getMainLooper();
                Handler h=new Handler(l);
                h.postDelayed(new Runnable()
                              {
                                  @Override public void run(){
                                      lottieAnim.setVisibility(View.INVISIBLE);
                                      lottieAnim.pauseAnimation();
                                  }
                              }
                        ,2000);
            }
        });

        holder.imageViewNoktaResim.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mContext,Card_Detay.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();


                /*PopupMenu popup=new PopupMenu(mContext,holder.imageViewNoktaResim);
                popup.getMenuInflater().inflate(R.menu.card_menu,popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.action_duzenle:
                                Toast.makeText(mContext,film.getFilm_ad()+" : düzenle çalıştı.",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.action_sil:
                                Toast.makeText(mContext,film.getFilm_ad()+" : sil çalıştı.",Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }

                    }
                });*/
            }
        });

        holder.imageViewFilmResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               /* Log.e("position-image",position+"");
                Log.e("getAdap-image",holder.getAdapterPosition()+"");
                Log.e("getLay-image",holder.getLayoutPosition()+"");
                Log.e("getOld-image",holder.getOldPosition()+"");

                kacinci= position;*/

                Intent intent=new Intent(mContext,Card_Detay.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();





            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mContext,Card_Detay.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();

            }
        });

        holder.textViewFilmBaslik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mContext,Card_Detay.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();

            }
        });

        holder.textViewFilmFiyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mContext,Card_Detay.class);
                intent.putExtra("nesne",film);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return filmlerList.size();
    }

    public static class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder
    {
        public ImageView imageViewFilmResim;
        public TextView textViewFilmBaslik;
        public TextView textViewFilmFiyat;
        public Button buttonSepeteEkle;
        public ImageView imageViewNoktaResim;
        public CardView cardView;
        public TextView txFiyatYaz;


        public CardViewTasarimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);

            imageViewFilmResim=itemView.findViewById(R.id.imageViewFilmResim);
            textViewFilmBaslik=itemView.findViewById(R.id.textViewFilmBaslik);
            textViewFilmFiyat=itemView.findViewById(R.id.textViewFilmFiyat);
            buttonSepeteEkle=itemView.findViewById(R.id.buttonSepeteEkle);
            imageViewNoktaResim=itemView.findViewById(R.id.imageViewNoktaResim);
            cardView=itemView.findViewById(R.id.cardView);

            txFiyatYaz=itemView.findViewById(R.id.textViewToplTutarMikt);

        }


    }

    public void yaziAta(TextView txview)
    {
        txview.findViewById(R.id.textViewToplTutarMikt);
        txview.setText(ToplTutar.ToplamTutar+" TL");
    }

    public void NesneYazdirLog(Filmler film,String yerNere)
    {
        Log.e("NERESİ : ",yerNere);
        Log.e("film id",String.valueOf(film.getFilm_id()));
        Log.e("film ad",film.getFilm_ad());
        Log.e("film resim ad",film.getFilm_resim_ad());
        Log.e("film fiyat",String.valueOf(film.getFilm_fiyat()));
        Log.e("film yonetmen",film.getFilm_yonetmen());
        Log.e("film yil",film.getFilm_yapim_yili());
        Log.e("film adet",String.valueOf(film.getFilm_adet()));
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

    public static void NesneTemizleme(Filmler f)
    {
        f.setFilm_adet(0);
    }




}
