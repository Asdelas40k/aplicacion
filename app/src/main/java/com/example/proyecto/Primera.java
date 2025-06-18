package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Primera extends AppCompatActivity {
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USER = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_primera);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnsi = findViewById(R.id.btnsi);
        Button btnno = findViewById(R.id.btnno);
        btnsi.setOnClickListener(v -> finishAffinity());

        btnsi.setOnClickListener(v -> {

            Intent intent = new Intent(Primera.this, carga.class);
            startActivity(intent);
        });
        TextView userTextView = findViewById(R.id.txtbienv);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUser = preferences.getString(KEY_USER, "");
        String message = "Hola " + savedUser + ", ¿quieres ingresar a una nueva aventura?";
        userTextView.setText(message);
    }



}
