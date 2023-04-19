package rainbow.model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rainbow.model.hashing.HashingAlgorithm;
import rainbow.model.reduction.AbstractReduce;
import rainbow.model.utils.UtilForRainbow;


/**
 * Cette classe nous permet de construire la Table arc-en-ciel à partir 
 * d'un fichier de mot de passe construit en avant sous la forme de
 *      "Pass" "Hash"
 * Elle prend en entrer un algorithme de hashage.
 * @author Ahmat
*/
public class RainbowTableGenerator
{
    protected ArrayList<AbstractReduce> reduceTab; // Nos reductions, après pour bien optimiser le code, on utilise un tableau de AbstractReduce
    protected HashingAlgorithm algorithmForHash;    // notre algo de hashage
    private HashMap<String,String> table;       // C'est notre RainbowTable, elle contient la table arc en ciel
    private HashMap<String,String> passTable;   // elle contient le mot de passe et hash qui sont dans le fichier password.txt
    public RainbowTableGenerator(HashingAlgorithm algorithmForHash)
    {
        table = new HashMap<>();
        passTable = new HashMap<>();
        reduceTab = new ArrayList<>();
        this.algorithmForHash = algorithmForHash;
    }
    /*
     * Charger la Table arc en ciel à partir d'un fichier 
     * qui contient de mots de passe et leur hash
     * @param String fileName
     * @return Hashmap<String,String> passTable ou null
    */
    public void loadFromFile(String filename)
    {
        passTable = UtilForRainbow.hashMapFromTextFile(filename);
    }
    /*
     * Enregistre la table arc en ciel dans le fichier
     * RainowTable.txt
    */
    public void saveInFile(String filename)
    {
        this.createRainbowTable();
        UtilForRainbow.writeInFileFromHashMap(table,filename,false);
    }
   
    /*
     * Setter de l'attribut reduceTab
     */
    public void setReduceTab(ArrayList<AbstractReduce> reduceListe)
    {
        this.reduceTab = reduceListe;
    }
    /*
     * Getter de l'attribut table
     * @return HashMap<String,String> table.
     */
    public HashMap<String,String> getTable()
    {
        return table;
    }
    /*
     * C'est pour créer la table arc en ciel à partir de
     * l'attribut passTable
     */
    public void createRainbowTable()
    {
        String tmp = "";
        for(Map.Entry<String, String> hashmap: passTable.entrySet())
        {
            String setHash = hashmap.getValue();
            for(AbstractReduce reduce : reduceTab)
            {
                reduce.setHashToReduce(setHash);         // On initialise le hash à reduire puis on reduit le hash
                tmp = algorithmForHash.hashPassword(reduce.reduce());  // On rehashe à nouveau le mot de passe trouver par reduction
                setHash = tmp;
            }
            table.put(hashmap.getKey(),tmp);
        }
    } 
}