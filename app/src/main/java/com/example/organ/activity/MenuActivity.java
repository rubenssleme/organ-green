package com.example.organ.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.organ.R;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    private Button buttonRegister;
    private Button buttonLogin;
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        buttonRegister = findViewById(R.id.register);
        buttonLogin = findViewById(R.id.login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent telaRegister = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(telaRegister);

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent telaLogin = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(telaLogin);

            }
        });
    }

}