package com.mycompany.evai.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Restaurante;

public class RestauranteDAO {

    public void incluir(Restaurante restaurante) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO restaurantes (nome, endereco, telefone) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           
            stmt.setString(1, restaurante.getNome()); // Nome do restaurante
            stmt.setString(2, restaurante.getEndereco()); // Seta o endereço na tabela restaurante
            stmt.setString(3, restaurante.getTelefone()); // Telefone do restaurante

            int linhasAfetadas = stmt.executeUpdate();
            
            System.out.println("Restaurante: " + restaurante.getNome() + " inserido com sucesso");

            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        restaurante.setId(rs.getInt(1)); // ID é salvo no objeto
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

    public void alterar(Restaurante restaurante) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE restaurantes SET nome = ?, endereco = ?, telefone = ? where id_restaurante = ? ");
          
            stmt.setString(1, restaurante.getNome()); // Nome do restaurante
            stmt.setString(2, restaurante.getEndereco()); // Seta o endereço na tabela restaurante
            stmt.setString(3, restaurante.getTelefone()); // Telefone do restaurante
            stmt.setInt(4, restaurante.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("Restaurante: " + restaurante.getNome() + " alterado com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao inserir informação no banco de dados");

        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }

    public void excluir(Restaurante restaurante) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE from restaurantes WHERE id_restaurante = ?");
           
            stmt.setInt(1, restaurante.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("Restaurante: " + restaurante.getNome() + " excluído com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

    }

     public List<Restaurante> consulta(){

       Connection con = Conexao.getConexao();
       PreparedStatement stmt = null;
       
       ResultSet rs = null;
       
       
       List<Restaurante> restaurantes = new ArrayList<Restaurante>();
       
       
       try{
           
           stmt = con.prepareStatement("select id_restaurante, nome, endereco, telefone from restaurantes");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               Restaurante restaurante =  new Restaurante();
              
               restaurante.setId(rs.getInt("id_restaurante"));
               restaurante.setNome(rs.getString("nome"));
               restaurante.setEndereco(rs.getString("endereco"));
               restaurante.setTelefone(rs.getString("telefone"));
              
               
               restaurantes.add(restaurante);
               
           }
           
           
       }catch (SQLException s){
           s.printStackTrace();
           
       }
       
        finally {
            Conexao.fecharConexao(con, stmt);

        }
       
      return restaurantes;
       
   }

     // Na classe RestauranteDAO
    public static Restaurante consultarPorId(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Restaurante restaurante = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM restaurantes WHERE id_restaurante = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                restaurante = new Restaurante();
                restaurante.setId(rs.getInt("id_restaurante"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEndereco(rs.getString("endereco"));
                restaurante.setTelefone(rs.getString("telefone"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar restaurante por ID", ex);
        } finally {
            Conexao.fecharConexao(con, stmt, rs);
        }

        return restaurante;
    }

}

