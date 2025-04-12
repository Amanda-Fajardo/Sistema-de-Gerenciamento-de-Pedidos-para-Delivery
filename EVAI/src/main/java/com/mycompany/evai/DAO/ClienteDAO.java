package com.mycompany.evai.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Cliente;

public class ClienteDAO {
    
    public void incluir(Cliente cliente) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO clientes (nome, telefone, endereco) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           
            stmt.setString(1, cliente.getNome()); // Nome do cliente
            stmt.setString(3, cliente.getTelefone()); // Seta o endereço na tabela cliente
            stmt.setString(2, cliente.getEndereco()); // Telefone do cliente

            int linhasAfetadas = stmt.executeUpdate();
            
            System.out.println("cliente: " + cliente.getNome() + " inserido com sucesso");

            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setId(rs.getInt(1)); // ID é salvo no objeto
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

    public void alterar(Cliente cliente) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clientes SET nome = ?, telefone = ?, endereco = ? where id_cliente = ? ");
          
            stmt.setString(1, cliente.getNome()); // Nome do cliente
            stmt.setString(3, cliente.getTelefone()); // Telefone do cliente
            stmt.setString(2, cliente.getEndereco()); // Seta o endereço na tabela cliente
            stmt.setInt(4, cliente.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("cliente: " + cliente.getNome() + " alterado com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao inserir informação no banco de dados");

        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }

    public void excluir(Cliente cliente) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE from clientes WHERE id_cliente = ?");
           
            stmt.setInt(1, cliente.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("cliente: " + cliente.getNome() + " excluído com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

    }

     public List<Cliente> consulta(){

       Connection con = Conexao.getConexao();
       PreparedStatement stmt = null;
       
       ResultSet rs = null;
       
       
       List<Cliente> clientes = new ArrayList<Cliente>();
       
       
       try{
           
           stmt = con.prepareStatement("select id_cliente, nome, endereco, telefone from clientes");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               Cliente cliente =  new Cliente();
              
               cliente.setId(rs.getInt("id_cliente"));
               cliente.setNome(rs.getString("nome"));
               cliente.setEndereco(rs.getString("endereco"));
               cliente.setTelefone(rs.getString("telefone"));
              
               
               clientes.add(cliente);
               
           }
           
           
       }catch (SQLException s){
           s.printStackTrace();
           
       }
       
        finally {
            Conexao.fecharConexao(con, stmt);

        }
       
      return clientes;
       
   }
     
    public static Cliente consultarPorId(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE id_cliente = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar cliente por ID", ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return cliente;
}

}
