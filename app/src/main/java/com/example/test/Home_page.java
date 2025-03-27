package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;

public class Home_page extends AppCompatActivity {

    private final int[] radioButtonIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
    };

    private final int[] drawableResources = {
            R.drawable.develop0, R.drawable.develop1, R.drawable.develop2,
            R.drawable.develop3, R.drawable.develop4, R.drawable.develop5,
            R.drawable.develop6, R.drawable.develop7, R.drawable.develop8,
            R.drawable.develop9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // To find our Button ID
        RadioGroup radGroup = findViewById(R.id.radioGroup);
        Button btnChange = findViewById(R.id.btnpost);

        // Initialize our image
        int targetWidthPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                80, // To define picture size
                //To get our image resource
                getResources().getDisplayMetrics()
        );
        //To fix our picture if they are big
        for (int i = 0; i < radioButtonIds.length; i++) {
            RadioButton rb = findViewById(radioButtonIds[i]);
            scaleRadioButtonDrawable(rb, drawableResources[i], targetWidthPx);
        }

        // To set a button is choice
        //radGroup.check(R.id.btn1);

        // Click activity
        btnChange.setOnClickListener(v -> handleSelection(radGroup));

        Button btnGoTopage2 = findViewById(R.id.btnGoToPage2);
        btnGoTopage2.setOnClickListener(v -> {
            Intent intent = new Intent(Home_page.this, HomePage2Activity.class);
            startActivity(intent);
        });
    }

    private void handleSelection(RadioGroup radGroup) {
        //Define select button id to match customer selected button
        int selectedId = radGroup.getCheckedRadioButtonId();
        //if there is no button be chose, back a wrong message
        if (selectedId == -1) {
            Toast.makeText(this, R.string.please_select, Toast.LENGTH_SHORT).show();
            return;
        }
        //if there is a button which is chose. find the id in array
        int index = Arrays.binarySearch(radioButtonIds, selectedId);
        if (index >= 0) {
            String message = getResources().getStringArray(R.array.option_toasts)[index];
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    //
    private void scaleRadioButtonDrawable(RadioButton radioButton, int drawableResId, int targetWidthPx) {
        Drawable originalDrawable = ContextCompat.getDrawable(this, drawableResId);
        //if no data, the function will return.
        if (originalDrawable == null) return;
        //running the function until all element has been solve.
        try {
            Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
            float aspectRatio = originalBitmap.getHeight() / (float) originalBitmap.getWidth();
            int targetHeightPx = Math.round(targetWidthPx * aspectRatio);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                    originalBitmap,
                    targetWidthPx,
                    targetHeightPx,
                    true
            );
            Drawable scaledDrawable = new BitmapDrawable(getResources(), scaledBitmap);
            scaledDrawable.setBounds(0, 0, targetWidthPx, targetHeightPx);
            radioButton.setCompoundDrawables(scaledDrawable, null, null, null);
            //clean our memory space
            if (originalBitmap != scaledBitmap) {
                originalBitmap.recycle();
            }
        } catch (Exception e) {
            //Print a message on console
            Log.e("ScaleDrawable", "Error scaling drawable", e);
        }
    }
}