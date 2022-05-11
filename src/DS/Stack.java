package DS;

import java.util.EmptyStackException;


public class Stack implements IStack
{
    private SLL content;

    Stack(Object element)
    {
        content = new SLL();
        content.add(element);
    }

    Stack()
    {
        content = new SLL();
    }

    public Object pop() throws EmptyStackException
    {
        if (content.size() == 0) throw new EmptyStackException();
        Object value = content.get(0);
        content.remove(0);
        return value;
    }
    
    
    public Object peek() throws EmptyStackException
    {
        if (content.size() == 0) throw new EmptyStackException();
        return content.get(0);
    }
    
    
    public void push(Object element)
    {
        content.add(0, element);
    }
    
    public boolean isEmpty()
    {
        return (content.size() == 0);
    }
    
    public int size()
    {
        return content.size();
    }
}




interface IStack 
{
  
    /*** Removes the element at the top of stack and returnsthat element.
    * @return top of stack element, or through exception if empty
    */
    
    public Object pop();
    
    /*** Get the element at the top of stack without removing it from stack.
    * @return top of stack element, or through exception if empty
    */
    
    public Object peek();
    
    /*** Pushes an item onto the top of this stack.
    * @param object to insert*
    */
    
    public void push(Object element);
    
    /*** Tests if this stack is empty
    * @return true if stack empty
    */
    public boolean isEmpty();
    
    /*** Get the size of the stack.
    * @return size of stack
    */

    public int size();
}
