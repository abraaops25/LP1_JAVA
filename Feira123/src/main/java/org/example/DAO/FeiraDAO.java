package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Feira;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeiraDAO {
    public void inserir(Feira feira, String diaDaSemana) {
        String sql = "INSERT INTO feira (nome, dia_da_semana, endereco) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, feira.nome());
            stmt.setString(2, diaDaSemana);
            stmt.setString(3, feira.endereco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) feira.setFeira_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir feira: " + e.getMessage(), e);
        }
    }

    public Feira buscarPorId(Long id) {
        String sql = "SELECT * FROM feira WHERE feira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Feira(
                        rs.getLong("feira_id"),
                        rs.getString("nome"),
                        null, // Enum precisa ser convertido conforme seu tipo
                        rs.getString("endereco")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar feira: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Feira> listarTodos() {
        String sql = "SELECT * FROM feira";
        List<Feira> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Feira(
                        rs.getLong("feira_id"),
                        rs.getString("nome"),
                        null,
                        rs.getString("endereco")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar feiras: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Feira feira) {
        String sql = "UPDATE feira SET nome = ?, dia_da_semana = ?, endereco = ? WHERE feira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, feira.nome());
            stmt.setString(2, feira.diaDaSemana().toString());
            stmt.setString(3, feira.endereco());
            stmt.setLong(4, feira.Feira_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar feira: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM feira WHERE feira_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar feira: " + e.getMessage(), e);
        }
    }
}