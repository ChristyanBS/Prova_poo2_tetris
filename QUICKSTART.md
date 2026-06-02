# QuickStart - Tetris Game

## Instruções Rápidas de Execução

### Pré-requisitos

1. **Java 11 ou superior**
   ```bash
   java -version
   ```
   Se não tiver, baixe em: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

2. **Maven 3.6 ou superior** (OBRIGATÓRIO)
   ```bash
   mvn --version
   ```
   
   Se não tiver, siga os passos abaixo:

### Instalando Maven

#### Windows

1. Baixe Maven em: https://maven.apache.org/download.cgi (Binary zip archive)
2. Extraia em uma pasta, ex: `C:\maven`
3. Adicione `C:\maven\bin` ao PATH do Windows:
   - Abra "Variáveis de Ambiente"
   - Clique em "Variáveis de Ambiente"
   - Em "Variáveis do Sistema", clique em PATH → Editar
   - Clique em "Novo" e adicione: `C:\maven\bin`
   - OK, OK, OK
4. Reinicie o terminal/CMD
5. Teste: `mvn --version`

#### Linux/Mac

```bash
# Ubuntu/Debian
sudo apt-get install maven

# Mac com Homebrew
brew install maven

# Ou baixe manualmente em: https://maven.apache.org/download.cgi
# Extraia em ~/maven e adicione ao PATH
```

### Compilar e Executar

Após instalar Maven, no diretório do projeto:

#### Opção 1: Usando o Script

**Windows:**
```bash
build.bat
```
Escolha opção 5 para executar

**Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```
Escolha opção 5 para executar

#### Opção 2: Comandos Maven Diretos

**Compilar:**
```bash
mvn clean compile
```

**Rodar Testes:**
```bash
mvn test
```

**Gerar Javadoc:**
```bash
mvn javadoc:javadoc
```

**Criar JAR Executável:**
```bash
mvn clean package
```

**Executar o Jogo:**
```bash
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
```

**Ou com o JAR após build:**
```bash
java -jar target/tetris-game-1.0.0.jar
```

### Primeira Execução

```bash
cd "c:\Users\Christyan\Documents\Faculdade\Prova_poo2_tetris"
mvn clean compile exec:java -Dexec.mainClass="com.tetris.TetrisGame"
```

O jogo deve abrir em uma janela.

### Controles

| Tecla | Ação |
|-------|------|
| ← / → | Mover |
| ↑ | Rotacionar |
| ↓ | Acelerar |
| Espaço | Queda rápida |
| P | Pausar |
| R | Reiniciar |
| S | Salvar |
| L | Carregar |

### Arquivos Gerados

Ao jogar, serão criados:
- `tetris_data/scores.json` - Ranking
- `tetris_data/savegame.dat` - Save do jogo

### Documentação

Gere a documentação:
```bash
mvn javadoc:javadoc
```

Abra em seu navegador: `docs/index.html`

### Solução de Problemas

**Erro: "mvn: command not found"**
- Maven não está no PATH
- Siga os passos de instalação acima

**Erro: "Cannot find symbol"**
- Verifique que as dependências estão no pom.xml
- Tente: `mvn clean package`

**Erro: "Invalid source release"**
- Java 11+ é necessário
- Atualize: `java -version`

**Erro: "ClassNotFoundException"**
- Compile primeiro: `mvn clean compile`

**Javadoc não gera**
- Verificar permissões na pasta `docs/`
- Tente: `mvn clean javadoc:javadoc`

### Estrutura do Projeto

```
Prova_poo2_tetris/
├── src/
│   ├── main/java/com/tetris/     ← Código-fonte principal
│   └── test/java/com/tetris/     ← Testes
├── target/                        ← Compilado (após mvn)
├── docs/                          ← Javadoc (após mvn javadoc)
├── pom.xml                        ← Configuração Maven
├── build.bat                      ← Script Windows
├── build.sh                       ← Script Linux/Mac
└── README.md                      ← Este arquivo
```

### Versões

- **v0.1**: Tetris básico
- **v0.2**: Com threads e níveis
- **v1.0**: Completo com save/load

Veja os READMEs em `release/` para detalhes de cada versão.

### Entrega

Antes de entregar, prepare:

1. ✅ Código compilando sem erros
2. ✅ Testes passando: `mvn test`
3. ✅ Javadoc gerado: `mvn javadoc:javadoc`
4. ✅ CONTRIBUTORS.md preenchido com nomes e RAs
5. ✅ Push para GitHub
6. ✅ Email com o link do repositório

---

Boa sorte! 🎮

Para dúvidas: Consulte README.md e ARCHITECTURE.md
