package com.example.perforamancetest.overdraw_custom_view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.performancetest.R;


/**
 * Demonstration Activity to present aview showing overlapping stack of cards. This will demonstrate difference in UI rendering performance
 * for both View with extra drawing and another with clipping to avoid drawing areas of the screen which are not visible
 */
public class ImageActivity extends Activity {

    private ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        imageView = findViewById(R.id.image_view);

        findViewById(R.id.btn_add_cards_View).setOnClickListener(v -> {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_resource_2700_1800);
            imageView.setImageBitmap(bitmap);

        });
    }
}