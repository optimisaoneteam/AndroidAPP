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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class Main2Activity extends AppCompatActivity {

    TextView text;
    HttpURLConnection httpURLConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
    public class SQL extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... urls) {
            try {

                Class.forName("org.postgresql.Driver");
                System.out.println("Conectando to data base..");

                Connection conn = DriverManager.getConnection( "jdbc:postgresql://10.10.7.104:5433/REGISTRO","postgres", "admin");

                String stsql = "Select version()";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(stsql);
                rs.next();
                System.out.println(rs.getString(1) );
                conn.close();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
         return null;
        }

    }



    public void botonE(View v) {
        text = (TextView) findViewById(R.id.textView3);
        new SQL().execute();

       /// new internet().execute("https://api.ipify.org/");

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
