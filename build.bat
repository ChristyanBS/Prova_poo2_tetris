@echo off
REM Script de compilação para Tetris Game
REM Windows Batch

echo ====================================
echo Tetris Game - Build Script
echo ====================================
echo.

REM Verifica se Maven está instalado
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERRO] Maven não encontrado no PATH
    echo.
    echo Por favor instale Maven:
    echo 1. Baixe em: https://maven.apache.org/download.cgi
    echo 2. Extraia o arquivo
    echo 3. Adicione o diretório bin ao PATH do Windows
    echo.
    pause
    exit /b 1
)

echo [OK] Maven encontrado
echo.

REM Opções de build
echo Escolha uma opção:
echo 1) Compilar (mvn clean compile)
echo 2) Rodar testes (mvn test)
echo 3) Gerar Javadoc (mvn javadoc:javadoc)
echo 4) Build completo (mvn clean package)
echo 5) Executar (mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame")
echo 6) Sair
echo.

set /p choice="Digite sua escolha (1-6): "

if "%choice%"=="1" (
    echo.
    echo Compilando...
    mvn clean compile
) else if "%choice%"=="2" (
    echo.
    echo Rodando testes...
    mvn test
) else if "%choice%"=="3" (
    echo.
    echo Gerando Javadoc...
    mvn javadoc:javadoc
    echo.
    echo Javadoc gerado em: docs/index.html
) else if "%choice%"=="4" (
    echo.
    echo Build completo...
    mvn clean package
) else if "%choice%"=="5" (
    echo.
    echo Executando Tetris...
    mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame"
) else if "%choice%"=="6" (
    exit /b 0
) else (
    echo Opção inválida
    exit /b 1
)

pause
