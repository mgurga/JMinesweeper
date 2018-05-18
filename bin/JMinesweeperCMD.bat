@echo off

:startAgain

java -jar JMinesweeper.jar

set /p playAgain=Do you want to play again Y/N?
if %playAgain%==Y goto startAgain
