package com.example.emailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
   private EditText edtLoginUserName, edtLoginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUserName=findViewById(R.id.edtLoginUserName);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);
    }

    public void loginIsPressed(View btnview) {
        ParseUser.logInInBackground(edtLoginUserName.getText().toString(),
                edtLoginPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user!=null){
                       if(user.getBoolean("emailVerified")){
                         alertDisplayer("Login Successfull","Welcome, "+
                                 edtLoginUserName.getText().toString()+"!",false);
                       }else{
                           ParseUser.logOut();
                           alertDisplayer("Login Fail","Please Verify your Email first",true);
                       }

                }else{
                    ParseUser.logOut();
                    alertDisplayer("Login Fail",e.getMessage()+"Please Re-try",true);
                }


            }
        });
    }

    private void alertDisplayer(String title, String message, final boolean error){
        AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error){
                            Intent intent =new Intent(LoginActivity.this,
                                    LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }
                });
        AlertDialog ok= builder.create();
        ok.show();
    }
}