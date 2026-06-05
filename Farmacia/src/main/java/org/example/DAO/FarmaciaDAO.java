package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Farmacia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaDAO {

    public void inserir(Farmacia farmacia) {
        String sql = "INSERT INTO farmacia (nome, cnpj, endereco) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, farmacia.nome());
            stmt.setString(2, farmacia.cnpj());
            stmt.setString(3, farmacia.endereco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) farmacia.setFarmacia_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir farmácia: " + e.getMessage(), e);
        }
    }

    public Farmacia buscarPorId(Long id) {
        String sql = "SELECT * FROM farmacia WHERE farmacia_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Farmacia(
                        rs.getLong("farmacia_id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar farmácia: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Farmacia> listarTodos() {
        String sql = "SELECT * FROM farmacia";
        List<Farmacia> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Farmacia(
                        rs.getLong("farmacia_id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar farmácias: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Farmacia farmacia) {
        String sql = "UPDATE farmacia SET nome = ?, cnpj = ?, endereco = ? WHERE farmacia_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, farmacia.nome());
            stmt.setString(2, farmacia.cnpj());
            stmt.setString(3, farmacia.endereco());
            stmt.setLong(4, farmacia.Farmacia_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar farmácia: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM farmacia WHERE farmacia_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar farmácia: " + e.getMessage(), e);
        }
    }
}
