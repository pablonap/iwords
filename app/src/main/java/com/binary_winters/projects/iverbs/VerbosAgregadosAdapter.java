package com.binary_winters.projects.iverbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.activities.AddVerbActivity;
import com.binary_winters.projects.iverbs.activities.VerbosAgregadosActivity;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.binary_winters.projects.iverbs.R.id.textViewNoHayVerbosLabel;

/**
 * Created by chipo on 24/02/18.
 */

public class VerbosAgregadosAdapter extends RecyclerView.Adapter<VerbosAgregadosAdapter.ViewHolder> {
    private Activity activity;
    private Integer recursoItem;
    private List<VerboGenericoBean> listaDeVerboGenericoBean;
    private VerbosAgregadosAdapter.OnItemClickListener onItemClickListener;
    private VerbosAgregadosAdapter.OnItemClickListener onItemClickListenerParaVerboVoice;
    private Context context;
    private Realm realm;

    private String verboEnIngCol1;
    private String verboEnIngCol2;
    private String verboEnIngCol3;

    private Toast toastTextoCompleto;
    private String textoCompleto;

    public VerbosAgregadosAdapter(List<VerboGenericoBean> listaDeVerboGenericoBean, Integer recursoItem,
                                  Activity activity, VerbosAgregadosAdapter.OnItemClickListener onItemClickListener,
                                  VerbosAgregadosAdapter.OnItemClickListener onItemClickListenerParaVerboVoice) {
        this.listaDeVerboGenericoBean = listaDeVerboGenericoBean;
        this.recursoItem = recursoItem;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
        this.onItemClickListenerParaVerboVoice = onItemClickListenerParaVerboVoice;

        realm = Realm.getDefaultInstance();

        toastTextoCompleto = Toast.makeText(activity, "", Toast.LENGTH_SHORT);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(recursoItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(listaDeVerboGenericoBean.get(position), position, onItemClickListener, onItemClickListenerParaVerboVoice);
    }

    @Override
    public int getItemCount() {
        return listaDeVerboGenericoBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        private TextView textViewVerboEnEsp;
        private TextView textViewVerboEnIngCol1;
        private TextView textViewVerboEnIngCol2;
        private TextView textViewVerboEnIngCol3;
        private ImageView imageViewStarVerboAg;
        private ImageView imageViewVerboVoice;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnCreateContextMenuListener(this);

            textViewVerboEnEsp = (TextView) itemView.findViewById(R.id.textViewVerboEnEsp);
            textViewVerboEnIngCol1 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol1);
            textViewVerboEnIngCol2 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol2);
            textViewVerboEnIngCol3 = (TextView) itemView.findViewById(R.id.textViewVerboEnIngCol3);

            imageViewStarVerboAg = (ImageView) itemView.findViewById(R.id.imageViewStarVerboAg);
            imageViewVerboVoice = (ImageView) itemView.findViewById(R.id.imageViewVerboVoice);
        }


    public void bind(final VerboGenericoBean verboGenericoBean, int position, final OnItemClickListener onItemClickListener,
                     final OnItemClickListener onItemClickListenerParaVerboVoice) {
        if((position % 2) == 0){
            itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.grisParaFilas));
        } else{
            itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.blancoParaFilas));
        }

        String cadenaDeVerboEnIng = verboGenericoBean.getCadenaDeVerbos();

        dividirCadenaDeVerboEnIng(cadenaDeVerboEnIng);

        textViewVerboEnEsp.setText(verboGenericoBean.getVerboEnEsp().getNombreDeVerbo());
        textViewVerboEnIngCol1.setText(verboEnIngCol1);
        textViewVerboEnIngCol2.setText(verboEnIngCol2);
        textViewVerboEnIngCol3.setText(verboEnIngCol3);

        if(verboGenericoBean.getVerboEnEsp().getMarkedWithStar()){
            imageViewStarVerboAg.setImageResource(R.mipmap.ic_full_star);
        } else{
            imageViewStarVerboAg.setImageResource(R.mipmap.ic_void_star);
        }

        imageViewStarVerboAg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.click(getAdapterPosition());
            }
        });

        imageViewVerboVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListenerParaVerboVoice.click(getAdapterPosition());
            }
        });
    }

        public void borrarAudio(String nombreAudioCompleto){
            if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
                File fileToDelete = new File(nombreAudioCompleto);
                if(fileToDelete != null){
                    fileToDelete.delete();
                }
            }
        }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.borrar_verbo_item:
                borrarAudio(listaDeVerboGenericoBean.get(getAdapterPosition()).getNombreArchivoAudio());

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        VerboEnEsp verboEnEsp = listaDeVerboGenericoBean.get(getAdapterPosition()).getVerboEnEsp();
                        RealmResults<VerboEnEsp> result = realm.where(VerboEnEsp.class).equalTo("id", verboEnEsp.getId()).findAll();
                        result.deleteAllFromRealm();
                        listaDeVerboGenericoBean.remove(getAdapterPosition());
                        ((VerbosAgregadosActivity) activity).getVerbosAgregadosFromRealm().deleteFromRealm(getAdapterPosition());
                    }
                });

                notifyDataSetChanged();

                if(listaDeVerboGenericoBean != null && listaDeVerboGenericoBean.isEmpty()){
                    TextView text = (TextView) activity.findViewById(textViewNoHayVerbosLabel);
                    text.setVisibility(View.VISIBLE);

                    VerbosAgregadosActivity miActivity = (VerbosAgregadosActivity) activity;
                    MenuItem menuDelete = miActivity.getMenuDelete();
                    menuDelete.setVisible(Boolean.FALSE);
                }
                return true;
            case R.id.editar_verbo_item:
                VerboGenericoBean verboGenericoBean = listaDeVerboGenericoBean.get(getAdapterPosition());
                editarVerboDeUsuario(verboGenericoBean);
                return true;
            case R.id.ver_texto_verbo_item:
                textoCompleto = listaDeVerboGenericoBean.get(getAdapterPosition()).getVerboEnEsp().getNombreDeVerbo() +
                        "\n" + "-" + "\n" + listaDeVerboGenericoBean.get(getAdapterPosition()).getCadenaDeVerbos();
                toastTextoCompleto.setText(textoCompleto);
                toastTextoCompleto.show();
                return true;
            default:
                return false;
        }
    }

    private void editarVerboDeUsuario(final VerboGenericoBean verboGenericoBeanParaEditar) {
        Intent intent = new Intent(context, AddVerbActivity.class);
        Bundle bundle = new Bundle();

        int id = verboGenericoBeanParaEditar.getId();

        VerboEnEsp verboEnEsp = new VerboEnEsp(verboGenericoBeanParaEditar.getVerboEnEsp().getNombreDeVerbo(),
                verboGenericoBeanParaEditar.getVerboEnEsp().getVERBO_DESDE_APP(),
                verboGenericoBeanParaEditar.getVerboEnEsp().getMarkedWithStar());
        verboEnEsp.setId(verboGenericoBeanParaEditar.getVerboEnEsp().getId());

        VerboGenericoBean verboGenericoBeanSerialize = new VerboGenericoBean(verboEnEsp, verboGenericoBeanParaEditar.getCadenaDeVerbos());
        verboGenericoBeanSerialize.setNombreArchivoAudio(verboGenericoBeanParaEditar.getNombreArchivoAudio());
        verboGenericoBeanSerialize.setArchivoDeAudioEnString(verboGenericoBeanParaEditar.getArchivoDeAudioEnString());

        verboGenericoBeanSerialize.setId(id);

        bundle.putSerializable("verboGenericoBeanParaEditarSerializado",(Serializable) verboGenericoBeanSerialize);
        intent.putExtra("verboGenericoBeanParaEditar",bundle);

        context.startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = activity.getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_list_verbs, contextMenu);

        for(int i=0; i < contextMenu.size(); i++){
            // Agrega a cada elemento del menú un listener. Usando el ListView desde el activity esto se
            // manejaba desde el método onContextItemSelected.
            contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }
    }
}
    private void dividirCadenaDeVerboEnIng(String cadenaDeVerboEnIng){
        String[] divisiones = cadenaDeVerboEnIng.split(" ");

        verboEnIngCol1 = divisiones[0];
        verboEnIngCol2 = divisiones[1];
        verboEnIngCol3 = divisiones[2];
    }

    public interface OnItemClickListener{
        void click(int position);
    }
}
