public class Clientes {

    private String quantidade;
    private String tenis;
    private String genero;

    public String setQuantidade(String quantidade) {
        return this.quantidade = quantidade;
    }

    public String setTenis(String tenis) {
        return this.tenis = tenis;
    }

    public String setGenero(String genero) {
        return this.genero = genero;
    }

    public void quantidade() {
        System.out.println("A quantidade de clientes é " + quantidade);
    }

    public void tenis() {
        System.out.println("O tenis desse cliente é" + tenis);
    }

    public void genero() {
        System.out.println("O genero desse cliente é " + genero);
    }
}