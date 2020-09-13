package com.binary_winters.projects.iverbs;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chipo on 24/02/18.
 */

public class TodosLosVerbosAdapter extends RecyclerView.Adapter<TodosLosVerbosAdapter.ViewHolder> {
    private Activity activity;
    private Integer recursoItem;
    private List<VerboGenericoBean> listaDeVerboGenericoBean;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemClickListenerParaVerbos;

    private String verboEnIngCol1;
    private String verboEnIngCol2;
    private String verboEnIngCol3;

    private String divisionVerboEnIngCol1Escritura;
    private String divisionVerboEnIngCol2Escritura;
    private String divisionVerboEnIngCol3Escritura;

    public TodosLosVerbosAdapter(List<VerboGenericoBean> listaDeVerboGenericoBean, Integer recursoItem,
                                 Activity activity, OnItemClickListener onItemClickListener,
                                 OnItemClickListener onItemClickListenerParaVerbos) {
        this.activity = activity;
        this.recursoItem = recursoItem;
        this.listaDeVerboGenericoBean = listaDeVerboGenericoBean;
        this.onItemClickListener = onItemClickListener;
        this.onItemClickListenerParaVerbos = onItemClickListenerParaVerbos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(recursoItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(listaDeVerboGenericoBean.get(position), position, onItemClickListener, onItemClickListenerParaVerbos);
    }

    private List<View> listaViews = new ArrayList<>();

    public List<View> getListaViews() {
        return listaViews;
    }

    @Override
    public int getItemCount() {
        return listaDeVerboGenericoBean.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewVerboEnEsp;
        private TextView textViewVerboEnIngCol1;
        private TextView textViewVerboEnIngCol2;
        private TextView textViewVerboEnIngCol3;
        private ImageView imageViewStar;
        private ImageView imageViewVerboIrregVoice;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewVerboEnEsp = (TextView) itemView.findViewById(R.id.textViewVerboEnEsp);
            textViewVerboEnIngCol1 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol1);
            textViewVerboEnIngCol2 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol2);
            textViewVerboEnIngCol3 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol3);
            imageViewStar = (ImageView) itemView.findViewById(R.id.imageViewStar);
            imageViewVerboIrregVoice = (ImageView) itemView.findViewById(R.id.imageViewVerboIrregVoice);
        }


        public void bind(final VerboGenericoBean verboGenericoBean, int position, final OnItemClickListener onItemClickListener,
                         final OnItemClickListener onItemClickListenerParaVerbos) {
            if((position % 2) == 0){
                itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.grisParaFilas));
            } else{
                itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.blancoParaFilas));
            }

            listaViews.add(itemView);

            String cadenaDeVerboEnIng = verboGenericoBean.getCadenaDeVerbos();
            dividirCadenaDeVerboEnIng(cadenaDeVerboEnIng);

            textViewVerboEnEsp.setText(verboGenericoBean.getVerboEnEsp().getNombreDeVerbo());
            textViewVerboEnIngCol1.setText(divisionVerboEnIngCol1Escritura);
            textViewVerboEnIngCol2.setText(divisionVerboEnIngCol2Escritura);
            textViewVerboEnIngCol3.setText(divisionVerboEnIngCol3Escritura);

            if(verboGenericoBean.getVerboEnEsp().getMarkedWithStar()){
                imageViewStar.setImageResource(R.mipmap.ic_full_star);
            } else{
                imageViewStar.setImageResource(R.mipmap.ic_void_star);
            }

            imageViewStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.click(getAdapterPosition());
                }
            });

            imageViewVerboIrregVoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListenerParaVerbos.click(getAdapterPosition());
                }
            });

        }
    }

    private void dividirCadenaDeVerboEnIng(String cadenaDeVerboEnIng){
        String[] divisiones = cadenaDeVerboEnIng.split(" ");

        verboEnIngCol1 = divisiones[0];
        String[] divisionVerboEnIngCol1 = verboEnIngCol1.split("-");
        divisionVerboEnIngCol1Escritura = divisionVerboEnIngCol1[0];

        verboEnIngCol2 = divisiones[1];
        String[] divisionVerboEnIngCol2 = verboEnIngCol2.split("-");
        divisionVerboEnIngCol2Escritura = divisionVerboEnIngCol2[0];

        verboEnIngCol3 = divisiones[2];
        String[] divisionVerboEnIngCol3 = verboEnIngCol3.split("-");
        divisionVerboEnIngCol3Escritura = divisionVerboEnIngCol3[0];
    }

    public interface OnItemClickListener{
        void click(int position);
    }

}
