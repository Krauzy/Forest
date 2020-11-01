package forest.trees.b;

import forest.Forest;

public class Node {
    private int[] info;
    private int[] position;
    private Node[] link;
    private int TL;
    
    public Node() {
        this.info = new int[2 * Forest.RULE + 1];
        this.position = new int[2 * Forest.RULE + 1];
        this.link = new Node[2 * Forest.RULE + 2];
    }
    
    public Node(int info, int position) {
        this.info = new int[2 * Forest.RULE + 1];
        this.position = new int[2 * Forest.RULE + 1];
        this.link = new Node[2 * Forest.RULE + 2];
        this.info[0] = info;
        this.position[0] = position;
        this.TL = 1;
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
        return this.link[pos];
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
    
    public int getIndex(int info) {
        int response = -1;
        for (int i = 0; i < this.TL && response == -1; i++) {
            if (this.info[i] == info)
                response = this.position[i];
        }
        return response;
    }
    
    public int getAlloc(int info) {
        int i = 0;
        while (i < this.TL && info > this.info[i])
            i++;
        return i;
    }
    
    public void arrange(int position) {
        this.link[this.TL + 1] = this.link[this.TL];
        for (int i = this.TL; i > position; i--) {
            this.info[i] = this.info[i - 1];
            this.position[i] = this.position[i - 1];
            this.link[i] = this.link[i - 1];
        }
    }
    
    public void arrangeDelete(int position) {
        for (int i = position; i < this.TL - 1; i++) {
            this.info[i] = this.info[i + 1];
            this.position[i] = this.position[i + 1];
            this.link[i] = this.link[i + 1];
        }
    }
    
    public void dismantle(int position) {
        for (int i = position; i < this.TL; i++) {
            this.info[i] = this.info[i + 1];
            this.position[i] = this.position[i + 1];
            this.link[i] = this.link[i + 1];
        }
    }
}
