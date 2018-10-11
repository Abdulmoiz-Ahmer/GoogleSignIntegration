package com.example.aceahmer.task_ten;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    com.google.android.gms.common.SignInButton SignIn;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount googleSignInAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn = findViewById(R.id.google_btn);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
         googleSignInAccount = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
        SignIn.setSize(SignInButton.SIZE_STANDARD);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Task<GoogleSignInAccount>task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else{
            Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount googleSignInAccount1=task.getResult(ApiException.class);
            DataModel dm=new DataModel();

            dm.setName(googleSignInAccount1.getDisplayName());
            dm.setEmail(googleSignInAccount1.getEmail());
            dm.setImageUrl(googleSignInAccount1.getPhotoUrl());
            dm.setName2(googleSignInAccount1.getGivenName());
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("obj",dm);
            startActivity(intent);
        }
        catch (ApiException exp){

        }



    }


}
