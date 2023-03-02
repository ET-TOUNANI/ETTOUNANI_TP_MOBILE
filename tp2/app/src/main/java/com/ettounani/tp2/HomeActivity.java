package com.ettounani.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        Bundle bundle=getIntent().getExtras();
        username.setText(String.format("Username : %s", bundle.getString("username")));
        password.setText(String.format("Password : %s", bundle.getString("password")));
    }
   public void retour(View view){
       Intent myIntent=new Intent(this,LoginActivity.class);
       startActivity(myIntent);
   }
}