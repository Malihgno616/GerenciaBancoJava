package org.example;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ConnSql connSql = new ConnSql();
        try {
            Connection conn = connSql.getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
            interfaceBanco();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar!");
            e.printStackTrace();
        }
    }

    public static void interfaceBanco() {
        // Criando o JFrame
        JFrame frame = new JFrame("Gerenciamento de Banco");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centraliza a janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Não permite redimensionar a janela

        // Criando um JPanel com o layout BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Criando um JLabel com texto e alinhando-o ao centro
        JLabel label = new JLabel("Bem-vindo ao Gerenciamento de Banco");
        label.setHorizontalAlignment(SwingConstants.CENTER); // Alinha o texto ao centro
        label.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do texto

        // Adicionando o JLabel ao painel (parte superior)
        panel.add(label, BorderLayout.NORTH);

        // Criando um JTextArea com texto
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setText("Aqui você pode realizar consultas, inserções e muito mais.");
        textArea.setLineWrap(true);  // Faz a quebra de linha automática
        textArea.setWrapStyleWord(true); // Quebra palavras ao invés de cortar a palavra
        textArea.setCaretPosition(0);  // Posiciona o cursor no início
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Define a fonte
        textArea.setEditable(false); // Define como não editável

        // Colocando o JTextArea dentro de um JScrollPane para permitir rolagem
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Criando um JButton
        JButton button = new JButton("Clique aqui");
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão clicado!");
        });

        // Adicionando o botão à parte inferior do painel
        panel.add(button, BorderLayout.SOUTH);

        // Adicionando o painel ao frame
        frame.add(panel);

        // Tornando o frame visível
        frame.setVisible(true);
    }
}