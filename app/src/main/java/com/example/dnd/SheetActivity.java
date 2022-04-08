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
import android.widget.Toast;

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
    EditText editTextName;
    EditText editTextClass;
    EditText editTextLevel;

    EditText etKD;
    EditText etIni;
    EditText etSpeed;
    EditText etHP;
    EditText etTempHP;
    EditText etHPDice;

    EditText etSTR;
    EditText etDEX;
    EditText etCON;
    EditText etINT;
    EditText etWIS;
    EditText etCHA;

    EditText etAthletics;
    EditText etSavSTR;

    EditText etAcrobat;
    EditText etHand;
    EditText etStealth;
    EditText etSavDEX;

    EditText etSavCON;

    EditText etAnaliz;
    EditText etHist;
    EditText etMag;
    EditText etNature;
    EditText etRelig;
    EditText etSavINT;

    EditText etVnimat;
    EditText etSurv;
    EditText etMed;
    EditText etPronic;
    EditText etAnim;
    EditText etSavWIS;

    EditText etPerf;
    EditText etZapug;
    EditText etLie;
    EditText etUbezhd;
    EditText etSavCHA;

    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sheet);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        etKD = findViewById(R.id.etKD);
        etIni = findViewById(R.id.etIni);
        etSpeed = findViewById(R.id.etSpeed);
        etHP = findViewById(R.id.etHP);
        etTempHP = findViewById(R.id.etTempHP);
        etHPDice = findViewById(R.id.etHPDice);
        etSTR = findViewById(R.id.etSTR);
        etAthletics = findViewById(R.id.etAthletics);
        etSavSTR = findViewById(R.id.etSavSTR);
        etDEX = findViewById(R.id.etDEX);
        etAcrobat = findViewById(R.id.etAcrobat);
        etHand = findViewById(R.id.etHand);
        etStealth = findViewById(R.id.etStealth);
        etSavDEX = findViewById(R.id.etSavDEX);
        etCON = findViewById(R.id.etCON);
        etSavCON = findViewById(R.id.etSavCON);
        etINT = findViewById(R.id.etINT);
        etAnaliz = findViewById(R.id.etAnaliz);
        etHist = findViewById(R.id.etHist);
        etMag = findViewById(R.id.etMag);
        etNature = findViewById(R.id.etNature);
        etRelig = findViewById(R.id.etRelig);
        etSavINT = findViewById(R.id.etSavINT);
        etWIS = findViewById(R.id.etWIS);
        etVnimat = findViewById(R.id.etVnimat);
        etSurv = findViewById(R.id.etSurv);
        etMed = findViewById(R.id.etMed);
        etPronic = findViewById(R.id.etPronic);
        etAnim = findViewById(R.id.etAnim);
        etSavWIS = findViewById(R.id.etSavWIS);
        etCHA = findViewById(R.id.etCHA);
        etPerf = findViewById(R.id.etPerf);
        etUbezhd = findViewById(R.id.etUbezhd);
        etZapug = findViewById(R.id.etZapug);
        etLie = findViewById(R.id.etLie);
        etSavCHA = findViewById(R.id.etSavCHA);


        buttonBack = findViewById(R.id.buttonBack);
        editTextName = findViewById(R.id.editTextName);
        editTextClass = findViewById(R.id.editTextClass);
        editTextLevel = findViewById(R.id.editTextLevel);

        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("json");
        System.out.println(jsonString);

        if (!jsonString.equals("")) {
            try {
                json = new JSONObject(jsonString);
                editTextName.setText(json.getString("name"));
                editTextClass.setText(json.getString("class"));
                editTextLevel.setText(json.getString("level"));
                etKD.setText(json.getString("KD"));
                etIni.setText(json.getString("initiative"));
                etSpeed.setText(json.getString("speed"));
                etHP.setText(json.getString("HP"));
                etTempHP.setText(json.getString("tempHP"));
                etHPDice.setText(json.getString("HPDice"));
                etSTR.setText(json.getString("STR"));
                etAthletics.setText(json.getString("athletics"));
                etSavSTR.setText(json.getString("savSTR"));
                etDEX.setText(json.getString("DEX"));
                etAcrobat.setText(json.getString("acrobatics"));
                etHand.setText(json.getString("hand"));
                etStealth.setText(json.getString("stealth"));
                etSavDEX.setText(json.getString("savDEX"));
                etCON.setText(json.getString("CON"));
                etSavCON.setText(json.getString("savCON"));
                etINT.setText(json.getString("INT"));
                etAnaliz.setText(json.getString("analiz"));
                etHist.setText(json.getString("hist"));
                etMag.setText(json.getString("mag"));
                etNature.setText(json.getString("nature"));
                etRelig.setText(json.getString("relig"));
                etSavINT.setText(json.getString("savINT"));
                etWIS.setText(json.getString("WIS"));
                etVnimat.setText(json.getString("vnimat"));
                etSurv.setText(json.getString("surv"));
                etMed.setText(json.getString("med"));
                etPronic.setText(json.getString("pronic"));
                etAnim.setText(json.getString("anim"));
                etSavWIS.setText(json.getString("savWIS"));
                etCHA.setText(json.getString("CHA"));
                etPerf.setText(json.getString("perf"));
                etUbezhd.setText(json.getString("zapug"));
                etZapug.setText(json.getString("lie"));
                etLie.setText(json.getString("ubezhd"));
                etSavCHA.setText(json.getString("savCHA"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject pers1 = new JSONObject();
                String name = editTextName.getText().toString();
                if (!editTextLevel.getText().toString().equals("")) {
                    try {
                        pers1.put("name", name);
                        pers1.put("class", editTextClass.getText().toString());
                        pers1.put("level", editTextLevel.getText().toString());
                        pers1.put("KD", etKD.getText().toString());
                        pers1.put("initiative", etIni.getText().toString());
                        pers1.put("speed", etSpeed.getText().toString());
                        pers1.put("HP", etHP.getText().toString());
                        pers1.put("tempHP", etTempHP.getText().toString());
                        pers1.put("HPDice", etHPDice.getText().toString());
                        pers1.put("STR", etSTR.getText().toString());
                        pers1.put("athletics", etAthletics.getText().toString());
                        pers1.put("savSTR", etSavSTR.getText().toString());
                        pers1.put("DEX", etDEX.getText().toString());
                        pers1.put("acrobatics", etAcrobat.getText().toString());
                        pers1.put("hand", etHand.getText().toString());
                        pers1.put("stealth", etStealth.getText().toString());
                        pers1.put("savDEX", etSavDEX.getText().toString());
                        pers1.put("CON", etCON.getText().toString());
                        pers1.put("savCON", etSavCON.getText().toString());
                        pers1.put("INT", etINT.getText().toString());
                        pers1.put("analiz", etAnaliz.getText().toString());
                        pers1.put("hist", etHist.getText().toString());
                        pers1.put("mag", etMag.getText().toString());
                        pers1.put("nature", etNature.getText().toString());
                        pers1.put("relig", etRelig.getText().toString());
                        pers1.put("savINT", etSavINT.getText().toString());
                        pers1.put("WIS", etWIS.getText().toString());
                        pers1.put("vnimat", etVnimat.getText().toString());
                        pers1.put("surv", etSurv.getText().toString());
                        pers1.put("med", etMed.getText().toString());
                        pers1.put("pronic", etPronic.getText().toString());
                        pers1.put("anim", etAnim.getText().toString());
                        pers1.put("savWIS", etSavWIS.getText().toString());
                        pers1.put("CHA", etCHA.getText().toString());
                        pers1.put("perf", etPerf.getText().toString());
                        pers1.put("zapug", etZapug.getText().toString());
                        pers1.put("lie", etLie.getText().toString());
                        pers1.put("ubezhd", etUbezhd.getText().toString());
                        pers1.put("savCHA", etSavCHA.getText().toString());

                        String jsonStr = pers1.toString();
                        System.out.println("jsonString: " + jsonStr);
                        writeToFile(name + ".json", jsonStr, getBaseContext());
                        Toast.makeText(getBaseContext(), "Успешно сохранено", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//              JSONArray jsonArray = new JSONArray();
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
}
