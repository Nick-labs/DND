package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dnd.NotesActivity;
import com.google.android.material.snackbar.Snackbar;

public class MasterActivity extends AppCompatActivity {
    Button dnd_su, dice, dnd_club, notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        dnd_su = findViewById(R.id.dnd_su_bth);
        dice = findViewById(R.id.dice);
        dnd_club = findViewById(R.id.dnd_club_bth);
        notes = findViewById(R.id.notes);

        dnd_su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, DndSu.class);
                startActivity(intent);
            }
        });
        dnd_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MasterActivity.this, DndСlub.class);
                startActivity(intent);
            }
        });

        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActivity(MasterActivity.this).show();
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MasterActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }

    private Dialog dialogActivity (Context context){
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
                Snackbar snackbar = Snackbar.make(v, " " + Dice.randomDies(20), 10000);
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
}