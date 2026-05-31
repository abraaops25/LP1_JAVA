package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome_pessoa, tipo_pessoa) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pessoa.Nome_Pessoa());
            stmt.setString(2, pessoa.tipoPessoa().toString());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) pessoa.setPessoa_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir pessoa: " + e.getMessage(), e);
        }
    }

    public Pessoa buscarPorId(Long id) {
        String sql = "SELECT * FROM pessoa WHERE pessoa_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pessoa(
                        rs.getLong("pessoa_id"),
                        null, // Enum precisa ser convertido conforme seu tipo
                        rs.getString("nome_pessoa")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Pessoa> listarTodos() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pessoa(
                        rs.getLong("pessoa_id"),
                        null,
                        rs.getString("nome_pessoa")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pessoas: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome_pessoa = ?, tipo_pessoa = ? WHERE pessoa_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.Nome_Pessoa());
            stmt.setString(2, pessoa.tipoPessoa().toString());
            stmt.setLong(3, pessoa.Pessoa_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pessoa: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM pessoa WHERE pessoa_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pessoa: " + e.getMessage(), e);
        }
    }
}