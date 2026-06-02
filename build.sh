#!/bin/bash
# Script de compilação para Tetris Game
# Linux/Mac

echo "===================================="
echo "Tetris Game - Build Script"
echo "===================================="
echo ""

# Verifica se Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "[ERRO] Maven não encontrado"
    echo ""
    echo "Por favor instale Maven:"
    echo "1. Baixe em: https://maven.apache.org/download.cgi"
    echo "2. Extraia o arquivo"
    echo "3. Adicione o diretório bin ao PATH"
    echo ""
    exit 1
fi

echo "[OK] Maven encontrado"
echo ""

# Menu de opções
echo "Escolha uma opção:"
echo "1) Compilar (mvn clean compile)"
echo "2) Rodar testes (mvn test)"
echo "3) Gerar Javadoc (mvn javadoc:javadoc)"
echo "4) Build completo (mvn clean package)"
echo "5) Executar (mvn exec:java)"
echo "6) Sair"
echo ""

read -p "Digite sua escolha (1-6): " choice

case $choice in
    1)
        echo ""
        echo "Compilando..."
        mvn clean compile
        ;;
    2)
        echo ""
        echo "Rodando testes..."
        mvn test
        ;;
    3)
        echo ""
        echo "Gerando Javadoc..."
        mvn javadoc:javadoc
        echo ""
        echo "Javadoc gerado em: docs/index.html"
        ;;
    4)
        echo ""
        echo "Build completo..."
        mvn clean package
        ;;
    5)
        echo ""
        echo "Executando Tetris..."
        mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
        ;;
    6)
        exit 0
        ;;
    *)
        echo "Opção inválida"
        exit 1
        ;;
esac
