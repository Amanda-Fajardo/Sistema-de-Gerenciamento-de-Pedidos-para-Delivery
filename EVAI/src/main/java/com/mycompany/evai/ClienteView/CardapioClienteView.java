/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.evai.ClienteView;

import com.mycompany.evai.DAO.ProdutoDAO;
import com.mycompany.evai.entidade.Produto;
import com.mycompany.evai.entidade.Restaurante;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author amand
 */
public class CardapioClienteView extends javax.swing.JFrame {
    
    private List<Produto> produtos;
    private int paginaAtual = 0;
    private final int PRODUTOS_POR_PAGINA = 3;
    private Restaurante restaurante;

    /**
     * Creates new form CardapioCliente
     */
    public CardapioClienteView(Restaurante r) {
        initComponents();
        
        this.restaurante = r;
        
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtos = produtoDao.consultarPorRestaurante(r.getId());

        atualizarCarrossel();
        
        jLCardapio.setText("Cardápio  " + r.getNome());
        jLTelefone.setText("Telefone: " + r.getTelefone());
        jLEndereco.setText("Endereço: " + r.getEndereco());
    }
    
    public CardapioClienteView() {
    initComponents();
    // Configurações iniciais básicas
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLCardapio = new javax.swing.JLabel();
        btnCarrinho = new javax.swing.JButton();
        btnMeusPedidos = new javax.swing.JButton();
        jLTelefone = new javax.swing.JLabel();
        jLEndereco = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();
        btnAtualizarCardapio = new javax.swing.JButton();
        jBVoltar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelCarrossel = new javax.swing.JPanel();
        pnlProduto1 = new javax.swing.JPanel();
        lbFoto = new javax.swing.JLabel();
        lbDescricao = new javax.swing.JLabel();
        lbPreco = new javax.swing.JLabel();
        btnAddCarrinho = new javax.swing.JButton();
        pnlProduto2 = new javax.swing.JPanel();
        lbFoto2 = new javax.swing.JLabel();
        lbDescricao2 = new javax.swing.JLabel();
        lbPreco2 = new javax.swing.JLabel();
        btnAddCarrinho2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbFoto3 = new javax.swing.JLabel();
        lbDescricao3 = new javax.swing.JLabel();
        lbPreco3 = new javax.swing.JLabel();
        btnAddCarrinho3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cardápio");
        setPreferredSize(new java.awt.Dimension(1219, 1083));

        jLCardapio.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLCardapio.setText("Cardápio");

        btnCarrinho.setText("Carrinho");
        btnCarrinho.setPreferredSize(new java.awt.Dimension(129, 23));
        btnCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarrinhoActionPerformed(evt);
            }
        });

        btnMeusPedidos.setText("Meus Pedidos");
        btnMeusPedidos.setPreferredSize(new java.awt.Dimension(129, 23));
        btnMeusPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusPedidosActionPerformed(evt);
            }
        });

        btnAnterior.setText("<<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnProximo.setText(">>");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnAtualizarCardapio.setText("Atualizar Cardápio");
        btnAtualizarCardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarCardapioActionPerformed(evt);
            }
        });

        jBVoltar.setText("<<");
        jBVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVoltarActionPerformed(evt);
            }
        });

        jPanel2.setMinimumSize(new java.awt.Dimension(110, 351));
        jPanel2.setPreferredSize(new java.awt.Dimension(1219, 400));

        panelCarrossel.setLayout(new java.awt.GridLayout(1, 3, 50, 0));

        pnlProduto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbFoto.setText("jLabel2");

        lbDescricao.setText("jLabel2");

        lbPreco.setText("jLabel2");

        btnAddCarrinho.setText("jButton1");
        btnAddCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarrinhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProduto1Layout = new javax.swing.GroupLayout(pnlProduto1);
        pnlProduto1.setLayout(pnlProduto1Layout);
        pnlProduto1Layout.setHorizontalGroup(
            pnlProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduto1Layout.createSequentialGroup()
                .addGroup(pnlProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduto1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(lbFoto))
                    .addGroup(pnlProduto1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPreco)
                            .addComponent(lbDescricao)))
                    .addGroup(pnlProduto1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnAddCarrinho)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        pnlProduto1Layout.setVerticalGroup(
            pnlProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduto1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbFoto)
                .addGap(124, 124, 124)
                .addComponent(lbDescricao)
                .addGap(18, 18, 18)
                .addComponent(lbPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnAddCarrinho)
                .addGap(35, 35, 35))
        );

        panelCarrossel.add(pnlProduto1);

        pnlProduto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbFoto2.setText("jLabel2");

        lbDescricao2.setText("jLabel2");

        lbPreco2.setText("jLabel2");

        btnAddCarrinho2.setText("jButton1");

        javax.swing.GroupLayout pnlProduto2Layout = new javax.swing.GroupLayout(pnlProduto2);
        pnlProduto2.setLayout(pnlProduto2Layout);
        pnlProduto2Layout.setHorizontalGroup(
            pnlProduto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduto2Layout.createSequentialGroup()
                .addGroup(pnlProduto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProduto2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lbFoto2))
                    .addGroup(pnlProduto2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlProduto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPreco2)
                            .addComponent(lbDescricao2)))
                    .addGroup(pnlProduto2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnAddCarrinho2)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        pnlProduto2Layout.setVerticalGroup(
            pnlProduto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduto2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbFoto2)
                .addGap(128, 128, 128)
                .addComponent(lbDescricao2)
                .addGap(18, 18, 18)
                .addComponent(lbPreco2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnAddCarrinho2)
                .addGap(40, 40, 40))
        );

        panelCarrossel.add(pnlProduto2);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbFoto3.setText("jLabel2");

        lbDescricao3.setText("jLabel3");

        lbPreco3.setText("jLabel4");

        btnAddCarrinho3.setText("jButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lbFoto3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPreco3)
                            .addComponent(lbDescricao3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnAddCarrinho3)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lbFoto3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(lbDescricao3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPreco3)
                .addGap(55, 55, 55)
                .addComponent(btnAddCarrinho3)
                .addGap(33, 33, 33))
        );

        panelCarrossel.add(jPanel1);

        jPanel2.add(panelCarrossel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jBVoltar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLCardapio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMeusPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizarCardapio)
                        .addGap(64, 64, 64))))
            .addGroup(layout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addComponent(btnAnterior)
                .addGap(274, 274, 274)
                .addComponent(btnProximo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1702, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBVoltar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLCardapio)
                        .addComponent(btnCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMeusPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtualizarCardapio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnProximo))
                .addGap(507, 507, 507))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarrinhoActionPerformed
        // TODO add your handling code here:     
        CarrinhoClienteView painel = new CarrinhoClienteView();
    
        JFrame frame = new JFrame("Carrinho de Compras");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setContentPane(painel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }//GEN-LAST:event_btnCarrinhoActionPerformed

    private void btnMeusPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeusPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMeusPedidosActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (paginaAtual > 0) {
            paginaAtual--;
            atualizarCarrossel();
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnAddCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarrinhoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnAddCarrinhoActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        // TODO add your handling code here:
        if ((paginaAtual + 1) * PRODUTOS_POR_PAGINA < produtos.size()) {
            paginaAtual++;
            atualizarCarrossel();
        }
    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnAtualizarCardapioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarCardapioActionPerformed
        // TODO add your handling code here:
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtos = produtoDao.consultarPorRestaurante(restaurante.getId());
        paginaAtual = 0;
        atualizarCarrossel();
    }//GEN-LAST:event_btnAtualizarCardapioActionPerformed

    private void jBVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVoltarActionPerformed
        // TODO add your handling code here:
         dispose();
         new TelaInicialCliente().setVisible(true);
    }//GEN-LAST:event_jBVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(CardapioClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardapioClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardapioClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardapioClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Restaurante r = null;
                new CardapioClienteView(r).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCarrinho;
    private javax.swing.JButton btnAddCarrinho2;
    private javax.swing.JButton btnAddCarrinho3;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAtualizarCardapio;
    private javax.swing.JButton btnCarrinho;
    private javax.swing.JButton btnMeusPedidos;
    private javax.swing.JButton btnProximo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBVoltar;
    private javax.swing.JLabel jLCardapio;
    private javax.swing.JLabel jLEndereco;
    private javax.swing.JLabel jLTelefone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDescricao;
    private javax.swing.JLabel lbDescricao2;
    private javax.swing.JLabel lbDescricao3;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbFoto2;
    private javax.swing.JLabel lbFoto3;
    private javax.swing.JLabel lbPreco;
    private javax.swing.JLabel lbPreco2;
    private javax.swing.JLabel lbPreco3;
    private javax.swing.JPanel panelCarrossel;
    private javax.swing.JPanel pnlProduto1;
    private javax.swing.JPanel pnlProduto2;
    // End of variables declaration//GEN-END:variables

    private void atualizarCarrossel() {
        panelCarrossel.removeAll();

        int inicio = paginaAtual * PRODUTOS_POR_PAGINA;
        int fim = Math.min(inicio + PRODUTOS_POR_PAGINA, produtos.size());

        for (int i = inicio; i < fim; i++) {
            Produto p = produtos.get(i);
            JPanel card = criarCardProduto(p);
            panelCarrossel.add(card);
        }

        panelCarrossel.revalidate();
        panelCarrossel.repaint();
    }
    
    private JPanel criarCardProduto(Produto produto) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setBackground(new Color(245, 245, 245));

        card.setPreferredSize(new Dimension(250, 300));
        card.setMaximumSize(new Dimension(250, 300));

        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblImagem = new JLabel(new ImageIcon("/com/mycompany/evai/ClienteView/assets/placeholder.png")); 
        lblImagem.setPreferredSize(new Dimension(150, 150));
        lblImagem.setMaximumSize(new Dimension(150, 150));
        lblImagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNome = new JLabel(produto.getNome());
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDesc = new JLabel("<html><center>" + produto.getDescricao() + "</center></html>");
        lblDesc.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel lblPreco = new JLabel(String.format("R$ %.2f", produto.getPreco()));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 16));

        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPreco.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCarrinho = new JButton("Adicionar ao carrinho");
        btnCarrinho.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblImagem);
        card.add(Box.createVerticalStrut(15));
        card.add(lblNome);
        card.add(Box.createVerticalStrut(10));
        card.add(lblDesc);
        card.add(Box.createVerticalStrut(15));
        card.add(lblPreco);
        card.add(Box.createVerticalStrut(15));
        card.add(btnCarrinho);

        return card;
    }
}
