public class Assentos {

    private String material;
    private String tamanho;
    private String cor;

    public String setMaterial(String material) {
        return this.material = material;
    }

    public String setTamanho(String tamanho) {
        return this.tamanho = tamanho;
    }

    public String setCor(String Cor) {
        return this.cor = cor;
    }

    public void material() {
        System.out.println("O material do assento é " + material);
    }

    public void tamanho() {
        System.out.println("O tamanho do assento é " + tamanho);
    }

    public void cor() {
        System.out.println("A cor do assento é " + cor);
    }
}