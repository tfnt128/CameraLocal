package com.facens.cameralocal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facens.cameralocal.R;

public class MainActivity extends AppCompatActivity {
    //Criação das variáveis
    private ImageView imageViewFoto;
    private Button btnGeo;
    //Criação da interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Referência layout e solicitação de permissão a localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
//Listener para o botão de localização
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //Ação é executada no clique do botão
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
//Verifica se a localização foi obtida
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
//Solicitação de permissão de acesso à câmera
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, 0);
        }
//Referência ao ImageView do layout e listener para o botão de tirar foto
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            //Ação a ser executada no clique do botão
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    //Pede permissão para acessar a câmera do celular
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Método chamado após a foto ser tirada para checar a foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//Verifica se a foto foi tirada com sucesso e imprime no aplicativo
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
