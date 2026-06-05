package org.example.Model;

public class Clientes {

    private Long Cliente_Id;
    private String nome;            // Nome completo do cliente
    private String cpf;             // CPF do cliente
    private String tipoPessoa;      // "COMUM", "CONVENIADO", "IDOSO"

    public Long Cliente_Id() {
        return Cliente_Id;
    }

    public Clientes setCliente_Id(Long cliente_Id) {
        Cliente_Id = cliente_Id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public Clientes setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String cpf() {
        return cpf;
    }

    public Clientes setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String tipoPessoa() {
        return tipoPessoa;
    }

    public Clientes setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        return this;
    }

    public Clientes(Long cliente_Id, String nome, String cpf, String tipoPessoa) {
        Cliente_Id = cliente_Id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoPessoa = tipoPessoa;
    }
}
