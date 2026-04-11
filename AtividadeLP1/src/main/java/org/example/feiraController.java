package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class feiraController {

    @FXML
    private void BotaoBarraca() {
        try {
            App.setRoot("Barraca");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoFruta() {
        try {
            App.setRoot("Fruta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoPessoas() {
        try {
            App.setRoot("Pessoa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoVoltar() {
        try {
            App.setRoot("Feira");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Pessoa pessoa = new Pessoa();

    @FXML
    private Barraca barraca = new Barraca();

    @FXML
    private Fruta fruta = new Fruta();

    @FXML
    private Label texto;


    @FXML
    private void BotaoFalar() {
        texto.setText(pessoa.falar());
    }

    @FXML
    private void BotaoComprar() {
        texto.setText(pessoa.comprar());
    }

    @FXML
    private void BotaoArmazenar() {
        texto.setText(barraca.armazenar());
    }

    @FXML
    private void BotaoExpor() {
        texto.setText(barraca.expor());
    }

    @FXML
    private void BotaoOrganizar() {
        texto.setText(barraca.organizar());
    }

    @FXML
    private void BotaoVender() {
        texto.setText(fruta.vender());
    }

    @FXML
    private void BotaoEstragar() {
        texto.setText(fruta.estragar());
    }

    @FXML
    private void BotaoComer() {
        texto.setText(fruta.comer());
    }
}
