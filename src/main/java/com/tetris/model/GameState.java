package com.tetris.model;

import java.io.Serializable;

/**
 * Representa o estado completo de uma partida de Tetris.
 * Utilizado para save/load do jogo.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GameState implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int[][] boardGrid;
    private int boardWidth;
    private int boardHeight;
    private long score;
    private int level;
    private int linesCleared;
    private String currentPieceType;
    private int currentPieceX;
    private int currentPieceY;
    private int currentPieceRotation;
    private String nextPieceType;
    private boolean gameOver;
    private boolean paused;
    private long elapsedTime;
    
    /**
     * Construtor vazio para desserialização.
     */
    public GameState() {
    }
    
    /**
     * Constrói um estado de jogo com os dados fornecidos.
     * 
     * @param boardGrid grade do tabuleiro
     * @param boardWidth largura do tabuleiro
     * @param boardHeight altura do tabuleiro
     * @param score pontuação atual
     * @param level nível atual
     * @param linesCleared linhas limpas
     */
    public GameState(int[][] boardGrid, int boardWidth, int boardHeight,
                     long score, int level, int linesCleared) {
        this.boardGrid = copyGrid(boardGrid);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.score = score;
        this.level = level;
        this.linesCleared = linesCleared;
    }
    
    /**
     * Copia uma grade bidimensional.
     * 
     * @param grid grade a copiar
     * @return cópia da grade
     */
    private int[][] copyGrid(int[][] grid) {
        if (grid == null) return null;
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }
    
    // Getters e Setters
    
    public int[][] getBoardGrid() {
        return boardGrid;
    }
    
    public void setBoardGrid(int[][] boardGrid) {
        this.boardGrid = copyGrid(boardGrid);
    }
    
    public int getBoardWidth() {
        return boardWidth;
    }
    
    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }
    
    public int getBoardHeight() {
        return boardHeight;
    }
    
    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }
    
    public long getScore() {
        return score;
    }
    
    public void setScore(long score) {
        this.score = score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getLinesCleared() {
        return linesCleared;
    }
    
    public void setLinesCleared(int linesCleared) {
        this.linesCleared = linesCleared;
    }
    
    public String getCurrentPieceType() {
        return currentPieceType;
    }
    
    public void setCurrentPieceType(String currentPieceType) {
        this.currentPieceType = currentPieceType;
    }
    
    public int getCurrentPieceX() {
        return currentPieceX;
    }
    
    public void setCurrentPieceX(int currentPieceX) {
        this.currentPieceX = currentPieceX;
    }
    
    public int getCurrentPieceY() {
        return currentPieceY;
    }
    
    public void setCurrentPieceY(int currentPieceY) {
        this.currentPieceY = currentPieceY;
    }
    
    public int getCurrentPieceRotation() {
        return currentPieceRotation;
    }
    
    public void setCurrentPieceRotation(int currentPieceRotation) {
        this.currentPieceRotation = currentPieceRotation;
    }
    
    public String getNextPieceType() {
        return nextPieceType;
    }
    
    public void setNextPieceType(String nextPieceType) {
        this.nextPieceType = nextPieceType;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    public long getElapsedTime() {
        return elapsedTime;
    }
    
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
