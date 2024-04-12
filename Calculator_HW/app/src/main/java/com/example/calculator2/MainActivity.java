package com.example.calculator2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

// 枚举类型用于表示计算器状态和操作符
enum State {FirstNumberInput, OperatorInputed, SecondNumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private String theValue = ""; // 当前值
    private String operand1 = ""; // 第一个操作数
    private String operand2 = ""; // 第二个操作数
    private String dotNumber = ""; // 小数点后的数字

    private OP op = OP.None; // 当前操作符
    private State state = State.FirstNumberInput; // 当前状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout keysGL = (GridLayout) findViewById(R.id.keys);
        int kbHeight = (int) (keysGL.getHeight() / keysGL.getRowCount());
        int kbWidth = (int) (keysGL.getWidth()/keysGL.getColumnCount());
        Log.v("value","kbheight_after"+kbHeight);
        Log.v("value","kbheight_after"+kbWidth);
    }


    @Override
    protected  void  onPause() {


        super.onPause();
        SharedPreferences appsharepref  = getSharedPreferences("pre_value",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = appsharepref.edit();
        prefsEditor.putString("newItem",(new Integer(tempinput)).toString());
        prefsEditor.commit();
    }
    @Override
    protected  void  onResume() {


        super.onResume();

        SharedPreferences appshareprefs = getSharedPreferences("pre_value", MODE_PRIVATE);
        theValue=(new  Integer((appshareprefs.getString("newItem","0"))));
    }

    // 在窗口焦点改变时调用，调整按钮大小和文本大小
    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = findViewById(R.id.id.keys);

        int kbHeight = keysGL.getHeight() / keysGL.getRowCount();
        int kbWidth = keysGL.getWidth() / keysGL.getColumnCount();
        Log.v("value","kbheight_after"+kbHeight);
        Log.v("value","kbheight_after"+kbWidth);
        Button btn;

        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
        EditText edt = (EditText) findViewById(R.id.display);
        edt.setText((CharSequence) (""+theValue));
    }

    // 处理按钮点击事件
    public void processKeyInput(View view) {
        Button b = (Button) view; // 获取点击的按钮
        String bstr = b.getText().toString(); // 获取按钮上的文字
        EditText edt = findViewById(R.id.display); // 获取显示结果的 EditText 元件

        try {
            // 处理数字按钮点击事件
            switch (bstr) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    theValue += bstr; // 将数字附加到当前值后面
                    edt.setText(theValue); // 更新 EditText 显示
                    break;

                case ".": // 处理小数点按钮点击事件
                    if (!theValue.contains(".")) { // 如果当前值不包含小数点，则可以添加小数点
                        theValue += ".";
                        edt.setText(theValue); // 更新 EditText 显示
                    }
                    break;

                // 运算符按钮被点击时
                case "+":
                case "-":
                case "*":
                case "/":
                    operand1 = theValue; // 设置第一个操作数
                    op = getOperator(bstr); // 设置当前操作符
                    state = State.OperatorInputed; // 进入运算符输入状态
                    edt.setText(theValue + bstr); // 更新 EditText 显示
                    theValue = ""; // 清空当前值
                    dotNumber = ""; // 清空小数点后的数字
                    break;

                // 等号按钮被点击时
                case "=":
                    operand2 = theValue; // 设置第二个操作数
                    BigDecimal result = calculate(operand1, operand2, op); // 计算结果
                    if (result.stripTrailingZeros().scale() <= 0) { // 如果结果是整数
                        theValue = String.valueOf(result.intValue()); // 将结果转换为整数字符串
                    } else {
                        theValue = String.valueOf(result.doubleValue()); // 将结果转换为双精度浮点数字符串
                    }
                    edt.setText(theValue); // 更新 EditText 显示
                    operand1 = theValue; // 更新第一个操作数为结果
                    op = OP.None; // 重置操作符
                    state = State.FirstNumberInput; // 回到第一个数字输入状态
                    break;

                // 清除按钮被点击时
                case "Clear":
                    operand1 = ""; // 清空第一个操作数
                    operand2 = ""; // 清空第二个操作数
                    theValue = ""; // 清空当前值
                    dotNumber = ""; // 清空小数点后的数字
                    op = OP.None; // 重置操作符
                    state = State.FirstNumberInput; // 回到第一个数字输入状态
                    edt.setText("0"); // 在 EditText 中显示 0
                    break;

                // 退格按钮被点击时
                case "Back":
                    if (!theValue.isEmpty()) { // 当前值不为空时
                        theValue = theValue.substring(0, theValue.length() - 1); // 移除当前值的最后一个字符
                        edt.setText(theValue); // 更新 EditText 显示
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            // 捕获转换异常
            Log.e("Error", "NumberFormatException: " + e.getMessage());
            edt.setText("Error"); // 在 EditText 中显示错误信息
        }
    }

    // 根据操作符字符串获取对应的操作符枚举
    private OP getOperator(String operator) {
        switch (operator) {
            case "+":
                return OP.Add;
            case "-":
                return OP.Sub;
            case "*":
                return OP.Mul;
            case "/":
                return OP.Div;
            default:
                return OP.None;
        }
    }

    // 计算两个操作数的结果
    private BigDecimal calculate(String operand1, String operand2, OP op) {
        BigDecimal num1 = new BigDecimal(operand1);
        BigDecimal num2 = new BigDecimal(operand2);

        switch (op) {
            case Add:
                return num1.add(num2);
            case Sub:
                return num1.subtract(num2);
            case Mul:
                return num1.multiply(num2);
            case Div:
                if (num2.compareTo(BigDecimal.ZERO) == 0) { // 除数为0时，返回null
                    return null;
                }
                return num1.divide(num2, 10, BigDecimal.ROUND_HALF_UP); // 小数点精度为10位，四舍五入
            default:
                return BigDecimal.ZERO;
        }
    }
}
