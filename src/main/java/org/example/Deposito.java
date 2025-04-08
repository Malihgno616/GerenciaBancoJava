package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Deposito extends JFrame {
    private ConnSql connSql;
    private TextField saldo = new TextField();
    private JLabel txtSaldo = new JLabel("Digite aqui a quantidade para depósito");
    private JButton btnDepositar = new JButton("Depositar");

    public Deposito(ConnSql connSql) {
        this.connSql = connSql;
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
        btnDepositar.setBackground(new Color(0,0,0));

        add(txtSaldo);
        add(saldo);
        add(btnDepositar);

        // Faltou adicionar o ActionListener ao botão
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });

        setVisible(true);
    }
    private void depositar() {
        String valorTexto = saldo.getText();

        try {
            // Converter o texto para número
            double valor = Double.parseDouble(valorTexto);

            // Obter o ID do cliente (você precisa ter essa informação)
            int idCliente = connSql.getIdCliente(); // Supondo que exista um método para pegar o ID

            // Preparar a query SQL
            String query = "UPDATE cliente SET saldo = saldo + ? WHERE id = ?";

            try (java.sql.PreparedStatement pstmt = connSql.getConnection().prepareStatement(query)) {
                pstmt.setDouble(1, valor);
                pstmt.setInt(2, idCliente);

                int linhasAfetadas = pstmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!");
                    this.dispose(); // Fechar janela após depósito
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar depósito");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Digite apenas números");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de banco de dados: " + ex.getMessage());
        }
    }
}
