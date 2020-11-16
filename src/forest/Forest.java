package forest;

import forest.trees.b.BTree;
import forest.trees.bplus.BPlusTree;
import java.util.Random;

/**
 *
 * @author Krauzy
 * @github: github.com/krauzy
 * 
 */
public class Forest {
    
    public static int RULE = 5;
    
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
    
    public void BPlusTreeApp() {
        Random rand = new Random();
        BPlusTree tree = new BPlusTree();
        for(int i = 0; i < 20; i++) {
            tree.insert(rand.nextInt(98) + 1);
        }
        tree.show();
    }
    
    public static void main(String[] args) {
        Forest forest = new Forest();
        //forest.BTreeApp();
        forest.BPlusTreeApp();
    }
    
}
