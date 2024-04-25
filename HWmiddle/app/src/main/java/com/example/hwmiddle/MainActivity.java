package com.example.hwmiddle;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rgGender), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void button_Click(View view) {
        String str = "";
        EditText editText = (EditText)  findViewById(R.id.editTextText);
        int number =Integer.parseInt(editText.getText().toString());
        // 取得性別
        RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
        if (boy.isChecked())
            str += "男\n";
        RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
        if (girl.isChecked())
            str += "女\n";
        // 取得門票種類
        RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
        if (type.getCheckedRadioButtonId() == R.id.rdbAdult)
        {
            str += "全票\n"+ number +"張";
            number = number*500;
            str = str + number + "元";
        }
        else if (type.getCheckedRadioButtonId() == R.id.rdbChild)
        {
            str += "學生票\n"+ number +"張";
            number = number*400;
            str = str + number + "元";
        }
        else
        {
            str += "兒童票\n"+ number +"張";
            number = number*250;
            str = str + number + "元";
        }
        TextView output = (TextView) findViewById(R.id.lblOutput);
        output.setText(str);
        Intent intent = new Intent(this, MainActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putString("textview",str.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}