package com.tetris.core;

import com.tetris.model.*;
import java.util.*;

/**
 * Engine principal do Tetris.
 * Gerencia a lógica do jogo, movimento de peças, detecção de colisões e pontuação.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class TetrisEngine {
    
    private Board board;
    private Piece currentPiece;
    private Piece nextPiece;
    private long score;
    private int level;
    private int linesCleared;
    private boolean gameOver;
    private boolean paused;
    private Random random;
    private GameListener listener;
    
    /**
     * Constrói um novo engine de Tetris.
     * 
     * @param listener listener para eventos do jogo
     */
    public TetrisEngine(GameListener listener) {
        this.board = new Board();
        this.score = 0;
        this.level = 1;
        this.linesCleared = 0;
        this.gameOver = false;
        this.paused = false;
        this.random = new Random();
        this.listener = listener;
        
        this.nextPiece = createRandomPiece();
        spawnNewPiece();
    }
    
    /**
     * Inicia uma nova partida.
     */
    public void newGame() {
        board.clear();
        score = 0;
        level = 1;
        linesCleared = 0;
        gameOver = false;
        paused = false;
        nextPiece = createRandomPiece();
        spawnNewPiece();
    }
    
    /**
     * Gera uma nova peça aleatória.
     * 
     * @return nova Piece
     */
    private Piece createRandomPiece() {
        Piece.PieceType[] types = Piece.PieceType.values();
        Piece.PieceType randomType = types[random.nextInt(types.length)];
        return new Piece(randomType, 3, 0);
    }
    
    /**
     * Spawna uma nova peça no topo do tabuleiro.
     */
    private void spawnNewPiece() {
        currentPiece = nextPiece;
        nextPiece = createRandomPiece();
        
        // Verifica game over
        if (!board.canPlacePiece(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            gameOver = true;
            if (listener != null) {
                listener.onGameOver(score);
            }
        }
        
        if (listener != null) {
            listener.onPieceSpawned(currentPiece, nextPiece);
        }
    }
    
    /**
     * Move a peça atual para baixo (gravidade).
     * Retorna true se a peça foi movida, false se não conseguiu.
     * 
     * @return true se movimento foi bem-sucedido
     */
    public boolean moveDown() {
        if (gameOver || paused) {
            return false;
        }
        
        int newY = currentPiece.getY() + 1;
        
        if (board.canPlacePiece(currentPiece, currentPiece.getX(), newY)) {
            currentPiece.setY(newY);
            return true;
        } else {
            // Fixa a peça
            fixCurrentPiece();
            return false;
        }
    }
    
    /**
     * Move a peça para a esquerda.
     */
    public void moveLeft() {
        if (gameOver || paused) {
            return;
        }
        
        int newX = currentPiece.getX() - 1;
        
        if (board.canPlacePiece(currentPiece, newX, currentPiece.getY())) {
            currentPiece.setX(newX);
            if (listener != null) {
                listener.onPieceMoved(currentPiece);
            }
        }
    }
    
    /**
     * Move a peça para a direita.
     */
    public void moveRight() {
        if (gameOver || paused) {
            return;
        }
        
        int newX = currentPiece.getX() + 1;
        
        if (board.canPlacePiece(currentPiece, newX, currentPiece.getY())) {
            currentPiece.setX(newX);
            if (listener != null) {
                listener.onPieceMoved(currentPiece);
            }
        }
    }
    
    /**
     * Rotaciona a peça atual no sentido horário.
     */
    public void rotatePiece() {
        if (gameOver || paused) {
            return;
        }
        
        Piece backup = currentPiece.copy();
        currentPiece.rotate();
        
        // Verifica se a rotação é válida, senão desfaz
        if (!board.canPlacePiece(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            currentPiece.setRotation(backup.getRotation());
        }
        
        if (listener != null) {
            listener.onPieceMoved(currentPiece);
        }
    }
    
    /**
     * Hard drop - desce a peça até o final.
     */
    public void hardDrop() {
        if (gameOver || paused) {
            return;
        }
        
        while (moveDown()) {
            // Continua movendo até não conseguir mais
        }
    }
    
    /**
     * Fixa a peça atual no tabuleiro.
     */
    private void fixCurrentPiece() {
        int colorId = currentPiece.getType().ordinal() + 1;
        board.fixPiece(currentPiece, colorId);
        
        // Limpa linhas completas
        int linesCleared = board.clearLines();
        if (linesCleared > 0) {
            addScore(linesCleared);
            this.linesCleared += linesCleared;
            
            // Aumenta nível a cada 10 linhas
            int newLevel = 1 + (this.linesCleared / 10);
            if (newLevel > level) {
                level = newLevel;
                if (listener != null) {
                    listener.onLevelUp(level);
                }
            }
            
            if (listener != null) {
                listener.onLinesCleared(linesCleared, this.linesCleared);
            }
        }
        
        // Spawna nova peça
        spawnNewPiece();
    }
    
    /**
     * Calcula e adiciona pontuação.
     * 
     * @param linesCleared número de linhas limpas
     */
    private void addScore(int linesCleared) {
        long points = 0;
        
        switch (linesCleared) {
            case 1:
                points = 40 * (level + 1);
                break;
            case 2:
                points = 100 * (level + 1);
                break;
            case 3:
                points = 300 * (level + 1);
                break;
            case 4:
                points = 1200 * (level + 1);
                break;
        }
        
        score += points;
        
        if (listener != null) {
            listener.onScoreChanged(score);
        }
    }
    
    /**
     * Pausa/resume o jogo.
     */
    public void togglePause() {
        if (!gameOver) {
            paused = !paused;
            if (listener != null) {
                listener.onPauseStateChanged(paused);
            }
        }
    }
    
    /**
     * Carrega um estado de jogo anterior.
     * 
     * @param state o GameState a carregar
     */
    public void loadState(GameState state) {
        // Restaura o tabuleiro
        Board newBoard = new Board(state.getBoardWidth(), state.getBoardHeight());
        int[][] grid = state.getBoardGrid();
        for (int row = 0; row < state.getBoardHeight(); row++) {
            for (int col = 0; col < state.getBoardWidth(); col++) {
                newBoard.getGrid()[row][col] = grid[row][col];
            }
        }
        
        this.board = newBoard;
        this.score = state.getScore();
        this.level = state.getLevel();
        this.linesCleared = state.getLinesCleared();
        this.gameOver = state.isGameOver();
        this.paused = state.isPaused();
        
        // Restaura peças
        try {
            this.currentPiece = new Piece(
                Piece.PieceType.valueOf(state.getCurrentPieceType()),
                state.getCurrentPieceX(),
                state.getCurrentPieceY()
            );
            this.currentPiece.setRotation(state.getCurrentPieceRotation());
            
            this.nextPiece = new Piece(
                Piece.PieceType.valueOf(state.getNextPieceType()),
                3,
                0
            );
        } catch (Exception e) {
            System.err.println("Erro ao restaurar peças: " + e.getMessage());
        }
        
        if (listener != null) {
            listener.onGameStateLoaded();
        }
    }
    
    /**
     * Obtém o estado atual do jogo para salvar.
     * 
     * @return GameState representando o jogo atual
     */
    public GameState getState() {
        GameState state = new GameState(
            board.getGrid(),
            board.getWidth(),
            board.getHeight(),
            score,
            level,
            linesCleared
        );
        
        state.setCurrentPieceType(currentPiece.getType().toString());
        state.setCurrentPieceX(currentPiece.getX());
        state.setCurrentPieceY(currentPiece.getY());
        state.setCurrentPieceRotation(currentPiece.getRotation());
        state.setNextPieceType(nextPiece.getType().toString());
        state.setGameOver(gameOver);
        state.setPaused(paused);
        
        return state;
    }
    
    // Getters
    
    public Board getBoard() {
        return board;
    }
    
    public Piece getCurrentPiece() {
        return currentPiece;
    }
    
    public Piece getNextPiece() {
        return nextPiece;
    }
    
    public long getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getLinesCleared() {
        return linesCleared;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    /**
     * Interface para listeners de eventos do jogo.
     */
    public interface GameListener {
        void onPieceSpawned(Piece current, Piece next);
        void onPieceMoved(Piece piece);
        void onLinesCleared(int lines, int total);
        void onScoreChanged(long score);
        void onLevelUp(int newLevel);
        void onGameOver(long finalScore);
        void onPauseStateChanged(boolean paused);
        void onGameStateLoaded();
    }
}
