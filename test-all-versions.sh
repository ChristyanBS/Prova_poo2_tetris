#!/bin/bash

# =====================================================================
# SCRIPT DE TESTE - TODAS AS VERSÕES DO TETRIS GAME
# =====================================================================
#
# Este script permite compilar, testar e executar todas as versões
# do projeto Tetris Game (v0.1, v0.2, v1.0)
#
# =====================================================================

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para limpar tela
clear_screen() {
    clear
}

# Função para mostrar menu
show_menu() {
    clear_screen
    echo -e "${BLUE}"
    echo "╔════════════════════════════════════════════════════════════════╗"
    echo "║                                                                ║"
    echo "║          TETRIS GAME - TESTE DE TODAS AS VERSÕES              ║"
    echo "║                                                                ║"
    echo "╚════════════════════════════════════════════════════════════════╝"
    echo -e "${NC}"
}

# Verificar se Maven está instalado
check_maven() {
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}❌ Maven não encontrado! Instale Maven para continuar.${NC}"
        echo ""
        echo "Instruções de instalação em: QUICKSTART.md"
        read -p "Pressione ENTER para continuar..."
        exit 1
    fi
}

# Verificar se Java está instalado
check_java() {
    if ! command -v java &> /dev/null; then
        echo -e "${RED}❌ Java não encontrado! Instale Java 11+ para continuar.${NC}"
        read -p "Pressione ENTER para continuar..."
        exit 1
    fi
}

# Menu principal
main_menu() {
    show_menu
    check_maven
    check_java
    
    echo -e "${GREEN}✅ Maven e Java encontrados${NC}"
    echo ""
    
    while true; do
        clear_screen
        echo -e "${BLUE}╔════════════════════════════════════════════════════════════════╗${NC}"
        echo -e "${BLUE}║                      MENU PRINCIPAL                           ║${NC}"
        echo -e "${BLUE}╚════════════════════════════════════════════════════════════════╝${NC}"
        echo ""
        echo "Escolha uma opção:"
        echo ""
        echo "  1) Compilar todas as versões"
        echo "  2) Testar todas as versões (JUnit)"
        echo "  3) Gerar Javadoc"
        echo "  4) Executar versão 1.0 (completa)"
        echo "  5) Build completo (compile + test)"
        echo "  6) Mostrar informações do Git"
        echo "  7) Sair"
        echo ""
        read -p "Opção [1-7]: " choice
        
        case $choice in
            1) compile_all ;;
            2) test_all ;;
            3) generate_javadoc ;;
            4) run_game ;;
            5) build_all ;;
            6) git_info ;;
            7) exit 0 ;;
            *) echo -e "${RED}❌ Opção inválida!${NC}"; sleep 2 ;;
        esac
    done
}

# Compilar todas as versões
compile_all() {
    clear_screen
    echo ""
    echo -e "${YELLOW}📦 COMPILANDO TODAS AS VERSÕES...${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: master (v1.0 - Completo)${NC}"
    git checkout master > /dev/null 2>&1
    mvn clean compile
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro ao compilar v1.0${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v1.0 compilado com sucesso!${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: v0.2 (Com threads e níveis)${NC}"
    git checkout v0.2 > /dev/null 2>&1
    mvn clean compile
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro ao compilar v0.2${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v0.2 compilado com sucesso!${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: v0.1 (Versão básica)${NC}"
    git checkout v0.1 > /dev/null 2>&1
    mvn clean compile
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro ao compilar v0.1${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v0.1 compilado com sucesso!${NC}"
    echo ""
    
    git checkout master > /dev/null 2>&1
    
    echo ""
    echo -e "${GREEN}✅ TODAS AS VERSÕES COMPILADAS COM SUCESSO!${NC}"
    echo ""
    read -p "Pressione ENTER para voltar..."
}

# Testar todas as versões
test_all() {
    clear_screen
    echo ""
    echo -e "${YELLOW}🧪 TESTANDO TODAS AS VERSÕES...${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: master (v1.0 - Completo)${NC}"
    git checkout master > /dev/null 2>&1
    mvn test
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Testes falharam em v1.0${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v1.0 testado com sucesso!${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: v0.2${NC}"
    git checkout v0.2 > /dev/null 2>&1
    mvn test
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Testes falharam em v0.2${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v0.2 testado com sucesso!${NC}"
    echo ""
    
    echo -e "${YELLOW}⏳ Branch: v0.1${NC}"
    git checkout v0.1 > /dev/null 2>&1
    mvn test
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Testes falharam em v0.1${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    echo -e "${GREEN}✅ v0.1 testado com sucesso!${NC}"
    echo ""
    
    git checkout master > /dev/null 2>&1
    
    echo ""
    echo -e "${GREEN}✅ TODOS OS TESTES PASSARAM!${NC}"
    echo ""
    read -p "Pressione ENTER para voltar..."
}

# Gerar Javadoc
generate_javadoc() {
    clear_screen
    echo ""
    echo -e "${YELLOW}📚 GERANDO JAVADOC...${NC}"
    echo ""
    
    mvn javadoc:javadoc
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro ao gerar Javadoc${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    
    echo ""
    echo -e "${GREEN}✅ JAVADOC GERADO COM SUCESSO!${NC}"
    echo ""
    echo "Documentação gerada em: docs/index.html"
    echo ""
    read -p "Pressione ENTER para voltar..."
}

# Executar jogo
run_game() {
    clear_screen
    echo ""
    echo -e "${YELLOW}🎮 EXECUTANDO TETRIS GAME v1.0...${NC}"
    echo ""
    
    echo "Compilando..."
    mvn clean compile -q
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro na compilação${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    
    echo ""
    echo -e "${GREEN}🎮 Iniciando jogo...${NC}"
    echo ""
    mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame" 2>/dev/null
    
    echo ""
    echo "Jogo encerrado."
    echo ""
    read -p "Pressione ENTER para voltar..."
}

# Build completo
build_all() {
    clear_screen
    echo ""
    echo -e "${YELLOW}🔨 BUILD COMPLETO...${NC}"
    echo ""
    
    echo "⏳ Limpando..."
    mvn clean
    
    echo "⏳ Compilando..."
    mvn compile
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro na compilação${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    
    echo "⏳ Executando testes..."
    mvn test
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Testes falharam${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    
    echo "⏳ Gerando pacote..."
    mvn package -DskipTests
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Erro ao gerar pacote${NC}"
        read -p "Pressione ENTER para voltar..."
        return
    fi
    
    echo ""
    echo -e "${GREEN}✅ BUILD COMPLETO COM SUCESSO!${NC}"
    echo ""
    echo "JAR gerado: target/tetris-game-1.0.0.jar"
    echo ""
    read -p "Pressione ENTER para voltar..."
}

# Informações Git
git_info() {
    clear_screen
    echo ""
    echo -e "${BLUE}📊 INFORMAÇÕES DO GIT${NC}"
    echo ""
    
    echo "Status atual:"
    git status
    echo ""
    
    echo "Branches disponíveis:"
    git branch -a
    echo ""
    
    echo "Histórico de commits:"
    git log --oneline -10
    echo ""
    
    read -p "Pressione ENTER para voltar..."
}

# Executar menu principal
main_menu
