package rainbow.model.utils;

import java.util.*;

public class Node{
    private char c;
    private int improbability;
    private List<Node> children;
    private int nbOfChild;

    public Node(char c, int improbability){
        this.c = c;
        this.improbability = improbability;
        this.children = new ArrayList<Node>();
        this.nbOfChild = 0;
    }

    public char getCharacter(){
        return this.c;
    }

    public int getImprobability(){
        return this.improbability;
    }

    public void setCharacter(char c){
        this.c = c;
    }

    public void setImprobability(int improbability){
        this.improbability = improbability;
    }

    public void addChild(Node child){
        this.children.add(child);
        this.nbOfChild++;
    }

    public int getChildrenNumber(){
        return this.nbOfChild;
    }

    public List<Node> getChildren(){
        return this.children;
    }
}