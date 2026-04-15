package org.example;

public class Cliente {

    private String nomeCliente = "Carl Johnson";
    private String corCliente = "AfroAmericano";
    private Double altura = 1.85;
    private String sentar;

    public String nomeCliente() {
        return nomeCliente;
    }

    public Cliente corCliente(String corCliente) {
        this.corCliente = corCliente;
        return this;
    }

    public Cliente setAltura(Double altura) {
        this.altura = altura;
        return this;
    }

    public Double altura() {
        return altura;
    }

    public String sentar() {
        return "CJ irá" + sentar + "na Cadeira";
    }

    public String escolherCorte() {
        return "O cliente " + nomeCliente + "escolheu Degradê, para pessoa" + corCliente;
    }

    public String pagar() {
        return "O cliente" + nomeCliente + " vai pagar o corte";

        }
        public String levantar () {
            return nomeCliente + "vai levantar, com seus" + altura + "o negão é grande";
        }
    }
