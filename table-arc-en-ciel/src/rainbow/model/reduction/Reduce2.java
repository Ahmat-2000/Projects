package rainbow.model.reduction;

public class Reduce2 extends AbstractReduce {
    @Override
    /**
     * Cette méthode répresente notre deuxième fonction de reduction
     * qui utilise un algorithme simple pour reduire le hash.
     * Elle prends les n derniers caractères du hash en commençant du 
     * dernier caractère avec n la taille du mot de passe.
     * @return string password
     * @author Ahmat
     */
    public String reduce() {
        String password = "";
        int tmp = 0; 
        int hashLength = this.hashToReduce.length() -1;
        int charsetLength = charset.length();
        for(int i = 0 ; i < this.reduceLength; i++)
        {
            tmp = (int) hashToReduce.charAt(hashLength -i);
            password += charset.charAt(tmp % (charsetLength));
        }
        return password;
    }
}
