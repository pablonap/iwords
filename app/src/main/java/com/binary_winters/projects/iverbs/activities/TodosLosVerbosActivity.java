package com.binary_winters.projects.iverbs.activities;

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
import android.widget.Toast;

import com.binary_winters.projects.iverbs.R;
import com.binary_winters.projects.iverbs.TodosLosVerbosAdapter;
import com.binary_winters.projects.iverbs.VerboBind;
import com.binary_winters.projects.iverbs.VerboEnEsp;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;
import com.binary_winters.projects.iverbs.util.BusquedaDePalabra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

public class TodosLosVerbosActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Realm realm;

    private List<VerboGenericoBean> todosLosVerbos = null;

    private MediaPlayer mp;

    private Toast toastGenerico;

    private BusquedaDePalabra busquedaDePalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_los_verbos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        VerboBind verboBind = new VerboBind();

        realm = Realm.getDefaultInstance();

        toastGenerico = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        VerboGenericoBean verboGenericoBean;
        todosLosVerbos = new ArrayList<>();

        busquedaDePalabra = new BusquedaDePalabra(TodosLosVerbosActivity.this, toastGenerico) {
            @Override
            public void buscarPalabraEnEsp(String palabra) {
                if(palabra != null){
                    VerboGenericoBean verboGenericoBean = null;
                    int indicePalabraHallada = 0;
                    for(int i = 0; i < todosLosVerbos.size(); i++){
                        if(palabra.equals(todosLosVerbos.get(i).getVerboEnEsp().getNombreDeVerbo())){
                            verboGenericoBean = todosLosVerbos.get(i);
                            indicePalabraHallada = i;
                        }
                    }

                    if(verboGenericoBean != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText(getString(R.string.verbo_no_hallado));
                        toastGenerico.show();
                    }
                }
            }

            @Override
            public void buscarPalabraEnIng(String nombrePalabraIng) {
                if(nombrePalabraIng != null){
                    VerboGenericoBean verboGenericoBean = null;
                    int indicePalabraHallada = 0;
                    for(int i = 0; i < todosLosVerbos.size(); i++){
                        String[] divisionesDeEspacio = todosLosVerbos.get(i).getCadenaDeVerbos().split(" ");
                        String cadenaEspacio1 = divisionesDeEspacio[0];
                        String cadenaEspacio2 = divisionesDeEspacio[1];
                        String cadenaEspacio3 = divisionesDeEspacio[2];

                        String[] divisionesDeGuion1 = cadenaEspacio1.split("-");
                        String[] divisionesDeGuion2 = cadenaEspacio2.split("-");
                        String[] divisionesDeGuion3 = cadenaEspacio3.split("-");

                        String divisionGuionCol1 = divisionesDeGuion1[0];
                        String divisionGuionCol2 = divisionesDeGuion2[0];
                        String divisionGuionCol3 = divisionesDeGuion3[0];

                        if(divisionGuionCol1 != null && divisionGuionCol2 != null && divisionGuionCol3 != null){
                            if(nombrePalabraIng.equals(divisionGuionCol1) || nombrePalabraIng.equals(divisionGuionCol2)
                                    || nombrePalabraIng.equals(divisionGuionCol3)){
                                verboGenericoBean = todosLosVerbos.get(i);
                                indicePalabraHallada = i;
                            }
                        }
                    }

                    if(verboGenericoBean != null){
                        mLayoutManager.scrollToPosition(indicePalabraHallada);
                    } else{
                        toastGenerico.setText(getString(R.string.verbo_no_hallado));
                        toastGenerico.show();
                    }
                }
            }
        };

        List<String> lista;
        VerboEnEsp verboEspActual = null;
        VerboEnEsp verboEspAnterior = null;

        Map<List<String>, VerboEnEsp> mapa = verboBind.getMapa();

        // Crea VerboGenericoBean desde el mapa que ya tiene todos los verbos.
        Iterator<Map.Entry<List<String>, VerboEnEsp>> it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<List<String>, VerboEnEsp> pair = it.next();
            lista = pair.getKey();
            verboEspActual = pair.getValue();
            String cadenaDeColumnas;
            if(verboEspAnterior == null && verboEspActual != null){
                cadenaDeColumnas = lista.get(0) + " " +  lista.get(1) + " " + lista.get(2);
                verboGenericoBean = new VerboGenericoBean(verboEspActual, cadenaDeColumnas);
                todosLosVerbos.add(verboGenericoBean);
                verboEspAnterior = verboEspActual;
            } else if(verboEspActual.getNombreDeVerbo().equals(verboEspAnterior.getNombreDeVerbo()) == false){
                cadenaDeColumnas = lista.get(0) + " " +  lista.get(1) + " " + lista.get(2);
                verboGenericoBean = new VerboGenericoBean(verboEspActual, cadenaDeColumnas);
                todosLosVerbos.add(verboGenericoBean);
                verboEspAnterior = verboEspActual;
            }
        }

        mAdapter = new TodosLosVerbosAdapter(todosLosVerbos, R.layout.item_view_todos_los_verbos,
                this, new TodosLosVerbosAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                VerboEnEsp verboEnEspActual = todosLosVerbos.get(position).getVerboEnEsp();
                if (verboEnEspActual.getMarkedWithStar()) {
                    verboEnEspActual.setMarkedWithStar(Boolean.FALSE);
                } else {
                    verboEnEspActual.setMarkedWithStar(Boolean.TRUE);

                    toastGenerico.setText(getString(R.string.agregado_a_favoritos));
                    toastGenerico.show();
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(verboEnEspActual);
                realm.commitTransaction();

                mAdapter.notifyDataSetChanged();
            }
        }, new TodosLosVerbosAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                VerboGenericoBean verboSeleccionado = todosLosVerbos.get(position);

                if(mp != null){
                    mp.release();
                    mp = null;
                }

                mp = VerboBind.cargarAudioSegunVerbo(TodosLosVerbosActivity.this, mp, verboSeleccionado.getVerboEnEsp());
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todos_los_verbos_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_find_verbo:
                busquedaDePalabra.realizarBusqueda();
                return true;
            case R.id.menu_item_arriba_verbos:
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.menu_item_abajo_verbos:
                mLayoutManager.scrollToPosition(todosLosVerbos.size()-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
