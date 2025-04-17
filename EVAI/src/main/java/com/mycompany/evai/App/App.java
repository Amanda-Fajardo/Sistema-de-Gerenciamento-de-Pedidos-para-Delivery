/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evai.App;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.evai.DAO.RestauranteDAO;
import com.mycompany.evai.entidade.Restaurante;

/**
 *
 * @author victor
 * 
 * Classe utilizada para testes iniciais
 */
public class App {
    public static void main(String args[]) {
        Restaurante restaurante = new Restaurante();
        
        restaurante.setNome("EVAI Lanches");
        restaurante.setEndereco("Rua X - 2876");
        restaurante.setTelefone("40028922");

        RestauranteDAO restauranteDAO = new RestauranteDAO();
        // restauranteDAO.incluir(restaurante);
        
        // System.out.println(restaurante.getId());

        List<Restaurante> lista = new ArrayList<Restaurante>();
        lista = restauranteDAO.consulta();

        for (Restaurante item : lista) {
            System.out.println(item.getId());
            System.out.println(item.getNome());
            System.out.println(item.getEndereco());
            System.out.println(item.getTelefone());
        }


        Restaurante removRestaurante = new Restaurante();
        removRestaurante.setId(5);
        removRestaurante.setNome("NOVO Lanches");
        removRestaurante.setEndereco("Rua Y - 0000");
        removRestaurante.setTelefone("7490873711");

       /*  restauranteDAO.excluir(removRestaurante);

        removRestaurante.setId(8);

        restauranteDAO.alterar(removRestaurante);*/

    }
}
