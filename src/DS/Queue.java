package DS;


public class Queue implements IQueue, ILinkedBased
{
    private SLL Q;

    Queue()
    {
        Q = new SLL();
    }

    public void enqueue(Object element)
    {
        Q.add(element);
    }


    public Object dequeue() throws IndexOutOfBoundsException
    {
        if (Q.size() == 0) throw new IndexOutOfBoundsException("Queue is empty");
        Object element = Q.get(0);
        Q.remove(0);
        return element;
    }

    public boolean isEmpty()
    {
        return Q.size() == 0;
    }

    public int size()
    {
        return Q.size();
    }

}


interface ILinkedBased {}