package com.example.allisonliu.connect4;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.allisonliu.connect4.R.id.btn_Login;
import static com.example.allisonliu.connect4.R.id.txt_UserName;
import static com.example.allisonliu.connect4.R.id.txt_UserPW;



public class  MainActivity extends Activity implements View.OnClickListener{

    EditText txt_UserName, txt_UserPW;
    Button btn_Login;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Login = (Button)findViewById(R.id.btn_Login);
        txt_UserName = (EditText)findViewById(R.id.txt_UserName);
       txt_UserPW = (EditText)findViewById(R.id.txt_UserPW);
        btn_Login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getBaseContext(), GamemainActivity.class);
        if (v.getId() == R.id.btn_Login) {
            String uname = txt_UserName.getText().toString();
            String upassword = txt_UserPW.getText().toString();
            System.out.println( "@@@@@@@@@@@@@@@\n" + "The UID is: " + uname + "\n" +
                    "The Password is: " + upassword + "\n" + "@@@@@@@@@@@@@@@" );

        }
        startActivity(intent);
    }

}
