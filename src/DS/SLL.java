package DS;

public class SLL implements ILinkedList
{
    private Node head, tail;
    private int size;


    class Node
    {
        Object obj;
        Node next;

        Node(Object obj, Node next)
        {
            this.obj = obj;
            this.next = next;
        }
    }


    public SLL()
    {
        size = 0;
        head = null;
        tail = null;
    }

    public SLL(Object element)
    {
        size = 0;
        this.add(element);
    }

    public SLL(Object[] elements)
    {
        size = 0;
        for (Object i : elements)
        {
            this.add(i);
        }
    }


    
    public void add(Object element)
    {
        Node newNode = new Node(element, null);
        if (tail != null) tail.next = newNode;
        tail = newNode;
        head = (head == null) ? newNode : head;
        size++;
    }

    
    public void add(int index, Object element) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Added Index is out of bounds.");
        if (index == size)
        {
            this.add(element);
            return;
        } 
        Node newNode;
        if (index == 0)
        {
            newNode = new Node(element, head);
            head = newNode;
            size++;
            return;
        }
        Node currentNode = head;
        
        while (index-- > 1)
        {//This loop traverses the list index-1 times.
            //Each iteration of the loop, we jump one node ahead in the list.
            //These 2 lines are a bit confusing.
            currentNode = currentNode.next;
        }
        //When the loop finishes, the new node should be inserted between the current node and the next one.
        newNode = new Node(element, currentNode.next);
        currentNode.next = newNode;
        size++;
    }

    public Object get(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size-1) throw new IndexOutOfBoundsException("Accessed index is out of bounds.");
        Node currentNode = head;
        while(index-- > 0)
        {
            currentNode = currentNode.next;
        }

        return currentNode.obj;
    }

    public void set(int index, Object element) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size-1) throw new IndexOutOfBoundsException("Accessed index is out of bounds.");
        Node currentNode = head;
        while (index-- > 0)
        {
            currentNode = currentNode.next;
        }
        currentNode.obj = element;
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size-1) throw new IndexOutOfBoundsException("Removed index is out of bounds.");
        if (index == 0)
        {
            head = head.next;
            size--;
            return;
        }
        Node currentNode = head;
        while (index-- > 1)
        {
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
        size--;
    }

    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public ILinkedList sublist(int from, int to) throws IndexOutOfBoundsException
    {
        if (from > to || from < 0 || to >= size) throw new IndexOutOfBoundsException("Illegal index bounds.");
        SLL result = new SLL();
        Node currentNode = head;
        

        while (from > 0)
        {
            from--;
            to--;
            currentNode = currentNode.next;
        }
        while (to >= 0)
        {
            result.add(currentNode.obj);
            currentNode = currentNode.next;
            to--;
        }

        return result;
    }

    public boolean contains(Object obj)
    {
        Node currentNode = head;
        boolean result = false;
        for (int i = 0; i < size; i++)
        {
            if (currentNode.obj.equals(obj))
            {
                result = true;
                break;
            }
            currentNode = currentNode.next;
        }
        return result;
    }
    
    public boolean isEmpty()
    {
        return (size == 0);
    }

}

