package forest.trees.patricia;

public class PatriciaTree {
    private final Node root;
    private final char value;
    
    public PatriciaTree() {
        root = new Node(false);
        value = 'a';
    }
    
    private StringBuilder cloneString(CharSequence string, int index) {
        StringBuilder str = new StringBuilder(100);
        while (index != string.length())
            str.append(string.charAt(index++));
        return str;
    }
    
    public void insert(String word) {
        Node node = root;
        int i = 0;
        boolean flag = false;
        
        while(!flag && i < word.length() && node.getWord(word.charAt(i) - value) != null) {
            int pos = word.charAt(i) - value;
            int j = 0;
            StringBuilder label = node.getWord(pos);
            while(j < label.length() && i < word.length() && label.charAt(j) == word.charAt(i)) { ++i; ++j; }
            if(j == label.length())
                node = node.getChild(pos);
            else {
                if(i == word.length()) {
                    Node child = node.getChild(pos);
                    Node newChild = new Node(true);
                    StringBuilder xLabel = cloneString(label, j);
                    label.setLength(j);
                    node.setChild(newChild, pos);
                    newChild.setChild(child, xLabel.charAt(0) - value);
                    newChild.setWord(xLabel, xLabel.charAt(0) - value);
                }
                else {
                    StringBuilder xlabel = this.cloneString(label, j);
                    Node newChild = new Node(false);
                    StringBuilder xWord = this.cloneString(word, i);
                    Node temp = node.getChild(pos);
                    label.setLength(j);
                    node.setChild(newChild, pos);
                    newChild.setWord(xlabel, xlabel.charAt(0) - value);
                    newChild.setChild(temp, xlabel.charAt(0) - value);
                    newChild.setWord(xWord, xWord.charAt(0) - value);
                    newChild.setChild(new Node(true), xWord.charAt(0) - value);
                }
                flag = true;
            }
        }
        if(flag == false) {
            if(i < word.length()) {
                node.setWord(this.cloneString(word, i), word.charAt(i) - value);
                node.setChild(new Node(true), word.charAt(i) - value);
            }
            else
                node.setFinal(true);
        }
    }
    
    private void log(Node node, StringBuilder string) {
        if(node.isFinal())
            System.out.println(string);
        for(int i = 0; i < node.getWords().length; ++i) {
            if(node.getWord(i) != null) {
                int size = string.length();
                string = string.append(node.getWord(i));
                log(node.getChild(i), string);              // Recursão
                string = string.delete(size, string.length());
            }
        }
    }
    
    public void show() {
        log(root, new StringBuilder());
    }
    
    public void showPerLevel() {
        Queue<Node> queue = new Queue<>();
        boolean flag = false;
        Node temp = root;
        queue.enqueue(temp);
        while(!queue.isEmpty()) {
            temp = queue.dequeue();
            for(int i = 0; i < temp.getWords().length; i++) {
                if(temp.getWord(i) != null) {
                    System.out.print(temp.getWord(i) + " ");
                    flag = true;
                }
                if(i+1 == temp.getWords().length && flag)
                    System.out.println("");
            }
            for(int i = 0; i < temp.getChildren().length; i++)
                if(temp.getChild(i) != null)
                    queue.enqueue(temp.getChild(i));
            flag = false;
        }
    }
}
