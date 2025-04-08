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
    private JButton btnVoltar = new JButton("Voltar");

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

    }
}
