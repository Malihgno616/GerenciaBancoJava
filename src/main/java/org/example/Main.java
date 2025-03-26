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
            new Screen(connSql);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar!");
            e.printStackTrace();
        }

    }
}