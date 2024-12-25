package comx;

import constant.CsColors;
import enums.Direction;
import enums.TimeDirection;
import models.Movie;
import models.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MovieList {
    Node head, tail = null;
       
    public void add(Movie movie) {          // Bir filmi envantere ekler
        Node newNode = new Node(movie);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (movie.getYear() < ((Movie) head.getItem()).getYear()) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else if (movie.getYear() > ((Movie) tail.getItem()).getYear()) {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            Node current = head;

            while (current.getNext() != null &&
                    ((Movie) current.getNext().getItem()).getYear() <= movie.getYear()) {

                if (((Movie) current.getNext().getItem()).getYear() == movie.getYear() &&
                        movie.getName().compareTo(((Movie) current.getNext().getItem()).getName()) < 0)
                        break;

                current = current.getNext();
            }

            newNode.setPrevious(current);
            newNode.setNext(current.getNext());

            if (current.getNext() != null)
                current.getNext().setPrevious(newNode);

            current.setNext(newNode);
        }
    }

    public void remove(String movieName) {          // Bir filmi envanterden kaldırır
        Node current = head;

        while (current != null) {
            if (((Movie) current.getItem()).getName().equalsIgnoreCase(movieName)) {
                if (current == head) {
                    head = current.getNext();

                    if (head != null)
                        head.setPrevious(null);
                } else if (current == tail) {
                    tail = current.getPrevious();

                    if (tail != null)
                        tail.setNext(null);
                } else {
                    Node prevNode = current.getPrevious();
                    Node nextNode = current.getNext();

                    prevNode.setNext(nextNode);
                    nextNode.setPrevious(prevNode);
                }

                System.out.println(CsColors.GREEN_BRIGHT + "Film envanterden silindi." + CsColors.RESET);
                return;
            }

            current = current.getNext();
        }

        System.out.println("Film bulunamadı.");
    }

    public void print() {               // Envanteri belirli bir sıralamada yazdırır (ileri)
        print(Direction.FORWARD);
    }

    public void print(Direction direction) {        // Envanteri belirli bir sıralamada yazdırır (geri)
        Node current;

        if(direction.equals(Direction.BACKWARD)) {
            current = tail;
            while (current != null) {
                System.out.println(current.getItem().toString());
                current = current.getPrevious();
            }
        } else {
            current = head;
            while (current != null) {
                System.out.println(current.getItem().toString());
                current = current.getNext();
            }
        }
    }

    public void find(String movieName) {            // Belirli bir film adını arar ve eşleşen bir film bulursa bilgilerini yazdırır
        Node current = head;

        while (current != null) {
            if (((Movie) current.getItem()).getName().equalsIgnoreCase(movieName.trim())) {
                System.out.println("\n" + current.getItem().toString());
                return;
            }

            current = current.getNext();
        }

        System.out.println("Film bulunamadı.");
    }

    public void whereYear(int year) {           // Belirli bir yıldan önce olan filmleri yazdırır
        whereYear(year, TimeDirection.BEFORE);
    }

    public void whereYear(int year, TimeDirection order) {      // Belirli bir yıldan önce veya sonra olan filmleri yazdırır

        Node current = head;

        while (current != null) {
            if(order.equals(TimeDirection.AFTER)) {
                if (((Movie) current.getItem()).getYear() > year)
                    System.out.println(current.getItem().toString());
            } else {
                if (((Movie) current.getItem()).getYear() < year)
                    System.out.println(current.getItem().toString());
            }

            current = current.getNext();
        }
    }

    public void save(String filename) {     // Envanterdeki filmleri belirtilen bir dosyaya kaydeder
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Node current = head;

            while (current != null) {
                printWriter.println(((Movie) current.getItem()).toFileString());
                current = current.getNext();
            }

            printWriter.close();
        } catch (IOException e) {
            System.out.println("Dosya kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }

    public boolean hasMovie() {     // En az bir film olup olmadığını kontrol eder
        return head != null;
    }
}
