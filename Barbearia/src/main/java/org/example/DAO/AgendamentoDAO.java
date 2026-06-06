package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Agendamento;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public void inserir(Agendamento a) {
        String sql = "INSERT INTO agendamento (cliente_id, funcionario_id, servico_id, data_hora, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, a.getClienteId());
            stmt.setInt(2, a.getFuncionarioId());
            stmt.setInt(3, a.getServicoId());
            stmt.setString(4, a.getDataHora() != null ? a.getDataHora().toString() : null);
            stmt.setString(5, a.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) a.setId((int) rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir agendamento: " + e.getMessage(), e);
        }
    }

    public Agendamento buscarPorId(int id) {
        String sql = "SELECT * FROM agendamento WHERE agendamento_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dtStr = rs.getString("data_hora");
                return new Agendamento(
                        rs.getInt("agendamento_id"),
                        null, null, null,
                        dtStr != null ? LocalDateTime.parse(dtStr) : null,
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar agendamento: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Agendamento> listarTodos() {
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String dtStr = rs.getString("data_hora");
                lista.add(new Agendamento(
                        rs.getInt("agendamento_id"),
                        null, null, null,
                        dtStr != null ? LocalDateTime.parse(dtStr) : null,
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar agendamentos: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Agendamento a) {
        String sql = "UPDATE agendamento SET cliente_id=?, funcionario_id=?, servico_id=?, data_hora=?, status=? WHERE agendamento_id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getClienteId());
            stmt.setInt(2, a.getFuncionarioId());
            stmt.setInt(3, a.getServicoId());
            stmt.setString(4, a.getDataHora() != null ? a.getDataHora().toString() : null);
            stmt.setString(5, a.getStatus());
            stmt.setInt(6, a.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar agendamento: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM agendamento WHERE agendamento_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar agendamento: " + e.getMessage(), e);
        }
    }
}
