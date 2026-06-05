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
import org.example.DAO.ClientesDAO;
import org.example.DAO.FarmaciaDAO;
import org.example.DAO.PrateleiraDAO;
import org.example.DAO.RemediosDAO;
import org.example.Model.Clientes;
import org.example.Model.Farmacia;
import org.example.Model.Prateleira;
import org.example.Model.Remedios;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FarmaciaController implements Initializable {

    // ── Estado global ─────────────────────────────────────
    private static Farmacia farmaciaSelecionada;

    // ── DAOs ──────────────────────────────────────────────
    private final FarmaciaDAO  farmaciaDAO  = new FarmaciaDAO();
    private final PrateleiraDAO prateleiraDAO = new PrateleiraDAO();
    private final RemediosDAO  remediosDAO  = new RemediosDAO();
    private final ClientesDAO  clientesDAO  = new ClientesDAO();

    // ── Campos Farmacia ───────────────────────────────────
    @FXML private TextField       campoNomeFarmacia;
    @FXML private TextField       campoCnpj;
    @FXML private TextField       campoEndereco;
    @FXML private Label           mensagemFarmacia;

    // ── Campos Prateleira ─────────────────────────────────
    @FXML private TextField       campoCodigo;
    @FXML private TextField       campoCorredor;
    @FXML private TextField       campoCapacidade;
    @FXML private ComboBox<String> campoFarmaciaDaPrateleira;
    @FXML private Label           mensagemPrateleira;

    // ── Campos Remedios ───────────────────────────────────
    @FXML private TextField       campoNomeRemedio;
    @FXML private TextField       campoTipo;
    @FXML private TextField       campoValidade;
    @FXML private TextField       campoPreco;
    @FXML private ComboBox<String> campoPrateleiraDoRemedio;
    @FXML private Label           mensagemRemedio;

    // ── Campos Clientes ───────────────────────────────────
    @FXML private TextField       campoNomeCliente;
    @FXML private TextField       campoCpf;
    @FXML private ComboBox<String> campoTipoPessoa;
    @FXML private Label           mensagemCliente;

    // ── Menu Principal ────────────────────────────────────
    @FXML private Label           labelFarmaciaNome;
    @FXML private Label           lblTotalFarmacias;
    @FXML private Label           lblTotalPrateleiras;
    @FXML private Label           lblTotalRemedios;
    @FXML private Label           lblTotalClientes;
    @FXML private Label           labelLista;
    @FXML private ListView<String> listaGeral;

    // ── Ver Todos ─────────────────────────────────────────
    @FXML private ListView<String> listaTodasFarmacias;
    @FXML private ListView<String> listaTodasPrateleiras;
    @FXML private ListView<String> listaTodosRemedios;
    @FXML private ListView<String> listaTodosClientes;

    // ─────────────────────────────────────────────────────
    //  INITIALIZE
    // ─────────────────────────────────────────────────────
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (campoTipoPessoa != null)
            campoTipoPessoa.getItems().addAll("COMUM", "CONVENIADO", "IDOSO");

        if (campoFarmaciaDaPrateleira != null) {
            try {
                for (Farmacia f : farmaciaDAO.listarTodos())
                    campoFarmaciaDaPrateleira.getItems().add(f.Farmacia_Id() + " - " + f.nome());
                if (farmaciaSelecionada != null)
                    campoFarmaciaDaPrateleira.setValue(
                            farmaciaSelecionada.Farmacia_Id() + " - " + farmaciaSelecionada.nome()
                    );
            } catch (Exception e) { System.out.println("Sem farmácias."); }
        }

        if (campoPrateleiraDoRemedio != null) {
            try {
                for (Prateleira p : prateleiraDAO.listarTodos())
                    campoPrateleiraDoRemedio.getItems().add(p.Prateleira_Id() + " - " + p.codigo());
            } catch (Exception e) { System.out.println("Sem prateleiras."); }
        }

        // Menu principal: carrega totais
        if (labelFarmaciaNome != null) atualizarResumo();

        // VerTodos: carrega listas
        if (listaTodasFarmacias != null) carregarTodos();
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR FARMACIA → navega para MenuPrincipal
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarFarmacia() {
        try {
            String nome = campoNomeFarmacia.getText().trim();
            String cnpj = campoCnpj.getText().trim();
            String end  = campoEndereco.getText().trim();

            if (nome.isEmpty() || cnpj.isEmpty() || end.isEmpty()) {
                mensagemFarmacia.setTextFill(Color.RED);
                mensagemFarmacia.setText("Preencha todos os campos!");
                return;
            }

            Farmacia farmacia = new Farmacia(null, nome, cnpj, end);
            farmaciaDAO.inserir(farmacia);
            farmaciaSelecionada = farmacia;

            mensagemFarmacia.setTextFill(Color.GREEN);
            mensagemFarmacia.setText("✅ Farmácia cadastrada! Indo para o menu...");

            new Thread(() -> {
                try { Thread.sleep(800); } catch (Exception ignored) {}
                javafx.application.Platform.runLater(() ->
                        navegarPara("MenuPrincipal.fxml",
                                (Stage) campoNomeFarmacia.getScene().getWindow())
                );
            }).start();

        } catch (Exception e) {
            mensagemFarmacia.setTextFill(Color.RED);
            mensagemFarmacia.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposFarmacia() {
        if (campoNomeFarmacia != null) campoNomeFarmacia.clear();
        if (campoCnpj != null)        campoCnpj.clear();
        if (campoEndereco != null)    campoEndereco.clear();
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR PRATELEIRA
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarPrateleira() {
        try {
            String codigo    = campoCodigo.getText().trim();
            String corredor  = campoCorredor.getText().trim();
            String capStr    = campoCapacidade.getText().trim();

            if (codigo.isEmpty() || corredor.isEmpty() || capStr.isEmpty()) {
                mensagemPrateleira.setTextFill(Color.RED);
                mensagemPrateleira.setText("Preencha todos os campos!");
                return;
            }

            Prateleira prateleira = new Prateleira(null, codigo, corredor, Integer.parseInt(capStr));
            prateleiraDAO.inserir(prateleira);

            mensagemPrateleira.setTextFill(Color.GREEN);
            mensagemPrateleira.setText("✅ Prateleira cadastrada! ID: " + prateleira.Prateleira_Id());
            limparCamposPrateleira();

            if (listaGeral != null) verPrateleiras();

        } catch (Exception e) {
            mensagemPrateleira.setTextFill(Color.RED);
            mensagemPrateleira.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposPrateleira() {
        if (campoCodigo != null)    campoCodigo.clear();
        if (campoCorredor != null)  campoCorredor.clear();
        if (campoCapacidade != null) campoCapacidade.clear();
        if (campoFarmaciaDaPrateleira != null) campoFarmaciaDaPrateleira.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR REMEDIO
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarRemedio() {
        try {
            String nome     = campoNomeRemedio.getText().trim();
            String tipo     = campoTipo.getText().trim();
            String validade = campoValidade.getText().trim();
            String precoStr = campoPreco.getText().trim();

            if (nome.isEmpty() || tipo.isEmpty() || validade.isEmpty() || precoStr.isEmpty()) {
                mensagemRemedio.setTextFill(Color.RED);
                mensagemRemedio.setText("Preencha todos os campos!");
                return;
            }

            Remedios remedio = new Remedios(null, nome, tipo, validade, Double.parseDouble(precoStr));
            remediosDAO.inserir(remedio);

            mensagemRemedio.setTextFill(Color.GREEN);
            mensagemRemedio.setText("✅ Remédio cadastrado! ID: " + remedio.Remedio_Id());
            limparCamposRemedio();

            if (listaGeral != null) verRemedios();

        } catch (Exception e) {
            mensagemRemedio.setTextFill(Color.RED);
            mensagemRemedio.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposRemedio() {
        if (campoNomeRemedio != null)        campoNomeRemedio.clear();
        if (campoTipo != null)               campoTipo.clear();
        if (campoValidade != null)           campoValidade.clear();
        if (campoPreco != null)              campoPreco.clear();
        if (campoPrateleiraDoRemedio != null) campoPrateleiraDoRemedio.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  SALVAR CLIENTE
    // ─────────────────────────────────────────────────────
    @FXML
    public void salvarCliente() {
        try {
            String nome = campoNomeCliente.getText().trim();
            String cpf  = campoCpf.getText().trim();
            String tipo = campoTipoPessoa.getValue();

            if (nome.isEmpty() || cpf.isEmpty() || tipo == null) {
                mensagemCliente.setTextFill(Color.RED);
                mensagemCliente.setText("Preencha todos os campos!");
                return;
            }

            Clientes cliente = new Clientes(null, nome, cpf, tipo);
            clientesDAO.inserir(cliente);

            mensagemCliente.setTextFill(Color.GREEN);
            mensagemCliente.setText("✅ Cliente cadastrado! ID: " + cliente.Cliente_Id());
            limparCamposCliente();

            if (listaGeral != null) verClientes();

        } catch (Exception e) {
            mensagemCliente.setTextFill(Color.RED);
            mensagemCliente.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML public void limparCamposCliente() {
        if (campoNomeCliente != null) campoNomeCliente.clear();
        if (campoCpf != null)         campoCpf.clear();
        if (campoTipoPessoa != null)  campoTipoPessoa.setValue(null);
    }

    // ─────────────────────────────────────────────────────
    //  LISTAS NO MENU
    // ─────────────────────────────────────────────────────
    @FXML public void verFarmacias() {
        try {
            if (labelLista != null) labelLista.setText("🏥 Farmácias:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Farmacia f : farmaciaDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + f.Farmacia_Id() + " | " + f.nome() + " | " + f.cnpj() + " | " + f.endereco()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verPrateleiras() {
        try {
            if (labelLista != null) labelLista.setText("🗄️ Prateleiras:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Prateleira p : prateleiraDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + p.Prateleira_Id() + " | " + p.codigo() + " | " + p.corredor() + " | Cap:" + p.capacidade()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verRemedios() {
        try {
            if (labelLista != null) labelLista.setText("💊 Remédios:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Remedios r : remediosDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + r.Remedio_Id() + " | " + r.nome() + " | " + r.tipo() +
                                    " | Val:" + r.validade() + " | R$" + r.preco()
                    );
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verClientes() {
        try {
            if (labelLista != null) labelLista.setText("👤 Clientes:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Clientes c : clientesDAO.listarTodos())
                    listaGeral.getItems().add(
                            "ID:" + c.Cliente_Id() + " | " + c.nome() + " | CPF:" + c.cpf() + " | " + c.tipoPessoa()
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
            if (listaTodasFarmacias != null) {
                listaTodasFarmacias.getItems().clear();
                for (Farmacia f : farmaciaDAO.listarTodos())
                    listaTodasFarmacias.getItems().add(
                            f.Farmacia_Id() + " | " + f.nome() + " | " + f.cnpj() + " | " + f.endereco()
                    );
            }
            if (listaTodasPrateleiras != null) {
                listaTodasPrateleiras.getItems().clear();
                for (Prateleira p : prateleiraDAO.listarTodos())
                    listaTodasPrateleiras.getItems().add(
                            p.Prateleira_Id() + " | " + p.codigo() + " | " + p.corredor() + " | Cap:" + p.capacidade()
                    );
            }
            if (listaTodosRemedios != null) {
                listaTodosRemedios.getItems().clear();
                for (Remedios r : remediosDAO.listarTodos())
                    listaTodosRemedios.getItems().add(
                            r.Remedio_Id() + " | " + r.nome() + " | " + r.tipo() +
                                    " | Val:" + r.validade() + " | R$" + r.preco()
                    );
            }
            if (listaTodosClientes != null) {
                listaTodosClientes.getItems().clear();
                for (Clientes c : clientesDAO.listarTodos())
                    listaTodosClientes.getItems().add(
                            c.Cliente_Id() + " | " + c.nome() + " | CPF:" + c.cpf() + " | " + c.tipoPessoa()
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
            if (labelFarmaciaNome != null)
                labelFarmaciaNome.setText(farmaciaSelecionada != null
                        ? "Farmácia ativa: " + farmaciaSelecionada.nome()
                        : "Nenhuma farmácia selecionada");

            List<Farmacia>   farmacias   = farmaciaDAO.listarTodos();
            List<Prateleira> prateleiras = prateleiraDAO.listarTodos();
            List<Remedios>   remedios    = remediosDAO.listarTodos();
            List<Clientes>   clientes    = clientesDAO.listarTodos();

            if (lblTotalFarmacias != null)   lblTotalFarmacias.setText("🏥 Farmácias: "    + farmacias.size());
            if (lblTotalPrateleiras != null) lblTotalPrateleiras.setText("🗄️ Prateleiras: " + prateleiras.size());
            if (lblTotalRemedios != null)    lblTotalRemedios.setText("💊 Remédios: "      + remedios.size());
            if (lblTotalClientes != null)    lblTotalClientes.setText("👤 Clientes: "      + clientes.size());

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
    @FXML public void BotaoPrateleira(javafx.event.ActionEvent e)  { navegarPara("Prateleira.fxml", e); }
    @FXML public void BotaoRemedio(javafx.event.ActionEvent e)     { navegarPara("Remedios.fxml", e); }
    @FXML public void BotaoCliente(javafx.event.ActionEvent e)     { navegarPara("Clientes.fxml", e); }
    @FXML public void BotaoNovaFarmacia(javafx.event.ActionEvent e){ navegarPara("Farmacia.fxml", e); }
    @FXML public void BotaoVoltar(javafx.event.ActionEvent e)      { navegarPara("MenuPrincipal.fxml", e); }
    @FXML public void BotaoMenu(javafx.event.ActionEvent e)        { navegarPara("MenuPrincipal.fxml", e); }
}