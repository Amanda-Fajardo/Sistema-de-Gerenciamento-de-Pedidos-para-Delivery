/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.evai.Sessao;

import com.mycompany.evai.RestauranteView.*;
import com.mycompany.evai.DAO.RestauranteDAO;
import com.mycompany.evai.entidade.Restaurante;
import com.mycompany.evai.ClienteView.*;
import com.mycompany.evai.DAO.ClienteDAO;
import com.mycompany.evai.entidade.Cliente;
/**
 *
 * @author amand
 */
public class Sessao {
    private static Restaurante restauranteLogado;
    private static Cliente clienteLogado;

    public static void setRestauranteLogado(Restaurante r) {
        restauranteLogado = r;
    }

    public static Restaurante getRestauranteLogado() {
        return restauranteLogado;
    }
    
    public static void setClienteLogado(Cliente c){
        clienteLogado = c;
    }
    
    public static Cliente getClienteLogado(){
        return clienteLogado;
    }

    public static void limparSessao() {
        restauranteLogado = null;
        clienteLogado = null;
    }
}