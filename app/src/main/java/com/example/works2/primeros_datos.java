package com.example.works2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class primeros_datos extends AppCompatActivity {

    TextView placement;
    EditText nombre;
    EditText apellido;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeros_datos);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        placement = findViewById(R.id.placement);
        nombre = findViewById(R.id.nombre_usuario);
        apellido = findViewById(R.id.apellidos_usuario);
        guardar = findViewById(R.id.guardar);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
        Context context;

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("set_fragment", R.id.nav_volvermenu+"");
                    startActivity(intent);
                    Toast.makeText(primeros_datos.this, "Usuario Creado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }else {

                    Log.d("error", "Error al crear usuario");
                }
            }
        });

    }

    //Ubicacion actual usuario
    private void locationStart(){
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setPrimeros_datos(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location l) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (l.getLatitude() != 0.0 && l.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        l.getLatitude(), l.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    placement.setText(""
                            + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Localizacion implements LocationListener{
        primeros_datos primeros_datos;

        public com.example.works2.primeros_datos getPrimeros_datos() {

            return primeros_datos;
        }

        public void setPrimeros_datos(com.example.works2.primeros_datos primeros_datos) {
            this.primeros_datos = primeros_datos;
        }


        @Override
        public void onLocationChanged(Location loc) {

            loc.getLatitude();
            loc.getLongitude();

            String Text = "Mi ubicación actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            placement.setText(Text);
            this.primeros_datos.setLocation(loc);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }

        }

        @Override
        public void onProviderEnabled(String provider) {
            placement.setText("GPS Activado");
        }

        @Override
        public void onProviderDisabled(String provider) {
            placement.setText("GPS Desactivado");
        }
    }

    //Validar Datos usuario

    private boolean validate() {
        nombre.setError(null);
        apellido.setError(null);
        boolean v = true;

        String name = nombre.getText().toString();
        String last_name = apellido.getText().toString();

        if (TextUtils.isEmpty(name)){
            nombre.setError("Ingrese su Nombre");
            nombre.requestFocus();
            v = false;
        }

        if (TextUtils.isEmpty(name)){
            apellido.setError("Ingrese su Apellido");
            apellido.requestFocus();
            v = false;
        }

        if (v){
            return true;
        }else {
            return false;
        }

    }

}
