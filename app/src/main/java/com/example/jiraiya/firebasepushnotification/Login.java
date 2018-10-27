package com.example.jiraiya.firebasepushnotification;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button login;
    private FirebaseAuth mAuth;
    TextView tv1,tv2;
    String email;
    String password;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.button);
        tv1 = findViewById(R.id.email);
        tv2 = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = tv1.getText().toString();
                password = tv2.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                    Toast.makeText(Login.this, "Field can not be Empty", Toast.LENGTH_SHORT).show();
                else
                    signIn(email,password);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }

    }

    void signIn(final String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            currentUser = mAuth.getCurrentUser();
                            if( currentUser != null) {

                                currentUser.getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                                    @Override
                                    public void onSuccess(GetTokenResult getTokenResult) {

                                        Map<String, String> users = new HashMap<>();
                                        users.put("Token",getTokenResult.getToken());
                                        Log.d("Token",getTokenResult.getToken());

                                        myRef.child("Users").child(currentUser.getUid()).setValue(users);
                                        Toast.makeText(Login.this, "SignUp Successful", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                });

                            }

                        } else {

                            Toast.makeText(Login.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}



