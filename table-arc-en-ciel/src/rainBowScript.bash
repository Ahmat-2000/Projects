#!/bin/bash
# @author Ahmat
#Testes effectuées: 

function generator()
{
    #crack algo charset reduce_number rainBowTable_pathname hashfile  pw_size
    #generator algo charset reduce_number table_size pw_number pw_size
    if [ "$1" = generator ] ; then 
        java -cp ../build rainbow.model.demo.Main $1 $2 $3 $4 $5 $6 $7
    elif [ "$1" = crack ] ; then 
        java -cp ../build rainbow.model.demo.Main $1 $2 $3 $4 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt $7
    else
        echo "Le premier argument du scripte doit être generator ou crack mais vous avez entré $1"
    fi

}

mode="crack"             # mode d'utilisation du script
algo="MD5"             # Algorithme de hashage utilisé
charset="0123";         # Alphabet utilisé par les mots de passe
reduce_number="2"    # Nombre de reduction utilisé
table_size="1"       # La taille de la table arc-en-ciel
pw_number="10"        # Nombre de mots de passe à cracker
pw_size="6"          # Longueur de mots de passe

# selection du mode d'usage du scripte
echo "Selectionner une option parmi le deux : 1 pour générer des mots de passe et 2 pour cracker ?"
select i in generator crack; do
    if [ "$i" != "" ]; then 
        mode="$i";
        break
    else 
        echo "mauvaise reponse"
        break
    fi
done
# selection d'un algorithme de hashage
echo "Selectionner un algorithme en choisissant un numéro : "
select i in "MD2" "MD5" "SHA-256" "SHA-1" "SHA-224" "SHA-256" "SHA-384" "SHA-512" "SHA-512/224" "SHA-512/256"; do
    if [ "$i" != "" ]; then 
        algo="$i";
        break
    else 
        echo "mauvaise reponse, relancer le scripte"
        break
    fi
done
read -p "Entrer la liste de l'alphabet utilisée dans les mots de passes : " charset
read -p "Entrer la longueur de mots de passe : " pw_size
read -p "Entrer le nombre de reduction à utiliser, un nombre positif: " reduce_number

if [ "$mode" = "generator" ] ; then
    read -p "Entrer le nombre de mots de passe à casser : " pw_number
    read -p "Entrer la taille de la table arc-en-ciel, elle doit être entre 1 à 100 : " table_size
fi
#generator algo charset reduce_number table_size pw_number pw_size
generator $mode $algo $charset $reduce_number $table_size $pw_number $pw_size
#java -cp ../build rainbow.model.demo.Main generator SHA-256 0123456789 2 10 10000 6
