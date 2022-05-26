package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button roomButton, masterButton;
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
            if(!file.getName().equals("file.txt")){
                Log.d("Files", "FilePath: " + file.getPath());
                fileNames.add(file.getName().replace(".json", ""));
            }
        }

        button = findViewById(R.id.button);
        roomButton = findViewById(R.id.room_button);
        masterButton = findViewById(R.id.MasterButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SheetActivity.class);
                intent.putExtra("json", "");
                startActivityForResult(intent, 300);
            }
        });
        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                startActivity(intent);
            }
        });

        masterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MasterActivity.class);
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
                intent.putExtra("file_name", fileNames.get(position) + ".json");
                intent.putExtra("file_path", files[position].getPath());
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
