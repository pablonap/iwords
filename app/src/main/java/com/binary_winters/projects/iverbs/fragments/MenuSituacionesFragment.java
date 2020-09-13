package com.binary_winters.projects.iverbs.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.binary_winters.projects.iverbs.MyAdapterParaSituaciones;
import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.Situacion;
import com.binary_winters.projects.iverbs.TipoSituacion;
import com.binary_winters.projects.iverbs.activities.HelpActivity;
import com.binary_winters.projects.iverbs.activities.MainActivity;
import com.binary_winters.projects.iverbs.util.ImpExpBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.binary_winters.projects.iverbs.TipoSituacion.MESERO;

public class MenuSituacionesFragment extends Fragment {
    private ImpExpBuilder impExpBuilder;

    private Avanzar avanzar;
    private MainActivity mainActivity;

    private GridView gridView;
    private List<Situacion> listaDeSituaciones;

    private final int PERMISSION_FOR_WRITE_EXTERNAL_STORAGE = 0;
    private final int PERMISO_PARA_EXPORTAR = 1;

    AlertDialog dialogOpcionesDeSituacion = null;

    public MenuSituacionesFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            avanzar = (Avanzar) context;
            mainActivity = (MainActivity) context;
        } catch(Exception ex){
            throw new ClassCastException(context.toString() + " Debe implementar Avanzar");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        avanzar = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_situaciones, container, false);
        setHasOptionsMenu(true);

        gridView = (GridView) view.findViewById(R.id.gridView);

        listaDeSituaciones = new ArrayList<Situacion>();
        listaDeSituaciones.add(new Situacion(MESERO.getDescripcion(), R.mipmap.ic_mesero_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.EN_RESTORAN.getDescripcion(), R.mipmap.ic_restoran_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.EN_AEROPUERTO.getDescripcion(), R.mipmap.ic_airport_situaciones));
        listaDeSituaciones.add(new Situacion(TipoSituacion.RECEPCION_HOTEL.getDescripcion(), R.mipmap.ic_front_desk_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.EN_DOCTOR.getDescripcion(), R.mipmap.ic_en_el_doctor_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), R.mipmap.ic_work_interview_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.SUPERMERCADO.getDescripcion(), R.mipmap.ic_supermercado_situacion));
        listaDeSituaciones.add(new Situacion(TipoSituacion.UBICARSE.getDescripcion(), R.mipmap.ic_ubicarse_situacion));

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
////                Toast.makeText(GridActivity.this, listaDeSituaciones.get(index), Toast.LENGTH_SHORT).show();
//            }
//        });

        MyAdapterParaSituaciones myAdapter = new MyAdapterParaSituaciones(getContext(), R.layout.situaciones_grid_item, listaDeSituaciones,
                new MyAdapterParaSituaciones.OnItemClickListener() {
                    @Override
                    public void click(int position) {
                        mostrarDialogOpcionesDeSituacion(listaDeSituaciones.get(position).getNombreSituacion());
                    }
                });
                gridView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_activity, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_help:
                Intent intent = new Intent(getContext(), HelpActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_exp_backup:
                pedirPermisosParaExp();
                return true;
            case R.id.menu_item_imp_backup:
                pedirPermisos();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void pedirPermisosParaExp(){
        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISO_PARA_EXPORTAR);
        } else{
            try{
                impExpBuilder = new ImpExpBuilder(getContext());
                impExpBuilder.exportarBackup();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void pedirPermisos(){
        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_FOR_WRITE_EXTERNAL_STORAGE);
        } else{
            try{
                mainActivity.performFileSearch();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void mostrarDialogOpcionesDeSituacion(final String descripcionDeSituacion) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View viewDialogDesign = inflater.inflate(R.layout.dialog_situacion, null);

        final ImageView imageViewPracticarSituacion = (ImageView) viewDialogDesign.findViewById(R.id.imageViewPracticarSituacion);
        final ImageView imageViewVerListaDeSituacion = (ImageView) viewDialogDesign.findViewById(R.id.imageViewVerListaDeSituacion);

        imageViewPracticarSituacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanzar.irHaciaPracticarSituacionGenericaActivity(descripcionDeSituacion);
                if(dialogOpcionesDeSituacion != null){
                    dialogOpcionesDeSituacion.dismiss();
                }
            }
        });

        imageViewVerListaDeSituacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanzar.irHaciaListaSituacionGenericaActivity(descripcionDeSituacion);
                if(dialogOpcionesDeSituacion != null){
                    dialogOpcionesDeSituacion.dismiss();
                }

            }
        });

        dialogOpcionesDeSituacion = new AlertDialog.Builder(getContext())
                .setView(viewDialogDesign)
                .setNegativeButton("CANCELAR", null)
                .setCancelable(false).create();

        dialogOpcionesDeSituacion.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button buttonNegative = ((AlertDialog) dialogOpcionesDeSituacion).getButton(AlertDialog.BUTTON_NEGATIVE);


                buttonNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogOpcionesDeSituacion.dismiss();
                    }
                });
            }
        });

        dialogOpcionesDeSituacion.show();
    }

    public interface Avanzar{
        void irHaciaListaSituacionGenericaActivity(String descripcionDeSituacion);
        void irHaciaPracticarSituacionGenericaActivity(String descripcionDeSituacion);
    }

}
