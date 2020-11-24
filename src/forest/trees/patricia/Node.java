package forest.trees.patricia;

public class Node {
    public static final int LETTERS = 26;
    private Node[] children;
    private StringBuilder[] words;
    private boolean _final;
    
    public Node(boolean _final) {
        children = new Node[Node.LETTERS];
        words = new StringBuilder[Node.LETTERS];
        this._final = _final;
    }
    
    public void setChildren(Node[] childrens) {
        this.children = childrens;
    }
    
    public Node[] getChildren() {
        return this.children;
    }
    
    public void setChild(Node child, int index) {
        this.children[index] = child;
    }
    
    public Node getChild(int index) {
        return this.children[index];
    }
    
    public void setWords(StringBuilder[] words) {
        this.words = words;
    }
    
    public StringBuilder[] getWords() {
        return this.words;
    }
    
    public void setWord(StringBuilder word, int index) {
        this.words[index] = word;
    }
    
    public StringBuilder getWord(int index) {
        return this.words[index];
    }
    
    public void setFinal(boolean _final) {
        this._final = _final;
    }
    
    public boolean isFinal() {
        return this._final;
    }
}
