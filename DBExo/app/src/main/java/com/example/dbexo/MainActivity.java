package com.example.dbexo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView ls = (ListView) findViewById(R.id.liste);
        DBconnection db = new DBconnection(this);
        ArrayList<String> liste = db.getAllRecord();
        ArrayAdapter Adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,liste);
        ls.setAdapter(Adapter);
    }

    public void Enregistrer(View view) {
        TextView nom = findViewById(R.id.editxtname);
        ListView ls = (ListView) findViewById(R.id.liste);
        DBconnection db = new DBconnection(this);
        db.insertNewAdmin(nom.getText().toString());
        ArrayList<String> arrayListe = db.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        ls.setAdapter(myAdapter);
        nom.setText("");
    }
    public void Update(View view) {
        TextView chNom = findViewById(R.id.editxtname);
        EditText chID = findViewById(R.id.editExtId);
        ListView ls = (ListView) findViewById(R.id.liste);
        DBconnection db = new DBconnection(this);
        db.updateRow(chNom.getText().toString(),Integer.parseInt(chID.getText().toString()));
        ArrayList<String> arrayListe = db.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        ls.setAdapter(myAdapter);
        chNom.setText(""); chID.setText("");
    }
    public void Supprimer(View view) {
        TextView chId = findViewById(R.id.editExtId);
        ListView ls = (ListView) findViewById(R.id.liste);
        DBconnection db = new DBconnection(this);
        db.deleteRow(Integer.parseInt(chId.getText().toString())); //supp la ligne en question
        ArrayList<String> arrayL = db.getAllRecord();
        ArrayAdapter myAdapter = new        ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayL);
        ls.setAdapter(myAdapter);
        chId.setText("");
    }



}
