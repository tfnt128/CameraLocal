package com.facens.cameralocal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
    Context context;
    //Construtor para inicializar o context
    public GPStracker(Context c){
        context = c;
    }
    //Método para obter a localização atual
    public Location getLocation(){
    //Verifica se possui a permissão necessária para acessar a localização
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
    //Se a permissão não for concedida, mostra uma mensagem de erro
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    //Registra as atualizações de localização do gps está ativado
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
    //Se o GPS não estiver ativado, mostra uma mensagem de erro
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
    //Não dá retorno se o gps estiver desativado.
        return null;
    }
    //Armazena se o gps foi desligado
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //Espera por uma nova localização
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //Fica atualizando para ver se o gps é desligado ou se move
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }

    private class ACCESS_FINE_LOCATION {
    }
}
