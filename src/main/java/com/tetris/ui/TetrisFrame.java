package com.tetris.ui;

import com.tetris.core.TetrisEngine;
import com.tetris.model.*;
import com.tetris.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Janela principal do jogo Tetris.
 * Gerencia a interface e o loop do jogo.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class TetrisFrame extends JFrame implements TetrisEngine.GameListener, GamePanel.GameListener {
    
    private TetrisEngine engine;
    private GamePanel gamePanel;
    private GameThread gameThread;
    private String playerName;
    
    /**
     * Constrói a janela principal do Tetris.
     * 
     * @param playerName nome do jogador
     */
    public TetrisFrame(String playerName) {
        this.playerName = playerName;
        
        setTitle("Tetris - POO II 2026");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Cria o engine do jogo
        engine = new TetrisEngine(this);
        
        // Cria o painel de jogo
        gamePanel = new GamePanel(engine, this);
        gamePanel.setPreferredSize(new Dimension(900, 700));
        
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Inicia a thread do jogo
        gameThread = new GameThread(engine, gamePanel, this);
        gameThread.start();
        
        // Pega o foco para o painel receber eventos de teclado
        gamePanel.requestFocus();
    }
    
    @Override
    public void onPieceSpawned(Piece current, Piece next) {
        gamePanel.repaint();
    }
    
    @Override
    public void onPieceMoved(Piece piece) {
        gamePanel.repaint();
    }
    
    @Override
    public void onLinesCleared(int lines, int total) {
        gamePanel.repaint();
    }
    
    @Override
    public void onScoreChanged(long score) {
        gamePanel.repaint();
    }
    
    @Override
    public void onLevelUp(int newLevel) {
        JOptionPane.showMessageDialog(this, "Parabéns! Você alcançou o nível " + newLevel + "!",
                                      "Level Up", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void onGameOver(long finalScore) {
        gamePanel.repaint();
        
        // Para a thread do jogo
        if (gameThread != null) {
            gameThread.stop();
        }
        
        // Pede o nome do jogador (para v1.0)
        String name = playerName;
        if (playerName == null || playerName.isEmpty()) {
            name = JOptionPane.showInputDialog(this, "Digite seu nome:", "Unnamed Player");
        }
        
        if (name == null || name.isEmpty()) {
            name = "Anonymous";
        }
        
        // Salva o score
        try {
            GameScore score = new GameScore(name, finalScore, engine.getLevel(), engine.getLinesCleared());
            FileManager.saveScore(score);
            
            // Mostra o score
            JOptionPane.showMessageDialog(this,
                    String.format("Game Over!\n\nJogador: %s\nScore: %d\nNível: %d\nLinhas: %d",
                                  name, finalScore, engine.getLevel(), engine.getLinesCleared()),
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar score: " + e.getMessage(),
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void onPauseStateChanged(boolean paused) {
        if (paused) {
            gameThread.pause();
        } else {
            gameThread.resume();
        }
        gamePanel.repaint();
    }
    
    @Override
    public void onGameStateLoaded() {
        gamePanel.repaint();
    }
    
    @Override
    public void onSaveRequested() {
        try {
            GameState state = engine.getState();
            FileManager.saveGameState(state);
            JOptionPane.showMessageDialog(this, "Jogo salvo com sucesso!",
                                          "Salvo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage(),
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void onLoadRequested() {
        try {
            GameState state = FileManager.loadGameState();
            if (state != null) {
                engine.loadState(state);
                gamePanel.repaint();
                JOptionPane.showMessageDialog(this, "Jogo carregado com sucesso!",
                                              "Carregado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma partida salva encontrada.",
                                              "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + e.getMessage(),
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Fecha a janela e pausa a thread.
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b && gameThread != null) {
            gameThread.stop();
        }
    }
}
