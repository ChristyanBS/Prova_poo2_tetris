package com.tetris.ui;

import com.tetris.model.GameScore;
import com.tetris.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Janela de leaderboard - exibe os melhores scores.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class LeaderboardFrame extends JFrame {
    
    /**
     * Constrói a janela de leaderboard.
     */
    public LeaderboardFrame() {
        setTitle("Leaderboard - Tetris");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel titleLabel = new JLabel("Top 10 Melhores Scores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);
        
        // Lista de scores
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setBackground(Color.WHITE);
        
        try {
            List<GameScore> topScores = FileManager.getTopScores(10);
            
            if (topScores.isEmpty()) {
                JLabel noScoresLabel = new JLabel("Nenhum score registrado ainda.");
                noScoresLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                scoresPanel.add(noScoresLabel);
            } else {
                int rank = 1;
                for (GameScore score : topScores) {
                    JLabel scoreLabel = new JLabel(
                        String.format("#%d - %s: %d pontos (Nível %d, %d linhas)",
                                      rank, score.getPlayerName(), score.getScore(),
                                      score.getLevel(), score.getLinesCleared())
                    );
                    scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    
                    // Alterna cores das linhas
                    if (rank % 2 == 0) {
                        scoreLabel.setOpaque(true);
                        scoreLabel.setBackground(new Color(230, 230, 230));
                    }
                    
                    scoresPanel.add(scoreLabel);
                    rank++;
                }
            }
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Erro ao carregar scores: " + e.getMessage());
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            scoresPanel.add(errorLabel);
        }
        
        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // Botão de fechar
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());
        contentPane.add(closeButton, BorderLayout.SOUTH);
        
        setContentPane(contentPane);
    }
}
