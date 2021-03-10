package week1;

public class QueueArray {
    private String[] q;
    private int head;
    private int tail;

    public QueueArray() {
        q = new String[1];
        head = 0;
        tail = 0;
    }

    public void enqueue(String el) {
        if (size() == q.length) {
            resize(q.length * 2);
        }
        q[tail] = el;
        tail++;
    }

    private int size() {
        return tail - head;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < size(); i++) {
            copy[i] = q[i + head];
        }
        q = copy;
        head = 0;
        tail = size();
    }

    public String dequeue() {
        if (size() > 0 && size() == q.length / 4) {
            resize(q.length / 2);
        }
        if (head < tail) {
            String el = q[head];
            q[head] = null;
            head++;
            return el;
        } else {
            return null;
        }
    }

    public boolean isEmtpy() {
        return size() == 0;
    }
}
