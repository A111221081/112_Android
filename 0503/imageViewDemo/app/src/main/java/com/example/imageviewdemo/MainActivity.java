package com.example.imageviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private ImageView image1,image2,image3,image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1=(ImageView) findViewById(R.id.imgOutput1);
        image2=(ImageView) findViewById(R.id.imgOutput2);
        image3=(ImageView) findViewById(R.id.imgOutput3);
        image4=(ImageView) findViewById(R.id.imgOutput4);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgImages);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rdbDog)
                {
                    image1.setVisibility(ImageView.VISIBLE);
                    image2.setVisibility(ImageView.GONE);
                    image3.setVisibility(ImageView.GONE);
                    image4.setVisibility(ImageView.GONE);
                }
                else if(checkedId == R.id.rdbElephant)
                {
                    image1.setVisibility(ImageView.GONE);
                    image2.setVisibility(ImageView.VISIBLE);
                    image3.setVisibility(ImageView.GONE);
                    image4.setVisibility(ImageView.GONE);
                }
                else if(checkedId == R.id.rdbMouse)
                {
                    image1.setVisibility(ImageView.GONE);
                    image2.setVisibility(ImageView.GONE);
                    image3.setVisibility(ImageView.VISIBLE);
                    image4.setVisibility(ImageView.GONE);
                }
                else if(checkedId == R.id.rdbRabbit)
                {
                    image1.setVisibility(ImageView.GONE);
                    image2.setVisibility(ImageView.GONE);
                    image3.setVisibility(ImageView.GONE);
                    image4.setVisibility(ImageView.VISIBLE);
                }
            }
        });
    }
}