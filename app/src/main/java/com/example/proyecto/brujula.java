package com.example.proyecto;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class brujula extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer, magnetometer;
    private float[] gravity;
    private float[] geomagnetic;
    private TextView directionText, degreesText;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_brujula);

        // Referencias de UI
        directionText = findViewById(R.id.directionText);
        degreesText = findViewById(R.id.degreesText);
        exitButton = findViewById(R.id.exitButton);

        // Inicializar sensores
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Botón para finalizar la aplicación
        exitButton.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registrar listeners
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Detener listeners
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = event.values.clone();
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values.clone();
        }

        if (gravity != null && geomagnetic != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360; // Convertir de -180 a 180 a 0-360

                updateDirection(azimuth);
            }
        }
    }

    private void updateDirection(float azimuth) {
        String direction = "";

        if (azimuth >= 337.5 || azimuth < 22.5) {
            direction = "Norteamérica";
        } else if (azimuth >= 22.5 && azimuth < 67.5) {
            direction = "Europa";
        } else if (azimuth >= 67.5 && azimuth < 112.5) {
            direction = "África";
        } else if (azimuth >= 112.5 && azimuth < 157.5) {
            direction = "Asia";
        } else if (azimuth >= 157.5 && azimuth < 202.5) {
            direction = "Sudamérica";
        } else if (azimuth >= 202.5 && azimuth < 247.5) {
            direction = "Asia";
        } else if (azimuth >= 247.5 && azimuth < 292.5) {
            direction = "África";
        } else if (azimuth >= 292.5 && azimuth < 337.5) {
            direction = "Europa";
        }

        directionText.setText("Dirección: " + direction);
        degreesText.setText("Grados: " + Math.round(azimuth) + "°");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se utiliza en este ejemplo
    }
}
