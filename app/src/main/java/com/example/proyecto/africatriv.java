package com.example.proyecto;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class africatriv extends AppCompatActivity {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    Button btnEnviar, btnSiguiente;
    boolean respuestasEnviadas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarResultado();
                respuestasEnviadas = true;
                btnSiguiente.setEnabled(true); // activar botón
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (respuestasEnviadas) {
                    // Aquí puedes pasar a otra pantalla o actividad si tienes otra
                    // Por ahora mostramos otro mensaje
                    new AlertDialog.Builder(africatriv.this)
                            .setTitle("Siguiente")
                            .setMessage("Vamos a la siguiente sección...")
                            .setPositiveButton("OK", null)
                            .show();

                    // Ejemplo: iniciar nueva actividad
                    // Intent intent = new Intent(africatriv.this, SiguienteActivity.class);
                    // startActivity(intent);
                }
            }
        });
    }

    private void mostrarResultado() {
        int correctas = 0;

        if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton1b) correctas++;
        if (radioGroup2.getCheckedRadioButtonId() == R.id.radioButton2b) correctas++;
        if (radioGroup3.getCheckedRadioButtonId() == R.id.radioButton3b) correctas++;
        if (radioGroup4.getCheckedRadioButtonId() == R.id.radioButton4c) correctas++;
        if (radioGroup5.getCheckedRadioButtonId() == R.id.radioButton5c) correctas++;

        String mensaje = "FELICIDADES HAS RESPONDIDO " + correctas + "/5 PREGUNTAS CORRECTAS";

        new AlertDialog.Builder(this)
                .setTitle("Resultado")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}
