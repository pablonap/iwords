package com.binary_winters.projects.iverbs.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.binary_winters.projects.iverbs.VerboEnEsp;
import com.binary_winters.projects.iverbs.modelo.PalabraAgregadaBean;
import com.binary_winters.projects.iverbs.modelo.VerboGenericoBean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import io.realm.Realm;
import io.realm.RealmResults;

import android.support.v4.content.FileProvider;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by chipo on 15/04/18.
 */

public class ImpExpBuilder {
    private RealmResults<VerboGenericoBean> listaDeVerbosAgregadosPorUsuario;
    private Realm realm;
    private File file;
    private File fileEncriptado;
    private Context context;
    private RealmResults<PalabraAgregadaBean> palabrasAgregadas;
    private final String MENSAJE_NO_ESCRIBIR = "*** NO MODIFICAR ESTE ARCHIVO ***\n";
    private final String HEADER_VERBOS_AGREGADOS = "#verbos agregados\n";
    private final String HEADER_PARALABRAS_AGREGADAS = "#palabras agregadas\n";
    private final String FAVORITOS_TRUE = "favoritos_true";
    private final String FAVORITOS_FALSE = "favoritos_false";

    public ImpExpBuilder(Context context){
        this.context = context;
        realm = Realm.getDefaultInstance();
        listaDeVerbosAgregadosPorUsuario = realm.where(VerboGenericoBean.class).equalTo("verboEnEsp.VERBO_DESDE_APP", Boolean.FALSE).findAll();
        palabrasAgregadas = realm.where(PalabraAgregadaBean.class).findAll();
    }

    public void exportarBackup(){
        if(listaDeVerbosAgregadosPorUsuario != null && listaDeVerbosAgregadosPorUsuario.isEmpty() == false
                || palabrasAgregadas != null && palabrasAgregadas.isEmpty() == false){
            String fileName = this.asignarNombre();
            file = this.crearArchivo(fileName);

            escribirEnArchivo(file);

            fileEncriptado = CryptoFile.encriptarArchivo(file);

            compartirArchivo(fileEncriptado);
        } else{
            Toast.makeText(context, "No hay verbos guardados", Toast.LENGTH_SHORT).show();
        }
    }

    private String asignarNombre() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yy hh:mm:ss");
        String date = sdf.format(new Date());
        date = date.replace(" ", "").replace("/", "").replace(":", "");

        StringBuilder fileName = new StringBuilder(date);
        fileName.insert(0, "bckp_");
        fileName.append(".txt");

        return fileName.toString();
    }

    public File crearArchivo(String nombreArchivo){
        // Guarda el archivo en la memoria pública externa. Es necesaria esta ubicación
        //  para luego poder acceder a él y enviarlo desde el método: Uri.fromFile(file).
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), nombreArchivo);

        return file;
    }

    public void escribirEnArchivo(File file){
        List<String> verbos = darListaDeVerbos();
        List<String> palabras = darListaDePalabras();

        try {
            FileOutputStream outputStream;
            outputStream = new FileOutputStream(file.getAbsolutePath());
            outputStream.write(MENSAJE_NO_ESCRIBIR.getBytes());
            if(verbos.isEmpty() == false){
                outputStream.write(HEADER_VERBOS_AGREGADOS.getBytes());
                for(String verbo : verbos){
                    outputStream.write(verbo.getBytes());

                }
            }

            if(palabras.isEmpty() == false){
                outputStream.write(HEADER_PARALABRAS_AGREGADAS.getBytes());
                for(String palabra : palabras){
                    outputStream.write(palabra.getBytes());
                }
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> darListaDeVerbos(){
        List<String> verbos = new ArrayList<>();
        for(int i=0; i<listaDeVerbosAgregadosPorUsuario.size(); i++){
            VerboGenericoBean verboGenericoBean = listaDeVerbosAgregadosPorUsuario.get(i);

            String verbo;

            if(verboGenericoBean.getVerboEnEsp().getNombreDeVerbo().equals("a")){
                System.out.println();
            }

            if(verboGenericoBean.getVerboEnEsp().getMarkedWithStar()){
                verbo = verboGenericoBean.getVerboEnEsp().getNombreDeVerbo()
                        + " / " + verboGenericoBean.getCadenaDeVerbos()
                        + " / " + FAVORITOS_TRUE
                        + " / " + verboGenericoBean.getNombreArchivoAudio()
                        + " / " + verboGenericoBean.getArchivoDeAudioEnString()
                        + "\n";
            } else{
                verbo = verboGenericoBean.getVerboEnEsp().getNombreDeVerbo()
                        + " / " + verboGenericoBean.getCadenaDeVerbos()
                        + " / " + FAVORITOS_FALSE
                        + " / " + verboGenericoBean.getNombreArchivoAudio()
                        + " / " + verboGenericoBean.getArchivoDeAudioEnString()
                        + "\n";
            }
            verbos.add(verbo);
        }

        return verbos;
    }

    private List<String> darListaDePalabras(){
        List<String> palabrasAgregadasDeUsuario = new ArrayList<>();
        for(int i=0; i<palabrasAgregadas.size(); i++){
            PalabraAgregadaBean palabraAgregadaBean = palabrasAgregadas.get(i);

            String palabra;
            if(palabraAgregadaBean.getMarkedWithStar()){
                palabra = palabraAgregadaBean.getPalabraEnEsp()
                        + " / " + palabraAgregadaBean.getCadenaEnIngles()
                        + " / " + FAVORITOS_TRUE
                        + " / " + palabraAgregadaBean.getNombreArchivoAudio()
                        + " / " + palabraAgregadaBean.getArchivoDeAudioEnString()
                        + "\n";
            } else{
                palabra = palabraAgregadaBean.getPalabraEnEsp()
                        + " / " + palabraAgregadaBean.getCadenaEnIngles()
                        + " / " + FAVORITOS_FALSE
                        + " / " + palabraAgregadaBean.getNombreArchivoAudio()
                        + " / " + palabraAgregadaBean.getArchivoDeAudioEnString()
                        + "\n";
            }

            palabrasAgregadasDeUsuario.add(palabra);
        }

        return palabrasAgregadasDeUsuario;
    }

    public void compartirArchivo(File file){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/*");

        //TODO: Work from here
        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName(), file);

        sharingIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
        //sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        context.startActivity(Intent.createChooser(sharingIntent, "Enviar a..."));
    }

    private List<VerboGenericoBean> listaDeVerboGenericoBeanImportada = new ArrayList<>();
    private List<PalabraAgregadaBean> listaDePalabraDeUsuarioBeanImportada = new ArrayList<>();

    public Boolean verificarValidezDeContenido(List<String> contenido) {
        Pattern patternMensaje = Pattern.compile("^\\*\\*\\*\\sNO\\sMODIFICAR\\sESTE\\sARCHIVO\\s\\*\\*\\*$");
        Pattern patternHeaderVerbosAgregados = Pattern.compile("^#verbos\\sagregados$");
        Pattern patternLineaDeVerboAgregado = Pattern.compile("^[a-zA-Z\\sÀ-ÿ;()¿?¡!]{1,80}\\s/\\s[a-zA-Z\\s'?!]{1,80}-[a-zA-Z\\sÀ-ÿ¿?¡!]{1,80}\\s[a-zA-Z\\s'?!]{1,80}-[a-zA-Z\\sÀ-ÿ¿?¡!]{1,80}\\s[a-zA-Z\\s'?!]{1,80}-[a-zA-Z\\sÀ-ÿ¿?¡!]{1,80}\\s/\\s(favoritos_true|favoritos_false).*$");
        Pattern patternHeaderPalabrasAgregadas = Pattern.compile("^#palabras\\sagregadas$");
        Pattern patternLineaDePalabraAgregada = Pattern.compile("^[a-zA-Z\\sÀ-ÿ;()¿?¡!]{1,80}\\s/\\s[a-zA-Z\\s'?!]{1,80}-[a-zA-Z\\sÀ-ÿ¿?¡!]{1,80}\\s/\\s(favoritos_true|favoritos_false).*$");

        final int PRIMERA_LINEA = 0;
        final int SEGUNDA_LINEA = 1;

        Boolean resultadoFinal = Boolean.FALSE;
        int indexDeLineaSiguiente = 2;
        int indiceIdParaVerbo = 1;

        if (contenido != null && contenido.isEmpty() == false) {
            if (patternMensaje.matcher(contenido.get(PRIMERA_LINEA)).matches()) {
                // Caso 1 en que el contenido comienza con verbos agregados
                if (patternHeaderVerbosAgregados.matcher(contenido.get(SEGUNDA_LINEA)).matches()) {
                    Boolean resultadoParaVerbos = Boolean.FALSE;

                    // El índice avanza hasta terminar la lista o encontrarse con algo distinto a un verbo agregado.
                    while (indexDeLineaSiguiente < contenido.size()
                            && contenido.get(indexDeLineaSiguiente) != null
                            && patternLineaDeVerboAgregado.matcher(contenido.get(indexDeLineaSiguiente)).matches()) {

                        String[] divisiones = contenido.get(indexDeLineaSiguiente).split(" / ");

                        String verboEnEsp = divisiones[0];
                        String cadenaEnIng = divisiones[1];
                        String cadenaEnFavoritos = divisiones[2];
                        String nombreArchivoAudio = divisiones[3];
                        String cadenaArchivoAudio = divisiones[4];

                        VerboGenericoBean verboGenericoBean;

                        if(cadenaEnFavoritos != null && FAVORITOS_TRUE.equals(cadenaEnFavoritos)){
                            VerboEnEsp esp =  new VerboEnEsp(verboEnEsp, Boolean.FALSE, Boolean.TRUE);
                            esp.setIdFromBd();
                            verboGenericoBean = new VerboGenericoBean(esp, cadenaEnIng);
                            verboGenericoBean.setNombreArchivoAudio(nombreArchivoAudio);
                            verboGenericoBean.setArchivoDeAudioEnString(cadenaArchivoAudio);
                            verboGenericoBean.setId(indiceIdParaVerbo);
                        } else{
                            VerboEnEsp esp =  new VerboEnEsp(verboEnEsp, Boolean.FALSE, Boolean.FALSE);
                            esp.setIdFromBd();
                            verboGenericoBean = new VerboGenericoBean(esp, cadenaEnIng);
                            verboGenericoBean.setNombreArchivoAudio(nombreArchivoAudio);
                            verboGenericoBean.setArchivoDeAudioEnString(cadenaArchivoAudio);
                            verboGenericoBean.setId(indiceIdParaVerbo);
                        }

                        indiceIdParaVerbo++;
                        listaDeVerboGenericoBeanImportada.add(verboGenericoBean);

                        crearArchivoConPathCompleto(nombreArchivoAudio, cadenaArchivoAudio);

                        indexDeLineaSiguiente++;
                        resultadoParaVerbos = Boolean.TRUE;
                    }

                    // Con este if me aseguro que lo que precede al indexDeLineaSiguiente es válido
                    if (resultadoParaVerbos.equals(Boolean.TRUE)) {
                        // Si se llegó al final de la lista significa que el contenido es válido.
                        if (indexDeLineaSiguiente == contenido.size()) {
                            resultadoFinal = Boolean.TRUE;

                            // Comienza con la lista de palabras agregadas
                        } else if (patternHeaderPalabrasAgregadas.matcher(contenido.get(indexDeLineaSiguiente)).matches()) {
                            Boolean resultadoParaPalabras = Boolean.FALSE;
                            indexDeLineaSiguiente++;
                            int indiceIdParaPalabra = 1;
                            while (indexDeLineaSiguiente < contenido.size()
                                    && contenido.get(indexDeLineaSiguiente) != null
                                    && patternLineaDePalabraAgregada.matcher(contenido.get(indexDeLineaSiguiente)).matches()) {

                                generarPalabraYAgregarALista(contenido, indexDeLineaSiguiente, indiceIdParaPalabra);

                                indexDeLineaSiguiente++;
                                indiceIdParaPalabra++;
                                resultadoParaPalabras = Boolean.TRUE;
                            }

                            // Con este if me aseguro que lo que precede al indexDeLineaSiguiente es válido
                            if (resultadoParaPalabras.equals(Boolean.TRUE)) {
                                // Si salió del loop llegando al final
                                if (indexDeLineaSiguiente == contenido.size()) {
                                    resultadoFinal = Boolean.TRUE;
                                } else {
                                    resultadoFinal = Boolean.FALSE;
                                }
                            } else {
                                resultadoFinal = Boolean.FALSE;
                            }

                        } else {
                            resultadoFinal = Boolean.FALSE;
                        }
                    } else {
                        resultadoFinal = Boolean.FALSE;
                    }
                } else {
                    // Caso 2 en que el contenido comienza con palabras agregadas
                    if (patternHeaderPalabrasAgregadas.matcher(contenido.get(SEGUNDA_LINEA)).matches()) {
                        Boolean resultadoParaPalabras = Boolean.FALSE;
                        int indiceIdParaPalabra = 1;
                        // El índice avanza hasta terminar la lista o encontrarse con algo distinto a un verbo agregado.
                        while (indexDeLineaSiguiente < contenido.size()
                                && contenido.get(indexDeLineaSiguiente) != null
                                && patternLineaDePalabraAgregada.matcher(contenido.get(indexDeLineaSiguiente)).matches()) {

                            generarPalabraYAgregarALista(contenido, indexDeLineaSiguiente, indiceIdParaPalabra);

                            indexDeLineaSiguiente++;
                            indiceIdParaPalabra++;

                            resultadoParaPalabras = Boolean.TRUE;
                        }

                        // Con este if me aseguro que lo que precede al indexDeLineaSiguiente es válido
                        if (resultadoParaPalabras.equals(Boolean.TRUE)) {

                            // Si se llegó al final de la lista significa que el contenido es válido.
                            if (indexDeLineaSiguiente == contenido.size()) {
                                resultadoFinal = Boolean.TRUE;
                            } else {
                                resultadoFinal = Boolean.FALSE;
                            }
                        }
                    } else {
                        resultadoFinal = Boolean.FALSE;
                    }
                }
            }
        }

        return resultadoFinal;
    }

    public void generarPalabraYAgregarALista(List<String> contenido, int indexDeLineaSiguiente, int indiceIdParaPalabra){
        String[] divisiones = contenido.get(indexDeLineaSiguiente).split(" / ");

        String palabraEnEsp = divisiones[0];
        String cadenaEnIng = divisiones[1];
        String cadenaEnFavoritos = divisiones[2];
        String nombreArchivoAudio = divisiones[3];
        String cadenaArchivoAudio = divisiones[4];

        PalabraAgregadaBean palabraAgregadaBean;
        if(cadenaEnFavoritos != null && FAVORITOS_TRUE.equals(cadenaEnFavoritos)){
            palabraAgregadaBean = new PalabraAgregadaBean(palabraEnEsp, cadenaEnIng, Boolean.TRUE);
            palabraAgregadaBean.setNombreArchivoAudio(nombreArchivoAudio);
            palabraAgregadaBean.setArchivoDeAudioEnString(cadenaArchivoAudio);
            palabraAgregadaBean.setId(indiceIdParaPalabra);
        } else{
            palabraAgregadaBean = new PalabraAgregadaBean(palabraEnEsp, cadenaEnIng, Boolean.FALSE);
            palabraAgregadaBean.setNombreArchivoAudio(nombreArchivoAudio);
            palabraAgregadaBean.setArchivoDeAudioEnString(cadenaArchivoAudio);
            palabraAgregadaBean.setId(indiceIdParaPalabra);
        }

        crearArchivoConPathCompleto(nombreArchivoAudio, cadenaArchivoAudio);

        listaDePalabraDeUsuarioBeanImportada.add(palabraAgregadaBean);
    }

    public List<String> desencriptarArchivo(Uri uriDeArchivoImportado){
        List<String> lineasDeArchivoDesencriptado = null;
        try {
            File fileImportado = crearFileDesdeUri(uriDeArchivoImportado);
            fileImportado = CryptoFile.desencriptarArchivo(fileImportado);

            lineasDeArchivoDesencriptado = this.leerArchivo(fileImportado);

            if(fileImportado.exists()){
                fileImportado.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineasDeArchivoDesencriptado;

    }

    public List<String> leerArchivo(Uri uri) throws IOException{
        BufferedReader bufferedReader = null;
        InputStream inputStream = context.getContentResolver().openInputStream(uri);

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String nextLine = null;
        String contenidoDeArchivo = null;

        List<String> lineasDeContenidoDeArchivo = new ArrayList<>();

        try {
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                if(contenidoDeArchivo == null){
                    contenidoDeArchivo = nextLine;
                } else{
                    contenidoDeArchivo = contenidoDeArchivo + "\n" + nextLine;
                }

                lineasDeContenidoDeArchivo.add(nextLine);
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        }catch(IOException ie){
            ie.printStackTrace();
        }

        return lineasDeContenidoDeArchivo;
    }

    public File crearFileDesdeUri(Uri uri) throws IOException{
        InputStream inputStream = context.getContentResolver().openInputStream(uri);

        File fileEncriptadoImportado = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "import_file");
        OutputStream outStream = new FileOutputStream(fileEncriptadoImportado);

        byte[] buf = new byte[1024];
        int len;
        while((len=inputStream.read(buf))>0){
            outStream.write(buf,0,len);
        }


        return fileEncriptadoImportado;
    }

    public List<String> leerArchivo(File file) throws IOException{
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String nextLine = null;
        String contenidoDeArchivo = null;

        List<String> lineasDeContenidoDeArchivo = new ArrayList<>();

        try {
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                if(contenidoDeArchivo == null){
                    contenidoDeArchivo = nextLine;
                } else{
                    contenidoDeArchivo = contenidoDeArchivo + "\n" + nextLine;
                }

                lineasDeContenidoDeArchivo.add(nextLine);
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();

        }catch(IOException ie){
            ie.printStackTrace();
        }

        return lineasDeContenidoDeArchivo;
    }

    public void reescribirBd() {
        if(listaDeVerboGenericoBeanImportada != null
                && listaDeVerboGenericoBeanImportada.isEmpty() == false){

            realm.beginTransaction();
            RealmResults<VerboEnEsp> verbosEnEspDeUsuarioParaBorrar = realm.where(VerboEnEsp.class).equalTo("VERBO_DESDE_APP", Boolean.FALSE).findAll();
            RealmResults<VerboGenericoBean> verbosAgregadosPorUsarioParaBorrar =
                    realm.where(VerboGenericoBean.class).findAll();

            verbosEnEspDeUsuarioParaBorrar.deleteAllFromRealm();
            verbosAgregadosPorUsarioParaBorrar.deleteAllFromRealm();
            realm.commitTransaction();

            for(VerboGenericoBean verboDeUsuarioImportado : listaDeVerboGenericoBeanImportada){
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(verboDeUsuarioImportado);
                realm.commitTransaction();
            }
        }

        if(listaDePalabraDeUsuarioBeanImportada != null
                && listaDePalabraDeUsuarioBeanImportada.isEmpty() == false){
            RealmResults<PalabraAgregadaBean> palabrasAgregadasParaBorrar =
                    realm.where(PalabraAgregadaBean.class).findAll();

            realm.beginTransaction();
            palabrasAgregadasParaBorrar.deleteAllFromRealm();
            realm.commitTransaction();

            for(PalabraAgregadaBean palabraAgregadaImportada : listaDePalabraDeUsuarioBeanImportada){
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(palabraAgregadaImportada);
                realm.commitTransaction();
            }
        }

    }

    public static void crearArchivoConPathCompleto(String fileNameAudio, String cadenaArchivoAudio ) {
        File newFile = new File(fileNameAudio);

        byte[] bytesArchivoAudio = contertirStringFileEnBytesFile(cadenaArchivoAudio);

        try {
            FileOutputStream outputStream;
            outputStream = new FileOutputStream(newFile.getAbsolutePath());
            outputStream.write(bytesArchivoAudio);
            outputStream.close();
        } catch (FileNotFoundException fex) {
            fex.printStackTrace();
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }

    public static byte[] contertirStringFileEnBytesFile(String fileEnString){
        String[] cadena = fileEnString.split(",");

        int sizeCadena = cadena.length;

        Byte[] regen = new Byte[sizeCadena];

        for(int i=0; i<sizeCadena; i++){
            regen[i] = Byte.valueOf(cadena[i]);
        }

        byte[] bytesAudioFileParaEscuchar = new byte[sizeCadena];
        for(int i=0; i<sizeCadena; i++){
            bytesAudioFileParaEscuchar[i] = regen[i];
        }

        return bytesAudioFileParaEscuchar;
    }
}