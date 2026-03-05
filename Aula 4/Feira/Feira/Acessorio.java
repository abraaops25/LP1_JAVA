public class Acessorio {

    private String Tamanho;
    private String Tipo;
    private String Utilidade;

    public String setTamanho(String tamanho) {
        return this.Tamanho = tamanho;
    }

    public String setTipo(String tipo) {
        return this.Tipo = tipo;
    }

    public String setUtilidade(String utilidade) {
        return this.Utilidade = utilidade;
    }
    public void Tamanho() {
        System.out.println("O tamanho do acessorio é " + Tamanho);
    }

    public void Tipo() {
        System.out.println("O tipo do acessorio é " + Tipo);
    }

    public void Utilidade() {
        System.out.println("A utilidade do acessorio é " + Utilidade);
    }
}
