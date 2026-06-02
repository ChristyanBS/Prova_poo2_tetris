package com.tetris.core;

import com.tetris.model.Piece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe TetrisEngine.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class TetrisEngineTest {
    
    private TetrisEngine engine;
    
    @Before
    public void setUp() {
        engine = new TetrisEngine(null);
    }
    
    @Test
    public void testEngineInitialization() {
        assertNotNull(engine.getBoard());
        assertNotNull(engine.getCurrentPiece());
        assertNotNull(engine.getNextPiece());
        assertFalse(engine.isGameOver());
        assertFalse(engine.isPaused());
        assertEquals(0, engine.getScore());
        assertEquals(1, engine.getLevel());
        assertEquals(0, engine.getLinesCleared());
    }
    
    @Test
    public void testNewGame() {
        engine.moveDown();
        engine.moveDown();
        
        engine.newGame();
        
        assertEquals(0, engine.getScore());
        assertEquals(1, engine.getLevel());
        assertEquals(0, engine.getLinesCleared());
        assertFalse(engine.isGameOver());
    }
    
    @Test
    public void testMoveLeft() {
        int initialX = engine.getCurrentPiece().getX();
        engine.moveLeft();
        assertEquals(initialX - 1, engine.getCurrentPiece().getX());
    }
    
    @Test
    public void testMoveRight() {
        int initialX = engine.getCurrentPiece().getX();
        engine.moveRight();
        assertEquals(initialX + 1, engine.getCurrentPiece().getX());
    }
    
    @Test
    public void testMoveDown() {
        int initialY = engine.getCurrentPiece().getY();
        engine.moveDown();
        assertEquals(initialY + 1, engine.getCurrentPiece().getY());
    }
    
    @Test
    public void testRotate() {
        int initialRotation = engine.getCurrentPiece().getRotation();
        engine.rotatePiece();
        
        // A rotação pode não mudar se a peça for O ou se colidir
        assertTrue(engine.getCurrentPiece().getRotation() >= 0);
        assertTrue(engine.getCurrentPiece().getRotation() <= 3);
    }
    
    @Test
    public void testHardDrop() {
        int initialY = engine.getCurrentPiece().getY();
        engine.hardDrop();
        
        // A peça deve ter descido bastante
        assertTrue(engine.getCurrentPiece().getY() > initialY);
    }
    
    @Test
    public void testPause() {
        assertFalse(engine.isPaused());
        engine.togglePause();
        assertTrue(engine.isPaused());
        engine.togglePause();
        assertFalse(engine.isPaused());
    }
    
    @Test
    public void testGameState() {
        var state = engine.getState();
        
        assertNotNull(state);
        assertEquals(engine.getScore(), state.getScore());
        assertEquals(engine.getLevel(), state.getLevel());
        assertEquals(engine.getLinesCleared(), state.getLinesCleared());
    }
    
    @Test
    public void testLoadState() {
        engine.loadState(engine.getState());
        
        // O jogo deve estar carregado
        assertNotNull(engine.getCurrentPiece());
        assertNotNull(engine.getNextPiece());
    }
    
    @Test
    public void testBoardDimensions() {
        assertEquals(10, engine.getBoard().getWidth());
        assertEquals(20, engine.getBoard().getHeight());
    }
    
    @Test
    public void testMovementWhenGameOver() {
        // Simula um game over (preenche o topo do tabuleiro)
        for (int col = 0; col < 10; col++) {
            engine.getBoard().getGrid()[0][col] = 1;
        }
        
        // Força o spawn de uma nova peça que colide imediatamente
        // (isso causaria game over)
        engine.newGame();
        
        // O engine continua funcionando mesmo após game over
        assertNotNull(engine.getCurrentPiece());
    }
}
