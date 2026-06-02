package com.tetris.model;

/**
 * Representa o tabuleiro do Tetris.
 * O tabuleiro é uma matriz que armazena as peças fixadas.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class Board {
    
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 20;
    
    private int width;
    private int height;
    private int[][] grid; // 0 = vazio, > 0 = peça (cor)
    
    /**
     * Constrói um tabuleiro com dimensões padrão.
     */
    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    /**
     * Constrói um tabuleiro com dimensões específicas.
     * 
     * @param width largura do tabuleiro
     * @param height altura do tabuleiro
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
    }
    
    /**
     * Verifica se uma posição no tabuleiro está vazia.
     * 
     * @param x coordenada X
     * @param y coordenada Y
     * @return true se vazio, false se ocupado ou fora do tabuleiro
     */
    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return grid[y][x] == 0;
    }
    
    /**
     * Verifica se uma peça pode ser colocada em uma posição específica.
     * 
     * @param piece a peça a verificar
     * @param x coordenada X
     * @param y coordenada Y
     * @return true se a peça pode ser colocada, false caso contrário
     */
    public boolean canPlacePiece(Piece piece, int x, int y) {
        boolean[][] shape = piece.getShape();
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (shape[row][col]) {
                    int boardX = x + col;
                    int boardY = y + row;
                    
                    // Verifica limites do tabuleiro
                    if (boardX < 0 || boardX >= width || boardY >= height) {
                        return false;
                    }
                    
                    // Verifica se posição está ocupada (ignora y < 0)
                    if (boardY >= 0 && grid[boardY][boardX] != 0) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    /**
     * Fixa uma peça no tabuleiro.
     * 
     * @param piece a peça a fixar
     * @param colorId identificador da cor/tipo da peça
     */
    public void fixPiece(Piece piece, int colorId) {
        boolean[][] shape = piece.getShape();
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (shape[row][col]) {
                    int boardX = piece.getX() + col;
                    int boardY = piece.getY() + row;
                    
                    if (boardY >= 0 && boardY < height && boardX >= 0 && boardX < width) {
                        grid[boardY][boardX] = colorId;
                    }
                }
            }
        }
    }
    
    /**
     * Detecta e remove linhas completas.
     * 
     * @return número de linhas removidas
     */
    public int clearLines() {
        int linesCleared = 0;
        
        for (int row = height - 1; row >= 0; row--) {
            if (isLineFull(row)) {
                clearLine(row);
                linesCleared++;
                row++; // Verifica a mesma linha novamente
            }
        }
        
        return linesCleared;
    }
    
    /**
     * Verifica se uma linha está completa.
     * 
     * @param row número da linha
     * @return true se a linha está completa
     */
    private boolean isLineFull(int row) {
        for (int col = 0; col < width; col++) {
            if (grid[row][col] == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Remove uma linha e move as linhas acima para baixo.
     * 
     * @param row número da linha a remover
     */
    private void clearLine(int row) {
        for (int r = row; r > 0; r--) {
            for (int c = 0; c < width; c++) {
                grid[r][c] = grid[r - 1][c];
            }
        }
        
        // Limpa a primeira linha
        for (int c = 0; c < width; c++) {
            grid[0][c] = 0;
        }
    }
    
    /**
     * Limpa o tabuleiro.
     */
    public void clear() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
            }
        }
    }
    
    /**
     * Retorna uma cópia do tabuleiro.
     * 
     * @return nova instância de Board com o mesmo estado
     */
    public Board copy() {
        Board copy = new Board(this.width, this.height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                copy.grid[row][col] = this.grid[row][col];
            }
        }
        return copy;
    }
    
    // Getters
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int[][] getGrid() {
        return grid;
    }
    
    public int getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[y][x];
        }
        return -1;
    }
}
