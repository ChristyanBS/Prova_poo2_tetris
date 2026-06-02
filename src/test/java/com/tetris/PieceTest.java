package com.tetris.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testes unitários para a classe Piece.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class PieceTest {
    
    private Piece piece;
    
    @Before
    public void setUp() {
        piece = new Piece(Piece.PieceType.I, 3, 0);
    }
    
    @Test
    public void testPieceCreation() {
        assertEquals(Piece.PieceType.I, piece.getType());
        assertEquals(3, piece.getX());
        assertEquals(0, piece.getY());
        assertEquals(0, piece.getRotation());
    }
    
    @Test
    public void testMoveLeft() {
        piece.moveLeft();
        assertEquals(2, piece.getX());
    }
    
    @Test
    public void testMoveRight() {
        piece.moveRight();
        assertEquals(4, piece.getX());
    }
    
    @Test
    public void testMoveDown() {
        piece.moveDown();
        assertEquals(1, piece.getY());
    }
    
    @Test
    public void testRotate() {
        piece.rotate();
        assertEquals(1, piece.getRotation());
        piece.rotate();
        assertEquals(2, piece.getRotation());
        piece.rotate();
        piece.rotate();
        assertEquals(0, piece.getRotation());
    }
    
    @Test
    public void testRotateO() {
        Piece oPiece = new Piece(Piece.PieceType.O, 3, 0);
        oPiece.rotate();
        // Peça O não rotaciona
        assertEquals(0, oPiece.getRotation());
    }
    
    @Test
    public void testUndoRotate() {
        piece.rotate();
        assertEquals(1, piece.getRotation());
        piece.undoRotate();
        assertEquals(0, piece.getRotation());
    }
    
    @Test
    public void testGetShapeI() {
        boolean[][] shape = piece.getShape();
        // Peça I no estado inicial (horizontal)
        assertNotNull(shape);
        assertEquals(4, shape.length);
    }
    
    @Test
    public void testCopy() {
        piece.moveRight();
        piece.rotate();
        
        Piece copy = piece.copy();
        
        assertEquals(piece.getType(), copy.getType());
        assertEquals(piece.getX(), copy.getX());
        assertEquals(piece.getY(), copy.getY());
        assertEquals(piece.getRotation(), copy.getRotation());
        
        // Modifica a cópia - não deve afetar o original
        copy.moveRight();
        assertEquals(4, piece.getX());
        assertEquals(5, copy.getX());
    }
    
    @Test
    public void testSetters() {
        piece.setX(5);
        piece.setY(10);
        piece.setRotation(2);
        
        assertEquals(5, piece.getX());
        assertEquals(10, piece.getY());
        assertEquals(2, piece.getRotation());
    }
    
    @Test
    public void testAllPieceTypes() {
        for (Piece.PieceType type : Piece.PieceType.values()) {
            Piece p = new Piece(type, 0, 0);
            assertNotNull(p.getShape());
        }
    }
}
