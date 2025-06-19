package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Principal2 extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USER = "username";

    Button btnIniciar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal2);

        TextView userTextView = findViewById(R.id.txtmost);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUser = preferences.getString(KEY_USER, "");

        String message = "Bienvenido de vuelta " + savedUser + ", ¿estás listo para seguir con tu aventura?";
        userTextView.setText(message);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnSalir = findViewById(R.id.btnsalir);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la Activity llamada "Primera"
                Intent intent = new Intent(Principal2.this, Primera.class);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la aplicación completamente
                finishAffinity(); // Cierra todas las actividades
                System.exit(0);   // Termina el proceso de la app
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
