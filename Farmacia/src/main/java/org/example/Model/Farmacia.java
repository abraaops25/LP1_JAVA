package org.example.Model;

public class Farmacia {

    private Long Farmacia_Id;
    private String nome;        // Ex: "Farmácia Central", "Drogaria São João"
    private String cnpj;        // CNPJ da farmácia
    private String endereco;    // Onde a farmácia está localizada

    public Long Farmacia_Id() {
        return Farmacia_Id;
    }

    public Farmacia setFarmacia_Id(Long farmacia_Id) {
        Farmacia_Id = farmacia_Id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public Farmacia setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String cnpj() {
        return cnpj;
    }

    public Farmacia setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String endereco() {
        return endereco;
    }

    public Farmacia setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Farmacia(Long farmacia_Id, String nome, String cnpj, String endereco) {
        Farmacia_Id = farmacia_Id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
}
