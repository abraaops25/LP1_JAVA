package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Prateleira;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrateleiraDAO {

    public void inserir(Prateleira prateleira) {
        String sql = "INSERT INTO prateleira (codigo, corredor, capacidade) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, prateleira.codigo());
            stmt.setString(2, prateleira.corredor());
            stmt.setInt(3, prateleira.capacidade());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) prateleira.setPrateleira_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir prateleira: " + e.getMessage(), e);
        }
    }

    public Prateleira buscarPorId(Long id) {
        String sql = "SELECT * FROM prateleira WHERE prateleira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Prateleira(
                        rs.getLong("prateleira_id"),
                        rs.getString("codigo"),
                        rs.getString("corredor"),
                        rs.getInt("capacidade")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prateleira: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Prateleira> listarTodos() {
        String sql = "SELECT * FROM prateleira";
        List<Prateleira> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Prateleira(
                        rs.getLong("prateleira_id"),
                        rs.getString("codigo"),
                        rs.getString("corredor"),
                        rs.getInt("capacidade")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar prateleiras: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Prateleira prateleira) {
        String sql = "UPDATE prateleira SET codigo = ?, corredor = ?, capacidade = ? WHERE prateleira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prateleira.codigo());
            stmt.setString(2, prateleira.corredor());
            stmt.setInt(3, prateleira.capacidade());
            stmt.setLong(4, prateleira.Prateleira_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar prateleira: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM prateleira WHERE prateleira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar prateleira: " + e.getMessage(), e);
        }
    }
}
