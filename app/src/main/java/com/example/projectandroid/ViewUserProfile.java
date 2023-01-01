package com.example.projectandroid;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUserProfile extends AppCompatActivity {

//    UserDBhelper userdbhelper = new UserDBhelper(ViewUserProfile.this);
    List<UsersModel> userRealList;
    TextView emailtxt,roletxt,nametxt;
    ImageView avatar;
    Button gotoprod;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_userprofile);
        avatar= findViewById(R.id.user_img);
        emailtxt = findViewById(R.id.user_email);
        nametxt = findViewById(R.id.user_name);
        roletxt = findViewById(R.id.user_role);
        gotoprod = findViewById(R.id.gotoprod);

        userRealList = new ArrayList<UsersModel>();
        getUsersOfApi();
        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();

        emailtxt.setText(bundle.getString("useremail"));
        nametxt.setText(bundle.getString("username"));
        roletxt.setText(bundle.getString("userrole"));
        Glide.with(this).load(bundle.getString("userimg"))
                .centerCrop().into(avatar);
        gotoprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent newintent = new Intent(ViewUserProfile.this, ProdViewActivity.class) ;
              startActivity(newintent);
            }
        });
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
}
