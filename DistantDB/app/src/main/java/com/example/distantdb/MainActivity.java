package com.example.distantdb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edit;
    ListView listTasks;
    ArrayAdapter<String> adapter = null;
    String url = "http://10.0.2.2:8888/calendar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTasks = findViewById(R.id.listTasks);
        btn = findViewById(R.id.addButton);
        edit = findViewById(R.id.editTask);


        Retrofit retrofit = new Retrofit.Builder( ).baseUrl(url).addConverterFactory(GsonConverterFactory.create( )).build( );
        myapi api = retrofit.create(myapi.class);

        Call<List<Task>> call = api.getalltasks( );
        call.enqueue(new Callback<List<Task>>( ) {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                List<Task> data = response.body( );

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, Listin(data));
                listTasks.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                process( );
            }
        });


    }

    public void process() {
        Retrofit retrofit = new Retrofit.Builder( )
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create( ))
                .build( );

        myapi api = retrofit.create(myapi.class);
        Call<Task> call = api.adtask(edit.getText( ).toString( ));
        call.enqueue(new Callback<Task>( ) {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

                edit.setText("");
                Toast.makeText(getApplicationContext( ), response.toString( ), Toast.LENGTH_LONG).show( );
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });


    }


    ArrayList Listin(List<Task> l) {
        ArrayList<String> maliste = new ArrayList<>( );
        for (int i = 0; i < l.size( ); i++) {
            maliste.add(l.get(i).getId( ) + ": " + l.get(i).getTask( ));
        }
        return maliste;
    }
}





