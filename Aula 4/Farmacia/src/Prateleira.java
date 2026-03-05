public class Prateleira {

    private String comprimento;
    private String cor;
    private String altura;

    public String setComprimento(String comprimento) {
        return this.comprimento = comprimento;
    }

    public String setCor(String cor) {
        return this.cor = cor;
    }

    public String setAltura(String altura) {
        return this.altura = altura;
    }

    public void comprimento() {
        System.out.println("O comprimento da prateleira é " + comprimento);
    }

    public void cor() {
        System.out.println("A cor da prateleira é" + cor);
    }

    public void altura() {
        System.out.println("A altura da prateleira é " + altura);
    }
}