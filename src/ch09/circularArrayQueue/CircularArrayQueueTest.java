package ch09.circularArrayQueue;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 9.1.1 集合接口与实现分离：演示如何扩展集合框架
 */
public class CircularArrayQueueTest {
    public static void main(String[] args) {
        CircularArrayQueue<String> q = new CircularArrayQueue<>(5);
        q.add("Amy");
        q.add("Bob");
        q.add("Carl");
        q.add("Deedee");
        q.add("Emile");
        q.remove();
        q.add("Fifi");
        q.remove();
        for (String s : q) System.out.println(s);   // Carl, Deedee, Emile, Fifi
    }
}

// A first-in, first-out bounded collection.
class CircularArrayQueue<E> extends AbstractQueue<E> {

    private Object[] elements;
    private int head;
    private int tail;
    private int count;
    private int modcount;

    // 构造一个空队列
    public CircularArrayQueue(int capacity) {
        elements = new Object[capacity];
        count = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(E e) {
        assert e != null;
        if (count < elements.length) {
            elements[tail] = e;
            tail = (tail + 1) % elements.length;
            count++;
            modcount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E poll() {
        if (count == 0) return null;
        E r = peek();
        head = (head + 1) % elements.length;
        count--;
        modcount++;
        return r;
    }

    @Override
    public E peek() {
        if (count == 0) return null;
        return (E) elements[head];
    }

    private class QueueIterator implements Iterator<E> {

        private int offset;
        private int modcountAtConstruction;

        public QueueIterator() {
            modcountAtConstruction = modcount;
        }

        @Override
        public boolean hasNext() {
            if (modcount != modcountAtConstruction)
                throw new ConcurrentModificationException();
            return offset < count;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E r = (E) elements[(head + offset) % elements.length];
            offset++;
            return r;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
