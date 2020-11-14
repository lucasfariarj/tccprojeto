package com.desapet.tccv4.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "desapert.preferences";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";

    public Preferences(Context contextoParamentro){
        contexto = contextoParamentro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit();
    }


    public void salvarDados(String identificadorUsuario){
        editor.putString(CHAVE_IDENTIFICADOR,identificadorUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR,null);
    }
}
