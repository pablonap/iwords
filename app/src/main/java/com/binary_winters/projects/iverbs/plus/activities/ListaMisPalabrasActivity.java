package com.binary_winters.projects.iverbs.plus.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;
import com.binary_winters.projects.iverbs.plus.PalabraAgregadaAdapter;
import com.binary_winters.projects.iverbs.util.AudioRecord;
import com.binary_winters.projects.iverbs.util.BusquedaDePalabra;
import com.binary_winters.projects.iverbs.util.IverbsPatterns;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListaMisPalabrasActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<PalabraAgregadaBean>> {
    private Realm realm;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RealmResults<PalabraAgregadaBean> palabrasAgregadasFromRealm;
    private TextView textViewNoHayPalabrasLabel;
    private List<PalabraAgregadaBean> palabrasAgregadas;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    private MenuItem menuDelete;

    private ImageView imageViewHelp;
    private Toast toastGenerico;

    private ImageView imageViewRec;
    private ImageView imageViewRecPush;
    private ImageView imageViewPlay;

    private TextView textViewRec;
    private TextView textViewRecPush;

    private String nombreAudioCompleto;
    private Set<String> listaDeNombreAudioCompleto;
    private Thread hiloActual;

    public MenuItem getMenuDelete() {
        return menuDelete;
    }

    public RealmResults<PalabraAgregadaBean> getPalabrasAgregadasFromRealm() {
        return palabrasAgregadasFromRealm;
    }

    private SharedPreferences sharedPreferencesParaPalabra;

    private BusquedaDePalabra busquedaDePalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mis_palabras);
        realm = Realm.getDefaultInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        // Pedir permisos en runtime (obligatorio)
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        listaDeNombreAudioCompleto = new HashSet<String>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewPalabras);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        textViewNoHayPalabrasLabel = (TextView) findViewById(R.id.textViewNoHayPalabrasLabel);

        imageViewHelp = (ImageView) findViewById(R.id.imageViewHelp);

        palabrasAgregadasFromRealm = realm.where(PalabraAgregadaBean.class).findAll();

        palabrasAgregadas = new ArrayList<>();

        toastGenerico = Toast.makeText(ListaMisPalabrasActivity.this, "", Toast.LENGTH_SHORT);

        busquedaDePalabra = new BusquedaDePalabra(ListaMisPalabrasActivity.this, toastGenerico) {
            @Override
            public void buscarPalabraEnEsp(String nombrePalabraEsp) {
                if(nombrePalabraEsp != null){
                    Integer indicePalabraHallada = null;
                    PalabraAgregadaBean palabraAgregadaBean = null;
                    for(int i = 0; i < palabrasAgregadasFromRealm.size(); i++){
                        if(nombrePalabraEsp.equals(palabrasAgregadasFromRealm.get(i).getPalabraEnEsp())){
                            palabraAgregadaBean = palabrasAgregadasFromRealm.get(i);
                            indicePalabraHallada = i;
                        }
                    }

                    if(palabraAgregadaBean != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText(getString(R.string.palabra_no_hallada));
                        toastGenerico.show();
                    }
                }
            }

            @Override
            public void buscarPalabraEnIng(String palabraEnIng) {
                if(palabraEnIng != null){
                    PalabraAgregadaBean palabraAgregadaBean = null;
                    int indicePalabraHallada = 0;
                    for(int i = 0; i < palabrasAgregadasFromRealm.size(); i++){
                        String[] divisionesDeGuion = palabrasAgregadasFromRealm.get(i).getCadenaEnIngles().split("-");
                        String divisionGuionEscritura = divisionesDeGuion[0];

                        if(divisionGuionEscritura != null){
                            if(palabraEnIng.equals(divisionGuionEscritura)){
                                palabraAgregadaBean = palabrasAgregadasFromRealm.get(i);
                                indicePalabraHallada = i;
                            }
                        }
                    }

                    if(palabraAgregadaBean != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText(getString(R.string.verbo_no_hallado));
                        toastGenerico.show();
                    }
                }
            }
        };

        sharedPreferencesParaPalabra = getSharedPreferences("archivoAudioParaPalabra", Context.MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            int posicion = bundle.getInt("posicionPorModificacion");
            mLayoutManager.scrollToPosition(posicion);
        }

        imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastGenerico.setText(getString(R.string.para_ver_op));
                toastGenerico.show();
            }
        });

        // Se vuelvan los objetos de la lista palabrasAgregadasFromRealm que vienen en null (ya que están dentro)
        // de un objeto proxy a otra lista que es la que se envía al adapter y usa acá para referencias la estrella.
        for(PalabraAgregadaBean palabraAgregadaActual : palabrasAgregadasFromRealm){
            PalabraAgregadaBean palabraAgregadaBean
                    = new PalabraAgregadaBean(palabraAgregadaActual.getPalabraEnEsp(),
                    palabraAgregadaActual.getCadenaEnIngles(), palabraAgregadaActual.getMarkedWithStar());
            palabraAgregadaBean.setNombreArchivoAudio(palabraAgregadaActual.getNombreArchivoAudio());
            palabraAgregadaBean.setArchivoDeAudioEnString(palabraAgregadaActual.getArchivoDeAudioEnString());
            palabraAgregadaBean.setId(palabraAgregadaActual.getId());
            palabrasAgregadas.add(palabraAgregadaBean);
        }

        myAdapter = new PalabraAgregadaAdapter(palabrasAgregadas, R.layout.item_view_palabra_agregada,
                this, new PalabraAgregadaAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                PalabraAgregadaBean palabraAgregadaBean = palabrasAgregadas.get(position);
                if (palabraAgregadaBean.getMarkedWithStar()) {
                    palabraAgregadaBean.setMarkedWithStar(Boolean.FALSE);
                } else {
                    palabraAgregadaBean.setMarkedWithStar(Boolean.TRUE);

                    toastGenerico.setText(getString(R.string.agregado_a_favoritos));
                    toastGenerico.show();
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(palabraAgregadaBean);
                realm.commitTransaction();

                myAdapter.notifyDataSetChanged();
            }
        }, new PalabraAgregadaAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                PalabraAgregadaBean palabraAgregadaBean = palabrasAgregadas.get(position);
                AudioRecord.startPlaying(palabraAgregadaBean.getNombreArchivoAudio());
            }
        });
        mRecyclerView.setAdapter(myAdapter);

        registerForContextMenu(mRecyclerView);

        if(palabrasAgregadasFromRealm.isEmpty()){
            textViewNoHayPalabrasLabel.setVisibility(View.VISIBLE);
        } else {
            textViewNoHayPalabrasLabel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onChange(RealmResults<PalabraAgregadaBean> miPalabraBeen) {
        myAdapter.notifyDataSetChanged();
        if(palabrasAgregadasFromRealm.isEmpty()){
            textViewNoHayPalabrasLabel.setVisibility(View.VISIBLE);
        } else{
            textViewNoHayPalabrasLabel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_palabras_agregadas_activity, menu);
        menuDelete = menu.findItem(R.id.menu_item_delete_palabras);

        if(palabrasAgregadasFromRealm != null && palabrasAgregadasFromRealm.isEmpty()){
            menu.findItem(R.id.menu_item_delete_palabras).setVisible(Boolean.FALSE);
        } else{
            menu.findItem(R.id.menu_item_delete_palabras).setVisible(Boolean.TRUE);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_find_palabra:
                busquedaDePalabra.realizarBusqueda();
                return true;
            case R.id.menu_item_add_palabra:
                mostrarDialogParaAgregarPalabra();
                return true;
            case R.id.menu_item_delete_palabras:
                mostrarDialogBorrarPalabrasAgregadas(getString(R.string.aviso), getString(R.string.borrar_palabras));
                return true;
            case R.id.menu_item_arriba_palabras:
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.menu_item_abajo_palabras:
                mLayoutManager.scrollToPosition(palabrasAgregadasFromRealm.size()-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogBorrarPalabrasAgregadas(String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrarPalabrasAgregadas();

                        // Invocando primero a finish() se elimina del stack el activity actual y luego se crea otro.
                        // Esto se hace para evitar que al presionar back vuelva a la versión anterior del activity.
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton(getString(R.string.no), null).setCancelable(false).show();
    }

    private void borrarPalabrasAgregadas() {
        for(PalabraAgregadaBean palabraAgregadaBean : palabrasAgregadas){
            borrarAudio(palabraAgregadaBean.getNombreArchivoAudio());
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                palabrasAgregadasFromRealm.deleteAllFromRealm();
            }
        });

        textViewNoHayPalabrasLabel.setVisibility(View.VISIBLE);
    }

    private void mostrarDialogParaAgregarPalabra() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewDialogDesign = inflater.inflate(R.layout.dialog_agregar_palabra, null);

        final EditText editTextPalabraEnEspDialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnEspDialog);
        final EditText editTextPalabraEnIng_1_Dialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnIng_1_Dialog);
        final EditText editTextPalabraEnIng_2_Dialog = (EditText) viewDialogDesign.findViewById(R.id.editTextPalabraEnIng_2_Dialog);

        imageViewRec = (ImageView) viewDialogDesign.findViewById(R.id.imageViewRec);
        imageViewRecPush = (ImageView) viewDialogDesign.findViewById(R.id.imageViewRecPush);
        imageViewPlay = (ImageView) viewDialogDesign.findViewById(R.id.imageViewPlay);

        textViewRec = (TextView) viewDialogDesign.findViewById(R.id.textViewRec);
        textViewRecPush = (TextView) viewDialogDesign.findViewById(R.id.textViewRecPush);

        editTextPalabraEnEspDialog.requestFocus();
        editTextPalabraEnEspDialog.setNextFocusDownId(R.id.editTextPalabraEnIng_1_Dialog);
        editTextPalabraEnIng_1_Dialog.setNextFocusDownId(R.id.editTextPalabraEnIng_2_Dialog);
        editTextPalabraEnIng_2_Dialog.setNextFocusDownId(R.id.editTextPalabraEnEspDialog);

        if(imageViewRecPush.isShown()){
            imageViewPlay.setVisibility(View.INVISIBLE);
        }

        imageViewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewRec.setVisibility(View.INVISIBLE);
                textViewRec.setVisibility(View.INVISIBLE);
                imageViewRecPush.setVisibility(View.VISIBLE);
                textViewRecPush.setVisibility(View.VISIBLE);

                imageViewPlay.setVisibility(View.INVISIBLE);

                toastGenerico.setText(getString(R.string.grabando_tiempo_max));
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

                if(hiloActual != null){
                    hiloActual.interrupt();
                }
            }
        });

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioRecord.startPlaying(nombreAudioCompleto);
            }
        });

        final AlertDialog dialog = new AlertDialog.Builder(this)
            .setView(viewDialogDesign)
            .setPositiveButton(getString(R.string.agregar_palabra), null)
            .setNegativeButton(getString(R.string.cancelar), null)
                .setCancelable(false).create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(imageViewRecPush.isShown()){
                            toastGenerico.setText(getString(R.string.grabando));
                            toastGenerico.show();
                        } else{
                            String palabraEnEsp = editTextPalabraEnEspDialog.getText().toString().trim();
                            String palabraEnIngEscrita = editTextPalabraEnIng_1_Dialog.getText().toString().trim();
                            String palabraEnIngPronunciacion = editTextPalabraEnIng_2_Dialog.getText().toString().trim();

                            if(IverbsPatterns.validarCadenaEsp(palabraEnEsp) == false
                                    || IverbsPatterns.validarCadenaIng(palabraEnIngEscrita) == false
                                    || IverbsPatterns.validarCadenaEsp(palabraEnIngPronunciacion) == false){
                                toastGenerico.setText(getString(R.string.datos_validos));
                                toastGenerico.show();
                            } else if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() || nombreAudioCompleto == null){
                                toastGenerico.setText(getString(R.string.grabar_pronunciacion));
                                toastGenerico.show();
                            } else{
                                String cadenaEnIngles =  palabraEnIngEscrita + "-" + palabraEnIngPronunciacion;
                                String cadenaDeArchivoAudio = generarCadenaDeArchivoAudio(nombreAudioCompleto);

                                PalabraAgregadaBean palabraAgregadaBean = new PalabraAgregadaBean(palabraEnEsp, cadenaEnIngles, Boolean.FALSE);
                                palabraAgregadaBean.setNombreArchivoAudio(nombreAudioCompleto);
                                palabraAgregadaBean.setArchivoDeAudioEnString(cadenaDeArchivoAudio);
                                palabraAgregadaBean.setIdFromBd();

                                if(siEsPosibleAgregarOModificar(palabraAgregadaBean)){
                                    agregarPalabra(palabraAgregadaBean);
                                    borrarArchivosDeAudioSinAsignacion();
                                    nombreAudioCompleto = null;
                                    dialog.dismiss();
                                } else{
                                    toastGenerico.setText(getString(R.string.palabra_existente));
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
                            nombreAudioCompleto = null;
                            dialog.dismiss();
                        } else{
                            toastGenerico.setText(getString(R.string.grabando));
                            toastGenerico.show();
                        }
                    }
                });
            }
        });

        dialog.show();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(imageViewRecPush.isShown()){
                            AudioRecord.stopRecording();

                            imageViewRec.setVisibility(View.VISIBLE);
                            textViewRec.setVisibility(View.VISIBLE);
                            imageViewRecPush.setVisibility(View.INVISIBLE);
                            textViewRecPush.setVisibility(View.INVISIBLE);

                            imageViewPlay.setVisibility(View.VISIBLE);

                            toastGenerico.setText(getString(R.string.grabacion_terminada));
                            toastGenerico.show();
                        }
                    }
                });
            }
        });
        hiloActual.start();
    }

    public String generarCadenaDeArchivoAudio(String nombreAudioCompleto){
        byte[] bytesAudioFile = AudioRecord.contertirAudioFileABytes(nombreAudioCompleto);
        String textoAudioFile = AudioRecord.contertirBytesFileEnStringFile(bytesAudioFile);

        return textoAudioFile;
    }

    public void crearArchivoAudioYComenzarAGrabar() {
        String nombreAudio = AudioRecord.generarNombreDeArchivoAudio();

        nombreAudioCompleto = AudioRecord.darNombreArchivoCompleto(this, nombreAudio);

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

    public Boolean siExisteAudio(String nuevoNombreArchivoAudio){
        Boolean respuesta = Boolean.FALSE;
        File file = new File(nuevoNombreArchivoAudio);

        if(file.exists()){
            respuesta = Boolean.TRUE;
        } else{
            System.out.println();
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

    private void agregarPalabra(PalabraAgregadaBean palabraAgregadaBean) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(palabraAgregadaBean);
        realm.commitTransaction();
        palabrasAgregadas.add(palabraAgregadaBean);

        SharedPreferences.Editor editor = sharedPreferencesParaPalabra.edit();
        listaDeNombreAudioCompleto.remove(nombreAudioCompleto);
        editor.putStringSet("audioTerminadoParaPalabra", listaDeNombreAudioCompleto);

        editor.apply();

        myAdapter.notifyDataSetChanged();
        textViewNoHayPalabrasLabel.setVisibility(View.INVISIBLE);
        menuDelete.setVisible(Boolean.TRUE);
        mLayoutManager.scrollToPosition(palabrasAgregadasFromRealm.size() - 1);
    }

    @Override
    public void onBackPressed() {
        borrarArchivosDeAudioSinAsignacion();
        finish();
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
}
