package com.binary_winters.projects.iverbs.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.VerboBind;
import com.binary_winters.projects.iverbs.VerboEnEsp;
import com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean;
import com.binary_winters.projects.iverbs.plus.activities.PracticarMisPalabrasActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import io.realm.Realm;

public class PartidaIverbsActivity extends AppCompatActivity {
    private LinearLayout mainLinerLayout;

    private Integer cantidadDeVerbosNumerica;

    static VerboBind verboBind;

    private Boolean isSoloEscrituraOn = Boolean.FALSE;

    private TextView textViewVerboEnEsp;
    private EditText editTextRow1_verb1;
    private EditText editTextRow1_verb2;
    private EditText editTextRow2_verb1;
    private EditText editTextRow2_verb2;
    private EditText editTextRow3_verb1;
    private EditText editTextRow3_verb2;

    private TextView textViewSeparador;
    private TextView textViewSeparador2;
    private TextView textViewSeparador3;

    private int txtSeparadorWidthSoloEscrituraOff1;
    private int txtSeparadorWidthSoloEscrituraOff2;
    private int txtSeparadorWidthSoloEscrituraOff3;
    private int editTextRow1_verb2WidthSoloEscrituraOff;
    private int editTextRow2_verb2WidthSoloEscrituraOff;
    private int editTextRow3_verb2WidthSoloEscrituraOff;

    private ImageView imageViewVerboAgregadoLabel;
    private TextView textViewDeImageViewVerboAgregadoLabel;

    private ImageView imageViewHelp;

    private Button buttonVerificar;
    private Button buttonSound;

    private int indice = 0;

    private List<VerboEnEsp> listaDeVerbosEnEsp;

    private List<String> cadenaEscritaPorUsuario = new ArrayList<String>();

    private Realm realm;

    private MediaPlayer mp;

    private ImageView imageViewStarFromPartidaIverbsActivity;

    private VerboEnEsp verboEnEspActual;

    private Toast toast;

    private int contadorParaToast;

    private AlertDialog dialogBackButton;

    private ImageView imageViewPronunciacionOn;
    private ImageView imageViewPronunciacionOff;
    private TextView textViewPronunciacionOn;
    private TextView textViewPronunciacionOff;

    private SharedPreferences sharedPreferencesParaVerbo;
    private SharedPreferences sharedPreferencesParaPalabra;

    private Integer contadorParaFelicitacion = 0;

    private Thread hiloActual;
    private int widthImageViewClock;
    private Integer segundosParaContdown;
    private Boolean isModoContdownActivado = Boolean.FALSE;
    private Boolean isTiempoTerminado = Boolean.FALSE;
    // Sirve para bloquear la cuenta en casos en que se vaya a otro activity.
    Boolean isPermitido = Boolean.TRUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_iverbs);

        mainLinerLayout = (LinearLayout) findViewById(R.id.mainLinerLayout);

        verboBind = new VerboBind();

        dialogBackButton  = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.abandonar))
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null).setCancelable(false).create();

        realm = Realm.getDefaultInstance();

        toast = Toast.makeText(PartidaIverbsActivity.this, "", Toast.LENGTH_LONG);

        imageViewPronunciacionOn = (ImageView) findViewById(R.id.imageViewPronunciacionOn);
        imageViewPronunciacionOff = (ImageView) findViewById(R.id.imageViewPronunciacionOff);
        textViewPronunciacionOn = (TextView) findViewById(R.id.textViewPronunciacionOn);
        textViewPronunciacionOff = (TextView) findViewById(R.id.textViewPronunciacionOff);

        textViewSeparador = (TextView) findViewById(R.id.textViewSeparador);
        textViewSeparador2 = (TextView) findViewById(R.id.textViewSeparador2);
        textViewSeparador3 = (TextView) findViewById(R.id.textViewSeparador3);

        imageViewVerboAgregadoLabel = (ImageView) findViewById(R.id.imageViewVerboAgregadoLabel);
        textViewDeImageViewVerboAgregadoLabel = (TextView) findViewById(R.id.textViewDeImageViewVerboAgregadoLabel);


        imageViewHelp = (ImageView) findViewById(R.id.imageViewHelp);

        imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogEjemplo();
            }
        });

        imageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
        textViewDeImageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);

        mostrarDialogCantidadDeVerbos();

        textViewVerboEnEsp = (TextView) findViewById(R.id.textViewVerboEnEsp);

        editTextRow1_verb1 = (EditText) findViewById(R.id.editTextRow1_verb1);
        editTextRow1_verb2 = (EditText) findViewById(R.id.editTextRow1_verb2);
        editTextRow2_verb1 = (EditText) findViewById(R.id.editTextRow2_verb1);
        editTextRow2_verb2 = (EditText) findViewById(R.id.editTextRow2_verb2);
        editTextRow3_verb1 = (EditText) findViewById(R.id.editTextRow3_verb1);
        editTextRow3_verb2 = (EditText) findViewById(R.id.editTextRow3_verb2);

        editTextRow1_verb1.requestFocus();

        editTextRow1_verb1.setNextFocusDownId(R.id.editTextRow1_verb2);
        editTextRow1_verb2.setNextFocusDownId(R.id.editTextRow2_verb1);
        editTextRow2_verb1.setNextFocusDownId(R.id.editTextRow2_verb2);
        editTextRow2_verb2.setNextFocusDownId(R.id.editTextRow3_verb1);
        editTextRow3_verb1.setNextFocusDownId(R.id.editTextRow3_verb2);
        editTextRow3_verb2.setNextFocusDownId(R.id.editTextRow1_verb1);

        imageViewPronunciacionOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEntornoPronunciacionOn();
            }
        });

        imageViewPronunciacionOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEntornoPronunciacionOff();
            }
        });

        buttonVerificar = (Button) findViewById(R.id.buttonVerificar);
        buttonSound = (Button) findViewById(R.id.buttonSound);

        sharedPreferencesParaVerbo = getSharedPreferences("archivoAudioParaVerbo", Context.MODE_PRIVATE);
        sharedPreferencesParaPalabra = getSharedPreferences("archivoAudioParaPalabra", Context.MODE_PRIVATE);

        // Para el caso que en medio de una grabación se haya cerrado abruptamente la aplicación.
        // Si se obtiene el nombre del audio, el archivo se creó y no se creó un verbo.
        // Esto es fácil de comprobar si se crea el archivo de modo que pueda ser visto por otras app:
        // Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/" + fileNameAudio + ".3gp";
//        String nombreDeAudioCompletoParaVerbo = sharedPreferencesParaVerbo.getString("audioTerminado", null);

        Set<String> nombreDeAudioCompletoParaVerbo = sharedPreferencesParaVerbo.getStringSet("audioTerminadoParaVerbo", null);
        SharedPreferences.Editor editorParaVerbo = sharedPreferencesParaVerbo.edit();

        if(nombreDeAudioCompletoParaVerbo != null){
            for (Iterator<String> iterator = nombreDeAudioCompletoParaVerbo.iterator(); iterator.hasNext();) {
                String nombreAudioCompletoParaBorrar = iterator.next();
                File fileToDelete = new File(nombreAudioCompletoParaBorrar);
                fileToDelete.delete();

                iterator.remove();
            }

            editorParaVerbo.remove("audioTerminadoParaVerbo");
            editorParaVerbo.apply();
        }

        Set<String> nombreDeAudioCompletoParaPalabra = sharedPreferencesParaPalabra.getStringSet("audioTerminadoParaPalabra", null);
        SharedPreferences.Editor editor = sharedPreferencesParaPalabra.edit();

        if(nombreDeAudioCompletoParaPalabra != null){
            for (Iterator<String> iterator = nombreDeAudioCompletoParaPalabra.iterator(); iterator.hasNext();) {
                String nombreAudioCompletoParaBorrar = iterator.next();
                File fileToDelete = new File(nombreAudioCompletoParaBorrar);
                fileToDelete.delete();

                iterator.remove();
            }

            editor.remove("audioTerminadoParaPalabra");
            editor.apply();
        }

        imageViewStarFromPartidaIverbsActivity = (ImageView) findViewById(R.id.imageViewStarFromMainActivity);

        listaDeVerbosEnEsp = new ArrayList<>();

        imageViewStarFromPartidaIverbsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verboEnEspActual != null){
                    if(verboEnEspActual.getMarkedWithStar()){
                        verboEnEspActual.setMarkedWithStar(Boolean.FALSE);
                    } else{
                        toast.setText(getString(R.string.agregado_a_favoritos));
                        toast.show();
                        verboEnEspActual.setMarkedWithStar(Boolean.TRUE);
                    }
                    cambiarEstrella(verboEnEspActual);

                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(verboEnEspActual);
                    realm.commitTransaction();
                }
            }
        });

        buttonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.release();
                    mp = null;
                }

                mp = VerboBind.cargarAudioSegunVerbo(PartidaIverbsActivity.this, mp, listaDeVerbosEnEsp.get(indice));

            }
        });

        buttonVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sirve para los casos en que el propio usuario presione el botón verificar y así la cuenta se reinicie para
                // el siguiente elemento.
                if(Boolean.FALSE.equals(isTiempoTerminado)){
                    if(hiloActual != null){
                        hiloActual.interrupt();
                    }
                }

                String row1_verb1_2;
                String row2_verb1_2;
                String row3_verb1_2;

                if(isSoloEscrituraOn){
                    row1_verb1_2 =  editTextRow1_verb1.getText().toString().toLowerCase();
                    row2_verb1_2 =  editTextRow2_verb1.getText().toString().toLowerCase();
                    row3_verb1_2 =  editTextRow3_verb1.getText().toString().toLowerCase();
                } else{
                    row1_verb1_2 =  editTextRow1_verb1.getText().toString().toLowerCase() + "-" +
                            editTextRow1_verb2.getText().toString().toLowerCase();
                    row2_verb1_2 =  editTextRow2_verb1.getText().toString().toLowerCase() + "-" +
                            editTextRow2_verb2.getText().toString().toLowerCase();
                    row3_verb1_2 =  editTextRow3_verb1.getText().toString().toLowerCase() + "-" +
                            editTextRow3_verb2.getText().toString().toLowerCase();
                }

                if(row1_verb1_2 != null && row2_verb1_2 != null && row3_verb1_2 != null){
                    cadenaEscritaPorUsuario.add(row1_verb1_2);
                    cadenaEscritaPorUsuario.add(row2_verb1_2);
                    cadenaEscritaPorUsuario.add(row3_verb1_2);

                    String respuesta = verboBind.buscarCadenaDeUsuarioEnMapa(cadenaEscritaPorUsuario,
                            listaDeVerbosEnEsp.get(indice), isSoloEscrituraOn);

                    if(respuesta.contains(getString(R.string.right))){
                        contadorParaFelicitacion++;
                    } else if(respuesta.contains(getString(R.string.wrong))){
                        contadorParaFelicitacion = 0;
                    }

                    if(contadorParaFelicitacion == 7){
                        Toast.makeText(PartidaIverbsActivity.this, getString(R.string.siete_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 15){
                        Toast.makeText(PartidaIverbsActivity.this, getString(R.string.quince_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 20){
                        Toast.makeText(PartidaIverbsActivity.this, getString(R.string.veinte_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 35){
                        Toast.makeText(PartidaIverbsActivity.this, getString(R.string.treinta_y_cinco_correctas), Toast.LENGTH_SHORT).show();
                    }

                    cadenaEscritaPorUsuario.clear();

                    toast.setText(respuesta);
                    toast.show();

                    limpiarEditText();

                    indice++;

                    if(indice >= cantidadDeVerbosNumerica){
                        registrarResultadosEnHistorial(cantidadDeVerbosNumerica);
                        contadorParaFelicitacion = 0;
                        if(verboBind.getListaDeVerbosEnEspMalRespondidos().size() > 0){
                            mostrarDialogRecomendacion();
                        } else{
                            mostrarDialogContinuacion();
                        }
                    } else{
                        verboEnEspActual = listaDeVerbosEnEsp.get(indice);
                        textViewVerboEnEsp.setText(verboEnEspActual.getNombreDeVerbo().toUpperCase());
                        editTextRow1_verb1.requestFocus();

                        if(verboEnEspActual.getVERBO_DESDE_APP().equals(Boolean.FALSE)){
                            imageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
                            textViewDeImageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
                        } else{
                            imageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
                            textViewDeImageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
                        }

                        cambiarEstrella(verboEnEspActual);

                        contadorParaToast++;

                        if(contadorParaToast == 3){
                            toast = Toast.makeText(PartidaIverbsActivity.this, "", Toast.LENGTH_SHORT);
                            contadorParaToast = 0;
                        }
                    }
                }

                if(isModoContdownActivado && indice < cantidadDeVerbosNumerica && isPermitido){
                    if(indice == cantidadDeVerbosNumerica - 1){
                        buttonVerificar.setEnabled(false);
                    }
                    esperarParaAvanzarAlSiguiente();
                }
            }
        });

        setEntornoPronunciacionOn();
    }

    public void esperarParaAvanzarAlSiguiente(){
        hiloActual = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isTiempoTerminado = Boolean.FALSE;
                    Thread.sleep(segundosParaContdown * 1000);
                    isTiempoTerminado = Boolean.TRUE;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Dentro de este 2do hilo se crea otro hilo que se comunicará con el hilo de la interfaz.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(isTiempoTerminado){
                            buttonVerificar.performClick();
                        }
                    }
                });
            }
        });
        hiloActual.start();
    }

    public void setEntornoPronunciacionOn(){
        imageViewPronunciacionOn.setVisibility(View.INVISIBLE);
        textViewPronunciacionOn.setVisibility(View.INVISIBLE);
        imageViewPronunciacionOff.setVisibility(View.VISIBLE);
        textViewPronunciacionOff.setVisibility(View.VISIBLE);

        editTextRow1_verb1.setNextFocusDownId(R.id.editTextRow2_verb1);
        editTextRow2_verb1.setNextFocusDownId(R.id.editTextRow3_verb1);
        editTextRow3_verb1.setNextFocusDownId(R.id.editTextRow1_verb1);

        ViewGroup.LayoutParams lpEditTextRow1_verb2 = editTextRow1_verb2.getLayoutParams();
        ViewGroup.LayoutParams lpEditTextRow2_verb2 = editTextRow2_verb2.getLayoutParams();
        ViewGroup.LayoutParams lpEditTextRow3_verb2 = editTextRow3_verb2.getLayoutParams();

        editTextRow1_verb2WidthSoloEscrituraOff = lpEditTextRow1_verb2.width;
        editTextRow2_verb2WidthSoloEscrituraOff = lpEditTextRow2_verb2.width;
        editTextRow3_verb2WidthSoloEscrituraOff = lpEditTextRow3_verb2.width;

        lpEditTextRow1_verb2.width=0;
        lpEditTextRow2_verb2.width=0;
        lpEditTextRow3_verb2.width=0;

        editTextRow1_verb2.setLayoutParams(lpEditTextRow1_verb2);
        editTextRow2_verb2.setLayoutParams(lpEditTextRow2_verb2);
        editTextRow3_verb2.setLayoutParams(lpEditTextRow3_verb2);

        ViewGroup.LayoutParams lpSeparador1 = textViewSeparador.getLayoutParams();
        ViewGroup.LayoutParams lpSeparador2 = textViewSeparador2.getLayoutParams();
        ViewGroup.LayoutParams lpSeparador3 = textViewSeparador3.getLayoutParams();

        txtSeparadorWidthSoloEscrituraOff1 = lpSeparador1.width;
        txtSeparadorWidthSoloEscrituraOff2 = lpSeparador2.width;
        txtSeparadorWidthSoloEscrituraOff3 = lpSeparador3.width;

        lpSeparador1.width=0;
        lpSeparador2.width=0;
        lpSeparador3.width=0;

        textViewSeparador.setLayoutParams(lpSeparador1);
        textViewSeparador2.setLayoutParams(lpSeparador2);
        textViewSeparador3.setLayoutParams(lpSeparador3);

        isSoloEscrituraOn = Boolean.TRUE;
    }

    public void setEntornoPronunciacionOff(){
        imageViewPronunciacionOn.setVisibility(View.VISIBLE);
        textViewPronunciacionOn.setVisibility(View.VISIBLE);
        imageViewPronunciacionOff.setVisibility(View.INVISIBLE);
        textViewPronunciacionOff.setVisibility(View.INVISIBLE);

        editTextRow1_verb1.setNextFocusDownId(R.id.editTextRow1_verb2);
        editTextRow2_verb1.setNextFocusDownId(R.id.editTextRow2_verb2);
        editTextRow3_verb1.setNextFocusDownId(R.id.editTextRow3_verb2);

        ViewGroup.LayoutParams lpEditTextRow1_verb2 = editTextRow1_verb2.getLayoutParams();
        ViewGroup.LayoutParams lpEditTextRow2_verb2 = editTextRow2_verb2.getLayoutParams();
        ViewGroup.LayoutParams lpEditTextRow3_verb2 = editTextRow3_verb2.getLayoutParams();

        lpEditTextRow1_verb2.width = editTextRow1_verb2WidthSoloEscrituraOff;
        lpEditTextRow2_verb2.width = editTextRow2_verb2WidthSoloEscrituraOff;
        lpEditTextRow3_verb2.width = editTextRow3_verb2WidthSoloEscrituraOff;

        editTextRow1_verb2.setLayoutParams(lpEditTextRow1_verb2);
        editTextRow2_verb2.setLayoutParams(lpEditTextRow2_verb2);
        editTextRow3_verb2.setLayoutParams(lpEditTextRow3_verb2);

        ViewGroup.LayoutParams lpSeparador1 = textViewSeparador.getLayoutParams();
        ViewGroup.LayoutParams lpSeparador2 = textViewSeparador2.getLayoutParams();
        ViewGroup.LayoutParams lpSeparador3 = textViewSeparador3.getLayoutParams();

        lpSeparador1.width=txtSeparadorWidthSoloEscrituraOff1;
        lpSeparador2.width=txtSeparadorWidthSoloEscrituraOff2;
        lpSeparador3.width=txtSeparadorWidthSoloEscrituraOff3;

        textViewSeparador.setLayoutParams(lpSeparador1);
        textViewSeparador2.setLayoutParams(lpSeparador2);
        textViewSeparador3.setLayoutParams(lpSeparador3);

        isSoloEscrituraOn = Boolean.FALSE;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(verboEnEspActual != null){
            cambiarEstrella(verboEnEspActual);
        }

        isPermitido = Boolean.TRUE;

        if(isModoContdownActivado && indice < cantidadDeVerbosNumerica && isPermitido){
            esperarParaAvanzarAlSiguiente();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isPermitido = Boolean.FALSE;
        if(hiloActual != null){
            hiloActual.interrupt();
        }
    }

    public void cambiarEstrella(VerboEnEsp verboEnEspActual){
        if(verboEnEspActual.getMarkedWithStar()){
            imageViewStarFromPartidaIverbsActivity.setBackgroundResource(R.mipmap.ic_full_star);
        } else{
            imageViewStarFromPartidaIverbsActivity.setBackgroundResource(R.mipmap.ic_void_star);
        }
    }



    private void registrarResultadosEnHistorial(int cantidadDeVerbosNumerica) {
        int porcentajeAciertos = (verboBind.getCantidadDeRespuestasCorrectas() * 100) / cantidadDeVerbosNumerica;
        int porcentajeIncorrectas = (verboBind.getListaDeVerbosEnEspMalRespondidos().size() * 100) / cantidadDeVerbosNumerica;

        HistorialDePartidaBean historialDePartidaBean =
                new HistorialDePartidaBean(cantidadDeVerbosNumerica, porcentajeAciertos, porcentajeIncorrectas);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(historialDePartidaBean);
        realm.commitTransaction();
    }

    private void mostrarDialogContdown(final ImageView imageViewClock, final ImageView imageViewClockOn) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewDialogContdown = inflater.inflate(R.layout.dialog_contdown, null);

        final EditText editTextSegundos = (EditText) viewDialogContdown.findViewById(R.id.editTextSegundos);

        final AlertDialog dialogContdown = new AlertDialog.Builder(this)
                .setView(viewDialogContdown)
                .setPositiveButton(getString(R.string.aceptar), null)
                .setNegativeButton(getString(R.string.cancelar), null)
                .setCancelable(false).create();

        dialogContdown.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) dialogContdown).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNegative = ((AlertDialog) dialogContdown).getButton(AlertDialog.BUTTON_NEGATIVE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer segundos = null;
                        String cadenaSegundos = editTextSegundos.getText().toString();

                        if(cadenaSegundos != null && cadenaSegundos.isEmpty() == false){
                            segundos = Integer.valueOf(editTextSegundos.getText().toString());

                            if(segundos >= 5 && segundos <= 25 ){
                                segundosParaContdown = segundos;
                                dialogContdown.dismiss();
                                toast.setText(getString(R.string.modo_contdown_activado));
                                toast.show();

                                ViewGroup.LayoutParams lpImageViewClock = imageViewClock.getLayoutParams();
                                widthImageViewClock = lpImageViewClock.width;
                                lpImageViewClock.width = 0;
                                imageViewClock.setLayoutParams(lpImageViewClock);

                                imageViewClockOn.setVisibility(View.VISIBLE);

                                isModoContdownActivado = Boolean.TRUE;
                            } else{
                                dialogContdown.show();
                                toast.setText("Min: 5\" - Max: 25\"");
                                toast.show();
                            }
                        } else{
                            dialogContdown.show();
                            toast.setText(getString(R.string.completar_campo));
                            toast.show();
                        }
                    }
                });

                buttonNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogContdown.dismiss();
                    }
                });
            }
        });

        dialogContdown.show();
    }

    public void mostrarDialogCantidadDeVerbos() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewDialogDesign = inflater.inflate(R.layout.dialog_design, null);

        final EditText editTextCantidad = (EditText) viewDialogDesign.findViewById(R.id.editTextCantidad);
        final TextView textViewCantidadParte2 = (TextView) viewDialogDesign.findViewById(R.id.textViewCantidadParte2);
        final TextView textViewCantidadFavoritosParte2 = (TextView) viewDialogDesign.findViewById(R.id.textViewCantidadFavoritosParte2);

        final ImageView imageViewClock = (ImageView) viewDialogDesign.findViewById(R.id.imageViewClock);
        final ImageView imageViewClockOn = (ImageView) viewDialogDesign.findViewById(R.id.imageViewClockOn);

        isModoContdownActivado = Boolean.FALSE;

        textViewCantidadParte2.setText(verboBind.getCantidadDeVerbosEnEsp().toString());
        textViewCantidadFavoritosParte2.setText(verboBind.getCantidadDeVerbosEnEspFavoritos().toString());

        imageViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogContdown(imageViewClock, imageViewClockOn);
            }
        });

        imageViewClockOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams lpImageViewClock = imageViewClock.getLayoutParams();
                lpImageViewClock.width = widthImageViewClock;
                imageViewClock.setLayoutParams(lpImageViewClock);

                imageViewClockOn.setVisibility(View.INVISIBLE);

                isModoContdownActivado = Boolean.FALSE;

                toast.setText(getString(R.string.modo_contdown_desactivado));
                toast.show();

            }
        });

        final AlertDialog dialogCantidadDeVerbos = new AlertDialog.Builder(this)
                .setView(viewDialogDesign)
                .setPositiveButton(getString(R.string.cargar), null)
                .setNegativeButton(getString(R.string.cancelar), null)
                .setCancelable(false).create();

        dialogCantidadDeVerbos.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button buttonPositive = ((AlertDialog) dialogCantidadDeVerbos).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNegative = ((AlertDialog) dialogCantidadDeVerbos).getButton(AlertDialog.BUTTON_NEGATIVE);

                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton radioButtonConTodos = (RadioButton) viewDialogDesign.findViewById(R.id.radioButtonConTodos);

                        int CANTIDAD_MAXIMA_VERBOS = verboBind.darSizeDeListaTotalDeVerbosEnEsp(radioButtonConTodos.isChecked());

                        if("".equals(editTextCantidad.getText().toString())){
                            toast.setText(getString(R.string.numero_valido));
                            toast.show();
                            dialogCantidadDeVerbos.show();

                        } else{
                            cantidadDeVerbosNumerica = Integer.valueOf(editTextCantidad.getText().toString());

                            if(CANTIDAD_MAXIMA_VERBOS > 0){
                                if(cantidadDeVerbosNumerica > 0 && cantidadDeVerbosNumerica <= CANTIDAD_MAXIMA_VERBOS){
                                    mainLinerLayout.setVisibility(View.VISIBLE);
                                    buttonVerificar.setVisibility(View.VISIBLE);

                                    for(int i=0; i<cantidadDeVerbosNumerica; i++ ){
                                        listaDeVerbosEnEsp.add(verboBind.generarVerboRandom(radioButtonConTodos.isChecked()));
                                    }

                                    verboEnEspActual = listaDeVerbosEnEsp.get(indice);

                                    cambiarEstrella(verboEnEspActual);

                                    textViewVerboEnEsp.setText(verboEnEspActual.getNombreDeVerbo().toUpperCase());

                                    if(verboEnEspActual.getVERBO_DESDE_APP().equals(Boolean.FALSE)){
                                        imageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
                                        textViewDeImageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
                                    } else{
                                        imageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
                                        textViewDeImageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
                                    }

                                    dialogCantidadDeVerbos.dismiss();
                                } else{
                                    String verboOVerbos;
                                    if(CANTIDAD_MAXIMA_VERBOS > 1){
                                        verboOVerbos = getString(R.string.verbos);
                                    } else{
                                        verboOVerbos = getString(R.string.verbo_singular);
                                    }
                                    toast.setText(getString(R.string.maximo_de)+ CANTIDAD_MAXIMA_VERBOS + verboOVerbos);
                                    toast.show();
                                    dialogCantidadDeVerbos.show();
                                }
                            } else{
                                toast.setText(getString(R.string.no_hay_verbos));
                                toast.show();
                                dialogCantidadDeVerbos.show();
                            }
                        }

                        if(isModoContdownActivado){
                            if(indice == cantidadDeVerbosNumerica - 1){
                                buttonVerificar.setEnabled(false);
                            }
                            esperarParaAvanzarAlSiguiente();
                        }
                    }
                });

                buttonNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogCantidadDeVerbos.dismiss();
                        finish();
                    }
                });
            }
        });

        dialogCantidadDeVerbos.show();
    }

    private void mostrarDialogEjemplo() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewDialogExample = inflater.inflate(R.layout.dialog_show_example, null);

        final AlertDialog dialogExample = new AlertDialog.Builder(this)
                .setView(viewDialogExample)
                .setCancelable(true).create();

        dialogExample.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogExample.show();
    }

    private void mostrarDialogRecomendacion() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewDialogResultados = inflater.inflate(R.layout.dialog_resultados, null);

        ImageView imageViewShareScore = (ImageView) viewDialogResultados.findViewById(R.id.imageViewShareScore);
        TextView textViewResultados = (TextView) viewDialogResultados.findViewById(R.id.textViewResultados);
        TextView textViewPregunta = (TextView) viewDialogResultados.findViewById(R.id.textViewPregunta);

        final String mensaje = getString(R.string.puntaje) + mostrarResultadoFinal();

        imageViewShareScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PartidaIverbsActivity.this, getString(R.string.puntaje_msj), Toast.LENGTH_SHORT).show();

                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, mensaje);

                try {
                    whatsappIntent.setPackage("com.whatsapp");
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Si whatsapp no está instalado
                    startActivity(whatsappIntent);
                }

            }
        });

        textViewResultados.setText(mostrarResultadoFinal());
        textViewPregunta.setText(getString(R.string.verbos_incorrectos));

        new AlertDialog.Builder(this)
                .setView(viewDialogResultados)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        practicarDesdeVerbosIncorrectos();
                        editTextRow1_verb1.requestFocus();
                        cambiarEstrella(verboEnEspActual);
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><
//                        finishAndRemoveTask();
                        finish();
                    }
                }).setCancelable(false).show();
    }

    private void mostrarDialogContinuacion() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewDialogResultados = inflater.inflate(R.layout.dialog_resultados, null);

        ImageView imageViewShareScore = (ImageView) viewDialogResultados.findViewById(R.id.imageViewShareScore);
        TextView textViewResultados = (TextView) viewDialogResultados.findViewById(R.id.textViewResultados);
        TextView textViewPregunta = (TextView) viewDialogResultados.findViewById(R.id.textViewPregunta);

        final String mensaje = getString(R.string.puntaje) + mostrarResultadoFinal();

        imageViewShareScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PartidaIverbsActivity.this, getString(R.string.puntaje_msj), Toast.LENGTH_SHORT).show();

                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, mensaje);

                try {
                    whatsappIntent.setPackage("com.whatsapp");
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();
                    // Si whatsapp no está instalado
//                    startActivity(whatsappIntent);
                }

            }
        });

        textViewResultados.setText(mostrarResultadoFinal());
        textViewPregunta.setText(getString(R.string.iniciar_partida));

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(viewDialogResultados)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><
//                        finishAndRemoveTask();
                        finish();
                    }
                }).setCancelable(false).show();
    }

    private void limpiarEditText() {
        editTextRow1_verb1.setText("");
        editTextRow1_verb2.setText("");
        editTextRow2_verb1.setText("");
        editTextRow2_verb2.setText("");
        editTextRow3_verb1.setText("");
        editTextRow3_verb2.setText("");
    }

    private String mostrarResultadoFinal(){
        Integer porcentajeAciertos = (verboBind.getCantidadDeRespuestasCorrectas() * 100) / cantidadDeVerbosNumerica;
        Integer porcentajeIncorrectas = (verboBind.getListaDeVerbosEnEspMalRespondidos().size() * 100) / cantidadDeVerbosNumerica;

        String correctas = getString(R.string.correctas) + verboBind.getCantidadDeRespuestasCorrectas() + " " + porcentajeAciertos +  "%";
        String incorrectas = getString(R.string.incorrectas) + verboBind.getListaDeVerbosEnEspMalRespondidos().size() + " " + porcentajeIncorrectas +  "%";

        return correctas + "\n" + incorrectas;
    }

    public void practicarDesdeVerbosIncorrectos(){
        verboBind.setCantidadDeRespuestasCorrectas(0);
        indice = 0;
        cantidadDeVerbosNumerica = verboBind.getListaDeVerbosEnEspMalRespondidos().size();

        listaDeVerbosEnEsp.clear();

        this.aramarListaAleatoria(listaDeVerbosEnEsp, verboBind.getListaDeVerbosEnEspMalRespondidos());

        verboEnEspActual = listaDeVerbosEnEsp.get(indice);

        cambiarEstrella(verboEnEspActual);

        if(verboEnEspActual.getVERBO_DESDE_APP().equals(Boolean.FALSE)){
            imageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
            textViewDeImageViewVerboAgregadoLabel.setVisibility(View.VISIBLE);
        } else{
            imageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
            textViewDeImageViewVerboAgregadoLabel.setVisibility(View.INVISIBLE);
        }

        textViewVerboEnEsp.setText(listaDeVerbosEnEsp.get(indice).getNombreDeVerbo().toUpperCase());

        if(isModoContdownActivado && indice < cantidadDeVerbosNumerica){
            if(indice == cantidadDeVerbosNumerica - 1){
                buttonVerificar.setEnabled(false);
            }
            esperarParaAvanzarAlSiguiente();
        }
    }

    public void aramarListaAleatoria(List<VerboEnEsp> listaConNuevoOrden, List<VerboEnEsp> lista){
        if(lista.size() == 1){
            listaConNuevoOrden.add(lista.get(0));
        } else{
            Random generarRandom = new Random( System.currentTimeMillis() );
            int contador = 0;
            int indiceDeListaAleatorio;
            Map<Integer, Integer> mapaDeIndices = new HashMap<Integer, Integer>();

            while(contador < lista.size()){
                indiceDeListaAleatorio = generarRandom.nextInt( lista.size() );
                if(mapaDeIndices.containsValue(indiceDeListaAleatorio) == false){
                    listaConNuevoOrden.add(lista.get(indiceDeListaAleatorio));
                    mapaDeIndices.put(indiceDeListaAleatorio, indiceDeListaAleatorio);
                    contador++;
                }
            }
        }
    }





    @Override
    public void onBackPressed() {
        dialogBackButton.show();
    }


}
