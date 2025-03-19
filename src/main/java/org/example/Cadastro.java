package org.example;

import javax.swing.*;
import java.awt.*;

public class Cadastro extends JFrame {
    private JLabel mainTitle;
    private JLabel titleNome;
    private JLabel titleSobreNome;
    private JLabel titleCpf;
    private JTextField nomeCad;
    private JTextField sobreNomeCad;
    private JTextField cpfCad;
    private JButton cadBtn;
    public Cadastro() {
        setTitle("Cadastro");
        setSize(786, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Não altera o tamanho da tela
        setLocationRelativeTo(null);
        setLayout(null);

        Font fontTitle = new Font("Arial", Font.BOLD, 32);

        Font fontText = new Font("Arial", Font.PLAIN, 24);

        mainTitle = new JLabel("Cadastre-se aqui!");
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitle.setBounds(0, 20, 750, 50);
        mainTitle.setFont(fontTitle);
        add(mainTitle);

        titleNome = new JLabel("Digite seu nome: ");
        titleNome.setHorizontalAlignment(SwingConstants.LEFT);
        titleNome.setFont(fontText);
        titleNome.setBounds((getWidth()-650)/2, 0, 750, 345);

        add(titleNome);

        titleSobreNome = new JLabel("Digite seu sobrenome: ");
        titleSobreNome.setHorizontalAlignment(SwingConstants.LEFT);
        titleSobreNome.setFont(fontText);
        titleSobreNome.setBounds((getWidth()-650)/2, 0, 750, 545);

        add(titleSobreNome);

        titleCpf = new JLabel("Digite seu cpf: ");
        titleCpf.setHorizontalAlignment(SwingConstants.LEFT);
        titleCpf.setFont(fontText);
        titleCpf.setBounds((getWidth()-650)/2, 0, 750, 745);

        add(titleCpf);

        nomeCad = new JTextField();

        nomeCad.setHorizontalAlignment(SwingConstants.LEFT);
        nomeCad.setFont(fontText);
        nomeCad.setBounds((getWidth()-80)/2, 145, 195, 50);

        add(nomeCad);

        sobreNomeCad = new JTextField();

        sobreNomeCad.setHorizontalAlignment(SwingConstants.LEFT);
        sobreNomeCad.setFont(fontText);
        sobreNomeCad.setBounds((getWidth()-80)/2, 245, 195, 50);

        add(sobreNomeCad);

        cpfCad = new JTextField();

        cpfCad.setHorizontalAlignment(SwingConstants.LEFT);
        cpfCad.setFont(fontText);
        cpfCad.setBounds((getWidth()-80)/2, 345, 195, 50);

        add(cpfCad);

        cadBtn = new JButton("Cadastrar");
        cadBtn.setFont(fontText);
        cadBtn.setHorizontalAlignment(SwingConstants.CENTER);
        cadBtn.setBounds((getWidth()-80)/2, 445, 195, 50);
        cadBtn.setForeground(new Color(255, 255, 255));
        cadBtn.setBackground(new Color(0,0,0, 255));
        add(cadBtn);

        setVisible(true);
    }
}
