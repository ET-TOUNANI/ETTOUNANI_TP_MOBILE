package com.jsoncontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jsoncontacts.adapters.ContactsAdapter;
import com.jsoncontacts.models.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        boolean isItFirstRun = prefs.getBoolean("isItFirstRun", true);


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.CALL_PHONE
                },
                1);


        ArrayList<Contact> liste = new ArrayList<>();
        String jsonFileString = "";
        if (isItFirstRun) {
            jsonFileString = getJsonFromAssets(getApplicationContext(), "data.json");
            createInternalFile(jsonFileString);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isItFirstRun", false);
            editor.apply();
        } else {
            try {
                File dir = new File(getFilesDir(), "contacts");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                    File file = new File(dir, "data.json");
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                jsonFileString = sb.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Contact>>() {
        }.getType();
        List<Contact> contacts = gson.fromJson(jsonFileString, type);
        if (contacts != null) {
            liste.addAll(contacts);
        }

        ListView listView = findViewById(R.id.list);
        ContactsAdapter contactsAdapter = new ContactsAdapter(liste, listView, this);
        listView.setAdapter(contactsAdapter);


       // Button add = findViewById(R.id.add);
        /*
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.manage_contact);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText name = dialog.findViewById(R.id.name);
                EditText email = dialog.findViewById(R.id.email);
                EditText job = dialog.findViewById(R.id.job);
                EditText phone = dialog.findViewById(R.id.phone);
                Button create = dialog.findViewById(R.id.create);

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(name.getText().toString().trim()) || TextUtils.isEmpty(email.getText().toString().trim() )|| TextUtils.isEmpty(job.getText().toString().trim()) || TextUtils.isEmpty(phone.getText().toString().trim())) {
                            Toast.makeText(MainActivity.this, "Please fill in all the fields correctly !", Toast.LENGTH_SHORT).show();
                        } else {
                            Contact contact = new Contact();
                            contact.setFirst_name(name.getText().toString().trim().split(" ")[0]);
                            contact.setLast_name(name.getText().toString().trim().split(" ")[1]);
                            contact.setJob(job.getText().toString().trim());
                            contact.setEmail(email.getText().toString().trim());
                            contact.setPhone(phone.getText().toString().trim());

                            liste.add(contact);
                            contactsAdapter.notifyDataSetChanged();
                            saveToJsonFile(liste, MainActivity.this);
                        }
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });*/

    }

    private void createInternalFile(String jsonFileString) {
        File dir = new File(getFilesDir(), "contacts");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = new File(dir, "data.json");
            FileWriter writer = new FileWriter(file);
            writer.append(jsonFileString);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    public static void saveToJsonFile(ArrayList<Contact> contacts, Context context) {

        File dir = new File(context.getFilesDir(), "contacts");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = new File(dir, "data.json");
            FileWriter writer = new FileWriter(file);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            String json = gson.toJson(contacts, type);
            writer.append(json);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}