package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button, dnd_su;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();

        String path = this.getFilesDir().toString();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();
        Log.d("Files", "Size: " + files.length);
        for (File file : files) {
            Log.d("Files", "FilePath: " + file.getPath());
            fileNames.add(file.getName().replace(".json", ""));
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SheetActivity.class);
                intent.putExtra("json", "");

                startActivityForResult(intent, 300);
            }
        });

        dnd_su = findViewById(R.id.dnd_su_button);
        dnd_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DndSu.class);
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.listView);

        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, fileNames);
        listView.setAdapter(a);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println(fileNames.get(position));
            Intent intent = new Intent(MainActivity.this, SheetActivity.class);
            String json = readFile(files[position]);
            intent.putExtra("json", json);
            startActivityForResult(intent, 300);
//                a.notifyDataSetInvalidated();
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
            e.printStackTrace();
        }
        return text.toString();
    }
}
