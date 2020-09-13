package com.binary_winters.projects.iverbs.util;

import java.util.regex.Pattern;

/**
 * Created by chipo on 29/04/18.
 */

public class IverbsPatterns {
    public static Boolean validarCadenaEsp(String cadenaEsp){
        Pattern patternPalabraEsp = Pattern.compile("^[a-zA-Z\\sÀ-ÿ;()¿?¡!]{1,80}$");
        if(patternPalabraEsp.matcher(cadenaEsp).matches()){
            return Boolean.TRUE;
        } else{
            return Boolean.FALSE;
        }
    }

    public static Boolean validarCadenaIng(String cadenaIng){
        Pattern patternPalabraEsp = Pattern.compile("^[a-zA-Z\\s'?!]{1,80}$");
        if(patternPalabraEsp.matcher(cadenaIng).matches()){
            return Boolean.TRUE;
        } else{
            return Boolean.FALSE;
        }
    }
}
