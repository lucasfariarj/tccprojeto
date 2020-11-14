package com.desapet.tccv4.ui.criarproduto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.desapet.tccv4.MainActivity;
import com.desapet.tccv4.R;
import com.desapet.tccv4.config.ConfiguracaoFirebase;
import com.desapet.tccv4.helper.Base64Custom;
import com.desapet.tccv4.helper.Preferences;
import com.desapet.tccv4.model.Produto;
import com.desapet.tccv4.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class CriarProdutoFragment extends Fragment {

    private EditText titulo;
    private EditText descricao;
    private ImageView imagemProduto;
    private Button btnUpload;
    private Button btnSalvar;
    private String identificarContato;
    private FirebaseAuth usuarioFirebase;
    private DatabaseReference referenciaFirebase;
    private Usuario usuario;


    public CriarProdutoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.desapet.tccv4.R.layout.fragment_criar_produto, container, false);

        titulo = view.findViewById(R.id.tituloPostagemId);
        descricao = view.findViewById(R.id.descricaoPostagemId);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloPost = titulo.getText().toString();
                String descricaoPost = descricao.getText().toString();

                if (tituloPost.isEmpty()) {
                    Toast.makeText(getActivity(), "O título não podem ficar em branco!", Toast.LENGTH_SHORT).show();
                } else if (descricaoPost.isEmpty()) {
                    Toast.makeText(getActivity(), "A descrição não podem ficar em branco!", Toast.LENGTH_SHORT).show();
                } else {

                    String tituloPostagem = Base64Custom.codificarParaBase64(tituloPost);
                    usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    String identificadorUsuarioLogado = usuarioFirebase.getCurrentUser().getEmail();
                    identificadorUsuarioLogado = Base64Custom.codificarParaBase64(identificadorUsuarioLogado);
                    Produto produtoLista = new Produto();
                    produtoLista.setTitulo(tituloPost);
                    produtoLista.setDescricao(descricaoPost);
                    Map<String, Object> produto = new HashMap<>();
                    produto.put("id", usuarioFirebase.getCurrentUser().getUid());
                    produto.put("titulo", produtoLista.getTitulo());
                    produto.put("descricao", produtoLista.getDescricao());
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("produtos")
                            .add(produto)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error adding document", e);
                                }
                            });

                    //Criar objeto na database


                    referenciaFirebase = ConfiguracaoFirebase.getFirebase().child("produtos").child(identificadorUsuarioLogado).child(tituloPostagem);
                    ;
                    referenciaFirebase.setValue(produto);
                    Toast.makeText(getActivity(), "Produto Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

}