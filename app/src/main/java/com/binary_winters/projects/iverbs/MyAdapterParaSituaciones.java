package com.binary_winters.projects.iverbs;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chipo on 27/05/18.
 */

public class MyAdapterParaSituaciones extends BaseAdapter{
    private Context context;
    private Integer layout;
    private List<Situacion> situaciones;
    private OnItemClickListener onItemClickListener;

    public MyAdapterParaSituaciones(Context context, Integer layout, List<Situacion> situaciones,
                                    OnItemClickListener onItemClickListener) {
        this.context = context;
        this.layout = layout;
        this.situaciones = situaciones;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return situaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return situaciones.get( position );
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            TextView textViewNombreSituacion = (TextView) convertView.findViewById(R.id.textViewNombreSituacion);
            ImageView imageViewPictureSituacion = (ImageView) convertView.findViewById(R.id.imageViewPictureSituacion);

            imageViewPictureSituacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.click(position);
                }
            });

            viewHolder.setTextViewNombreSituacion(textViewNombreSituacion);
            viewHolder.setImageViewPictureSituacion(imageViewPictureSituacion);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Situacion situacionActual = situaciones.get(position);

        viewHolder.getTextViewNombreSituacion().setText(situacionActual.getNombreSituacion());
        viewHolder.getImageViewPictureSituacion().setBackground(ContextCompat.getDrawable(context, situacionActual.getImagen()));
//        Picasso.with(context).load(situacionActual.getImagen()).fit().into(viewHolder.getImageViewPictureSituacion());

        return convertView;
    }

    static class ViewHolder{
        private TextView textViewNombreSituacion;
        private ImageView imageViewPictureSituacion;

        public TextView getTextViewNombreSituacion() {
            return textViewNombreSituacion;
        }

        public void setTextViewNombreSituacion(TextView textViewNombreSituacion) {
            this.textViewNombreSituacion = textViewNombreSituacion;
        }

        public ImageView getImageViewPictureSituacion() {
            return imageViewPictureSituacion;
        }

        public void setImageViewPictureSituacion(ImageView imageViewPictureSituacion) {
            this.imageViewPictureSituacion = imageViewPictureSituacion;
        }
    }

    public interface OnItemClickListener{
        void click(int position);
    }

}
