package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private static final String PREFS_NAME = "CargaPrefs";
    private static final String KEY_COUNT = "CargaCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carga);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ProgressBar progressBar = findViewById(R.id.Progreso);
        TextView randomTextView = findViewById(R.id.mensajitos);

        String[] texts = {
                "La función del historiador no es amar el pasado, ni emanciparse del pasado, " +
                        "pero sí dominarlo y comprenderlo como la clave para el entendimiento del presente.",
                "Al final todos nos convertimos en historias",
                "Un buen historiador escucha lo que los objetos y los libros antiguos quieren contar.",
                "Cada héroe, reina o inventor fue una vez parte de una historia. ¡Tú también estás escribiendo la tuya!",
                "Conocer la historia nos ayuda a entender el mundo y a construir uno mejor.",
                "El pasado está lleno de aventuras... ¡y los historiadores son quienes las descubren!"
        };

        String randomText = getRandomText(texts);
        randomTextView.setText(randomText);

        progressBar.setMax(100);
        simulateProgressBar(progressBar, 10000);  // 10 segundos
    }

    private void simulateProgressBar(ProgressBar progressBar, int duration) {
        int interval = 50;
        int totalSteps = duration / interval;
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
                    handler.removeCallbacks(this);
                    navigateToNextActivity();
                }
            }
        };

        handler.post(runnable);
    }

    private void navigateToNextActivity() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int count = prefs.getInt(KEY_COUNT, 0);
        count++;
        if (count > 4) {
            count = 1; // reset después de la cuarta carga
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_COUNT, count);
        editor.apply();

        Intent intent;
        switch (count) {
            case 1:
                intent = new Intent(carga.this, significado.class);
                break;
            case 2:
                intent = new Intent(carga.this, video2.class);
                break;
            case 3:
                //intent = new Intent(carga.this, ActivityTres.class);
                break;
            case 4:
               // intent = new Intent(carga.this, ActivityCuatro.class);
                break;
            default:
                //intent = new Intent(carga.this, ActivityUno.class);
                break;
        }

        //startActivity(intent);
        finish();
    }

    private String getRandomText(String[] texts) {
        Random random = new Random();
        int randomIndex = random.nextInt(texts.length);
        return texts[randomIndex];
    }
}
