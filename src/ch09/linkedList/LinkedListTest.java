package ch09.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 9.3.1 LinkedList
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<>();
        a.add("Albert");
        a.add("Bruce");
        a.add("Charlie");

        LinkedList<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // merge the words from b to a
        ListIterator<String> aIter = a.listIterator();
        ListIterator<String> bIter = b.listIterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);  // [Albert, Bob, Bruce, Doug, Charlie, Frances, Gloria]

        // remove every second word from b
        Iterator<String> iter = b.iterator();
        while (iter.hasNext()) {
            iter.next();    // skip one element
            if (iter.hasNext()) {
                iter.next();    // skip next element
                iter.remove();
            }
        }

        System.out.println(b);  // [Bob, Frances]

        // bulk operation: remove all words in b from a
        a.removeAll(b);
        System.out.println(a);  // [Albert, Bruce, Doug, Charlie, Gloria]
    }
}
