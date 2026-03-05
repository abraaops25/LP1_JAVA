//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
   public class Feira {
    public static void main() {
        Fruta ft = new Fruta();
        Pessoa ps = new Pessoa();
        Acessorio ac = new Acessorio();

        ft.setCor("roxa");
        ft.setNome("Uva");
        ft.setEspecie("Vitis vinifera");
        ps.setChapeu("Loiro");
        ps.setCorpo("Gorda");
        ps.setEtnia("Afrodescendente");
        ac.setTipo("Brinco");
        ac.setTamanho("pequeno");
        ac.setUtilidade("Beleza");

        ft.cor();
        ft.nome();
        ft.especie();
        ps.chapeu();
        ps.Corpo();
        ps.Etnia();
        ac.Tipo();
        ac.Tamanho();
        ac.Utilidade();
    }

}


