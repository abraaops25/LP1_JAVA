package org.example.Model;

public class Remedios {

    private Long Remedio_Id;
    private String nome;            // Ex: "Dexametasona", "Paracetamol"
    private String tipo;            // Ex: "corticosteroide", "analgésico"
    private String validade;        // Ex: "04/06/2028"
    private Double preco;           // Preço de venda

    public Long Remedio_Id() {
        return Remedio_Id;
    }

    public Remedios setRemedio_Id(Long remedio_Id) {
        Remedio_Id = remedio_Id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public Remedios setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String tipo() {
        return tipo;
    }

    public Remedios setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public String validade() {
        return validade;
    }

    public Remedios setValidade(String validade) {
        this.validade = validade;
        return this;
    }

    public Double preco() {
        return preco;
    }

    public Remedios setPreco(Double preco) {
        this.preco = preco;
        return this;
    }

    public Remedios(Long remedio_Id, String nome, String tipo, String validade, Double preco) {
        Remedio_Id = remedio_Id;
        this.nome = nome;
        this.tipo = tipo;
        this.validade = validade;
        this.preco = preco;
    }
}
