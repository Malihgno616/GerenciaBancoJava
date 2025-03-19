package org.example;

import javax.swing.*;
import java.awt.*;

public class Visualizar extends JFrame {
    private JLabel mainTitle;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JButton btnView;

    public Visualizar(){
        setTitle("Visualização da conta");
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

        btnView = new JButton("Verificar");
        btnView.setFont(fontText);
        btnView.setHorizontalAlignment(SwingConstants.CENTER);
        btnView.setBounds((getWidth()-80)/2, 245, 195, 50);
        btnView.setForeground(new Color(255, 255, 255));
        btnView.setBackground(new Color(0,0,0, 255));

        add(btnView);

        setVisible(true);
    }
}
