package DS;

public class PQueue<V> implements IPQueue<V>
{
    class Pair
    {
        private V value;
        private int key;
        Pair(V value, int key)
        {
            this.value = value;
            this.key = key;
        }
        V value()
        {
            return value;
        }
        int key()
        {
            return key;
        }
    }

    SLL Q;

    PQueue()
    {
        Q = new SLL();
    }

    public void insert(V value, int key)
    {
        for (int i=0; i<Q.size(); i++)
        {
            @SuppressWarnings("unchecked") Pair x = (Pair) Q.get(i);
            if (x.key > key)
            {
                Q.add(i, new Pair(value, key));
                return;
            }
        }
        Q.add(new Pair(value, key));
    }

    public V min()
    {
        @SuppressWarnings("unchecked") Pair x = (Pair) Q.get(0);
        return x.value();
    }

    public V remove_min()
    {
        @SuppressWarnings("unchecked") Pair x = (Pair) Q.get(0);
        Q.remove(0);
        return x.value();
    }

    public int size()
    {
        return Q.size();
    }

    public boolean isEmpty()
    {
        return Q.size() == 0;
    }
}



interface IPQueue<V>
{
    /**
     * Adds a new value to the priority queue with a key.
     * @param value the added value.
     * @param key The key assigned to the value.
     */
    void insert(V value, int key);

    /**
     * Returns the value with the lowest key without removing it.
     * @return the value with the lowest key.
     */
    V min();

    /**
     * Removes the value with the lowest key and returns it.
     * @return the value with the lowest key.
     */
    V remove_min();

    /**
     * 
     * @return the size of the Priority queue.
     */
    int size();

    /**
     * 
     * @return true if the queue is empty and false otherwise.
     */
    boolean isEmpty();
}