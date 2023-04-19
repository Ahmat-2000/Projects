package rainbow.model.reduction;

public class Reduce1 extends AbstractReduce{
   
    @Override
    /**
     * Cette méthode répresente notre prémière fonction de reduction
     * qui utilise un algorithme simple pour reduire le hash.
     * Elle prends les n premiers caractères du hash en partant du 
     * début du hash.
     * @return string password
     * @author Ahmat
     */
    public String reduce() {
        String password = "";
        int tmp, i;
        int charsetLength = charset.length();
        for(i= 0;i<this.reduceLength;i++)
        {
            tmp = (int) hashToReduce.charAt(i);
            password += charset.charAt(tmp % (charsetLength));
        }
        return password;
    }
    
}
