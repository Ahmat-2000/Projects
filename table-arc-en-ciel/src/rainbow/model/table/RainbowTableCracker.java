package rainbow.model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rainbow.model.hashing.HashingAlgorithm;
import rainbow.model.reduction.AbstractReduce;
import rainbow.model.utils.UtilForRainbow;
/**
 * C'est notre crack
 * @author Ahmat
 */
public class RainbowTableCracker {
    private String hash;
    protected ArrayList<AbstractReduce> reduceTab;  // Nos reduce, après pour bien optimiser le code, on peut utiliser un tableau de AbstractReduce
    private HashMap<String,String> table;   // elle contient le mot de passe et hash qui sont dans le fichier passwordFilePath
    protected String passwordFilePath;          // C'est le chemin du fichier qui contient la table-arc-en-ciel
    protected HashingAlgorithm algorithmForHash;    // notre algo de hashage
    /**
     * @param algorithmForHash
     * @param hash
     * @param passwodfilePath
     */
    public RainbowTableCracker(HashingAlgorithm algorithmForHash,String hash, String passwodfilePath)
    {
        this.table = new HashMap<>(); this.hash = hash; this.passwordFilePath = passwodfilePath;
        reduceTab = new ArrayList<>();
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
     * paramètre au constructeur et retourne le mot de passe en clair ou null.
     * @return String password
     */
    public String findPasswordFromHash() 
    {
        this.loadFromFile(passwordFilePath);
        String password  = this.verifyHashInTableValue(this.hash);
        if(password != null)
            return this.regeneratePassword(password, this.hash);
        else
        {
            int i = reduceTab.size() -1; 
            String newHash = null;
            String setHash = this.hash; /* On initialise le hash à reduire */
            while(i >= 0)
            {
                newHash = this.reduceHash(setHash,i);
                password = this.verifyHashInTableValue(newHash);
                if(password != null)
                    return this.regeneratePassword(password, this.hash);
                i = i - 1;
            }
            return null; 
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
        String reduce = "";
        for(AbstractReduce r : reduceTab)
        {
            r.setHashToReduce(tmp);
            reduce = r.reduce();
            tmp = algorithmForHash.hashPassword(reduce);
            if(tmp.equals(newhash))
            {
                return reduce; 
            }
        }
        return reduce;
    }

    /**
     * Cette méthode permet de factoriser le code
     * @param r
     * @param hashToReduce
     * @return String Hash
     */
    public String reduceHash(String hashToReduce, int i)
    {
        int size = reduceTab.size();
        AbstractReduce reduce = null;
        String setHash = hashToReduce;
        if(i == size -1)
        {
            reduce = reduceTab.get(i);
            reduce.setHashToReduce(setHash);/*  On initialise le hash à reduire */ 
            setHash = algorithmForHash.hashPassword(reduce.reduce()); 
        }
        else
        {
            int j = i;
            while( j < size )
            {
                reduce = reduceTab.get(j);
                reduce.setHashToReduce(setHash);/*  On initialise le hash à reduire */ 
                setHash = algorithmForHash.hashPassword(reduce.reduce());
                j = j+1;
            }
        }
        return setHash;
    }
    /*
     * Setter de l'attribut reduceTab
     */
    public void setReduceTab(ArrayList<AbstractReduce> reduceListe)
    {
        this.reduceTab = reduceListe;
    }
    /*
     * Getter
     */
    public HashMap<String, String> getTable() {
        return table;
    }
}
