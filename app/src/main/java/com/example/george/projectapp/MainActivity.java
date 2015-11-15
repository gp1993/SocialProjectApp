package com.example.george.projectapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    //initialize
    private EditText user;
    private EditText pass;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.EditText1);
        pass = (EditText) findViewById(R.id.EditText2);


    }

    //do something with button
    public void sendData (View view) {
        String username = user.getText().toString();
        String password = pass.getText().toString();

        String param = null;
        try {
            param = "param1=" + URLEncoder.encode(username, "UTF-8") + "&param2=" + URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {


            // open a connection to the site
            URL url = new URL("http://192.168.1.96/android/getdata.php");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //set output to true to post
            con.setDoOutput(true);
            //POST
            con.setRequestMethod("POST");
            con.setFixedLengthStreamingMode(param.getBytes().length);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //start printwriter
            PrintWriter ps = new PrintWriter(con.getOutputStream());
            // send parameters
            ps.print(param);

            // close the print stream
            ps.close();

            //get response
            String response = "";
            Scanner inStream = new Scanner(con.getInputStream());
            while (inStream.hasNextLine())
                response += (inStream.nextLine());

        }

        catch (MalformedURLException ex) {
            Toast.makeText(MainActivity.this, ex.toString(), 1).show();

        }
// and some more
        catch (IOException ex) {

            Toast.makeText(MainActivity.this, ex.toString(), 1).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}