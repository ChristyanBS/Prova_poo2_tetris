# Documentação do Projeto Tetris

## Visão Geral

Este é um projeto de implementação do clássico jogo Tetris em Java com interface gráfica Swing. O projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos II do Centro Universitário Anhanguera de Niterói, seguindo as requisições da Avaliação Oficial 2 (AO2).

## Arquitetura do Projeto

### Pacotes e Responsabilidades

#### `com.tetris.model` - Modelos de Dados
- **Piece**: Representa uma peça Tetris com posição, rotação e tipo
- **Board**: Tabuleiro 10x20 com detecção de colisão e limpeza de linhas
- **GameScore**: Dados de um placar individual
- **GameState**: Estado completo do jogo para serialização

#### `com.tetris.core` - Lógica do Jogo
- **TetrisEngine**: Engine principal que gerencia:
  - Movimento de peças
  - Detecção de colisão
  - Pontuação e níveis
  - Estado do jogo
  
#### `com.tetris.ui` - Interface Gráfica
- **GamePanel**: Renderização visual do jogo com Graphics2D
- **TetrisFrame**: Janela principal
- **GameThread**: Thread que controla o loop do jogo
- **LeaderboardFrame**: Janela de leaderboard

#### `com.tetris.util` - Utilitários
- **FileManager**: Persistência em JSON e serialização

#### `com.tetris` - Aplicação Principal
- **TetrisGame**: Ponto de entrada com menu inicial

## Padrões de Design Utilizados

### 1. **Model-View-Controller (MVC)**
- **Model**: Classes em `model/` + `TetrisEngine`
- **View**: Classes em `ui/`
- **Controller**: `TetrisFrame` coordena

### 2. **Observer Pattern**
- `TetrisEngine.GameListener` para eventos do jogo
- Implementado por `TetrisFrame`

### 3. **Thread Pattern**
- `GameThread` gerencia o loop do jogo
- Sincronização com `SwingUtilities.invokeLater()`

### 4. **Facade Pattern**
- `FileManager` abstrai operações de I/O

## Fluxo da Aplicação

```
TetrisGame.main()
    ↓
showStartMenu() - Menu Principal
    ├─ Novo Jogo → TetrisFrame + playerName
    ├─ Carregar Jogo → TetrisFrame (carrega state)
    ├─ Leaderboard → LeaderboardFrame
    └─ Sair → System.exit()

TetrisFrame
    ├─ Cria TetrisEngine
    ├─ Cria GamePanel (renderização)
    └─ Inicia GameThread (loop)

GameThread
    ├─ Calcula delay baseado em level
    ├─ Chama engine.moveDown()
    └─ Repinta GamePanel

GamePanel
    ├─ Renderiza Board
    ├─ Renderiza Piece atual
    ├─ Renderiza preview da próxima
    └─ Trata entrada do teclado

TetrisEngine
    ├─ Valida movimentos
    ├─ Detecta colisões
    ├─ Limpa linhas
    ├─ Calcula pontuação
    └─ Notifica listeners
```

## Algoritmos Principais

### Detecção de Colisão

```java
boolean canPlacePiece(Piece piece, int x, int y) {
    for (int row = 0; row < 4; row++) {
        for (int col = 0; col < 4; col++) {
            if (shape[row][col]) {
                int boardX = x + col;
                int boardY = y + row;
                
                // Verifica limites e células ocupadas
                if (boardX < 0 || boardX >= width || 
                    boardY >= height ||
                    grid[boardY][boardX] != 0) {
                    return false;
                }
            }
        }
    }
    return true;
}
```

### Limpeza de Linhas

```java
int clearLines() {
    int linesCleared = 0;
    
    for (int row = height - 1; row >= 0; row--) {
        if (isLineFull(row)) {
            clearLine(row);           // Move tudo acima para baixo
            linesCleared++;
            row++;                    // Verifica a mesma linha novamente
        }
    }
    
    return linesCleared;
}
```

### Pontuação

```
1 linha  = 40 * (nível + 1)
2 linhas = 100 * (nível + 1)
3 linhas = 300 * (nível + 1)
4 linhas = 1200 * (nível + 1)   // Tetris!
```

### Velocidade por Nível

```
delay (ms) = max(1000 - (level - 1) * 50, 100)

Nível 1: 1000ms
Nível 2: 950ms
...
Nível 20: 100ms (mínimo)
```

## Formas das Peças

Cada peça é representada por uma matriz 4x4 booleana:

```
I: ████     T: █      O: ██     S:  ██
   ····        ███       ██        ██

L: █       J:   █     Z: ██      
   ███         ███         ██
```

## Persistência de Dados

### Scores (JSON)

```
tetris_data/scores.json
[
  {
    "playerName": "String",
    "score": long,
    "level": int,
    "linesCleared": int,
    "timestamp": long
  },
  ...
]
```

### Estado do Jogo (Serialização Java)

```
tetris_data/savegame.dat
GameState object serializado
├── boardGrid: int[20][10]
├── score, level, linesCleared
├── currentPiece (tipo, pos, rot)
├── nextPiece (tipo)
└── flags (gameOver, paused)
```

## Testes Unitários

Cobertura de teste para as principais classes:

| Classe | Testes | Casos |
|--------|--------|-------|
| Piece | PieceTest | 10 |
| Board | BoardTest | 12 |
| GameScore | GameScoreTest | 8 |
| GameState | GameStateTest | 9 |
| TetrisEngine | TetrisEngineTest | 11 |

Total: 50+ casos de teste

Execute com: `mvn test`

## Documentação Javadoc

Todas as classes públicas possuem documentação completa:
- Descrição da classe
- Descrição de cada método público
- Parâmetros (`@param`)
- Retorno (`@return`)
- Exceções (`@throws`)
- Exemplos onde aplicável

Gere com: `mvn javadoc:javadoc`

## Compilação e Execução

### Pré-requisitos
- Java 11+
- Maven 3.6+

### Comandos

```bash
# Compilar
mvn clean compile

# Rodar testes
mvn test

# Gerar Javadoc
mvn javadoc:javadoc

# Build JAR
mvn clean package

# Executar
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

# Ou com o JAR
java -jar target/tetris-game-1.0.0.jar
```

## Estrutura de Diretórios

```
Prova_poo2_tetris/
├── src/
│   ├── main/java/com/tetris/
│   │   ├── TetrisGame.java
│   │   ├── core/
│   │   │   ├── TetrisEngine.java
│   │   │   └── GameThread.java
│   │   ├── model/
│   │   │   ├── Piece.java
│   │   │   ├── Board.java
│   │   │   ├── GameScore.java
│   │   │   └── GameState.java
│   │   ├── ui/
│   │   │   ├── GamePanel.java
│   │   │   ├── TetrisFrame.java
│   │   │   ├── GameThread.java
│   │   │   └── LeaderboardFrame.java
│   │   └── util/
│   │       └── FileManager.java
│   └── test/java/com/tetris/
│       ├── PieceTest.java
│       ├── BoardTest.java
│       ├── GameScoreTest.java
│       ├── GameStateTest.java
│       └── TetrisEngineTest.java
├── docs/
│   └── [Javadoc gerado]
├── release/
│   ├── v0.1/
│   ├── v0.2/
│   └── v1.0/
├── pom.xml
└── README.md
```

## Controles do Jogo

| Controle | Ação |
|----------|------|
| ← | Mover para esquerda |
| → | Mover para direita |
| ↑ | Rotacionar no sentido horário |
| ↓ | Acelerar descida |
| Espaço | Hard drop (queda livre) |
| P | Pausar/Retomar |
| R | Reiniciar jogo |
| S | Salvar jogo |
| L | Carregar jogo |

## Versões

### v0.1 - Tetris Básico
- Interface gráfica
- Movimentação e rotação
- Detecção de colisão
- Game over

### v0.2 - Com Threads e Níveis
- Threads para controle de velocidade
- Níveis com dificuldade variável
- Preview da próxima peça
- Ranking em JSON

### v1.0 - Completo
- Menu inicial
- Leaderboard
- Save/Load
- Entrada de nome
- Tela de resultados

## Conformidade com Requisitos

✅ Interface gráfica com Swing (v0.1)
✅ Detecção de colisão e fixação (v0.1)
✅ Game over (v0.1)
✅ Threads para velocidade (v0.2)
✅ Níveis com dificuldade variável (v0.2)
✅ Preview da próxima peça (v0.2)
✅ Ranking em JSON (v0.2)
✅ Botão reiniciar (v0.2)
✅ Menu inicial (v1.0)
✅ Entrada de nome (v1.0)
✅ Leaderboard top 10 (v1.0)
✅ Save/Load (v1.0)
✅ Documentação Javadoc
✅ Testes unitários JUnit

---

Documentação - Tetris Game v1.0
Junho de 2026
