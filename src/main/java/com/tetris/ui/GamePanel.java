package com.tetris.ui;

import com.tetris.core.TetrisEngine;
import com.tetris.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Painel de renderização do jogo Tetris.
 * Responsável por desenhar o tabuleiro, peças e interface.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GamePanel extends JPanel {
    
    private static final int CELL_SIZE = 30;
    private static final Color[] PIECE_COLORS = {
        new Color(200, 0, 0),     // I - Vermelho
        new Color(0, 200, 0),     // O - Verde
        new Color(0, 0, 200),     // T - Azul
        new Color(200, 200, 0),   // S - Amarelo
        new Color(200, 0, 200),   // Z - Magenta
        new Color(0, 200, 200),   // J - Ciano
        new Color(200, 100, 0)    // L - Laranja
    };
    private static final Color GRID_COLOR = new Color(100, 100, 100);
    private static final Color BOARD_BG = new Color(30, 30, 30);
    
    private TetrisEngine engine;
    private GameListener listener;
    
    /**
     * Constrói um novo painel de jogo.
     * 
     * @param engine engine do Tetris
     * @param listener listener de eventos
     */
    public GamePanel(TetrisEngine engine, GameListener listener) {
        this.engine = engine;
        this.listener = listener;
        
        setBackground(new Color(50, 50, 50));
        setFocusable(true);
        setupKeyListener();
    }
    
    /**
     * Configura o listener de teclado.
     */
    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                
                switch (code) {
                    case KeyEvent.VK_LEFT:
                        engine.moveLeft();
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        engine.moveRight();
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        engine.moveDown();
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        engine.rotatePiece();
                        repaint();
                        break;
                    case KeyEvent.VK_SPACE:
                        engine.hardDrop();
                        repaint();
                        break;
                    case KeyEvent.VK_P:
                        engine.togglePause();
                        repaint();
                        break;
                    case KeyEvent.VK_R:
                        engine.newGame();
                        repaint();
                        break;
                    case KeyEvent.VK_S:
                        if (listener != null) {
                            listener.onSaveRequested();
                        }
                        break;
                    case KeyEvent.VK_L:
                        if (listener != null) {
                            listener.onLoadRequested();
                        }
                        break;
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int offsetX = 50;
        int offsetY = 50;
        
        // Desenha o tabuleiro
        Board board = engine.getBoard();
        drawBoard(g2d, board, offsetX, offsetY);
        
        // Desenha a peça atual
        Piece currentPiece = engine.getCurrentPiece();
        drawPiece(g2d, currentPiece, offsetX, offsetY);
        
        // Desenha a próxima peça (em um painel à parte)
        drawNextPiecePanel(g2d, engine.getNextPiece(), offsetX + board.getWidth() * CELL_SIZE + 100, offsetY);
        
        // Desenha informações do jogo
        drawGameInfo(g2d, offsetX + board.getWidth() * CELL_SIZE + 100, offsetY + 150);
        
        // Desenha mensagens de pause/game over
        if (engine.isPaused()) {
            drawPauseMessage(g2d);
        }
        
        if (engine.isGameOver()) {
            drawGameOverMessage(g2d);
        }
        
        // Desenha controles
        drawControls(g2d);
    }
    
    /**
     * Desenha o tabuleiro.
     */
    private void drawBoard(Graphics2D g2d, Board board, int offsetX, int offsetY) {
        int width = board.getWidth();
        int height = board.getHeight();
        
        // Fundo do tabuleiro
        g2d.setColor(BOARD_BG);
        g2d.fillRect(offsetX, offsetY, width * CELL_SIZE, height * CELL_SIZE);
        
        // Grade
        g2d.setColor(GRID_COLOR);
        g2d.setStroke(new BasicStroke(1));
        
        for (int row = 0; row <= height; row++) {
            g2d.drawLine(offsetX, offsetY + row * CELL_SIZE,
                         offsetX + width * CELL_SIZE, offsetY + row * CELL_SIZE);
        }
        
        for (int col = 0; col <= width; col++) {
            g2d.drawLine(offsetX + col * CELL_SIZE, offsetY,
                         offsetX + col * CELL_SIZE, offsetY + height * CELL_SIZE);
        }
        
        // Células preenchidas
        int[][] grid = board.getGrid();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (grid[row][col] > 0) {
                    drawCell(g2d, col, row, grid[row][col] - 1, offsetX, offsetY);
                }
            }
        }
    }
    
    /**
     * Desenha a peça atual.
     */
    private void drawPiece(Graphics2D g2d, Piece piece, int offsetX, int offsetY) {
        if (piece == null) return;
        
        boolean[][] shape = piece.getShape();
        int colorId = piece.getType().ordinal();
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (shape[row][col]) {
                    int x = piece.getX() + col;
                    int y = piece.getY() + row;
                    
                    if (y >= 0) {
                        drawCell(g2d, x, y, colorId, offsetX, offsetY);
                    }
                }
            }
        }
    }
    
    /**
     * Desenha uma célula no tabuleiro.
     */
    private void drawCell(Graphics2D g2d, int col, int row, int colorId, int offsetX, int offsetY) {
        int x = offsetX + col * CELL_SIZE;
        int y = offsetY + row * CELL_SIZE;
        
        // Cor da célula
        g2d.setColor(PIECE_COLORS[colorId % PIECE_COLORS.length]);
        g2d.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
        
        // Borda para efeito 3D
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x + 1, y + 1, x + CELL_SIZE - 2, y + 1);
        g2d.drawLine(x + 1, y + 1, x + 1, y + CELL_SIZE - 2);
        
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x + CELL_SIZE - 2, y + 1, x + CELL_SIZE - 2, y + CELL_SIZE - 2);
        g2d.drawLine(x + 1, y + CELL_SIZE - 2, x + CELL_SIZE - 2, y + CELL_SIZE - 2);
    }
    
    /**
     * Desenha o painel da próxima peça.
     */
    private void drawNextPiecePanel(Graphics2D g2d, Piece nextPiece, int x, int y) {
        // Fundo do painel
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillRect(x, y, 120, 120);
        
        // Borda
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(x, y, 120, 120);
        
        // Texto
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Próxima peça:", x + 10, y + 20);
        
        // Desenha a peça
        if (nextPiece != null) {
            drawSmallPiece(g2d, nextPiece, x + 20, y + 40);
        }
    }
    
    /**
     * Desenha uma peça pequena (para preview).
     */
    private void drawSmallPiece(Graphics2D g2d, Piece piece, int offsetX, int offsetY) {
        boolean[][] shape = piece.getShape();
        int colorId = piece.getType().ordinal();
        int smallCellSize = 15;
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (shape[row][col]) {
                    int x = offsetX + col * smallCellSize;
                    int y = offsetY + row * smallCellSize;
                    
                    g2d.setColor(PIECE_COLORS[colorId % PIECE_COLORS.length]);
                    g2d.fillRect(x, y, smallCellSize - 1, smallCellSize - 1);
                    
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(x, y, smallCellSize - 1, smallCellSize - 1);
                }
            }
        }
    }
    
    /**
     * Desenha informações do jogo.
     */
    private void drawGameInfo(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        
        int lineHeight = 30;
        int currentY = y;
        
        g2d.drawString("Score: " + engine.getScore(), x, currentY);
        currentY += lineHeight;
        
        g2d.drawString("Level: " + engine.getLevel(), x, currentY);
        currentY += lineHeight;
        
        g2d.drawString("Lines: " + engine.getLinesCleared(), x, currentY);
    }
    
    /**
     * Desenha mensagem de pause.
     */
    private void drawPauseMessage(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fm = g2d.getFontMetrics();
        String msg = "PAUSED";
        g2d.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2);
    }
    
    /**
     * Desenha mensagem de game over.
     */
    private void drawGameOverMessage(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g2d.getFontMetrics();
        String msg = "GAME OVER";
        g2d.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2 - 30);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        fm = g2d.getFontMetrics();
        String restart = "Pressione R para reiniciar";
        g2d.drawString(restart, (getWidth() - fm.stringWidth(restart)) / 2, getHeight() / 2 + 30);
    }
    
    /**
     * Desenha controles do jogo.
     */
    private void drawControls(Graphics2D g2d) {
        g2d.setColor(new Color(200, 200, 200));
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        
        String[] controls = {
            "← → : Mover",
            "↑ : Rotacionar",
            "↓ : Acelerar",
            "Espaço: Hard Drop",
            "P: Pausar",
            "R: Reiniciar",
            "S: Salvar",
            "L: Carregar"
        };
        
        int y = getHeight() - 100;
        for (String control : controls) {
            g2d.drawString(control, 20, y);
            y += 15;
        }
    }
    
    /**
     * Interface para listener de eventos da UI.
     */
    public interface GameListener {
        void onSaveRequested();
        void onLoadRequested();
    }
}
