package rainbow.model.reduction;

import java.math.BigInteger;
/**
 * Reduction pour l'algorithme markovien
 * Cette classe prend en entrée le cardinal de notre ensemble de mots de passe.
 * @author Ahmat
*/
public class ReductionMarkov extends AbstractReduce {
    private int passwordListLength;

    public ReductionMarkov(int length)
    {
        this.passwordListLength = length;
    }

    @Override
    /**
     * Elle permet de convertir un hash en un entier modulo le cardianl de notre ensemble de mots de passes.
     */
    public String reduce() {
        BigInteger hashToBigInteger = new BigInteger(this.hashToReduce, 16);
        BigInteger listLength = new BigInteger(String.valueOf(this.passwordListLength));
        return String.valueOf(hashToBigInteger.mod(listLength));
    }

    public void setPasswordListLength(int passwordListLength) {
        this.passwordListLength = passwordListLength;
    }

    public int getPasswordListLength() {
        return passwordListLength;
    }
}
