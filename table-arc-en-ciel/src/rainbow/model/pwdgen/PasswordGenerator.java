package rainbow.model.pwdgen;

import java.util.*;

import rainbow.model.hashing.HashingAlgorithm;
import rainbow.model.utils.SimpleFile;

public class PasswordGenerator {
    private int length;
    private HashMap<String, String> entries;
    private PasswordStrategy strategy;

    /**
     * @param length
     */
    public PasswordGenerator(int length) {
        this.length = length;
        this.entries = new HashMap<String, String>();
    }


    /**
     * @return
     */
    private String getRandomPassword() {
        return strategy.getRandomPassword(this.length);
    }

    /**
     * @param numberOfPassword
     * @return
     */
    private Set<String> generatePlainPassword(int numberOfPassword){
        Set<String> passwords = new HashSet<>();

        while(passwords.size() < numberOfPassword){
            passwords.add(getRandomPassword());
        }
        return passwords;
    }
    /**
     * @param algorithm
     */
    public void generatePasswordHash(HashingAlgorithm algorithm,int numberOfPassword) {
        Set<String> passwords = generatePlainPassword(numberOfPassword);
        String hash;
        for (String password : passwords) {
            hash = algorithm.hashPassword(password);
            this.entries.put(password, hash);
        }
    }
    /**
     * 
     */
    public void savePasswords(String filename) {
        SimpleFile file = new SimpleFile(filename);
        file.writeFile(toString());
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        String content = "";
        for(Map.Entry<String, String> entry: this.entries.entrySet()){
            content += entry.getKey() + " " + entry.getValue() + "\n";
        }
        return content.trim();
    }
    /**
     * @return
     */
    public HashMap<String,String> getEntries(){
        return this.entries;
    }
    /**
     * @param newStrategy
     */
    public void setStrategy(PasswordStrategy newStrategy){
        this.strategy = newStrategy;
    }
}