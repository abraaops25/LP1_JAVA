package org.example;

public class Pessoa {
    private String nome = "Cláudia";
    private String corRoupa = "Azul";
    private String funcao = "Feirante";

    public String nome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String corRoupa() {
        return corRoupa;
    }

    public Pessoa setCorRoupa(String corRoupa) {
        this.corRoupa = corRoupa;
        return this;
    }

    public String funcao() {
        return funcao;
    }

    public Pessoa setFuncao(String funcao) {
        this.funcao = funcao;
        return this;
    }
    public String vender(){
        return "A " + funcao + " vendeu suas frutas";
    }

    public String falar(){
        return nome + " falou com a feirante de roupa " + corRoupa;
    }

    public String comprar(){
        return nome + " comprou um abacaxi";
    }
}
