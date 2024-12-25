package models;

public class Node {
    private Object item;
    private Node previous;
    private Node next;

    public Node(Object item) {         // Node sınıfı için yapıcı metot.
        this.item = item;
    }

    public Object getItem()  {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Boolean hasNext() {      // Bir önceki Node'un olup olmadığını kontrol eder. Bir önceki Node varsa true, aksi halde false.
        return next != null;
    }

    public Boolean hasPrevius() {       
        return previous != null;
    }
}

