package forest.trees.b;

import forest.Forest;

public class BTree {
    
    private int m;
    private Node root;
    
    public BTree() {
        m = Forest.RULE;
        this.root = null;
    }
    
    public Node getRoot() {
        return this.root;
    }
    
    public Node getNode(int info) {
        Node temp = this.root;
        int p = temp.getAlloc(info);
        while (info != temp.getInfo(p) && temp.getLink(0) != null) {
            for (int i = 0; i < temp.getTL(); i++)
                p++;
            temp = temp.getLink(p);
        }
        return temp;
    }
    
    public void inSort(Node node) {
        if (node != null) {
            for (int i = 0; i < node.getTL(); i++) {
                this.inSort(node.getLink(i));
                System.out.print(node.getInfo(i) + " ");
            }
            this.inSort(node.getLink(node.getTL()));
        }
    }
    
    public void split(Node child, Node owner) {
        Node temp1 = new Node();
        Node temp2 = new Node();
        
        for (int i = 0; i < this.m; i++) {
            temp1.setInfo(i, child.getInfo(i));
            temp1.setPosition(i, child.getPosition(i));
            temp1.setLink(i, child.getLink(i));
        }
        temp1.setLink(this.m, child.getLink(this.m));
        temp1.setTL(this.m);
        for (int i = this.m + 1; i < 2 * this.m + 1; i++) {
            temp2.setInfo(i - this.m - 1, child.getInfo(i));
            temp2.setPosition(i - this.m - 1, child.getPosition(i));
            temp2.setLink(i - this.m - 1, child.getLink(i));
        }
        temp2.setLink(this.m, child.getLink(2 * this.m + 1));
        temp2.setTL(this.m);
        if (child == owner) {
            child.setInfo(0, child.getInfo(this.m));
            child.setPosition(0, child.getPosition(this.m));
            child.setLink(0, temp1);
            child.setLink(1, temp2);
            child.setTL(1);
        } else {
            int pos = owner.getAlloc(child.getInfo(this.m));
            owner.arrange(pos);
            owner.setInfo(pos, child.getInfo(this.m));
            owner.setPosition(pos, child.getPosition(this.m));
            owner.setLink(pos, temp1);
            owner.setLink(pos + 1, temp2);
            owner.setTL(owner.getTL() + 1);
            if (owner.getTL() > 2 * this.m) {
                child = owner;
                owner = this.getOwner(child, owner.getInfo(0));
                this.split(child, owner);
            }
        }
    }
    
    public Node getOwner(Node child, int info) {
        Node temp = this.root;
        Node owner = temp;
        while (temp != child) {
            int i = 0;
            while (i < temp.getTL() && info > temp.getInfo(i))
                i++;
            owner = temp;
            temp = temp.getLink(i);
        }
        return owner;
    }
    
    public void insert(int info, int pos) {
        if(this.root == null)
            root = new Node(info, pos);
        else {
            Node child = this.getChild(info);
            int p = child.getAlloc(info);
            child.arrange(p);
            child.setInfo(p, info);
            child.setPosition(p, pos);
            child.setTL(child.getTL() + 1);
            if (child.getTL() > 2 * this.m) {
                Node owner = this.getOwner(child, info);
                this.split(child, owner);
            }
        }
    }
    
    public Node getChild(int info) {
        Node temp = this.root;        
        while (temp.getLink(0) != null) {
            int i = 0;
            while (i < temp.getTL() && info > temp.getInfo(i))
                i++;
            temp = temp.getLink(i);
        }
        return temp;
    }
    
    public Node getChildDelete(int info) {
        Node temp = this.root;        
        while (temp.getLink(0) != null) {
            int i = 0;
            while (i < temp.getTL() && info > temp.getInfo(i))
                i++;
            if (info == temp.getInfo(i))
                return temp;
            else
                temp = temp.getLink(i);
        }
        return temp;
    }
    
    public Node getLeft(Node node) {
        Node left = node.getLink(0);
        while (left.getLink(left.getTL()) != null)
            left = left.getLink(left.getTL());
        return left;
    }

    public Node getRight(Node node) {
        Node right = node.getLink(node.getTL());
        while (right.getLink(0) != null)
            node = node.getLink(0);
        return right;
    }
    
    private void merge(Node node, Node owner, int pos) {
        Node neighbor;
        Node box = new Node();
        int i;
        int j;
        if (pos == 0) {
            neighbor = owner.getLink(pos + 1);
            for (i = 0; i < node.getTL(); i++) {
                box.setInfo(i, node.getInfo(i));
                box.setPosition(i, node.getPosition(i));
                box.setLink(i, node.getLink(i));
                box.setTL(box.getTL() + 1);
            }
            box.setLink(i, node.getLink(i));
            box.setInfo(i, owner.getInfo(pos));
            node.setPosition(node.getTL(), owner.getPosition(pos));
            owner.arrangeDelete(pos);
            owner.setTL(owner.getTL() - 1);
            box.setTL(box.getTL() + 1);
            i++;
            for (j = 0; j < neighbor.getTL(); j++) {
                box.setInfo(i, neighbor.getInfo(j));
                box.setPosition(i, box.getPosition(j));
                box.setLink(i, neighbor.getLink(j));
                box.setTL(box.getTL() + 1);
                i++;
            }
            box.setLink(i, neighbor.getLink(j));
        } else {
            neighbor = owner.getLink(pos - 1);
            i = 0;
            for (i = 0; i < neighbor.getTL(); i++) {
                box.setInfo(i, neighbor.getInfo(i));
                box.setPosition(i, box.getPosition(i));
                box.setLink(i, neighbor.getLink(i));
                box.setTL(box.getTL() + 1);
            }
            box.setLink(i, node.getLink(i));
            box.setInfo(i, owner.getInfo(pos - 1));
            node.setPosition(node.getTL(), owner.getPosition(pos - 1));
            box.setTL(box.getTL() + 1);
            box.arrangeDelete(pos - 1);
            owner.setTL(owner.getTL() - 1);
            i++;
            for (j = 0; j < node.getTL(); j++) {
                box.setInfo(i, node.getInfo(j));
                box.setPosition(i, node.getPosition(j));
                box.setLink(i, node.getLink(j));
                box.setTL(box.getTL() + 1);
                i++;
            }
            box.setLink(i, node.getLink(j));
        }

        if (owner.getTL() < this.m) {
            if (owner == this.root && owner.getTL() == 0)
                owner = this.root = box;
            else if (owner == this.root && owner.getTL() > 0)
                owner.setLink(pos, box);
            else {
                owner.setLink(pos, box);
                node = this.getChildDelete(owner.getInfo(0));
                owner = this.getOwner(node, node.getInfo(0));
                pos = owner.getAlloc(node.getInfo(0));
                this.merge(node, owner, pos);
            }
        } else
            owner.setLink(pos, box);
    }
    
    private boolean spread(Node node, Node owner, int pos) {
        Node neighbor;
        if (pos == 0) {
            neighbor = owner.getLink(pos + 1);
            if (neighbor.getTL() > m) {
                node.setInfo(node.getTL(), owner.getInfo(pos));
                node.setPosition(node.getTL(), owner.getPosition(pos));
                node.setTL(node.getTL() + 1);

                owner.setInfo(pos, neighbor.getInfo(0));
                owner.setPosition(pos, neighbor.getPosition(0));

                neighbor.arrangeDelete(0);
                neighbor.setTL(neighbor.getTL() - 1);
                return true;
            }
        } else {
            neighbor = owner.getLink(pos - 1);
            if (neighbor.getTL() > this.m) {
                node.arrange(0);
                node.setInfo(0, owner.getInfo(pos - 1));
                node.setPosition(0, owner.getPosition(pos - 1));
                node.setTL(node.getTL() + 1);
                owner.setInfo(pos - 1, neighbor.getInfo(neighbor.getTL() - 1));
                owner.setPosition(pos - 1, neighbor.getPosition(neighbor.getTL() - 1));
                neighbor.setTL(neighbor.getTL() - 1);
                return true;
            } else if (pos + 1 < owner.getTL() + 1) {
                neighbor = owner.getLink(pos + 1);
                if (neighbor.getTL() > this.m) {
                    node.setInfo(node.getTL(), owner.getInfo(pos));
                    node.setPosition(node.getTL(), owner.getPosition(pos));
                    node.setTL(node.getTL() + 1);
                    owner.setInfo(pos, neighbor.getInfo(0));
                    owner.setPosition(pos, neighbor.getPosition(0));
                    neighbor.arrangeDelete(0);
                    neighbor.setTL(neighbor.getTL() - 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean delete(int info) {
        Node owner;
        int i;
        Node right;
        Node left;
        if (this.root == null) {
            return false;
        } else {
            Node node = this.getChildDelete(info);
            int pos = node.getAlloc(info);
            if (node.getInfo(pos) == info) {
                if (node.getLink(0) != null) {
                    right = this.getRight(node);
                    left = this.getLeft(node);
                    if (left.getTL() > this.m || right.getTL() <= this.m) {
                        node.setInfo(pos, left.getInfo(left.getTL() - 1));
                        node = left;
                        pos = left.getTL() + 1;
                    } else {
                        node.setInfo(pos, right.getInfo(0));
                        node = right;
                        pos = 0;
                    }
                }
                node.arrangeDelete(pos);
                node.setTL(node.getTL() - 1);
                if (node.getTL() < this.m && node != this.root) {
                    owner = this.getOwner(node, node.getInfo(0));
                    pos = owner.getAlloc(node.getInfo(0));
                    if (!this.spread(node, owner, pos))
                        this.merge(node, owner, pos);
                }
                return true;
            }
            return false;
        }
    }
}
