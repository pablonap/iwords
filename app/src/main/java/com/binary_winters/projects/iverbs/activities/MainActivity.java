package com.binary_winters.projects.iverbs.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.ViewPagerAdapter;
import com.binary_winters.projects.iverbs.fragments.MenuIverbsFragment;
import com.binary_winters.projects.iverbs.fragments.MenuMisPalabrasFragment;
import com.binary_winters.projects.iverbs.fragments.MenuSituacionesFragment;
import com.binary_winters.projects.iverbs.plus.activities.ListaMisPalabrasActivity;
import com.binary_winters.projects.iverbs.plus.activities.PracticarMisPalabrasActivity;
import com.binary_winters.projects.iverbs.situaciones.activities.ListaSituacionGenericaActivity;
import com.binary_winters.projects.iverbs.situaciones.activities.PracticarSituacionGenericaActivity;
import com.binary_winters.projects.iverbs.util.ImpExpBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MenuMisPalabrasFragment.Avanzar, MenuIverbsFragment.Avanzar, MenuSituacionesFragment.Avanzar{
    private ViewPager viewPager;

    private ProgressBar progressBar;
    private TextView textViewLabelProgressBar;

    private final int PERMISSION_FOR_WRITE_EXTERNAL_STORAGE = 0;
    private final int PERMISO_PARA_EXPORTAR = 1;
    private static final int READ_REQUEST_CODE = 42;

    private ImpExpBuilder impExpBuilder;

    private MenuIverbsFragment menuIverbsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textViewLabelProgressBar = (TextView) findViewById(R.id.textViewLabelProgressBar);

        progressBar.setVisibility(View.INVISIBLE);
        textViewLabelProgressBar.setVisibility(View.INVISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        final String NOMBRE_TAB1 = getString(R.string.iverbs);
        final String NOMBRE_TAB2 = getString(R.string.misPalabrasLower);
        final String NOMBRE_TAB3 = getString(R.string.situaciones);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(NOMBRE_TAB1));
        tabLayout.addTab(tabLayout.newTab().setText(NOMBRE_TAB2));
        tabLayout.addTab(tabLayout.newTab().setText(NOMBRE_TAB3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setBackgroundColor(Color.WHITE);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        menuIverbsFragment = (MenuIverbsFragment) viewPagerAdapter.getItem(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void irHaciaListaMisPalabrasActivity() {
        Intent intent = new Intent(MainActivity.this, ListaMisPalabrasActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaPracticarMisPalabrasActivity() {
        Intent intent = new Intent(MainActivity.this, PracticarMisPalabrasActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaPartidaIverbsActivity() {
        Intent intent = new Intent(MainActivity.this, PartidaIverbsActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaTodosLosVerbosActivity() {
        Intent intent = new Intent(MainActivity.this, TodosLosVerbosActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaVerbosAgregadosActivity() {
        Intent intent = new Intent(MainActivity.this, VerbosAgregadosActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaHistorialDePartidaActivity() {
        Intent intent = new Intent(MainActivity.this, HistorialDePartidaActivity.class);
        startActivity(intent);
    }

    @Override
    public void irHaciaListaSituacionGenericaActivity(String tipoSituacion) {
        Intent intent = new Intent(MainActivity.this, ListaSituacionGenericaActivity.class);
        intent.putExtra("key_descripcion_situacion", tipoSituacion);

        startActivity(intent);
    }

    @Override
    public void irHaciaPracticarSituacionGenericaActivity(String tipoSituacion) {
        Intent intent = new Intent(MainActivity.this, PracticarSituacionGenericaActivity.class);
        intent.putExtra("key_descripcion_situacion", tipoSituacion);
        startActivity(intent);
    }

    // Al responder al ActivityCompat.requestPermissions(..)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_FOR_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, getString(R.string.permiso_para_importar), Toast.LENGTH_SHORT).show();
                } else{
                    try{
                        performFileSearch();
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

            case PERMISO_PARA_EXPORTAR : {
                if (grantResults.length > 0
                        && grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, getString(R.string.permiso_para_importar), Toast.LENGTH_SHORT).show();
                } else{
                    try{
                        impExpBuilder = new ImpExpBuilder(this);
                        impExpBuilder.exportarBackup();
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public void performFileSearch() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("text/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    // Una vez seleccionado el archivo por el performFileSearch()
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        try{
            // The ACTION_OPEN_DOCUMENT intent was sent with the request code
            // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
            // response to some other intent, and the code below shouldn't run at all.

            if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                // The document selected by the user won't be returned in the intent.
                // Instead, a URI to that document will be contained in the return intent
                // provided to this method as a parameter.
                // Pull that URI using resultData.getData().
                Uri uriDeArchivoImp = null;
                if (resultData != null) {
                    AsyncTaskImportar asyncTaskImportar = new AsyncTaskImportar(uriDeArchivoImp, resultData);
                    asyncTaskImportar.execute();
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    private class AsyncTaskImportar extends AsyncTask<Void, Integer, Boolean> {
        private Uri uriDeArchivoImp;
        private Intent resultData;

        public AsyncTaskImportar(Uri uriDeArchivoImp, Intent resultData) {
            this.uriDeArchivoImp = uriDeArchivoImp;
            this.resultData = resultData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            textViewLabelProgressBar.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.RED,android.graphics.PorterDuff.Mode.MULTIPLY);

            Toast.makeText(MainActivity.this, getString(R.string.importando_contenido), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            importarContenido(uriDeArchivoImp, resultData);

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0].intValue());
        }

        protected void onPostExecute(Boolean resultado){
            if(resultado){
                Toast.makeText(MainActivity.this, getString(R.string.contenido_importado), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                textViewLabelProgressBar.setVisibility(View.INVISIBLE);
            } else{
                Toast.makeText(MainActivity.this, getString(R.string.error_importar_contenido), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void importarContenido(Uri uriDeArchivoImp, Intent resultData){
        uriDeArchivoImp = resultData.getData();
        impExpBuilder = new ImpExpBuilder(MainActivity.this);

        List<String> lineasDeArchivoDesencriptado = impExpBuilder.desencriptarArchivo(uriDeArchivoImp);

        Boolean isContenidoValido = impExpBuilder.verificarValidezDeContenido(lineasDeArchivoDesencriptado);

        if(isContenidoValido){
            impExpBuilder.reescribirBd();
            finish();
            startActivity(getIntent());
        } else{
            Toast.makeText(this, getString(R.string.contenido_no_valido), Toast.LENGTH_SHORT).show();
        }
    }


}