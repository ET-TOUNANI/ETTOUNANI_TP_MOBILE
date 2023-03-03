package com.example.preferencesscreensettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to change the appBare
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Abderrahmane");


        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean performSync=prefs.getBoolean("veille",true);
        String syncInterval=prefs.getString("veille_intervale","20");
        String fullName=prefs.getString("full_name","");
        String email=prefs.getString("email_address","");

        Toast.makeText(this,performSync+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,syncInterval+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,fullName+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,email+ "",Toast.LENGTH_SHORT).show();
        //prefs.edit().putBoolean("shouldwe",true).apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
   }
