package com.mycompany.evai.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Cliente;
import com.mycompany.evai.entidade.Pedido;
import com.mycompany.evai.entidade.Restaurante;

public class PedidoDAO {

    public void incluir(Pedido pedido, Restaurante restaurante, Cliente cliente) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pedidos (id_cliente, id_restaurante, status, data_pedido) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           
            stmt.setInt(1, cliente.getId()); // Id do cliente
            stmt.setInt(2, restaurante.getId()); // Id do restaurante
            stmt.setString(3, pedido.getStatus()); 
            stmt.setDate(4, Date.valueOf(pedido.getData()));

            int linhasAfetadas = stmt.executeUpdate();
            
            System.out.println("pedido: " + pedido.getId() + " inserido com sucesso");

            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId(rs.getInt(1)); // ID é salvo no objeto
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

    public void alterar(Pedido pedido, Cliente cliente, Restaurante restaurante) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedidos SET id_cliente = ?, id_restaurante = ?, status = ?, data_pedido = ? where id_pedido = ? ");
        
           
            stmt.setInt(1, cliente.getId()); // Id do cliente
            stmt.setInt(2, restaurante.getId()); // Id do restaurante
            stmt.setString(3, pedido.getStatus()); 
            stmt.setDate(4, Date.valueOf(pedido.getData()));
            
            stmt.executeUpdate();
            
            System.out.println("pedido: " + pedido.getId() + " alterado com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao inserir informação no banco de dados");

        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }

    public void excluir(Pedido pedido) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE from pedidos WHERE id_pedido = ?");
           
            stmt.setInt(1, pedido.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("pedido: " + pedido.getId() + " excluído com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

    }

     public List<Pedido> consulta(){

       Connection con = Conexao.getConexao();
       PreparedStatement stmt = null;
       
       ResultSet rs = null;
       
       
       List<Pedido> pedidos = new ArrayList<Pedido>();
       
       
       try{
           
           stmt = con.prepareStatement("select id_pedido, id_cliente, id_restaurante, status, data_pedido from pedidos");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               Pedido pedido =  new Pedido();
              
               pedido.setId(rs.getInt("id_pedido"));
               pedido.setIdCliente(rs.getInt("id_cliente"));
               pedido.setIdRestaurante(rs.getInt("id_restaurante"));
               pedido.setStatus(rs.getString("status"));
               pedido.setData(rs.getDate("data_pedido").toLocalDate());
              
               
               pedidos.add(pedido);
               
           }
           
           
       }catch (SQLException s){
           s.printStackTrace();
           
       }
       
        finally {
            Conexao.fecharConexao(con, stmt);

        }
       
      return pedidos;
       
   }
}
