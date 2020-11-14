package com.desapet.tccv4.model;

import android.widget.ImageView;

public class Produto {

    private String identificadorProduto;
    private String titulo;
    private String descricao;
    private ImageView imagemProduto;

    public Produto() {

    }

    public String getIdentificadorProduto() {
        return identificadorProduto;
    }


    public void setIdentificadorProduto(String identificadorProduto) {
        this.identificadorProduto = identificadorProduto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
