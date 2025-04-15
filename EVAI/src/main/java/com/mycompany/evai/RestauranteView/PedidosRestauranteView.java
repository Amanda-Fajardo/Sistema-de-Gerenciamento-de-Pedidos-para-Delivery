/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.evai.RestauranteView;

import com.mycompany.evai.DAO.*;
import com.mycompany.evai.entidade.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author erick
 */
public class PedidosRestauranteView extends javax.swing.JFrame {

    /**
     * Creates new form PedidosRestauranteView
     */
    
    private final int idRestaurante;
    private final PedidoDAO pedidoDAO;
    private final ItemPedidoDAO itemPedidoDAO;
    private final ProdutoDAO produtoDAO;
    private final ClienteDAO clienteDAO;

    
    public PedidosRestauranteView(int idRestaurante) {
        
        if (idRestaurante <= 0) {
            JOptionPane.showMessageDialog(null, "ID de restaurante inválido!");
            System.exit(0);
        }
        
        this.idRestaurante = idRestaurante;
        this.pedidoDAO = new PedidoDAO();
        this.itemPedidoDAO = new ItemPedidoDAO();
        this.produtoDAO = new ProdutoDAO();
        this.clienteDAO = new ClienteDAO();
        
        initComponents();
        configurarTabelas();
        carregarPedidos();
        this.setTitle("Gerenciador de Pedidos - Restaurante ID: " + idRestaurante);
        System.out.println("Iniciando para restaurante ID: " + idRestaurante);

    }
    
     // Função para configurar as tabelas
    private void configurarTabelas() {
        String[] colunas = {"ID", "Cliente", "Itens", "Total", "Data", "Status", "Ação"};
        int[] larguras = {50, 150, 250, 80, 150, 100, 150}; // Aumentei a largura para acomodar melhor os botões

        for (JTable tabela : new JTable[]{jTablePendentes, jTableAndamento, jTableACaminho}) {
            // Cria o modelo da tabela
            DefaultTableModel model = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 6; // Apenas a coluna de ação é editável
                }

                @Override
                public Class<?> getColumnClass(int column) {
                    // Alterado para JPanel pois agora usamos painéis para agrupar botões
                    return column == 6 ? JPanel.class : Object.class;
                }
            };

            tabela.setModel(model);
            tabela.setRowHeight(40); // Aumentei a altura para melhor visualização
            tabela.setIntercellSpacing(new Dimension(5, 5)); // Adiciona espaçamento entre células

            // Configura o renderizador para células que contêm JPanel (botões)
            tabela.setDefaultRenderer(JPanel.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof JPanel) {
                        return (JPanel) value;
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, 
                            hasFocus, row, column);
                }
            });

            // Configura as colunas
            TableColumnModel columnModel = tabela.getColumnModel();
            for (int i = 0; i < colunas.length; i++) {
                TableColumn coluna = columnModel.getColumn(i);
                coluna.setPreferredWidth(larguras[i]);

                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                // Centraliza colunas ID, Data e Status
                if (i == 0 || i == 4 || i == 5) {
                    renderer.setHorizontalAlignment(SwingConstants.CENTER);
                } 
                // Alinha valores monetários à direita
                else if (i == 3) {
                    renderer.setHorizontalAlignment(SwingConstants.RIGHT);
                }
                coluna.setCellRenderer(renderer);
            }

            // Remove o editor padrão para evitar problemas com os botões
            tabela.setDefaultEditor(JPanel.class, null);
        }

        // Adiciona listener para duplo clique (mantido da versão original)
        MouseListener tableListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable table = (JTable) e.getSource();
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        int pedidoId = (int) table.getValueAt(row, 0);
                        carregarItensPedido(pedidoId);
                    }
                }
            }
        };

        jTablePendentes.addMouseListener(tableListener);
        jTableAndamento.addMouseListener(tableListener);
        jTableACaminho.addMouseListener(tableListener);
    }
        
        // Adiciona listener para duplo clique
        MouseListener tableListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable table = (JTable) e.getSource();
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        int pedidoId = (int) table.getValueAt(row, 0);
                        carregarItensPedido(pedidoId);
                    }
                }
            }
        };
        
        jTablePendentes.addMouseListener(tableListener);
        jTableAndamento.addMouseListener(tableListener);
        jTableACaminho.addMouseListener(tableListener);
    }

    private void carregarPedidos() {
    List<Pedido> todosPedidos = pedidoDAO.consulta();
    List<ItemPedido> todosItens = itemPedidoDAO.consulta();
    List<Produto> produtos = produtoDAO.consultarPorRestaurante(idRestaurante);

    List<Pedido> pedidosRestaurante = todosPedidos.stream()
            .filter(p -> p.getIdRestaurante() == idRestaurante)
            .collect(Collectors.toList());

    // Filtra os pedidos por status
    List<Pedido> pendentes = pedidosRestaurante.stream()
            .filter(p -> "pendente".equalsIgnoreCase(p.getStatus().split("\\|")[0]))
            .collect(Collectors.toList());

    List<Pedido> emAndamento = pedidosRestaurante.stream()
            .filter(p -> "preparando".equalsIgnoreCase(p.getStatus().split("\\|")[0]))
            .collect(Collectors.toList());

    List<Pedido> aCaminho = pedidosRestaurante.stream()
            .filter(p -> "a caminho".equalsIgnoreCase(p.getStatus().split("\\|")[0]))
            .collect(Collectors.toList());

    // Preenche cada aba com o nome correspondente
    preencherTabela(jTablePendentes, pendentes, todosItens, produtos, "Pendentes");
    preencherTabela(jTableAndamento, emAndamento, todosItens, produtos, "Em Andamento");
    preencherTabela(jTableACaminho, aCaminho, todosItens, produtos, "A Caminho");
}
    
    private List<Pedido> filtrarPedidos(List<Pedido> pedidos, String status) {
        return pedidos.stream()
                .filter(p -> p.getStatus().equalsIgnoreCase(status) && 
                             p.getIdRestaurante() == idRestaurante)
                .collect(Collectors.toList());
    }

    private void preencherTabela(JTable tabela, List<Pedido> pedidos, 
                    List<ItemPedido> itens, List<Produto> produtos, String aba) {
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0);

    DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    for (Pedido pedido : pedidos) {
        // Se o pedido estiver cancelado ou entregue, não exibe
        if (pedido.getStatus().startsWith("cancelado") || pedido.getStatus().startsWith("entregue")) {
            continue;
        }

        String[] partesStatus = pedido.getStatus().split("\\|");
        String status = partesStatus[0];
        String hora = partesStatus.length > 1 ? partesStatus[1] : "--:--";

        Cliente cliente = clienteDAO.consultarPorId(pedido.getIdCliente());
        String nomeCliente = cliente != null ? cliente.getNome() : "Cliente não encontrado";

        String itensPedido = itens.stream()
                .filter(i -> i.getIdPedido() == pedido.getId())
                .map(item -> produtos.stream()
                        .filter(p -> p.getId() == item.getIdProduto())
                        .findFirst()
                        .map(p -> item.getQuantidade() + "x " + p.getNome())
                        .orElse("Produto indisponível"))
                .collect(Collectors.joining(", "));

        double total = itens.stream()
                .filter(i -> i.getIdPedido() == pedido.getId())
                .mapToDouble(i -> i.getQuantidade() * i.getValorUnitario())
                .sum();

        // Cria o botão com ação específica para cada aba
        JButton btnAcao = new JButton();
        btnAcao.setPreferredSize(new Dimension(120, 25));
        
        if (aba.equals("Pendentes")) {
            btnAcao.setText("Aceitar Pedido");
            btnAcao.setBackground(new Color(144, 238, 144)); // Verde claro
            btnAcao.addActionListener(e -> avancarStatusPedido(tabela, pedido, "preparando"));
            
            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.setBackground(new Color(255, 99, 71)); // Vermelho claro
            btnCancelar.addActionListener(e -> cancelarPedido(tabela, pedido));
            
            // Usamos um painel para agrupar os botões
            JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            panelBotoes.add(btnAcao);
            panelBotoes.add(btnCancelar);
            
            model.addRow(new Object[]{
                pedido.getId(),
                nomeCliente,
                itensPedido,
                String.format("R$ %.2f", total),
                pedido.getData().format(dtfData),
                status,
                panelBotoes
            });
        } 
        else if (aba.equals("Em Andamento")) {
            btnAcao.setText("Pronto para Entrega");
            btnAcao.setBackground(new Color(100, 149, 237)); // Azul claro
            btnAcao.addActionListener(e -> avancarStatusPedido(tabela, pedido, "a caminho"));
            
            model.addRow(new Object[]{
                pedido.getId(),
                nomeCliente,
                itensPedido,
                String.format("R$ %.2f", total),
                pedido.getData().format(dtfData),
                status,
                btnAcao
            });
        }
        else if (aba.equals("A Caminho")) {
            btnAcao.setText("Marcar como Entregue");
            btnAcao.setBackground(new Color(152, 251, 152)); // Verde mais forte
            btnAcao.addActionListener(e -> avancarStatusPedido(tabela, pedido, "entregue"));
            
            model.addRow(new Object[]{
                pedido.getId(),
                nomeCliente,
                itensPedido,
                String.format("R$ %.2f", total),
                pedido.getData().format(dtfData),
                status,
                btnAcao
            });
        }
    }
}
    
    private void acaoPedidoPendente(JTable tabela, Pedido pedido) {
        Object[] options = {"Aceitar", "Cancelar", "Manter"};
        int escolha = JOptionPane.showOptionDialog(
            this,
            "Escolha uma ação para o pedido #" + pedido.getId(),
            "Ação para Pedido Pendente",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (escolha == 0) { // Aceitar
            avancarStatusPedido(tabela, pedido, "preparando");
        } else if (escolha == 1) { // Cancelar
            cancelarPedido(tabela, pedido);
        }
        // Se escolher "Manter" ou fechar, não faz nada
    }

    private void avancarStatusPedido(JTable tabela, Pedido pedido, String novoStatus) {
    pedido.setStatus(novoStatus);
    pedidoDAO.alterar(pedido, null, null);
    
    // Atualiza a tabela
    carregarPedidos();
    JOptionPane.showMessageDialog(this, "Pedido #" + pedido.getId() + " atualizado para: " + novoStatus);
}

private void cancelarPedido(JTable tabela, Pedido pedido) {
    int confirmacao = JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente cancelar o pedido #" + pedido.getId() + "?",
        "Confirmar Cancelamento",
        JOptionPane.YES_NO_OPTION
    );
    
    if (confirmacao == JOptionPane.YES_OPTION) {
        pedido.setStatus("cancelado");
        pedidoDAO.alterar(pedido, null, null);
        carregarPedidos();
        JOptionPane.showMessageDialog(this, "Pedido #" + pedido.getId() + " cancelado com sucesso!");
    }
}

    private void carregarItensPedido(int pedidoId) {
        List<Produto> produtos = produtoDAO.consultarPorRestaurante(idRestaurante);
        List<ItemPedido> itens = itemPedidoDAO.consulta().stream()
                .filter(i -> i.getIdPedido() == pedidoId)
                .collect(Collectors.toList());

        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Produto", "Quantidade", "Preço Unitário", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        double totalPedido = 0.0;

        for (ItemPedido item : itens) {
            Produto produto = produtos.stream()
                .filter(p -> p.getId() == item.getIdProduto())
                .findFirst()
                .orElse(null);

            if (produto != null) {
                double subtotal = item.getQuantidade() * item.getValorUnitario();
                totalPedido += subtotal;

                model.addRow(new Object[]{
                    produto.getNome(),
                    item.getQuantidade(),
                    String.format("R$ %.2f", item.getValorUnitario()),
                    String.format("R$ %.2f", subtotal)
                });
            }
        }
    
        model.addRow(new Object[]{"", "", "Total:", String.format("R$ %.2f", totalPedido)});
        exibirDetalhesPedido(model, pedidoId);
    }
    
    private void exibirDetalhesPedido(DefaultTableModel model, int pedidoId) {
        JFrame frameDetalhes = new JFrame("Detalhes do Pedido #" + pedidoId);
        frameDetalhes.setSize(500, 400);
        frameDetalhes.setLocationRelativeTo(this);
        
        JTable tabelaDetalhes = new JTable(model);
        tabelaDetalhes.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaDetalhes.setRowHeight(25);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        tabelaDetalhes.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tabelaDetalhes.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tabelaDetalhes.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        
        JScrollPane scrollPane = new JScrollPane(tabelaDetalhes);
        frameDetalhes.add(scrollPane);
        frameDetalhes.setVisible(true);
    }
    
    private void cancelarPedido(JTable tabela, int row) {
        int idPedido = (int) tabela.getValueAt(row, 0);
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente cancelar o pedido #" + idPedido + "?",
            "Confirmar Cancelamento",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            Pedido pedido = new Pedido();
            pedido.setId(idPedido);
            pedido.setStatus("cancelado");

            try {
                pedidoDAO.alterar(pedido, null, null); // Passamos null para cliente e restaurante pois só queremos alterar o status
                JOptionPane.showMessageDialog(this, "Pedido cancelado com sucesso!");
                carregarPedidos(); // Atualiza a tabela
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cancelar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

 
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        spEmAndamento = new javax.swing.JScrollPane();
        jTableAndamento = new javax.swing.JTable();
        spACaminho = new javax.swing.JScrollPane();
        jTableACaminho = new javax.swing.JTable();
        spPendentes = new javax.swing.JScrollPane();
        jTablePendentes = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("PEDIDOS");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableAndamento.setModel(new javax.swing.table.DefaultTableModel(
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
        spEmAndamento.setViewportView(jTableAndamento);

        jTabbedPane1.addTab("Em Andamento", spEmAndamento);

        jTableACaminho.setModel(new javax.swing.table.DefaultTableModel(
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
        spACaminho.setViewportView(jTableACaminho);

        jTabbedPane1.addTab("A Caminho", spACaminho);

        jTablePendentes.setModel(new javax.swing.table.DefaultTableModel(
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
        spPendentes.setViewportView(jTablePendentes);

        jTabbedPane1.addTab("Pendentes", spPendentes);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(btnAtualizar)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        carregarPedidos();
        JOptionPane.showMessageDialog(this, "Pedidos atualizados com sucesso!");
    }//GEN-LAST:event_btnAtualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(PedidosRestauranteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(PedidosRestauranteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(PedidosRestauranteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(PedidosRestauranteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        // Modificação principal: solicita o ID do restaurante via input
        String input = JOptionPane.showInputDialog("Digite o ID do restaurante para teste:");
        try {
            int idRestaurante = Integer.parseInt(input);
            new PedidosRestauranteView(idRestaurante).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido! Digite um número.");
            System.exit(0);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableACaminho;
    private javax.swing.JTable jTableAndamento;
    private javax.swing.JTable jTablePendentes;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spACaminho;
    private javax.swing.JScrollPane spEmAndamento;
    private javax.swing.JScrollPane spPendentes;
    // End of variables declaration//GEN-END:variables
}
