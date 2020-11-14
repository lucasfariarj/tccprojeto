package com.desapet.tccv4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desapet.tccv4.config.ConfiguracaoFirebase;
import com.desapet.tccv4.helper.Base64Custom;
import com.desapet.tccv4.helper.Preferences;
import com.desapet.tccv4.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class CadastroActivity extends AppCompatActivity {

    //private DatabaseReference referenciaFirebase;
    private EditText nome;
    private EditText senha;
    private EditText email;
    private EditText celular;
    private EditText endereco;
    private Boolean termos;
    private Button btnCadastrar;
    private Button btnCancelar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.nomeId);
        senha = findViewById(R.id.senhaId);
        email = findViewById(R.id.emailId);
        celular = findViewById(R.id.celularId);
        endereco = findViewById(R.id.enderecoId);
        btnCadastrar = findViewById(R.id.cadastrarId);

        /*fff = new ConfiguracaoFirebase().getFirebase();
        fff.child("PTS").setValue("800");*/

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setCelular(celular.getText().toString());
                cadastrarUsuario();
            }
        });

    }

    public void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this,"Cadastrado com Sucesso!",Toast.LENGTH_LONG).show();
                    //FirebaseUser usuarioFirebase = task.getResult().getUser();
                    String identificarUsuario = Base64Custom.codificarParaBase64(usuario.getEmail());
                    usuario.setId(identificarUsuario);
                    usuario.salvar();

                    Preferences prefencias = new Preferences(CadastroActivity.this);
                    prefencias.salvarDados(identificarUsuario);

                    abrirLoginUsuario();
                }else{
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte.";
                        }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "Email inválido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Usuário já registrado no aplicativo";
                    }catch (Exception e){
                        erroExcecao = "Erro ao cadastrar usuário";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this,"Erro: " + erroExcecao,Toast.LENGTH_LONG).show();
                }
            }
        });

        }

    public void abrirLoginUsuario() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        autenticacao.signOut();
        finish();
    }


}