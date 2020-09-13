package com.binary_winters.projects.iverbs.situaciones.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.ContenidoEnEspGenericoHelper;
import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.situaciones.SituacionGenericaBind;
import com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;

public class PracticarSituacionGenericaActivity extends AppCompatActivity {
    private String tipoDeSituacionGenerica;

    private Integer cantidadDePalabrasNumerica;

    static SituacionGenericaBind situacionGenericaBind;

    private Boolean isSoloEscrituraOn = Boolean.FALSE;

    private TextView textViewPalabraParaPracticar;
    private EditText editTextPalabraEnIng1_1;
    private EditText editTextPalabraEnIng1_2;

    private TextView textViewSeparador;

    private Button buttonSoundParaPalabra;

    private ImageView imageViewPronunciacionOn;
    private ImageView imageViewPronunciacionOff;
    private TextView textViewPronunciacionOn;
    private TextView textViewPronunciacionOff;

    private int txtSeparadorWidthSoloEscrituraOff;
    private int editTextPalabraEnIng1_2WidthSoloEscrituraOff;

    private Button buttonVerificarPalabra;

    private int indice = 0;

    private List<ContenidoEnEspGenericoHelper> palabrasEnEspHelper;

    private ContenidoEnEspGenericoHelper palabraEnEspHelperActual;

    private ImageView imageViewStarFromPalabras;

    private Realm realm;

    private Toast toast;

    int contadorParaToast;

    private AlertDialog dialogBackButton;

    private Integer contadorParaFelicitacion = 0;

    private Thread hiloActual;
    private int widthImageViewClock;
    private Integer segundosParaContdown;
    private Boolean isModoContdownActivado = Boolean.FALSE;
    private Boolean isTiempoTerminado = Boolean.FALSE;
    // Sirve para bloquear la cuenta en casos en que se vaya a otro activity.
    private Boolean isPermitido = Boolean.TRUE;

    private RelativeLayout relativeLayoutContenidoPracticarMisPalabrasActivity;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar_mis_palabras);

        Bundle bundle = getIntent().getExtras();
        tipoDeSituacionGenerica = bundle.getString("key_descripcion_situacion");

        toast = Toast.makeText(PracticarSituacionGenericaActivity.this, "", Toast.LENGTH_LONG);

        relativeLayoutContenidoPracticarMisPalabrasActivity = (RelativeLayout) findViewById(R.id.relativeLayoutContenidoPracticarMisPalabrasActivity);

        dialogBackButton  = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.abandonar))
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        isPermitido = Boolean.FALSE;
                        if(hiloActual != null){
                            hiloActual.interrupt();
                        }
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null).setCancelable(false).create();

        situacionGenericaBind = new SituacionGenericaBind(tipoDeSituacionGenerica);

        mostrarDialogCantidadDePalabras();

        realm = Realm.getDefaultInstance();

        textViewPalabraParaPracticar = (TextView) findViewById(R.id.textViewPalabraParaPracticar);
        editTextPalabraEnIng1_1 = (EditText) findViewById(R.id.editTextPalabraEnIng1_1);
        editTextPalabraEnIng1_2 = (EditText) findViewById(R.id.editTextPalabraEnIng1_2);

        editTextPalabraEnIng1_1.requestFocus();
        editTextPalabraEnIng1_1.setNextFocusDownId(R.id.editTextPalabraEnIng1_2);
        editTextPalabraEnIng1_2.setNextFocusDownId(R.id.editTextPalabraEnIng1_1);

        textViewSeparador = (TextView) findViewById(R.id.textViewSeparador);

        imageViewPronunciacionOn = (ImageView) findViewById(R.id.imageViewPronunciacionOn);
        imageViewPronunciacionOff = (ImageView) findViewById(R.id.imageViewPronunciacionOff);
        textViewPronunciacionOn = (TextView) findViewById(R.id.textViewPronunciacionOn);
        textViewPronunciacionOff = (TextView) findViewById(R.id.textViewPronunciacionOff);

        buttonSoundParaPalabra = (Button) findViewById(R.id.buttonSoundParaPalabra);

        buttonVerificarPalabra = (Button) findViewById(R.id.buttonVerificarPalabra);

        imageViewStarFromPalabras = (ImageView) findViewById(R.id.imageViewStarFromPalabras);

        palabrasEnEspHelper = new ArrayList<>();

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

        buttonSoundParaPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.release();
                    mp = null;
                }

                SituacionGenerica situacionGenericaHallada = situacionGenericaBind.buscarSituacionGenericaSegunNombrePalabraEnEsp(palabrasEnEspHelper.get(indice).getPalabraEnEsp());

                String[] divisiones = situacionGenericaHallada.getCadenaEnIngles().split("-");
                String palabraEnIngles = divisiones[0];

                palabraEnIngles = palabraEnIngles.replace(" ", "_");
                palabraEnIngles = palabraEnIngles.replace("?", "");
                palabraEnIngles = palabraEnIngles.replace("'", "");
                palabraEnIngles = palabraEnIngles.replace(",", "");
                palabraEnIngles = palabraEnIngles.replace("/", "_");

                mp = SituacionGenericaBind.cargarAudioSegunVerbo(PracticarSituacionGenericaActivity.this, mp, palabraEnIngles);

            }
        });

        imageViewStarFromPalabras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(palabraEnEspHelperActual != null){
                    if(palabraEnEspHelperActual.getMarkedWithStar()){
                        palabraEnEspHelperActual.setMarkedWithStar(Boolean.FALSE);
                    } else{
                        palabraEnEspHelperActual.setMarkedWithStar(Boolean.TRUE);
                        toast.setText(getString(R.string.agregado_a_favoritos));
                        toast.show();
                    }
                    cambiarEstrella(palabraEnEspHelperActual);

                    realm.beginTransaction();
                    SituacionGenerica palabraFromDB = realm.where(SituacionGenerica.class).equalTo("id", palabraEnEspHelperActual.getId()).findFirst();
                    palabraFromDB.setMarkedWithStar(palabraEnEspHelperActual.getMarkedWithStar());
                    realm.copyToRealmOrUpdate(palabraFromDB);
                    realm.commitTransaction();
                }
            }
        });

        buttonVerificarPalabra.setOnClickListener(new View.OnClickListener() {
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
                if(isSoloEscrituraOn){
                    row1_verb1_2 =  editTextPalabraEnIng1_1.getText().toString().toLowerCase();
                } else{
                    row1_verb1_2 =  editTextPalabraEnIng1_1.getText().toString().toLowerCase() + "-" +
                            editTextPalabraEnIng1_2.getText().toString().toLowerCase();
                }

                if(row1_verb1_2 != null){
                    String respuesta = situacionGenericaBind.buscarCadenaDeUsuarioEnMapa(row1_verb1_2, palabrasEnEspHelper.get(indice), isSoloEscrituraOn);

                    if(respuesta.contains(getString(R.string.right))){
                        contadorParaFelicitacion++;
                    } else if(respuesta.contains(getString(R.string.wrong))){
                        contadorParaFelicitacion = 0;
                    }

                    if(contadorParaFelicitacion == 7){
                        Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.siete_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 15){
                        Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.quince_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 20){
                        Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.veinte_correctas), Toast.LENGTH_SHORT).show();
                    } else if(contadorParaFelicitacion == 35){
                        Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.treinta_y_cinco_correctas), Toast.LENGTH_SHORT).show();
                    }

                    toast.setText(respuesta);
                    toast.show();

                    limpiarEditText();

                    indice++;

                    if(indice >= cantidadDePalabrasNumerica){
                        contadorParaFelicitacion = 0;
                        if(situacionGenericaBind.getListaDePalabrasEnEspMalRespondidas().size() > 0){
                            mostrarDialogRecomendacion();
                        } else{
                            mostrarDialogContinuacion();
                        }
                    } else{
                        palabraEnEspHelperActual = palabrasEnEspHelper.get(indice);
                        textViewPalabraParaPracticar.setText(palabrasEnEspHelper.get(indice).getPalabraEnEsp().toUpperCase());
                        cambiarEstrella(palabraEnEspHelperActual);
                        editTextPalabraEnIng1_1.requestFocus();
                        contadorParaToast++;

                        if(contadorParaToast == 3){
                            toast = Toast.makeText(PracticarSituacionGenericaActivity.this, "", Toast.LENGTH_SHORT);
                            contadorParaToast = 0;
                        }
                    }
                }

                if(isModoContdownActivado && indice < cantidadDePalabrasNumerica && isPermitido){
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
                            buttonVerificarPalabra.performClick();
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

        ViewGroup.LayoutParams lpEditTextPalabraEnIng1_2 = editTextPalabraEnIng1_2.getLayoutParams();

        editTextPalabraEnIng1_2WidthSoloEscrituraOff = lpEditTextPalabraEnIng1_2.width;

        lpEditTextPalabraEnIng1_2.width=0;

        editTextPalabraEnIng1_2.setLayoutParams(lpEditTextPalabraEnIng1_2);

        ViewGroup.LayoutParams lpSeparador = textViewSeparador.getLayoutParams();

        txtSeparadorWidthSoloEscrituraOff = lpSeparador.width;

        lpSeparador.width=0;

        textViewSeparador.setLayoutParams(lpSeparador);

        isSoloEscrituraOn = Boolean.TRUE;
    }

    public void setEntornoPronunciacionOff(){
        imageViewPronunciacionOff.setVisibility(View.INVISIBLE);
        textViewPronunciacionOff.setVisibility(View.INVISIBLE);
        imageViewPronunciacionOn.setVisibility(View.VISIBLE);
        textViewPronunciacionOn.setVisibility(View.VISIBLE);

        ViewGroup.LayoutParams lpEditTextPalabraEnIng1_2 = editTextPalabraEnIng1_2.getLayoutParams();

        lpEditTextPalabraEnIng1_2.width = editTextPalabraEnIng1_2WidthSoloEscrituraOff;

        editTextPalabraEnIng1_2.setLayoutParams(lpEditTextPalabraEnIng1_2);

        ViewGroup.LayoutParams lpSeparador = textViewSeparador.getLayoutParams();

        lpSeparador.width = txtSeparadorWidthSoloEscrituraOff;

        textViewSeparador.setLayoutParams(lpSeparador);

        isSoloEscrituraOn = Boolean.FALSE;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(palabraEnEspHelperActual != null){
            cambiarEstrella(palabraEnEspHelperActual);
        }

        isPermitido = Boolean.TRUE;

        if(isModoContdownActivado && indice < cantidadDePalabrasNumerica && isPermitido){
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

    private void limpiarEditText() {
        editTextPalabraEnIng1_1.setText("");
        editTextPalabraEnIng1_2.setText("");
    }

    public void mostrarDialogCantidadDePalabras() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewDialogDesign = inflater.inflate(R.layout.dialog_design_palabras, null);

        final EditText editTextCantidad = (EditText) viewDialogDesign.findViewById(R.id.editTextCantidad);

        final TextView textViewCantidadParte2 = (TextView) viewDialogDesign.findViewById(R.id.textViewCantidadParte2);
        final TextView textViewCantidadFavoritosParte2 = (TextView) viewDialogDesign.findViewById(R.id.textViewCantidadFavoritosParte2);

        final ImageView imageViewClock = (ImageView) viewDialogDesign.findViewById(R.id.imageViewClock);
        final ImageView imageViewClockOn = (ImageView) viewDialogDesign.findViewById(R.id.imageViewClockOn);

        isModoContdownActivado = Boolean.FALSE;

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

        textViewCantidadParte2.setText(situacionGenericaBind.getCantidadPalabras().toString());
        textViewCantidadFavoritosParte2.setText(situacionGenericaBind.getCantidadPalabrasFavoritas().toString());

        final AlertDialog dialogCantidadDePalabras = new AlertDialog.Builder(this)
                .setView(viewDialogDesign)
                .setPositiveButton(getString(R.string.cargar), null)
                .setNegativeButton(getString(R.string.cancelar), null)
                .setCancelable(false).create();

        dialogCantidadDePalabras.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button buttonPositive = ((AlertDialog) dialogCantidadDePalabras).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNegative = ((AlertDialog) dialogCantidadDePalabras).getButton(AlertDialog.BUTTON_NEGATIVE);

                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton radioButtonConTodos = (RadioButton) viewDialogDesign.findViewById(R.id.radioButtonConTodos);
                        if("".equals(editTextCantidad.getText().toString())){
                            toast.setText(getString(R.string.numero_valido));
                            toast.show();
                            dialogCantidadDePalabras.show();
                        } else{
                            cantidadDePalabrasNumerica = Integer.valueOf(editTextCantidad.getText().toString());
                            int CANTIDAD_MAXIMA_PALABRAS = situacionGenericaBind.darSizeDeListaTotalDePalabrasEnEsp(radioButtonConTodos.isChecked());
                            if(CANTIDAD_MAXIMA_PALABRAS > 0){
                                if(cantidadDePalabrasNumerica > 0 && cantidadDePalabrasNumerica <= CANTIDAD_MAXIMA_PALABRAS){
                                    relativeLayoutContenidoPracticarMisPalabrasActivity.setVisibility(View.VISIBLE);
                                    for(int i=0; i<cantidadDePalabrasNumerica; i++ ){
                                        palabrasEnEspHelper.add(situacionGenericaBind.generarPalabraRandom(radioButtonConTodos.isChecked()));
                                    }

                                    palabraEnEspHelperActual = palabrasEnEspHelper.get(indice);

                                    cambiarEstrella(palabraEnEspHelperActual);

                                    textViewPalabraParaPracticar.setText(palabraEnEspHelperActual.getPalabraEnEsp().toUpperCase());

                                    dialogCantidadDePalabras.dismiss();
                                } else{
                                    String palabraOPalabras;
                                    if(CANTIDAD_MAXIMA_PALABRAS > 1){
                                        palabraOPalabras = getString(R.string.palabras);
                                    } else{
                                        palabraOPalabras = getString(R.string.palabra);
                                    }

                                    toast.setText(getString(R.string.maximo_de)+ CANTIDAD_MAXIMA_PALABRAS + palabraOPalabras);
                                    toast.show();
                                    dialogCantidadDePalabras.show();
                                }
                            } else{
                                toast.setText(getString(R.string.no_hay_palabras_favoritas));
                                toast.show();
                                dialogCantidadDePalabras.show();
                            }
                        }

                        if(isModoContdownActivado){
                            esperarParaAvanzarAlSiguiente();
                        }
                    }
                });

                buttonNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });

        dialogCantidadDePalabras.show();
    }

    public void cambiarEstrella(ContenidoEnEspGenericoHelper palabra){
        if(palabra.getMarkedWithStar()){
            imageViewStarFromPalabras.setBackgroundResource(R.mipmap.ic_full_star);
        } else{
            imageViewStarFromPalabras.setBackgroundResource(R.mipmap.ic_void_star);
        }
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
                Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.puntaje_msj), Toast.LENGTH_SHORT).show();

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
        textViewPregunta.setText(getString(R.string.practicar_incorrectas));

        new AlertDialog.Builder(this)
                .setView(viewDialogResultados)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        practicarDesdePalabrasIncorrectas();
                        editTextPalabraEnIng1_1.requestFocus();
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                Toast.makeText(PracticarSituacionGenericaActivity.this, getString(R.string.puntaje_msj), Toast.LENGTH_SHORT).show();

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
                        finish();
                    }
                }).setCancelable(false).show();
    }

    private String mostrarResultadoFinal(){
        Integer porcentajeAciertos = (situacionGenericaBind.getCantidadDeRespuestasCorrectas() * 100) / cantidadDePalabrasNumerica;
        Integer porcentajeIncorrectas = (situacionGenericaBind.getListaDePalabrasEnEspMalRespondidas().size() * 100) / cantidadDePalabrasNumerica;

        String correctas = getString(R.string.correctas) + situacionGenericaBind.getCantidadDeRespuestasCorrectas() + " " + porcentajeAciertos +  "%";
        String incorrectas = getString(R.string.incorrectas) + situacionGenericaBind.getListaDePalabrasEnEspMalRespondidas().size() + " " + porcentajeIncorrectas +  "%";

        return correctas + "\n" + incorrectas;
    }

    public void practicarDesdePalabrasIncorrectas(){
        situacionGenericaBind.setCantidadDeRespuestasCorrectas(0);
        indice = 0;
        cantidadDePalabrasNumerica = situacionGenericaBind.getListaDePalabrasEnEspMalRespondidas().size();

        palabrasEnEspHelper.clear();

        this.aramarListaAleatoria(palabrasEnEspHelper, situacionGenericaBind.getListaDePalabrasEnEspMalRespondidas());

        palabraEnEspHelperActual = palabrasEnEspHelper.get(indice);

        textViewPalabraParaPracticar.setText(palabraEnEspHelperActual.getPalabraEnEsp().toUpperCase());

        cambiarEstrella(palabraEnEspHelperActual);

        if(isModoContdownActivado && indice < cantidadDePalabrasNumerica){
            esperarParaAvanzarAlSiguiente();
        }
    }

    public void aramarListaAleatoria(List<ContenidoEnEspGenericoHelper> listaConNuevoOrden, List<ContenidoEnEspGenericoHelper> lista){
        if(lista.size() == 1){
            listaConNuevoOrden.add(lista.get(0));
        } else{
            Random generarRandom = new Random( System.currentTimeMillis() );
            int contador = 0;
            int indiceDeListaAleatorio;
            Map<Integer, Integer> mapaDeIndices = new HashMap<Integer, Integer>();

            while(contador < lista.size()){
                indiceDeListaAleatorio = generarRandom.nextInt(lista.size());
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
