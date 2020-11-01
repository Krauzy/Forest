package forest;

import forest.trees.b.BTree;
import java.util.Random;

/**
 *
 * @author Krauzy
 * @github: github.com/krauzy
 * 
 */
public class Forest {
    
    public static int RULE = 2;
    
    public Forest() {}
    
    public void BTreeApp() {
        Random rand = new Random();
        BTree tree = new BTree();
        int[] arr = new int[20];
        for(int i = 0; i < 20; i++) {
            arr[i] = rand.nextInt(98) + 1;
            tree.insert(arr[i], 0);
        }
        tree.inSort(tree.getRoot());
        System.out.println("\nREMOVER -> " + arr[10]);
        tree.delete(arr[10]);
        tree.inSort(tree.getRoot());
    }
    
    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.BTreeApp();
    }
    
}
