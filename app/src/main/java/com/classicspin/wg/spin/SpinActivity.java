package com.classicspin.wg.spin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.classicspin.wg.R;

import java.util.Random;

public class SpinActivity extends AppCompatActivity {

    public static final Random sRandom = new Random();
    private static final String SPIN = "spin";
    private ImageView spinImageView, imageView2;
    private int lastAngle = -1;
    private int spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin);

        Intent intent = getIntent();
        if (intent !=null) {
            spin = intent.getIntExtra(SPIN,0);
        }

        spinImageView = findViewById(R.id.spin_imageView);
        imageView2 = findViewById(R.id.imageView2);


        if(spin == 1){
            spinImageView.setImageDrawable(getResources().getDrawable(R.drawable.prizewheel));
            imageView2.setImageDrawable(getResources().getDrawable(R.drawable.wheel_cursor));

        }else if (spin ==2){
            spinImageView.setImageDrawable(getResources().getDrawable(R.drawable.prizewheel2));
            imageView2.setImageDrawable(getResources().getDrawable(R.drawable.wheel_cursor));
        }

        spinImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinBottle();
            }
        });
    }

    private void spinBottle() {
        int angle = sRandom.nextInt(3600 - 360) + 360;
        // Центр вращения
        float pivotX = spinImageView.getWidth() / 2;
        float pivotY = spinImageView.getHeight() / 2;

        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animation.setDuration(2500);
        animation.setFillAfter(true);

        spinImageView.startAnimation(animation);
    }

    // В примере не используется
    private void resetBottle() {
        float pivotX = spinImageView.getWidth() / 2;
        float pivotY = spinImageView.getHeight() / 2;

        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animation.setDuration(2000);
        animation.setFillAfter(true);

        spinImageView.startAnimation(animation);
    }


}
