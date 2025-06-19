package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class americatriv extends AppCompatActivity {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    Button btnEnviar, btnSiguiente;
    boolean respuestasEnviadas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_americatriv); // Inflar el diseño correcto

        // Asignar las vistas del XML
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
                btnSiguiente.setEnabled(true); // Activar botón "Siguiente"
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (respuestasEnviadas) {
                    // Aquí puedes pasar a otra actividad si está definida
                    new AlertDialog.Builder(americatriv.this)
                            .setTitle("Siguiente")
                            .setMessage("Vamos a la siguiente sección...")
                            .setPositiveButton("OK", null)
                            .show();

                    // Si quieres iniciar otra actividad, descomenta lo siguiente:
                    Intent intent = new Intent(americatriv.this, carga.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void mostrarResultado() {
        int correctas = 0;

        // Comprobación de las respuestas correctas
        if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton1b) correctas++;
        if (radioGroup2.getCheckedRadioButtonId() == R.id.radioButton2b) correctas++;
        if (radioGroup3.getCheckedRadioButtonId() == R.id.radioButton3a) correctas++;
        if (radioGroup4.getCheckedRadioButtonId() == R.id.radioButton4c) correctas++;
        if (radioGroup5.getCheckedRadioButtonId() == R.id.radioButton5b) correctas++;

        String mensaje = "FELICIDADES HAS RESPONDIDO " + correctas + "/5 PREGUNTAS CORRECTAS";

        new AlertDialog.Builder(this)
                .setTitle("Resultado")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}
