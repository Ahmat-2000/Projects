package rainbow.model.demo;
import java.io.*;
import java.util.*;
import java.lang.Math;

import rainbow.model.hashing.*;
import rainbow.model.pwdgen.*;
import rainbow.model.reduction.AbstractReduce;
import rainbow.model.table.*;
import rainbow.model.utils.UtilForRainbow;
/**
 * @author Ahmat
 */
public class Main
{
    private static final String[] ALGOLIST = new String[]{
        "MD2","MD5","SHA-256","SHA-1","SHA-224","SHA-256", "SHA-384","SHA-512","SHA-512/224","SHA-512/256"
    };
    private static HashMap<String, String> passwordMap = new HashMap<String, String>();
    private static void printUsage(){
        System.out.println("Usage:");
        System.out.println("   java -cp ../build/ rainbow/model/demo/Main generator algo charset reduce_number table_size pw_number pw_size");
        System.out.println("   java -cp ../build/ rainbow/model/demo/Main crack algo charset reduce_number rainBowTableFile hashfile pw_size \n");
    }
    private static ArrayList<AbstractReduce> reduceSet(int number,String charset,int reduceLength)
    {
        ArrayList<AbstractReduce> reduceListe = new ArrayList<>();
        AbstractReduce reduce = null;
        int k = 1;
        for (int i = 1; i <= number; i++) {
            try {
                if( i != 1 && i % 2 != 0 ){
                    k = 1;
                }
                Class<?> rClass =  Class.forName("rainbow.model.reduction.Reduce"+k);
                reduce = (AbstractReduce) rClass.getDeclaredConstructor().newInstance();
                reduce.setcharset(charset);
                reduce.setReduceLength(reduceLength);
                reduceListe.add(reduce);
                k++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reduceListe;
    }
    // génération de mot de passe
    private static PasswordGenerator setGenerator(int pw_number, PasswordStrategy strategy, HashingAlgorithm algo, int pw_size)
    {
        PasswordGenerator generator = new PasswordGenerator(pw_size);
        generator.setStrategy(strategy);
        generator.generatePasswordHash(algo,pw_number);
        return generator;
    }
    /* PassWord Generator */
    private static void UltimGenerator(String[] args)
    {
        String algorithm = args[1]; String charset = args[2];
        int reduce_number = Integer.parseInt(args[3]);
        int table_size = Integer.parseInt(args[4]); // taille de la table-arc-en-ciel
        int pw_number = Integer.parseInt(args[5]); // nombre de mot de passe à cracker
        int pw_size = Integer.parseInt(args[6]);
        // PassWordGenerator
        HashingAlgorithm algo = new AlgoHashing(algorithm);
        RandomPassWord strategy = new RandomPassWord(charset);
        PasswordGenerator generator = Main.setGenerator((int) (Math.pow(charset.length(), pw_size)*table_size / 100),strategy,algo,pw_size);
        generator.savePasswords("./DataBase/password.txt");
        // generation de hashFile qui contient que le hash à cracker
        generator = Main.setGenerator(pw_number,strategy,algo,pw_size);
        UtilForRainbow.writeInFileFromHashMap(generator.getEntries(),"./DataBase/passWordToCrack.txt",true);  
        // initialisation des reductions
        ArrayList<AbstractReduce> reduceListe = Main.reduceSet(reduce_number,charset,pw_size);
        // RainBowGenerator
        RainbowTableGenerator rainbowTableGenerator = new RainbowTableGenerator(algo);
        rainbowTableGenerator.setReduceTab(reduceListe);
        rainbowTableGenerator.loadFromFile("./DataBase/password.txt");
        rainbowTableGenerator.saveInFile("./DataBase/RainBowTable.txt");
    }
    /*
     * Notre crack
    */
    private static void crack(String[] args)
    {
        String rainBowTable_pathname = args[4]; // rainbow file
        String hashes_pathname = args[5];       // hash to crack file
        String algorithm = args[1];             // Algo de hashage utilisé 
        int reduce_number = Integer.parseInt(args[3]);
        String charset = args[2];
        HashingAlgorithm algo = new AlgoHashing(algorithm);
        try {
            Scanner scan = new Scanner(new File(hashes_pathname));
            ArrayList<AbstractReduce> reduceListe = Main.reduceSet(reduce_number,charset,6);
            RainbowTableCracker crack = null;
            String hash = null;
            while(scan.hasNextLine())
            {
                hash = scan.nextLine();
                crack = new RainbowTableCracker(algo, hash, rainBowTable_pathname);
                crack.setReduceTab(reduceListe);
                String password = crack.findPasswordFromHash();
                if(password != null)
                    Main.passwordMap.put(hash, password);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("\nErreur produite lors de l'ouverture du fichier "+hashes_pathname);
        }
    }
    /*
     * Cette fonction permet de vérifier qu'on a le bon nombre d'argument avec les bon types
     */
    private static void verifyEnties(String[] args,String choix) throws Exception
    {
        List<String> algoList = new ArrayList<>(Arrays.asList(Main.ALGOLIST));
        String algorithm = args[1]; String charset = args[2];
        int pw_size = Integer.parseInt(args[6]);
        int reduce_number = Integer.parseInt(args[3]); // si la dernière valeur n'est pas un nombre, ça produit une exception
       if (choix.equals("crack")) 
       {    
            // crack algo charset reduce_number rainBowTable_pathname hashfile pw_size
            String rainBowTable_pathname = args[4]; // rainbow file
            String hashes_pathname = args[5];       // hash to crack file 
            if(!algoList.contains(algorithm.toUpperCase()))
                throw new Exception("L'algorithme doit appartenir à cette liste "+ algoList+"\nc'est l'argument 2");
            if(charset.trim().length() == 0)
                throw new Exception("La liste de caractère ne doit pas être vide, c'est l'argument 3\n");
            if(reduce_number <= 0 || reduce_number > 3)
                throw new Exception("L'argument 4 ne peut pas être vide, ça doit être un nombre compris entre 1 à 3");
            File rt = new File(rainBowTable_pathname);
            if(!rt.exists()) 
                throw new Exception("L'argument 5 doit être le chemin vers le fichier de la Table-arc-en-ciel");
            File hashfile = new File(hashes_pathname);
            if(!hashfile.exists()) 
                throw new Exception("L'argument 6 doit être le chemin vers le fichier de hashes à casser ");
            if(pw_size <= 0)
                throw new Exception("L'argument 7 ne peut pas être vide, ça doit être un nombre positif");
        }
       else
       {
            //  generator algo charset reduce_number table_size pw_number pw_size
            int table_size = Integer.parseInt(args[4]); // taille de la table-arc-en-ciel
            int pw_number = Integer.parseInt(args[5]); // nombre de mot de passe à cracker
            if(!algoList.contains(algorithm.toUpperCase()))
                throw new Exception("L'algorithme doit appartenir à cette liste "+ algoList+"\nC'est l'argument 2");
            if(charset.trim().length() == 0)
                throw new Exception("La liste de caractère ne doit pas être vide, c'est l'argument 3\n");
            if(reduce_number <= 0)
                throw new Exception("L'argument 4 ne peut pas être vide, ça doit être un nombre compris entre 1 à 3");
            if(table_size <= 0 || table_size > 100)
                throw new Exception("L'argument 5 ne peut pas être vide, ça doit être un nombre positif entre 1 et 100");
            if(pw_number <= 0)
                throw new Exception("L'argument 6 ne peut pas être vide, ça doit être un nombre positif");
            if(pw_size <= 0)
                throw new Exception("L'argument 7 ne peut pas être vide, ça doit être un nombre positif");
       }
    }

// Main principal
public static void main(String[] args){
    //if (args.length < 6 || args.length > 7){
    if (args.length != 7){
        //System.out.println("ici : "+ args.length);
        Main.printUsage();
    } 
    else {
        switch(args[0]){
            case "generator":   // generator algo charset reduce_number table_size pw_number pw_size
                try {
                    Main.verifyEnties(args, args[0]);
                    System.out.println("Debut du génération de mot de passe \nProgramme en cours d'execution ...");
                    long time = System.currentTimeMillis();
                    Main.UltimGenerator(args);
                    time = System.currentTimeMillis() -time ;
                    float tempsDexecution = (float)time/1000; 
                    System.out.println("La génération de mot de passe est terminée.");
                    System.out.println("Le temps d'execution est : "+ tempsDexecution + " Seconds");
                    UtilForRainbow.createAndWriteTextInFile("./DataBase/tempsDeGeneration.txt",String.valueOf(tempsDexecution));
                } catch (Exception e) {
                    Main.printUsage();
                    System.out.println(e.getMessage()); 
                }   
                break;  
            case "crack":    // crack algo charset reduce_number rainBowTable_pathname hashfile pw_size
                try {
                    Main.verifyEnties(args, args[0]);
                    float tempsDexecution = 0.000f;
                    int nbPassWord = UtilForRainbow.nbFileLine(args[5]);
                    String resultat = "Début du cassage de mot de passe";
                    String tmp = resultat;
                    System.out.println(tmp);
                    long time = System.currentTimeMillis();
                    Main.crack(args);
                    time = System.currentTimeMillis() -time;
                    tmp = "Le cassage est terminé \n\nLes mots de passe cassés sont : \n";
                    resultat += tmp + UtilForRainbow.hashMapToString(passwordMap);
                    System.out.println(tmp);
                    int i = UtilForRainbow.print(passwordMap);
                    tempsDexecution = (float)time/1000;
                    tmp = "\nIl y a "+i+" mots de passe cassés sur "+nbPassWord+"\nLe temps d'execution est : "+ tempsDexecution + " Seconds";
                    resultat += tmp;
                    System.out.println(tmp);
                    String filename = "./DataBase/log/" + args[1] + "_" + args[2] + "_" + args[3] +"_" 
                                + UtilForRainbow.nbFileLine(args[4]) + "_" +UtilForRainbow.nbFileLine(args[5])+ "_" + args[6] + ".txt";
                    UtilForRainbow.createAndWriteTextInFile(filename,resultat);
                    resultat = args[1] + ";" + args[3] + ";" + UtilForRainbow.nbFileLine(args[4]) +";" 
                                + nbPassWord+ ";" + args[6] + ";" + i +";"+tempsDexecution ;
                    Scanner sc = new Scanner(new File("./DataBase/tempsDeGeneration.txt"));
                        resultat += ";" + sc.nextLine();
                    sc.close();
                    UtilForRainbow.writeFile("./DataBase/resultat.csv",resultat);
                } catch (Exception e) {
                    Main.printUsage();
                    System.out.println(e.getMessage()); 
                }   
                break;
            default:
                Main.printUsage();
                break;
        }
    }
}
}