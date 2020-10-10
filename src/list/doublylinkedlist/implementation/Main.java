package list.doublylinkedlist.implementation;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList numbers = new DoublyLinkedList();
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        //numbers.addFirst(5);
        //numbers.addLast(40);
        //System.out.println(numbers.removeLast());
        //System.out.println(numbers.size());
        //System.out.println(numbers.get(6));
        //System.out.println(numbers.indexOf(100));
        //System.out.println(numbers);

        DoublyLinkedList.ListIterator i = numbers.listIterator();
        while(i.hasNext()){
            int number = (int)i.next();
            if( number == 20){
                i.remove();
            }
        }
         System.out.println(numbers);

    }

}
