package com.desapet.tccv4.ui.meusprodutos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.desapet.tccv4.CadastroActivity;
import com.desapet.tccv4.R;
//import com.desapet.tccv4.adaptadores.ProdutosAdapter;
import com.desapet.tccv4.adaptadores.TestAdapterProdutos;
import com.desapet.tccv4.config.ConfiguracaoFirebase;
import com.desapet.tccv4.model.Produto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MeusProdutosFragment extends Fragment {

    private ListView listViewProdutos;
    private String[] produtos = {
            "Coleira de cachorro", "Casa de plástico animais pequeno porte", "Ração Pedigree", "corrente de cachorro"
    };
    private ArrayList<Produto> listProdutos = new ArrayList();
    private Button botaoCad;
    private DatabaseReference referenciaFirebase;
    private ListView listaRecycleView;
    private FirebaseFirestore referenciaFireStore;
    //private RecyclerView.Adapter<ProdutosAdapter.MeuViewHolder> recycleView;





    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meus_produtos, container, false);
        listaRecycleView = view.findViewById(R.id.recycleViewProdutosId);
        //Converter XML em objeto
        Log.println(Log.ASSERT, "aass", "olaaaaaa");
        //String teste = ConfiguracaoFirebase.getFirebaseAutenticacao().getCurrentUser().getEmail();
        //Log.println(Log.ASSERT,"aass",referenciaFirebase.child("produtos").orderByChild(teste).getRef().addChildEventListener(result => for()));
        //Alterar usuário .child
       // Query myTopPostsQuery = referenciaFirebase.child("produtos");
        //listProdutos = myTopPostsQuery.addChildEventListener();
        //Log.println(Log.ASSERT, "aass", myTopPostsQuery.toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("produtos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        Produto produto = new Produto();
                        produto.setTitulo(document.get("titulo").toString());
                        produto.setDescricao(document.getData().get("descricao").toString());
                        listProdutos.add(produto);
                        Log.println(Log.ASSERT, "Produto: ", String.valueOf(listProdutos));
                    }
                } else {
                    Log.w("TAG", "Deu merda aí 06", task.getException());
                }
                populaLista(listProdutos);
            }
        });


        return view;
    }

    private void populaLista(ArrayList<Produto> listProdutos) {
        final TestAdapterProdutos adapter = new TestAdapterProdutos(this, listProdutos);
        listaRecycleView.setAdapter(adapter);
        this.listProdutos = listProdutos;
    }
}

