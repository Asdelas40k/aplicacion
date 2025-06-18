package com.example.proyecto;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carga);

        // Configuración de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a las vistas
        ProgressBar progressBar = findViewById(R.id.Progreso);
        TextView randomTextView = findViewById(R.id.mensajitos);

        // Array de textos
        String[] texts = {
                "La función del historiador no es amar el pasado, ni emanciparse del pasado, " +
                        "pero sí dominarlo y comprenderlo como la clave para el entendimiento del presente.",
                "Aprender historia es como viajar en el tiempo sin moverse del lugar.",
                "Un buen historiador escucha lo que los objetos y los libros antiguos quieren contar.",
                "Cada héroe, reina o inventor fue una vez parte de una historia. ¡Tú también estás escribiendo la tuya!",
                "Conocer la historia nos ayuda a entender el mundo y a construir uno mejor.",
                "El pasado está lleno de aventuras... ¡y los historiadores son quienes las descubren!"
        };

        // Selección de texto aleatorio y asignación al TextView
        String randomText = getRandomText(texts);
        randomTextView.setText(randomText);

        // Configuración del ProgressBar
        progressBar.setMax(100);
        simulateProgressBar(progressBar, 15000);
    }

    // Simula el progreso del ProgressBar
    private void simulateProgressBar(ProgressBar progressBar, int duration) {
        int interval = 50; // Intervalo de actualización (milisegundos)
        int totalSteps = duration / interval; // Total de pasos
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress <= totalSteps) {
                    progressBar.setProgress((int) ((float) progress / totalSteps * 100));
                    progress++;
                    handler.postDelayed(this, interval);
                } else {
                    handler.removeCallbacks(this); // Detiene el Runnable
                }
            }
        };

        handler.post(runnable); // Inicia la simulación
    }

    // Obtiene un texto aleatorio del array
    private String getRandomText(String[] texts) {
        Random random = new Random();
        int randomIndex = random.nextInt(texts.length);
        return texts[randomIndex];
    }
}
