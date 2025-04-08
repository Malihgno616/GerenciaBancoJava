package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JLabel mainTitle;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JButton btnView;
    private ConnSql connSql;
    private String clienteCPF;
    private JButton btnVoltar = new JButton("Voltar");

    public Login(ConnSql connSql, String clienteCPF){
        this.connSql = connSql;
        this.clienteCPF = clienteCPF;

        setTitle("Entrar na conta");
        setSize(786, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Não altera o tamanho da tela
        setLocationRelativeTo(null);
        setLayout(null);

        Font fontTitle = new Font("Arial", Font.BOLD, 32);
        Font fontText = new Font("Arial", Font.PLAIN, 24);

        mainTitle = new JLabel("Insira seu CPF para verificar sua conta");
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitle.setBounds(0, 20, 750, 50);
        mainTitle.setFont(fontTitle);
        add(mainTitle);

        lblCpf = new JLabel("Digite seu CPF: ");
        lblCpf.setFont(fontText);
        lblCpf.setBounds((getWidth()-650)/2, 0, 750, 345);

        add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setHorizontalAlignment(SwingConstants.LEFT);
        txtCpf.setFont(fontText);
        txtCpf.setBounds((getWidth()-80)/2, 150, 195, 50);

        add(txtCpf);

        btnView = new JButton("Entrar");
        btnView.setFont(fontText);
        btnView.setHorizontalAlignment(SwingConstants.CENTER);
        btnView.setBounds((getWidth()-80)/2, 245, 195, 50);
        btnView.setForeground(new Color(255, 255, 255));
        btnView.setBackground(new Color(0,0,0, 255));

        add(btnView);

        btnVoltar.setFont(fontText);
        btnVoltar.setHorizontalAlignment(SwingConstants.CENTER);
        btnVoltar.setBounds((getWidth()-80)/2, 370, 195, 50);
        btnVoltar.setForeground(new Color(255,255,255));
        btnVoltar.setBackground(new Color(0, 133, 22));

        add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Screen(connSql);
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logarConta();
            }
        });

        setVisible(true);

    }
    private void logarConta() {
        String cpf = txtCpf.getText().trim();

        // Verificação única de CPF vazio
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um CPF");
            return;
        }

        String query = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";

        try (Connection conn = connSql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);

                if (count > 0) {
                    // CPF válido: abre TelaBanco com o CPF digitado
                    JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                    this.dispose();
                    new TelaBanco(connSql, cpf); // Corrigido: usa o cpf do campo de texto
                } else {
                    JOptionPane.showMessageDialog(this, "CPF não cadastrado!");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar CPF: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
