package forest.trees.patricia;

public class NList<Class> {     //Node List
    private Class info;
    private NList next;
    
    public NList(Class info, NList next) {
        this.info = info;
        this.next = next;
    }
    
    public NList(Class info) {
        this(info, null);
    }
    
    public NList() {
        this(null, null);
    }
    
    public Class getInfo() {
        return info;
    }

    public void setInfo(Class info) {
        this.info = info;
    }

    public NList getNext() {
        return next;
    }

    public void setNext(NList next) {
        this.next = next;
    }
}
