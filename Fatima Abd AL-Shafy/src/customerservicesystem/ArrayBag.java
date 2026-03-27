
package customerservicesystem;


public class ArrayBag <T> {
  
     private T items[];
    private int Capacity; //حجم العناصركلي
    private int size;//عدد العناصر

    public ArrayBag(int capacity) {
        this.Capacity = capacity;//لتخزين حجم
        this.items = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void Add(T item) {
        if (Isfull()) {
            System.out.println("sorry bag is full!!");
            return;

        }
        addItem(item);
       
    }

    private void addItem(T item) {
        this.items[size] = item;
        size++;

    }

    public void remove(T item) {
        if (!IsEmppty()) {
            int index = indexof(item);
            if (index != -1) {
                for (int i = index; i < size - 1; i++) {
                    this.items[i] = this.items[i + 1];

                }
                size--;
            } else {
                System.out.println("item not found ..");
            }

        } else {
            System.out.println("bag is empty..");
        }

    }

    public void removeAt(int index) {
        T item = get(index);
        if (item != null) {
            remove(item);
        } else {
            System.out.println("index invalid!!");
        }
    }

    public T get(int index) {
        if (!IsEmppty() && index >= 0 && index < size) {
            for (int i = 0; i < size; i++) {
                if (items[i] == items[index]) {
                    return items[i];
                }
            }
        }
        return null;

    }

    public int indexof(T item) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (this.items[i] == item) {
                index = i;

            }
        }
        return index;
    }

    public boolean Isfull() {
        
        return size == Capacity;
    }

    public boolean IsEmppty() {

        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public boolean Contins(T item) {
        boolean isfound = false;
        if (!IsEmppty()) {
            for (int i = 0; i < size; i++) {
                if (items[i] == items) {
                    isfound = true;
                    break;

                }

            }

        }
        return isfound;
    }

    public void toarray() {
       for (int i = 0; i < size; i++) {
          System.out.println("item :" + items[i]);

       }
   }

}


