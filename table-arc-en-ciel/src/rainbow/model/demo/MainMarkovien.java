package rainbow.model.demo;

import rainbow.model.pwdgen.*;
import rainbow.model.utils.*;

public class MainMarkovien {
    public static void main(String[] args) {

        MarkovPasswordGenerator generator = new MarkovPasswordGenerator("./DataBase/base_pwd.txt", "AB");
        generator.calculateTransitionTable();
        Tree t = generator.buildTree(32);
        System.out.println(t.getLeavesNumber());
    }
}
