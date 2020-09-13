package com.binary_winters.projects.iverbs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.binary_winters.projects.iverbs.modelo.HistorialDePartidaBean;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;
import com.binary_winters.projects.iverbs.situaciones.modelo.SituacionGenerica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by chipo on 23/02/18.
 */

public class MyApp extends Application {
    // Recibe un valor y lo incrementa
    public static AtomicInteger VerboGenericoBeanID = new AtomicInteger();
    public static AtomicInteger HistorialDePartidaID = new AtomicInteger();
    public static AtomicInteger MiPalabraID = new AtomicInteger();
    public static AtomicInteger VerboEnEspID = new AtomicInteger();
    public static AtomicInteger SituacionGenericaID = new AtomicInteger();

    private SharedPreferences sharedPreferencesParaBorrarContenido;

    private VerboBind verboBind;

    Realm realm;

    private RealmResults<SituacionGenerica> listaDeSituacionesGenericasFromRealm;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        sharedPreferencesParaBorrarContenido = getSharedPreferences("sharedPreferencesParaBorrarContenido", Context.MODE_PRIVATE);

        realm = Realm.getDefaultInstance();
        VerboGenericoBeanID = getIdByTable(realm, VerboGenericoBean.class);
        HistorialDePartidaID = getIdByTable(realm, HistorialDePartidaBean.class);
        MiPalabraID = getIdByTable(realm, PalabraAgregadaBean.class);
        VerboEnEspID = getIdByTable(realm, VerboEnEsp.class);
        SituacionGenericaID = getIdByTable(realm, SituacionGenerica.class);

        verboBind = new VerboBind();

        this.crearSituacionesGenericas();

        // Hecho para el caso en que se desinstalaba la app y al volver a instalarla conservaba parte del contenido
        // anteriormente grabado.
        if("primeraVez".equals(sharedPreferencesParaBorrarContenido.getString("abiertoPrimeraVez", null)) == false){
            borrarContenidoDeBD();
            SharedPreferences.Editor editor = sharedPreferencesParaBorrarContenido.edit();
            editor.putString("abiertoPrimeraVez", "primeraVez");
            editor.apply();
        }

        realm.close();
    }

    public void borrarContenidoDeBD(){
        realm.beginTransaction();
        RealmResults<VerboEnEsp> verbosEnEspDeUsuarioParaBorrar = realm.where(VerboEnEsp.class).equalTo("VERBO_DESDE_APP", Boolean.FALSE).findAll();

        RealmResults<VerboGenericoBean> verbosAgregadosPorUsarioParaBorrar =
                realm.where(VerboGenericoBean.class).findAll();

        RealmResults<PalabraAgregadaBean> palabraAgregadaBeanParaBorrar = realm.where(PalabraAgregadaBean.class).findAll();

        RealmResults<HistorialDePartidaBean> historial = realm.where(HistorialDePartidaBean.class).findAll();

        if(verbosEnEspDeUsuarioParaBorrar != null){
            verbosEnEspDeUsuarioParaBorrar.deleteAllFromRealm();
        }

        if(verbosAgregadosPorUsarioParaBorrar != null){
            verbosAgregadosPorUsarioParaBorrar.deleteAllFromRealm();
        }

        if(palabraAgregadaBeanParaBorrar != null){
            palabraAgregadaBeanParaBorrar.deleteAllFromRealm();
        }

        if(historial != null){
            historial.deleteAllFromRealm();
        }

        realm.commitTransaction();
    }

    public void crearSituacionesGenericas(){
        listaDeSituacionesGenericasFromRealm = realm.where(SituacionGenerica.class).findAll();

        // La primera vez que se ejecute la app en BD no hay ninguna SituacionGenerica.
        if(listaDeSituacionesGenericasFromRealm != null && listaDeSituacionesGenericasFromRealm.isEmpty()){
            List<SituacionGenerica> listaDeSituacionGenerica = this.armarListaDeSituacionGenerica();
            persistirSituacionesGenericasPorPrimeraVez(listaDeSituacionGenerica);
        }
    }

    public List<SituacionGenerica> armarListaDeSituacionGenerica(){
        List<SituacionGenerica> listaDeSituacionGenerica = new ArrayList<>();

        // MESERO

        SituacionGenerica sgMesero1 = new SituacionGenerica("comida", "food-fud", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.FOOD);
        SituacionGenerica sgMesero2 = new SituacionGenerica("bebida", "drink-drink", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DRINK);
        SituacionGenerica sgMesero3 = new SituacionGenerica("bebida alcohólica", "alcoholic drink-alcojolic drink", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.ALCOHOLIC_DRINK);
        SituacionGenerica sgMesero4 = new SituacionGenerica("bebida sin alcohol", "non alcoholic drink-nonalcojolic drink", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.NON_ALCOHOLIC_DRINK);
        SituacionGenerica sgMesero5 = new SituacionGenerica("agua mineral", "mineral water-minerol uorer", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.MINERAL_WATER);
        SituacionGenerica sgMesero6 = new SituacionGenerica("refresco", "soft drink-soft drink", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.SOFT_DRINK);
        SituacionGenerica sgMesero7 = new SituacionGenerica("camarero", "waiter-ueiter", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WAITER);
        SituacionGenerica sgMesero8 = new SituacionGenerica("camarera", "waitress-ueitres", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WAITRESS);
        SituacionGenerica sgMesero9 = new SituacionGenerica("cocinero", "chef-shef", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.CHEF);
        SituacionGenerica sgMesero10 = new SituacionGenerica("la carta", "the menu-de meniu", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.THE_MENU);
        SituacionGenerica sgMesero11 = new SituacionGenerica("la carta de vinos", "the wine list-de uain list", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.THE_WINE_LIST);
        SituacionGenerica sgMesero12 = new SituacionGenerica("la cuenta", "the bill-de bil", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.THE_BILL);
        SituacionGenerica sgMesero13 = new SituacionGenerica("propina", "the tip-de tip", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.THE_TIP);
        SituacionGenerica sgMesero14 = new SituacionGenerica("mesa", "table-teibol", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.TABLE);
        SituacionGenerica sgMesero15 = new SituacionGenerica("silla", "chair-cher", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.CHAIR);
        SituacionGenerica sgMesero16 = new SituacionGenerica("mantel", "table cloth-teibol clo", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.TABLE_CLOTH);
        SituacionGenerica sgMesero17 = new SituacionGenerica("cuchara", "spoon-spun", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.SPOON);
        SituacionGenerica sgMesero18 = new SituacionGenerica("tenedor", "fork-fork", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.FORK);
        SituacionGenerica sgMesero19 = new SituacionGenerica("cuchillo", "knife-naif", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.KNIFE);
        SituacionGenerica sgMesero20 = new SituacionGenerica("pimentero", "pepper pot-peper pot", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.PEPPER_POT);
        SituacionGenerica sgMesero21 = new SituacionGenerica("salero", "salt cellar-soult sela", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.SALT_CELLAR);
        SituacionGenerica sgMesero22 = new SituacionGenerica("servilleta", "napkin-napkin", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.NAPKIN);
        SituacionGenerica sgMesero23 = new SituacionGenerica("servir la mesa", "to serve the table-tu serf de teibol", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.TO_SERVE_THE_TABLE);
        SituacionGenerica sgMesero24 = new SituacionGenerica("menú del día", "daily menu-deili meniu", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DAILY_MENU);
        SituacionGenerica sgMesero25 = new SituacionGenerica("carne", "meat-mit", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.MEAT);
        SituacionGenerica sgMesero26 = new SituacionGenerica("pescado", "fish-fish", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.FISH);
        SituacionGenerica sgMesero27 = new SituacionGenerica("legumbres y verduras", "vegetables-vechitabols", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.VEGETABLES);
        SituacionGenerica sgMesero28 = new SituacionGenerica("pasta", "pasta-pasta", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.PASTA);
        SituacionGenerica sgMesero29 = new SituacionGenerica("plato", "course-cors", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.COURSE);
        SituacionGenerica sgMesero30 = new SituacionGenerica("plato principal", "main course-mein cors", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.MAIN_COURSE);
        SituacionGenerica sgMesero31 = new SituacionGenerica("segundo plato", "second course-second cors", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.SECOND_COURSE);
        SituacionGenerica sgMesero32 = new SituacionGenerica("postre", "dessert-disert", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DESSERT);
        SituacionGenerica sgMesero33 = new SituacionGenerica("helado", "ice cream-ais crim", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.ICE_CREAM);
        SituacionGenerica sgMesero34 = new SituacionGenerica("¿puedo ayudarte?", "can i help you?-can ai jelp iu?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.CAN_I_HELP_YOU);
        SituacionGenerica sgMesero35 = new SituacionGenerica("¿cómo puedo ayudarle?", "how can i help you?-jau can ai jelp iu?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.HOW_CAN_I_HELP_YOU);
        SituacionGenerica sgMesero36 = new SituacionGenerica("¿ya decidiste?", "have you decided yet?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.HAVE_YOU_DECIDED_YET);
        SituacionGenerica sgMesero37 = new SituacionGenerica("¿necesita algo más?", "do you need anything else?-du iu nid enizin els?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DO_YOU_NEED_ANYTHING_ELSE);
        SituacionGenerica sgMesero38 = new SituacionGenerica("¿qué les gustaría beber?", "what would you like to drink?-uot wud iu laic tu drink?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WHAT_WOULD_YOU_LIKE_TO_DRINK);
        SituacionGenerica sgMesero39 = new SituacionGenerica("¿qué les gustaría comer?", "what would you like to eat?-uot wud iu laic tu it?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WHAT_WOULD_YOU_LIKE_TO_EAT);
        SituacionGenerica sgMesero40 = new SituacionGenerica("¿está todo bien?", "is everything fine?-is everizin fain?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.IS_EVERYTHING_FINE);
        SituacionGenerica sgMesero41 = new SituacionGenerica("¿algo más?", "anything else?-enizin els?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.ANYTHING_ELSE);
        SituacionGenerica sgMesero42 = new SituacionGenerica("¿qué más?", "what else?-uot els?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WHAT_ELSE);
        SituacionGenerica sgMesero43 = new SituacionGenerica("¿quiere la cuenta?", "do you want the bill?-du iu uont de bil", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DO_YOU_WANT_THE_BILL);
        SituacionGenerica sgMesero44 = new SituacionGenerica("¿la quiere muy hecha o poco hecha?", "do you want it well coocked or rare?-du iu uont it uel cuct or rer?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DO_YOU_WANT_IT_WELL_COOCKED_OR_RARE);
        SituacionGenerica sgMesero45 = new SituacionGenerica("¿quieren tomar postre?", "would you like to have desserts?-wud iu laic tu jav diserts?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WOULD_YOU_LIKE_TO_HAVE_DESSERTS);
        SituacionGenerica sgMesero46 = new SituacionGenerica("¿quieren ver la carta?", "do you want to see the menu?-du iu uont tu si de meniu?", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.DO_YOU_WANT_TO_SEE_THE_MENU);
        SituacionGenerica sgMesero47 = new SituacionGenerica("agua", "water-uorer", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.WATER);

        // EN RESTORÁN

        SituacionGenerica sgEnRestoran1 = new SituacionGenerica("bebida alcohólica", "alcoholic drink-alcojolic drink", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ALCOHOLIC_DRINK);
        SituacionGenerica sgEnRestoran2 = new SituacionGenerica("bebida sin alcohol", "non alcoholic drink-nonalcojolic drink", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.NON_ALCOHOLIC_DRINK);
        SituacionGenerica sgEnRestoran3 = new SituacionGenerica("agua mineral", "mineral water-minerol uorer", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MINERAL_WATER);
        SituacionGenerica sgEnRestoran4 = new SituacionGenerica("refresco", "soft drink-soft drink", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.SOFT_DRINK);
        SituacionGenerica sgEnRestoran5 = new SituacionGenerica("camarero", "waiter-ueiter", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WAITER);
        SituacionGenerica sgEnRestoran6 = new SituacionGenerica("camarera", "waitress-ueitres", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WAITRESS);
        SituacionGenerica sgEnRestoran7 = new SituacionGenerica("cocinero", "chef-shef", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.CHEF);
        SituacionGenerica sgEnRestoran8 = new SituacionGenerica("la carta", "the menu-de meniu", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THE_MENU);
        SituacionGenerica sgEnRestoran9 = new SituacionGenerica("la carta de vinos", "the wine list-de uain list", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THE_WINE_LIST);
        SituacionGenerica sgEnRestoran10 = new SituacionGenerica("la cuenta", "the bill-de bil", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THE_BILL);
        SituacionGenerica sgEnRestoran11 = new SituacionGenerica("mesa", "table-teibol", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.TABLE);
        SituacionGenerica sgEnRestoran12 = new SituacionGenerica("silla", "chair-cher", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.CHAIR);
        SituacionGenerica sgEnRestoran13 = new SituacionGenerica("mantel", "table cloth-teibol clo", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.TABLE_CLOTH);
        SituacionGenerica sgEnRestoran14 = new SituacionGenerica("cuchara", "spoon-spun", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.SPOON);
        SituacionGenerica sgEnRestoran15 = new SituacionGenerica("tenedor", "fork-fork", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.FORK);
        SituacionGenerica sgEnRestoran16 = new SituacionGenerica("cuchillo", "knife-naif", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.KNIFE);
        SituacionGenerica sgEnRestoran17 = new SituacionGenerica("pimentero", "pepper pot-peper pot", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.PEPPER_POT);
        SituacionGenerica sgEnRestoran18 = new SituacionGenerica("salero", "salt cellar-soult sela", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.SALT_CELLAR);
        SituacionGenerica sgEnRestoran19 = new SituacionGenerica("servilleta", "napkin-napkin", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.NAPKIN);
        SituacionGenerica sgEnRestoran20 = new SituacionGenerica("menú del día", "daily menu-deili meniu", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.DAILY_MENU);
        SituacionGenerica sgEnRestoran21 = new SituacionGenerica("carne", "meat-mit", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MEAT);
        SituacionGenerica sgEnRestoran22 = new SituacionGenerica("pescado", "fish-fish", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.FISH);
        SituacionGenerica sgEnRestoran23 = new SituacionGenerica("legumbres y verduras", "vegetables-vechitabols", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.VEGETABLES);
        SituacionGenerica sgEnRestoran24 = new SituacionGenerica("pasta", "pasta-pasta", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.PASTA);
        SituacionGenerica sgEnRestoran25 = new SituacionGenerica("plato", "course-cors", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.COURSE);
        SituacionGenerica sgEnRestoran26 = new SituacionGenerica("plato principal", "main course-mein cors", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MAIN_COURSE);
        SituacionGenerica sgEnRestoran27 = new SituacionGenerica("segundo plato", "second course-second cors", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.SECOND_COURSE);
        SituacionGenerica sgEnRestoran28 = new SituacionGenerica("postre", "dessert-disert", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.DESSERT);
        SituacionGenerica sgEnRestoran29 = new SituacionGenerica("helado", "ice cream-ais crim", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ICE_CREAM);

        // Hasta acá son los mismos que para "Mesero"

        SituacionGenerica sgEnRestoran30 = new SituacionGenerica("comida para llevar(british)", "takeaway-teikeuei", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.TAKEAWAY);
        SituacionGenerica sgEnRestoran31 = new SituacionGenerica("comida para llevar(usa)", "takeout-teikaut", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.TAKEOUT);
        SituacionGenerica sgEnRestoran32 = new SituacionGenerica("un café para llevar", "coffee to take away-cofi tu teik euei", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.COFFEE_TO_TAKE_AWAY);
        SituacionGenerica sgEnRestoran33 = new SituacionGenerica("taza de café", "cup of coffee-cap of cofi", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.CUP_OF_COFFEE);
        SituacionGenerica sgEnRestoran34 = new SituacionGenerica("té helado", "iced tea-ais ti", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ICED_TEA);
        SituacionGenerica sgEnRestoran35 = new SituacionGenerica("picadita/tentenmpié", "snack-snac", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.SNACK);
        SituacionGenerica sgEnRestoran36 = new SituacionGenerica("vaso; copa", "glass-glas", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.GLASS);
        SituacionGenerica sgEnRestoran37 = new SituacionGenerica("almuerzo", "lunch-lanch", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.LUNCH);
        SituacionGenerica sgEnRestoran38 = new SituacionGenerica("carne poco cocida", "rare-rer", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.RARE);
        SituacionGenerica sgEnRestoran39 = new SituacionGenerica("término medio", "medium-midiem", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MEDIUM);
        SituacionGenerica sgEnRestoran40 = new SituacionGenerica("carne bien cocida", "well done-uel don", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WELL_DONE);
        SituacionGenerica sgEnRestoran41 = new SituacionGenerica("pedir", "to order-tu order", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.TO_ORDER);
        SituacionGenerica sgEnRestoran42 = new SituacionGenerica("buen provecho", "enjoy your meal-enshoi de mil", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ENJOY_YOUR_MEAL);
        SituacionGenerica sgEnRestoran43 = new SituacionGenerica("destapador", "bottle opener-batl opener", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.BOTTLE_OPENER);
        SituacionGenerica sgEnRestoran44 = new SituacionGenerica("taza", "cup-cap", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.CUP);
        SituacionGenerica sgEnRestoran45 = new SituacionGenerica("cena", "dinner-diner", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.DINNER);
        SituacionGenerica sgEnRestoran46 = new SituacionGenerica("aderezo", "dressing-dresin", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.DRESSING);
        SituacionGenerica sgEnRestoran47 = new SituacionGenerica("¿puedes darme el menú por favor?", "may i have the menu please?-mei ai jav de meniu plis?", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MAY_I_HAVE_THE_MENU_PLEASE);
        SituacionGenerica sgEnRestoran48 = new SituacionGenerica("quiero un sanguche", "i would like to have a sandwich-ai wud laic tu jav e senduich", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.I_WOULD_LIKE_TO_HAVE_A_SANDWICH);
        SituacionGenerica sgEnRestoran49 = new SituacionGenerica("¿qué es este plato?", "what's this dish?-uots dis dish", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHATS_THIS_DISH);
        SituacionGenerica sgEnRestoran50 = new SituacionGenerica("¿cuál es el plato del día?", "what is today's special?-uot is tudeis speshial", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHAT_IS_TODAYS_SPECIAL);
        SituacionGenerica sgEnRestoran51 = new SituacionGenerica("¿qué me recomienda?", "what do you recommend me?-uot du iu ricomend mi?", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHAT_DO_YOU_RECOMMEND_ME);
        SituacionGenerica sgEnRestoran52 = new SituacionGenerica("no lo sé aún", "i'm not ready yet-aim not redi iet", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.IM_NOT_READY_YET);
        SituacionGenerica sgEnRestoran53 = new SituacionGenerica("quisiera pedir ahora", "i would like to order now-i wud laic tu order nau", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.I_WOULD_LIKE_TO_ORDER_NOW);
        SituacionGenerica sgEnRestoran54 = new SituacionGenerica("voy a pedir", "i'll have the", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ILL_HAVE_THE);
        SituacionGenerica sgEnRestoran55 = new SituacionGenerica("quiero el", "i'd like the-aid laic de", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ID_LIKE_THE);
        SituacionGenerica sgEnRestoran56 = new SituacionGenerica("por favor me trae", "please, can i have the-plis, can ai jav de", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.PLEASE_CAN_I_HAVE_THE);
        SituacionGenerica sgEnRestoran57 = new SituacionGenerica("¿qué ingredientes lleva el plato?", "what are the ingredients?-uot ar de ingredients", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHAT_ARE_THE_INGREDIENTS);
        SituacionGenerica sgEnRestoran58 = new SituacionGenerica("tomaré lo mismo", "i'll have the same-ail jav de seim", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.ILL_HAVE_THE_SAME);
        SituacionGenerica sgEnRestoran59 = new SituacionGenerica("¿qué tipo de postres tienen?", "what kind of desserts do you have?-uot kaind of diserts du iu jaf", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHAT_KIND_OF_DESSERTS_DO_YOU_HAVE);
        SituacionGenerica sgEnRestoran60 = new SituacionGenerica("¿podría traerme una cerveza?", "may i have a beer?-mei ai jav e bir", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.MAY_I_HAVE_A_BEER);
        SituacionGenerica sgEnRestoran61 = new SituacionGenerica("eso es todo, gracias", "that's all, thank you-dats ol, zenkiu", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THATS_ALL_THANK_YOU);
        SituacionGenerica sgEnRestoran62 = new SituacionGenerica("estaba todo delicioso", "everything was delicious-everizin uos delishas", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.EVERYTHING_WAS_DELICIOUS);
        SituacionGenerica sgEnRestoran63 = new SituacionGenerica("esto está muy sabroso", "this is very tasty-dis is veri teisti", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THIS_IS_VERY_TASTY);
        SituacionGenerica sgEnRestoran64 = new SituacionGenerica("quiero hablar con el encargado", "i want to talk to the manager-ai uont tu tolk tu de menesher", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.I_WANT_TO_TALK_TO_THE_MANAGER);
        SituacionGenerica sgEnRestoran65 = new SituacionGenerica("quedate con el cambio", "keep the change-kip de cheinsh", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.KEEP_THE_CHANGE);
        SituacionGenerica sgEnRestoran66 = new SituacionGenerica("¿podría traerme la cuenta?", "could i have the bill, please?-culd ai jav de bil, plis", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.COULD_I_HAVE_THE_BILL_PLEASE);
        SituacionGenerica sgEnRestoran67 = new SituacionGenerica("¿podría pagar con tarjeta?", "could i pay by card?-culd ai pei bai card?", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.COULD_I_PAY_BY_CARD);
        SituacionGenerica sgEnRestoran68 = new SituacionGenerica("esto no es lo que pedí", "this is not what i ordered-dis is not uot ai ordert", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.THIS_IS_NOT_WHAT_I_ORDERED);
        SituacionGenerica sgEnRestoran69 = new SituacionGenerica("¿dónde están los baños?", "where are the restrooms?-uer ar de restrums?", Boolean.FALSE, TipoSituacion.EN_RESTORAN.getDescripcion(), NombresAudioParaSituaciones.WHERE_ARE_THE_RESTROOMS);


        // AEROPUERTO

        SituacionGenerica sgEnAeropuerto1 = new SituacionGenerica("aeropuerto", "airport-erport", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.AIRPORT);
        SituacionGenerica sgEnAeropuerto2 = new SituacionGenerica("avión", "airplane-erplein", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.AIRPLANE);
        SituacionGenerica sgEnAeropuerto3 = new SituacionGenerica("vuelo", "flight-flaigt", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.FLIGHT);
        SituacionGenerica sgEnAeropuerto4 = new SituacionGenerica("tarjeta de embarque", "boarding pass-bordin pas", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.BOARDING_PASS);
        SituacionGenerica sgEnAeropuerto5 = new SituacionGenerica("azafata", "air hostess-er joustes", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.AIR_HOSTESS);
        SituacionGenerica sgEnAeropuerto6 = new SituacionGenerica("sala de embarque", "departure lounge-departur lonch", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.DEPARTURE_LOUNGE);
        SituacionGenerica sgEnAeropuerto7 = new SituacionGenerica("facturación del equipaje", "checkin-chekin", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.CHECK_IN);
        SituacionGenerica sgEnAeropuerto8 = new SituacionGenerica("ida", "one way-uan uei", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.ONE_WAY);
        SituacionGenerica sgEnAeropuerto9 = new SituacionGenerica("ida y vuelta", "round trip-round trip", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.ROUND_TRIP);
        SituacionGenerica sgEnAeropuerto10 = new SituacionGenerica("salidas", "departures-departurs", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.DEPARTURES);
        SituacionGenerica sgEnAeropuerto11 = new SituacionGenerica("llegadas", "arrivals-arraivals", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.ARRIVALS);
        SituacionGenerica sgEnAeropuerto12 = new SituacionGenerica("parada", "layover-leiover", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.LAYOVER);
        SituacionGenerica sgEnAeropuerto13 = new SituacionGenerica("clase turista", "coach class-couch clas", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.COACH_CLASS);
        SituacionGenerica sgEnAeropuerto14 = new SituacionGenerica("equipaje", "luggage-lagich", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.LUGGAGE);
        SituacionGenerica sgEnAeropuerto15 = new SituacionGenerica("maleta", "suitcase-sutkeis", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.SUITCASE);
        SituacionGenerica sgEnAeropuerto16 = new SituacionGenerica("equipaje de mano", "carry on luggage-kerri on lagich", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.CARRY_ON_LUGGAGE);
        SituacionGenerica sgEnAeropuerto17 = new SituacionGenerica("aduana", "customs-castoms", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.CUSTOMS);
        SituacionGenerica sgEnAeropuerto18 = new SituacionGenerica("embarcar", "board-bord", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.BOARD);
        SituacionGenerica sgEnAeropuerto19 = new SituacionGenerica("salir", "depart-depart", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.DEPART);
        SituacionGenerica sgEnAeropuerto20 = new SituacionGenerica("llegar", "arrive-arraiv", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.ARRIVE);
        SituacionGenerica sgEnAeropuerto21 = new SituacionGenerica("despegar", "take off-teic of", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.TAKE_OFF);
        SituacionGenerica sgEnAeropuerto22 = new SituacionGenerica("aterrizar", "land-land", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.LAND);
        SituacionGenerica sgEnAeropuerto23 = new SituacionGenerica("asiento de pasillo", "aisle seat-al si", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.AISLE_SEAT);
        SituacionGenerica sgEnAeropuerto24 = new SituacionGenerica("¿dónde está la zona de recojida de equipaje?", "where is the baggage claim?-uer is de bagich cleim?", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.WHERE_IS_THE_BAGGAGE_CLAIM);
        SituacionGenerica sgEnAeropuerto25 = new SituacionGenerica("escala", "stopover-stopouver", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.STOPOVER);
        SituacionGenerica sgEnAeropuerto26 = new SituacionGenerica("cinturón de seguridad", "seat belt-sit belt", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.SEAT_BELT);
        SituacionGenerica sgEnAeropuerto27 = new SituacionGenerica("sanitarios", "restrooms-restrums", Boolean.FALSE, TipoSituacion.EN_AEROPUERTO.getDescripcion(), NombresAudioParaSituaciones.RESTROOMS);

        // RECEPCIÓN DE HOTEL

        SituacionGenerica sgEnRecepcionDeHotel1 = new SituacionGenerica("equipaje", "luggage-lagich", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.LUGGAGE);
        SituacionGenerica sgEnRecepcionDeHotel2 = new SituacionGenerica("¿hay habitaciones disponibles?", "have you got any rooms available?-jav iu got enu rums availabl?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.HAVE_YOU_GOT_ANY_ROOMS_AVAILABLE);
        SituacionGenerica sgEnRecepcionDeHotel3 = new SituacionGenerica("¿tiene una reserva?", "do you have a reservation?-du iu jav e reserveishon?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.DO_YOU_HAVE_A_RESERVATION);
        SituacionGenerica sgEnRecepcionDeHotel4 = new SituacionGenerica("alojamiento", "accommodation-acamadeishon", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.ACCOMMODATION);
        SituacionGenerica sgEnRecepcionDeHotel5 = new SituacionGenerica("sábanas", "sheets-shits", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.SHEETS);
        SituacionGenerica sgEnRecepcionDeHotel6 = new SituacionGenerica("cama matrimonial", "double bed-dabl bed", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.DOUBLE_BED);
        SituacionGenerica sgEnRecepcionDeHotel7 = new SituacionGenerica("habitación doble", "double room-dabl rum", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.DOUBLE_ROOM);
        SituacionGenerica sgEnRecepcionDeHotel8 = new SituacionGenerica("reserva", "booking-bukin", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.BOOKING);
        SituacionGenerica sgEnRecepcionDeHotel9 = new SituacionGenerica("cama individual", "single bed-singl bed", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.SINGLE_BED);
        SituacionGenerica sgEnRecepcionDeHotel10 = new SituacionGenerica("habitación individual", "single room-singl rum", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.SINGLE_ROOM);
        SituacionGenerica sgEnRecepcionDeHotel11 = new SituacionGenerica("aire acondicionado", "air conditioning-er condishionin", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.AIR_CONDITIONING);
        SituacionGenerica sgEnRecepcionDeHotel12 = new SituacionGenerica("mantas", "blankets-blankets", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.BLANKETS);
        SituacionGenerica sgEnRecepcionDeHotel13 = new SituacionGenerica("comedor", "dining room-dainin rum", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.DINING_ROOM);
        SituacionGenerica sgEnRecepcionDeHotel14 = new SituacionGenerica("secador de pelo", "hair dryer-jer draier", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.HAIR_DRYER);
        SituacionGenerica sgEnRecepcionDeHotel15 = new SituacionGenerica("llave", "key-ki", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.KEY);
        SituacionGenerica sgEnRecepcionDeHotel16 = new SituacionGenerica("servicio de lavandería", "laundry service-londri servis", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.LAUNDRY_SERVICE);
        SituacionGenerica sgEnRecepcionDeHotel17 = new SituacionGenerica("colchón", "mattress-matres", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.MATTRESS);
        SituacionGenerica sgEnRecepcionDeHotel18 = new SituacionGenerica("almohada", "pillow-pilo", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.PILLOW);
        SituacionGenerica sgEnRecepcionDeHotel19 = new SituacionGenerica("factura", "receipt-ricip", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.RECEIPT);
        SituacionGenerica sgEnRecepcionDeHotel20 = new SituacionGenerica("jabón", "soap-sop", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.SOAP);
        SituacionGenerica sgEnRecepcionDeHotel21 = new SituacionGenerica("escaleras", "stairs-sters", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.STAIRS);
        SituacionGenerica sgEnRecepcionDeHotel22 = new SituacionGenerica("grifo", "tap-tap", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.TAP);
        SituacionGenerica sgEnRecepcionDeHotel23 = new SituacionGenerica("papel higiénico", "toilet paper-toilet peiper", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.TOILET_PAPER);
        SituacionGenerica sgEnRecepcionDeHotel24 = new SituacionGenerica("toalla", "towel-tauel", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.TOWEL);
        SituacionGenerica sgEnRecepcionDeHotel25 = new SituacionGenerica("camas gemelas", "twin beds-tuin beds", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.TWIN_BEDS);
        SituacionGenerica sgEnRecepcionDeHotel26 = new SituacionGenerica("quiero una habitación doble para dos noches", "i'd like a double room for two nights-aid laic e dobl rum for tu naits", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.ID_LIKE_A_DOUBLE_ROOM_FOR_TWO_NIGHTS);
        SituacionGenerica sgEnRecepcionDeHotel27 = new SituacionGenerica("¿cuánto cuesta por día?", "how much does it cost per day?-jau mach das it cost per dei?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.HOW_MUCH_DOES_IT_COST_PER_DAY);
        SituacionGenerica sgEnRecepcionDeHotel28 = new SituacionGenerica("¿está incluído el desayuno?", "is breakfast included?-is brecfast includit?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.IS_BREAKFAST_INCLUDED);
        SituacionGenerica sgEnRecepcionDeHotel29 = new SituacionGenerica("comodidades", "amenities-amenitis", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.AMENITIES);
        SituacionGenerica sgEnRecepcionDeHotel30 = new SituacionGenerica("balcón", "balcony-balconi", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.BALCONY);
        SituacionGenerica sgEnRecepcionDeHotel31 = new SituacionGenerica("¿puedes darme tu pasaporte?", "can i have your passport?- can ai jav ior pasport?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.CAN_I_HAVE_YOUR_PASSPORT);
        SituacionGenerica sgEnRecepcionDeHotel32 = new SituacionGenerica("¿de dónde vienes?", "from where are you coming from?-from uer ar iu camin from?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.FROM_WHERE_ARE_YOU_COMING_FROM);
        SituacionGenerica sgEnRecepcionDeHotel33 = new SituacionGenerica("la hora del desayuno", "breakfast time-brecfast taim", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.BREAKFAST_TIME);
        SituacionGenerica sgEnRecepcionDeHotel34 = new SituacionGenerica("entrada", "check in-chekin", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.CHECK_IN);
        SituacionGenerica sgEnRecepcionDeHotel35 = new SituacionGenerica("salida", "check out-chek aut", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.CHECK_OUT);
        SituacionGenerica sgEnRecepcionDeHotel36 = new SituacionGenerica("tenemos comodidades como", "we have amenities such as-ui jav amenitis sach as", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.WE_HAVE_AMENITIES_SUCH_AS);
        SituacionGenerica sgEnRecepcionDeHotel37 = new SituacionGenerica("¿podría indicarme cómo llegar, por favor?", "could you please give me directions?-culd iu plis giv mi direcshons", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.COULD_YOU_PLEASE_GIVE_ME_DIRECTIONS);
        SituacionGenerica sgEnRecepcionDeHotel38 = new SituacionGenerica("¿a qué hora se sirve el desayuno?", "at what time is the breakfast served?-at uot taim is de brekfast servt", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.AT_WHAT_TIME_IS_THE_BREAKFAST_SERVED);
        SituacionGenerica sgEnRecepcionDeHotel39 = new SituacionGenerica("¿podría sugerirme un buen restaurante?", "could you please suggest me a good restaurant?-culd iu plis sugshest mi a gud restoran?", Boolean.FALSE, TipoSituacion.RECEPCION_HOTEL.getDescripcion(), NombresAudioParaSituaciones.COULD_YOU_PLEASE_SUGGEST_ME_A_GOOD_RESTAURANT);

        // EN EL DOCTOR

        SituacionGenerica sgEnElDoctor1 = new SituacionGenerica("me duele mucho la garganta", "i have a sore throat-ai jav e sor zrout", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_SORE_THROAT);
        SituacionGenerica sgEnElDoctor2 = new SituacionGenerica("me duele cuando trago", "it hurts when i swallow-it jerts uen ai sualou", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IT_HURTS_WHEN_I_SWALLOW);
        SituacionGenerica sgEnElDoctor3 = new SituacionGenerica("anginas", "tonsillitis-tonselaites", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.TONSILLITIS);
        SituacionGenerica sgEnElDoctor4 = new SituacionGenerica("me duele la cabeza", "i have a headache-ai jav e jedec", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_HEADACHE);
        SituacionGenerica sgEnElDoctor5 = new SituacionGenerica("me duele la espalda", "i have a backache-ai jav e bakeic", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_BACKACHE);
        SituacionGenerica sgEnElDoctor6 = new SituacionGenerica("me duele el estómago", "i have a stomachace-ai jav e stomakeic", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_STOMACHACE);
        SituacionGenerica sgEnElDoctor7 = new SituacionGenerica("me duelen los dientes", "i have a toothache-ai jav e tuzeic", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_TOOTHACHE);
        SituacionGenerica sgEnElDoctor8 = new SituacionGenerica("me duele el oído", "i have an earache-ai jav an ireic", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_AN_EARACHE);
        SituacionGenerica sgEnElDoctor9 = new SituacionGenerica("estoy resfriado", "i have a cold-ai jav e cold", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_COLD);
        SituacionGenerica sgEnElDoctor10 = new SituacionGenerica("tengo la nariz tapada", "i have a blocked nose-ai jav e bloct nous", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_BLOCKED_NOSE);
        SituacionGenerica sgEnElDoctor11 = new SituacionGenerica("tengo tos", "i have a cough-ai jav e cof", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_COUGH);
        SituacionGenerica sgEnElDoctor12 = new SituacionGenerica("no puedo parar de estornudar", "i can't stop sneezing-ai cant stop sniisin", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_CANT_STOP_SNEEZING);
        SituacionGenerica sgEnElDoctor13 = new SituacionGenerica("tengo gripe", "i have the flu-ai jav de flu", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_THE_FLU);
        SituacionGenerica sgEnElDoctor14 = new SituacionGenerica("tengo fiebre (alta)", "i have a fever-ai jaf e fiver", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_FEVER);
        SituacionGenerica sgEnElDoctor15 = new SituacionGenerica("tengo fiebre (no tan alta)", "i have temperature-ai jav a tempecha", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_TEMPERATURE);
        SituacionGenerica sgEnElDoctor16 = new SituacionGenerica("me caí por las escaleras y torcí el tobillo", "i fell down the stairs and i twisted my ankle-ai fel down de sters and ai tuistid mai ancol", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_FELL_DOWN_THE_STAIRS_AND_I_TWISTED_MY_ANKLE);
        SituacionGenerica sgEnElDoctor17 = new SituacionGenerica("tengo un moretón", "i have a bruise-ai jav e brus", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_BRUISE);
        SituacionGenerica sgEnElDoctor18 = new SituacionGenerica("me fracturé una costilla", "i've fractured a rib-aiv fractut e rib", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IVE_FRACTURED_A_RIB);
        SituacionGenerica sgEnElDoctor19 = new SituacionGenerica("tengo un sarpullido", "i have a rash-ai jav e rash", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_A_RASH);
        SituacionGenerica sgEnElDoctor20 = new SituacionGenerica("tengo alergia a las nueces", "i'm allergic to nuts-aim alershic tu nats", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IM_ALLERGIC_TO_NUTS);
        SituacionGenerica sgEnElDoctor21 = new SituacionGenerica("estoy herido", "i'm injured-aim inshert", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IM_INJURED);
        SituacionGenerica sgEnElDoctor22 = new SituacionGenerica("me lesioné la rodilla jugando al fútbol", "i've injured my knee playing football-aiv inshert mai kni plein futbol", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IVE_INJURED_MY_KNEE_PLAYING_FOOTBALL);
        SituacionGenerica sgEnElDoctor23 = new SituacionGenerica("tengo una herida", "i have an injury-ai jav an inshuri", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_AN_INJURY);
        SituacionGenerica sgEnElDoctor24 = new SituacionGenerica("le dispararon y tenía una herida en su pecho", "they shot him and he had a wound on his chest-dei shot jim and ji jad e wunt on jis chest", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.THEY_SHOT_HIM_AND_HE_HAD_A_WOUND_ON_HIS_CHEST);
        SituacionGenerica sgEnElDoctor25 = new SituacionGenerica("me va a dar un infarto", "i'm going to have a heart attack-aim goin tu jaf a jart atac", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IM_GOING_TO_HAVE_A_HEART_ATTACK);
        SituacionGenerica sgEnElDoctor26 = new SituacionGenerica("necesito una benda", "i need a bandage-ai nid e bendich", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_NEED_A_BANDAGE);
        SituacionGenerica sgEnElDoctor27 = new SituacionGenerica("curita; tirita", "plaster-plasta", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.PLASTER);
        SituacionGenerica sgEnElDoctor28 = new SituacionGenerica("puntos (de cocer)", "stitches-stichis", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.STITCHES);
        SituacionGenerica sgEnElDoctor29 = new SituacionGenerica("me dieron five puntos en la mano", "i got five stitches in my hand-ai got faiv stitchis in mai jand", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_GOT_FIVE_STITCHES_IN_MY_HAND);
        SituacionGenerica sgEnElDoctor30 = new SituacionGenerica("necesitas reposo", "you need to rest-iu nid tu rest", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.YOU_NEED_TO_REST);
        SituacionGenerica sgEnElDoctor31 = new SituacionGenerica("pastillas", "pills-pils", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.PILLS);
        SituacionGenerica sgEnElDoctor32 = new SituacionGenerica("inyección", "injection-inshecshion", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.INJECTION);
        SituacionGenerica sgEnElDoctor33 = new SituacionGenerica("el doctor me dió pastillas para calmar el dolor", "the doctor gave me some painkillers-de doctor geiv mi som peinkilers", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.THE_DOCTOR_GAVE_ME_SOME_PAINKILLERS);
        SituacionGenerica sgEnElDoctor34 = new SituacionGenerica("yeso", "plaster cast-plaste cast", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.PLASTER_CAST);
        SituacionGenerica sgEnElDoctor35 = new SituacionGenerica("me van a operar de la rodilla", "i'm going to have an operation on my knee-aim goin tu jav an opereishon on mai kni", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.IM_GOING_TO_HAVE_AN_OPERATION_ON_MY_KNEE);
        SituacionGenerica sgEnElDoctor36 = new SituacionGenerica("siento náuseas", "i feel nauseous-ai fil noshes", Boolean.FALSE, TipoSituacion.EN_DOCTOR.getDescripcion(), NombresAudioParaSituaciones.I_FEEL_NAUSEOUS);

        // ENTREVISTA DE TRABAJO

        SituacionGenerica sgEntrevista1 = new SituacionGenerica("entevista", "interview-inerviu", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.INTERVIEW);
        SituacionGenerica sgEntrevista2 = new SituacionGenerica("logro", "accomplishment-acomplishment", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.ACCOMPLISHMENT);
        SituacionGenerica sgEntrevista3 = new SituacionGenerica("solicitar un trabajo", "apply-aplai", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.APPLY);
        SituacionGenerica sgEntrevista4 = new SituacionGenerica("candidato", "candidate-kendideit", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.CANDIDATE);
        SituacionGenerica sgEntrevista5 = new SituacionGenerica("objetivo", "goal-gol", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.GOAL);
        SituacionGenerica sgEntrevista6 = new SituacionGenerica("contratar", "hire-jaier", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.HIRE);
        SituacionGenerica sgEntrevista7 = new SituacionGenerica("liderazgo", "leadership-lidershep", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.LEADERSHIP);
        SituacionGenerica sgEntrevista8 = new SituacionGenerica("responsabilidad", "responsibility-risponsabiliti", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.RESPONSIBILITY);
        SituacionGenerica sgEntrevista9 = new SituacionGenerica("curriculum", "resume-risum", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.RESUME);
        SituacionGenerica sgEntrevista10 = new SituacionGenerica("expectativas salariales", "salary expectations-salari espectishons", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.SALARY_EXPECTATIONS);
        SituacionGenerica sgEntrevista11 = new SituacionGenerica("capacidades", "skills-skils", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.SKILLS);
        SituacionGenerica sgEntrevista12 = new SituacionGenerica("fortaleza", "strength-strengt", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.STRENGTH);
        SituacionGenerica sgEntrevista13 = new SituacionGenerica("tarea", "task-tasc", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.TASK);
        SituacionGenerica sgEntrevista14 = new SituacionGenerica("debilidades", "weakness-uicnes", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WEAKNESS);
        SituacionGenerica sgEntrevista15 = new SituacionGenerica("¿qué puedes decirme sobre ti?", "what can you tell me about yourself?-uot can iu tel mi abau iorself?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_CAN_YOU_TELL_ME_ABOUT_YOURSELF);
        SituacionGenerica sgEntrevista16 = new SituacionGenerica("¿cómo te describirían tu familia y amigos?", "how would your family and friends describe you?-jou wuld ior famili and frends discraib iu?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.HOW_WOULD_YOUR_FAMILY_AND_FRIENDS_DESCRIBE_YOU);
        SituacionGenerica sgEntrevista17 = new SituacionGenerica("¿eres bueno trabajando en equipo o prefieres trabajar solo?", "are you a good team worker or do you prefer to work on your own?-ar iu e gud tim uorker or du iu prifer tu uork on ior oon?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.ARE_YOU_A_GOOD_TEAM_WORKER_OR_DO_YOU_PREFER_TO_WORK_ON_YOUR_OWN);
        SituacionGenerica sgEntrevista18 = new SituacionGenerica("¿cuáles son tus fortalezas?", "what are your strengths?-uot ar ior strengts?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_ARE_YOUR_STRENGTHS);
        SituacionGenerica sgEntrevista19 = new SituacionGenerica("¿cuáles son tus debilidades?", "what are your weaknesses?-uot ar ior uikneses?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_ARE_YOUR_WEAKNESSES);
        SituacionGenerica sgEntrevista20 = new SituacionGenerica("¿por qué deberíamos contratarte?", "why should we hire you?-uai shuld ui jaier iu?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHY_SHOULD_WE_HIRE_YOU);
        SituacionGenerica sgEntrevista21 = new SituacionGenerica("¿cuál ha sido tu mayor logro?", "what has been your greatest accomplishment?-uat jas bin ior grirest acomplishment?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_HAS_BEEN_YOUR_GREATEST_ACCOMPLISHMENT);
        SituacionGenerica sgEntrevista22 = new SituacionGenerica("¿qué sabes acerca de esta organización?", "what do you know about this company?-uat du iu nou abau dis compani", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_DO_YOU_KNOW_ABOUT_THIS_COMPANY);
        SituacionGenerica sgEntrevista23 = new SituacionGenerica("¿dónde te ves a ti mismo en cinco diez años?", "where do you see yourself in five ten years?-uer du iu si iorself in faiv ten iars?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHERE_DO_YOU_SEE_YOURSELF_IN_FIVE_TEN_YEARS);
        SituacionGenerica sgEntrevista24 = new SituacionGenerica("¿cuáles son tus objetivos en la vida?", "what are your goals in life?-uat ar ior-uat ar ior gols in laif?", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.WHAT_ARE_YOUR_GOALS_IN_LIFE);
        SituacionGenerica sgEntrevista25 = new SituacionGenerica("tengo una cita a las tres y media", "i have an appointment at three thirty-i jav an apointment at zri zersti", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.I_HAVE_AN_APPOINTMENT_AT_THREE_THIRTY);
        SituacionGenerica sgEntrevista26 = new SituacionGenerica("toma asiento", "have a seat-jav e sit", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.HAVE_A_SEAT);
        SituacionGenerica sgEntrevista27 = new SituacionGenerica("hablame sobre vos", "tell me a little bit about yourself-tel mi e litl bit abau iorself", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.TELL_ME_A_LITTLE_BIT_ABOUT_YOURSELF);
        SituacionGenerica sgEntrevista28 = new SituacionGenerica("buena capacidad de comunicación", "good communication skills-gud comunikeishon skils", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.GOOD_COMMUNICATION_SKILLS);
        SituacionGenerica sgEntrevista29 = new SituacionGenerica("responsable", "responsible-risponsabl", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.RESPONSIBLE);
        SituacionGenerica sgEntrevista30 = new SituacionGenerica("confiable", "reliable-rilaiabl", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.RELIABLE);
        SituacionGenerica sgEntrevista31 = new SituacionGenerica("eficiente", "efficient-efishient", Boolean.FALSE, TipoSituacion.ENTREVISTA_TRABAJO.getDescripcion(), NombresAudioParaSituaciones.EFFICIENT);

        // EN EL SUPERMERCADO

        SituacionGenerica sgSupermercado1 = new SituacionGenerica("supermercado", "supermarket-supermarke", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.SUPERMARKET);
        SituacionGenerica sgSupermercado2 = new SituacionGenerica("pasillo", "aisle-eel", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.AISLE);
        SituacionGenerica sgSupermercado3 = new SituacionGenerica("bolsa de plástico", "plastic bag-plastic bag", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.PLASTIC_BAG);
        SituacionGenerica sgSupermercado4 = new SituacionGenerica("20 porciento de descuento", "20 percent off-20 percent of", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.PERCENT_OFF);
        SituacionGenerica sgSupermercado5 = new SituacionGenerica("panadería", "bakery-bekeri", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BAKERY);
        SituacionGenerica sgSupermercado6 = new SituacionGenerica("lector de código de barras", "barcode reader-barcoud rider", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BARCODE_READER);
        SituacionGenerica sgSupermercado7 = new SituacionGenerica("bebidas", "beverages-bevrishis", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BEVERAGES);
        SituacionGenerica sgSupermercado8 = new SituacionGenerica("productos enlatados", "canned goods-ken gods", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CANNED_GOODS);
        SituacionGenerica sgSupermercado9 = new SituacionGenerica("efectivo", "cash-cash", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CASH);
        SituacionGenerica sgSupermercado10 = new SituacionGenerica("cajero", "cashier-cashier", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CASHIER);
        SituacionGenerica sgSupermercado11 = new SituacionGenerica("vuelto/cambio", "change-cheinsh", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CHANGE);
        SituacionGenerica sgSupermercado12 = new SituacionGenerica("farmacia", "drugstore-drogstor", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.DRUGSTORE);
        SituacionGenerica sgSupermercado13 = new SituacionGenerica("cliente", "customer-castomer", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CUSTOMER);
        SituacionGenerica sgSupermercado14 = new SituacionGenerica("rotisería", "delicatessen-delicatesen", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.DELICATESSEN);
        SituacionGenerica sgSupermercado15 = new SituacionGenerica("sector", "department-dipartment", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.DEPARTMENT);
        SituacionGenerica sgSupermercado16 = new SituacionGenerica("productos electrónicos", "electronics-ilectronics", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.ELECTRONICS);
        SituacionGenerica sgSupermercado17 = new SituacionGenerica("caja rápida", "express lane-expres lein", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.EXPRESS_LANE);
        SituacionGenerica sgSupermercado18 = new SituacionGenerica("comida congelada", "frozen food-frousen fud", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.FROZEN_FOOD);
        SituacionGenerica sgSupermercado19 = new SituacionGenerica("a mitad de precio", "half price-jaf prais", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.HALF_PRICE);
        SituacionGenerica sgSupermercado20 = new SituacionGenerica("precio", "price-prais", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.PRICE);
        SituacionGenerica sgSupermercado21 = new SituacionGenerica("compra", "purchase-purches", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.PURCHASE);
        SituacionGenerica sgSupermercado22 = new SituacionGenerica("balanza", "scales-skels", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.SCALES);
        SituacionGenerica sgSupermercado23 = new SituacionGenerica("canasta", "basket-basket", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BASKET);
        SituacionGenerica sgSupermercado24 = new SituacionGenerica("horario de la tienda", "store hours-stor auers", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.STORE_HOURS);
        SituacionGenerica sgSupermercado25 = new SituacionGenerica("productos lácteos", "dairy products-deri prodocts", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.DAIRY_PRODUCTS);
        SituacionGenerica sgSupermercado26 = new SituacionGenerica("leche descremada", "skim milk-skim milk", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.SKIM_MILK);
        SituacionGenerica sgSupermercado27 = new SituacionGenerica("huevos", "eggs-egs", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.EGGS);
        SituacionGenerica sgSupermercado28 = new SituacionGenerica("mantequilla", "butter-bater", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BUTTER);
        SituacionGenerica sgSupermercado29 = new SituacionGenerica("levadura", "yeast-ist  ", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.YEAST);
        SituacionGenerica sgSupermercado30 = new SituacionGenerica("harina", "flour-flauer", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.FLOUR);
        SituacionGenerica sgSupermercado31 = new SituacionGenerica("harina de maíz", "corn flour-corn flauer", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CORN_FLOUR);
        SituacionGenerica sgSupermercado32 = new SituacionGenerica("sal", "salt-solt", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.SALT);
        SituacionGenerica sgSupermercado33 = new SituacionGenerica("azúcar", "sugar-shugar", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.SUGAR);
        SituacionGenerica sgSupermercado34 = new SituacionGenerica("zanahoria", "carrot-kerrot", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CARROT);
        SituacionGenerica sgSupermercado35 = new SituacionGenerica("carne", "meat-mit", Boolean.FALSE, TipoSituacion.MESERO.getDescripcion(), NombresAudioParaSituaciones.MEAT);
        SituacionGenerica sgSupermercado36 = new SituacionGenerica("pescado", "fish-fish", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.FISH);
        SituacionGenerica sgSupermercado37 = new SituacionGenerica("verduras", "vegetables-veshtbls", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.VEGETABLES);
        SituacionGenerica sgSupermercado38 = new SituacionGenerica("fideos", "noodles-nurls", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.NOODLES);
        SituacionGenerica sgSupermercado39 = new SituacionGenerica("arroz", "rice-rais  ", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.RICE);
        SituacionGenerica sgSupermercado40 = new SituacionGenerica("lentejas", "lentils-lentels    ", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.LENTILS);
        SituacionGenerica sgSupermercado41 = new SituacionGenerica("cebolla", "onion-onion    ", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.ONION);
        SituacionGenerica sgSupermercado42 = new SituacionGenerica("tomate", "tomato-tomeirou", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.TOMATO);
        SituacionGenerica sgSupermercado43 = new SituacionGenerica("tomates", "tomatoes-tomeiros", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.TOMATOES);
        SituacionGenerica sgSupermercado44 = new SituacionGenerica("lechuga", "lettuce-leres", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.LETTUCE);
        SituacionGenerica sgSupermercado45 = new SituacionGenerica("queso", "cheese-chis", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.CHEESE);
        SituacionGenerica sgSupermercado46 = new SituacionGenerica("pan", "bread-bred", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.BREAD);
        SituacionGenerica sgSupermercado47 = new SituacionGenerica("ajo", "garlic-garlic", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.GARLIC);
        SituacionGenerica sgSupermercado48 = new SituacionGenerica("¿cuánto cuesta?", "how much does it cost?-jou mach das it coust?", Boolean.FALSE, TipoSituacion.SUPERMERCADO.getDescripcion(), NombresAudioParaSituaciones.HOW_MUCH_DOES_IT_COST);

        // UBICARSE

        SituacionGenerica sgUbicarse1 = new SituacionGenerica("derecho", "straight-streit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.STRAIGHT);
        SituacionGenerica sgUbicarse2 = new SituacionGenerica("adelante", "ahead-ajed", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.AHEAD);
        SituacionGenerica sgUbicarse3 = new SituacionGenerica("seguí derecho", "go straight-gou streit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.GO_STRAIGHT);
        SituacionGenerica sgUbicarse4 = new SituacionGenerica("seguí adelante", "go ahead-gou ajed", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.GO_AHEAD);
        SituacionGenerica sgUbicarse5 = new SituacionGenerica("pasá", "go past-gou past", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.GO_PAST);
        SituacionGenerica sgUbicarse6 = new SituacionGenerica("a la derecha", "on the right-on de raigt", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ON_THE_RIGHT);
        SituacionGenerica sgUbicarse7 = new SituacionGenerica("a la izquierda", "on the left-on de left", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ON_THE_LEFT);
        SituacionGenerica sgUbicarse8 = new SituacionGenerica("al lado de", "next to-next tu", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.NEXT_TO);
        SituacionGenerica sgUbicarse9 = new SituacionGenerica("en la esquina", "at the corner-at de corner", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.AT_THE_CORNER);
        SituacionGenerica sgUbicarse10 = new SituacionGenerica("cuadra", "block-bloc", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.BLOCK);
        SituacionGenerica sgUbicarse11 = new SituacionGenerica("semáforo", "stoplight-stoplait", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.STOPLIGHT);
        SituacionGenerica sgUbicarse12 = new SituacionGenerica("intersección", "intersection-inersecshion", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.INTERSECTION);
        SituacionGenerica sgUbicarse13 = new SituacionGenerica("calle", "road-roud", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ROAD);
        SituacionGenerica sgUbicarse14 = new SituacionGenerica("avenida", "avenue-avenu", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.AVENUE);
        SituacionGenerica sgUbicarse15 = new SituacionGenerica("rotonda", "roundabout-raundabau", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ROUNDABOUT);
        SituacionGenerica sgUbicarse16 = new SituacionGenerica("vereda", "sidewalk-saiduoc", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.SIDEWALK);
        SituacionGenerica sgUbicarse17 = new SituacionGenerica("calle lateral", "side street-said strit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.SIDE_STREET);
        SituacionGenerica sgUbicarse18 = new SituacionGenerica("subí/baja", "go up/down-gou ap/daun", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.GO_UP_DOWN);
        SituacionGenerica sgUbicarse19 = new SituacionGenerica("estoy buscando", "i'm looking for-aim lukin for", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.IM_LOOKING_FOR);
        SituacionGenerica sgUbicarse20 = new SituacionGenerica("me perdí", "i'm lost-aim lost", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.IM_LOST);
        SituacionGenerica sgUbicarse21 = new SituacionGenerica("en frente de", "opposite-oposit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.OPPOSITE);
        SituacionGenerica sgUbicarse22 = new SituacionGenerica("¿está lejos?", "is it far?-is it far", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.IS_IT_FAR);
        SituacionGenerica sgUbicarse23 = new SituacionGenerica("¿a qué distancia está?", "how far is it?-jou far is it?", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.HOW_FAR_IS_IT);
        SituacionGenerica sgUbicarse24 = new SituacionGenerica("cruzá la calle", "cross the street-cros de strit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.CROSS_THE_STREET);
        SituacionGenerica sgUbicarse25 = new SituacionGenerica("muy cerca", "quite close-cuait clos", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.QUITE_CLOSE);
        SituacionGenerica sgUbicarse26 = new SituacionGenerica("dobla en la esquina", "turn at the corner-tern at de corner", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.TURN_AT_THE_CORNER);
        SituacionGenerica sgUbicarse27 = new SituacionGenerica("¿voy bien para el centro?", "is this the right way to the center?-is dis de rai uei tu de center?", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.IS_THIS_THE_RIGHT_WAY_TO_THE_CENTER);
        SituacionGenerica sgUbicarse28 = new SituacionGenerica("¿dónde tomo el bus a?", "where do i catch the bus for?-uer du ai cach de bas for?", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.WHERE_DO_I_CATCH_THE_BUS_FOR);
        SituacionGenerica sgUbicarse29 = new SituacionGenerica("está a 100 metros de acá", "it's 100 meters away-its 100 meters euei", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ITS_METTERS_AWAY);
        SituacionGenerica sgUbicarse30 = new SituacionGenerica("a unos diez min caminando", "about ten min on foot-abaut ten min on fut", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ABOUT_TEN_MIN_ON_FOOT);
        SituacionGenerica sgUbicarse31 = new SituacionGenerica("está en la vereda de en frente", "it's on the other side of the street-its on de oder said of de strit", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ITS_ON_THE_OTHER_SIDE_OF_THE_STREET);
        SituacionGenerica sgUbicarse32 = new SituacionGenerica("¿se puede ir en tren?", "can you get there by train?-can iu get der bai trein?", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.CAN_YOU_GET_THERE_BY_TRAIN);
        SituacionGenerica sgUbicarse33 = new SituacionGenerica("está del lado opuesto de x", "it's opposite to the x-its ouposit tu de x", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.ITS_OPPOSITE_TO_THE_X);
        SituacionGenerica sgUbicarse34 = new SituacionGenerica("no soy de acá", "i'm not from around here-aim not from araund jiar", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.IM_NOT_FROM_AROUND_HERE);
        SituacionGenerica sgUbicarse35 = new SituacionGenerica("¿puedes indicarlo en el mapa?", "can you show me on the map?-can iu show mi on de map?", Boolean.FALSE, TipoSituacion.UBICARSE.getDescripcion(), NombresAudioParaSituaciones.CAN_YOU_SHOW_ME_ON_THE_MAP);

        listaDeSituacionGenerica.add(sgMesero1);
        listaDeSituacionGenerica.add(sgMesero2);
        listaDeSituacionGenerica.add(sgMesero3);
        listaDeSituacionGenerica.add(sgMesero4);
        listaDeSituacionGenerica.add(sgMesero5);
        listaDeSituacionGenerica.add(sgMesero6);
        listaDeSituacionGenerica.add(sgMesero7);
        listaDeSituacionGenerica.add(sgMesero8);
        listaDeSituacionGenerica.add(sgMesero9);
        listaDeSituacionGenerica.add(sgMesero10);
        listaDeSituacionGenerica.add(sgMesero11);
        listaDeSituacionGenerica.add(sgMesero12);
        listaDeSituacionGenerica.add(sgMesero13);
        listaDeSituacionGenerica.add(sgMesero14);
        listaDeSituacionGenerica.add(sgMesero15);
        listaDeSituacionGenerica.add(sgMesero16);
        listaDeSituacionGenerica.add(sgMesero17);
        listaDeSituacionGenerica.add(sgMesero18);
        listaDeSituacionGenerica.add(sgMesero19);
        listaDeSituacionGenerica.add(sgMesero20);
        listaDeSituacionGenerica.add(sgMesero21);
        listaDeSituacionGenerica.add(sgMesero22);
        listaDeSituacionGenerica.add(sgMesero23);
        listaDeSituacionGenerica.add(sgMesero24);
        listaDeSituacionGenerica.add(sgMesero25);
        listaDeSituacionGenerica.add(sgMesero26);
        listaDeSituacionGenerica.add(sgMesero27);
        listaDeSituacionGenerica.add(sgMesero28);
        listaDeSituacionGenerica.add(sgMesero29);
        listaDeSituacionGenerica.add(sgMesero30);
        listaDeSituacionGenerica.add(sgMesero31);
        listaDeSituacionGenerica.add(sgMesero32);
        listaDeSituacionGenerica.add(sgMesero33);
        listaDeSituacionGenerica.add(sgMesero34);
        listaDeSituacionGenerica.add(sgMesero35);
        listaDeSituacionGenerica.add(sgMesero36);
        listaDeSituacionGenerica.add(sgMesero37);
        listaDeSituacionGenerica.add(sgMesero38);
        listaDeSituacionGenerica.add(sgMesero39);
        listaDeSituacionGenerica.add(sgMesero40);
        listaDeSituacionGenerica.add(sgMesero41);
        listaDeSituacionGenerica.add(sgMesero42);
        listaDeSituacionGenerica.add(sgMesero43);
        listaDeSituacionGenerica.add(sgMesero44);
        listaDeSituacionGenerica.add(sgMesero45);
        listaDeSituacionGenerica.add(sgMesero46);
        listaDeSituacionGenerica.add(sgMesero47);

        listaDeSituacionGenerica.add(sgEnRestoran1);
        listaDeSituacionGenerica.add(sgEnRestoran2);
        listaDeSituacionGenerica.add(sgEnRestoran3);
        listaDeSituacionGenerica.add(sgEnRestoran4);
        listaDeSituacionGenerica.add(sgEnRestoran5);
        listaDeSituacionGenerica.add(sgEnRestoran6);
        listaDeSituacionGenerica.add(sgEnRestoran7);
        listaDeSituacionGenerica.add(sgEnRestoran8);
        listaDeSituacionGenerica.add(sgEnRestoran9);
        listaDeSituacionGenerica.add(sgEnRestoran10);
        listaDeSituacionGenerica.add(sgEnRestoran11);
        listaDeSituacionGenerica.add(sgEnRestoran12);
        listaDeSituacionGenerica.add(sgEnRestoran13);
        listaDeSituacionGenerica.add(sgEnRestoran14);
        listaDeSituacionGenerica.add(sgEnRestoran15);
        listaDeSituacionGenerica.add(sgEnRestoran16);
        listaDeSituacionGenerica.add(sgEnRestoran17);
        listaDeSituacionGenerica.add(sgEnRestoran18);
        listaDeSituacionGenerica.add(sgEnRestoran19);
        listaDeSituacionGenerica.add(sgEnRestoran20);
        listaDeSituacionGenerica.add(sgEnRestoran21);
        listaDeSituacionGenerica.add(sgEnRestoran22);
        listaDeSituacionGenerica.add(sgEnRestoran23);
        listaDeSituacionGenerica.add(sgEnRestoran24);
        listaDeSituacionGenerica.add(sgEnRestoran25);
        listaDeSituacionGenerica.add(sgEnRestoran26);
        listaDeSituacionGenerica.add(sgEnRestoran27);
        listaDeSituacionGenerica.add(sgEnRestoran28);
        listaDeSituacionGenerica.add(sgEnRestoran29);
        listaDeSituacionGenerica.add(sgEnRestoran30);
        listaDeSituacionGenerica.add(sgEnRestoran31);
        listaDeSituacionGenerica.add(sgEnRestoran32);
        listaDeSituacionGenerica.add(sgEnRestoran33);
        listaDeSituacionGenerica.add(sgEnRestoran34);
        listaDeSituacionGenerica.add(sgEnRestoran35);
        listaDeSituacionGenerica.add(sgEnRestoran36);
        listaDeSituacionGenerica.add(sgEnRestoran37);
        listaDeSituacionGenerica.add(sgEnRestoran38);
        listaDeSituacionGenerica.add(sgEnRestoran39);
        listaDeSituacionGenerica.add(sgEnRestoran40);
        listaDeSituacionGenerica.add(sgEnRestoran41);
        listaDeSituacionGenerica.add(sgEnRestoran42);
        listaDeSituacionGenerica.add(sgEnRestoran43);
        listaDeSituacionGenerica.add(sgEnRestoran44);
        listaDeSituacionGenerica.add(sgEnRestoran45);
        listaDeSituacionGenerica.add(sgEnRestoran46);
        listaDeSituacionGenerica.add(sgEnRestoran47);
        listaDeSituacionGenerica.add(sgEnRestoran48);
        listaDeSituacionGenerica.add(sgEnRestoran49);
        listaDeSituacionGenerica.add(sgEnRestoran50);
        listaDeSituacionGenerica.add(sgEnRestoran51);
        listaDeSituacionGenerica.add(sgEnRestoran52);
        listaDeSituacionGenerica.add(sgEnRestoran53);
        listaDeSituacionGenerica.add(sgEnRestoran54);
        listaDeSituacionGenerica.add(sgEnRestoran55);
        listaDeSituacionGenerica.add(sgEnRestoran56);
        listaDeSituacionGenerica.add(sgEnRestoran57);
        listaDeSituacionGenerica.add(sgEnRestoran58);
        listaDeSituacionGenerica.add(sgEnRestoran59);
        listaDeSituacionGenerica.add(sgEnRestoran60);
        listaDeSituacionGenerica.add(sgEnRestoran61);
        listaDeSituacionGenerica.add(sgEnRestoran62);
        listaDeSituacionGenerica.add(sgEnRestoran63);
        listaDeSituacionGenerica.add(sgEnRestoran64);
        listaDeSituacionGenerica.add(sgEnRestoran65);
        listaDeSituacionGenerica.add(sgEnRestoran66);
        listaDeSituacionGenerica.add(sgEnRestoran67);
        listaDeSituacionGenerica.add(sgEnRestoran68);
        listaDeSituacionGenerica.add(sgEnRestoran69);

        listaDeSituacionGenerica.add(sgEnAeropuerto1);
        listaDeSituacionGenerica.add(sgEnAeropuerto2);
        listaDeSituacionGenerica.add(sgEnAeropuerto3);
        listaDeSituacionGenerica.add(sgEnAeropuerto4);
        listaDeSituacionGenerica.add(sgEnAeropuerto5);
        listaDeSituacionGenerica.add(sgEnAeropuerto6);
        listaDeSituacionGenerica.add(sgEnAeropuerto7);
        listaDeSituacionGenerica.add(sgEnAeropuerto8);
        listaDeSituacionGenerica.add(sgEnAeropuerto9);
        listaDeSituacionGenerica.add(sgEnAeropuerto10);
        listaDeSituacionGenerica.add(sgEnAeropuerto11);
        listaDeSituacionGenerica.add(sgEnAeropuerto12);
        listaDeSituacionGenerica.add(sgEnAeropuerto13);
        listaDeSituacionGenerica.add(sgEnAeropuerto14);
        listaDeSituacionGenerica.add(sgEnAeropuerto15);
        listaDeSituacionGenerica.add(sgEnAeropuerto16);
        listaDeSituacionGenerica.add(sgEnAeropuerto17);
        listaDeSituacionGenerica.add(sgEnAeropuerto18);
        listaDeSituacionGenerica.add(sgEnAeropuerto19);
        listaDeSituacionGenerica.add(sgEnAeropuerto20);
        listaDeSituacionGenerica.add(sgEnAeropuerto21);
        listaDeSituacionGenerica.add(sgEnAeropuerto22);
        listaDeSituacionGenerica.add(sgEnAeropuerto23);
        listaDeSituacionGenerica.add(sgEnAeropuerto24);
        listaDeSituacionGenerica.add(sgEnAeropuerto25);
        listaDeSituacionGenerica.add(sgEnAeropuerto26);
        listaDeSituacionGenerica.add(sgEnAeropuerto27);

        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel1);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel2);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel3);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel4);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel5);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel6);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel7);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel8);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel9);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel10);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel11);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel12);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel13);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel14);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel15);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel16);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel17);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel18);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel19);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel20);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel21);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel22);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel23);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel24);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel25);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel26);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel27);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel28);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel29);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel30);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel31);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel32);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel33);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel34);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel35);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel36);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel37);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel38);
        listaDeSituacionGenerica.add(sgEnRecepcionDeHotel39);

        listaDeSituacionGenerica.add(sgEnElDoctor1);
        listaDeSituacionGenerica.add(sgEnElDoctor2);
        listaDeSituacionGenerica.add(sgEnElDoctor3);
        listaDeSituacionGenerica.add(sgEnElDoctor4);
        listaDeSituacionGenerica.add(sgEnElDoctor5);
        listaDeSituacionGenerica.add(sgEnElDoctor6);
        listaDeSituacionGenerica.add(sgEnElDoctor7);
        listaDeSituacionGenerica.add(sgEnElDoctor8);
        listaDeSituacionGenerica.add(sgEnElDoctor9);
        listaDeSituacionGenerica.add(sgEnElDoctor10);
        listaDeSituacionGenerica.add(sgEnElDoctor11);
        listaDeSituacionGenerica.add(sgEnElDoctor12);
        listaDeSituacionGenerica.add(sgEnElDoctor13);
        listaDeSituacionGenerica.add(sgEnElDoctor14);
        listaDeSituacionGenerica.add(sgEnElDoctor15);
        listaDeSituacionGenerica.add(sgEnElDoctor16);
        listaDeSituacionGenerica.add(sgEnElDoctor17);
        listaDeSituacionGenerica.add(sgEnElDoctor18);
        listaDeSituacionGenerica.add(sgEnElDoctor19);
        listaDeSituacionGenerica.add(sgEnElDoctor20);
        listaDeSituacionGenerica.add(sgEnElDoctor21);
        listaDeSituacionGenerica.add(sgEnElDoctor22);
        listaDeSituacionGenerica.add(sgEnElDoctor23);
        listaDeSituacionGenerica.add(sgEnElDoctor24);
        listaDeSituacionGenerica.add(sgEnElDoctor25);
        listaDeSituacionGenerica.add(sgEnElDoctor26);
        listaDeSituacionGenerica.add(sgEnElDoctor27);
        listaDeSituacionGenerica.add(sgEnElDoctor28);
        listaDeSituacionGenerica.add(sgEnElDoctor29);
        listaDeSituacionGenerica.add(sgEnElDoctor30);
        listaDeSituacionGenerica.add(sgEnElDoctor31);
        listaDeSituacionGenerica.add(sgEnElDoctor32);
        listaDeSituacionGenerica.add(sgEnElDoctor33);
        listaDeSituacionGenerica.add(sgEnElDoctor34);
        listaDeSituacionGenerica.add(sgEnElDoctor35);
        listaDeSituacionGenerica.add(sgEnElDoctor36);

        listaDeSituacionGenerica.add(sgEntrevista1);
        listaDeSituacionGenerica.add(sgEntrevista2);
        listaDeSituacionGenerica.add(sgEntrevista3);
        listaDeSituacionGenerica.add(sgEntrevista4);
        listaDeSituacionGenerica.add(sgEntrevista5);
        listaDeSituacionGenerica.add(sgEntrevista6);
        listaDeSituacionGenerica.add(sgEntrevista7);
        listaDeSituacionGenerica.add(sgEntrevista8);
        listaDeSituacionGenerica.add(sgEntrevista9);
        listaDeSituacionGenerica.add(sgEntrevista10);
        listaDeSituacionGenerica.add(sgEntrevista11);
        listaDeSituacionGenerica.add(sgEntrevista12);
        listaDeSituacionGenerica.add(sgEntrevista13);
        listaDeSituacionGenerica.add(sgEntrevista14);
        listaDeSituacionGenerica.add(sgEntrevista15);
        listaDeSituacionGenerica.add(sgEntrevista16);
        listaDeSituacionGenerica.add(sgEntrevista17);
        listaDeSituacionGenerica.add(sgEntrevista18);
        listaDeSituacionGenerica.add(sgEntrevista19);
        listaDeSituacionGenerica.add(sgEntrevista20);
        listaDeSituacionGenerica.add(sgEntrevista21);
        listaDeSituacionGenerica.add(sgEntrevista22);
        listaDeSituacionGenerica.add(sgEntrevista23);
        listaDeSituacionGenerica.add(sgEntrevista24);
        listaDeSituacionGenerica.add(sgEntrevista25);
        listaDeSituacionGenerica.add(sgEntrevista26);
        listaDeSituacionGenerica.add(sgEntrevista27);
        listaDeSituacionGenerica.add(sgEntrevista28);
        listaDeSituacionGenerica.add(sgEntrevista29);
        listaDeSituacionGenerica.add(sgEntrevista30);
        listaDeSituacionGenerica.add(sgEntrevista31);

        listaDeSituacionGenerica.add(sgSupermercado1);
        listaDeSituacionGenerica.add(sgSupermercado2);
        listaDeSituacionGenerica.add(sgSupermercado3);
        listaDeSituacionGenerica.add(sgSupermercado4);
        listaDeSituacionGenerica.add(sgSupermercado5);
        listaDeSituacionGenerica.add(sgSupermercado6);
        listaDeSituacionGenerica.add(sgSupermercado7);
        listaDeSituacionGenerica.add(sgSupermercado8);
        listaDeSituacionGenerica.add(sgSupermercado9);
        listaDeSituacionGenerica.add(sgSupermercado10);
        listaDeSituacionGenerica.add(sgSupermercado11);
        listaDeSituacionGenerica.add(sgSupermercado12);
        listaDeSituacionGenerica.add(sgSupermercado13);
        listaDeSituacionGenerica.add(sgSupermercado14);
        listaDeSituacionGenerica.add(sgSupermercado15);
        listaDeSituacionGenerica.add(sgSupermercado16);
        listaDeSituacionGenerica.add(sgSupermercado17);
        listaDeSituacionGenerica.add(sgSupermercado18);
        listaDeSituacionGenerica.add(sgSupermercado19);
        listaDeSituacionGenerica.add(sgSupermercado20);
        listaDeSituacionGenerica.add(sgSupermercado21);
        listaDeSituacionGenerica.add(sgSupermercado22);
        listaDeSituacionGenerica.add(sgSupermercado23);
        listaDeSituacionGenerica.add(sgSupermercado24);
        listaDeSituacionGenerica.add(sgSupermercado25);
        listaDeSituacionGenerica.add(sgSupermercado26);
        listaDeSituacionGenerica.add(sgSupermercado27);
        listaDeSituacionGenerica.add(sgSupermercado28);
        listaDeSituacionGenerica.add(sgSupermercado29);
        listaDeSituacionGenerica.add(sgSupermercado30);
        listaDeSituacionGenerica.add(sgSupermercado31);
        listaDeSituacionGenerica.add(sgSupermercado32);
        listaDeSituacionGenerica.add(sgSupermercado33);
        listaDeSituacionGenerica.add(sgSupermercado34);
        listaDeSituacionGenerica.add(sgSupermercado35);
        listaDeSituacionGenerica.add(sgSupermercado36);
        listaDeSituacionGenerica.add(sgSupermercado37);
        listaDeSituacionGenerica.add(sgSupermercado38);
        listaDeSituacionGenerica.add(sgSupermercado39);
        listaDeSituacionGenerica.add(sgSupermercado40);
        listaDeSituacionGenerica.add(sgSupermercado41);
        listaDeSituacionGenerica.add(sgSupermercado42);
        listaDeSituacionGenerica.add(sgSupermercado43);
        listaDeSituacionGenerica.add(sgSupermercado44);
        listaDeSituacionGenerica.add(sgSupermercado45);
        listaDeSituacionGenerica.add(sgSupermercado46);
        listaDeSituacionGenerica.add(sgSupermercado47);
        listaDeSituacionGenerica.add(sgSupermercado48);

        listaDeSituacionGenerica.add(sgUbicarse1);
        listaDeSituacionGenerica.add(sgUbicarse2);
        listaDeSituacionGenerica.add(sgUbicarse3);
        listaDeSituacionGenerica.add(sgUbicarse4);
        listaDeSituacionGenerica.add(sgUbicarse5);
        listaDeSituacionGenerica.add(sgUbicarse6);
        listaDeSituacionGenerica.add(sgUbicarse7);
        listaDeSituacionGenerica.add(sgUbicarse8);
        listaDeSituacionGenerica.add(sgUbicarse9);
        listaDeSituacionGenerica.add(sgUbicarse10);
        listaDeSituacionGenerica.add(sgUbicarse11);
        listaDeSituacionGenerica.add(sgUbicarse12);
        listaDeSituacionGenerica.add(sgUbicarse13);
        listaDeSituacionGenerica.add(sgUbicarse14);
        listaDeSituacionGenerica.add(sgUbicarse15);
        listaDeSituacionGenerica.add(sgUbicarse16);
        listaDeSituacionGenerica.add(sgUbicarse17);
        listaDeSituacionGenerica.add(sgUbicarse18);
        listaDeSituacionGenerica.add(sgUbicarse19);
        listaDeSituacionGenerica.add(sgUbicarse20);
        listaDeSituacionGenerica.add(sgUbicarse21);
        listaDeSituacionGenerica.add(sgUbicarse22);
        listaDeSituacionGenerica.add(sgUbicarse23);
        listaDeSituacionGenerica.add(sgUbicarse24);
        listaDeSituacionGenerica.add(sgUbicarse25);
        listaDeSituacionGenerica.add(sgUbicarse26);
        listaDeSituacionGenerica.add(sgUbicarse27);
        listaDeSituacionGenerica.add(sgUbicarse28);
        listaDeSituacionGenerica.add(sgUbicarse29);
        listaDeSituacionGenerica.add(sgUbicarse30);
        listaDeSituacionGenerica.add(sgUbicarse31);
        listaDeSituacionGenerica.add(sgUbicarse32);
        listaDeSituacionGenerica.add(sgUbicarse33);
        listaDeSituacionGenerica.add(sgUbicarse34);
        listaDeSituacionGenerica.add(sgUbicarse35);

        return listaDeSituacionGenerica;
    }

    public void persistirSituacionesGenericasPorPrimeraVez(List<SituacionGenerica> listaDeSituacionGenerica){
        realm.beginTransaction();
        for(SituacionGenerica situacionGenerica : listaDeSituacionGenerica){
            situacionGenerica.setIdFromBd();
            realm.copyToRealmOrUpdate(situacionGenerica);
        }
        realm.commitTransaction();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        // La lista de resultados de un tipo que desconocemos PERO ese T deberá extender de RealmObjetc
        RealmResults<T> resultadosDeLaTabla = realm.where(anyClass).findAll();
        AtomicInteger value;

        if(resultadosDeLaTabla.size() > 0){
            int idMax = resultadosDeLaTabla.max("id").intValue();
            value = new AtomicInteger(idMax);
        } else{
            value = new AtomicInteger();
        }

        return value;
    }
}
