# Tetris Game - POO II 2026

Projeto de implementação de um jogo Tetris em Java com interface gráfica Swing, desenvolvido para a disciplina de Programação Orientada a Objetos II do Centro Universitário Anhanguera de Niterói.

## Estrutura do Projeto

```
Prova_poo2_tetris/
├── src/
│   ├── main/java/com/tetris/        # Código-fonte principal
│   │   ├── core/                    # Lógica do jogo
│   │   ├── ui/                      # Interface gráfica (Swing)
│   │   ├── model/                   # Modelos de dados
│   │   └── util/                    # Utilitários e gerenciamento de arquivos
│   └── test/java/com/tetris/        # Testes JUnit
├── docs/                            # Documentação Javadoc
├── release/                         # Versões do MVP
│   ├── v0.1/                        # Tetris básico single-player
│   ├── v0.2/                        # Com threads, níveis e high scores
│   └── v1.0/                        # Com save/load e leaderboard completo
├── pom.xml                          # Configuração Maven
└── README.md                        # Este arquivo

```

## Versões

### v0.1 - Tetris Básico
- ✅ Interface gráfica com Swing
- ✅ Detecção de colisão
- ✅ Fixação de peça no tabuleiro
- ✅ Game Over (quando nova peça colide ao nascer)

### v0.2 - Com Threads e Níveis
- ✅ Threads para controlar velocidade das peças
- ✅ Níveis com dificuldade variável
- ✅ Preview da próxima peça
- ✅ Ranking (high scores) em JSON/YAML
- ✅ Botão para reiniciar o jogo

### v1.0 - Completo com Save/Load
- ✅ Tela inicial (novo jogo / carregar jogo)
- ✅ Tela de entrada de nome do jogador
- ✅ Leaderboard dos 10 melhores placares
- ✅ Save/Load do estado do jogo
- ✅ Persistência em JSON/YAML

## Como Compilar

```bash
# Compilar projeto
mvn clean compile

# Rodar testes
mvn test

# Gerar Javadoc
mvn javadoc:javadoc

# Criar JAR executável
mvn clean package
```

## Como Executar

```bash
# Via Maven
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

# Ou diretamente com Java (após compilar)
java -cp target/tetris-game-1.0.0.jar com.tetris.TetrisGame
```

## Controles do Jogo

- **Setas Esquerda/Direita**: Mover peça
- **Seta Para Cima**: Rotacionar peça
- **Seta Para Baixo**: Acelerar descida
- **Espaço**: Descida rápida (hard drop)
- **P**: Pausar/Retomar
- **R**: Reiniciar (v0.2+)
- **S**: Salvar (v1.0)
- **L**: Carregar (v1.0)
- **ESC**: Menu Principal(v1.0)

## Documentação

A documentação Javadoc completa pode ser acessada em `docs/index.html` após executar `mvn javadoc:javadoc`.

## Testes

Os testes unitários estão em `src/test/java/com/tetris/` e cobrem todas as classes e métodos relevantes.

Execute com: `mvn test`

-----------------------------

02 de junho de 2026
