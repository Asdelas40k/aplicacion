package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USER = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button Macerc = findViewById(R.id.btnacerc);
        Button Mini = findViewById(R.id.btnini);
        Button Msalir = findViewById(R.id.btnsalir);
        EditText Musuario = findViewById(R.id.usuario);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUser = preferences.getString(KEY_USER, "");
        Musuario.setText(savedUser);

        Msalir.setOnClickListener(v -> finishAffinity());

        Mini.setOnClickListener(v -> {
            String inputText = Musuario.getText().toString();


            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_USER, inputText);
            editor.apply();


            Intent intent = new Intent(MainActivity.this, Primera.class);
            intent.putExtra("userInput", inputText);
            startActivity(intent);
        });
    }
}

