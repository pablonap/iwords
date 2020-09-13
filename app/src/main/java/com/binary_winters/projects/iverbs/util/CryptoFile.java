package com.binary_winters.projects.iverbs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by chipo on 19/05/18.
 */

public class CryptoFile {
    private static final String KEY = "zip";

    public static File encriptarArchivo(File originalFile){
        encrypt(originalFile);

        return originalFile;
    }

    public static File desencriptarArchivo(File fileImportado){
        return decrypt(fileImportado);
    }

    public static void encrypt(File inputFile) {
        doCrypto(Cipher.ENCRYPT_MODE, inputFile);
    }

    public static File decrypt(File inputFile) {
        inputFile = doCrypto(Cipher.DECRYPT_MODE, inputFile);

        return inputFile;
    }

    private static File doCrypto(int cipherMode, File inputFile){
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(KEY.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(cipherMode, skeyspec);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(inputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            ex.printStackTrace();
        }

        return inputFile;
    }
}
