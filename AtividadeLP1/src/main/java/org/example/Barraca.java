package org.example;

public class Barraca {

    private String nomeDono = "Tio Chico";
    private String conteudo = "Bananas";
    private Double tamanho = 2.5;

    public String nomeDono() {
        return nomeDono;
    }

    public Barraca setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
        return this;
    }

    public String conteudo() {
        return conteudo;
    }

    public Barraca setConteudo(String conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public Double tamanho() {
        return tamanho;
    }

    public Barraca setTamanho(Double tamanho) {
        this.tamanho = tamanho;
        return this;
    }
    public String armazenar(){
        return "A barraca da " + nomeDono + "armazena frutas";}

    public String expor(){
        return "A barraca da " + nomeDono + " expõe " + conteudo;
    }

    public String organizar(){
        return "A barraca com tamanho " + tamanho + " organiza as frutas" + conteudo;
    }
}
