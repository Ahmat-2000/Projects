package rainbow.model.reduction;

/**
 * C'est une classe abstraite qui implemente l'interface IReduce 
 * Elle sera la classe parente de Reduce1, Reduce2 ...
 * @author Ahmat
 */
public abstract class AbstractReduce implements IReduce {
    protected String hashToReduce;                          // Attribut sur notre hash à reduire 
    protected int reduceLength;                             // Attribut pour la taille de notre reduction
    protected String charset;                         // Attribut sur notre ensemble des charcharcter utilisé dans notre mot de passe en clair.
    /*
     * Un setter sur le hash à reduire.
     * @param String hash
     */
    public abstract String reduce() ;
    public void setHashToReduce(String hashToReduce) {
        this.hashToReduce = hashToReduce;
    }
    public void setReduceLength(int reduceLength) {
        this.reduceLength = reduceLength;
    }
    public void setcharset(String charset) {
        this.charset = charset;
    }
    
}
