package com.example.eyetester;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class RectangleView extends View {

    private int numPartitions = 2;
    private int[] colors = {0xFF000000, 0xFFFFFFFF}; // black and white colors

    public RectangleView(Context context) {
        super(context);
    }

    public void setNumPartitions(int numPartitions) {
        this.numPartitions = numPartitions;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int partitionWidth = width / numPartitions;
        int colorIndex = 0;

        Paint paint = new Paint();

        for (int i = 0; i < numPartitions; i++) {
            paint.setColor(colors[colorIndex]);
            canvas.drawRect(i * partitionWidth, 0, (i + 1) * partitionWidth, height, paint);
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }
}
