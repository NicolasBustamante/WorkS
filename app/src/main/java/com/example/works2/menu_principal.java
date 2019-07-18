package com.example.works2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class menu_principal extends Fragment {

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        context = rootView.getContext();

        Button Post = rootView.findViewById(R.id.post);
        ImageButton contru = rootView.findViewById(R.id.construccion);
        ImageButton electri = rootView.findViewById(R.id.electricidad);
        ImageButton electro = rootView.findViewById(R.id.electronica);
        ImageButton gasfi = rootView.findViewById(R.id.gasfiteria);

        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.works2.Post.class);
                startActivity(intent);
            }
        });

        contru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, contruccion_cat.class);
                startActivity(intent);
            }
        });

        electri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, electricidad_cat.class);
                startActivity(intent);
            }
        });

        electro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, electronica_cat.class);
                startActivity(intent);
            }
        });

        gasfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, gasfiteria_cat.class);
                startActivity(intent);
            }
        });

        return rootView;

    }



}
