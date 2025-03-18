package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Screen extends JFrame {
    private JButton btnViewConta;
    private JButton btnCriarConta;

    public Screen(){
       setTitle("Gerenciamento Bancário");
       setSize(786, 300);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false); // Não altera o tamanho da tela
       setLocationRelativeTo(null);

       setLayout(null);

//     Título principal do sistema bancário
       JLabel mainTitle = new JLabel();
       mainTitle.setText("Seja bem-vindo ao nosso sistema bancário!");
       Font fontTitle = new Font("Arial", Font.BOLD, 32);
       mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
       mainTitle.setBounds(0, 20, 800, 50);
       mainTitle.setFont(fontTitle);

//     Label secundária
       JLabel txt = new JLabel("Selecione as opções");
       txt.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
       Font fontText = new Font("Arial", Font.PLAIN, 20);
       txt.setFont(fontText);
       txt.setBounds(0,100,800,40);

//     Opções botões - visual
       btnViewConta = new JButton("Visualizar conta");
       btnViewConta.setHorizontalAlignment(SwingConstants.CENTER);
       btnViewConta.setBounds((getWidth() - 200 )/2, 200, 180, 50);
       btnViewConta.setFont(fontText);
       btnViewConta.setBackground(new Color(5, 64, 242, 255));
       btnViewConta.setForeground(new Color(242, 242, 242, 255));

       add(mainTitle);
       add(txt);

       add(btnViewConta);

       btnCriarConta = new JButton("Criar Conta");
       btnCriarConta.setHorizontalAlignment(SwingConstants.CENTER);
       btnCriarConta.setBounds((getWidth() - 200 )/2, 140, 180, 50);
       btnCriarConta.setFont(fontText);
       btnCriarConta.setBackground(new Color(5, 64, 242, 255));
       btnCriarConta.setForeground(new Color(242, 242, 242, 255));

       add(btnCriarConta);

       setVisible(true); // Torna a janela visível
    }

}
