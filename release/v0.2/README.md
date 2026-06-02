# Tetris Game - v0.2

## Versão com Threads, Níveis e High Scores

Segunda versão do Tetris com melhorias significativas.

### Features Implementadas:

✅ **Tudo de v0.1 mais:**

✅ **Threads para Controle de Velocidade**
- GameThread gerencia o loop do jogo
- Velocidade baseada no nível (começa em 1000ms, mínimo 100ms)
- Sincronização adequada com a UI Swing

✅ **Níveis de Dificuldade Variável**
- Começa no nível 1
- Aumenta a cada 10 linhas limpas
- Velocidade aumenta com cada novo nível

✅ **Preview da Próxima Peça**
- Painel à parte mostrando a próxima peça em tamanho reduzido
- Atualiza em tempo real

✅ **Ranking de High Scores**
- Salva scores em arquivo JSON
- Armazena: nome do jogador, score, nível, linhas limpas, timestamp
- Mantém os 100 melhores scores

✅ **Botão para Reiniciar (Tecla R)**
- Sem sair do jogo
- Reinicia o tabuleiro mantendo a janela aberta

### Controles Adicionais:

- **R**: Reiniciar o jogo sem sair
- **P**: Pausar/Retomar o jogo
- **S**: Salvar o jogo (para v1.0)
- **L**: Carregar o jogo (para v1.0)

### Persistência de Dados:

Os scores são salvos em `tetris_data/scores.json` com o seguinte formato:

```json
[
  {
    "playerName": "João Silva",
    "score": 5000,
    "level": 8,
    "linesCleared": 32,
    "timestamp": 1718827200000
  },
  ...
]
```

### Como Compilar e Executar:

```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

# Rodar testes
mvn test

# Gerar Javadoc
mvn javadoc:javadoc
```

### Novas Classes:

```
src/main/java/com/tetris/
├── util/
│   └── FileManager.java    # Gerencia persistência JSON
├── core/
│   └── GameThread.java     # Thread do loop (movido)
└── model/
    ├── GameState.java      # Estado do jogo para save/load
    └── [anteriores]
```

### Algoritmo de Pontuação:

```
1 linha  = 40 * (nível + 1) pontos
2 linhas = 100 * (nível + 1) pontos
3 linhas = 300 * (nível + 1) pontos
4 linhas (Tetris) = 1200 * (nível + 1) pontos
```

### Próximos Passos (v1.0):

- Menu inicial
- Tela de entrada de nome do jogador
- Leaderboard dos 10 melhores scores
- Save/Load do estado do jogo
- Tela inicial para escolher novo jogo ou carregar

---

Versão 0.2 - Maio de 2026
Bacharelado em Ciência da Computação - Anhanguera Niterói
