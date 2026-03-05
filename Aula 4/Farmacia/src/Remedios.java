public class Remedios {

    private String nome;
    private String tipo;
    private String validade;

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String setTipo(String tipo) {
        return this.tipo = tipo;
    }

    public String setValidade(String validade) {
        return this.validade = validade;
    }

    public void nome() {
        System.out.println("O nome do remedio é " + nome);
    }

    public void tipo() {
        System.out.println("O tipo do remedio é" + tipo);
    }

    public void validade() {
        System.out.println("A validade do remedio é " + validade);
    }
}