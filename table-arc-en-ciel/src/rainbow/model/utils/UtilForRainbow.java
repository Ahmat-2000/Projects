package rainbow.model.utils;

import java.io.*;
import java.util.*;
/**
 * @author Ahmat
 */
public class UtilForRainbow {
    public static HashMap<String, String> hashMapFromTextFile(String filePath)
    {
        HashMap<String, String> map = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            // create file object
            File file = new File(filePath);
            // create BufferedReader object from the File
            br = new BufferedReader(new java.io.FileReader(file));
            String line = null;
            // read file line by line
            while ((line = br.readLine()) != null) {
                // split the line by :
                String[] parts = line.split(" ");
                // first part is passWord, second is hash
                String passWord = parts[0].trim();
                String hash = parts[1].trim();
                // put password, hash in HashMap if they are
                // not empty
                if (!passWord.equals("") && !hash.equals(""))
                    map.put(passWord, hash);
            }
        }
        catch (Exception e) {
            System.out.println("Un problème est survenu pendant l'ouverture du fichier"+filePath+".\nImpossible de lire le fichier.\n"+
            "Soit le fichier n'existe pas, soit il est renommé :)");
        }
        finally {
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                    System.out.println("Un problème est survenu pendant la fermeture du fichier"+filePath+" \n");
                };
            }
        }
        return map;
    }
    /*
     * Ecrit le contenu d'une HashMap dans un fichier en fonction de valueJust
     */
    public static void writeInFileFromHashMap(HashMap<String,String> content,String outputFilePath,boolean valueJust)
    {
        File file = new File(outputFilePath);
        String contenu = valueJust ? hashMapToStringOfValue(content) : hashMapToString(content);
        BufferedWriter bf = null;
        try {
            // création du BufferedWriter pour le fichier de sorti
            bf = new BufferedWriter(new FileWriter(file));
            // iteration de notre map
            bf.write(contenu); 
        }
        catch (IOException e) {
            System.out.println("Un problème est survenu pendant l'ouverture du fichier"+outputFilePath+".\nImpossible d'écrire dans le fichier.\n"+
            "Soit le fichier n'existe pas, soit il est renommé :)");
        }
        finally 
        {
            try {
                // fermeture du BufferWriter
                bf.close();
            }
            catch (Exception e) {
                System.out.println("Un problème est survenu pendant la fermeture du fichier"+outputFilePath+" \n");
            }
        }
    }
     /*
     * Cette méthode transforme un HashMap en String
     * @param HashMap<String,String> map
     * @return String content
    */
    public static String hashMapToString(HashMap<String,String> map){
        String content = "";
        for(Map.Entry<String, String> line: map.entrySet()){
            content += line.getKey() + " " + line.getValue() + "\n";
        }
        return content.trim();
    }
    /*
     * pour enregistrer les valeurs d'une hashMap dans un fichier
     */
    public static String hashMapToStringOfValue(HashMap<String,String> map){
        String content = "";
        for(Map.Entry<String, String> line: map.entrySet()){
            content += line.getValue() + "\n";
        }
        return content.trim();
    }
    /*
     * Elle compte le nombre de lignes dans un fichier
     */
    public static int nbFileLine(String filename)
    {
        int i = 0;
        try {
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine())
            {
                i = i +1;
                sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }
     /*
     * pour afficher la table arc en ciel, le premier 
     * mot de pass en clair et le dernier hash de la table arc en ciel 
     */
    public static int print(HashMap<String, String> table)
    {
        String content = ""; int i = 0;
        for(Map.Entry<String, String> entry: table.entrySet()){
            i = i +1;
            content += entry.getKey() + " " + entry.getValue() + "\n";
        }
        System.out.println(content.trim());
        return i;
    }
    
    /**
     * Créer un fichier et écrit dessus le contenu
     * @param filename
     * @param contenu
     */
    public static void createAndWriteTextInFile(String filename,String contenu)
    {
        File file = new File(filename);
        BufferedWriter bf = null;
        try {
            // création du BufferedWriter pour le fichier de sorti
            bf = new BufferedWriter(new FileWriter(file));
            // iteration de notre map
            bf.write(contenu); 
        }
        catch (IOException e) {
            System.out.println("Un problème est survenu pendant l'ouverture du fichier.\nImpossible d'écrire dans le fichier"+filename+".\n"+
            "Soit le fichier n'existe pas, soit il est renommé :)");
        }
        finally 
        {
            try {
                // fermeture du BufferWriter
                bf.close();
            }
            catch (Exception e) {
                System.out.println("Un problème est survenu pendant la fermeture du fichier"+filename+" \n");
            }
        }
    }
    public static void writeFile(String filename,String content){
        try{
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n");
            fw.write(content);
            fw.close();
        } catch(IOException e){
            System.out.print(e.getMessage());

        }
    }
    /*
     * pour lire le fichier tempsDexecution
     */
}
