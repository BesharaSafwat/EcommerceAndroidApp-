package com.example.projectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SignupActivity extends AppCompatActivity {
    Button btnSignUp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        btnSignUp = findViewById(R.id.btnSignUp);
        MaterialAlertDialogBuilder dialogOflogin = mydialog("Positive Message","Cancel Message");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOflogin.show();
            }
        });
    }
    public MaterialAlertDialogBuilder mydialog(String posBtnMsg, String negBtnMsg) {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
        dialog.setTitle("Successful Login")
                .setMessage("Select Button")
                .setCancelable(false)
                .setPositiveButton("Go to Products", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SignupActivity.this, posBtnMsg, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent in = new Intent(SignupActivity.this,CategoryActivity.class);
                        startActivity(in);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SignupActivity.this, negBtnMsg, Toast.LENGTH_SHORT).show();
                    }
                });
        return dialog;
    }
}
