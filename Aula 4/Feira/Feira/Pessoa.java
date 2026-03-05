public class Pessoa {

private String Chapeu;
private String Etnia;
private String Corpo;

public void setChapeu(String chapeu){
    this.Chapeu = chapeu;
}
public void setEtnia(String etnia) {
    this.Etnia = etnia;
}
public void setCorpo(String corpo) {
    this.Corpo = corpo;
}
    public void chapeu() {
        System.out.println("O Chapeu da pessoa é " + Chapeu);
    }

    public void Etnia() {
        System.out.println("A cor da fruta é " + Etnia);
    }

    public void Corpo() {
        System.out.println("O nome da fruta é " + Corpo);
    }
}