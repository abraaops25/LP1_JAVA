package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Fruta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrutaDAO {

    public void inserir(Fruta fruta) {
        String sql = "INSERT INTO fruta (nome_fruta, preco_por_unidade, epoca, barraca_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fruta.Nome_fruta());
            stmt.setDouble(2, fruta.precoPorUnidade());
            stmt.setString(3, fruta.epoca());
            stmt.setLong(4, fruta.Fruta_Id()); // ajuste se tiver barraca_id separado
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) fruta.setFruta_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fruta: " + e.getMessage(), e);
        }
    }

    public Fruta buscarPorId(Long id) {
        String sql = "SELECT * FROM fruta WHERE fruta_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("epoca"),
                        rs.getString("nome_fruta"),
                        rs.getDouble("preco_por_unidade")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fruta: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Fruta> listarTodos() {
        String sql = "SELECT * FROM fruta";
        List<Fruta> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Fruta(
                        rs.getLong("fruta_id"),
                        rs.getString("epoca"),
                        rs.getString("nome_fruta"),
                        rs.getDouble("preco_por_unidade")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar frutas: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Fruta fruta) {
        String sql = "UPDATE fruta SET nome_fruta = ?, preco_por_unidade = ?, epoca = ? WHERE fruta_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fruta.Nome_fruta());
            stmt.setDouble(2, fruta.precoPorUnidade());
            stmt.setString(3, fruta.epoca());
            stmt.setLong(4, fruta.Fruta_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fruta: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM fruta WHERE fruta_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fruta: " + e.getMessage(), e);
        }
    }
}