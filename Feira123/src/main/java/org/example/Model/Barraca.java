package org.example.Model;

public class Barraca {

    private Long Barraca_Id; // O identificador único da barraca no banco de dados.
    private String Nome; //Ex: "Barraca do Zé", "Frutas Frescas".
    private int numero; //(int/String): O número do espaço que ela ocupa na feira.

    public Long Id() {
        return Barraca_Id;
    }

    public Barraca setBarraca_Id(Long barraca_Id) {
        Barraca_Id = barraca_Id;
        return this;
    }

    public String Nome() {
        return Nome;
    }

    public Barraca setNome(String nome) {
        Nome = nome;
        return this;
    }

    public int numero() {
        return numero;
    }

    public Barraca setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public Barraca(Long barraca_Id, String nome, int numero) {
        Barraca_Id = barraca_Id;
        Nome = nome;
        this.numero = numero;
    }
}
