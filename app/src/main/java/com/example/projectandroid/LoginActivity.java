package com.example.projectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText emailEd,pswrdEd;
    UserDBhelper userdbhelper;
    List<UsersModel> userRealList;
    boolean loginSuccess =false;
    Intent in2 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        in2 = new Intent(LoginActivity.this,ViewUserProfile.class);
        btnlogin = findViewById(R.id.btntologin);
        emailEd =findViewById(R.id.emailEd);
        pswrdEd =findViewById(R.id.pswrdEd);
        userdbhelper = new UserDBhelper(LoginActivity.this);
        userRealList = new ArrayList<UsersModel>();
        getUsersOfApi();
        MaterialAlertDialogBuilder dialogOfSuccesslogin = mydialog("Go to Products ","View Profile",
                "","Login Successfully","select button");
        MaterialAlertDialogBuilder dialogOfFaillogin = mydialog("","Retry Login",
                "Skip to Products",
                "Login Failed","select button");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLoginEmail() && checkLoginPswrd())
                    dialogOfSuccesslogin.show();
                else
                    dialogOfFaillogin.show();
            }
        });
    }
    public boolean checkLoginEmail(){
        String enteredEmail = emailEd.getText().toString();
        boolean founduser=false;
        if(enteredEmail.contains("@")){
            for (UsersModel user : userRealList){
               if(enteredEmail.equals(user.getEmail())){
                   in2.putExtra("userimg",user.getAvatar());
                   in2.putExtra("useremail",user.getEmail());
                   in2.putExtra("username",user.getName());
                   in2.putExtra("userrole",user.getRole());
                   founduser = true;
                   return founduser;
               }
            }
            if (!founduser){
                return founduser;
            }
        }
        return founduser;
    }
    public boolean checkLoginPswrd(){
        String enteredpswrd = pswrdEd.getText().toString();
        String enteredEmail = emailEd.getText().toString();
        boolean rightpassword =false;
        if(true){
            for (UsersModel user : userRealList){
                if(enteredEmail.equals(user.getEmail()))
                     if(enteredpswrd.equals(user.getPassword())){
                        rightpassword = true;
                         loginSuccess = true;
                         return rightpassword;
                }
            }
            if (!rightpassword){
                return rightpassword;
            }
        }
        return rightpassword;
    }

    public void getUsersOfApi() {
        Call<List<UsersModel>> call = MyRetrofitclient.getInstance().getApi().getUsers();
        call.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<UsersModel> userlist = response.body();
                    for (UsersModel user : userlist) {
                        userRealList.add(user);
//                        userdbhelper.addUser(user.getEmail(),user.getPassword(),
//                                user.getName(),user.getRole(),user.getAvatar());
//                        txt.setText(txt.getText().toString() + "Name: " + product.getProd_title() + "\n");
                    }
                    Log.d("lifecycledbapi", "elseBody");
                }
            }
            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {
                Log.d("lifecycle", "" + t.getMessage());
            }
        });
    }
    public MaterialAlertDialogBuilder mydialog(String posBtntxt, String negBtntxt,String Btntxt,String msgtitle , String msg ) {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
        dialog.setTitle(msgtitle)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(posBtntxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(LoginActivity.this, posBtnMsg, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent in = new Intent(LoginActivity.this,CategoryActivity.class);
                        startActivity(in);
                    }
                })
                .setNegativeButton(negBtntxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (loginSuccess){

                            startActivity(in2);
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Cancelled",
                                    Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton(Btntxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent in = new Intent(LoginActivity.this,CategoryActivity.class);
                        startActivity(in);
                    }
                });
        return dialog;
    }
}

