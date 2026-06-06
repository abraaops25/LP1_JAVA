package org.example.Model;

public class Servico {
    private int id;
    private String nomeServico;
    private double preco;
    private int tempoEstimado;

    public Servico(int id, String nomeServico, int tempoEstimado, double preco) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.tempoEstimado = tempoEstimado;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(int tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
