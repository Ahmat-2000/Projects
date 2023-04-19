@author Ahmat
Pour compliler :
  javac -d ../build rainbow/model/demo/*.java rainbow/model/pwdgen/*.java rainbow/model/utils/*.java rainbow/model/hashing/*.java rainbow/model/table/*.java rainbow/model/reduction/*.java 

Pour executer : 
  algo             # Algorithme de hashage utilisé
  charset          # Alphabet utilisé par les mots de passe
  reduce_number    # Nombre de reduction utilisé
  table_size       # La taille de la table arc-en-ciel
  pw_number        # Nombre de mots de passe à cracker
  pw_size          # Longueur de mots de passe
    
  pour casser les mots de passes.
    // crack algo charset reduce_number rainBowTable_pathname hashfile  pw_size
    
  pour générer
    //  generator algo charset reduce_number table_size pw_number pw_size
    

exemple de teste: 
    -- java -cp ../build rainbow.model.demo.Main generator MD5 abc123 2 10 10 6   
    -- java -cp ../build rainbow.model.demo.Main crack MD5 abc123 2 ./DataBase/RainBowTable.txt ./DataBase/passWordToCrack.txt 6
@Auteur Ahmat Mahamat , 21912949

