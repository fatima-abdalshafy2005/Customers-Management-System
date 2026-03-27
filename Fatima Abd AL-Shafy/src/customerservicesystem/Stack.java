
package customerservicesystem;

 class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

}
public class Stack <T>{
   


    private Node<T> top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T data) {
        Node<T> newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;

    }

    public T pop() {
        if (isempty()) {
            System.out.println("stack is empty .....");
           
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;

    }

    public T peek() {

        if (isempty()) {
            System.out.println("stack is empty .....");
          
        }
       
        return top.data;
        
        
        
    }
public int size() {
        return size;
    }

    public boolean isempty() {

        return top == null;

    }}



