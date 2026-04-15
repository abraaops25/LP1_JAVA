package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BarbeariaController {

    @FXML
    private void BotaoCliente() {
        try {
            App.setRoot("Cliente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoAssentos() {
        try {
            App.setRoot("Assentos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoMaquininha() {
        try {
            App.setRoot("Maquininha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BotaoVoltar() {
        try {
            App.setRoot("Barbearia");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Cliente cliente = new Cliente();

    @FXML
    private Assentos assentos = new Assentos();

    @FXML
    private Maquininhas maquininhas = new Maquininhas();

    @FXML
    private Label texto;


    @FXML
    private void BotaoSentar() {
        texto.setText(cliente.sentar());
    }

    @FXML
    private void BotaoEsCorte() {
        texto.setText(cliente.escolherCorte());
    }

    @FXML
    private void BotaoPagar() {
        texto.setText(cliente.pagar());
    }
    @FXML
    private void BotaoLevantar() {
        texto.setText(cliente.levantar());
    }

    @FXML
    private void BotaoAbaixar() {
        texto.setText(assentos.abaixar());
    }

    @FXML
    private void BotaoRodar() {
        texto.setText(assentos.rodar());
    }

    @FXML
    private void BotaoDeitar() {
        texto.setText(assentos.deitar());
    }
    @FXML
    private void BotaoLevantar() {
        texto.setText(assentos.rodar());
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
