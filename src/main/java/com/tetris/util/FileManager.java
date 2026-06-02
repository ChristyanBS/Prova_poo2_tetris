package com.tetris.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tetris.model.GameScore;
import com.tetris.model.GameState;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Utilitário para gerenciar persistência de dados em JSON/YAML.
 * Responsável por salvar e carregar scores e estado do jogo.
 * 
 * @author POO II 2026
 * @version 1.0
 */
public class FileManager {
    
    private static final String SCORES_FILE = "scores.json";
    private static final String SAVE_FILE = "savegame.dat";
    private static final String DATA_DIR = "tetris_data";
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    static {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de dados: " + e.getMessage());
        }
    }
    
    /**
     * Salva um score em arquivo JSON.
     * 
     * @param score o GameScore a salvar
     * @throws IOException se ocorrer erro de I/O
     */
    public static void saveScore(GameScore score) throws IOException {
        List<GameScore> scores = loadScores();
        scores.add(score);
        Collections.sort(scores);
        
        // Mantém apenas os 100 melhores scores
        if (scores.size() > 100) {
            scores = scores.subList(0, 100);
        }
        
        String json = gson.toJson(scores);
        Files.write(Paths.get(DATA_DIR, SCORES_FILE), json.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Carrega todos os scores do arquivo JSON.
     * 
     * @return lista de GameScore ordenada por score
     * @throws IOException se ocorrer erro de I/O
     */
    public static List<GameScore> loadScores() throws IOException {
        Path scoresPath = Paths.get(DATA_DIR, SCORES_FILE);
        
        if (!Files.exists(scoresPath)) {
            return new ArrayList<>();
        }
        
        String json = new String(Files.readAllBytes(scoresPath), StandardCharsets.UTF_8);
        GameScore[] scoresArray = gson.fromJson(json, GameScore[].class);
        
        if (scoresArray == null) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(Arrays.asList(scoresArray));
    }
    
    /**
     * Retorna os 10 melhores scores.
     * 
     * @return lista com até 10 melhores scores
     * @throws IOException se ocorrer erro de I/O
     */
    public static List<GameScore> getTopScores(int count) throws IOException {
        List<GameScore> scores = loadScores();
        if (scores.size() > count) {
            return scores.subList(0, count);
        }
        return scores;
    }
    
    /**
     * Limpa todos os scores.
     * 
     * @throws IOException se ocorrer erro de I/O
     */
    public static void clearScores() throws IOException {
        Path scoresPath = Paths.get(DATA_DIR, SCORES_FILE);
        if (Files.exists(scoresPath)) {
            Files.delete(scoresPath);
        }
    }
    
    /**
     * Salva o estado do jogo em arquivo serializado.
     * 
     * @param state o GameState a salvar
     * @throws IOException se ocorrer erro de I/O
     */
    public static void saveGameState(GameState state) throws IOException {
        Path savePath = Paths.get(DATA_DIR, SAVE_FILE);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(savePath, StandardOpenOption.CREATE, 
                                     StandardOpenOption.TRUNCATE_EXISTING))) {
            oos.writeObject(state);
        }
    }
    
    /**
     * Carrega o estado do jogo do arquivo serializado.
     * 
     * @return GameState carregado, ou null se não existir
     * @throws IOException se ocorrer erro de I/O
     * @throws ClassNotFoundException se a classe não for encontrada
     */
    public static GameState loadGameState() throws IOException, ClassNotFoundException {
        Path savePath = Paths.get(DATA_DIR, SAVE_FILE);
        
        if (!Files.exists(savePath)) {
            return null;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(savePath))) {
            return (GameState) ois.readObject();
        }
    }
    
    /**
     * Deleta o arquivo de save.
     * 
     * @throws IOException se ocorrer erro de I/O
     */
    public static void deleteSaveFile() throws IOException {
        Path savePath = Paths.get(DATA_DIR, SAVE_FILE);
        if (Files.exists(savePath)) {
            Files.delete(savePath);
        }
    }
    
    /**
     * Verifica se existe um save anterior.
     * 
     * @return true se existe um save, false caso contrário
     */
    public static boolean hasSaveFile() {
        Path savePath = Paths.get(DATA_DIR, SAVE_FILE);
        return Files.exists(savePath);
    }
}
