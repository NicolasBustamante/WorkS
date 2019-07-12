package com.example.works2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class create_account extends AppCompatActivity {

    EditText newUser;
    EditText newemail;
    EditText newPass;
    Button registro;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        newUser = findViewById(R.id.newusername);
        newemail = findViewById(R.id.email);
        newPass = findViewById(R.id.newpassword);
        registro = findViewById(R.id.registrarme);
        check = findViewById(R.id.terminos);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (validarRegistro()) {
                    Intent intent = new Intent(getApplicationContext(),primeros_datos.class);
                    startActivity(intent);
                    Toast.makeText(create_account.this,"Cuenta creada con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.d("error", "Error en la validacion");
                }
            }
        });
    }
    private boolean validarRegistro() {
        newUser.setError(null);
        newPass.setError(null);
        newemail.setError(null);
        boolean v = true;

        String name = newUser.getText().toString();
        String password = newPass.getText().toString();
        String email = newemail.getText().toString();

        if (TextUtils.isEmpty(name)) {
            newUser.setError(getString(R.string.error_campo_username));
            newUser.requestFocus();
            v = false;
        }

        if (TextUtils.isEmpty(password)) {
            newPass.setError(getString(R.string.error_campo_password));
            newPass.requestFocus();
            v = false;
        }

        if (check.isChecked()){
            v = true;
        }else {
            Toast.makeText(create_account.this,"Debe aceptar Terminos y Condiciones", Toast.LENGTH_SHORT).show();
            check.requestFocus();
            v = false;
        }


        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find()==false) {
            newemail.setError(getString(R.string.error_email));
            newemail.requestFocus();
            v = false;
        }

        if (v) {
            return true;
        } else {
            return false;
        }
    }
}
