package org.example.Model;

import java.time.LocalDateTime;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private LocalDateTime dataHora;
    private String status; // "AGENDADO", "CONCLUIDO", "CANCELADO"

    public Agendamento(int id, Cliente cliente, Funcionario funcionario, Servico servico, LocalDateTime dataHora, String status) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return cliente != null ? cliente.getId() : 0; }
    public int getFuncionarioId() { return funcionario != null ? funcionario.getId() : 0; }
    public int getServicoId() { return servico != null ? servico.getId() : 0; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
