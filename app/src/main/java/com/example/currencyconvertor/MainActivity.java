package com.example.currencyconvertor;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    CurrencyApiHandler api=new CurrencyApiHandler();
    Spinner fromcurerncy,tocurrency;
    EditText amount;
    static TextView data,datan;
    database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new database(this);
        fromcurerncy=(Spinner) findViewById(R.id.from);
        tocurrency=(Spinner) findViewById(R.id.to);
        amount=(EditText) findViewById(R.id.amount);
        data=(TextView) findViewById(R.id.textView4);
        datan=(TextView) findViewById(R.id.textView5);
        db.insertdata("from","");
        db.insertdata("to","");

    }
    public void convert(View view)
    {
        final String from;
        final String to;
        final String[] da = new String[1];
        final String[] da1 = new String[1];
        final String amountinsereted;
        final int[] data1 = new int[1];
        final float[] data2 = new float[1];
        final float[] data3 = new float[1];
        final int[] converted = new int[1];
        amountinsereted=amount.getText().toString().trim();
        from=String.valueOf(fromcurerncy.getSelectedItem());
        to=String.valueOf(tocurrency.getSelectedItem());
        api.getdata(from,to);
        api.execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                da[0] =data.getText().toString().trim();
                Toast toast=Toast.makeText(MainActivity.this,""+da[0],Toast.LENGTH_LONG);
                toast.show();
                da1[0] =datan.getText().toString().trim();
                data2[0] =Float.parseFloat(da[0]);//to currency
                data3[0] =Float.parseFloat(da1[0]);//from currency
            }
        }, 3500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data1[0] =Integer.parseInt(amountinsereted);
                if(data3[0]>data2[0])
                {
                    converted[0] = (int) (data1[0] /data3[0]);
                }
                else {
                    converted[0] =(int) (data1[0] *data2[0]);
                }
                db.update_data("from",from+"  "+amountinsereted);
                db.update_data("to",to+"  "+converted[0]);
                Intent intent=new Intent(MainActivity.this,Result.class);
                startActivity(intent);
            }
        }, 4000);


    }


}
