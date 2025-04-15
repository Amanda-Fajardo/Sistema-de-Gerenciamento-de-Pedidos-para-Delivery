package com.mycompany.evai.RestauranteView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mycompany.evai.DAO.ClienteDAO;
import com.mycompany.evai.DAO.ItemPedidoDAO;
import com.mycompany.evai.DAO.PedidoDAO;
import com.mycompany.evai.DAO.ProdutoDAO;
import com.mycompany.evai.entidade.Cliente;
import com.mycompany.evai.entidade.ItemPedido;
import com.mycompany.evai.entidade.Pedido;
import com.mycompany.evai.entidade.Produto;

public class PedidosRestauranteView extends javax.swing.JFrame {
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

        System.out.println("Chamando initComponents...");
        initComponents();

        // Ajusta os modelos das tabelas após o initComponents
        configurarTabelas();

        // Configura os botões
        jButton1.addActionListener(e -> avancarStatusSelecionado());
        btnAtualizar.addActionListener(e -> carregarPedidos());

        System.out.println("Chamando carregarPedidos...");
        carregarPedidos();

        this.setTitle("Gerenciador de Pedidos - Restaurante ID: " + idRestaurante);
        this.setLocationRelativeTo(null); // Centraliza a janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Garante que a janela fecha o app
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println("Construtor finalizado, janela deve aparecer agora!");
    }

    private void configurarTabelas() {
        // Nomes das colunas para as abas "Pendentes" e "Em Andamento"
        String[] colunasPendentesEAndamento = {"ID", "Cliente", "Itens", "Total", "Data", "Status"};
        // Nomes das colunas para a aba "A Caminho" (sem a coluna "Ação")
        String[] colunasACaminho = {"ID", "Cliente", "Itens", "Total", "Data", "Status"};

        int[] larguras = {50, 150, 250, 80, 150, 100}; // Larguras ajustadas para 6 colunas

        // Configura as tabelas "Pendentes" e "Em Andamento"
        configurarModeloTabela(jTablePendentes, colunasPendentesEAndamento, larguras);
        configurarModeloTabela(jTableAndamento, colunasPendentesEAndamento, larguras);

        // Configura a tabela "A Caminho" sem a coluna "Ação"
        configurarModeloTabela(jTableACaminho, colunasACaminho, larguras);
    }

    private void configurarModeloTabela(JTable tabela, String[] colunas, int[] larguras) {
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula é editável
            }
        };
        tabela.setModel(model);
        tabela.setRowHeight(40);
        tabela.setIntercellSpacing(new Dimension(5, 5));

        TableColumnModel columnModel = tabela.getColumnModel();
        for (int i = 0; i < colunas.length; i++) {
            TableColumn coluna = columnModel.getColumn(i);
            coluna.setPreferredWidth(larguras[i]);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            if (i == 0 || i == 4 || i == 5) renderer.setHorizontalAlignment(SwingConstants.CENTER);
            else if (i == 3) renderer.setHorizontalAlignment(SwingConstants.RIGHT);
            coluna.setCellRenderer(renderer);
        }
    }

    private void carregarPedidos() {
        List<Pedido> todosPedidos = pedidoDAO.consulta();
        List<ItemPedido> todosItens = itemPedidoDAO.consulta();
        List<Produto> produtos = produtoDAO.consultarPorRestaurante(idRestaurante);

        List<Pedido> pedidosRestaurante = todosPedidos.stream()
                .filter(p -> p.getIdRestaurante() == idRestaurante)
                .collect(Collectors.toList());

        preencherTabela(jTablePendentes, filtrarPedidosPorStatus(pedidosRestaurante, "pendente"), todosItens, produtos, "Pendentes");
        preencherTabela(jTableAndamento, filtrarPedidosPorStatus(pedidosRestaurante, "preparando"), todosItens, produtos, "Em Andamento");
        preencherTabela(jTableACaminho, filtrarPedidosPorStatus(pedidosRestaurante, "a caminho"), todosItens, produtos, "A Caminho");
    }

    private List<Pedido> filtrarPedidosPorStatus(List<Pedido> pedidos, String status) {
        return pedidos.stream()
                .filter(p -> status.equalsIgnoreCase(p.getStatus().split("\\|")[0]))
                .collect(Collectors.toList());
    }

    private void preencherTabela(JTable tabela, List<Pedido> pedidos, 
                                 List<ItemPedido> itens, List<Produto> produtos, String aba) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd/MM/yy");

        for (Pedido pedido : pedidos) {
            if (pedido.getStatus().startsWith("cancelado") || pedido.getStatus().startsWith("entregue")) continue;

            String[] partesStatus = pedido.getStatus().split("\\|");
            String status = partesStatus[0];

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

            model.addRow(new Object[]{
                pedido.getId(),
                nomeCliente,
                itensPedido,
                String.format("R$ %.2f", total),
                pedido.getData().format(dtfData),
                status
            });
        }
    }

    private void avancarStatusPedido(Pedido pedido, String novoStatus, String mensagem) {
        pedido.setStatus(novoStatus);
        pedidoDAO.atualizarStatus(pedido.getId(), novoStatus); // Atualiza o status no banco de dados
        carregarPedidos(); // Recarrega as tabelas para refletir a mudança
        JOptionPane.showMessageDialog(this, mensagem); // Exibe o popup de confirmação
    }

    private void cancelarPedido(Pedido pedido) {
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

    private void avancarStatusSelecionado() {
        JTable tabelaAtual = null;
        String statusAtual = null;
        String proximoStatus = null;
        String mensagem = null;

        // Identifica a aba atual e configura os valores correspondentes
        int abaSelecionada = jTabbedPane1.getSelectedIndex();
        if (abaSelecionada == 0) { // Aba "Pendentes"
            tabelaAtual = jTablePendentes;
            statusAtual = "pendente";
            proximoStatus = "preparando";
            mensagem = "Pedido está em preparo!";
        } else if (abaSelecionada == 1) { // Aba "Em Andamento"
            tabelaAtual = jTableAndamento;
            statusAtual = "preparando";
            proximoStatus = "a caminho";
            mensagem = "Pedido saiu para entrega!";
        } else if (abaSelecionada == 2) { // Aba "A Caminho"
            tabelaAtual = jTableACaminho;
            statusAtual = "a caminho";
            proximoStatus = "entregue";
            mensagem = "Pedido foi finalizado!";
        }

        // Verifica se há uma tabela válida e uma linha selecionada
        if (tabelaAtual != null) {
            int linhaSelecionada = tabelaAtual.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int pedidoId = (int) tabelaAtual.getValueAt(linhaSelecionada, 0);

                // Atualiza o status do pedido
                Pedido pedido = pedidoDAO.consultarPorId(pedidoId);
                if (pedido != null && pedido.getStatus().equalsIgnoreCase(statusAtual)) {
                    avancarStatusPedido(pedido, proximoStatus, "Pedido #" + pedidoId + " " + mensagem);
                } else {
                    JOptionPane.showMessageDialog(this, "O status do pedido não pode ser alterado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um pedido para avançar o status!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e) {
        super.processWindowEvent(e);
        btnAtualizar.addActionListener(ev -> carregarPedidos());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            // Obtém o ID do restaurante do LoginRestauranteView
            int idRestaurante = LoginRestauranteView.getIdRestauranteLogado(); // Método fictício para obter o ID
            if (idRestaurante <= 0) {
                JOptionPane.showMessageDialog(null, "Erro ao obter o ID do restaurante. Verifique o login.");
                System.exit(0);
            }

            System.out.println("Abrindo tela PedidosRestauranteView...");
            Toolkit.getDefaultToolkit().beep();
            new PedidosRestauranteView(idRestaurante).setVisible(true);
        });
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPedidos = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        spPendentes = new javax.swing.JScrollPane();
        jTablePendentes = new javax.swing.JTable();
        spEmAndamento = new javax.swing.JScrollPane();
        jTableAndamento = new javax.swing.JTable();
        spACaminho = new javax.swing.JScrollPane();
        jTableACaminho = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        lblPedidos.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblPedidos.setForeground(new java.awt.Color(255, 255, 255));
        lblPedidos.setText("Pedidos");

        jTabbedPane1.setBackground(new java.awt.Color(255, 153, 0));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        btnAtualizar.setText("Atualizar");

        jButton1.setText("Avançar Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(53, 53, 53)
                .addComponent(btnAtualizar)
                .addGap(343, 343, 343))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(lblPedidos)
                    .addContainerGap(719, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar)
                    .addComponent(jButton1))
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(lblPedidos)
                    .addContainerGap(629, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableACaminho;
    private javax.swing.JTable jTableAndamento;
    private javax.swing.JTable jTablePendentes;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JScrollPane spACaminho;
    private javax.swing.JScrollPane spEmAndamento;
    private javax.swing.JScrollPane spPendentes;
    // End of variables declaration//GEN-END:variables
}