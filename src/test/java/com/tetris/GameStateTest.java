package com.tetris.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe GameState.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class GameStateTest {
    
    private GameState state;
    private int[][] testGrid;
    
    @Before
    public void setUp() {
        testGrid = new int[20][10];
        testGrid[5][5] = 1;
        state = new GameState(testGrid, 10, 20, 500, 2, 5);
    }
    
    @Test
    public void testGameStateCreation() {
        assertEquals(10, state.getBoardWidth());
        assertEquals(20, state.getBoardHeight());
        assertEquals(500, state.getScore());
        assertEquals(2, state.getLevel());
        assertEquals(5, state.getLinesCleared());
    }
    
    @Test
    public void testGridCopy() {
        // Modifica a grade original
        testGrid[5][5] = 2;
        
        // O estado deve ter uma cópia independente
        assertEquals(1, state.getBoardGrid()[5][5]);
    }
    
    @Test
    public void testSetBoardGrid() {
        int[][] newGrid = new int[20][10];
        newGrid[3][3] = 5;
        
        state.setBoardGrid(newGrid);
        assertEquals(5, state.getBoardGrid()[3][3]);
        
        // Modifica a nova grade
        newGrid[3][3] = 10;
        
        // O estado deve ter uma cópia independente
        assertEquals(5, state.getBoardGrid()[3][3]);
    }
    
    @Test
    public void testSetters() {
        state.setScore(1000);
        state.setLevel(5);
        state.setLinesCleared(10);
        state.setGameOver(true);
        state.setPaused(true);
        
        assertEquals(1000, state.getScore());
        assertEquals(5, state.getLevel());
        assertEquals(10, state.getLinesCleared());
        assertTrue(state.isGameOver());
        assertTrue(state.isPaused());
    }
    
    @Test
    public void testPieceState() {
        state.setCurrentPieceType("I");
        state.setCurrentPieceX(3);
        state.setCurrentPieceY(5);
        state.setCurrentPieceRotation(1);
        state.setNextPieceType("O");
        
        assertEquals("I", state.getCurrentPieceType());
        assertEquals(3, state.getCurrentPieceX());
        assertEquals(5, state.getCurrentPieceY());
        assertEquals(1, state.getCurrentPieceRotation());
        assertEquals("O", state.getNextPieceType());
    }
    
    @Test
    public void testSerialization() {
        assertTrue(state instanceof java.io.Serializable);
    }
    
    @Test
    public void testDefaultConstructor() {
        GameState emptyState = new GameState();
        assertNotNull(emptyState);
    }
}
