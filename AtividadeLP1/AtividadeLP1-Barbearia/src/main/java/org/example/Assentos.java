package org.example;

public class Assentos {

    private String nomeAssento = "Cadeira Giratoria";
    private String tipo = "Oblivion";
    private Double tamanho = 1.40;


    public Assentos setNomeAssento(String nomeAssento) {
        this.nomeAssento = nomeAssento;
        return this;
    }


    public String getTipo() {
        return tipo;
    }

    public Assentos getTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Double tamanho() {
        return tamanho;
    }

    public Assentos setTamanho(Double tamanho) {
        this.tamanho = tamanho;
        return this;
    }
    public  String abaixar(){
        return "A cadeira" + nomeAssento + "Desceu";}

    public String rodar(){
        return "A cadeira esta" + nomeAssento + " girando ";
    }

    public String deitar(){
        return "A cadeira de" + tamanho + "esta levantando";
    }
}
