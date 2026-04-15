package org.example;

public class Maquininhas {

    private String nomeMaquininha = "Carl Johnson";
    private String corMaquininha = "AfroAmericano";
    private String marca = "Taiff";
    private String tipo = "Corte Geral";

    public String nomeMaquininha() {
        return nomeMaquininha;
    }

    public Maquininhas corMaquininha(String corMaquininha) {
        this.corMaquininha = corMaquininha;
        return this;
    }

    public Maquininhas setMarca(String Marca) {
        this.marca = Marca;
        return this;
    }
    public Maquininhas setTipo(String Tipo) {
        this.tipo = tipo;
        return this;
    }
    public String Marca() {
        return marca;
    }

    public String Tipo() {
        return "A Maquininha do tipo " + tipo + "esta com ótimo corte";
    }

    public String EscolherCorte() {
        return "O cliente " + nomeCliente + "escolheu Degradê, para pessoa" + corCliente;
    }

    public String Pagar() {
        return "O cliente" + nomeCliente + " vai pagar o corte";

    }
    public String Levantar () {
        return nomeCliente + "vai levantar, com seus" + altura + "o negão é grande";
    }
}
