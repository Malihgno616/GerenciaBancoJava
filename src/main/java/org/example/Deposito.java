package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deposito extends JFrame {
    private ConnSql connSql;
    private TextField saldo = new TextField();
    private JLabel txtSaldo = new JLabel("Digite aqui a quantidade para depósito");
    private JButton btnDepositar = new JButton("Depositar");
    private String clienteCPF;
    private TelaBanco telaBanco;

    public Deposito(ConnSql connSql, String clienteCPF, TelaBanco telaBanco) {
        this.connSql = connSql;
        this.clienteCPF = clienteCPF;
        this.telaBanco = telaBanco;
        setTitle("Depósito");
        setSize(350, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false); // Não altera o tamanho da tela
        setLocationRelativeTo(null);
        setLayout(null);

        txtSaldo.setBounds(20, 20, 300, 20);
        saldo.setBounds(20, 50, 200, 30);
        btnDepositar.setBounds(20, 100, 120, 30);

        btnDepositar.setForeground(new Color(255, 255, 255));
        btnDepositar.setBackground(new Color(0, 0, 0));

        add(txtSaldo);
        add(saldo);
        add(btnDepositar);

        // Ação do botão de depósito
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });

        setVisible(true);
    }

    private void realizarDeposito() {
        // Verifica se o valor do saldo é um número válido
        try {
            double valorDeposito = Double.parseDouble(saldo.getText());

            if (valorDeposito <= 0) {
                JOptionPane.showMessageDialog(this, "O valor de depósito deve ser positivo.");
                return;
            }

            // Atualiza o saldo do cliente no banco de dados
            String query = "UPDATE cliente SET saldo = saldo + ? WHERE cpf = ?"; // Considerando CPF como identificador
            try (Connection conn = connSql.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setDouble(1, valorDeposito); // Valor a ser depositado
                stmt.setString(2, clienteCPF); // CPF do cliente

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar depósito: " + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para o depósito.");
        }
    }

}
