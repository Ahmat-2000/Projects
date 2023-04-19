package rainbow.model.pwdgen;

import java.util.Random;
public class RandomPassWord implements PasswordStrategy {
    private String lowerCaseletters;
    public RandomPassWord(String lowerCaseletters)
    {
        this.lowerCaseletters = lowerCaseletters;
    }
    public String getRandomPassword(int length){
        char[] password = new char[length];
        Random random = new Random();

        for(int i = 0; i < length; i++){
            password[i] = lowerCaseletters.charAt(random.nextInt(lowerCaseletters.length()));
        }
        return new String(password);
    }
}
