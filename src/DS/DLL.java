package DS;

public class DLL implements ILinkedList 
{
    private Node first, last;
    private int size;
    
    class Node
    {
        Object data;
        Node next, prev;

        Node(Object s, Node prev, Node next)
        {
            this.data = s;
            this.next = next;
            this.prev = prev;
        }
    }

    public DLL()
    {
        first = last = null;
        size = 0;
    }
    
    public DLL(Object element)
    {
        size = 0;
        this.add(element);
    }

    public DLL(Object[] elements)
    {
        size = 0;
        for (Object i : elements)
        {
            this.add(i);
        }
    }

    public void add(Object element){
        Node newNode = new Node(element, last, null);
        if (size() == 0)
        {
            first = last = newNode;
            size++;
            return;
        }

        last.next = newNode;
        last = newNode;
        size++;
        
    }

    public void add(int index, Object element) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Added index out of bounds");
        if (index == size())
        {
            add(element);
            return;
        }
        Node newNode;
        if (index == 0)
        {
            newNode = new Node(element, null, first);
            first.prev = newNode;
            first = newNode;
            size++;
            return;
        }
        Node currentNode;
        if (index < size/2)
        {
            currentNode = first;
            for (int i = 0; i != index-1; i++) currentNode = currentNode.next;
        }
        else 
        {
            currentNode = last;
            for (int i = size()-1; i != index-1; i--) currentNode = currentNode.prev;
        }
        newNode  = new Node(element, currentNode, currentNode.next);
        (currentNode.next).prev = newNode;
        currentNode.next = newNode;
        size++;
    }

    public Object get(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Accessed index is out of bounds.");
        
        Node currentNode;
        if (index < size()/2)
        {
            currentNode = first;
            for (int i = 0; i != index; i++) currentNode = currentNode.next;
            
        }
        else
        {
            currentNode = last;
            for (int i = size()-1; i != index; i--) currentNode = currentNode.prev;
        }
        return currentNode.data;
    }

    public void set(int index, Object element) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Accessed index is out of bounds.");

        Node currentNode;
        if (index < size()/2)
        {
            currentNode = first;
            for (int i = 0; i != index; i++) currentNode = currentNode.next;
        }
        else
        {
            currentNode = last;
            for (int i = size()-1; i != index; i--) currentNode = currentNode.prev;
        }
        currentNode.data = element;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Removed index is out of bounds.");

        if (index == 0)
        {
            first.next.prev = null;
            first = first.next;
            size--;
            return;
        }
        if (index == size-1)
        {
            last.prev.next = null;
            last = last.prev;
            size--;
            return;
        }

        Node currentNode;

        if (index < size()/2)
        {
            currentNode = first;
            for (int i = 0; i != index; i++) currentNode = currentNode.next;
        }
        else
        {
            currentNode = last;
            for (int i = size()-1; i != index; i--) currentNode = currentNode.prev;
        }
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        size--;
    }

    public int size()
    {
        return size;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) throws IndexOutOfBoundsException
    {
        if (fromIndex > toIndex || fromIndex < 0 || toIndex >= size()) throw new IndexOutOfBoundsException("Illegal index bounds.");

        Node currentNode = first;
        DLL sub = new DLL();
        
        for (int i = 0; i != fromIndex; i++) currentNode = currentNode.next;
        for (int j = fromIndex; j != toIndex+1; j++)
        {
            sub.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return sub;
    }

    public boolean contains(Object o){
        Node temp = first;
        for (int i = 0; i < size(); i++)
        {
            if (temp.data.equals(o))
                return true;
            temp = temp.next;
        }
        return false;
    }
}
