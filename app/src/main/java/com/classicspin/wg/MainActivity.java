package com.classicspin.wg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random sRandom = new Random();
    private ImageView mBottleImageView;
    private int lastAngle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottleImageView = (ImageView) findViewById(R.id.spin_imageView);

        mBottleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinBottle();
            }
        });
    }

    private void spinBottle() {
        int angle = sRandom.nextInt(3600 - 360) + 360;
        // Центр вращения
        float pivotX = mBottleImageView.getWidth() / 2;
        float pivotY = mBottleImageView.getHeight() / 2;

        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animation.setDuration(2500);
        animation.setFillAfter(true);

        mBottleImageView.startAnimation(animation);
    }

    // В примере не используется
    private void resetBottle() {
        float pivotX = mBottleImageView.getWidth() / 2;
        float pivotY = mBottleImageView.getHeight() / 2;

        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animation.setDuration(2000);
        animation.setFillAfter(true);

        mBottleImageView.startAnimation(animation);
    }


}
