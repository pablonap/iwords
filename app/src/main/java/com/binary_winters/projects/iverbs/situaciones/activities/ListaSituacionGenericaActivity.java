package com.binary_winters.projects.iverbs.situaciones.activities;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;
import com.binary_winters.projects.iverbs.situaciones.SituacionGenericaAdapter;
import com.binary_winters.projects.iverbs.situaciones.SituacionGenericaBind;
import com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica;
import com.binary_winters.projects.iverbs.util.BusquedaDePalabra;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListaSituacionGenericaActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<PalabraAgregadaBean>> {
    private Realm realm;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RealmResults<SituacionGenerica> situacionGenericaFromRealm;
    private TextView textViewNoHayPalabrasLabel;
    private List<SituacionGenerica> listaDeSituacionGenerica;

    private Toast toastGenerico;

    public RealmResults<SituacionGenerica> getSituacionGenericaFromRealm() {
        return situacionGenericaFromRealm;
    }

    private BusquedaDePalabra busquedaDePalabra;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_situaciones);

        Bundle bundle = getIntent().getExtras();

        String descripcionTipo = bundle.getString("key_descripcion_situacion");

        realm = Realm.getDefaultInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewPalabras);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        textViewNoHayPalabrasLabel = (TextView) findViewById(R.id.textViewNoHayPalabrasLabel);

        situacionGenericaFromRealm = realm.where(SituacionGenerica.class).equalTo("tipoSituacion", descripcionTipo).findAll();

        listaDeSituacionGenerica = new ArrayList<>();

        toastGenerico = Toast.makeText(ListaSituacionGenericaActivity.this, "", Toast.LENGTH_SHORT);

        busquedaDePalabra = new BusquedaDePalabra(ListaSituacionGenericaActivity.this, toastGenerico) {
            @Override
            public void buscarPalabraEnEsp(String nombrePalabraEsp) {
                if(nombrePalabraEsp != null){
                    Integer indicePalabraHallada = null;
                    SituacionGenerica situacionGenerica = null;
                    for(int i = 0; i < situacionGenericaFromRealm.size(); i++){
                        if(nombrePalabraEsp.equals(situacionGenericaFromRealm.get(i).getPalabraEnEsp())){
                            situacionGenerica = situacionGenericaFromRealm.get(i);
                            indicePalabraHallada = i;
                        }
                    }

                    if(situacionGenerica != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText("Palabra no hallada");
                        toastGenerico.show();
                    }
                }
            }

            @Override
            public void buscarPalabraEnIng(String palabraEnIng) {
                if(palabraEnIng != null){
                    SituacionGenerica situacionGenerica = null;
                    int indicePalabraHallada = 0;
                    for(int i = 0; i < situacionGenericaFromRealm.size(); i++){
                        String[] divisionesDeGuion = situacionGenericaFromRealm.get(i).getCadenaEnIngles().split("-");
                        String divisionGuionEscritura = divisionesDeGuion[0];

                        if(divisionGuionEscritura != null){
                            if(palabraEnIng.equals(divisionGuionEscritura)){
                                situacionGenerica = situacionGenericaFromRealm.get(i);
                                indicePalabraHallada = i;
                            }
                        }
                    }

                    if(situacionGenerica != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText("Verbo no hallado");
                        toastGenerico.show();
                    }
                }
            }
        };

        // Se vuelvan los objetos de la lista palabrasAgregadasFromRealm que vienen en null (ya que están dentro)
        // de un objeto proxy a otra lista que es la que se envía al adapter y usa acá para referencias la estrella.
        for(SituacionGenerica situacionGenericaActual : situacionGenericaFromRealm){
            SituacionGenerica situacionGenerica
                    = new SituacionGenerica(situacionGenericaActual.getPalabraEnEsp(),
                    situacionGenericaActual.getCadenaEnIngles(), situacionGenericaActual.getMarkedWithStar(),
                    situacionGenericaActual.getTipoSituacion(), situacionGenericaActual.getNombreAudio());
            situacionGenerica.setId(situacionGenericaActual.getId());
            listaDeSituacionGenerica.add(situacionGenerica);
        }

        myAdapter = new SituacionGenericaAdapter(listaDeSituacionGenerica, R.layout.item_view_palabra_agregada,
                this, new SituacionGenericaAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                SituacionGenerica situacionGenerica = listaDeSituacionGenerica.get(position);
                if (situacionGenerica.getMarkedWithStar()) {
                    situacionGenerica.setMarkedWithStar(Boolean.FALSE);
                } else {
                    situacionGenerica.setMarkedWithStar(Boolean.TRUE);

                    toastGenerico.setText("Agregado a favoritos");
                    toastGenerico.show();
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(situacionGenerica);
                realm.commitTransaction();

                myAdapter.notifyDataSetChanged();
            }
        }, new SituacionGenericaAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                SituacionGenerica situacionGenerica = listaDeSituacionGenerica.get(position);

                if(mp != null){
                    mp.release();
                    mp = null;
                }

                mp = SituacionGenericaBind.cargarAudioSegunVerbo(ListaSituacionGenericaActivity.this, mp, situacionGenerica.getNombreAudio());
            }
        });
        mRecyclerView.setAdapter(myAdapter);

        registerForContextMenu(mRecyclerView);

        if(situacionGenericaFromRealm.isEmpty()){
            textViewNoHayPalabrasLabel.setVisibility(View.VISIBLE);
        } else {
            textViewNoHayPalabrasLabel.setVisibility(View.INVISIBLE);
        }
    }



    @Override
    public void onChange(RealmResults<PalabraAgregadaBean> miPalabraBeen) {
        myAdapter.notifyDataSetChanged();
        if(situacionGenericaFromRealm.isEmpty()){
            textViewNoHayPalabrasLabel.setVisibility(View.VISIBLE);
        } else{
            textViewNoHayPalabrasLabel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_generico_situaciones_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_find_palabra:
                busquedaDePalabra.realizarBusqueda();
                return true;
            case R.id.menu_item_arriba_palabras:
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.menu_item_abajo_palabras:
                mLayoutManager.scrollToPosition(situacionGenericaFromRealm.size()-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
