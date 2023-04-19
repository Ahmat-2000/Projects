package rainbow.model.utils;

public class Tree{
    private Node root;

    public Tree(){
        root = null;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node root){
        this.root = root;
    }

    private int countLeaves(Node node){
        if(node == null)
            return 0;
        if (node.getChildrenNumber() == 0){
            return 1;
        }
        int count = 0;
        for(Node child : node.getChildren()){
            count += countLeaves(child);
        }
        return count;
    }

    public int getLeavesNumber(){
        return countLeaves(root);
    }
}