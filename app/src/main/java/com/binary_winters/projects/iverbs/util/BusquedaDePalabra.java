package com.binary_winters.projects.iverbs.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;

/**
 * Created by chipo on 26/05/18.
 */

public abstract class BusquedaDePalabra implements IBusqueda{
    private Context context;
    Toast toastGenerico;

    public BusquedaDePalabra(Context context, Toast toast){
        this.context = context;
        this.toastGenerico = toast;
    }

    public void realizarBusqueda(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewBuscarPalabra = inflater.inflate(R.layout.dialog_buscar_palabra, null);

        final EditText editTextPalabraEnEsp = (EditText) viewBuscarPalabra.findViewById(R.id.editTextPalabraEnEsp);
        final EditText editTextPalabraEnIng = (EditText) viewBuscarPalabra.findViewById(R.id.editTextPalabraEnIng);

        final AlertDialog dialogBuscarPalabra = new AlertDialog.Builder(context)
                .setView(viewBuscarPalabra)
                .setPositiveButton("BUSCAR", null)
                .setNegativeButton("Cancelar", null)
                .setCancelable(false).create();

        dialogBuscarPalabra.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button buttonPositive = ((AlertDialog) dialogBuscarPalabra).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNegative = ((AlertDialog) dialogBuscarPalabra).getButton(AlertDialog.BUTTON_NEGATIVE);

                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String palabraEnEsp = editTextPalabraEnEsp.getText().toString();
                        String palabraEnIng = editTextPalabraEnIng.getText().toString();

                        if(palabraEnEsp != null && palabraEnEsp.isEmpty() == false
                                && IverbsPatterns.validarCadenaEsp(palabraEnEsp)){
                            buscarPalabraEnEsp(palabraEnEsp);
                            dialogBuscarPalabra.dismiss();
                        } else if (IverbsPatterns.validarCadenaEsp(palabraEnEsp) == false
                                && palabraEnIng != null && palabraEnIng.isEmpty()){
                            toastGenerico.setText("Completar con datos válidos");
                            toastGenerico.show();

                            dialogBuscarPalabra.show();
                        }

                        if(palabraEnIng != null && palabraEnIng.isEmpty() == false
                                && IverbsPatterns.validarCadenaEsp(palabraEnIng)){
                            buscarPalabraEnIng(palabraEnIng);
                            dialogBuscarPalabra.dismiss();
                        } else if (IverbsPatterns.validarCadenaEsp(palabraEnIng) == false
                                && palabraEnEsp != null && palabraEnEsp.isEmpty()){
                            toastGenerico.setText("Completar con datos válidos");
                            toastGenerico.show();
                            dialogBuscarPalabra.show();
                        }

                        editTextPalabraEnEsp.setText("");
                        editTextPalabraEnIng.setText("");
                        editTextPalabraEnEsp.requestFocus();
                    }
                });

                buttonNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTextPalabraEnEsp.setText("");
                        editTextPalabraEnIng.setText("");
                        editTextPalabraEnEsp.requestFocus();

                        dialogBuscarPalabra.dismiss();
                    }
                });
            }
        });

        dialogBuscarPalabra.show();

        editTextPalabraEnEsp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    editTextPalabraEnIng.setText("");
                }
            }
        });

        editTextPalabraEnIng.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    editTextPalabraEnEsp.setText("");
                }
            }
        });
    }
}
