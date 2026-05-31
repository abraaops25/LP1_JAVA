package org.example.Model;

public class Feira {
    private Long Feira_Id;//
    private String nome;//Ex: "Feira de Domingo de Santana", "Feira Orgânica do Centro".
    private Enum diaDaSemana;//Ex: "DOMINGO", "SÁBADO".
    private String endereco; // Onde a feira acontece.

    public Long Feira_Id() {
        return Feira_Id;
    }

    public Feira setFeira_Id(Long feira_Id) {
        Feira_Id = feira_Id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public Feira setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Enum diaDaSemana() {
        return diaDaSemana;
    }

    public Feira setDiaDaSemana(Enum diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
        return this;
    }

    public String endereco() {
        return endereco;
    }

    public Feira setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Feira(Long feira_Id, String nome, Enum diaDaSemana, String endereco) {
        Feira_Id = feira_Id;
        this.nome = nome;
        this.diaDaSemana = diaDaSemana;
        this.endereco = endereco;
    }
}
