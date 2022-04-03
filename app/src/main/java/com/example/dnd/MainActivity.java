package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        System.out.println(this.getFilesDir().toString());
//        String path = Environment.getExternalStorageDirectory().toString() + "/Android"
    }

    @Override
    public void onResume() {
        super.onResume();

        String path = this.getFilesDir().toString();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        for (File file : files) {
            Log.d("Files", "FilePath: " + file.getPath());
//            Log.d("Files", "FileName: " + file.getName());
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SheetActivity.class);
//                intent.putExtra("fileName", name);
//                String json = readFile(files[files.length - 1]);
                String json = readFile(files[0]);
                intent.putExtra("json", json);
                startActivityForResult(intent, 300);
            }
        });
    }

    protected String readFile(File file) {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {

            //You'll need to add proper error handling here
        }
        return text.toString();
    }
}
