package com.example.works2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class primerapantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primerapantalla);

        Button login = findViewById(R.id.signin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(primerapantalla.this, iniciarsesion.class);
                startActivity(i);
            }
        });

        final Button createaccount = findViewById(R.id.create_account);
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(primerapantalla.this, create_account.class);
                startActivity(i);
            }
        });
    }
}
