package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaBanco extends JFrame {
    private final JLabel txtNome = new JLabel("Nome: ");
    private final JLabel txtSobreNome = new JLabel("Sobrenome: ");
    private final JLabel txtCpf = new JLabel("CPF: ");
    private final JLabel txtSaldo = new JLabel("Saldo: ");
    private final JButton btnSaque = new JButton("CLIQUE AQUI PARA SACAR");
    private final JButton btnDeposito = new JButton("CLIQUE AQUI PARA DEPOSITAR");
    private final Font fontTitle = new Font("Arial", Font.BOLD, 32);
    private ConnSql connSql;

    public TelaBanco(ConnSql connSql) {
        this.connSql = connSql;
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
    }
}
