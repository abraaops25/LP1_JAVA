package org.example;

public class Fruta {
    private String nome = "Maçã";
    private String cor = "Amarela";
    private Double preco = 2.99;

    public String nome() {
        return nome;}

    public Fruta setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String cor() {
        return cor;
    }

    public Fruta setCor(String cor) {
        this.cor = cor;
        return this;
    }

    public Double preco() {
        return preco;
    }

    public Fruta setPreco(Double preco) {
        this.preco = preco;
        return this;
    }
    public String vender(){
        return "A fruta " + nome + " foi vendida por " + preco;
    }

    public String estragar(){
        return "A " + nome + " estragou ";
    }

    public String comer(){
        return "Alguém comeu a fruta de cor " + cor;
    }
}
