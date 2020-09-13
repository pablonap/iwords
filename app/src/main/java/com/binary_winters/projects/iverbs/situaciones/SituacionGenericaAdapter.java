package com.binary_winters.projects.iverbs.situaciones;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chipo on 24/02/18.
 */

public class SituacionGenericaAdapter extends RecyclerView.Adapter<SituacionGenericaAdapter.ViewHolder> {
    private Activity activity;
    private Integer recursoItem;
    private List<SituacionGenerica> listaDeSituacionGenerica;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemClickListenerParaAudio;

    public SituacionGenericaAdapter(List<SituacionGenerica> listaDeSituacionGenerica, Integer recursoItem,
                                    Activity activity, OnItemClickListener onItemClickListener,
                                    OnItemClickListener onItemClickListenerParaAudio) {
        this.activity = activity;
        this.recursoItem = recursoItem;
        this.listaDeSituacionGenerica = listaDeSituacionGenerica;
        this.onItemClickListener = onItemClickListener;
        this.onItemClickListenerParaAudio = onItemClickListenerParaAudio;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(recursoItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(listaDeSituacionGenerica.get(position), position, onItemClickListener, onItemClickListenerParaAudio);
    }

    private List<View> listaViews = new ArrayList<>();

    public List<View> getListaViews() {
        return listaViews;
    }

    @Override
    public int getItemCount() {
        return listaDeSituacionGenerica.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewPalabraEnEsp;
        private TextView textViewPalabraEnIng;
        private ImageView imageViewPalabraVoice;
        private ImageView imageViewStarPalabraAg;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewPalabraEnEsp = (TextView) itemView.findViewById(R.id.textViewPalabraEnEsp);
            textViewPalabraEnIng = (TextView) itemView.findViewById(R.id.textViewPalabraEnIng);
            imageViewPalabraVoice = (ImageView) itemView.findViewById(R.id.imageViewPalabraVoice);
            imageViewStarPalabraAg = (ImageView) itemView.findViewById(R.id.imageViewStarPalabraAg);
        }


        public void bind(final SituacionGenerica situacionGenerica, int position, final OnItemClickListener onItemClickListener,
                         final OnItemClickListener onItemClickListenerParaAudio) {
            if((position % 2) == 0){
                itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.grisParaFilas));
            } else{
                itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.blancoParaFilas));
            }

            listaViews.add(itemView);

            String cadenaEnIng = situacionGenerica.getCadenaEnIngles();
            String cadenaEnIngDeEscritura = dividirCadenaEnIng(cadenaEnIng);

            textViewPalabraEnEsp.setText(situacionGenerica.getPalabraEnEsp());
            textViewPalabraEnIng.setText(cadenaEnIngDeEscritura);

            if(situacionGenerica.getMarkedWithStar()){
                imageViewStarPalabraAg.setImageResource(R.mipmap.ic_full_star);
            } else{
                imageViewStarPalabraAg.setImageResource(R.mipmap.ic_void_star);
            }

            imageViewStarPalabraAg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.click(getAdapterPosition());
                }
            });

            imageViewPalabraVoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListenerParaAudio.click(getAdapterPosition());
                }
            });

        }
    }

    private String dividirCadenaEnIng(String cadenaEnIng){
        String[] divisiones = cadenaEnIng.split("-");

        return divisiones[0];

    }

    public interface OnItemClickListener{
        void click(int position);
    }

}
