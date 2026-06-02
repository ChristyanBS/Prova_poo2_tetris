package com.tetris.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe GameScore.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GameScoreTest {
    
    private GameScore score1;
    private GameScore score2;
    
    @Before
    public void setUp() {
        score1 = new GameScore("Player1", 1000, 5, 20);
        score2 = new GameScore("Player2", 500, 3, 10);
    }
    
    @Test
    public void testGameScoreCreation() {
        assertEquals("Player1", score1.getPlayerName());
        assertEquals(1000, score1.getScore());
        assertEquals(5, score1.getLevel());
        assertEquals(20, score1.getLinesCleared());
    }
    
    @Test
    public void testTimestamp() {
        long beforeCreation = System.currentTimeMillis();
        GameScore score = new GameScore("Test", 100, 1, 5);
        long afterCreation = System.currentTimeMillis();
        
        assertTrue(score.getTimestamp() >= beforeCreation);
        assertTrue(score.getTimestamp() <= afterCreation);
    }
    
    @Test
    public void testCompareTo() {
        // score1 tem score maior (1000) que score2 (500)
        // Comparação decrescente, então score1 deve ser "menor" que score2
        int comparison = score1.compareTo(score2);
        assertTrue(comparison < 0);
    }
    
    @Test
    public void testCompareToEqual() {
        GameScore score3 = new GameScore("Player3", 1000, 5, 20);
        int comparison = score1.compareTo(score3);
        assertEquals(0, comparison);
    }
    
    @Test
    public void testCompareToReverse() {
        int comparison = score2.compareTo(score1);
        assertTrue(comparison > 0);
    }
    
    @Test
    public void testSetters() {
        score1.setPlayerName("NewName");
        score1.setScore(2000);
        score1.setLevel(10);
        score1.setLinesCleared(40);
        
        assertEquals("NewName", score1.getPlayerName());
        assertEquals(2000, score1.getScore());
        assertEquals(10, score1.getLevel());
        assertEquals(40, score1.getLinesCleared());
    }
    
    @Test
    public void testToString() {
        String str = score1.toString();
        assertTrue(str.contains("Player1"));
        assertTrue(str.contains("1000"));
        assertTrue(str.contains("5"));
        assertTrue(str.contains("20"));
    }
    
    @Test
    public void testSerialization() {
        assertTrue(score1 instanceof java.io.Serializable);
    }
}
