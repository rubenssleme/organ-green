package com.example.organ.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.organ.R;
import com.example.organ.config.ConfigFirebase;
import com.example.organ.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText varEmail, varSenha;
    private String email, senha;
    private FirebaseAuth auth;
    private Button btnEntrar;
    private TextView btnRecupSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        varEmail = findViewById(R.id.txtEmailLog);
        varSenha = findViewById(R.id.txtSenhaLog);
        email = varEmail.getText().toString();
        senha = varSenha.getText().toString();
        auth = ConfigFirebase.getFirebaseAuth();

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationLogUser(email, senha);
            }
        });

        btnRecupSenha = findViewById(R.id.txtForgot);
        btnRecupSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirRecupSenha();
            }
        });
    }
    public void abrirRecupSenha(){
        Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(i);
    }


    public void validationLogUser(String email, String senha) {
        String txtEmail = varEmail.getText().toString();
        String txtSenha = varSenha.getText().toString();


        if (txtEmail.isEmpty() && txtSenha.isEmpty()) {
            Toast.makeText(LoginActivity.this,
                    "Preencha TODOS os campos!",
                    Toast.LENGTH_LONG).show();
        }
        else{
            if (!txtEmail.isEmpty()) {
                if (!txtSenha.isEmpty()) {
                    Usuario usuario = new Usuario();
                    usuario.setEmail(txtEmail);
                    usuario.setSenha(txtSenha);

                    logUser(usuario);

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Senha não preenchida!",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(LoginActivity.this,
                        "E-mail não preenchido!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void logUser(Usuario usuario){
        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,
                            "Sucesso ao logar!",
                            Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                }
                else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao ="Usuário não está cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha não correspondem a usuário cadastrado";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta já foi cadastrada";
                    }catch (Exception e){
                        excecao = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this,
                            excecao,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}