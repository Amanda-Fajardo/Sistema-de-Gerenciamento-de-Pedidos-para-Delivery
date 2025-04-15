/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.evai.ClienteView;

import com.mycompany.evai.DAO.ItemPedidoDAO;
import com.mycompany.evai.DAO.PedidoDAO;
import com.mycompany.evai.DAO.ProdutoDAO;
import com.mycompany.evai.entidade.ItemPedido;
import com.mycompany.evai.entidade.Pedido;
import com.mycompany.evai.entidade.Produto;
import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author amand
 */
public class PedidosClienteView extends javax.swing.JFrame {

    /**
     * Creates new form PedidosClientePendenteView
     */
    
    private int idCliente;
    private int idRestaurante;
    private ProdutoDAO produtoDAO;
    private PedidoDAO pedidoDAO;
    private ItemPedidoDAO itemPedidoDAO;
    
    public PedidosClienteView(int idCliente, int idRestaurante) {
        this.idCliente = idCliente;
        this.idRestaurante = idRestaurante;
        this.produtoDAO = new ProdutoDAO();
        this.pedidoDAO = new PedidoDAO();
        this.itemPedidoDAO = new ItemPedidoDAO();
        

        initComponents();
        configurarTabelas();
        carregarPedidos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        spPendentes = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        spEmAndamento = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        spACaminho = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        lbPedidos = new javax.swing.JLabel();
        jbAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 204));

        jPanel1.setBackground(new java.awt.Color(119, 45, 139));

        jTabbedPane1.setBackground(new java.awt.Color(255, 153, 0));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spPendentes.setViewportView(jTable1);

        jTabbedPane1.addTab("Pendentes", spPendentes);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spEmAndamento.setViewportView(jTable3);

        jTabbedPane1.addTab("Em Andamento", spEmAndamento);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spACaminho.setViewportView(jTable4);

        jTabbedPane1.addTab("A Caminho", spACaminho);

        lbPedidos.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lbPedidos.setForeground(new java.awt.Color(255, 255, 255));
        lbPedidos.setText("Pedidos");

        jbAtualizar.setBackground(new java.awt.Color(255, 153, 0));
        jbAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        jbAtualizar.setText("Atualizar");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(lbPedidos)
                            .addGap(630, 630, 630)
                            .addComponent(jbAtualizar))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPedidos)
                        .addComponent(jbAtualizar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        // TODO add your handling code here:
        carregarPedidos();
    }//GEN-LAST:event_jbAtualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidosClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosClienteView(idCliente, idRestaurante).setVisible(true);
            }
        });
    }
    
    private void configurarTabelas() {
        String[] colunas = {"Produto", "Quantidade", "Preço Unit.", "Subtotal"};

        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(model);  // Tabela de pedidos pendentes
        jTable3.setModel(new DefaultTableModel(colunas, 0));  // Tabela de pedidos em andamento
        jTable4.setModel(new DefaultTableModel(colunas, 0));  // Tabela de pedidos a caminho
    }
    
    private void carregarPedidos() {
        // Pedidos Pendentes
        preencherTabela(jTable1, pedidoDAO.consultarPorClienteERestauranteEStatus(
        idCliente, idRestaurante, "pendente"), "Pendente");
    
        preencherTabela(jTable3, pedidoDAO.consultarPorClienteERestauranteEStatus(
        idCliente, idRestaurante, "preparando"), "Preparando");

        preencherTabela(jTable4, pedidoDAO.consultarPorClienteERestauranteEStatus(
        idCliente, idRestaurante, "a caminho"), "A Caminho"); // Chamada adicionada
        

    }

    
    private void preencherTabela(JTable tabela, List<Pedido> pedidos, String status) {
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0);

    for (Pedido pedido : pedidos) {
        // Adiciona cabeçalho do pedido
        model.addRow(new Object[]{
            "Pedido #" + pedido.getId() + " - " + status,
            "", "", ""
        });

        List<ItemPedido> itens = itemPedidoDAO.consultarPorPedido(pedido.getId());
        double totalPedido = 0;

        for (ItemPedido item : itens) {
            Produto produto = produtoDAO.consultarPorId(item.getIdProduto());
            String nomeProduto = produto != null ? produto.getNome() : "Produto não encontrado";
            double subtotal = item.getQuantidade() * item.getValorUnitario();
            totalPedido += subtotal;

            model.addRow(new Object[]{
                "   " + nomeProduto, // Indentação para itens
                item.getQuantidade(),
                String.format("R$ %.2f", item.getValorUnitario()),
                String.format("R$ %.2f", subtotal)
            });
        }

        // Adiciona total do pedido
        model.addRow(new Object[]{
            "Total do Pedido:",
            "", "",
            String.format("R$ %.2f", totalPedido)
        });

        // Espaçamento entre pedidos
        model.addRow(new Object[]{"", "", "", ""});
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JLabel lbPedidos;
    private javax.swing.JScrollPane spACaminho;
    private javax.swing.JScrollPane spEmAndamento;
    private javax.swing.JScrollPane spPendentes;
    // End of variables declaration//GEN-END:variables
}
