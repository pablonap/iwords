package com.binary_winters.projects.iverbs.fragments;


import android.Manifest;
import android.content.Context;
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
import android.widget.ImageView;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.activities.HelpActivity;
import com.binary_winters.projects.iverbs.activities.MainActivity;
import com.binary_winters.projects.iverbs.util.ImpExpBuilder;

public class MenuMisPalabrasFragment extends Fragment {
    private ImageView imageViewVerMisPalabras;
    private ImageView imageViewPracticarMisPalabras;

    private ImpExpBuilder impExpBuilder;

    private Avanzar avanzar;
    private MainActivity mainActivity;

    private final int PERMISSION_FOR_WRITE_EXTERNAL_STORAGE = 0;
    private final int PERMISO_PARA_EXPORTAR = 1;

    public MenuMisPalabrasFragment() {}

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
        View view = inflater.inflate(R.layout.fragment_menu_mis_palabras, container, false);

        setHasOptionsMenu(true);

        imageViewVerMisPalabras = (ImageView) view.findViewById(R.id.imageViewVerMisPalabras);
        imageViewPracticarMisPalabras = (ImageView) view.findViewById(R.id.imageViewPracticarMisPalabras);

        imageViewVerMisPalabras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avanzar.irHaciaListaMisPalabrasActivity();
            }
        });

        imageViewPracticarMisPalabras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avanzar.irHaciaPracticarMisPalabrasActivity();
            }
        });

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

    public interface Avanzar{
        void irHaciaListaMisPalabrasActivity();
        void irHaciaPracticarMisPalabrasActivity();
    }

}
