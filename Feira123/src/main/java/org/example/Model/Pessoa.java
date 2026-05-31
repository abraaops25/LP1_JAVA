package org.example.Model;

public class Pessoa {

private Long Pessoa_Id;
private String Nome_Pessoa;// Nome completo da pessoa.
private Enum tipoPessoa;//Para diferenciar se ela é "FEIRANTE", "FUNCIONARIO" ou "CLIENTE".

    public String Nome_Pessoa() {
        return Nome_Pessoa;
    }

    public Pessoa setNome_Pessoa(String nome_Pessoa) {
        Nome_Pessoa = nome_Pessoa;
        return this;
    }

    public Enum tipoPessoa() {
        return tipoPessoa;
    }

    public Pessoa setTipoPessoa(Enum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        return this;
    }

    public Long Pessoa_Id() {
        return Pessoa_Id;
    }

    public Pessoa setPessoa_Id(Long pessoa_Id) {
        Pessoa_Id = pessoa_Id;
        return this;
    }

    public Pessoa(Long pessoa_Id, Enum tipoPessoa, String nome_Pessoa) {
        Pessoa_Id = pessoa_Id;
        this.tipoPessoa = tipoPessoa;
        Nome_Pessoa = nome_Pessoa;
    }
}