package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;

public class Retrieve extends AppCompatActivity {
    EditText nameEditText, passEditText;
    Button retrievebt, backbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive);

        nameEditText = (EditText)findViewById(R.id.nametext);
        passEditText = (EditText)findViewById(R.id.passtext);

        retrievebt = (Button) findViewById(R.id.retrievebtn);
        retrievebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrieve(view);
            }
        });

        backbt = (Button) findViewById(R.id.backbtn);
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back(view);
            }
        });
    }

    public void Retrieve(View view){
        try{
            FileInputStream fileInputStream = openFileInput("androidtext.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read=fileInputStream.read()) != -1){
                buffer.append((char)read);
            }

            String name = buffer.substring(buffer.indexOf(""), buffer.indexOf(" "));
            String password = buffer.substring(buffer.indexOf(" ")+1);

            nameEditText.setText(name);
            passEditText.setText(password);

        }catch (Exception e){
            Log.e("Exception: ", e.toString());
        }
    }

    public void Back(View view){
        Intent intent = new Intent(Retrieve.this, MainActivity.class);
        startActivity(intent);
    }
}