package com.example.emailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtPassword);


    }

    public void signUpIsPressed(View btnView) {

               try{
           ParseUser user= new ParseUser();
           user.setUsername(edtName.getText().toString());
           user.setPassword(edtPassword.getText().toString());
           user.setEmail(edtEmail.getText().toString());
           user.signUpInBackground(new SignUpCallback() {
               @Override
               public void done(ParseException e) {
                   if(e==null){
                      ParseUser.logOut();
                      alertDisplayer("Account Created Successfully!",
                              "Please Verify your email before login",false);
                   }
                   else{
                       ParseUser.logOut();
                       alertDisplayer("Error Account Creation Failed",
                               "Account could not be created "+":"+e.getMessage(),true);
                   }

               }
           });

       }catch (Exception e){
                e.printStackTrace();
               }

    }

    private void alertDisplayer(String title, String message, final boolean error){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error){
                            Intent intent =new Intent(MainActivity.this,
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





