package com.tetris.model;

/**
 * Representa uma peça do Tetris.
 * Cada peça possui um tipo (I, O, T, S, Z, J, L), uma posição e rotação.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class Piece {
    
    /**
     * Tipos de peças Tetris disponíveis.
     */
    public enum PieceType {
        I, O, T, S, Z, J, L
    }
    
    private PieceType type;
    private int x;
    private int y;
    private int rotation; // 0, 1, 2, 3
    
    /**
     * Constrói uma peça com tipo, posição e rotação inicial.
     * 
     * @param type tipo da peça
     * @param x coordenada X inicial
     * @param y coordenada Y inicial
     */
    public Piece(PieceType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }
    
    /**
     * Retorna a matriz da peça para a rotação atual.
     * Cada posição true significa que há um bloco naquela célula.
     * 
     * @return matriz 4x4 representando a peça
     */
    public boolean[][] getShape() {
        boolean[][] shape = new boolean[4][4];
        
        switch (type) {
            case I:
                // Peça I (reta)
                if (rotation == 0 || rotation == 2) {
                    shape[1][0] = shape[1][1] = shape[1][2] = shape[1][3] = true;
                } else {
                    shape[0][1] = shape[1][1] = shape[2][1] = shape[3][1] = true;
                }
                break;
                
            case O:
                // Peça O (quadrado) - não rotaciona
                shape[1][1] = shape[1][2] = shape[2][1] = shape[2][2] = true;
                break;
                
            case T:
                // Peça T
                if (rotation == 0) {
                    shape[0][1] = true;
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                } else if (rotation == 1) {
                    shape[0][1] = shape[1][1] = shape[2][1] = true;
                    shape[1][2] = true;
                } else if (rotation == 2) {
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                    shape[2][1] = true;
                } else {
                    shape[0][1] = true;
                    shape[1][0] = shape[1][1] = true;
                    shape[2][1] = true;
                }
                break;
                
            case S:
                // Peça S
                if (rotation == 0 || rotation == 2) {
                    shape[0][1] = shape[0][2] = true;
                    shape[1][0] = shape[1][1] = true;
                } else {
                    shape[0][1] = shape[1][1] = true;
                    shape[1][2] = shape[2][2] = true;
                }
                break;
                
            case Z:
                // Peça Z
                if (rotation == 0 || rotation == 2) {
                    shape[0][0] = shape[0][1] = true;
                    shape[1][1] = shape[1][2] = true;
                } else {
                    shape[0][2] = shape[1][1] = true;
                    shape[1][2] = shape[2][1] = true;
                }
                break;
                
            case J:
                // Peça J
                if (rotation == 0) {
                    shape[0][0] = true;
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                } else if (rotation == 1) {
                    shape[0][1] = shape[0][2] = true;
                    shape[1][1] = true;
                    shape[2][1] = true;
                } else if (rotation == 2) {
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                    shape[2][2] = true;
                } else {
                    shape[0][1] = true;
                    shape[1][1] = true;
                    shape[2][0] = shape[2][1] = true;
                }
                break;
                
            case L:
                // Peça L
                if (rotation == 0) {
                    shape[0][2] = true;
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                } else if (rotation == 1) {
                    shape[0][1] = true;
                    shape[1][1] = true;
                    shape[2][1] = shape[2][2] = true;
                } else if (rotation == 2) {
                    shape[1][0] = shape[1][1] = shape[1][2] = true;
                    shape[2][0] = true;
                } else {
                    shape[0][0] = shape[0][1] = true;
                    shape[1][1] = true;
                    shape[2][1] = true;
                }
                break;
        }
        
        return shape;
    }
    
    /**
     * Rotaciona a peça no sentido horário.
     */
    public void rotate() {
        if (type != PieceType.O) {
            rotation = (rotation + 1) % 4;
        }
    }
    
    /**
     * Reverte a rotação da peça (desfaz a última rotação).
     */
    public void undoRotate() {
        if (type != PieceType.O) {
            rotation = (rotation + 3) % 4;
        }
    }
    
    /**
     * Move a peça para a esquerda.
     */
    public void moveLeft() {
        x--;
    }
    
    /**
     * Move a peça para a direita.
     */
    public void moveRight() {
        x++;
    }
    
    /**
     * Move a peça para baixo.
     */
    public void moveDown() {
        y++;
    }
    
    /**
     * Retorna uma cópia da peça atual.
     * 
     * @return nova instância de Piece com os mesmos atributos
     */
    public Piece copy() {
        Piece copy = new Piece(this.type, this.x, this.y);
        copy.rotation = this.rotation;
        return copy;
    }
    
    // Getters e Setters
    
    public PieceType getType() {
        return type;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getRotation() {
        return rotation;
    }
    
    public void setRotation(int rotation) {
        this.rotation = rotation % 4;
    }
}
