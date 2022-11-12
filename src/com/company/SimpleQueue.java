package com.company;

import java.util.LinkedList;
import java.util.List;

//     Очередь FIFO (Первый пришёл, первый вышел)
public class SimpleQueue {
    List<Object> queue = new LinkedList<>();

    //    enqueue() - помещает элемент в конец очереди
    public void enqueue(Object obj) {
        queue.add(obj);
    }

    //    dequeue() - возвращает первый элемент из очереди и удаляет его
    public Object dequeue() {
        return queue.remove(0);
    }

    //    first() - возвращает первый элемент из очереди, не удаляя
    public Object first() {
        return queue.get(0);
    }

    @Override
    public String toString() {
        return "SimpleQueue " + queue;
    }
}
