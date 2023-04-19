package rainbow.model.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class AlgoHashing implements HashingAlgorithm{
    private String algo;
    public AlgoHashing(String algo)
    {
        this.algo = algo;
    }
    public String getAlgo() {
        return algo;
    }
    public void setAlgo(String algo) {
        this.algo = algo;
    }
    public String hashPassword(String password){
        try {

            MessageDigest md = MessageDigest.getInstance(algo);

            byte[] messageDigest = md.digest(password.getBytes());

            BigInteger intValue = new BigInteger(1, messageDigest);

            String hashtext = intValue.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
