package com.tetris;

import com.tetris.ui.TetrisFrame;
import com.tetris.ui.LeaderboardFrame;
import com.tetris.util.FileManager;

import javax.swing.*;

public class TetrisGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showStartMenu());
    }

    public static void showStartMenu() {
        JFrame menuFrame = new JFrame("Tetris - Menu Principal");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 300);
        menuFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("TETRIS");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 40));
        titleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("POO II 2026");
        subtitleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 14));
        subtitleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(subtitleLabel);

        panel.add(Box.createVerticalStrut(30));

        // NOVO JOGO
        JButton newGameButton = new JButton("Novo Jogo");
        newGameButton.setMaximumSize(new java.awt.Dimension(200, 40));
        newGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(e -> {
            menuFrame.dispose();
            String playerName = JOptionPane.showInputDialog(null, "Digite seu nome:", "Novo Jogo", JOptionPane.QUESTION_MESSAGE);
            new TetrisFrame(playerName == null ? "" : playerName, false);
        });
        panel.add(newGameButton);
        panel.add(Box.createVerticalStrut(10));

        // CARREGAR JOGO (Blindado com try-catch)
        JButton loadGameButton = new JButton("Carregar Jogo");
        loadGameButton.setMaximumSize(new java.awt.Dimension(200, 40));
        loadGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        loadGameButton.addActionListener(e -> {
            try {
                if (FileManager.hasSaveFile()) {
                    menuFrame.dispose();
                    new TetrisFrame("", true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma partida salva encontrada.", "Carregar Jogo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro interno ao carregar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loadGameButton);
        panel.add(Box.createVerticalStrut(10));

        // LEADERBOARD (Blindado com try-catch)
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setMaximumSize(new java.awt.Dimension(200, 40));
        leaderboardButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        leaderboardButton.addActionListener(e -> {
            try {
                new LeaderboardFrame().setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir Leaderboard: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(leaderboardButton);
        panel.add(Box.createVerticalStrut(10));

        // SAIR
        JButton exitButton = new JButton("Sair");
        exitButton.setMaximumSize(new java.awt.Dimension(200, 40));
        exitButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        menuFrame.add(panel);
        menuFrame.setVisible(true);
    }
}