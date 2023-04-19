package rainbow.model.oldRelease;

import java.util.ArrayList;

import rainbow.model.hashing.HashingAlgorithm;
import rainbow.model.hashing.Md5;
import rainbow.model.reduction.AbstractReduce;
import rainbow.model.reduction.Reduce1;
import rainbow.model.reduction.Reduce2;
import rainbow.model.table.RainbowTableCracker;
/**
 * @author Ahmat
 */
public class MainRainbow {
public static void main(String[] args){
    String charset = "abc123";
    HashingAlgorithm md5 = new Md5();
    // generateur de mot de passe
    /*PasswordGenerator generator = new PasswordGenerator(6);
    RandomPassWord strategy = new RandomPassWord(charset);
    generator.setStrategy(strategy);
    generator.generatePasswordHash(md5,(int)Math.pow(6, 6)*10/100);
    generator.savePasswords("./DataBase/password.txt");
    // rainowGenerator*/
    AbstractReduce reduce1 = new Reduce1();
    AbstractReduce reduce2 = new Reduce2();
    //AbstractReduce reduce3 = new Reduce2();
    reduce1.setcharset(charset); reduce1.setReduceLength(6);
    reduce2.setcharset(charset); reduce2.setReduceLength(6);
    //reduce3.setcharset(charset); reduce3.setReduceLength(6);
    ArrayList<AbstractReduce> reduceListe = new ArrayList<>();
    reduceListe.add(reduce1); reduceListe.add(reduce2); //reduceListe.add(reduce3);
    /*RainbowTableGenerator rainbowTableGenerator = new RainbowTableGenerator(md5);
    rainbowTableGenerator.setReduceTab(reduceListe);
    rainbowTableGenerator.loadFromFile("./DataBase/password.txt");
    rainbowTableGenerator.saveInFile("./DataBase/RainBowTable.txt");*/
    
    //Cassage de hash
    String hash = "8f84d6aa35309bd263fd92d7ca0605c5";  
    String pathFile = "./DataBase/RainBowTable.txt";
    RainbowTableCracker crack = new RainbowTableCracker(md5, hash, pathFile);
    crack.setReduceTab(reduceListe);
    System.out.println("\nExecution...");
    String password = crack.findPasswordFromHash();
    if (password == null)
    {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Le hash recherché est introuvable dans la table arc-en-ciel \n"+"Impossible de le casser avec cette table");
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    } 
    else {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Le mot de passe correspondant du hash "+hash+" est :\n"+password);
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

}
}
