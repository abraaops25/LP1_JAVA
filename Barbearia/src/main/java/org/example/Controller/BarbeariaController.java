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
import org.example.DAO.AgendamentoDAO;
import org.example.DAO.ClienteDAO;
import org.example.DAO.FuncionarioDAO;
import org.example.DAO.ServicoDAO;
import org.example.Model.Agendamento;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.Model.Cliente;
import org.example.Model.Funcionario;
import org.example.Model.Servico;

import java.net.URL;
import java.util.ResourceBundle;

public class BarbeariaController implements Initializable {

    private final ClienteDAO     clienteDAO     = new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ServicoDAO     servicoDAO     = new ServicoDAO();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    // Campos Cliente
    @FXML private TextField campoNomeCliente;
    @FXML private TextField campoTelefoneCliente;
    @FXML private TextField campoEmailCliente;
    @FXML private Label     mensagemCliente;

    // Campos Funcionario
    @FXML private TextField campoNomeFuncionario;
    @FXML private TextField campoTelefoneFuncionario;
    @FXML private TextField campoEspecialidade;
    @FXML private TextField campoComissao;
    @FXML private Label     mensagemFuncionario;

    // Campos Servico
    @FXML private TextField campoNomeServico;
    @FXML private TextField campoPreco;
    @FXML private TextField campoTempo;
    @FXML private Label     mensagemServico;

    // Campos Agendamento
    @FXML private ComboBox<String> campoClienteAgend;
    @FXML private ComboBox<String> campoFuncionarioAgend;
    @FXML private ComboBox<String> campoServicoAgend;
    @FXML private TextField        campoDataHora;
    @FXML private ComboBox<String> campoStatus;
    @FXML private Label            mensagemAgendamento;

    // Menu
    @FXML private Label            lblTotalClientes;
    @FXML private Label            lblTotalFuncionarios;
    @FXML private Label            lblTotalServicos;
    @FXML private Label            lblTotalAgendamentos;
    @FXML private Label            labelLista;
    @FXML private ListView<String> listaGeral;

    // Ver Todos
    @FXML private ListView<String> listaTodosClientes;
    @FXML private ListView<String> listaTodosFuncionarios;
    @FXML private ListView<String> listaTodosServicos;
    @FXML private ListView<String> listaTodosAgendamentos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (campoStatus != null)
            campoStatus.getItems().addAll("AGENDADO", "CONCLUIDO", "CANCELADO");

        if (campoClienteAgend != null) {
            try { for (Cliente c : clienteDAO.listarTodos())
                campoClienteAgend.getItems().add(c.getId() + " - " + c.getNome());
            } catch (Exception e) { System.out.println("Sem clientes."); }
        }
        if (campoFuncionarioAgend != null) {
            try { for (Funcionario f : funcionarioDAO.listarTodos())
                campoFuncionarioAgend.getItems().add(f.getId() + " - " + f.getNome());
            } catch (Exception e) { System.out.println("Sem funcionários."); }
        }
        if (campoServicoAgend != null) {
            try { for (Servico s : servicoDAO.listarTodos())
                campoServicoAgend.getItems().add(s.getId() + " - " + s.getNomeServico());
            } catch (Exception e) { System.out.println("Sem serviços."); }
        }
        if (lblTotalClientes != null) atualizarResumo();
        if (listaTodosClientes != null) carregarTodos();
    }

    // ── SALVAR CLIENTE ────────────────────────────────────
    @FXML public void salvarCliente() {
        try {
            String nome = campoNomeCliente.getText().trim();
            String tel  = campoTelefoneCliente.getText().trim();
            String email = campoEmailCliente.getText().trim();
            if (nome.isEmpty() || tel.isEmpty() || email.isEmpty()) {
                mensagemCliente.setTextFill(Color.RED);
                mensagemCliente.setText("Preencha todos os campos!"); return;
            }
            Cliente c = new Cliente(nome, tel, email, 0);
            clienteDAO.inserir(c);
            mensagemCliente.setTextFill(Color.GREEN);
            mensagemCliente.setText("✅ Cliente cadastrado! ID: " + c.getId());
            limparCamposCliente();
            if (listaGeral != null) verClientes();
        } catch (Exception e) { mensagemCliente.setTextFill(Color.RED); mensagemCliente.setText("Erro: " + e.getMessage()); }
    }

    @FXML public void limparCamposCliente() {
        if (campoNomeCliente != null)     campoNomeCliente.clear();
        if (campoTelefoneCliente != null) campoTelefoneCliente.clear();
        if (campoEmailCliente != null)    campoEmailCliente.clear();
    }

    // ── SALVAR FUNCIONARIO ────────────────────────────────
    @FXML public void salvarFuncionario() {
        try {
            String nome = campoNomeFuncionario.getText().trim();
            String tel  = campoTelefoneFuncionario.getText().trim();
            String esp  = campoEspecialidade.getText().trim();
            String com  = campoComissao.getText().trim();
            if (nome.isEmpty() || tel.isEmpty() || esp.isEmpty() || com.isEmpty()) {
                mensagemFuncionario.setTextFill(Color.RED);
                mensagemFuncionario.setText("Preencha todos os campos!"); return;
            }
            Funcionario f = new Funcionario(0, nome, tel, esp, Double.parseDouble(com));
            funcionarioDAO.inserir(f);
            mensagemFuncionario.setTextFill(Color.GREEN);
            mensagemFuncionario.setText("✅ Funcionário cadastrado! ID: " + f.getId());
            limparCamposFuncionario();
            if (listaGeral != null) verFuncionarios();
        } catch (Exception e) { mensagemFuncionario.setTextFill(Color.RED); mensagemFuncionario.setText("Erro: " + e.getMessage()); }
    }

    @FXML public void limparCamposFuncionario() {
        if (campoNomeFuncionario != null)     campoNomeFuncionario.clear();
        if (campoTelefoneFuncionario != null) campoTelefoneFuncionario.clear();
        if (campoEspecialidade != null)       campoEspecialidade.clear();
        if (campoComissao != null)            campoComissao.clear();
    }

    // ── SALVAR SERVICO ────────────────────────────────────
    @FXML public void salvarServico() {
        try {
            String nome  = campoNomeServico.getText().trim();
            String preco = campoPreco.getText().trim();
            String tempo = campoTempo.getText().trim();
            if (nome.isEmpty() || preco.isEmpty() || tempo.isEmpty()) {
                mensagemServico.setTextFill(Color.RED);
                mensagemServico.setText("Preencha todos os campos!"); return;
            }
            Servico s = new Servico(0, nome, Integer.parseInt(tempo), Double.parseDouble(preco));
            servicoDAO.inserir(s);
            mensagemServico.setTextFill(Color.GREEN);
            mensagemServico.setText("✅ Serviço cadastrado! ID: " + s.getId());
            limparCamposServico();
            if (listaGeral != null) verServicos();
        } catch (Exception e) { mensagemServico.setTextFill(Color.RED); mensagemServico.setText("Erro: " + e.getMessage()); }
    }

    @FXML public void limparCamposServico() {
        if (campoNomeServico != null) campoNomeServico.clear();
        if (campoPreco != null)       campoPreco.clear();
        if (campoTempo != null)       campoTempo.clear();
    }

    // ── SALVAR AGENDAMENTO ────────────────────────────────
    @FXML public void salvarAgendamento() {
        try {
            String clienteSel     = campoClienteAgend.getValue();
            String funcionarioSel = campoFuncionarioAgend.getValue();
            String servicoSel     = campoServicoAgend.getValue();
            String dataHora       = campoDataHora.getText().trim();
            String status         = campoStatus.getValue();
            if (clienteSel == null || funcionarioSel == null || servicoSel == null
                    || dataHora.isEmpty() || status == null) {
                mensagemAgendamento.setTextFill(Color.RED);
                mensagemAgendamento.setText("Preencha todos os campos!"); return;
            }
            int clienteId     = Integer.parseInt(clienteSel.split(" - ")[0]);
            int funcionarioId = Integer.parseInt(funcionarioSel.split(" - ")[0]);
            int servicoId     = Integer.parseInt(servicoSel.split(" - ")[0]);

            Cliente c     = clienteDAO.buscarPorId(clienteId);
            Funcionario f = funcionarioDAO.buscarPorId(funcionarioId);
            Servico s     = servicoDAO.buscarPorId(servicoId);

            LocalDateTime dt = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Agendamento ag = new Agendamento(0, c, f, s, dt, status);
            agendamentoDAO.inserir(ag);
            mensagemAgendamento.setTextFill(Color.GREEN);
            mensagemAgendamento.setText("✅ Agendamento criado! ID: " + ag.getId());
            limparCamposAgendamento();
            if (listaGeral != null) verAgendamentos();
        } catch (Exception e) { mensagemAgendamento.setTextFill(Color.RED); mensagemAgendamento.setText("Erro: " + e.getMessage()); e.printStackTrace(); }
    }

    @FXML public void limparCamposAgendamento() {
        if (campoClienteAgend != null)     campoClienteAgend.setValue(null);
        if (campoFuncionarioAgend != null) campoFuncionarioAgend.setValue(null);
        if (campoServicoAgend != null)     campoServicoAgend.setValue(null);
        if (campoDataHora != null)         campoDataHora.clear();
        if (campoStatus != null)           campoStatus.setValue(null);
    }

    // ── LISTAS ────────────────────────────────────────────
    @FXML public void verClientes() {
        try {
            if (labelLista != null) labelLista.setText("👤 Clientes:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Cliente c : clienteDAO.listarTodos())
                    listaGeral.getItems().add("ID:" + c.getId() + " | " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verFuncionarios() {
        try {
            if (labelLista != null) labelLista.setText("✂️ Funcionários:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Funcionario f : funcionarioDAO.listarTodos())
                    listaGeral.getItems().add("ID:" + f.getId() + " | " + f.getNome() + " | " + f.getEspecialidade() + " | " + f.getComissao() + "%");
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verServicos() {
        try {
            if (labelLista != null) labelLista.setText("🛠️ Serviços:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Servico s : servicoDAO.listarTodos())
                    listaGeral.getItems().add("ID:" + s.getId() + " | " + s.getNomeServico() + " | R$" + s.getPreco() + " | " + s.getTempoEstimado() + "min");
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    @FXML public void verAgendamentos() {
        try {
            if (labelLista != null) labelLista.setText("📅 Agendamentos:");
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                for (Agendamento a : agendamentoDAO.listarTodos())
                    listaGeral.getItems().add("ID:" + a.getId() + " | " + (a.getDataHora() != null ? a.getDataHora().toString() : "-") + " | " + a.getStatus());
            }
        } catch (Exception e) { if (listaGeral != null) listaGeral.getItems().add("Erro: " + e.getMessage()); }
    }

    // ── VER TODOS ─────────────────────────────────────────
    @FXML public void verTodos(javafx.event.ActionEvent e) { navegarPara("VerTodos.fxml", e); }

    @FXML public void carregarTodos() {
        try {
            if (listaTodosClientes != null) {
                listaTodosClientes.getItems().clear();
                for (Cliente c : clienteDAO.listarTodos())
                    listaTodosClientes.getItems().add(c.getId() + " | " + c.getNome() + " | " + c.getTelefone());
            }
            if (listaTodosFuncionarios != null) {
                listaTodosFuncionarios.getItems().clear();
                for (Funcionario f : funcionarioDAO.listarTodos())
                    listaTodosFuncionarios.getItems().add(f.getId() + " | " + f.getNome() + " | " + f.getEspecialidade());
            }
            if (listaTodosServicos != null) {
                listaTodosServicos.getItems().clear();
                for (Servico s : servicoDAO.listarTodos())
                    listaTodosServicos.getItems().add(s.getId() + " | " + s.getNomeServico() + " | R$" + s.getPreco());
            }
            if (listaTodosAgendamentos != null) {
                listaTodosAgendamentos.getItems().clear();
                for (Agendamento a : agendamentoDAO.listarTodos())
                    listaTodosAgendamentos.getItems().add(a.getId() + " | " + (a.getDataHora() != null ? a.getDataHora().toString() : "-") + " | " + a.getStatus());
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // ── RESUMO ────────────────────────────────────────────
    @FXML public void atualizarResumo() {
        try {
            if (lblTotalClientes != null)
                lblTotalClientes.setText("👤 Clientes: " + clienteDAO.listarTodos().size());
            if (lblTotalFuncionarios != null)
                lblTotalFuncionarios.setText("✂️ Funcionários: " + funcionarioDAO.listarTodos().size());
            if (lblTotalServicos != null)
                lblTotalServicos.setText("🛠️ Serviços: " + servicoDAO.listarTodos().size());
            if (lblTotalAgendamentos != null)
                lblTotalAgendamentos.setText("📅 Agendamentos: " + agendamentoDAO.listarTodos().size());
            if (listaGeral != null) {
                listaGeral.getItems().clear();
                listaGeral.getItems().add("← Clique em Ver para listar");
            }
        } catch (Exception e) { System.out.println("Erro resumo: " + e.getMessage()); }
    }

    // ── NAVEGAÇÃO ─────────────────────────────────────────
    private void navegarPara(String fxml, Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/" + fxml));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void navegarPara(String fxml, javafx.event.ActionEvent event) {
        navegarPara(fxml, (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow());
    }

    @FXML public void BotaoCliente(javafx.event.ActionEvent e)     { navegarPara("Cliente.fxml", e); }
    @FXML public void BotaoFuncionario(javafx.event.ActionEvent e) { navegarPara("Funcionario.fxml", e); }
    @FXML public void BotaoServico(javafx.event.ActionEvent e)     { navegarPara("Servico.fxml", e); }
    @FXML public void BotaoAgendamento(javafx.event.ActionEvent e) { navegarPara("Agendamento.fxml", e); }
    @FXML public void BotaoMenu(javafx.event.ActionEvent e)        { navegarPara("MenuPrincipal.fxml", e); }
}
