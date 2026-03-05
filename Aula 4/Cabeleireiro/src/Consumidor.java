public class Consumidor {

    private String cabelo;
    private String roupa;
    private String altura;

    public String setCabelo(String cabelo) {
        return this.cabelo = cabelo;
    }

    public String setRoupa(String roupa) {
        return this.roupa = roupa;
    }

    public String setAltura(String altura) {
        return this.altura = altura;
    }

    public void cabelo() {
        System.out.println("O cabelo que o consumidor quer é " + cabelo);
    }

    public void roupa() {
        System.out.println("A roupa que o consumidor usa é " + roupa);
    }

    public void altura() {
        System.out.println("A altura do consumidor é " + altura);
    }
}