package rainbow.model.reduction;

public class Reduce3 extends AbstractReduce{
    @Override
    /**
     * Cette méthode répresente notre troisieume fonction de reduction
     * qui utilise un algorithme simple pour reduire le hash.
     * Elle prends les n caractères du hash situés dans cette intervalle
     * [1 , 0 , 0 , 1 , 3 , 6 , 10 , 15 , 21 , 28] 
     * la taille du mot de passe ne doit pas dépassée 10 caractères.
     * @return string password
     * @author Ahmat
     */
    public String reduce() {
        String password = "";
        int tmp, i;
        int charsetLength = charset.length();
        int k = 0;
        for(i= 1 ;i<=this.reduceLength;i++)
        {
            k = k + i;
            tmp = (int) this.hashToReduce.charAt(k);
            password += this.charset.charAt(tmp % (charsetLength));
            k -= 3;
        }
        return password;
    }
    
}
