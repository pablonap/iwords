package com.binary_winters.projects.iverbs.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.VerboEnEsp;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;
import com.binary_winters.projects.iverbs.util.AudioRecord;
import com.binary_winters.projects.iverbs.util.IverbsPatterns;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import io.realm.Realm;

public class AddVerbActivity extends AppCompatActivity {
    private EditText editTextVerboEsp;
    private EditText editTextRow1_verb1;
    private EditText editTextRow1_verb2;
    private EditText editTextRow2_verb1;
    private EditText editTextRow2_verb2;
    private EditText editTextRow3_verb1;
    private EditText editTextRow3_verb2;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    private String nombreAudioCompleto;
    private String nombreAudioCompletoAntiguo;

    private Button buttonAgregar;

    private Realm realm;

    private String verbos;

    private VerboGenericoBean verboDeUsuario;

    private VerboGenericoBean verboGenericoBeanParaEditar = null;

    private VerboEnEsp verboEspParaEditar;

    private ImageView imageViewRec;
    private ImageView imageViewRecPush;
    private ImageView imageViewPlay;

    private TextView textViewRec;
    private TextView textViewRecPush;

    private Toast toastGenerico;

    private SharedPreferences sharedPreferencesParaVerbo;
    private Set<String> listaDeNombreAudioCompleto;

    private Thread hiloActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_verb);

        toastGenerico = Toast.makeText(AddVerbActivity.this, "", Toast.LENGTH_SHORT);

        // Pedir permisos en runtime (obligatorio)
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        realm = Realm.getDefaultInstance();

        listaDeNombreAudioCompleto = new HashSet<String>();

        editTextVerboEsp = (EditText) findViewById(R.id.editTextVerboEsp);
        editTextRow1_verb1 = (EditText) findViewById(R.id.editTextRow1_verb1);
        editTextRow1_verb2 = (EditText) findViewById(R.id.editTextRow1_verb2);
        editTextRow2_verb1 = (EditText) findViewById(R.id.editTextRow2_verb1);
        editTextRow2_verb2 = (EditText) findViewById(R.id.editTextRow2_verb2);
        editTextRow3_verb1 = (EditText) findViewById(R.id.editTextRow3_verb1);
        editTextRow3_verb2 = (EditText) findViewById(R.id.editTextRow3_verb2);

        editTextVerboEsp.requestFocus();
        editTextVerboEsp.setNextFocusDownId(R.id.editTextRow1_verb1);
        editTextRow1_verb1.setNextFocusDownId(R.id.editTextRow1_verb2);
        editTextRow1_verb2.setNextFocusDownId(R.id.editTextRow2_verb1);
        editTextRow2_verb1.setNextFocusDownId(R.id.editTextRow2_verb2);
        editTextRow2_verb2.setNextFocusDownId(R.id.editTextRow3_verb1);
        editTextRow3_verb1.setNextFocusDownId(R.id.editTextRow3_verb2);
        editTextRow3_verb2.setNextFocusDownId(R.id.editTextVerboEsp);

        buttonAgregar = (Button) findViewById(R.id.buttonAgregar);

        imageViewRec = (ImageView) findViewById(R.id.imageViewRec);
        imageViewRecPush = (ImageView) findViewById(R.id.imageViewRecPush);
        imageViewPlay = (ImageView) findViewById(R.id.imageViewPlay);

        textViewRec = (TextView) findViewById(R.id.textViewRec);
        textViewRecPush = (TextView) findViewById(R.id.textViewRecPush);

        if(imageViewRecPush.isShown()){
            imageViewPlay.setVisibility(View.INVISIBLE);
        }

        sharedPreferencesParaVerbo = getSharedPreferences("archivoAudioParaVerbo", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("verboGenericoBeanParaEditar");

        if(bundle != null){
            verboGenericoBeanParaEditar = (VerboGenericoBean) bundle.getSerializable("verboGenericoBeanParaEditarSerializado");

            if(verboGenericoBeanParaEditar != null){
                nombreAudioCompleto = verboGenericoBeanParaEditar.getNombreArchivoAudio();
                verboEspParaEditar = verboGenericoBeanParaEditar.getVerboEnEsp();
                String[] divisiones = verboGenericoBeanParaEditar.getCadenaDeVerbos().split(" ");

                if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
                    imageViewPlay.setVisibility(View.VISIBLE);
                    nombreAudioCompletoAntiguo = nombreAudioCompleto;
                }

                String verboEnIngCol1 = divisiones[0];
                String verboEnIngCol2 = divisiones[1];
                String verboEnIngCol3 = divisiones[2];

                String[] divisiones_col1 = divisiones[0].split("-");

                String verboEnIngCol1_1 = divisiones_col1[0];
                String verboEnIngCol1_2 = divisiones_col1[1];

                String[] divisiones_col2 = divisiones[1].split("-");

                String verboEnIngCol2_1 = divisiones_col2[0];
                String verboEnIngCol2_2 = divisiones_col2[1];

                String[] divisiones_col3 = divisiones[2].split("-");

                String verboEnIngCol3_1 = divisiones_col3[0];
                String verboEnIngCol3_2 = divisiones_col3[1];

                editTextVerboEsp.setText(verboEspParaEditar.getNombreDeVerbo());
                editTextRow1_verb1.setText(verboEnIngCol1_1);
                editTextRow1_verb2.setText(verboEnIngCol1_2);
                editTextRow2_verb1.setText(verboEnIngCol2_1);
                editTextRow2_verb2.setText(verboEnIngCol2_2);
                editTextRow3_verb1.setText(verboEnIngCol3_1);
                editTextRow3_verb2.setText(verboEnIngCol3_2);

                buttonAgregar.setText(getString(R.string.modificar_upper));
            }
        }

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageViewRecPush.isShown()){
                    toastGenerico.setText(getString(R.string.grabando));
                    toastGenerico.show();
                } else{
                    if(IverbsPatterns.validarCadenaEsp(editTextVerboEsp.getText().toString()) == false
                            || IverbsPatterns.validarCadenaIng(editTextRow1_verb1.getText().toString()) == false
                            || IverbsPatterns.validarCadenaEsp(editTextRow1_verb2.getText().toString()) == false
                            || IverbsPatterns.validarCadenaIng(editTextRow2_verb1.getText().toString()) == false
                            || IverbsPatterns.validarCadenaEsp(editTextRow2_verb2.getText().toString()) == false
                            || IverbsPatterns.validarCadenaIng(editTextRow3_verb1.getText().toString()) == false
                            || IverbsPatterns.validarCadenaEsp(editTextRow3_verb2.getText().toString()) == false){
                        toastGenerico.setText(getString(R.string.datos_validos));
                        toastGenerico.show();
                    } else if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() || nombreAudioCompleto == null){
                        toastGenerico.setText(getString(R.string.grabar_pronunciacion));
                        toastGenerico.show();
                    }
                    else{
                        // Evita que sea eliminado el audio que va a vincularse al nuevo verbo.
                        SharedPreferences.Editor editor = sharedPreferencesParaVerbo.edit();
                        listaDeNombreAudioCompleto.remove(nombreAudioCompleto);
                        editor.putStringSet("audioTerminadoParaVerbo", listaDeNombreAudioCompleto);
                        editor.apply();

                        borrarArchivosDeAudioSinAsignacion();
                        mostrarAvisoAntesDeAgregar();
                    }
                }
            }
        });

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

        if(siExisteAudio(nombreAudioCompleto)) {
            crearArchivoAudioYComenzarAGrabar();
        } else {
            AudioRecord.startRecording(nombreAudioCompleto);

            listaDeNombreAudioCompleto.add(nombreAudioCompleto);

            SharedPreferences.Editor editor = sharedPreferencesParaVerbo.edit();
            editor.putStringSet("audioTerminadoParaVerbo", listaDeNombreAudioCompleto);
            editor.apply();
        }
    }

    public Boolean siExisteAudio(String nuevoNombreArchivoAudio){
        Boolean respuesta = Boolean.FALSE;
        File file = new File(nuevoNombreArchivoAudio);

        if(file.exists()){
            respuesta = Boolean.TRUE;
        } else{
            // Borra el archivo recién creado
            file.delete();
        }

        return respuesta;
    }

    public void borrarAudio(String nombreAudioCompleto){
        if(nombreAudioCompleto != null){
            File fileToDelete = new File(nombreAudioCompleto);
            if(fileToDelete != null){
                fileToDelete.delete();
            }
        }
    }

    private void mostrarAvisoAntesDeAgregar() {
        String textRow1_verb1 = editTextRow1_verb1.getText().toString().trim();
        String textRow1_verb2 = editTextRow1_verb2.getText().toString().trim();
        String textRow2_verb1 = editTextRow2_verb1.getText().toString().trim();
        String textRow2_verb2 = editTextRow2_verb2.getText().toString().trim();
        String textRow3_verb1 = editTextRow3_verb1.getText().toString().trim();
        String textRow3_verb2 = editTextRow3_verb2.getText().toString().trim();

        verbos = textRow1_verb1 + "-" + textRow1_verb2 +
                "\n" + textRow2_verb1 + "-" + textRow2_verb2 +
                "\n" + textRow3_verb1 + "-" + textRow3_verb2;

        String mensaje = getString(R.string.texto_ingresado) + verbos;

        String tituloDeDialog = null;

        if(verboGenericoBeanParaEditar == null){
            tituloDeDialog = getString(R.string.agregar_nuevo_verbo);
        } else{
            tituloDeDialog = getString(R.string.modificar_verbo);
        }

            new AlertDialog.Builder(this)
                .setTitle(tituloDeDialog)
                .setMessage(mensaje)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        VerboEnEsp verboEnEsp = new VerboEnEsp(editTextVerboEsp.getText().toString(), Boolean.FALSE, Boolean.FALSE);
                        verbos = verbos.replace("\n", " ");

                        if(verboGenericoBeanParaEditar != null){
                            verboGenericoBeanParaEditar.setCadenaDeVerbos(verbos);
                            verboGenericoBeanParaEditar.setVerboEnEsp(verboEnEsp);
                            verboEnEsp.setId(verboEspParaEditar.getId());
                        }

                        if(siEsPosibleAgregarOModificar(verboEnEsp)){
                            persistirVerbos(verboEnEsp, verbos);

                            if(nombreAudioCompleto != null && nombreAudioCompletoAntiguo != null
                                    && nombreAudioCompleto.equals(nombreAudioCompletoAntiguo) == false){
                                borrarAudio(nombreAudioCompletoAntiguo);
                            }

                            retornarHaciaListaDeVerbosAgregados();
                        } else{
                            toastGenerico.setText(getString(R.string.verbo_existe));
                            toastGenerico.show();
                        }
                    }
                })
                .setNegativeButton(getString(R.string.cancelar), null).setCancelable(false).show();
    }

    public Boolean siEsPosibleAgregarOModificar(VerboEnEsp verboEnEsp){
        VerboEnEsp verboDesdeBd = realm.where(VerboEnEsp.class).equalTo("nombreDeVerbo", verboEnEsp.getNombreDeVerbo()).findFirst();
        Boolean respuesta = Boolean.FALSE;

        if(verboDesdeBd == null){
            respuesta = Boolean.TRUE;
        }


//        else if(verboDesdeBd != null && verboEnEsp.getId() == verboDesdeBd.getId()){
//            respuesta = Boolean.TRUE;
//        }

        return respuesta;
    }

    private void retornarHaciaListaDeVerbosAgregados() {
        Intent intent = new Intent(AddVerbActivity.this, VerbosAgregadosActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("ultimoVerboDeUsuarioAgregadoSerializado",(Serializable)verboDeUsuario);
        intent.putExtra("ultimoVerboDeUsuarioAgregado",bundle);
        startActivity(intent);
        finish();
    }

    private void persistirVerbos(VerboEnEsp verboEnEsp, String verbos) {
        realm.beginTransaction();

        verboDeUsuario = new VerboGenericoBean(verboEnEsp, verbos);

        String cadenaDeArchivoAudio = generarCadenaDeArchivoAudio(nombreAudioCompleto);
        verboDeUsuario.setNombreArchivoAudio(nombreAudioCompleto);
        verboDeUsuario.setArchivoDeAudioEnString(cadenaDeArchivoAudio);

        if(verboGenericoBeanParaEditar != null){
            verboDeUsuario.setId(verboGenericoBeanParaEditar.getId());
        } else{
            verboDeUsuario.setIdFromBd();
            verboDeUsuario.getVerboEnEsp().setIdFromBd();
        }

        realm.copyToRealmOrUpdate(verboDeUsuario);
        realm.commitTransaction();

        SharedPreferences.Editor editor = sharedPreferencesParaVerbo.edit();
        listaDeNombreAudioCompleto.remove(nombreAudioCompleto);
        editor.putStringSet("audioTerminadoParaPalabra", listaDeNombreAudioCompleto);

        editor.apply();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();
    }

    @Override
    public void onBackPressed() {
        if(imageViewRecPush.isShown() == false){
            borrarArchivosDeAudioSinAsignacion();

            finish();
        } else{
            toastGenerico.setText(getString(R.string.grabando));
            toastGenerico.show();
        }
    }

    public void borrarArchivosDeAudioSinAsignacion() {
        Set<String> nombreDeAudioCompletoParaPalabra = sharedPreferencesParaVerbo.getStringSet("audioTerminadoParaVerbo", null);
        SharedPreferences.Editor editor = sharedPreferencesParaVerbo.edit();

        if (nombreDeAudioCompletoParaPalabra != null) {
            for (Iterator<String> iterator = nombreDeAudioCompletoParaPalabra.iterator(); iterator.hasNext(); ) {
                String nombreAudioCompletoParaBorrar = iterator.next();
                File fileToDelete = new File(nombreAudioCompletoParaBorrar);
                fileToDelete.delete();

                iterator.remove();
            }

            editor.remove("audioTerminadoParaVerbo");
            editor.apply();
        }
    }
}