package com.binary_winters.projects.iverbs.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.binary_winters.projects.iverbs.HistorialDePartidaAdapter;
import com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean;
import com.binary_winters.projects.iverbs.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.binary_winters.projects.iverbs.R.id.listViewHistorial;

public class HistorialDePartidaActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<HistorialDePartidaBean>> {
    private Realm realm;

    private ListView listView;
    private HistorialDePartidaAdapter myAdapter;
    private RealmResults<HistorialDePartidaBean> historial;

    private TextView textViewNoHayHistorialLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_de_partida);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        textViewNoHayHistorialLabel = (TextView) findViewById(R.id.textViewNoHayHistorialLabel);
        listView = (ListView) findViewById(listViewHistorial);

        realm = Realm.getDefaultInstance();

        historial = realm.where(HistorialDePartidaBean.class).findAll();

        historial.addChangeListener(this);

        myAdapter = new HistorialDePartidaAdapter(historial, R.layout.item_view_historial, this);
        listView.setAdapter( myAdapter );

        if(historial.isEmpty()){
            listView.setVisibility(View.INVISIBLE);
        } else{
            textViewNoHayHistorialLabel.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_historial, menu);

        if(historial != null && historial.isEmpty()){
            menu.findItem(R.id.menu_item_delete_historial).setVisible(Boolean.FALSE);
        } else{
            menu.findItem(R.id.menu_item_delete_historial).setVisible(Boolean.TRUE);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete_historial:
                mostrarDialogBorrarHistorial(getString(R.string.aviso), getString(R.string.borrar_historial));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void borrarHistorial() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                historial.deleteAllFromRealm();
            }
        });
        textViewNoHayHistorialLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onChange(RealmResults<HistorialDePartidaBean> historialDePartidaBeen) {
        myAdapter.notifyDataSetChanged();
    }

    private void mostrarDialogBorrarHistorial(String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrarHistorial();
                    }
                })
                .setNegativeButton(getString(R.string.no), null).setCancelable(false).show();
    }
}
