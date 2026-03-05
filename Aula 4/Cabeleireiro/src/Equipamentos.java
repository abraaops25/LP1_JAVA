public class Equipamentos {

    private String volume;
    private String nome;
    private String utilidade;

    public String setVolume(String tamanho) {
        return this.volume = volume;
    }
    public String setNome(String nome) {
        return this.nome = nome;
    }
    public String setUtilidade(String utilidade) {
        return this.utilidade = utilidade;
    }
    public void volume() {
        System.out.println("O volume do equipamento é " + volume);
    }
    public void nome() {
        System.out.println("O nome do equipamento é " + nome);
    }
    public void utilidade() {
        System.out.println("A utilidade do equipamento é " + utilidade);
    }
}