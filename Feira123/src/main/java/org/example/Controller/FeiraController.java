package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.DAO.BarracaDAO;
import org.example.DAO.FeiraDAO;
import org.example.DAO.FrutaDAO;
import org.example.DAO.PessoaDAO;
import org.example.Model.Barraca;
import org.example.Model.Feira;
import org.example.Model.Fruta;
import org.example.Model.Pessoa;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FeiraController implements Initializable {

    // ── Estado global ─────────────────────────────────────
    private static Feira feiraSelecionada;

    // ── DAOs ──────────────────────────────────────────────
    private final FeiraDAO   feiraDAO   = new FeiraDAO();
    private final BarracaDAO barracaDAO = new BarracaDAO();
    private final FrutaDAO   frutaDAO   = new FrutaDAO();
    private final PessoaDAO  pessoaDAO  = new PessoaDAO();

    // ── Campos Feira ──────────────────────────────────────
    @FXML private TextField      campoNomeFeira;
    @FXML private ComboBox<String> campoDia;
    @FXML private TextField      campoEndereco;
    @FXML private Label          mensagemFeira;

    // ── Campos Barraca ────────────────────────────────────
    @FXML private TextField      campoNomeBarraca;
    @FXML private TextField      campoNumero;
    @FXML private ComboBox<String> campoFeiraDaBarraca;
    @FXML private Label          mensagemBarraca;

    // ── Campos Fruta ──────────────────────────────────────
    @FXML private TextField      campoNomeFruta;
    @FXML private TextField      campoPreco;
    @FXML private TextField      campoEpoca;
    @FXML private ComboBox<String> campoBarracaDaFruta;
    @FXML private Label          mensagemFruta;

    // ── Campos Pessoa ─────────────────────────────────────
    @FXML private TextField      campoNomePessoa;
    @FXML private ComboBox<String> campoTipoPessoa;
    @FXML private Label          mensagemPessoa;

    // ── Menu Principal ────────────────────────────────────
    @FXML private Label          labelFeiraNome;
    @FXML private Label          lblTotalFeiras;
    @FXML private Label          lblTotalBarracas;
    @FXML private Label          lblTotalFrutas;
    @FXML private Label          lblTotalPessoas;
    @FXML private Label          labelLista;
    @FXML private ListView<String> listaGeral;

    // ── Ver Todos ─────────────────────────────────────────
    @FXML private ListView<String> listaTodasFeiras;
    @FXML private ListView<String> listaTodasBarracas;
    @FXML private ListView<String> listaTodasFrutas;
    @FXML private ListView<String> listaTodasPessoas;

    // ── Telas de ação antigas ─────────────────────────────
    @FXML private Label texto;

    // ─────────────────────────────────────────────────────
    //  INITIALIZE
    // ─────────────────────────────────────────────────────
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (campoDia != null)
            campoDia.getItems().addAll(
                    "SEGUNDA", "TERÇA", "QUARTA",
                    "QUINTA", "SEXTA", "SÁBADO", "DOMINGO"
            );

        if (campoTipoPessoa != null)
            campoTipoPessoa.getItems().addAll("FEIRANTE", "FUNCIONARIO", "CLIENTE");

        if (campoFeiraDaBarraca != null) {
            try {
                for (Feira f : feiraDAO.listarTodos())
                    campoFeiraDaBarraca.getItems().add(f.Feira_Id() + " - " + f.nome());
                if (feiraSelecionada != null)
                    campoFeiraDaBarraca.setValue(
                            feiraSelecionada.Feira_Id() + " - " + feiraSelecionada.nome()
                    );
            } catch (Exception e) { System.out.println("Sem feiras."); }
        }

        if (campoBarracaDaFruta != null) {
            try {
                for (Barraca b : barracaDAO.listarTodos())
                    campoBarracaDaFruta.getItems().add(b.Id() + " - " + b.Nome());
            } catch (Exception e) { System.out.println("Sem barracas."); }
        }

        // Menu principal: carrega totais
        if (labelFeiraNome != null) atualizarResumo();

        // VerTodos: carrega listas
        if (listaTodasFeiras != null) carregarTodos();
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR FEIRA → navega para MenuPrincipal
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarFeira() {
        try {
            String nome = campoNomeFeira.getText().trim();
            String dia  = campoDia.getValue();
            String end  = campoEndereco.getText().trim();

            if (nome.isEmpty() || dia == null || end.isEmpty()) {
                mensagemFeira.setTextFill(Color.RED);
                mensagemFeira.setText("Preencha todos os campos!");
                return;
            }

            Feira feira = new Feira(null, nome, null, end);
            feiraDAO.inserir(feira, dia);
            feiraSelecionada = feira;

            mensagemFeira.setTextFill(Color.GREEN);
            mensagemFeira.setText("✅ Feira cadastrada! Indo para o menu...");

            // Navega após breve pausa
            new Thread(() -> {
                try { Thread.sleep(800); } catch (Exception ignored) {}
                javafx.application.Platform.runLater(() ->
                        navegarPara("MenuPrincipal.fxml",
                                (Stage) campoNomeFeira.getScene().getWindow())
                );
            }).start();

        } catch (Exception e) {
            mensagemFeira.setTextFill(Color.RED);
            mensagemFeira.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposFeira() {
        if (campoNomeFeira != null) campoNomeFeira.clear();
        if (campoDia != null)       campoDia.setValue(null);
        if (campoEndereco != null)  campoEndereco.clear();
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR BARRACA → navega para VerBarracas
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarBarraca() {
        try {
            String nome     = campoNomeBarraca.getText().trim();
            String numStr   = campoNumero.getText().trim();
            String feiraStr = campoFeiraDaBarraca.getValue();

            if (nome.isEmpty() || numStr.isEmpty() || feiraStr == null) {
                mensagemBarraca.setTextFill(Color.RED);
                mensagemBarraca.setText("Preencha todos os campos!");
                return;
            }

            Barraca barraca = new Barraca(null, nome, Integer.parseInt(numStr));
            barracaDAO.inserir(barraca);

            mensagemBarraca.setTextFill(Color.GREEN);
            mensagemBarraca.setText("✅ Barraca cadastrada! ID: " + barraca.Id());
            limparCamposBarraca();

            // Atualiza lista lateral se existir
            if (listaGeral != null) verBarracas();

        } catch (NumberFormatException e) {
            mensagemBarraca.setTextFill(Color.RED);
            mensagemBarraca.setText("Número inválido!");
        } catch (Exception e) {
            mensagemBarraca.setTextFill(Color.RED);
            mensagemBarraca.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposBarraca() {
        if (campoNomeBarraca != null)     campoNomeBarraca.clear();
        if (campoNumero != null)          campoNumero.clear();
        if (campoFeiraDaBarraca != null)  campoFeiraDaBarraca.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR FRUTA → atualiza lista lateral
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarFruta() {
        try {
            String nome       = campoNomeFruta.getText().trim();
            String precoStr   = campoPreco.getText().trim();
            String epoca      = campoEpoca.getText().trim();
            String barracaStr = campoBarracaDaFruta.getValue();

            if (nome.isEmpty() || precoStr.isEmpty() || epoca.isEmpty() || barracaStr == null) {
                mensagemFruta.setTextFill(Color.RED);
                mensagemFruta.setText("Preencha todos os campos!");
                return;
            }

            Double preco = Double.parseDouble(precoStr.replace(",", "."));
            Fruta fruta = new Fruta(null, epoca, nome, preco);
            frutaDAO.inserir(fruta);

            mensagemFruta.setTextFill(Color.GREEN);
            mensagemFruta.setText("✅ Fruta cadastrada! ID: " + fruta.Fruta_Id());
            limparCamposFruta();

            if (listaGeral != null) verFrutas();

        } catch (NumberFormatException e) {
            mensagemFruta.setTextFill(Color.RED);
            mensagemFruta.setText("Preço inválido!");
        } catch (Exception e) {
            mensagemFruta.setTextFill(Color.RED);
            mensagemFruta.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposFruta() {
        if (campoNomeFruta != null)      campoNomeFruta.clear();
        if (campoPreco != null)          campoPreco.clear();
        if (campoEpoca != null)          campoEpoca.clear();
        if (campoBarracaDaFruta != null) campoBarracaDaFruta.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR PESSOA → atualiza lista lateral
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarPessoa() {
        try {
            String nome = campoNomePessoa.getText().trim();
            String tipo = campoTipoPessoa.getValue();

            if (nome.isEmpty() || tipo == null) {
                mensagemPessoa.setTextFill(Color.RED);
                mensagemPessoa.setText("Preencha todos os campos!");
                return;
            }

            Pessoa pessoa = new Pessoa(null, null, nome);
            pessoaDAO.inserir(pessoa);

            mensagemPessoa.setTextFill(Color.GREEN);
            mensagemPessoa.setText("✅ Pessoa cadastrada! ID: " + pessoa.Pessoa_Id());
            limparCamposPessoa();

            if (listaGeral != null) verPessoas();

        } catch (Exception e) {
            mensagemPessoa.setTextFill(Color.RED);
            mensagemPessoa.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposPessoa() {
        if (campoNomePessoa != null)  campoNomePessoa.clear();
        if (campoTipoPessoa != null)  campoTipoPessoa.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  LISTAS NO MENU
    // ─────────────────────────────────────────────────────
    @FXML public void verFeiras() {
        try {
            if (labelLista != null) labelLista.setText("🏬 Feiras:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Feira f : feiraDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + f.Feira_Id() + " | " + f.nome() + " | " + f.endereco()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verBarracas() {
        try {
            if (labelLista != null) labelLista.setText("🏪 Barracas:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Barraca b : barracaDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + b.Id() + " | Nº" + b.numero() + " | " + b.Nome()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verFrutas() {
        try {
            if (labelLista != null) labelLista.setText("🍎 Frutas:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Fruta f : frutaDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + f.Fruta_Id() + " | " + f.Nome_fruta() +
                                    " | R$" + f.precoPorUnidade() + " | " + f.epoca()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verPessoas() {
        try {
            if (labelLista != null) labelLista.setText("👤 Pessoas:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Pessoa p : pessoaDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + p.Pessoa_Id() + " | " + p.Nome_Pessoa()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    // ─────────────────────────────────────────────────────
    //  VER TODOS
    // ─────────────────────────────────────────────────────
    @FXML public void verTodos(javafx.event.ActionEvent e) { navegarPara("VerTodos.fxml", e); }

    @FXML
    public void carregarTodos() {
        try {
            if (listaTodasFeiras != null) {
                listaTodasFeiras.getItems().clear();
                for (Feira f : feiraDAO.listarTodos())
                    listaTodasFeiras.getItems().add(
                            f.Feira_Id() + " | " + f.nome() + " | " + f.endereco()
                    );
            }
            if (listaTodasBarracas != null) {
                listaTodasBarracas.getItems().clear();
                for (Barraca b : barracaDAO.listarTodos())
                    listaTodasBarracas.getItems().add(
                            b.Id() + " | Nº" + b.numero() + " | " + b.Nome()
                    );
            }
            if (listaTodasFrutas != null) {
                listaTodasFrutas.getItems().clear();
                for (Fruta f : frutaDAO.listarTodos())
                    listaTodasFrutas.getItems().add(
                            f.Fruta_Id() + " | " + f.Nome_fruta() +
                                    " | R$" + f.precoPorUnidade() + " | " + f.epoca()
                    );
            }
            if (listaTodasPessoas != null) {
                listaTodasPessoas.getItems().clear();
                for (Pessoa p : pessoaDAO.listarTodos())
                    listaTodasPessoas.getItems().add(
                            p.Pessoa_Id() + " | " + p.Nome_Pessoa()
                    );
            }
        } catch (Exception e) { System.out.println("Erro carregar todos: " + e.getMessage()); }
    }

    // ─────────────────────────────────────────────────────
    //  RESUMO
    // ─────────────────────────────────────────────────────
    @FXML
    public void atualizarResumo() {
        try {
            if (labelFeiraNome != null)
                labelFeiraNome.setText(feiraSelecionada != null
                        ? "Feira ativa: " + feiraSelecionada.nome()
                        : "Nenhuma feira selecionada");

            List<Feira>   feiras   = feiraDAO.listarTodos();
            List<Barraca> barracas = barracaDAO.listarTodos();
            List<Fruta>   frutas   = frutaDAO.listarTodos();
            List<Pessoa>  pessoas  = pessoaDAO.listarTodos();

            if (lblTotalFeiras != null)   lblTotalFeiras.setText("🏬 Feiras: "    + feiras.size());
            if (lblTotalBarracas != null) lblTotalBarracas.setText("🏪 Barracas: " + barracas.size());
            if (lblTotalFrutas != null)   lblTotalFrutas.setText("🍎 Frutas: "    + frutas.size());
            if (lblTotalPessoas != null)  lblTotalPessoas.setText("👤 Pessoas: "  + pessoas.size());

            if (listaGeral != null) {
                listaGeral.getItems().clear();
                listaGeral.getItems().add("← Clique em Ver para listar");
            }
        } catch (Exception e) { System.out.println("Erro resumo: " + e.getMessage()); }
    }

    // ─────────────────────────────────────────────────────
    //  NAVEGAÇÃO
    // ─────────────────────────────────────────────────────
    private void navegarPara(String fxml, Stage stage) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/org/example/" + fxml)
            );
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void navegarPara(String fxml, javafx.event.ActionEvent event) {
        navegarPara(fxml,
                (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()
        );
    }

    // ─────────────────────────────────────────────────────
    //  BOTÕES DE NAVEGAÇÃO
    // ─────────────────────────────────────────────────────
    @FXML public void BotaoBarraca(javafx.event.ActionEvent e)  { navegarPara("Barraca.fxml", e); }
    @FXML public void BotaoFruta(javafx.event.ActionEvent e)    { navegarPara("Fruta.fxml", e); }
    @FXML public void BotaoPessoas(javafx.event.ActionEvent e)  { navegarPara("Pessoa.fxml", e); }
    @FXML public void BotaoNovaFeira(javafx.event.ActionEvent e){ navegarPara("Feira.fxml", e); }
    @FXML public void BotaoVoltar(javafx.event.ActionEvent e)   { navegarPara("MenuPrincipal.fxml", e); }
    @FXML public void BotaoMenu(javafx.event.ActionEvent e)     { navegarPara("MenuPrincipal.fxml", e); }
    @FXML public void BotaoArmazenar(javafx.event.ActionEvent e){ navegarPara("Barraca.fxml", e); }
    @FXML public void BotaoExpor(javafx.event.ActionEvent e)    { navegarPara("Fruta.fxml", e); }
    @FXML public void BotaoOrganizar(javafx.event.ActionEvent e){ navegarPara("Pessoa.fxml", e); }
    @FXML public void BotaoVender(javafx.event.ActionEvent e)   { if (texto != null) texto.setText("Vendendo..."); }
    @FXML public void BotaoEstragar(javafx.event.ActionEvent e) { if (texto != null) texto.setText("Estragando..."); }
    @FXML public void BotaoComer(javafx.event.ActionEvent e)    { if (texto != null) texto.setText("Comendo..."); }
    @FXML public void BotaoFalar(javafx.event.ActionEvent e)    { if (texto != null) texto.setText("Falando..."); }
    @FXML public void BotaoComprar(javafx.event.ActionEvent e)  { if (texto != null) texto.setText("Comprando..."); }
}