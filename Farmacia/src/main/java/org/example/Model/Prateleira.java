package org.example.Model;

public class Prateleira {

    private Long Prateleira_Id;
    private String codigo;      // Ex: "A1", "B3" — identificação da prateleira
    private String corredor;    // Ex: "Corredor 1", "Corredor Analgésicos"
    private int capacidade;     // Quantidade máxima de itens que comporta

    public Long Prateleira_Id() {
        return Prateleira_Id;
    }

    public Prateleira setPrateleira_Id(Long prateleira_Id) {
        Prateleira_Id = prateleira_Id;
        return this;
    }

    public String codigo() {
        return codigo;
    }

    public Prateleira setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String corredor() {
        return corredor;
    }

    public Prateleira setCorredor(String corredor) {
        this.corredor = corredor;
        return this;
    }

    public int capacidade() {
        return capacidade;
    }

    public Prateleira setCapacidade(int capacidade) {
        this.capacidade = capacidade;
        return this;
    }

    public Prateleira(Long prateleira_Id, String codigo, String corredor, int capacidade) {
        Prateleira_Id = prateleira_Id;
        this.codigo = codigo;
        this.corredor = corredor;
        this.capacidade = capacidade;
    }
}
