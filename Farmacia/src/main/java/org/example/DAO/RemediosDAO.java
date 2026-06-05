package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Remedios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemediosDAO {

    public void inserir(Remedios remedio) {
        String sql = "INSERT INTO remedios (nome, tipo, validade, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, remedio.nome());
            stmt.setString(2, remedio.tipo());
            stmt.setString(3, remedio.validade());
            stmt.setDouble(4, remedio.preco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) remedio.setRemedio_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir remédio: " + e.getMessage(), e);
        }
    }

    public Remedios buscarPorId(Long id) {
        String sql = "SELECT * FROM remedios WHERE remedio_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Remedios(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getString("validade"),
                        rs.getDouble("preco")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar remédio: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Remedios> listarTodos() {
        String sql = "SELECT * FROM remedios";
        List<Remedios> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Remedios(
                        rs.getLong("remedio_id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getString("validade"),
                        rs.getDouble("preco")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar remédios: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Remedios remedio) {
        String sql = "UPDATE remedios SET nome = ?, tipo = ?, validade = ?, preco = ? WHERE remedio_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, remedio.nome());
            stmt.setString(2, remedio.tipo());
            stmt.setString(3, remedio.validade());
            stmt.setDouble(4, remedio.preco());
            stmt.setLong(5, remedio.Remedio_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar remédio: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM remedios WHERE remedio_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar remédio: " + e.getMessage(), e);
        }
    }
}
