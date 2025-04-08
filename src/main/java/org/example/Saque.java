package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Saque extends JFrame {
    private ConnSql connSql;
    private TextField saldo = new TextField();
    private JLabel txtSaldo = new JLabel("Digite a quantidade que deseja sacar:");
    private JButton btnSacar = new JButton("Sacar");
    private String clienteCPF;
    private TelaBanco telaBanco;
    public Saque(ConnSql connSql, String clienteCPF, TelaBanco telaBanco) {
        this.connSql = connSql;
        this.clienteCPF = clienteCPF;
        this.telaBanco = telaBanco;
        setTitle("Saque");
        setSize(300,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        txtSaldo.setBounds(20, 20, 300, 20);
        saldo.setBounds(20, 50, 200, 30);
        btnSacar.setBounds(20, 100, 120, 30);

        btnSacar.setForeground(new Color(255, 255, 255));
        btnSacar.setBackground(new Color(0, 0, 0));

        add(txtSaldo);
        add(saldo);
        add(btnSacar);

        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarSaque();
            }
        });
    }
    private void realizarSaque() {
        try {
            double valorSaque = Double.parseDouble(saldo.getText());

            if (valorSaque <= 0) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                return;
            }

            // Atualiza o saldo do cliente no banco de dados
            String query = "UPDATE cliente SET saldo = saldo - ? WHERE cpf = ?"; // Considerando CPF como identificador
            try (Connection conn = connSql.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setDouble(1, valorSaque); // Valor a ser depositado
                stmt.setString(2, clienteCPF); // CPF do cliente

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar o saque: " + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para o saque.");
        }
    }
}
