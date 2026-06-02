package com.tetris.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe Board.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class BoardTest {
    
    private Board board;
    
    @Before
    public void setUp() {
        board = new Board(10, 20);
    }
    
    @Test
    public void testBoardCreation() {
        assertEquals(10, board.getWidth());
        assertEquals(20, board.getHeight());
    }
    
    @Test
    public void testDefaultBoardSize() {
        Board defaultBoard = new Board();
        assertEquals(10, defaultBoard.getWidth());
        assertEquals(20, defaultBoard.getHeight());
    }
    
    @Test
    public void testIsValidPosition() {
        assertTrue(board.isValidPosition(5, 5));
        assertFalse(board.isValidPosition(-1, 5));
        assertFalse(board.isValidPosition(10, 5));
        assertFalse(board.isValidPosition(5, 20));
    }
    
    @Test
    public void testCanPlacePiece() {
        Piece piece = new Piece(Piece.PieceType.I, 3, 0);
        assertTrue(board.canPlacePiece(piece, 3, 0));
    }
    
    @Test
    public void testCannotPlacePieceOutOfBounds() {
        Piece piece = new Piece(Piece.PieceType.I, 0, 0);
        assertFalse(board.canPlacePiece(piece, -1, 0));
        assertFalse(board.canPlacePiece(piece, 9, 0));
    }
    
    @Test
    public void testFixPiece() {
        Piece piece = new Piece(Piece.PieceType.O, 3, 5);
        board.fixPiece(piece, 1);
        
        // Verifica se o O foi fixado (4 blocos)
        int count = 0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(j, i) > 0) {
                    count++;
                }
            }
        }
        assertEquals(4, count);
    }
    
    @Test
    public void testClearLines() {
        // Preenche uma linha completa
        for (int col = 0; col < 10; col++) {
            board.getGrid()[19][col] = 1;
        }
        
        int linesCleared = board.clearLines();
        assertEquals(1, linesCleared);
        
        // Verifica se a linha foi limpa
        boolean isLineClear = true;
        for (int col = 0; col < 10; col++) {
            if (board.getCell(col, 19) != 0) {
                isLineClear = false;
                break;
            }
        }
        assertTrue(isLineClear);
    }
    
    @Test
    public void testClearMultipleLines() {
        // Preenche 2 linhas completas
        for (int col = 0; col < 10; col++) {
            board.getGrid()[18][col] = 1;
            board.getGrid()[19][col] = 1;
        }
        
        int linesCleared = board.clearLines();
        assertEquals(2, linesCleared);
    }
    
    @Test
    public void testClear() {
        // Adiciona algumas peças
        board.getGrid()[5][5] = 1;
        board.getGrid()[10][7] = 2;
        
        board.clear();
        
        // Verifica se tudo foi limpo
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                assertEquals(0, board.getCell(col, row));
            }
        }
    }
    
    @Test
    public void testCopy() {
        board.getGrid()[5][5] = 1;
        board.getGrid()[10][7] = 2;
        
        Board copy = board.copy();
        
        assertEquals(board.getWidth(), copy.getWidth());
        assertEquals(board.getHeight(), copy.getHeight());
        
        // Modifica a cópia
        copy.getGrid()[0][0] = 3;
        
        // Verifica se o original não foi modificado
        assertEquals(0, board.getCell(0, 0));
        assertEquals(3, copy.getCell(0, 0));
    }
    
    @Test
    public void testGetCell() {
        board.getGrid()[5][3] = 1;
        assertEquals(1, board.getCell(3, 5));
        assertEquals(0, board.getCell(0, 0));
    }
    
    @Test
    public void testGetCellOutOfBounds() {
        assertEquals(-1, board.getCell(-1, 5));
        assertEquals(-1, board.getCell(5, -1));
        assertEquals(-1, board.getCell(10, 5));
        assertEquals(-1, board.getCell(5, 20));
    }
}
