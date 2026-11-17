package com.example.tp6_grupo_11.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class FechaTextWatcher implements TextWatcher {

    private final EditText editText;
    private boolean isUpdating = false;
    private String textoAnterior = "";

    public FechaTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        textoAnterior = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isUpdating) return;

        String actual = s.toString();
        // Si el usuario está borrando, no forzamos las barras para evitar el bug de bloqueo
        if (actual.length() < textoAnterior.length()) {
            return;
        }

        //  dígitos numéricos (máximo 8: ddMMyyyy)
        String soloNumeros = actual.replaceAll("[^\\d]", "");
        if (soloNumeros.length() > 8) {
            soloNumeros = soloNumeros.substring(0, 8);
        }

        StringBuilder formateado = new StringBuilder();
        for (int i = 0; i < soloNumeros.length(); i++) {
            formateado.append(soloNumeros.charAt(i));
            if ((i == 1 || i == 3) && i != soloNumeros.length() - 1) {
                formateado.append("/");
            }
        }

        isUpdating = true;
        editText.setText(formateado.toString());
        editText.setSelection(formateado.length());
        isUpdating = false;
    }
}