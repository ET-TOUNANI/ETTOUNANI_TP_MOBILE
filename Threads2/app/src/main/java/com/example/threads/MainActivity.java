package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Activity myActivity;
    monPremierThread T1;
    monSecondThread T2;
    boolean runer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivity = this;
    }

    public void Start(View view) {
        runer = true;
        T1 = new monPremierThread();
        T1.start();
        T2 = new monSecondThread();
        T2.start();
    }

    public void Stop(View view) {

        runer = false;
    }

    public void AfficherMsg(View view) {
        Toast.makeText(this, "Bonjour, vous avez cliqué sur le bouton Message !", Toast.LENGTH_SHORT).show();
    }


    class monPremierThread extends Thread {

        int x = 0;
        public void run() {
            final TextView cpt1 = findViewById(R.id.txtvThread1);
            //créer un cpt infini
            while (runer) {
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cpt1.setText(String.valueOf(x));
                    }
                });

                x++;
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {}
            }
        }
    }



    class monSecondThread extends Thread {
        int y = 100;
        public void run() {
            final TextView cpt2 = findViewById(R.id.txtvThread2);
            while (runer) { //créer un cpt infini
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cpt2.setText(String.valueOf(y));
                    }
                });
                y++;
                try {
                    Thread.sleep(1000);
                }
                catch (Exception ex) {}
            }
        }
    }
}

