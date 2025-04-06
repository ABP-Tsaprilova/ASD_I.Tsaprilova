package labs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class node {
    int value;
    node left;
    node right;
    node(int value) {
        this.value = value;
        left=right=null;
    }
}
class BinaryTree{
    node root;

    void add(int value){
        root= addRecursive(root, value);
    }

    private node addRecursive(node current, int value) {
        if(current==null){
            return new node(value);
        }
        if(value<current.value){
            current.left=addRecursive(current.left, value);
        }else if(value>current.value){
            current.right=addRecursive(current.right, value);
        }
        return current;
    }
    int find(int element){
        if(root==null){
            return -1;
        }
        return findRecursive( root, element, 0);
    }
    private int findRecursive(node current, int element, int depth) {
        if (current == null) {
            return -1;
        }
        if(current.value==element){
            return depth;
        }
        if(element<current.value){
            return findRecursive(current.left, element, depth+1);
        }else{
            return findRecursive(current.right, element, depth+1);
        }
    }
    int findMaxDepth(){
        if(root==null){
            return 0;
        }
        return findMaxDepthRecursive(root);
    }
    private int findMaxDepthRecursive(node current){
        if(current==null){
            return 0;
        }
        int leftDepth = findMaxDepthRecursive(current.left);
        int rightDepth = findMaxDepthRecursive(current.right);

        return Math.max(leftDepth,rightDepth)+1;
    }

}
public class Lab_7 {
    public static void lab_7() {
        BinaryTree tree=new BinaryTree();

        tree.add(10);
        tree.add(2);
        tree.add(13);
        tree.add(24);
        tree.add(5);
        tree.add(61);
        tree.add(7);
        tree.add(81);
        tree.add(9);
        tree.add(170);

        int pathLength = tree.find(170);

        if(pathLength !=-1){
            System.out.println( "path to 10: "+pathLength);
        }else{
            System.out.println("error");
        }

        int MaxDepth = tree.findMaxDepth();
        System.out.println("max depth:" +MaxDepth);

        List<Integer> busNumbers = List.of(11, 32, 23, 12, 6, 52, 47, 63, 69, 50, 43, 28, 35, 33, 42, 56, 55, 101);

        List<Integer> secondPlace = new ArrayList<>();

        for(int i: busNumbers){
            if(i%2!=0){
                secondPlace.add(i);
            }
        }
        Collections.sort(secondPlace,Collections.reverseOrder());
        System.out.println("sorted buses in second place: "+secondPlace);

    }
}