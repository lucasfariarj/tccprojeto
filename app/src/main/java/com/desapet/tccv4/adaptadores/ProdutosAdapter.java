package com.desapet.tccv4.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.desapet.tccv4.R;

class ProdutosAdaptador extends RecyclerView.Adapter<ProdutosAdaptador.MeuViewHolder> {

    public static Object MeuViewHolder;

    public ProdutosAdaptador(View view) {
    }

    @NonNull
    @Override
    //Criar as visualizações
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemProduto = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_produtos_adatpter,parent,false);
        return new MeuViewHolder(itemProduto);
    }

    @Override
    //Exibir cada um dos elementos da recycle
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.titulo.setText("Meu Novo Produto");
        holder.comentario.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
        holder.imagem.setImageResource(R.drawable.ic_baseline_pets_24);
        holder.status.setText("Disponível");
    }

    @Override
    //Qtd de itens a serem exibidos
    public int getItemCount() {
        return 10;
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView comentario;
        private ImageView imagem;
        private TextView status;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTituloId);
            comentario = itemView.findViewById(R.id.textDescricaoId);
            status = itemView.findViewById(R.id.textStatusId);
            imagem = itemView.findViewById(R.id.imageProdutoId);
        }

    }




}
