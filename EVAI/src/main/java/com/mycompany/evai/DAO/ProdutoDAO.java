package com.mycompany.evai.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Produto;
import com.mycompany.evai.entidade.Restaurante;

public class ProdutoDAO {
    
    public void incluir(Produto produto, Restaurante restaurante) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos (id_restaurante, nome, descricao, preco) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           
            stmt.setInt(1, restaurante.getId()); // Id do restaurante
            stmt.setString(2, produto.getNome()); // Nome do produto
            stmt.setString(3, produto.getDescricao()); // descrição do produto
            stmt.setFloat(4, produto.getPreco()); // preço do produto

            int linhasAfetadas = stmt.executeUpdate();
            
            System.out.println("produto: " + produto.getNome() + " inserido com sucesso");

            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        produto.setId(rs.getInt(1)); // ID é salvo no objeto
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

    public void alterar(Produto produto) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET nome = ?, descricao = ?, preco = ? where id_produto = ? ");
          
            stmt.setString(1, produto.getNome()); // Nome do produto
            stmt.setString(3, produto.getDescricao()); // descrição do produto
            stmt.setFloat(2, produto.getPreco()); // preço na tabela produto
            stmt.setInt(4, produto.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("produto: " + produto.getNome() + " alterado com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException("Erro ao inserir informação no banco de dados");

        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }

    public void excluir(Produto produto) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE from produtos WHERE id_produto = ?");
           
            stmt.setInt(1, produto.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("produto: " + produto.getNome() + " excluído com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

    }

     public List<Produto> consulta(){

       Connection con = Conexao.getConexao();
       PreparedStatement stmt = null;
       
       ResultSet rs = null;
       
       
       List<Produto> produtos = new ArrayList<Produto>();
       
       
       try{
           
           stmt = con.prepareStatement("select id_produto, id_restaurante, nome, descricao, preco from produtos");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               Produto produto =  new Produto();
              
               produto.setId(rs.getInt("id_produto"));
               produto.setNome(rs.getString("nome"));
               produto.setDescricao(rs.getString("descricao"));
               produto.setPreco(rs.getFloat("preco"));
              
               
               produtos.add(produto);
               
           }
           
           
       }catch (SQLException s){
           s.printStackTrace();
           
       }
       
        finally {
            Conexao.fecharConexao(con, stmt);

        }
       
      return produtos;
       
   }
     
   public List<Produto> consultarPorRestaurante(int idRestaurante) {
    List<Produto> lista = new ArrayList<>();
    Connection con = Conexao.getConexao();
       

    try {
        String sql = "SELECT * FROM produtos WHERE id_restaurante = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idRestaurante);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id_produto"));
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getFloat("preco"));
            p.setIdRestaurante(rs.getInt("id_restaurante"));
            lista.add(p);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    System.out.println("Produtos retornados: " + lista.size());
    return lista;
  }

}
