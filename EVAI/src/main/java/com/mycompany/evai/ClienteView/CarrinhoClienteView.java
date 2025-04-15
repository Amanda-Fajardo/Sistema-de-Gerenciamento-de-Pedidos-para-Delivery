/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.evai.ClienteView;

import com.mycompany.evai.DAO.ClienteDAO;
import com.mycompany.evai.DAO.ItemPedidoDAO;
import com.mycompany.evai.DAO.PedidoDAO;
import com.mycompany.evai.DAO.RestauranteDAO;
import com.mycompany.evai.entidade.Cliente;
import com.mycompany.evai.entidade.ItemPedido;
import com.mycompany.evai.entidade.Pedido;
import com.mycompany.evai.entidade.Produto;
import com.mycompany.evai.entidade.Restaurante;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * @author erick
 */
public class CarrinhoClienteView extends javax.swing.JPanel {
    
    private int idCliente, idRestaurante;
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();;
    private double total = 0.0;

    CarrinhoClienteView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Creates new form CarrinhoClienteView
     */
    
    private class ItemCarrinho {
        Produto produto;
        int quantidade;
        
        public ItemCarrinho(Produto produto) {
            this.produto = produto;
            this.quantidade = 1;
        }
        
        public void incrementarQuantidade() {
            this.quantidade++;
        }
        
        public double getSubtotal() {
            return produto.getPreco() * quantidade;
        }
    }
    
    public CarrinhoClienteView(int idCliente, int idRestaurante) {
        this.idRestaurante = idRestaurante;
        this.idCliente = idCliente;
        initComponents();
        
        jPanel1.setLayout(new java.awt.GridLayout(0, 4));
    }
    
    
    public void adicionarProduto(Produto produto) {
        
        for (ItemCarrinho item : itensCarrinho) {
            if (item.produto.getId() == produto.getId()) {
                item.incrementarQuantidade();
                atualizarCarrinho();
                return;
            }
        }
        
        itensCarrinho.add(new ItemCarrinho(produto));
        atualizarCarrinho();
    }
     
   private void atualizarCarrinho() {
    jPanel1.removeAll();
    total = 0.0;
    
    // Configurações de estilo
    Color roxoBackground = new Color(119, 45, 139);
    Color branco = Color.WHITE;
    Font fontNormal = new Font("Segoe UI", Font.PLAIN, 14);
    Font fontBold = new Font("Segoe UI", Font.BOLD, 16);
    
    // Layout principal
    jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
    jPanel1.setBackground(roxoBackground);
    
    // Cabeçalho - usando JPanel com GridLayout
    JPanel headerPanel = new JPanel(new GridLayout(1, 4));
    headerPanel.setBackground(roxoBackground.darker());
    headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    
    String[] headers = {"PRODUTO", "QTD", "PREÇO UNIT.", "SUBTOTAL"};
    for (String header : headers) {
        JLabel label = new JLabel(header, SwingConstants.CENTER);
        label.setFont(fontBold);
        label.setForeground(branco);
        headerPanel.add(label);
    }
    jPanel1.add(headerPanel);
    
    // Itens do carrinho
    for (ItemCarrinho item : itensCarrinho) {
        JPanel itemPanel = new JPanel(new GridLayout(1, 4));
        itemPanel.setBackground(roxoBackground);
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Nome do produto (com quebra de linha se necessário)
        JLabel nomeLabel = new JLabel("<html><div style='width:150px;text-align:center'>" + 
                                    item.produto.getNome() + "</div></html>", SwingConstants.CENTER);
        nomeLabel.setFont(fontNormal);
        nomeLabel.setForeground(branco);
        
        // Quantidade
        JLabel qtdLabel = new JLabel(String.valueOf(item.quantidade), SwingConstants.CENTER);
        qtdLabel.setFont(fontNormal);
        qtdLabel.setForeground(branco);
        
        // Preço unitário
        JLabel precoLabel = new JLabel(String.format("R$ %.2f", item.produto.getPreco()), 
                                    SwingConstants.CENTER);
        precoLabel.setFont(fontNormal);
        precoLabel.setForeground(branco);
        
        // Subtotal
        JLabel subtotalLabel = new JLabel(String.format("R$ %.2f", item.getSubtotal()), 
                                       SwingConstants.CENTER);
        subtotalLabel.setFont(fontBold);
        subtotalLabel.setForeground(branco);
        
        itemPanel.add(nomeLabel);
        itemPanel.add(qtdLabel);
        itemPanel.add(precoLabel);
        itemPanel.add(subtotalLabel);
        
        jPanel1.add(itemPanel);
        
        // Adiciona linha divisória
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setForeground(new Color(160, 100, 180));
        jPanel1.add(separator);
        
        total += item.getSubtotal();
    }
    
    // Atualiza o total geral
    jLabel2.setText(String.format("TOTAL: R$ %.2f", total));
    jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
    jLabel2.setForeground(branco);
    
    jPanel1.revalidate();
    jPanel1.repaint();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        btnFinalizarPedido = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(119, 45, 139));

        label1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(229, 229, 229));
        label1.setPreferredSize(new java.awt.Dimension(67, 60));
        label1.setText("CARRINHO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(229, 229, 229));
        jLabel1.setText("ITEM");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(229, 229, 229));
        jLabel2.setText("TOTAL");

        btnFinalizarPedido.setBackground(new java.awt.Color(69, 168, 52));
        btnFinalizarPedido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnFinalizarPedido.setForeground(new java.awt.Color(229, 229, 229));
        btnFinalizarPedido.setText("Finalizar");
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(119, 45, 139));
        jPanel1.setForeground(new java.awt.Color(119, 45, 139));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFinalizarPedido)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1)
                        .addComponent(jSeparator2)
                        .addComponent(jLabel2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnFinalizarPedido)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        if (itensCarrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seu carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        try {
            // 1. Obter cliente e restaurante
            Cliente cliente = ClienteDAO.consultarPorId(this.idCliente);
            if (cliente == null) {
                throw new Exception("Cliente não encontrado");
            }
            
            System.out.println(cliente);


            Restaurante restaurante = obterRestauranteDosItens();

            // 2. Criar o pedido
            Pedido pedido = new Pedido();
            pedido.setData(LocalDate.now());
            pedido.setStatus("Pendente");

            // 3. Inserir no banco
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.incluir(pedido, restaurante, cliente);
            
             ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
        
            for (ItemCarrinho itemCarrinho : itensCarrinho) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setQuantidade(itemCarrinho.quantidade);
                itemPedido.setValorUnitario(itemCarrinho.produto.getPreco());
                itemPedido.setIdProduto(itemCarrinho.produto.getId());
                itemPedido.setIdPedido(pedido.getId());

                
                
                // Inserir o item no banco de dados
                itemPedidoDAO.incluir(itemPedido);
            }

            // 4. Limpar carrinho e mostrar mensagem de sucesso
            itensCarrinho.clear();
            atualizarCarrinho();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao finalizar pedido: " + e.getMessage(), 
                                       "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private Restaurante obterRestauranteDosItens() throws Exception {
        if (itensCarrinho == null || itensCarrinho.isEmpty()) {
            throw new Exception("Não há itens no carrinho");
        }

        int idRestaurante = itensCarrinho.get(0).produto.getIdRestaurante();

        Restaurante restaurante = RestauranteDAO.consultarPorId(idRestaurante);

        if (restaurante == null) {
            throw new Exception("Restaurante não encontrado");
        }

        return restaurante;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
