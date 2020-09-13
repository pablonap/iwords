package com.binary_winters.projects.iverbs.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chipo on 12/05/18.
 */

public class AudioRecord {
    private static MediaRecorder mRecorder;
    private static MediaPlayer mPlayer = new MediaPlayer();

    public static void startRecording(String fileName) {
        try{
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(fileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mRecorder.prepare();

            mRecorder.start();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void stopRecording() {
        try{
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void startPlaying(String fileName) {
        try {
            mPlayer.reset();
            mPlayer.setDataSource(fileName);

            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crearArchivoAudio(String fileName, byte[] audioFileEnBytes){
        File fileAudio = new File(fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileAudio);

            fos.write(audioFileEnBytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public static byte[] contertirAudioFileABytes(String fileName){
        byte[] bytesFromFile = null;
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            bytesFromFile = new byte[(int)file.length()];
            file.readFully(bytesFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesFromFile;

    }

    public static String contertirBytesFileEnStringFile(byte[] fileEnBytes){
        StringBuilder stringBuilder = new StringBuilder();

        int index = 0;
        while(index < fileEnBytes.length){
            stringBuilder.append(fileEnBytes[index]);
            stringBuilder.append(",");

            index++;
        }
        return stringBuilder.toString();
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

    public static String generarNombreDeArchivoAudio(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yy hh:mm:ss");
        String date = sdf.format(new Date());
        date = "af_" + date.replace(" ", "").replace("/", "").replace(":", "");

        return date;
    }

    public static String darNombreArchivoCompleto(Context context,String fileNameAudio){
            File file = context.getFilesDir();
            String path = file.getAbsolutePath();

            return path + "/" + fileNameAudio + ".3gp";
//        return Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DCIM).getAbsolutePath() + "/" + fileNameAudio + ".3gp";
    }
}
