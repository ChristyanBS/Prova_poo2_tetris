# 🚀 GUIA DE ENTREGA - TETRIS GAME

## Instruções Finais para Submissão

---

## 📋 ANTES DE ENTREGAR

### 1. Verificar Compilação
```bash
cd "c:\Users\Christyan\Documents\Faculdade\Prova_poo2_tetris"
mvn clean compile
```
✅ Deve compilar sem erros

### 2. Verificar Testes
```bash
mvn test
```
✅ Todos os 50+ testes devem passar

### 3. Gerar Documentação
```bash
mvn javadoc:javadoc
```
✅ Javadoc deve ser gerado em `docs/`

### 4. Preencher CONTRIBUTORS.md
```markdown
| Nome Completo | RA | GitHub Username |
|---------------|----|----|
| Nome do Aluno 1 | 12345678 | usuario1 |
| Nome do Aluno 2 | 12345679 | usuario2 |
```

---

## 🐙 CRIAR REPOSITÓRIO NO GITHUB

### Passo 1: Acessar GitHub
- Abra: https://github.com
- Faça login com sua conta
- Clique em "+" (canto superior direito)
- Selecione "New repository"

### Passo 2: Configurar Repositório
```
Repository name: Prova_poo2_tetris
Description: Tetris Game - POO II 2026
Public: ✅ MARCAR (importante!)
Add .gitignore: Java
Add a license: MIT
```

### Passo 3: Criar Repositório
- Clique "Create repository"
- GitHub gerará a URL

---

## 📤 FAZER PUSH DO CÓDIGO

### Opção 1: Git (se já tem configurado)

```bash
# Navegue para o diretório
cd "c:\Users\Christyan\Documents\Faculdade\Prova_poo2_tetris"

# Inicialize git
git init
git add .
git commit -m "Tetris Game - AO2 POO II 2026"

# Adicione remote (substitua URL_DO_SEU_REPO)
git remote add origin https://github.com/SEU_USUARIO/Prova_poo2_tetris.git

# Envie para GitHub
git branch -M main
git push -u origin main
```

### Opção 2: GitHub Desktop

1. Abra GitHub Desktop
2. Clique "Add" → "Add Existing Repository"
3. Selecione a pasta `Prova_poo2_tetris`
4. Clique "Publish repository"
5. Marque "Keep this code private" como DESMARCADO
6. Clique "Publish Repository"

### Opção 3: Upload Manual via GitHub

1. Acesse seu repositório no GitHub
2. Clique "Upload files"
3. Arraste ou selecione os arquivos
4. Faça commit

---

## ✉️ PREPARAR EMAIL PARA ENTREGA

### Destinatário
```
pedro.h.braga@anhanguera.com
```

### Assunto
```
Entrega AO2 - Tetris Game - [Seu RA]
```

### Corpo do Email

```
Prezado Prof. Pedro,

Segue a entrega da Avaliação Oficial 2 de Programação Orientada a Objetos II.

INTEGRANTES DO GRUPO:

Nome Completo: [Nome]
RA: [RA]
GitHub: [usuario]

Nome Completo: [Nome]
RA: [RA]
GitHub: [usuario]

Nome Completo: [Nome]
RA: [RA]
GitHub: [usuario]

LINK DO REPOSITÓRIO:
https://github.com/[usuario]/Prova_poo2_tetris

SOBRE O PROJETO:

✅ Tetris Game - Versões v0.1, v0.2 e v1.0
✅ Interface gráfica com Swing
✅ Threads para controle de velocidade
✅ Níveis de dificuldade
✅ Preview da próxima peça
✅ Ranking em JSON
✅ Save/Load de jogo
✅ Leaderboard completo
✅ 50+ testes JUnit
✅ Documentação Javadoc completa

COMO COMPILAR E EXECUTAR:

1. Clone o repositório
2. Certifique-se de ter Java 11+ e Maven 3.6+ instalados
3. Execute: mvn clean compile
4. Execute: mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"

Documentação:
- README.md - Visão geral
- QUICKSTART.md - Instalação
- ARCHITECTURE.md - Detalhes técnicos
- docs/ - Javadoc completo

Atenciosamente,
[Nomes dos alunos]
```

---

## 🔍 VERIFICAÇÃO FINAL (CHECKLIST)

### Repositório GitHub
- [ ] Repositório criado e PÚBLICO
- [ ] URL funcionando
- [ ] Todos os arquivos visíveis
- [ ] README.md aparece na tela inicial

### Código
- [ ] Compila sem erros: `mvn clean compile`
- [ ] Testes passam: `mvn test`
- [ ] Javadoc gerado: `mvn javadoc:javadoc`

### Documentação
- [ ] README.md preenchido
- [ ] CONTRIBUTORS.md preenchido
- [ ] QUICKSTART.md presente
- [ ] ARCHITECTURE.md presente
- [ ] release/v0.1/README.md presente
- [ ] release/v0.2/README.md presente
- [ ] release/v1.0/README.md presente

### Estrutura
- [ ] src/main/ com código
- [ ] src/test/ com testes
- [ ] docs/ com Javadoc (gerado)
- [ ] release/ com 3 versões
- [ ] pom.xml presente
- [ ] .gitignore presente

### Email
- [ ] Email endereçado para: pedro.h.braga@anhanguera.com
- [ ] Nomes, RAs e usernames inclusos
- [ ] Link do GitHub incluído
- [ ] NEM ENVIADO COM ANEXOS (apenas link)

---

## ⏰ CRONOGRAMA RECOMENDADO

| Data | Atividade |
|------|-----------|
| 31 de maio | Finalizar código e testes |
| 01 de junho | Criar GitHub e fazer push |
| 01 de junho | Gerar documentação final |
| 02 de junho (antes de 23h59m) | Enviar email de entrega |

---

## ❌ NÃO FAZER

- ❌ Não enviar anexos
- ❌ Não deixar repositório privado
- ❌ Não esquecer de preencher CONTRIBUTORS.md
- ❌ Não enviar sem compilar antes
- ❌ Não enviar sem executar testes

---

## ✅ O QUE FAZER

- ✅ Verificar compilação antes
- ✅ Deixar repositório PÚBLICO
- ✅ Preencher todos os dados
- ✅ Testar antes de enviar
- ✅ Enviar apenas o link do GitHub

---

## 🆘 PROBLEMAS COMUNS

### "Maven não encontrado"
Solução: Instale Maven seguindo QUICKSTART.md

### "Teste falhou"
Solução: Verifique pom.xml e execute: `mvn clean test`

### "Compilação falha"
Solução: Verifique Java 11+: `java -version`

### "GitHub não aceita push"
Solução: Verifique credenciais Git e URL remota

### "Repositório criado privado"
Solução: Vá em Settings → Make public

---

## 💡 DICAS IMPORTANTES

1. **Compile sempre antes de entregar**
   ```bash
   mvn clean compile
   ```

2. **Execute todos os testes**
   ```bash
   mvn test
   ```

3. **Verifique documentação**
   - Abra docs/index.html no navegador

4. **Teste o programa**
   - Execute o Tetris Game
   - Jogue um pouco
   - Verifique se funciona

5. **Faça backup**
   - Copie os arquivos antes de enviar

---

## 📞 CONTATO DO PROFESSOR

**Email**: pedro.h.braga@anhanguera.com

**Observações**:
- Envie o email da entrega até 23h59m do dia 02 de junho
- Sem anexos (apenas link do GitHub)
- Inclua nomes, RAs e usernames
- Repositório deve estar PÚBLICO

---

## ✨ BOA SORTE!

Você implementou um projeto de qualidade profissional.

Siga as instruções acima e tudo correrá bem!

**Pontuação esperada: Máxima** 🎉

---

**Última atualização**: Junho de 2026
**Status**: ✅ Pronto para entrega
