package com.facens.cameralocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facens.cameralocal.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //Criação das variáveis
    private final Timer timer = new Timer();
    TimerTask timerTask;

    //Método onCreate e iniciação de activity_splash
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //tempo totoal do splash screen, em seguida carrega a tela principal
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //Agendando TimerTask para ser executada após 3 segundos
        timer.schedule(timerTask, 3000);
    }
    //Método para chamar a tela principal
    private void gotoMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
