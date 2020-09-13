package com.binary_winters.projects.iverbs.plus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;
import com.binary_winters.projects.iverbs.plus.activities.ListaMisPalabrasActivity;
import com.binary_winters.projects.iverbs.util.AudioRecord;
import com.binary_winters.projects.iverbs.util.IverbsPatterns;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.realm.Realm;

/**
 * Created by chipo on 01/03/18.
 */

public class PalabraAgregadaAdapter extends RecyclerView.Adapter<PalabraAgregadaAdapter.ViewHolder> {
    private Activity activity;
    private Integer recursoItem;
    private List<PalabraAgregadaBean> palabrasAgregadas;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemClickListenerParaPalabraVoice;
    private Context context;
    private Realm realm;
    private String textoCompleto;

    private ImageView imageViewRec;
    private ImageView imageViewRecPush;
    private ImageView imageViewPlay;

    private TextView textViewRec;
    private TextView textViewRecPush;

    private String nombreAudioCompleto;
    private String nombreAudioCompletoAntiguo;

    private Set<String> listaDeNombreAudioCompleto;

    private SharedPreferences sharedPreferencesParaPalabra;

    private Toast toastGenerico;

    private Thread hiloActual;

    public PalabraAgregadaAdapter(List<PalabraAgregadaBean> palabrasAgregadas, Integer recursoItem,
                                 Activity activity, OnItemClickListener onItemClickListener, OnItemClickListener onItemClickListenerParaPalabraVoice) {
        this.palabrasAgregadas = palabrasAgregadas;
        this.recursoItem = recursoItem;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
        this.onItemClickListenerParaPalabraVoice = onItemClickListenerParaPalabraVoice;

        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(recursoItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = parent.getContext();

        toastGenerico = Toast.makeText(context, "", Toast.LENGTH_SHORT);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(palabrasAgregadas.get(position), position, onItemClickListener, onItemClickListenerParaPalabraVoice);
    }

    @Override
    public int getItemCount() {
        return palabrasAgregadas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        private TextView textViewpalabraEnEsp;
        private TextView textViewPalabraEnIng;
        private ImageView imageViewStarPalabraAg;
        private ImageView imageViewPalabraVoice;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnCreateContextMenuListener(this);

            textViewpalabraEnEsp = (TextView) itemView.findViewById(R.id.textViewPalabraEnEsp);
            textViewPalabraEnIng = (TextView) itemView.findViewById(R.id.textViewPalabraEnIng);
            imageViewStarPalabraAg = (ImageView) itemView.findViewById(R.id.imageViewStarPalabraAg);
            imageViewPalabraVoice = (ImageView) itemView.findViewById(R.id.imageViewPalabraVoice);
        }

        public void bind(final PalabraAgregadaBean palabraAgregadaBean, int position, final OnItemClickListener onItemClickListener,
                         final OnItemClickListener onItemClickListenerParaPalabraVoice){
                if((position % 2) == 0){
                    itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.grisParaFilas));
                } else{
                    itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.blancoParaFilas));
                }

            textViewpalabraEnEsp.setText(palabraAgregadaBean.getPalabraEnEsp());
            textViewPalabraEnIng.setText(palabraAgregadaBean.getCadenaEnIngles());

            if(palabraAgregadaBean.getMarkedWithStar()){
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
                    onItemClickListenerParaPalabraVoice.click(getAdapterPosition());
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.borrar_palabra_item:
                    borrarAudio(palabrasAgregadas.get(getAdapterPosition()).getNombreArchivoAudio());
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            palabrasAgregadas.remove(getAdapterPosition());
                            ((ListaMisPalabrasActivity) activity).getPalabrasAgregadasFromRealm().deleteFromRealm(getAdapterPosition());
                        }
                    });

                    notifyDataSetChanged();

                    if(palabrasAgregadas != null && palabrasAgregadas.isEmpty()){
                        TextView text = (TextView) activity.findViewById(R.id.textViewNoHayPalabrasLabel);
                        text.setVisibility(View.VISIBLE);

                        ListaMisPalabrasActivity miActivity = (ListaMisPalabrasActivity) activity;
                        MenuItem menuDelete = miActivity.getMenuDelete();
                        menuDelete.setVisible(Boolean.FALSE);
                    }
                    return true;
                case R.id.edititar_palabra_item:
                    PalabraAgregadaBean palabraAgregadaBean = palabrasAgregadas.get(getAdapterPosition());
                    editarPalabraDeUsuario(palabraAgregadaBean, getAdapterPosition());
                    return true;

                case R.id.ver_texto_palabra_item:
                    textoCompleto = palabrasAgregadas.get(getAdapterPosition()).getPalabraEnEsp() +
                            "\n" + "-" + "\n" + palabrasAgregadas.get(getAdapterPosition()).getCadenaEnIngles();
                    toastGenerico.setText(textoCompleto);
                    toastGenerico.show();
                    return true;
                default:
                    return false;
            }
        }

        private void editarPalabraDeUsuario(final PalabraAgregadaBean palabraAgregadaBeanParaEditar, final int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View viewDialogDesign = inflater.inflate(R.layout.dialog_agregar_palabra, null);

            final EditText editTextPalabraEnEspDialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnEspDialog);
            final EditText editTextPalabraEnIng_1_Dialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnIng_1_Dialog);
            final EditText editTextPalabraEnIng_2_Dialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnIng_2_Dialog);

            imageViewRec = (ImageView) viewDialogDesign.findViewById(R.id.imageViewRec);
            imageViewRecPush = (ImageView) viewDialogDesign.findViewById(R.id.imageViewRecPush);
            imageViewPlay = (ImageView) viewDialogDesign.findViewById(R.id.imageViewPlay);

            textViewRec = (TextView) viewDialogDesign.findViewById(R.id.textViewRec);
            textViewRecPush = (TextView) viewDialogDesign.findViewById(R.id.textViewRecPush);

            nombreAudioCompleto = palabraAgregadaBeanParaEditar.getNombreArchivoAudio();

            sharedPreferencesParaPalabra = context.getSharedPreferences("archivoAudioParaPalabra", Context.MODE_PRIVATE);

            listaDeNombreAudioCompleto = new HashSet<String>();

            if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
                imageViewPlay.setVisibility(View.VISIBLE);
            }

            if(imageViewRecPush.isShown()){
                imageViewPlay.setVisibility(View.INVISIBLE);
            }

            if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
                nombreAudioCompletoAntiguo = nombreAudioCompleto;
            }

            imageViewRec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewRec.setVisibility(View.INVISIBLE);
                    textViewRec.setVisibility(View.INVISIBLE);
                    imageViewRecPush.setVisibility(View.VISIBLE);
                    textViewRecPush.setVisibility(View.VISIBLE);

                    imageViewPlay.setVisibility(View.INVISIBLE);

                    toastGenerico.setText(context.getString(R.string.grabando_tiempo_max));
                    toastGenerico.show();

                    crearArchivoAudioYComenzarAGrabar();

                    detenerGrabacionAutomaticamente();
                }
            });

            imageViewRecPush.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AudioRecord.stopRecording();

                    imageViewRec.setVisibility(View.VISIBLE);
                    textViewRec.setVisibility(View.VISIBLE);
                    imageViewRecPush.setVisibility(View.INVISIBLE);
                    textViewRecPush.setVisibility(View.INVISIBLE);

                    imageViewPlay.setVisibility(View.VISIBLE);
                }
            });

            imageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AudioRecord.startPlaying(nombreAudioCompleto);
                }
            });

            String[] divisiones = palabraAgregadaBeanParaEditar.getCadenaEnIngles().split("-");

            String cadenaInglesParte1 = divisiones[0];
            String cadenaInglesParte2 = divisiones[1];

            editTextPalabraEnEspDialog.setText(palabraAgregadaBeanParaEditar.getPalabraEnEsp());
            editTextPalabraEnIng_1_Dialog.setText(cadenaInglesParte1);
            editTextPalabraEnIng_2_Dialog.setText(cadenaInglesParte2);

            editTextPalabraEnEspDialog.requestFocus();
            editTextPalabraEnEspDialog.setNextFocusDownId(R.id.editTextPalabraEnIng_1_Dialog);
            editTextPalabraEnIng_1_Dialog.setNextFocusDownId(R.id.editTextPalabraEnIng_2_Dialog);
            editTextPalabraEnIng_2_Dialog.setNextFocusDownId(R.id.editTextPalabraEnEspDialog);

            final int positionParaDialog = position;

            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(viewDialogDesign)
                    .setPositiveButton(context.getString(R.string.modificar_palabra), null)
                    .setNegativeButton(context.getString(R.string.cancelar), null).setCancelable(false).create();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

                    buttonPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(imageViewRecPush.isShown()){
                                toastGenerico.setText(context.getString(R.string.grabando));
                                toastGenerico.show();
                            } else{
                                String palabraEnEsp = editTextPalabraEnEspDialog.getText().toString();
                                String palabraEnIngEscrita = editTextPalabraEnIng_1_Dialog.getText().toString();
                                String palabraEnIngPronunciacion = editTextPalabraEnIng_2_Dialog.getText().toString();
                                Boolean markedWithStar = palabraAgregadaBeanParaEditar.getMarkedWithStar();

                                if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() || nombreAudioCompleto == null){
                                    nombreAudioCompleto = palabraAgregadaBeanParaEditar.getNombreArchivoAudio();
                                }

                                Integer id = palabraAgregadaBeanParaEditar.getId();

                                if(IverbsPatterns.validarCadenaEsp(palabraEnEsp) == false
                                        || IverbsPatterns.validarCadenaIng(palabraEnIngEscrita) == false
                                        || IverbsPatterns.validarCadenaEsp(palabraEnIngPronunciacion) == false){
                                    toastGenerico.setText(context.getString(R.string.datos_validos));
                                    toastGenerico.show();
                                } else if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() || nombreAudioCompleto == null) {
                                    toastGenerico.setText(context.getString(R.string.grabar_pronunciacion));
                                    toastGenerico.show();
                                } else{
                                    String cadenaEnIngles =  palabraEnIngEscrita + "-" + palabraEnIngPronunciacion;
                                    String cadenaDeArchivoAudio = generarCadenaDeArchivoAudio(nombreAudioCompleto);
                                    PalabraAgregadaBean palabraAgregadaBean = new PalabraAgregadaBean(palabraEnEsp, cadenaEnIngles, markedWithStar);
                                    palabraAgregadaBean.setNombreArchivoAudio(nombreAudioCompleto);
                                    palabraAgregadaBean.setArchivoDeAudioEnString(cadenaDeArchivoAudio);

                                    palabraAgregadaBean.setId(id);

                                    if(siEsPosibleAgregarOModificar(palabraAgregadaBean)){
                                        actualizarPalabra(palabraAgregadaBean, positionParaDialog);

                                        borrarArchivosDeAudioSinAsignacion();

                                        if(nombreAudioCompleto != null && nombreAudioCompletoAntiguo != null
                                                && nombreAudioCompleto.equals(nombreAudioCompletoAntiguo) == false){
                                            borrarAudio(nombreAudioCompletoAntiguo);
                                        }

                                        dialog.dismiss();
                                    } else{
                                        toastGenerico.setText(context.getString(R.string.palabra_existente));
                                        toastGenerico.show();
                                    }
                                }
                            }
                        }
                    });

                    buttonNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(imageViewRecPush.isShown() == false){

                                borrarArchivosDeAudioSinAsignacion();

                                dialog.dismiss();
                            } else{
                                toastGenerico.setText(context.getString(R.string.grabando));
                                toastGenerico.show();
                            }
                        }
                    });
                }
            });

            dialog.show();
        }

        public String generarCadenaDeArchivoAudio(String nombreAudioCompleto){
            byte[] bytesAudioFile = AudioRecord.contertirAudioFileABytes(nombreAudioCompleto);
            String textoAudioFile = AudioRecord.contertirBytesFileEnStringFile(bytesAudioFile);

            return textoAudioFile;
        }

        public void crearArchivoAudioYComenzarAGrabar(){
            String nombreAudio = AudioRecord.generarNombreDeArchivoAudio();

            nombreAudioCompleto = AudioRecord.darNombreArchivoCompleto(context, nombreAudio);

            if(siExisteAudio(nombreAudioCompleto)){
                nombreAudioCompleto = null;
                crearArchivoAudioYComenzarAGrabar();
            } else {
                AudioRecord.startRecording(nombreAudioCompleto);

                listaDeNombreAudioCompleto.add(nombreAudioCompleto);

                SharedPreferences.Editor editor = sharedPreferencesParaPalabra.edit();
                editor.putStringSet("audioTerminadoParaPalabra", listaDeNombreAudioCompleto);
                editor.apply();
            }
        }

        public void detenerGrabacionAutomaticamente(){
            hiloActual = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Dentro de este 2do hilo se crea otro hilo que se comunicará con el hilo de la interfaz.
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(imageViewRecPush.isShown()){
                                AudioRecord.stopRecording();

                                imageViewRec.setVisibility(View.VISIBLE);
                                textViewRec.setVisibility(View.VISIBLE);
                                imageViewRecPush.setVisibility(View.INVISIBLE);
                                textViewRecPush.setVisibility(View.INVISIBLE);

                                imageViewPlay.setVisibility(View.VISIBLE);

                                toastGenerico.setText(context.getString(R.string.grabacion_terminada));
                                toastGenerico.show();
                            }
                        }
                    });
                }
            });
            hiloActual.start();
        }

        public Boolean siExisteAudio(String nuevoNombreArchivoAudio){
            Boolean respuesta = Boolean.FALSE;
            File file = new File(nuevoNombreArchivoAudio);

            if(file.exists()){
                respuesta = Boolean.TRUE;
            } else{
                file.delete();
            }

            return respuesta;
        }

        public void borrarAudio(String nombreAudioCompleto){
            if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
                File fileToDelete = new File(nombreAudioCompleto);
                if(fileToDelete != null){
                    fileToDelete.delete();
                }
            }
        }

        public Boolean siEsPosibleAgregarOModificar(PalabraAgregadaBean palabra){
            PalabraAgregadaBean palabraDesdeBd = realm.where(PalabraAgregadaBean.class).equalTo("palabraEnEsp", palabra.getPalabraEnEsp()).findFirst();
            Boolean respuesta = Boolean.FALSE;

            if(palabraDesdeBd == null){
                respuesta = Boolean.TRUE;
            } else if(palabraDesdeBd != null && palabra.getId() == palabraDesdeBd.getId()){
                respuesta = Boolean.TRUE;
            }

            return respuesta;
        }

        private void actualizarPalabra(PalabraAgregadaBean palabraAgregadaBean, int position) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(palabraAgregadaBean);
            realm.commitTransaction();

            SharedPreferences.Editor editor = sharedPreferencesParaPalabra.edit();
            listaDeNombreAudioCompleto.remove(nombreAudioCompleto);
            editor.putStringSet("audioTerminadoParaPalabra", listaDeNombreAudioCompleto);
            editor.apply();

            activity.finish();
            Intent intent = new Intent(activity, ListaMisPalabrasActivity.class);
            intent.putExtra("posicionPorModificacion", position);
            context.startActivity(intent);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater menuInflater = activity.getMenuInflater();
            menuInflater.inflate(R.menu.context_menu_list_palabras, contextMenu);

            for(int i=0; i < contextMenu.size(); i++){
                // Agrega a cada elemento del menú un listener. Usando el ListView desde el activity esto se
                // manejaba desde el método onContextItemSelected.
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }
    }

    public void borrarArchivosDeAudioSinAsignacion() {
        Set<String> nombreDeAudioCompletoParaPalabra = sharedPreferencesParaPalabra.getStringSet("audioTerminadoParaPalabra", null);
        SharedPreferences.Editor editor = sharedPreferencesParaPalabra.edit();

        if (nombreDeAudioCompletoParaPalabra != null) {
            for (Iterator<String> iterator = nombreDeAudioCompletoParaPalabra.iterator(); iterator.hasNext(); ) {
                String nombreAudioCompletoParaBorrar = iterator.next();
                File fileToDelete = new File(nombreAudioCompletoParaBorrar);
                fileToDelete.delete();

                iterator.remove();
            }

            editor.remove("audioTerminadoParaPalabra");
            editor.apply();
        }
    }

    public interface OnItemClickListener{
        void click(int position);
    }
}