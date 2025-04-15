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
import com.mycompany.evai.entidade.Pedido;
import com.mycompany.evai.entidade.Produto;

public class ItemPedidoDAO {
    
    public void incluir(ItemPedido itemPedido, Produto produto, Pedido pedido) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           
            stmt.setInt(1, pedido.getId()); 
            stmt.setInt(2, produto.getId());
            stmt.setInt(3, itemPedido.getQuantidade()); 
            stmt.setFloat(4, itemPedido.getValorUnitario());  

            int linhasAfetadas = stmt.executeUpdate();
            
            System.out.println("itemPedido: " + itemPedido.getId() + " inserido com sucesso");

            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        itemPedido.setId(rs.getInt(1)); // ID é salvo no objeto
                    }
                }
            }
    

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    
    }

    public void alterar(ItemPedido itemPedido, Pedido pedido, Produto produto) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            // Corrigido nome da tabela e ordem dos parâmetros
            stmt = con.prepareStatement("UPDATE itens_pedido SET id_pedido = ?, id_produto = ?, quantidade = ?, preco_unitario = ? WHERE id_item = ?");
          
            stmt.setInt(1, pedido.getId()); 
            stmt.setInt(2, produto.getId()); 
            stmt.setInt(3, itemPedido.getQuantidade()); 
            stmt.setFloat(4, itemPedido.getValorUnitario());
            stmt.setInt(5, itemPedido.getId()); // Corrigido índice para 5
            
            stmt.executeUpdate();
            
            System.out.println("itemPedido: " + itemPedido.getId() + " alterado com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao alterar informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public void excluir(ItemPedido itemPedido) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            // Corrigido nome da tabela
            stmt = con.prepareStatement("DELETE FROM itens_pedido WHERE id_item = ?");
            stmt.setInt(1, itemPedido.getId());
            stmt.executeUpdate();
            
            System.out.println("itemPedido: " + itemPedido.getId() + " excluído com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao excluir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
    }

    public List<ItemPedido> consulta() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ItemPedido> itemPedidos = new ArrayList<>();

        try {
            // Corrigido nome da tabela e colunas
            stmt = con.prepareStatement("SELECT id_item, id_pedido, id_produto, quantidade, preco_unitario FROM itens_pedido");
            rs = stmt.executeQuery();
           
            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id_item"));
                itemPedido.setIdPedido(rs.getInt("id_pedido"));
                itemPedido.setIdProduto(rs.getInt("id_produto"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itemPedido.setValorUnitario(rs.getFloat("preco_unitario"));
                itemPedidos.add(itemPedido);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            Conexao.fecharConexao(con, stmt);
        }
        return itemPedidos;
    }
}
