# 🚀 GUIA COMPLETO - GIT SETUP & PUSH PARA GITHUB

## ✅ O QUE JÁ FOI FEITO

```
✅ Git inicializado localmente
✅ Todos os arquivos adicionados ao Git
✅ Primeira commit realizada (v1.0 - versão final)
✅ Branches criadas para cada versão:
   - master (branch padrão com v1.0)
   - v0.1 (versão básica)
   - v0.2 (com threads e níveis)
   - v1.0 (versão completa)
```

---

## 📋 PRÓXIMOS PASSOS

### 1️⃣ CRIAR REPOSITÓRIO NO GITHUB

Acesse: https://github.com/new

**Configure assim:**

```
Repository name: Prova_poo2_tetris
Description: Tetris Game - POO II 2026 - AO2

Owner: [seu usuário]
Public: ✅ MARCAR (MUITO IMPORTANTE!)

Add .gitignore: Java (já temos, mas reforça)
Add a license: MIT (opcional)
Initialize with README: ⭕ NÃO MARCAR (já temos)
```

Clique em **"Create repository"** e GitHub vai mostrar a URL.

---

### 2️⃣ COPIAR A URL DO REPOSITÓRIO

Após criar, GitHub vai mostrar algo como:

```
https://github.com/christyanbsr/Prova_poo2_tetris.git
```

**COPIE ESSA URL** (você vai usar no próximo passo)

---

### 3️⃣ CONECTAR COM GITHUB

No PowerShell/Terminal, execute:

```bash
cd "c:\Users\Christyan\Documents\Faculdade\Prova_poo2_tetris"

git remote add origin https://github.com/COLOQUE_SUA_URL_AQUI.git
```

Exemplo completo:
```bash
git remote add origin https://github.com/christyanbsr/Prova_poo2_tetris.git
```

---

### 4️⃣ FAZER PUSH PARA GITHUB

```bash
# Rename branch para main (GitHub usa main por padrão)
git branch -M main

# Fazer push da branch main
git push -u origin main

# Fazer push das outras branches
git push -u origin v0.1
git push -u origin v0.2
git push -u origin v1.0
```

---

### 5️⃣ VERIFICAR NO GITHUB

Acesse seu repositório em GitHub e verifique:

```
https://github.com/christyanbsr/Prova_poo2_tetris
```

Deve estar **PÚBLICO** ✅ e com todos os branches visíveis.

---

## 🧪 COMO TESTAR TODAS AS VERSÕES

### Opção 1: Script Interativo (Recomendado)

#### Windows:
```bash
.\test-all-versions.bat
```

Abre um menu com opções:
- 1) Compilar todas as versões
- 2) Testar todas as versões
- 3) Gerar Javadoc
- 4) Executar jogo
- 5) Build completo
- 6) Info Git
- 7) Sair

#### Linux/Mac:
```bash
chmod +x test-all-versions.sh
./test-all-versions.sh
```

### Opção 2: Comandos Manuais

#### Compilar cada versão:
```bash
# v0.1 - Básico
git checkout v0.1
mvn clean compile

# v0.2 - Com threads
git checkout v0.2
mvn clean compile

# v1.0 - Completo
git checkout main
mvn clean compile
```

#### Testar cada versão:
```bash
# v0.1
git checkout v0.1
mvn test

# v0.2
git checkout v0.2
mvn test

# v1.0
git checkout main
mvn test
```

#### Executar o jogo:
```bash
git checkout main
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
```

---

## 📊 ESTRUTURA GIT FINAL

```
Repositório GitHub: https://github.com/[usuario]/Prova_poo2_tetris

Branches:
├── main (ou master)
│   └── Versão completa v1.0 com todas as features
│       - Menu inicial
│       - Save/Load
│       - Leaderboard
│       - Todos os controles
│
├── v0.2
│   └── Versão com threads e níveis
│       - Interface Swing
│       - Threads e velocidade
│       - Níveis
│       - Preview próxima peça
│       - Ranking JSON
│
└── v0.1
    └── Versão básica
        - Interface Swing
        - Movimentação
        - Colisão
        - Game over
```

---

## ✨ ARQUIVOS INCLUSOS NO REPOSITÓRIO

```
.
├── 📁 src/
│   ├── main/java/com/tetris/ (código-fonte - 11 classes)
│   └── test/java/com/tetris/ (testes - 5 classes, 50+ casos)
│
├── 📁 docs/ (Javadoc - gerar com: mvn javadoc:javadoc)
│
├── 📁 release/ (documentação por versão)
│   ├── v0.1/README.md
│   ├── v0.2/README.md
│   └── v1.0/README.md
│
├── 📝 Documentação
│   ├── README.md
│   ├── QUICKSTART.md
│   ├── ARCHITECTURE.md
│   ├── IMPLEMENTATION_SUMMARY.md
│   ├── PROJECT_OVERVIEW.md
│   ├── FINAL_SUMMARY.md
│   ├── DELIVERY_GUIDE.md
│   ├── GIT_SETUP.md (este arquivo)
│   └── CONTRIBUTORS.md
│
├── ⚙️ Configuração
│   ├── pom.xml
│   ├── .gitignore
│   ├── build.bat
│   └── build.sh
│
├── 🧪 Testes
│   └── test-all-versions.bat (Windows)
│   └── test-all-versions.sh (Linux/Mac)
│
└── 📊 Git
    ├── .git/ (repositório)
    └── Branches: main, v0.1, v0.2, v1.0
```

---

## 🔍 VERIFICAR ANTES DE ENVIAR EMAIL

### Checklist GitHub:

```
[ ] Repositório criado
[ ] Repositório está PUBLIC
[ ] URL funciona: https://github.com/[usuario]/Prova_poo2_tetris
[ ] Todos os branches aparecem no GitHub
[ ] README.md visível na página inicial
[ ] Todos os arquivos .java estão lá
[ ] Pasta src/main/ visível
[ ] Pasta src/test/ visível
[ ] Documentação visível
```

### Checklist Local:

```
[ ] Compilação funciona: mvn clean compile
[ ] Testes passam: mvn test
[ ] Javadoc gera: mvn javadoc:javadoc
[ ] Jogo executa: mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
[ ] Todas as versões compilam
[ ] Todas as versões testam
```

---

## 📧 EMAIL DE ENTREGA FINAL

**Para**: pedro.h.braga@anhanguera.com

**Assunto**: Entrega AO2 - Tetris Game - [Seu RA]

**Corpo**:

```
Prezado Prof. Pedro,

Segue a entrega da Avaliação Oficial 2 de Programação Orientada a 
Objetos II.

INTEGRANTES DO GRUPO:

Nome Completo: Christyan Bernardo de Souza Rodrigues
RA: 400147611024
GitHub: christyanbsr

Nome Completo: [Nome do aluno 2]
RA: [RA do aluno 2]
GitHub: [usuario2]

[Adicione mais integrantes se necessário]

LINK DO REPOSITÓRIO:
https://github.com/christyanbsr/Prova_poo2_tetris

SOBRE O PROJETO:

✅ Tetris Game em Java com Swing
✅ 3 Versões: v0.1, v0.2, v1.0
✅ Menu inicial, save/load, leaderboard
✅ 50+ testes JUnit
✅ Documentação Javadoc completa
✅ Pronto para compilar e testar

COMO TESTAR:

1. Clone o repositório
2. Execute: mvn clean compile
3. Execute: mvn test
4. Execute: mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

Ou use os scripts de teste:
- Windows: .\test-all-versions.bat
- Linux/Mac: ./test-all-versions.sh

DOCUMENTAÇÃO:
- README.md - Visão geral
- QUICKSTART.md - Instalação
- ARCHITECTURE.md - Detalhes técnicos
- docs/ - Javadoc completo (gerar com: mvn javadoc:javadoc)

Atenciosamente,
[Nomes dos alunos]
```

---

## 🆘 PROBLEMAS COMUNS

### "remote already exists"
```bash
git remote remove origin
git remote add origin https://github.com/[usuario]/Prova_poo2_tetris.git
```

### "Permission denied"
Certifique-se de que tem acesso ao repositório GitHub. Se usar SSH:
```bash
git remote set-url origin git@github.com:[usuario]/Prova_poo2_tetris.git
```

### "Branch não aparece no GitHub"
```bash
git push -u origin [nome-da-branch]
```

### "Repositório aparece privado"
No GitHub, vá em:
Settings → Visibility → Public → Save

### "Maven não encontrado"
Instale Maven seguindo: QUICKSTART.md

---

## ✅ CHECKLIST FINAL

- [ ] Git inicializado ✅
- [ ] Commits realizadas ✅
- [ ] Branches criadas ✅
- [ ] Repositório criado no GitHub
- [ ] Remote origin adicionado
- [ ] Push realizado para GitHub
- [ ] Verificar se está PUBLIC no GitHub
- [ ] Compilar e testar localmente
- [ ] Email de entrega preparado
- [ ] Enviar email com link do GitHub (SEM ANEXOS)

---

## 📞 PRÓXIMA AÇÃO

Agora execute no PowerShell/Terminal:

```bash
cd "c:\Users\Christyan\Documents\Faculdade\Prova_poo2_tetris"

# Adicionar remote (copie a URL do seu repositório GitHub)
git remote add origin https://github.com/[SEU_USUARIO]/Prova_poo2_tetris.git

# Fazer push
git branch -M main
git push -u origin main
git push -u origin v0.1
git push -u origin v0.2
git push -u origin v1.0
```

**Após fazer push**, verifique em: `https://github.com/[SEU_USUARIO]/Prova_poo2_tetris`

---

**Última atualização**: Junho 1, 2026  
**Status**: ✅ Git configurado e pronto para GitHub
