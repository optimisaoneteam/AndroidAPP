package com.example.foyarzun.appoptimisa;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Main2Activity extends AppCompatActivity {

    TextView text;
    HttpURLConnection httpURLConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    public void botonE(View v) {
        text = (TextView) findViewById(R.id.textView3);
        new internet().execute("https://api.ipify.org/");

    }

    public class internet extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer Buffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    Buffer.append(line);
                    return Buffer.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            text.setText(s);
        }
    }
}
