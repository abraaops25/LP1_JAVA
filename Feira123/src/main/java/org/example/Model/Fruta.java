package org.example.Model;

public class Fruta {

    private Long Fruta_Id;
    private String Nome_fruta; //Ex: "Banana Prata", "Maçã Gala".
    private Double precoPorUnidade; //(double): O valor de venda.
    private String epoca; //Ex: "Inverno", "Ano todo" (ajuda a saber se está na época da fruta).

    public Long Fruta_Id() {
        return Fruta_Id;
    }

    public Fruta setFruta_Id(Long fruta_Id) {
        Fruta_Id = fruta_Id;
        return this;
    }

    public String Nome_fruta() {
        return Nome_fruta;
    }

    public Fruta setNome_fruta(String nome_fruta) {
        Nome_fruta = nome_fruta;
        return this;
    }

    public Double precoPorUnidade() {
        return precoPorUnidade;
    }

    public Fruta setPrecoPorUnidade(Double precoPorUnidade) {
        this.precoPorUnidade = precoPorUnidade;
        return this;
    }

    public String epoca() {
        return epoca;
    }

    public Fruta setEpoca(String epoca) {
        this.epoca = epoca;
        return this;
    }

    public Fruta(Long fruta_Id, String epoca, String nome_fruta, Double precoPorUnidade) {
        Fruta_Id = fruta_Id;
        this.epoca = epoca;
        Nome_fruta = nome_fruta;
        this.precoPorUnidade = precoPorUnidade;
    }
}