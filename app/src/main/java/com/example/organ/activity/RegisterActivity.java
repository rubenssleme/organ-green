package com.example.organ.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.organ.R;
import com.example.organ.config.ConfigFirebase;
import com.example.organ.helper.Base64Custom;
import com.example.organ.helper.UsuarioFirebase;
import com.example.organ.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegistrar;
    private TextInputEditText varNome, varEmail, varSenha;
    private FirebaseAuth auth;
    private String email, senha;
    private RadioButton rbtnTermo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        varNome = findViewById(R.id.txtNomeUser);
        varEmail = findViewById(R.id.txtEmailRegister);
        varSenha = findViewById(R.id.txtSenhaRegister);
        email = varEmail.getText().toString();
        senha = varSenha.getText().toString();

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationUser(email, senha);
            }
        });
    }
    public void validationUser(String email, String senha){
        String txtNome = varNome.getText().toString();
        String txtEmail = varEmail.getText().toString();
        String txtSenha = varSenha.getText().toString();
        rbtnTermo = findViewById(R.id.rbtnTermo);

        if(!txtNome.isEmpty() && !txtEmail.isEmpty() && !txtSenha.isEmpty())
            if(!txtNome.isEmpty()) {
                if (!txtEmail.isEmpty()) {
                    if (!txtSenha.isEmpty()) {
                        if(rbtnTermo.isChecked()) {

                            Usuario usuario = new Usuario();
                            usuario.setNome(txtNome);
                            usuario.setEmail(txtEmail);
                            usuario.setSenha(txtSenha);

                            registerUser(usuario);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,
                                    "Aceite os termos e condições",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this,
                                "Senha não preenchida!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "E-mail não preenchido!",
                            Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(RegisterActivity.this,
                        "Nome não preenchido!",
                        Toast.LENGTH_LONG).show();
            }
        else{
            Toast.makeText(RegisterActivity.this,
                    "Preencha TODOS os campos!",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void registerUser(final Usuario usuario){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,
                            "Sucesso ao cadastrar usuário",
                            Toast.LENGTH_LONG).show();
                    UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());
                    finish();

                    try{
                        String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setIdUsuario(idUsuario);
                        usuario.salvar();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao ="Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta já foi cadastrada";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegisterActivity.this,
                            excecao,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}