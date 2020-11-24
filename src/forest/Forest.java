package forest;

import forest.trees.b.BTree;
import forest.trees.bplus.BPlusTree;
import forest.trees.patricia.PatriciaTree;
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
    
    public void PatriciaTreeApp() {
        PatriciaTree patricia = new PatriciaTree();
        patricia.insert("time");
        patricia.insert("people");
        patricia.insert("way");
        patricia.insert("water");
        patricia.insert("words");
        patricia.insert("man");
        patricia.insert("work");
        patricia.insert("word");
        patricia.insert("part");
        patricia.insert("place");
        patricia.insert("years");
        patricia.insert("number");
        patricia.insert("men");
        patricia.insert("name");
        patricia.insert("mister");
        patricia.insert("home");
        patricia.insert("air");
        patricia.insert("line");
        patricia.insert("end");
        patricia.insert("house");
        patricia.insert("world");
        patricia.insert("school");
        patricia.insert("form");
        patricia.insert("food");
        patricia.insert("children");
        patricia.insert("feet");
        patricia.insert("land");
        patricia.insert("side");
        patricia.insert("boy");
        patricia.insert("animals");
        patricia.insert("life");
        patricia.insert("head");
        patricia.insert("kind");
        patricia.insert("page");
        patricia.insert("earth");
        patricia.insert("hand");
        patricia.insert("year");
        patricia.insert("mother");
        patricia.insert("light");
        patricia.insert("country");
        patricia.insert("father");
        patricia.insert("picture");
        patricia.insert("eyes");
        patricia.insert("times");
        patricia.insert("help");
        patricia.insert("thought");
        patricia.insert("show");
        patricia.insert("night");
        patricia.insert("story");
        patricia.insert("boys");
        patricia.insert("bear");
        patricia.insert("bell");
        patricia.insert("bid");
        patricia.insert("bull");
        patricia.insert("buy");
        patricia.insert("sell");
        patricia.insert("stock");
        patricia.insert("stop");
        System.out.println("-----------------------PALAVRAS------------------------------------");
        patricia.show();
        System.out.println("------------------------NÍVEIS-------------------------------------");
        patricia.showPerLevel();
    }
    
    public static void main(String[] args) {
        Forest forest = new Forest();
        //forest.BTreeApp();
        //forest.BPlusTreeApp();
        forest.PatriciaTreeApp();
    }
    
}
