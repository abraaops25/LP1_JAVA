package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public void inserir(Servico s) {
        String sql = "INSERT INTO servico (nome_servico, preco, tempo_estimado) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, s.getNomeServico());
            stmt.setDouble(2, s.getPreco());
            stmt.setInt(3, s.getTempoEstimado());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) s.setId((int) rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir serviço: " + e.getMessage(), e);
        }
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE servico_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Servico(
                    rs.getInt("servico_id"),
                    rs.getString("nome_servico"),
                    rs.getInt("tempo_estimado"),
                    rs.getDouble("preco")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM servico";
        List<Servico> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) lista.add(new Servico(
                    rs.getInt("servico_id"),
                    rs.getString("nome_servico"),
                    rs.getInt("tempo_estimado"),
                    rs.getDouble("preco")
            ));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Servico s) {
        String sql = "UPDATE servico SET nome_servico = ?, preco = ?, tempo_estimado = ? WHERE servico_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getNomeServico());
            stmt.setDouble(2, s.getPreco());
            stmt.setInt(3, s.getTempoEstimado());
            stmt.setInt(4, s.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM servico WHERE servico_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar serviço: " + e.getMessage(), e);
        }
    }
}
