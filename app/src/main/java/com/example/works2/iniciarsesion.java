package com.example.works2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class iniciarsesion extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        signin = findViewById(R.id.sign_in);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(iniciarsesion.this,"Sesión iniciada con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Log.d("error", "Error en la validación");
                }
            }
        });
    }

    private boolean validate() {
        editTextUsername.setError(null);
        editTextPassword.setError(null);
        boolean v = true;

        String name = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            editTextUsername.setError(getString(R.string.error_campo_username));
            editTextUsername.requestFocus();
            v = false;
        }

        if (TextUtils.isEmpty(password)){
            editTextPassword.setError(getString(R.string.error_campo_password));
            editTextPassword.requestFocus();
            v = false;
        }

        if (v){
            return true;
        }else {
            return false;
        }
    }
}
