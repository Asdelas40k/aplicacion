package com.example.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class video4 extends AppCompatActivity {

    private boolean isPlaying = true;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MusicService.stopMusic();
        stopService(new Intent(this, MusicService.class));
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VideoView videoView = findViewById(R.id.videoView);
        SeekBar seekBar = findViewById(R.id.seekBar);
        Button playPauseButton = findViewById(R.id.playPauseButton);
        Button btnPrev = findViewById(R.id.btnants);
        Button btnNext = findViewById(R.id.btndps);

        // Detener cualquier reproducción previa
        videoView.stopPlayback();

        // Configurar URI del video correcto
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.america);
        videoView.setVideoURI(videoUri);

        // Listener para iniciar el video cuando esté preparado
        videoView.setOnPreparedListener(mp -> {
            videoView.start();
            seekBar.setMax(100);
            updateSeekBar(videoView, seekBar);
        });

        playPauseButton.setOnClickListener(v -> {
            if (isPlaying) {
                videoView.pause();
                playPauseButton.setText("Reanudar");
            } else {
                videoView.start();
                playPauseButton.setText("Pausar");
            }
            isPlaying = !isPlaying;
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int position = (int) (progress / 100.0 * videoView.getDuration());
                    videoView.seekTo(position);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnPrev.setOnClickListener(v -> {
            Intent intent = new Intent(video4.this, video3.class); // Regresa a video3
            startActivity(intent);
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(video4.this, asiatriv.class); // Avanza a asiatriv o la siguiente actividad
            startActivity(intent);
        });
    }

    private void updateSeekBar(VideoView videoView, SeekBar seekBar) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (videoView.isPlaying()) {
                    int progress = (int) ((videoView.getCurrentPosition() / (float) videoView.getDuration()) * 100);
                    seekBar.setProgress(progress);
                }
                handler.postDelayed(this, 500);
            }
        }, 500);
    }
}
