package com.binary_winters.projects.iverbs;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by chipo on 28/02/18.
 */

public class HistorialDePartidaAdapter extends BaseAdapter {
    private List<HistorialDePartidaBean> historial;
    private int layout;
    private Context context;

    public HistorialDePartidaAdapter(List<HistorialDePartidaBean> historial, int layout, Context context) {
        this.historial = historial;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return historial.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicionActual, View viewItem, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        // Cuando hace scroll hacia arriba viewItem deja de venir null y aprovechando esto se entra por el else
        if( viewItem == null ){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            viewItem = layoutInflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            TextView textViewFecha = (TextView) viewItem.findViewById(R.id.textViewFecha);
            TextView textViewCantidad = (TextView) viewItem.findViewById(R.id.textViewCantidad);
            TextView textViewAciertos = (TextView) viewItem.findViewById(R.id.textViewAciertos);
            TextView textViewErrores = (TextView) viewItem.findViewById(R.id.textViewErrores);

            viewHolder.setTextViewFecha(textViewFecha);
            viewHolder.setTextViewCantidad(textViewCantidad);
            viewHolder.setTextViewAciertos(textViewAciertos);
            viewHolder.setTextViewErrores(textViewErrores);

            viewItem.setTag( viewHolder );
        } else{
            viewHolder = (ViewHolder) viewItem.getTag();
        }

        if((posicionActual % 2) == 0){
            viewItem.setBackgroundColor(ContextCompat.getColor(context, R.color.grisParaFilas));
        } else{
            viewItem.setBackgroundColor(ContextCompat.getColor(context, R.color.blancoParaFilas));
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        String fecha = dateFormat.format(historial.get(posicionActual).getFechaDePartida());
        viewHolder.getTextViewFecha().setText(fecha);
        viewHolder.getTextViewCantidad().setText(Integer.toString(historial.get(posicionActual).getCantidadDeVerbos()));
        viewHolder.getTextViewAciertos().setText(Integer.toString(historial.get(posicionActual).getPorcentajeAciertos()) + "%");
        viewHolder.getTextViewErrores().setText(Integer.toString(historial.get(posicionActual).getPorcentajeErrores()) + "%");

        return viewItem;
    }

    static class ViewHolder {
        private TextView textViewFecha;
        private TextView textViewCantidad;
        private TextView textViewAciertos;
        private TextView textViewErrores;

        public TextView getTextViewFecha() {
            return textViewFecha;
        }

        public void setTextViewFecha(TextView textViewFecha) {
            this.textViewFecha = textViewFecha;
        }

        public TextView getTextViewCantidad() {
            return textViewCantidad;
        }

        public void setTextViewCantidad(TextView textViewCantidad) {
            this.textViewCantidad = textViewCantidad;
        }

        public TextView getTextViewAciertos() {
            return textViewAciertos;
        }

        public void setTextViewAciertos(TextView textViewAciertos) {
            this.textViewAciertos = textViewAciertos;
        }

        public TextView getTextViewErrores() {
            return textViewErrores;
        }

        public void setTextViewErrores(TextView textViewErrores) {
            this.textViewErrores = textViewErrores;
        }
    }
}
