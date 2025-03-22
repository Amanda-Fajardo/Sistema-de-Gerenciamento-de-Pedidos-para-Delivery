/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evai.App;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author va001
 */
public class App {
    public static void main(String args[]) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        Produto produto = new Produto();
        
        produto.setDescricao("descricao x");

        try {
            stmt = con.prepareStatement("INSERT INTO produto (descricao) VALUES (?)");
           
            stmt.setString(1, produto.getDescricao());

            stmt.executeUpdate();
            
            System.out.println("Curso " + produto.getDescricao() + " inserido com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();

              throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }
}
