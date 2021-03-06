package com.example.tp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Authentification extends AppCompatActivity {
EditText username;
EditText password;
Button Authenticate;
String user_name;
String pass;
TextView Result;
    JSONObject resu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Result   = findViewById(R.id.result);
        Authenticate = findViewById(R.id.connect);
        class result implements Runnable {

            @Override
            public void run() {
                try {

                    URL url = null;
                    try {

                        url = new URL("https://httpbin.org/basic-auth/" + user_name + "/" + pass);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        String basicAuth = "Basic " + Base64.encodeToString((user_name + ":" + pass).getBytes(),
                                Base64.NO_WRAP);
                        urlConnection.setRequestProperty("Authorization", basicAuth);
                        try {
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            String s = readStream(in);
                            Log.i("JFL", s);
                            Authentification.this.resu = new JSONObject(s);


                        } finally {
                            urlConnection.disconnect();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                JSONObject json = Authentification.this.resu;
                                Result.setText("My result here is " + json.get("authenticated"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        Thread thread = new Thread(new result());


        Authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = username.getText().toString();
                pass = password.getText().toString();
                thread.start();
            }
        });
    }
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}