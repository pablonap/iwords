package com.binary_winters.projects.iverbs.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.binary_winters.projects.iverbs.VerboEnEsp;
import com.binary_winters.projects.iverbs.VerbosAgregadosAdapter;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;
import com.binary_winters.projects.iverbs.util.AudioRecord;
import com.binary_winters.projects.iverbs.util.BusquedaDePalabra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class VerbosAgregadosActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<VerboGenericoBean>> {
    private Realm realm;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView textViewNoHayVerbosLabel;

    private VerboGenericoBean ultimoVerboDeUsuarioAgregado;

    private RealmResults<VerboGenericoBean> verbosAgregadosFromRealm;
    private List<VerboGenericoBean> verbosGenericos;

    private Toast toastGenerico;

    private MenuItem menuDelete;

    public MenuItem getMenuDelete() {
        return menuDelete;
    }

    public RealmResults<VerboGenericoBean> getVerbosAgregadosFromRealm() {
        return verbosAgregadosFromRealm;
    }

    private ImageView imageViewHelp;

    private BusquedaDePalabra busquedaDePalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verbos_agregados);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        toastGenerico = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewVerbosAg);

        textViewNoHayVerbosLabel = (TextView) findViewById(R.id.textViewNoHayVerbosLabel);

        realm = Realm.getDefaultInstance();

        verbosAgregadosFromRealm = realm.where(VerboGenericoBean.class).findAll();

        imageViewHelp = (ImageView) findViewById(R.id.imageViewHelp);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        imageViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastGenerico.setText(getString(R.string.para_ver_op));
                toastGenerico.show();
            }
        });

        verbosGenericos = new ArrayList<>();

        for(VerboGenericoBean verboActual : verbosAgregadosFromRealm){
            VerboEnEsp verboEnEsp = new VerboEnEsp(verboActual.getVerboEnEsp().getNombreDeVerbo(),
                    verboActual.getVerboEnEsp().getVERBO_DESDE_APP(), verboActual.getVerboEnEsp().getMarkedWithStar());
            verboEnEsp.setId(verboActual.getVerboEnEsp().getId());
            VerboGenericoBean verboGenericoBean = new VerboGenericoBean(verboEnEsp, verboActual.getCadenaDeVerbos());
            verboGenericoBean.setNombreArchivoAudio(verboActual.getNombreArchivoAudio());
            verboGenericoBean.setArchivoDeAudioEnString(verboActual.getArchivoDeAudioEnString());
            verboGenericoBean.setId(verboActual.getId());
            verbosGenericos.add(verboGenericoBean);
        }

        myAdapter = new VerbosAgregadosAdapter(verbosGenericos, R.layout.item_view_verbo_agregado,
                this, new VerbosAgregadosAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                VerboGenericoBean verboGenericoBean = verbosGenericos.get(position);
                if (verboGenericoBean.getVerboEnEsp().getMarkedWithStar()) {
                    verboGenericoBean.getVerboEnEsp().setMarkedWithStar(Boolean.FALSE);
                } else {
                    verboGenericoBean.getVerboEnEsp().setMarkedWithStar(Boolean.TRUE);

                    toastGenerico.setText(getString(R.string.agregado_a_favoritos));
                    toastGenerico.show();
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(verboGenericoBean);
                realm.commitTransaction();

                myAdapter.notifyDataSetChanged();
            }
        }, new VerbosAgregadosAdapter.OnItemClickListener() {
            @Override
            public void click(int position) {
                VerboGenericoBean verboGenericoBean = verbosGenericos.get(position);
                AudioRecord.startPlaying(verboGenericoBean.getNombreArchivoAudio());
            }
        });

        mRecyclerView.setAdapter(myAdapter);

        registerForContextMenu(mRecyclerView);

        busquedaDePalabra = new BusquedaDePalabra(VerbosAgregadosActivity.this, toastGenerico) {
            @Override
            public void buscarPalabraEnEsp(String nombrePalabraEsp) {
                if(nombrePalabraEsp != null){
                    VerboGenericoBean verboGenericoBean = null;
                    int indicePalabraHallada = 0;
                    for(int i = 0; i < verbosAgregadosFromRealm.size(); i++){
                        if(nombrePalabraEsp.equals(verbosAgregadosFromRealm.get(i).getVerboEnEsp().getNombreDeVerbo())){
                            verboGenericoBean = verbosAgregadosFromRealm.get(i);
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
                    for(int i = 0; i < verbosAgregadosFromRealm.size(); i++){
                        String[] divisionesDeEspacio = verbosAgregadosFromRealm.get(i).getCadenaDeVerbos().split(" ");
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
                                verboGenericoBean = verbosAgregadosFromRealm.get(i);
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

        if(verbosAgregadosFromRealm.isEmpty()){
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else{
            textViewNoHayVerbosLabel.setVisibility(View.INVISIBLE);

            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("ultimoVerboDeUsuarioAgregado");

            if(bundle != null){
                ultimoVerboDeUsuarioAgregado = (VerboGenericoBean) bundle.getSerializable("ultimoVerboDeUsuarioAgregadoSerializado");
                mLayoutManager.scrollToPosition(verbosAgregadosFromRealm.size() - 1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_verbos_agregados_activity, menu);

        menuDelete = menu.findItem(R.id.menu_item_delete_verbos_agregados);

        if(verbosAgregadosFromRealm != null && verbosAgregadosFromRealm.isEmpty()){
            menu.findItem(R.id.menu_item_delete_verbos_agregados).setVisible(Boolean.FALSE);
        } else{
            menu.findItem(R.id.menu_item_delete_verbos_agregados).setVisible(Boolean.TRUE);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_find_verbo:
                busquedaDePalabra.realizarBusqueda();
                return true;
            case R.id.menu_item_add_verbo:
                Intent intent = new Intent(VerbosAgregadosActivity.this, AddVerbActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_delete_verbos_agregados:
                mostrarDialogBorrarVerbosAgregados(getString(R.string.aviso), getString(R.string.borrar_todos_los_verbos));
                return true;
            case R.id.menu_item_arriba_verbos:
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.menu_item_abajo_verbos:
                mLayoutManager.scrollToPosition(verbosAgregadosFromRealm.size()-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VerbosAgregadosActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void mostrarDialogBorrarVerbosAgregados(String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrarVerbosAgregados();
                    }
                })
                .setNegativeButton(getString(R.string.no), null).setCancelable(false).show();
    }

    public void borrarAudio(String nombreAudioCompleto){
        if(nombreAudioCompleto != null && nombreAudioCompleto.isEmpty() == false){
            File fileToDelete = new File(nombreAudioCompleto);
            if(fileToDelete != null){
                fileToDelete.delete();
            }
        }
    }

    private void borrarVerbosAgregados() {
        for(VerboGenericoBean verboGenericoBean : verbosAgregadosFromRealm){
            borrarAudio(verboGenericoBean.getNombreArchivoAudio());
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<VerboEnEsp> verbosEnEspDeUsuarioParaBorrar;
                verbosEnEspDeUsuarioParaBorrar = realm.where(VerboEnEsp.class).equalTo("VERBO_DESDE_APP", Boolean.FALSE).findAll();

                verbosEnEspDeUsuarioParaBorrar.deleteAllFromRealm();
                verbosAgregadosFromRealm.deleteAllFromRealm();
            }
        });
        Intent intent = new Intent(VerbosAgregadosActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        textViewNoHayVerbosLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onChange(RealmResults<VerboGenericoBean> verboGenericoBeen) {}
}