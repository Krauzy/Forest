package forest.trees.bplus;

import forest.Forest;

public class BPlusTree {
    private Node root;
    
    public BPlusTree() {}
    
    public Node getRoot() {
        return this.root;
    }
    
    public Node getChild(int info) {
        int i = 0;
        Node aux = this.root;
        while (aux.getLink(1) != null) {
            i = 0;
            while(i < aux.getTL() && info > aux.getInfo(i)) i++;
            aux = aux.getLink(i);
        }
        return aux;
    }
    
    public Node getOwner (Node child, int info) {
        Node aux = this.root;
        Node owner = aux;
        int i;
        while(aux != child) {
            i = 0;
            while(i < aux.getTL() && info > aux.getInfo(i)) i++;
            owner = aux;
            aux = aux.getLink(i);
        }
        return owner;
    }
    
    public void insert(int info) {
        Node child;
        Node owner;
        int pos;
        if(this.root == null)
            this.root = new Node(info);
        else {
            child = this.getChild(info);
            pos = child.getPOS(info);
            child.arrange(pos);
            child.setTL(child.getTL() + 1);
            child.setInfo(pos, info);
            child.setPosition(pos, info);
            if (child.getTL() > Forest.RULE - 1) {
                owner = getOwner(child, info);
                split(child, owner);
            }
        }
    }
    
    public void show() {
        Node aux = this.root;
        while (aux.getLink(0) != null)
            aux = aux.getLink(0);
        while(aux != null) {
            for(int i = 0; i < aux.getTL(); i++)
                System.out.println(aux.getInfo(i) + " ");
            aux = aux.getNext();
        }
    }
    
    public void split(Node child, Node owner) {
        Node t1 = new Node();
        Node t2 = new Node();
        int aux, mid, i, pos, info;
        mid = 0;
        if (child.getLink(0) == null) {
            aux = (Forest.RULE - 1) / 2;
            for (i = 0; i < aux; i++) {
                t1.setInfo(i, child.getInfo(i));
                t1.setPosition(i, child.getPosition(i));
                t1.setTL(t1.getTL() + 1);
            }
            mid = aux;
            for (i = aux; i < Forest.RULE; i++) {
                t2.setInfo(i - (aux), child.getInfo(i));
                t2.setPosition(i - (aux), child.getPosition(i));
                t2.setTL(t2.getTL() + 1);
            }
        } else {
            aux = (Forest.RULE / 2);
            for (i = 0; i < aux; i++) {
                t1.setInfo(i, child.getInfo(i));
                t1.setPosition(i, child.getPosition(i));
                t1.setLink(i, child.getLink(i));
                t1.setTL(t1.getTL() + 1);
            }
            t1.setLink(aux, child.getLink(aux));
            mid = aux++;
            for (i = aux; i < Forest.RULE; i++) {
                t2.setInfo(i - (aux), child.getInfo(i));
                t2.setPosition(i - (aux), child.getPosition(i));
                t2.setLink(i - (aux), child.getLink(i));
                t2.setTL(t2.getTL() + 1);
            }
            t2.setLink(i - aux, child.getLink(Forest.RULE));
        }
        if (child == owner) {
            child.setInfo(0, child.getInfo(mid));
            child.setPosition(0, child.getPosition(mid));
            child.setLink(0, t1);
            child.setLink(1, t2);
            child.setTL(1);
        } else {
            info = child.getInfo(mid);
            pos = owner.getPOS(info);
            owner.arrange(pos);
            owner.setTL(owner.getTL() + 1);
            owner.setInfo(pos, child.getInfo(mid));
            owner.setPosition(pos, child.getPosition(mid));
            owner.setLink(pos, t1);
            owner.setLink(pos + 1, t2);
            if (owner.getLink(0).getLink(0) == null) {
                for (int j = 0; j < owner.getTL(); j++) {
                    owner.getLink(j).setNext(owner.getLink(j + 1));
                    owner.getLink(j + 1).setBack(owner.getLink(j));
                }
                owner.getLink(owner.getTL()).setBack(owner.getLink(owner.getTL() - 1));
            }
            if (owner.getTL() > Forest.RULE - 1) {
                child = owner;
                info = child.getInfo(mid);
                owner = this.getOwner(child, info);
                split(child, owner);
            }
        }
    }
}
