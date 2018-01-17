package com.example.foyarzun.appoptimisa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia al boton que esta en main, llamando a su id asociado
        buttonv1 = (Button)findViewById(R.id.goView1);
        // insancia boton   y enlazandolo con ID
        Button boton =  (Button)findViewById(R.id.GoToVista2);
        buttonv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //se usa para llamar a otro componentes
                //se ocupan generalmente para:
                /*
                * iniciar actividad
                * iniciar un servicio
                * entregar un mensaje
                * */
                Intent buttonv1 = new Intent(MainActivity.this,vista1Activity.class);
                startActivity(buttonv1);

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Main2Activity.class);
            startActivityForResult(intent,0);

            }

        });

    }
}
