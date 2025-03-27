/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evai.App;

import com.mycompany.evai.conexao.Conexao;
import com.mycompany.evai.entidade.Restaurante;
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
        Restaurante restaurante = new Restaurante();
        
        restaurante.setNome("EVAI Lanches");
        restaurante.setEndereco("Rua X - 2876");
        restaurante.setTelefone("40028922");

        try {
            stmt = con.prepareStatement("INSERT INTO restaurantes (nome, endereco, telefone) VALUES (?, ?, ?)");


            stmt.setString(1, restaurante.getNome()); // Nome do restaurante
            stmt.setString(2, restaurante.getEndereco()); // Seta o endereço na tabela restaurante
            stmt.setString(3, restaurante.getTelefone()); // Telefone do restaurante

            stmt.executeUpdate();
            
            System.out.println("Restaurante:  " + restaurante.getNome() + " inserido com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();

              throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }
}
