package com.desapet.tccv4.adaptadores;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.desapet.tccv4.R;
import com.desapet.tccv4.model.Produto;
import com.desapet.tccv4.ui.meusprodutos.MeusProdutosFragment;

import java.util.List;

public class TestAdapterProdutos extends BaseAdapter{

    private List<Produto> produtos;
    private MeusProdutosFragment activity;



    public TestAdapterProdutos(MeusProdutosFragment activity, List<Produto> produtos) {
        this.activity = activity;
        this.produtos = produtos;

    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return produtos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        //todo verificar o identificador
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewInflater = this.activity.getLayoutInflater().inflate(R.layout.lista_produtos_adatpter, null);

        TextView textViewNome = viewInflater.findViewById(R.id.textTituloId);
        Produto prod = produtos.get(posicao);


        textViewNome.setText(prod.getDescricao());




        return viewInflater;
    }
}
