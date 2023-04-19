#!/bin/bash
# @author Ahmat
#Testes effectuées: 
#Avec SHA-256
# avec reduce de 1
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 10 200 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 5 200 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 5 1000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 10 1000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 5 10000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 10 10000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
# avec reduce de 1
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 5 200 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 10 200 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 5 1000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 10 1000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 5 10000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 1 10 10000 6
java -cp ../build rainbow.model.demo.Main crack SHA-256 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
#-------------------------------------------------------------------------------------------------------------------------------
#Avec MD5
#-------------------------------------------------------------------------------------------------------------------------------
# avec reduce de 1
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 10 200 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 5 200 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 5 1000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 10 1000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 5 10000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 2 10 10000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
# avec reduce de 1
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 5 200 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 10 200 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 5 1000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 10 1000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 5 10000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
java -cp ../build rainbow.model.demo.Main generator MD5 0123456789 1 10 10000 6
java -cp ../build rainbow.model.demo.Main crack MD5 0123456789 1 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6