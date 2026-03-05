public class Fruta {

    private String especie;
    private String cor;
    private String nome;

    public String setEspecie(String especie) {
        return this.especie = especie;
    }

    public String setCor(String cor) {
        return this.cor = cor;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public void especie() {
        System.out.println("Especie da fruta é " + especie);
    }

    public void cor() {
        System.out.println("A cor da fruta é " + cor);
    }

    public void nome() {
        System.out.println("O nome da fruta é " + nome);
    }
}