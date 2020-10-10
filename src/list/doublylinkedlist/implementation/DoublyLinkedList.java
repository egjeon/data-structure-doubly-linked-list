package list.doublylinkedlist.implementation;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size = 0;


    private class Node {
        private Object data;
        private Node next;
        private Node prev;

        public Node(Object input){
            this.data = input;
            this.next = null;
            this.prev = null;
        }

        public String toString(){
            return String.valueOf(this.data);

        }

    }

    public void addFirst(Object input) {
        Node newNode = new Node(input);
        newNode.next = head;

        if(head != null)
            head.prev = newNode;

        head = newNode;
        size++;

        if(head.next == null)
            tail = head;


    }

    public void addLast(Object input) {
        Node newNode = new Node(input);
        if(size == 0)
            addFirst(input);
         else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }

    public void add(int k, Object input) {
        if(k == 0)
            addFirst(input);
        else{
            Node temp1 = node(k-1);
            Node temp2 = temp1.next;
            Node newNode = new Node(input);
            temp1.next = newNode;
            newNode.next = temp2;
            if(temp2 != null){
                temp2.prev = newNode;
            }
            newNode.prev = temp1;

            size++;

            if(newNode.next == null)
                tail = newNode;
        }
    }

    public Object removeFirst() {
        Node temp = head;
        head = head.next;
        Object returnData = temp.data;
        temp = null;

        if(head != null)
            head.prev = null;

        size--;

        return returnData;
    }

    public Object remove(int idx) {

        if(idx == 0)
            return removeFirst();

        Node temp = node(idx-1);
        Node todoDeleted = temp.next;
        temp.next = temp.next.next;
        Object returnData = todoDeleted.data;

        if(temp.next != null)
         temp.next.prev = temp;

        if(todoDeleted == tail)
            tail = temp;

        todoDeleted = null;
        size--;

        return returnData;


    }
    public int size(){
        return size;
    }
    public Object removeLast() {

        return remove(size - 1);
    }

    public Object get(int idx) {
        Node temp = node(idx);
        return temp.data;

    }

    public int indexOf(Object data) {
        Node temp = head;
        int index = 0;

        while (temp.data != data){
            temp = temp.next;
            index++;
            if(temp == null){
                return -1;
            }
        }
        return index;
    }


    Node node(int index){
        Node x = head;
        if(index < size/2){
            for(int i = 0; i < index; i++){
                x = x.next;
            }
        }else {
            x = tail;
            for(int i = size-1; i > index; i--){
                x = x.prev;
            }
        }

        return x;
    }
    public String toString(){
      if(head == null)
          return "[]";

      Node temp = head;
      String str = "[";

      while(temp.next != null){
          str += temp.data + ", ";
          temp = temp.next;
      }
      str += temp.data;

      return str + "]";
    }


    public ListIterator listIterator() {
        return new ListIterator();

    }
    class ListIterator{
        private Node next;
        private Node lastReturned;
        private int nextIndex;
        ListIterator(){
            next = head;
        }
        public Object next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
        public Object privious(){
            if(next == null)
                lastReturned = next = tail;
            else{
                lastReturned = next = next.prev;
            }
            nextIndex --;

            return lastReturned.data;
        }

        public boolean hasPrivious(){
            return nextIndex > 0;
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public void add(Object input) {
            Node newNode = new Node(input);

            if(lastReturned == null){
                head = newNode;
                newNode.next = next;
            } else {
                lastReturned.next = newNode;
                newNode.prev = lastReturned;

                if(next != null){
                    newNode.next = next;
                    next.prev = newNode;
                } else {
                    tail = newNode;
                }
            }

            lastReturned = newNode;
            nextIndex++;
            size++;

        }

        public void remove() {
            if(nextIndex == 0){
                throw new IllegalStateException();
            }
            Node n = lastReturned.next;
            Node p = lastReturned.prev;
            if(p==null){
                head = n;
                head.prev = null;
                lastReturned = null;
            } else {
                p.next = next;
                lastReturned.prev = null;
            }
            if(n == null){
                tail = p;
                tail.next = null;
            } else {
                n.prev = p;
            }

            if(next == null)
                lastReturned = tail;
              else {
                lastReturned = next.prev;
            }

            size--;
            nextIndex--;

        }
    }
}
