package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SheetActivity extends AppCompatActivity {
    Button buttonBack;
    EditText editTextId;
    EditText editTextName;
    EditText editTextClass;
    EditText editTextLevel;

    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sheet);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();


        buttonBack = findViewById(R.id.buttonBack);
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextClass = findViewById(R.id.editTextClass);
        editTextLevel = findViewById(R.id.editTextLevel);

        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("json");
        System.out.println(jsonString);

        try {
            json = new JSONObject(jsonString);

        } catch (JSONException err) {
            Log.d("Error", err.toString());
        }
        try {
            System.out.println(json.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            editTextId.setText(json.getInt("id") + "");
            editTextName.setText(json.getString("name"));
            editTextClass.setText(json.getString("class"));
            editTextLevel.setText(json.getInt("level") + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        File dir = Environment.getExternalStorageDirectory();
//        System.out.println(dir);
//        File file = new File(dir, persFileName);

//Read text from file
//        StringBuilder text = new StringBuilder();

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                text.append(line);
//                text.append('\n');
//            }
//            br.close();
//        } catch (IOException e) {
//            //You'll need to add proper error handling here
//        }


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject pers1 = new JSONObject();
                String name = editTextName.getText().toString();
                if (!editTextLevel.getText().toString().equals("") && !editTextId.getText().toString().equals("")) {
                    try {
                        pers1.put("id", Integer.parseInt(editTextId.getText().toString()));
                        pers1.put("name", name);
                        pers1.put("class", editTextClass.getText().toString());
                        pers1.put("level", Integer.parseInt(editTextLevel.getText().toString()));
                    } catch (JSONException e) {    // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.put(student1);
//                JSONObject studentsObj = new JSONObject();
//                try {
//                    studentsObj.put("Students", jsonArray);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String jsonStr = studentsObj.toString();
                String jsonStr = pers1.toString();
                System.out.println("jsonString: " + jsonStr);
                writeToFile(name + ".json", jsonStr, getBaseContext());
                readFile(name + ".json");

                finish();
            }
        });
    }

    private void writeToFile(String fileName, String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            System.out.println("Created");
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFile(String path) {
        System.out.println("read");
        return "";
    }
}