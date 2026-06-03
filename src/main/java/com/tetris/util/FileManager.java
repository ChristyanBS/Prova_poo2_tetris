package com.tetris.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tetris.model.GameScore;
import com.tetris.model.GameState;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    private static final String SCORES_FILE = "scores.json";
    private static final String SAVE_FILE = "savegame.json";
    private static final Path DATA_DIR;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        // Usa diretório no home do usuário para garantir permissões de escrita
        DATA_DIR = Paths.get(System.getProperty("user.home"), ".tetris-game");
        try {
            Files.createDirectories(DATA_DIR);
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de dados: " + e.getMessage());
            System.err.println("Diretório tentado: " + DATA_DIR.toAbsolutePath());
        }
    }

    public static void saveScore(GameScore score) throws IOException {
        List<GameScore> scores = loadScores();
        scores.add(score);
        Collections.sort(scores);
        if (scores.size() > 100) scores = scores.subList(0, 100);
        Files.write(DATA_DIR.resolve(SCORES_FILE), gson.toJson(scores).getBytes(StandardCharsets.UTF_8));
    }

    public static List<GameScore> loadScores() throws IOException {
        try {
            Path path = DATA_DIR.resolve(SCORES_FILE);
            if (!Files.exists(path)) return new ArrayList<>();
            String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            if (json.trim().isEmpty()) return new ArrayList<>();
            GameScore[] arr = gson.fromJson(json, GameScore[].class);
            if (arr == null) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(arr));
        } catch (Exception e) {
            // Se der erro de JSON corrompido, retorna lista vazia e evita travar o Leaderboard!
            return new ArrayList<>();
        }
    }

    public static List<GameScore> getTopScores(int count) throws IOException {
        List<GameScore> scores = loadScores();
        if (scores.size() > count) return scores.subList(0, count);
        return scores;
    }

    public static void saveGameState(GameState state) throws IOException {
        String json = gson.toJson(state);
        Files.write(DATA_DIR.resolve(SAVE_FILE), json.getBytes(StandardCharsets.UTF_8));
    }

    public static GameState loadGameState() throws Exception {
        Path path = DATA_DIR.resolve(SAVE_FILE);
        if (!Files.exists(path)) return null;
        String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        return gson.fromJson(json, GameState.class);
    }

    public static boolean hasSaveFile() {
        return Files.exists(DATA_DIR.resolve("savegame.json")) || Files.exists(DATA_DIR.resolve("savegame.dat"));
    }
}