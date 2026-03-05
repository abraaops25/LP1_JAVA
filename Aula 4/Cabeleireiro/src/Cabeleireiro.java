//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Cabeleireiro {
    public static void main() {
        Consumidor co = new Consumidor();
        Equipamentos eq = new Equipamentos();
        Assentos at = new Assentos();

        co.setCabelo("Low Fade");
        co.setRoupa("OldMoney");
        co.setAltura("1.95");
        eq.setVolume("15cm");
        eq.setNome("Taiff");
        eq.setUtilidade("Cortes Pequenos");
        at.setMaterial("Couro");
        at.setTamanho("1.50");
        at.setCor("Cinza");

        co.cabelo();
        co.roupa();
        co.altura();
        eq.volume();
        eq.nome();
        eq.utilidade();
        at.material();
        at.tamanho();
        at.cor();
    }

}
