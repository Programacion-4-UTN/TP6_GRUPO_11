package com.example.tp6_grupo_11.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tp6_grupo_11.R;
import com.example.tp6_grupo_11.model.Contacto;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {

    public ContactoAdapter(@NonNull Context context, @NonNull List<Contacto> contactos) {
        super(context, 0, contactos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contacto, parent, false);
        }

        Contacto contacto = getItem(position);
        TextView tvItem = convertView.findViewById(R.id.tvItemContacto);

        if (contacto != null) {
            tvItem.setText(contacto.getNombre() + " " + contacto.getApellido() + " \u2013 " + contacto.getEmail());
        }

        return convertView;
    }
}