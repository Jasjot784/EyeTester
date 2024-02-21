package com.example.eyetester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Bundle bundle;
    private FragmentManager fragmentManager;
    private FrameLayout frameLayout;
    private Handler handler = new Handler();
    private int numPartitions = 58;
    private boolean isWhite = false;
    private final int[] colors = {0xFF000000, 0xFFFFFFFF}; // black and white colors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btStart);
        button.setOnClickListener(this);
        frameLayout = findViewById(R.id.fragmentContainer);
    }

    @Override
    public void onClick(View view) {

        schedulePartitionUpdates();


    }
    private void schedulePartitionUpdates() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updatePartitionColors();
                numPartitions += 2;
                if (numPartitions <= 60) { // Change to your desired maximum number of partitions
                    schedulePartitionUpdates();
                }
            }
        }, 5000); // Change to adjust the time interval (milliseconds)
    }

    private void updatePartitionColors() {
        RectangleView rectangleView = new RectangleView(this);
        rectangleView.setColors(colors);
        rectangleView.setNumPartitions(numPartitions);
        rectangleView.setBackgroundColor(0xFFFFFFFF); // White background
        frameLayout.removeAllViews();
        frameLayout.addView(rectangleView);
    }
    private void displayRectangles() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        RectangleFragment fragment = new RectangleFragment();
        transaction.add(R.id.fragmentContainer, fragment);

        transaction.commit();
    }
}