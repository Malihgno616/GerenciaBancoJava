package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaBanco extends JFrame {
    private JLabel txtNome = new JLabel("Nome: ");
    private JLabel txtSobreNome = new JLabel("Sobrenome: ");
    private JLabel txtCpf = new JLabel("CPF: ");
    private JLabel txtSaldo = new JLabel("Saldo: ");
    private JButton btnSaque = new JButton("CLIQUE AQUI PARA SACAR");
    private JButton btnDeposito = new JButton("CLIQUE AQUI PARA DEPOSITAR");
    private Font fontTitle = new Font("Arial", Font.BOLD, 32);
    private JLabel nome = new JLabel("");
    private JLabel sobreNome = new JLabel("");
    private JLabel cpf = new JLabel("");
    private JLabel saldo = new JLabel("");
    private ConnSql connSql;
    private String clienteCPF;
    private JButton btnVoltar = new JButton("Voltar");
    private TelaBanco TelaBanco;
    public TelaBanco(ConnSql connSql, String clienteCPF) {
        this.connSql = connSql;
        this.clienteCPF = clienteCPF;
        setTitle("Conta");
        setSize(786, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Não altera o tamanho da tela
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);

        txtNome.setFont(fontTitle);
        txtNome.setHorizontalAlignment(SwingConstants.LEFT);
        txtNome.setBounds(150, 150, 750, 50);

        add(txtNome);

        txtSobreNome.setFont(fontTitle);
        txtSobreNome.setHorizontalAlignment(SwingConstants.LEFT);
        txtSobreNome.setBounds(150,250,750,50);

        add(txtSobreNome);

        txtCpf.setFont(fontTitle);
        txtCpf.setHorizontalAlignment(SwingConstants.LEFT);
        txtCpf.setBounds(150,350,750,50);

        add(txtCpf);

        txtSaldo.setFont(fontTitle);
        txtSaldo.setHorizontalAlignment(SwingConstants.LEFT);
        txtSaldo.setBounds(150, 450, 750, 50);

        add(txtSaldo);

        btnSaque.setFont(fontTitle);
        btnSaque.setHorizontalAlignment(SwingConstants.CENTER);
        btnSaque.setBounds(75,20, 600, 50);
        btnSaque.setForeground(new Color(255,255,255));
        btnSaque.setBackground(new Color(0,0,0,255));

        add(btnSaque);

        btnDeposito.setFont(fontTitle);
        btnDeposito.setHorizontalAlignment(SwingConstants.CENTER);
        btnDeposito.setBounds(75,75, 600, 50);
        btnDeposito.setForeground(new Color(255,255,255));
        btnDeposito.setBackground(new Color(0,0,0,255));

        add(btnDeposito);

        nome.setFont(fontTitle);
        nome.setBounds(400, 150, 350, 50);
        add(nome);

        sobreNome.setFont(fontTitle);
        sobreNome.setBounds(400, 250, 350, 50);
        add(sobreNome);

        cpf.setFont(fontTitle);
        cpf.setBounds(400, 350, 350, 50);
        add(cpf);

        saldo.setFont(fontTitle);
        saldo.setBounds(400, 450, 350, 50);
        add(saldo);

        btnVoltar.setFont(fontTitle);
        btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
        btnVoltar.setBounds((getWidth()-80)/2, 550, 195, 50);
        btnVoltar.setForeground(new Color(255,255,255));
        btnVoltar.setBackground(new Color(0, 133, 22));

        add(btnVoltar);

        exibirPerfil();

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login(connSql, clienteCPF);
            }
        });

        btnDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposito(connSql, clienteCPF, TelaBanco);
            }
        });

        btnSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Saque();
            }
        });

    }

    void exibirPerfil() {
        try {
            // Remove caracteres não numéricos do CPF
            String cpfBusca = clienteCPF.replaceAll("[^0-9]", "");

            // Consulta SQL para buscar os dados
            String query = "SELECT * FROM cliente WHERE cpf = ?";

            try (Connection conn = connSql.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, cpfBusca);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Formata os dados
                        String cpfFormatado = rs.getString("cpf")
                                .replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

                        // Atualiza os JLabels
                        nome.setText(rs.getString("nome"));
                        sobreNome.setText(rs.getString("sobrenome"));
                        cpf.setText(cpfFormatado);

                        // Tratamento do saldo
                        double saldoValor = rs.getDouble("saldo");
                        if (rs.wasNull()) {  // Verifica se o valor do saldo é nulo no banco
                            saldoValor = 0.0; // Valor padrão se for nulo
                        }

                        saldo.setText(String.format("R$ %.2f", saldoValor));
                    } else {
                        JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
                        dispose(); // Fecha a janela se não encontrar
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void atualizarSaldoNaTela(double novoSaldo) {
        saldo.setText(String.format("R$ %.2f", novoSaldo));
    }

}
