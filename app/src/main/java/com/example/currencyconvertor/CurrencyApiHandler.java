package com.example.currencyconvertor;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrencyApiHandler extends AsyncTask<Void,Void,Void> {

    public String s = "", t = "", data = "",valuefrom="",valueto="";

    public void getdata(String currencyfrom, String currencyto) {
        s = currencyfrom;
        t = currencyto;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + s + "," + t);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                System.out.println(line);
                data = data + line;
            }
            JSONObject obj = new JSONObject(data);
            JSONObject rates = obj.getJSONObject("rates");
            valueto=rates.getString(t);
            valuefrom=rates.getString(s);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(valueto);
        MainActivity.datan.setText(valuefrom);



    }


}
