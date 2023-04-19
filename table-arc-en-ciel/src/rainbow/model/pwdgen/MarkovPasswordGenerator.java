package rainbow.model.pwdgen;

import java.util.*;
import rainbow.model.utils.*;

public class MarkovPasswordGenerator{
    String pathname;
    String alphabet;
    int[][] improbabilities;
    HashMap<Character, Integer> indexes;    

    public MarkovPasswordGenerator(String pwd_pathname, String alphabet){
        this.pathname = pwd_pathname;
        this.alphabet = alphabet;
        this.improbabilities = new int[alphabet.length()][alphabet.length()];
        this.indexes = new HashMap<>();
    }

    // Construis l'arbre de Markov
    public Tree buildTree(int imax){
        Tree markovTree = new Tree();
        Node root = new Node(Character.MIN_VALUE, imax);
        markovTree.setRoot(root);

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        int alphabetSize = alphabet.length();

        for(int i = 0; i < alphabetSize; i++){
            for(int j = 0; j < alphabetSize; j++){
                System.out.println(improbabilities[i][j]);
            }
        }
        
        while(!queue.isEmpty()){
            Node current = queue.remove();
            int imp;
            for(int i = 0; i < alphabetSize; i++){
                imp = Character.compare(current.getCharacter(), Character.MIN_VALUE) == 0 ? this.improbabilities[i][i] : this.improbabilities[indexes.get(current.getCharacter())][i];
                if ((current.getImprobability() - imp) >= 0){
                    Node node = new Node(this.alphabet.charAt(i), current.getImprobability() - imp);
                    current.addChild(node);
                    queue.add(node);
                }
            }
        }
        return markovTree;
    }

    public void calculateTransitionTable(){
        int alphabetSize = alphabet.length();
        double[][] transitionsTable = new double[alphabetSize][alphabetSize];
        // Cree une table de transition initiale avec les probabilités de
        // 1 / |alphabet|
        for(int i = 0; i < alphabetSize; i++){
            for(int j = 0; j < alphabetSize; j++){
                transitionsTable[i][j] = 1.0/alphabetSize;
            }
        }

        for(int i = 0; i < alphabetSize; i++){
            indexes.put(alphabet.charAt(i), i);
        }

        SimpleFile pwd = new SimpleFile(this.pathname);
        String fullFile = pwd.readFile();
        int[][] transitonCounts = new int[alphabetSize][alphabetSize];

        // Compte de nombre d'occurence de transitions dans les mots de 
        // de références
        for(String password : fullFile.split("\n")){
            int previousState = this.indexes.get(password.charAt(0));
            for(int i = 1; i < password.length(); i++){
                int currentState = this.indexes.get(password.charAt(i));
                transitonCounts[previousState][currentState] += 1;
                previousState = currentState;
            }
        }

        // Calcule les probabilités de transition puis les improbabilités
        int totalTransitions = 0;
        for(int i = 0; i < alphabetSize; i++){
            for(int j = 0; j < alphabetSize; j++){
                totalTransitions += transitonCounts[i][j];
            }
        }

        for(int i = 0; i < alphabetSize; i++){
            for(int j = 0; j < alphabetSize; j++){
                if(totalTransitions > 0){
                    transitionsTable[i][j] = (double)transitonCounts[i][j] / totalTransitions;
                }

                if(transitonCounts[i][j] == 0){
                    improbabilities[i][j] = 1000;
                } else {
                    double improbableMeasure = Math.ceil(-10 * Math.log10(transitionsTable[i][j]));
                    this.improbabilities[i][j] = (int)improbableMeasure;
                }
                
            }
        }
    }
}