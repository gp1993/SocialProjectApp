package com.example.george.projectapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button
        Button button = (Button) findViewById(R.id.sendButton);

        //get details entered
        final EditText user = (EditText) findViewById(R.id.EditText1);
        final EditText pass = (EditText) findViewById(R.id.EditText2);

        //set button to listen
        button.setOnClickListener(new OnClickListener(){
            @Override
            //button onclick do this
            public void onClick(final View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                Intent mServiceIntent = new Intent(MainActivity.this, sendDataIntent.class);
                mServiceIntent.putExtra(sendDataIntent.PARAM_USER, username);
                mServiceIntent.putExtra(sendDataIntent.PARAM_PASS, password);
                // Starts the IntentService
                startService(mServiceIntent);

            }
        });
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