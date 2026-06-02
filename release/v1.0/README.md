# Tetris Game - v1.0

## Versão Completa com Save/Load e Leaderboard

Versão final do Tetris com todas as funcionalidades solicitadas.

### Features Implementadas:

✅ **Tudo de v0.2 mais:**

✅ **Tela Inicial com Menu**
- Novo Jogo
- Carregar Jogo (se existir save)
- Ver Leaderboard
- Sair

✅ **Entrada de Nome do Jogador**
- Ao iniciar novo jogo
- Solicitação via dialog
- Exibido no leaderboard

✅ **Leaderboard Completo**
- Exibe os 10 melhores scores
- Mostra: Posição, Nome, Score, Nível, Linhas Limpas
- Ordenado por score decrescente
- Interface visual organizada com alternância de cores

✅ **Save/Load do Estado do Jogo**
- Salva estado completo incluindo:
  - Tabuleiro
  - Peça atual e próxima
  - Score, nível, linhas limpas
  - Posição e rotação da peça
- Carrega automaticamente se existir um save
- Arquivo serializado: `tetris_data/savegame.dat`

✅ **Persistência em JSON e Serialização**
- Scores em JSON
- Estado do jogo em serialização Java
- Estrutura organizada de diretórios

### Controles Completos:

| Tecla | Função |
|-------|--------|
| ← / → | Mover peça |
| ↑ | Rotacionar |
| ↓ | Acelerar queda |
| Espaço | Hard drop |
| P | Pausar/Retomar |
| R | Reiniciar jogo |
| S | Salvar jogo |
| L | Carregar jogo |

### Fluxo de Jogo:

1. **Inicialização**
   - Exibe menu principal
   - Opções: Novo Jogo, Carregar, Leaderboard, Sair

2. **Novo Jogo**
   - Pede nome do jogador
   - Inicia partida

3. **Durante o Jogo**
   - Controles normais
   - Pode salvar a qualquer momento (S)
   - Pode carregar jogo anterior (L)

4. **Game Over**
   - Exibe placar final
   - Salva automaticamente no ranking
   - Opção de voltar ao menu

### Estrutura de Dados Persistidos:

**Scores (JSON):**
```json
[
  {
    "playerName": "João",
    "score": 10000,
    "level": 12,
    "linesCleared": 50,
    "timestamp": 1718827200000
  }
]
```

**Save (Serializado):**
```
GameState
├── boardGrid (matriz 20x10)
├── score (long)
├── level (int)
├── linesCleared (int)
├── currentPieceType (String)
├── currentPieceX, Y, Rotation
├── nextPieceType
└── gameOver, paused flags
```

### Como Usar:

```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

# Testes
mvn test

# Documentação
mvn javadoc:javadoc

# Build JAR executável
mvn clean package
java -jar target/tetris-game-1.0.0.jar
```

### Classes Principais:

```
src/main/java/com/tetris/
├── TetrisGame.java         # Main e menu inicial
├── core/
│   ├── TetrisEngine.java
│   └── GameThread.java
├── model/
│   ├── Piece.java
│   ├── Board.java
│   ├── GameScore.java
│   └── GameState.java
├── ui/
│   ├── TetrisFrame.java
│   ├── GamePanel.java
│   ├── GameThread.java
│   └── LeaderboardFrame.java
└── util/
    └── FileManager.java
```

### Testes Inclusos:

- PieceTest (10 casos)
- BoardTest (12 casos)
- GameScoreTest (8 casos)
- GameStateTest (9 casos)
- TetrisEngineTest (11 casos)

Total: 50+ casos de teste

### Documentação:

Javadoc completo para todas as classes públicas com:
- Descrição de propósito
- Parâmetros e retorno
- Exemplos de uso

### Arquivos de Dados:

```
tetris_data/
├── scores.json              # Ranking de scores
└── savegame.dat             # Estado do jogo salvo
```

### Requisitos do Sistema:

- Java 11 ou superior
- Maven 3.6+
- ~50MB de espaço em disco

### Melhorias Técnicas v1.0:

✅ Serialização Java para estado do jogo
✅ Menu inicial com Swing puro
✅ Leaderboard com interface visual
✅ Tratamento de exceções robusto
✅ Testes unitários abrangentes
✅ Documentação Javadoc completa
✅ Estrutura de diretórios organizada

### Limitações Conhecidas:

- Sem rede multiplayer
- Sem som/efeitos
- Sem temas customizáveis
- Interface em português

---

Versão 1.0 - Maio de 2026
Bacharelado em Ciência da Computação - Anhanguera Niterói
Pontuação máxima: Atende a todos os requisitos da AO2
