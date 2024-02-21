package com.example.eyetester;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RectangleFragment extends Fragment {

    private View rectangleView;
    private Handler handler = new Handler();
    private int numPartitions = 2;
    private boolean isWhite = false;
    private final int[] colors = {Color.BLACK, Color.WHITE};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.framerectangle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rectangleView = view.findViewById(R.id.rectangleView);
        schedulePartitionUpdates();
    }

    private void schedulePartitionUpdates() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updatePartitionColors();
                numPartitions += 2;
                if (numPartitions <= 30) { // Change to your desired maximum number of partitions
                    schedulePartitionUpdates();
                }
            }
        }, 5000); // Change to adjust the time interval (milliseconds)
    }

    private void updatePartitionColors() {
        int partitionWidth = rectangleView.getWidth() / numPartitions;
        int colorIndex = 0;
        for (int i = 0; i < numPartitions; i++) {
            View partitionView = new View(getContext());
            partitionView.setBackgroundColor(colors[colorIndex]);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(partitionWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            partitionView.setLayoutParams(layoutParams);
            ((ViewGroup) rectangleView).addView(partitionView);
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }

//    private View rectangleView;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.framerectangle, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        rectangleView = view.findViewById(R.id.rectangleView);
//    }
//
//    public void updateRectangleColor(boolean isWhite) {
//        if (rectangleView != null) {
//            rectangleView.setBackgroundColor(isWhite ? Color.WHITE : Color.BLACK);
//        }
////        rectangleView.setBackgroundColor(isWhite ? Color.WHITE : Color.BLACK);
//    }
}
