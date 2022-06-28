package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity  {
EditText nameEditText, passEditText;
private Button nextbt, savebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.nametext);
        passEditText = (EditText) findViewById(R.id.passtext);

        nextbt = (Button) findViewById(R.id.nextbt);
        nextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Retrieve.class);
                startActivity(intent);
            }
        });

        savebt = (Button) findViewById(R.id.savebtn);
        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save(view);
            }
        });
    }

    public void Save(View view){
        String name = nameEditText.getText().toString() + " ";
        String password = passEditText.getText().toString();

        File file = null;
        FileOutputStream fileOutputStream = null;

        try{
            name = name + "";
            file = getFilesDir();

            fileOutputStream = openFileOutput("androidtext.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(password.getBytes());
            fileOutputStream.close();

            Toast.makeText(MainActivity.this, "Your file has been \n at path " + file +
                    "\t androidtext.txt ", Toast.LENGTH_LONG).show();

            nameEditText.setText("");
            passEditText.setText("");

            return;
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}