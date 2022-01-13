package com.example.credit_card_validation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText number = (EditText) findViewById(R.id.credit_edit);
//       String str=number.toString();
//       long num=Long.parseLong(str);
//        double num=Double.parseDouble(str);
//        long num1=(long)num;
        EditText cvv=(EditText) findViewById(R.id.ccv_edit);
        EditText first=(EditText) findViewById(R.id.first_edit);
        EditText last=(EditText) findViewById(R.id.last_edit);
        Button submit=(Button) findViewById(R.id.payment);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid(number) && isCcv(cvv))
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Payment Successfull",Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                    if(!isValid(number))
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Invalid Credit Card number",Toast.LENGTH_LONG);
                    toast.show();
                }
                    if(!isCcv(cvv))
                    {
                        Toast toast=Toast.makeText(getApplicationContext(),"Invalid Cvv",Toast.LENGTH_LONG);
                        toast.show();
                    }
            }
        });
   }
    public static boolean isCcv(EditText cvv)
    {

        if (cvv.length()==4 || cvv.length()==3)
            return true;
        else
            return false;
    }
    public static boolean isValid(EditText number)
    {
        return (getSize(number.length()) >= 13 &&
                getSize(number.length()) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }
    public static int sumOfDoubleEvenPlace(EditText number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number.length()) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }
    public static int sumOfOddPlace(EditText number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number.length()) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    public static boolean prefixMatched(EditText number, int d)
    {

        return getPrefix(number, getSize(d)).equals(d);
    }

    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    public static String getPrefix(EditText number, int k)
    {
        if (getSize(number.length()) > k) {
            String num = number + "";
            return (num.substring(0, k));
        }
        return number.toString();
   }
}