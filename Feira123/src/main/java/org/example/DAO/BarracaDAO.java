package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Barraca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarracaDAO {

    public void inserir(Barraca barraca) {
        String sql = "INSERT INTO barraca (nome, numero) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, barraca.Nome());
            stmt.setInt(2, barraca.numero());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) barraca.setBarraca_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir barraca: " + e.getMessage(), e);
        }
    }

    public Barraca buscarPorId(Long id) {
        String sql = "SELECT * FROM barraca WHERE barraca_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Barraca(
                        rs.getLong("barraca_id"),
                        rs.getString("nome"),
                        rs.getInt("numero")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar barraca: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Barraca> listarTodos() {
        String sql = "SELECT * FROM barraca";
        List<Barraca> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Barraca(
                        rs.getLong("barraca_id"),
                        rs.getString("nome"),
                        rs.getInt("numero")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar barracas: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Barraca barraca) {
        String sql = "UPDATE barraca SET nome = ?, numero = ? WHERE barraca_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, barraca.Nome());
            stmt.setInt(2, barraca.numero());
            stmt.setLong(3, barraca.Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar barraca: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM barraca WHERE barraca_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar barraca: " + e.getMessage(), e);
        }
    }
}