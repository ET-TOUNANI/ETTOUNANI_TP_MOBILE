package com.ettounani.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SplashScreen.class);
        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show();
    }

    public void envoyer(View view) {
        Intent myIntent=new Intent(this,HomeActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("username",usernameEditText.getText().toString());
        bundle.putString("password",passwordEditText.getText().toString());
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}