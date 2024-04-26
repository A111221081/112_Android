//A111221081 葉鎮宇
package com.example.hwmiddle;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    public static String lasttxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editTextText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalPrice();
            }
        });

        // Add a checked change listener to the radio group to update the output in real-time
        RadioGroup type = findViewById(R.id.rgType);
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice() {
        String str = "";
        EditText editText = findViewById(R.id.editTextText);
        int number = 0;
        try {
            number = Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        RadioButton boy = findViewById(R.id.rdbBoy);
        if (boy.isChecked())
            str += "男\n";
        RadioButton girl = findViewById(R.id.rdbGirl);
        if (girl.isChecked())
            str += "女\n";

        RadioGroup type = findViewById(R.id.rgType);
        int checkedId = type.getCheckedRadioButtonId();
        if (checkedId != -1) {
            RadioButton radioButton = findViewById(checkedId);
            str += radioButton.getText().toString() + "\n" + number + "張";

            int price = 0;
            if (checkedId == R.id.rdbAdult) {
                price = number * 500;
            } else if (checkedId == R.id.rdbChild) {
                price = number * 400;
            } else if (checkedId == R.id.rdbStudent) {
                price = number * 250;
            }
            str += "\n" + price + "元";
        }

        TextView output = findViewById(R.id.lblOutput);
        output.setText(str);
        lasttxt = str;
    }

    public void button_Click2(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}