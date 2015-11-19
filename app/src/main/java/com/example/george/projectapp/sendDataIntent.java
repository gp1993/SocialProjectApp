package com.example.george.projectapp;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class sendDataIntent extends IntentService {
    public sendDataIntent() {
        super("sendDataIntent");

    }

    public static final String PARAM_USER = "";
    public static final String PARAM_PASS = "";
   @Override
    protected void onHandleIntent(Intent mServiceIntent) {

        String param="";
        try {
           param = "param1=" + URLEncoder.encode(PARAM_USER, "UTF-8") + "&param2=" + URLEncoder.encode(PARAM_PASS, "UTF-8");
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
           while (inStream.hasNextLine()) {
               response += (inStream.nextLine());
               System.out.println(response);
           }
       }

       catch (MalformedURLException ex) {
           Toast.makeText(sendDataIntent.this, ex.toString(), 1).show();

       }

       catch (IOException ex) {

           Toast.makeText(sendDataIntent.this, ex.toString(), 1).show();
       }
   }
   }

