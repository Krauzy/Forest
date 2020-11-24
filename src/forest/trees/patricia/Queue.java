package forest.trees.patricia;

public class Queue<Class> {
    private NList init;
    
    public Queue() {
        this.init = null;
    }
    
    public void enqueue(Class info) {
        NList temp = new NList<>(info);
        if(this.isEmpty())
            this.init = temp;
        else {
            NList aux = init;
            while(aux.getNext() != null)
                aux = aux.getNext();
            aux.setNext(temp);
        }
    }
    
    public Class dequeue() {
        if(this.isEmpty())
            return null;
        else {
            NList aux = init;
            this.init = init.getNext();
            return (Class)aux.getInfo();
        }
    }
    
    public boolean isEmpty() {
        return this.init == null;
    }
}
