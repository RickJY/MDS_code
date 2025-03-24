package com.example.test;

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

        // 初始化控件
        RadioGroup radGroup = findViewById(R.id.radioGroup);
        Button btnChange = findViewById(R.id.btnpost);

        // 动态缩放图片
        int targetWidthPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                80, // 100dp
                getResources().getDisplayMetrics()
        );

        for (int i = 0; i < radioButtonIds.length; i++) {
            RadioButton rb = findViewById(radioButtonIds[i]);
            scaleRadioButtonDrawable(rb, drawableResources[i], targetWidthPx);
        }

        // 设置默认选中
        //radGroup.check(R.id.btn1);

        // 点击事件
        btnChange.setOnClickListener(v -> handleSelection(radGroup));
    }

    private void handleSelection(RadioGroup radGroup) {
        int selectedId = radGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, R.string.please_select, Toast.LENGTH_SHORT).show();
            return;
        }

        int index = Arrays.binarySearch(radioButtonIds, selectedId);
        if (index >= 0) {
            String message = getResources().getStringArray(R.array.option_toasts)[index];
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    // 优化后的图片缩放方法
    private void scaleRadioButtonDrawable(RadioButton radioButton, int drawableResId, int targetWidthPx) {
        Drawable originalDrawable = ContextCompat.getDrawable(this, drawableResId);
        if (originalDrawable == null) return;

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

            if (originalBitmap != scaledBitmap) {
                originalBitmap.recycle();
            }
        } catch (Exception e) {
            Log.e("ScaleDrawable", "Error scaling drawable", e);
        }
    }
}