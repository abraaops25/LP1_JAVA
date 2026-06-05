package org.example.DAO;

import org.example.ConnectionFactory;
import org.example.Model.Clientes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

    public void inserir(Clientes cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, tipo_pessoa) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.cpf());
            stmt.setString(3, cliente.tipoPessoa());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) cliente.setCliente_Id(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
        }
    }

    public Clientes buscarPorId(Long id) {
        String sql = "SELECT * FROM clientes WHERE cliente_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Clientes(
                        rs.getLong("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("tipo_pessoa")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Clientes> listarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Clientes> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Clientes(
                        rs.getLong("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("tipo_pessoa")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(Clientes cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, tipo_pessoa = ? WHERE cliente_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.cpf());
            stmt.setString(3, cliente.tipoPessoa());
            stmt.setLong(4, cliente.Cliente_Id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM clientes WHERE cliente_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }
}
