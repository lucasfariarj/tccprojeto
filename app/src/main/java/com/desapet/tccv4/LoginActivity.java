package com.desapet.tccv4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desapet.tccv4.config.ConfiguracaoFirebase;
import com.desapet.tccv4.helper.Base64Custom;
import com.desapet.tccv4.helper.Preferences;
import com.desapet.tccv4.model.Usuario;
import com.desapet.tccv4.ui.meusprodutos.MeusProdutosFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button btnLogar;
    private Button btnCadastro;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailId);
        senha = findViewById(R.id.senhaId);
        btnLogar = findViewById(R.id.btnLogarId);
        btnCadastro = findViewById(R.id.btnCadastroId);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Preferences prefencias = new Preferences(LoginActivity.this);
                    String identificadorUsuarioLogado = Base64Custom.codificarParaBase64(usuario.getEmail());
                    prefencias.salvarDados(identificadorUsuarioLogado);

                    Toast.makeText(LoginActivity.this,"Login efetuado com sucesso!",Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }else{
                    Toast.makeText(LoginActivity.this,"Erro ao logar!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirCadastro(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
    }
}