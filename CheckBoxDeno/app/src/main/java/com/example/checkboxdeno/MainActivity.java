package com.example.checkboxdeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {
    private CheckBox ChkOriginal,ChkBeef,ChkSeafood;
    private TextView output;

    private int[] chkIDs = {R.id.chkOriginal,R.id.chkBeef,R.id.chkSeafood};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ChkOriginal = (CheckBox) findViewById(R.id.chkOriginal);
//        ChkBeef = (CheckBox) findViewById(R.id.chkBeef);
//        ChkSeafood = (CheckBox) findViewById(R.id.chkSeafood);

        for(int id : chkIDs)
        {
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }

        output = (TextView) findViewById(R.id.lblOutput);

//        Button btnConfirm = (Button) findViewById(R.id.button);
//
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = "";
//                if(ChkOriginal.isChecked())
//                {
//                    str +=ChkOriginal.getText()+"\n";
//                }
//                if(ChkBeef.isChecked())
//                {
//                    str +=ChkBeef.getText()+"\n";
//                }
//                if(ChkSeafood.isChecked())
//                {
//                    str +=ChkSeafood.getText() +"\n";
//                }
//            }
//        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        TextView txvStatus = (TextView) findViewById(R.id.txvStatus);
        int id = buttonView.getId();
        String status = "";
        if(id == R.id.chkOriginal)
        {
            status = (isChecked? "選取" : "取消" ) + "原味...";
        }
        if(id == R.id.chkBeef)
        {
            status = (isChecked? "選取" : "取消" ) + "牛肉...";
        }
        if(id == R.id.chkSeafood)
        {
            status = (isChecked? "選取" : "取消" ) + "海鮮...";
        }
        txvStatus.setText(status);

        showResult();
    }

    public void showResult()
    {
        String str = "";
        for(int i :chkIDs)
        {
            CheckBox chk = (CheckBox) findViewById(i);
            if(chk.isChecked())
            {
                str += chk.getText() + "\n";
            }
            output.setText(str);
        }
    }
}