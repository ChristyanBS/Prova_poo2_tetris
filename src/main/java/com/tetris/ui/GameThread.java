package com.tetris.ui;

import com.tetris.core.TetrisEngine;
import javax.swing.*;

/**
 * Thread que controla o loop do jogo Tetris.
 * Responsável pela velocidade das peças baseada no nível.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GameThread extends Thread {
    
    private TetrisEngine engine;
    private GamePanel gamePanel;
    private TetrisFrame frame;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    
    /**
     * Constrói a thread do jogo.
     * 
     * @param engine engine do Tetris
     * @param gamePanel painel de renderização
     * @param frame frame principal
     */
    public GameThread(TetrisEngine engine, GamePanel gamePanel, TetrisFrame frame) {
        this.engine = engine;
        this.gamePanel = gamePanel;
        this.frame = frame;
        setDaemon(true);
    }
    
    @Override
    public void run() {
        while (running) {
            if (!paused && !engine.isGameOver()) {
                // Calcula o delay baseado no nível
                int delay = calculateDelay(engine.getLevel());
                
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                
                // Faz a peça descer
                engine.moveDown();
                
                // Repinta o painel
                SwingUtilities.invokeLater(() -> gamePanel.repaint());
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    /**
     * Calcula o delay em milissegundos baseado no nível.
     * Quanto maior o nível, menor o delay (mais rápido).
     * 
     * @param level nível do jogo
     * @return delay em ms
     */
    private int calculateDelay(int level) {
        // Começa em 1000ms (nível 1) e diminui com a progressão
        // Mínimo de 100ms
        int delay = 1000 - ((level - 1) * 50);
        return Math.max(delay, 100);
    }
    
    /**
     * Pausa a thread do jogo.
     */
    public void pause() {
        paused = true;
    }
    
    /**
     * Resume a thread do jogo.
     */
    public void resume() {
        paused = false;
    }
    
    /**
     * Para a thread do jogo.
     */
    public void stopGame() {
        running = false;
    }
}
