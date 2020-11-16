package forest.trees.bplus;

import forest.Forest;

public class Node {
    private int[] info;
    private int[] position;
    private Node[] link;
    private int TL;
    private Node back;
    private Node next;
    
    public Node() {
        this.info = new int[Forest.RULE];
        this.position = new int[Forest.RULE];
        this.link = new Node[Forest.RULE + 1];
        this.back = this.next = null;
        this.TL = 0;
    }
    
    public Node(int info) {
        this.info = new int[Forest.RULE];
        this.position = new int[Forest.RULE];
        this.link = new Node[Forest.RULE + 1];
        this.back = this.next = null;
        this.TL = 0;
        this.info[this.TL++] = info;
    }

    public int getInfo(int pos) {
        return this.info[pos];
    }

    public void setInfo(int pos, int info) {
        this.info[pos] = info;
    }

    public int getPosition(int pos) {
        return this.position[pos];
    }

    public void setPosition(int pos, int position) {
        this.position[pos] = position;
    }

    public Node getLink(int pos) {
        return link[pos];
    }

    public void setLink(int pos, Node link) {
        this.link[pos] = link;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public Node getBack() {
        return back;
    }

    public void setBack(Node back) {
        this.back = back;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    public void arrange(int pos) {
        this.link[this.TL + 1] = this.link[this.TL];
        for (int i = this.TL; i > pos; i--) {
            this.info[i] = this.info[i - 1];
            this.position[i] = this.position[i - 1];
            this.link[i] = this.link[i - 1];
        }
    }
    
    @SuppressWarnings("empty-statement")
    public int getPOS(int info) {
        int i;
        for(i = 0; i < this.TL && info > this.info[i]; i++);
        return i;
    }
}
