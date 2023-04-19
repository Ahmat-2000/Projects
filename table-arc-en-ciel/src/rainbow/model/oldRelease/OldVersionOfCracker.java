package rainbow.model.oldRelease;

import java.util.HashMap;
import java.util.Map;

import rainbow.model.hashing.HashingAlgorithm;
import rainbow.model.reduction.AbstractReduce;
import rainbow.model.utils.UtilForRainbow;

public class OldVersionOfCracker {
    private String hash;
   // protected ArrayList<AbstractReduce> reduceTab;  // Nos reduce, après pour bien optimiser le code, on peut utiliser un tableau de AbstractReduce
    private HashMap<String,String> table;   // elle contient le mot de passe et hash qui sont dans le fichier passwordFilePath
    protected String passwordFilePath;          // C'est le chemin du fichier qui contient la table-arc-en-ciel
    protected HashingAlgorithm algorithmForHash;    // notre algo de hashage
    protected AbstractReduce reduce1,reduce2;
    /**
     * @param algorithmForHash
     * @param hash
     * @param reduce1
     * @param reduce2
     * @param passwodfilePath
     * @param length
     * @param chainLength
     */
    public OldVersionOfCracker(HashingAlgorithm algorithmForHash,String hash,AbstractReduce reduce1,AbstractReduce reduce2, String passwodfilePath,int length,int chainLength)
    {
        this.table = new HashMap<>(); this.hash = hash; this.passwordFilePath = passwodfilePath;
        //reduceTab = new ArrayList<>(); reduceTab.add(reduce1); reduceTab.add(reduce2);
        this.reduce1 = reduce1; this.reduce2 = reduce2;
        this.algorithmForHash = algorithmForHash;
    }
    /**
     * charge la Table arc en ciel à partir d'un fichier génèré en avance.
     * @param fileName
     * @return Boolean
     */
    public void loadFromFile(String fileName)
    {
        this.table = UtilForRainbow.hashMapFromTextFile(fileName);
    }
    /**
     * Recherche le mot de passe à partir du hash donnée en 
     * paramètre et retourne le mode de passe en clair.
     * @param String hash
     * @return String password
     * @throws Exception
     */
    public String findPasswordFromHash() 
    {
        this.loadFromFile(passwordFilePath);
        String password  = this.verifyHashInTableValue(this.hash);
        if(password != null)
            return regeneratePassword(password, this.hash);
        else
        {
            reduce2.setHashToReduce(this.hash);/* On initialise le hash à reduire */
            String newHash = algorithmForHash.hashPassword(reduce2.reduce()); 
            password = this.verifyHashInTableValue(newHash); // on vérifie si le newhash est dans le fichier rainbowTable.txt
            if(password != null)
                return regeneratePassword(password, this.hash); // On génére le mot de passe correspondant au hash
            reduce1.setHashToReduce(this.hash);/* On initialise le hash à reduire */
            newHash = algorithmForHash.hashPassword(reduce1.reduce()); 
            reduce2.setHashToReduce(newHash);/* On initialise le hash à reduire */
            newHash = algorithmForHash.hashPassword(reduce2.reduce());
            password = this.verifyHashInTableValue(newHash); // on vérifie si le newhash est dans le fichier rainbowTable.txt
            if(password != null)
                return regeneratePassword(password, this.hash); // On génére le mot de passe correspondant au hash
            return null;                                       // Sinon on retourne null pour dire que le hash n'est dans la table 
        }
    }

    /**
     * Méthodes utiles
     * vérifie si le hash appartient directement dans le fichier RainBowTable.txt
     * @param HashMap<String,String> table1
     * @return String
    */
    public String verifyHashInTableValue(String newhash)
    {  
        if(this.table.containsValue(newhash))
        {
            for(Map.Entry<String, String> entry: table.entrySet()){
                if(entry.getValue().equals(newhash))
                {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
    /**
     * Construction du mot de passe à partir d'un hash qui est dans la table.
     * elle prend en paramètre la tête d'un maillon de notre table et le hash à 
     * casser
     * @param pass
     * @param hash
     * @return String 
     */
    public String regeneratePassword(String pass,String newhash)
    {
        String tmp = algorithmForHash.hashPassword(pass);
        if(tmp.equals(newhash))
        {
            return pass;
        }
        reduce1.setHashToReduce(tmp);
        String reduce = reduce1.reduce();
        tmp = algorithmForHash.hashPassword(reduce);
        if(tmp.equals(newhash))
        {
            return reduce;
        }
        reduce2.setHashToReduce(tmp);
        tmp = reduce2.reduce();
        return tmp;
    }

    /**
     * Cette méthode permet de factoriser le code
     * @param r
     * @param hashToReduce
     * @return String Hash
     */
    /*
     * Getter
     */
    public HashMap<String, String> getTable() {
        return table;
    }
}
