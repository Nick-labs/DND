package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SheetActivity extends AppCompatActivity {
    Button buttonBack, dice, deleteButton, etHP_Button, connectBtn;
    EditText etIP;

    EditText editTextName, editTextClass, editTextLevel, etKD, etIni, etSpeed, etTempHP, etHPDice, etSTR;
    EditText etDEX, etCON, etINT, etWIS, etCHA, etAthletics, etSavSTR, etAcrobat, etHand, etStealth;
    EditText etSavDEX, etSavCON, etAnaliz, etHist, etMag, etNature, etRelig, etSavINT, etVnimat;
    EditText etSurv, etMed, etPronic, etAnim, etSavWIS, etPerf, etZapug, etLie, etUbezhd, etSavCHA;
    EditText etInv, etProf, etFeatures, etHP;

    JSONObject json;

    String serverIP;
    int serverPort = 8080;

    private PrintWriter output;
    private BufferedReader input;

    Thread Thread1 = null;
    Socket socket = null;

    String now_hp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sheet);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        etKD = findViewById(R.id.etKD);
        etIni = findViewById(R.id.etIni);
        etSpeed = findViewById(R.id.etSpeed);
        etHP_Button = findViewById(R.id.etHP_Button);
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
        deleteButton = findViewById(R.id.deleteButton);
        dice = findViewById(R.id.dice);
        editTextName = findViewById(R.id.editTextName);
        editTextClass = findViewById(R.id.editTextClass);
        editTextLevel = findViewById(R.id.editTextLevel);
        etInv = findViewById(R.id.editTextInv);
        etProf = findViewById(R.id.editTextProf);
        etFeatures = findViewById(R.id.editTextFeatures);

        etIP = findViewById(R.id.etIP);
        connectBtn = findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverIP = etIP.getText().toString().trim();
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });



        /*
            вызов диалогового окна для кубиков
        */
        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActivity(SheetActivity.this).show();
            }
        });

        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("json");
        String path = this.getFilesDir().toString();
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

                etInv.setText(json.getString("inv"));
                etProf.setText(json.getString("prof"));
                etFeatures.setText(json.getString("features"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        etHP_Button.setText(etHP.getText() + "/" + etHP.getText());
        now_hp = String.valueOf(etHP.getText());
        etHP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                now_hp = String.valueOf(etHP.getText());
                etHP_Button.setText(etHP.getText() + "/" + etHP.getText());
            }
        });

        etHP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hp = etHP.getText().toString();

                dialogActivityCalc(SheetActivity.this, hp).show();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(SheetActivity.this);
                builder1.setTitle("Delete");
                builder1.setMessage("Are you sure?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                try {
                                    File file = new File(path + "/" + json.getString("name") + ".json");
                                    System.out.println("Delete " + file);
                                    System.out.println(file.exists());
                                    if (file.exists()) {
                                        boolean deleted = file.delete();
                                        if (deleted) {
                                            Toast.makeText(getBaseContext(), "Удалено", Toast.LENGTH_LONG).show();
                                        }
                                        ;
                                        System.out.println("Удалено");
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getBaseContext(),
                                        "Молодец, и не надо удалять, это плохо))))",
                                        Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


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
                        pers1.put("inv", etInv.getText().toString());
                        pers1.put("prof", etProf.getText().toString());
                        pers1.put("features", etFeatures.getText().toString());

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


    private Dialog dialogActivityCalc(Context context, String hp) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hp_calc);

        TextView newHp = (TextView) dialog.findViewById(R.id.newHp);
        TextView oldHp = (TextView) dialog.findViewById(R.id.oldHp);
        EditText panel = (EditText) dialog.findViewById(R.id.ansED);

        Button removeBth = (Button) dialog.findViewById(R.id.removeBth);
        Button bth9 = (Button) dialog.findViewById(R.id.button9);
        Button bth8 = (Button) dialog.findViewById(R.id.button8);
        Button bth7 = (Button) dialog.findViewById(R.id.button7);
        Button bth6 = (Button) dialog.findViewById(R.id.button6);
        Button bth5 = (Button) dialog.findViewById(R.id.button5);
        Button bth4 = (Button) dialog.findViewById(R.id.button4);
        Button bth3 = (Button) dialog.findViewById(R.id.button3);
        Button bth2 = (Button) dialog.findViewById(R.id.button2);
        Button bth1 = (Button) dialog.findViewById(R.id.button1);
        Button bth0 = (Button) dialog.findViewById(R.id.button0);
        Button bthDamage = (Button) dialog.findViewById(R.id.buttonDamage);
        Button bthTreatment = (Button) dialog.findViewById(R.id.buttonTreatment);

        oldHp.setText(hp);
        newHp.setText(now_hp);


        removeBth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.setText(""); }});

        bth9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("9");}});

        bth8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("8"); }});

        bth7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("7"); }});

        bth6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("6"); }});

        bth5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("5"); }});

        bth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("4"); }});

        bth3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("3"); }});

        bth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("2"); }});

        bth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("1"); }});

        bth0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { panel.append("0"); }});

        bthTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newHp.getText().toString().equals("0") || !newHp.getText().toString().equals("")) {
                    newHp.setText(String.valueOf(Integer.parseInt(String.valueOf(newHp.getText())) + Integer.parseInt(String.valueOf(panel.getText()))));
                } else{
                    newHp.setText(panel.getText());
                }
                now_hp = String.valueOf(Integer.parseInt(newHp.getText().toString()));
                panel.setText("");
                etHP_Button.setText(String.valueOf(Integer.parseInt(newHp.getText().toString()) + "/" + etHP.getText()));

            }});
        bthDamage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newHp.getText().toString().equals("0") || !newHp.getText().toString().equals("")) {
                    newHp.setText(String.valueOf(Integer.parseInt(String.valueOf(newHp.getText())) - Integer.parseInt(String.valueOf(panel.getText()))));
                } else{
                    newHp.setText(panel.getText());
                }
                now_hp = String.valueOf(Integer.parseInt(newHp.getText().toString()));
                panel.setText("");
                etHP_Button.setText(String.valueOf(Integer.parseInt(newHp.getText().toString()) + "/" + etHP.getText()));

            }});

        return dialog;
    }


    private Dialog dialogActivity(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_dice_2);

        Button k20 = (Button) dialog.findViewById(R.id.k20);
        Button k12 = (Button) dialog.findViewById(R.id.k12);
        Button k10 = (Button) dialog.findViewById(R.id.k10);
        Button k8 = (Button) dialog.findViewById(R.id.k8);
        Button k6 = (Button) dialog.findViewById(R.id.k6);
        Button k4 = (Button) dialog.findViewById(R.id.k4);

        k20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k20.setText("K20");
                int dice = Dice.randomDies(20);
                Snackbar snackbar = Snackbar.make(v, " " + dice, 10000);
                new Thread(new Thread3(String.valueOf(dice))).start();
                snackbar.show();
            }
        });
        k12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(12), 10000);
                snackbar.show();
            }
        });
        k10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(10), 10000);
                snackbar.show();
            }
        });
        k8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(8), 10000);
                snackbar.show();
            }
        });
        k6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(6), 10000);
                snackbar.show();
            }
        });
        k4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(4), 10000);
                snackbar.show();
            }
        });

        return dialog;
    }

    class Thread1 implements Runnable {
        @Override
        public void run() {
            Socket socket;
            try {
                socket = new Socket(serverIP, serverPort);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SheetActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                    }
                });
//                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

// Сообщения от сервера
//    class Thread2 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    try {
//                        String message = input.readLine();
//                        if (message != null) {
//                            runOnUiThread(() -> tvMessages.append("server: " + message + "\n"));
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//        }
//    }

    class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            if (null == output) return;
            System.out.println(message);
            output.println(message + "\n");
            output.flush();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SheetActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
