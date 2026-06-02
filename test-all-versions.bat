@echo off
REM =====================================================================
REM SCRIPT DE TESTE - TODAS AS VERSÕES DO TETRIS GAME
REM =====================================================================
REM
REM Este script permite compilar, testar e executar todas as versões
REM do projeto Tetris Game (v0.1, v0.2, v1.0)
REM
REM =====================================================================

color 0A
cls

echo.
echo ╔════════════════════════════════════════════════════════════════╗
echo ║                                                                ║
echo ║          TETRIS GAME - TESTE DE TODAS AS VERSÕES              ║
echo ║                                                                ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

REM Verificar se Maven está instalado
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Maven não encontrado! Instale Maven para continuar.
    echo.
    echo Instruções de instalação em: QUICKSTART.md
    pause
    exit /b 1
)

REM Verificar se Java está instalado
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Java não encontrado! Instale Java 11+ para continuar.
    pause
    exit /b 1
)

echo ✅ Maven e Java encontrados
echo.

:MENU
cls
echo ╔════════════════════════════════════════════════════════════════╗
echo ║                      MENU PRINCIPAL                           ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.
echo Escolha uma opção:
echo.
echo   1) Compilar todas as versões
echo   2) Testar todas as versões (JUnit)
echo   3) Gerar Javadoc
echo   4) Executar versão 1.0 (completa)
echo   5) Build completo (compile + test)
echo   6) Mostrar informações do Git
echo   7) Sair
echo.
set /p choice="Opção [1-7]: "

if "%choice%"=="1" goto COMPILE
if "%choice%"=="2" goto TEST
if "%choice%"=="3" goto JAVADOC
if "%choice%"=="4" goto RUN
if "%choice%"=="5" goto BUILD
if "%choice%"=="6" goto GIT_INFO
if "%choice%"=="7" goto END

echo ❌ Opção inválida!
timeout /t 2 >nul
goto MENU

REM =====================================================================
REM COMPILE TODAS AS VERSÕES
REM =====================================================================
:COMPILE
cls
echo.
echo 📦 COMPILANDO TODAS AS VERSÕES...
echo.

echo ⏳ Branch: master (v1.0 - Completo)
git checkout master >nul 2>nul
mvn clean compile
if %errorlevel% neq 0 (
    echo ❌ Erro ao compilar v1.0
    pause
    goto MENU
)
echo ✅ v1.0 compilado com sucesso!
echo.

echo ⏳ Branch: v0.2 (Com threads e níveis)
git checkout v0.2 >nul 2>nul
mvn clean compile
if %errorlevel% neq 0 (
    echo ❌ Erro ao compilar v0.2
    pause
    goto MENU
)
echo ✅ v0.2 compilado com sucesso!
echo.

echo ⏳ Branch: v0.1 (Versão básica)
git checkout v0.1 >nul 2>nul
mvn clean compile
if %errorlevel% neq 0 (
    echo ❌ Erro ao compilar v0.1
    pause
    goto MENU
)
echo ✅ v0.1 compilado com sucesso!
echo.

git checkout master >nul 2>nul

echo.
echo ✅ TODAS AS VERSÕES COMPILADAS COM SUCESSO!
echo.
pause
goto MENU

REM =====================================================================
REM TESTAR TODAS AS VERSÕES
REM =====================================================================
:TEST
cls
echo.
echo 🧪 TESTANDO TODAS AS VERSÕES...
echo.

echo ⏳ Branch: master (v1.0 - Completo)
git checkout master >nul 2>nul
mvn test
if %errorlevel% neq 0 (
    echo ❌ Testes falharam em v1.0
    pause
    goto MENU
)
echo ✅ v1.0 testado com sucesso!
echo.

echo ⏳ Branch: v0.2
git checkout v0.2 >nul 2>nul
mvn test
if %errorlevel% neq 0 (
    echo ❌ Testes falharam em v0.2
    pause
    goto MENU
)
echo ✅ v0.2 testado com sucesso!
echo.

echo ⏳ Branch: v0.1
git checkout v0.1 >nul 2>nul
mvn test
if %errorlevel% neq 0 (
    echo ❌ Testes falharam em v0.1
    pause
    goto MENU
)
echo ✅ v0.1 testado com sucesso!
echo.

git checkout master >nul 2>nul

echo.
echo ✅ TODOS OS TESTES PASSARAM!
echo.
pause
goto MENU

REM =====================================================================
REM GERAR JAVADOC
REM =====================================================================
:JAVADOC
cls
echo.
echo 📚 GERANDO JAVADOC...
echo.

mvn javadoc:javadoc
if %errorlevel% neq 0 (
    echo ❌ Erro ao gerar Javadoc
    pause
    goto MENU
)

echo.
echo ✅ JAVADOC GERADO COM SUCESSO!
echo.
echo Abrir documentação? (S/N)
set /p open_doc="Resposta: "
if /i "%open_doc%"=="S" (
    start docs\index.html
)
echo.
pause
goto MENU

REM =====================================================================
REM EXECUTAR VERSÃO 1.0
REM =====================================================================
:RUN
cls
echo.
echo 🎮 EXECUTANDO TETRIS GAME v1.0...
echo.

echo Compilando...
mvn clean compile -q
if %errorlevel% neq 0 (
    echo ❌ Erro na compilação
    pause
    goto MENU
)

echo.
echo 🎮 Iniciando jogo...
echo.
mvn exec:java -Dexec.mainClass="com.tetris.TetrisGame" 2>nul

echo.
echo Jogo encerrado.
echo.
pause
goto MENU

REM =====================================================================
REM BUILD COMPLETO
REM =====================================================================
:BUILD
cls
echo.
echo 🔨 BUILD COMPLETO...
echo.

echo ⏳ Limpando...
mvn clean

echo ⏳ Compilando...
mvn compile
if %errorlevel% neq 0 (
    echo ❌ Erro na compilação
    pause
    goto MENU
)

echo ⏳ Executando testes...
mvn test
if %errorlevel% neq 0 (
    echo ❌ Testes falharam
    pause
    goto MENU
)

echo ⏳ Gerando pacote...
mvn package -DskipTests
if %errorlevel% neq 0 (
    echo ❌ Erro ao gerar pacote
    pause
    goto MENU
)

echo.
echo ✅ BUILD COMPLETO COM SUCESSO!
echo.
echo JAR gerado: target\tetris-game-1.0.0.jar
echo.
pause
goto MENU

REM =====================================================================
REM INFORMAÇÕES DO GIT
REM =====================================================================
:GIT_INFO
cls
echo.
echo 📊 INFORMAÇÕES DO GIT
echo.

echo Status atual:
git status
echo.

echo Branches disponíveis:
git branch -a
echo.

echo Histórico de commits:
git log --oneline -10
echo.

pause
goto MENU

REM =====================================================================
REM FIM
REM =====================================================================
:END
cls
echo.
echo ✅ Até logo!
echo.
exit /b 0
