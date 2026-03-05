//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Farmacia {
  public static void main() {
    Clientes cl = new Clientes();
    Remedios rm = new Remedios();
    Prateleira pr = new Prateleira();

    cl.setQuantidade("7");
    cl.setTenis("Nike");
    cl.setGenero("Homens");
    rm.setNome("Dexametasona");
    rm.setTipo("corticosteroides");
    rm.setValidade("04/06/2028");
    pr.setComprimento("8m");
    pr.setCor("Cinza");
    pr.setAltura("Beleza");

    cl.quantidade();
    cl.tenis();
    cl.genero();
    rm.nome();
    rm.tipo();
    rm.validade();
    pr.comprimento();
    pr.cor();
    pr.altura();
  }

}
