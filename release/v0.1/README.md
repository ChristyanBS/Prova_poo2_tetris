# Tetris Game - v0.1

## Versão Mínimo Produto Viável - Tetris Básico

Esta é a primeira versão do Tetris com funcionalidades básicas.

### Features Implementadas:

✅ **Interface Gráfica com Swing**
- Tabuleiro 10x20 com grade visual
- Renderização das peças com cores diferentes
- Informações de score, nível e linhas limpas em tempo real

✅ **Mecânica de Jogo Completa**
- 7 tipos de peças Tetris (I, O, T, S, Z, J, L)
- Movimentação: esquerda, direita, descida e rotação
- Hard drop (descida rápida com espaço)

✅ **Detecção de Colisão e Fixação**
- Verificação precisa de colisão com bordas e outras peças
- Fixação automática de peças quando atingem o tabuleiro

✅ **Game Over**
- Detecta quando uma nova peça colide ao nascer
- Mensagem clara de fim de jogo

### Controles:

- **Setas Esquerda/Direita**: Mover a peça
- **Seta Para Cima**: Rotacionar a peça no sentido horário
- **Seta Para Baixo**: Acelerar a descida
- **Espaço**: Hard drop (queda livre até o fundo)
- **R**: Reiniciar o jogo

### Como Compilar e Executar:

```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
```

### Estrutura do Código:

```
src/main/java/com/tetris/
├── model/
│   ├── Piece.java          # Representa uma peça Tetris
│   ├── Board.java          # Tabuleiro do jogo
│   └── GameScore.java      # Placar de jogo
├── core/
│   └── TetrisEngine.java   # Lógica principal do jogo
└── ui/
    ├── GamePanel.java      # Painel de renderização
    ├── TetrisFrame.java    # Janela principal
    └── GameThread.java     # Thread do loop do jogo
```

### Limitações v0.1:

- Sem threads explícitas (velocidade fixa)
- Sem níveis de dificuldade
- Sem preview da próxima peça
- Sem persistência de dados
- Sem menu inicial

### Próximos Passos (v0.2):

- Adicionar threads para controle de velocidade
- Implementar níveis com dificuldade variável
- Preview da próxima peça
- Ranking de high scores em JSON
- Botão de reinício

### Testes:

Execute os testes com:
```bash
mvn test
```

Estão inclusos testes para as principais classes:
- PieceTest
- BoardTest
- GameScoreTest
- TetrisEngineTest

### Documentação:

Gere a documentação Javadoc com:
```bash
mvn javadoc:javadoc
```

A documentação estará em `docs/index.html`

---

Versão 0.1 - Maio de 2026
Bacharelado em Ciência da Computação - Anhanguera Niterói
