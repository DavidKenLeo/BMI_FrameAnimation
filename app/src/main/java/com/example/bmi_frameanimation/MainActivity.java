package com.example.bmi_frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        playFrameAnimation();
    }

    private Button calcbutton;
    private EditText fieldheight, fieldweight;
    private TextView view_result, view_suggest, tipText2, tipText3;
    private LinearLayout description;
    private ImageView imageView1;

    private void findViews() {
        description = (LinearLayout) findViewById(R.id.description);
        calcbutton = (Button) findViewById(R.id.submit);
        fieldheight = (EditText) findViewById(R.id.height);
        fieldweight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
        tipText2 = (TextView) findViewById(R.id.tipText2);
        tipText3 = (TextView) findViewById(R.id.tipText3);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

    }

    // Listen for button clicks
    private void setListeners() {
        calcbutton.setOnClickListener(calcBMI);
        description.setOnClickListener(descListener);
    }

    private Button.OnClickListener calcBMI = new Button.OnClickListener() {
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");

            double height = Double
                    .parseDouble(fieldheight.getText().toString()) / 100;
            double weight = Double
                    .parseDouble(fieldweight.getText().toString());
            double BMI = weight / (height * height);
            // Present result
            view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // Give health advice
            view_suggest = (TextView) findViewById(R.id.suggest);
            if (BMI > 25) {
                view_suggest.setText(R.string.advice_heavy);
            } else if (BMI < 20) {
                view_suggest.setText(R.string.advice_light);
            } else {
                view_suggest.setText(R.string.advice_average);
            }
        }
    };

    // 建立說明畫面所使用的監聽器
    private View.OnClickListener descListener = new View.OnClickListener() {
        int clickCount = 1;

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (clickCount == 1) {
                tipText2.setVisibility(View.VISIBLE);
                clickCount++;
            } else if (clickCount == 2) {
                tipText3.setVisibility(View.VISIBLE);
                clickCount++;
            } else
                description.setVisibility(View.GONE);
        }
    };

    void playFrameAnimation() {
        AnimationDrawable anim1 = (AnimationDrawable) imageView1.getBackground();
        anim1.start();
    }
}
