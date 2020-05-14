package com.classicspin.wg.spin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.classicspin.wg.R;

import static com.classicspin.wg.spin.SpinActivity.SPIN;

public class SpinSelectActivity extends AppCompatActivity {
    ImageView imageView, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_select);


        imageView = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView4);
        imageView3 = findViewById(R.id.imageView5);

        imageView.setOnClickListener(v -> {
            startSpinActivity(1);
        });
        imageView2.setOnClickListener(v -> {
            startSpinActivity(2);
        });

        imageView3.setOnClickListener(v -> {
            startSpinActivity(3);
        });

    }

    private void startSpinActivity(int value) {
        Intent intent = new Intent(this, SpinActivity.class);
        intent.putExtra(SPIN, value);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
