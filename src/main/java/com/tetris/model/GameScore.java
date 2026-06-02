package com.tetris.model;

import java.io.Serializable;

/**
 * Representa um placar de jogo.
 * Armazena nome do jogador, pontuação, nível e data.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GameScore implements Comparable<GameScore>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String playerName;
    private long score;
    private int level;
    private int linesCleared;
    private long timestamp;
    
    /**
     * Constrói um placar com os dados fornecidos.
     * 
     * @param playerName nome do jogador
     * @param score pontuação
     * @param level nível alcançado
     * @param linesCleared linhas limpas
     */
    public GameScore(String playerName, long score, int level, int linesCleared) {
        this.playerName = playerName;
        this.score = score;
        this.level = level;
        this.linesCleared = linesCleared;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * Compara este placar com outro.
     * Ordena por pontuação decrescente.
     * 
     * @param other outro GameScore
     * @return valor negativo se este é menor, zero se igual, positivo se maior
     */
    @Override
    public int compareTo(GameScore other) {
        // Ordenação decrescente por score
        return Long.compare(other.score, this.score);
    }
    
    @Override
    public String toString() {
        return String.format("%s - Score: %d (Level %d, %d lines)", 
                             playerName, score, level, linesCleared);
    }
    
    // Getters e Setters
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
