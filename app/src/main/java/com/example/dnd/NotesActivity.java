package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotesActivity extends AppCompatActivity {
    EditText notes_site;
    Button bth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notes_site = findViewById(R.id.editTextNotes);
        bth = findViewById(R.id.button);

        try {
            FileInputStream inputStream = openFileInput("file.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append("\n");
            }
            notes_site.setText(total);
            r.close();
            inputStream.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }


        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(NotesActivity.this);
                builder1.setTitle("Clear");
//                builder1.setMessage("Are you sure?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        (dialog, id) -> notes_site.setText(""));

                builder1.setNegativeButton(
                        "No",
                        (dialog, id) -> {
                            Toast.makeText(getBaseContext(),
                                    "ok",
                                    Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        notes_site.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                saveNote(notes_site.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveNote(notes_site.getText().toString());
            }
        });

    }


    public void saveNote(String res) {
        String filename = "file.txt";
        String outputString = res;

        try {
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(outputString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream inputStream = openFileInput(filename);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append("\n");
            }
            r.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}