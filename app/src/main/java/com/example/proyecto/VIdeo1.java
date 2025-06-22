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

public class VIdeo1 extends AppCompatActivity {

    private boolean isPlaying = true;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MusicService.stopMusic();
        stopService(new Intent(this, MusicService.class));
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VideoView videoView = findViewById(R.id.videoView);
        SeekBar seekBar = findViewById(R.id.seekBar);
        Button playPauseButton = findViewById(R.id.playPauseButton);
        Button Ants = findViewById(R.id.btnants);
        Button Dps = findViewById(R.id.btndps);
        videoView.stopPlayback();

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.africa);
        videoView.setVideoURI(videoUri);
        videoView.start();

        seekBar.setMax(100);
        updateSeekBar(videoView, seekBar);

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
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Ants.setOnClickListener(v -> {
            Intent intent = new Intent(VIdeo1.this, Principal2.class);
            startActivity(intent);
        });

        Dps.setOnClickListener(v -> {
            Intent intent = new Intent(VIdeo1.this, africatriv.class);
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
