package com.mycompany.evai.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.ItemPedido;

public class ItemPedidoDAO {

    // Método para incluir um item no pedido
    public void incluir(ItemPedido itemPedido) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, itemPedido.getIdPedido());
            stmt.setInt(2, itemPedido.getIdProduto());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setFloat(4, itemPedido.getValorUnitario());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        itemPedido.setId(rs.getInt(1)); // Define o ID gerado no objeto
                    }
                }
            }

            System.out.println("Item do pedido inserido com sucesso: " + itemPedido.getId());

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir item no pedido", ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    // Método para consultar todos os itens
    public List<ItemPedido> consulta() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemPedido> itens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT id_item, id_pedido, id_produto, quantidade, preco_unitario FROM itens_pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setId(rs.getInt("id_item"));
                item.setIdPedido(rs.getInt("id_pedido"));
                item.setIdProduto(rs.getInt("id_produto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorUnitario(rs.getFloat("preco_unitario"));

                itens.add(item);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar itens do pedido", ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return itens;
    }

    // Método para consultar itens por pedido
    public List<ItemPedido> consultarPorPedido(int idPedido) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemPedido> itens = new ArrayList<>();

        try {
            stmt = con.prepareStatement(
                "SELECT id_item, id_pedido, id_produto, quantidade, preco_unitario FROM itens_pedido WHERE id_pedido = ?"
            );
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setId(rs.getInt("id_item"));
                item.setIdPedido(rs.getInt("id_pedido"));
                item.setIdProduto(rs.getInt("id_produto"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorUnitario(rs.getFloat("preco_unitario"));

                itens.add(item);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar itens do pedido", ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return itens;
    }

    // Método para alterar um item do pedido
    public void alterar(ItemPedido itemPedido) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE itens_pedido SET id_pedido = ?, id_produto = ?, quantidade = ?, preco_unitario = ? WHERE id_item = ?"
            );

            stmt.setInt(1, itemPedido.getIdPedido());
            stmt.setInt(2, itemPedido.getIdProduto());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setFloat(4, itemPedido.getValorUnitario());
            stmt.setInt(5, itemPedido.getId());

            stmt.executeUpdate();

            System.out.println("Item do pedido alterado com sucesso: " + itemPedido.getId());

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao alterar item do pedido", ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    // Método para excluir um item do pedido
    public void excluir(int idItem) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM itens_pedido WHERE id_item = ?");
            stmt.setInt(1, idItem);

            stmt.executeUpdate();

            System.out.println("Item do pedido excluído com sucesso: " + idItem);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao excluir item do pedido", ex);
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
