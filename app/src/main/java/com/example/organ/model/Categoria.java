package com.example.organ.model;

public class Categoria {

    private int imgProdutoCategoria;
    private Long id;
    private String nome;
    private String nomeCategoria;


    public Categoria() {

    }

    public Categoria(String nomeCategoria, int imgProdutoCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.imgProdutoCategoria = imgProdutoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getImgProdutoCategoria() {
        return imgProdutoCategoria;
    }

    public void setImgProdutoCategoria(int imgProdutoCategoria) {
        this.imgProdutoCategoria = imgProdutoCategoria;
    }
}
