package com.phil.algorithm._06linkedlist;

import java.util.Objects;

/**
 * 尾插法双端队列LRU实现
 * 1. 每次对获取或者修改的node移动到队尾
 * 2. 当容量装满的时候,移除队首的node
 *
 * @author mingwei.xia
 * @date 2019-08-06 09:18
 */
public class LRUDequeLinkedList<E> {

    private Node<E> head;
    private Node<E> last;
    private int capacity;
    private int size;

    public LRUDequeLinkedList() {
        this(10);
    }

    public LRUDequeLinkedList(int capacity) {
        this.head = null;
        this.last = null;
        this.size = 0;
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUDequeLinkedList<String> list = new LRUDequeLinkedList<>(4);
        list.add("abc");
        list.add("def");
        list.add("ghi");
        list.add("xxx");
        System.out.println(list);

        list.get(2);
        System.out.println(list);
    }

    public int size() {
        return this.size;
    }

    public void add(E e) {
        if (head == null) {
            head = new Node<>(null, e, null);
            last = head;
        } else {
            last.next = new Node<>(last, e, null);
            last = last.next;
        }
        size++;

        removeFrontIfNecessary(size);
    }

    public void addLast(E e) {
        add(e);
    }

    public E remove(int index) {
        checkIndex(index);

        Node<E> node = getNode(index);
        if (node == head) {
            head = head.next;
        } else if (node == last) {
            last = last.prev;
        } else {
            node.prev.next = node.next;
            node = null;
        }

        size--;
        return node.item;
    }

    public boolean remove(E e) {
        Node<E> cur = head;
        while (cur != null) {
            if (Objects.equals(cur.item, e)) {
                if (size == 1) {
                    head = null;
                    last = null;
                } else {
                    if (cur == last) {
                        cur.prev.next = null;
                        last = cur.prev;
                    } else if (cur == head){
                        head = head.next;
                    }else {
                        cur.prev.next = cur.next;
                    }
                    cur = null;
                }
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean set(int index, E value) {
        Node<E> node = getNode(index);
        if (Objects.nonNull(node)) {
            node.item = value;

            // 移动到队尾
            removeToLast(node);

            return true;
        }
        return false;
    }

    public E get(int index) {
        Node<E> node = getNode(index);
        if (node != null) {
            // 移动到队尾
            removeToLast(node);
            return node.item;
        }

        return null;
    }

    public E getFirst() {
        if (head != null) {
            removeToLast(head);
            return head.item;
        }
        return null;
    }

    public E getLast() {
        return last != null ? last.item : null;
    }

    private Node<E> getNode(int index) {
        checkIndex(index);

        if (index < size / 2) {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else {
            Node<E> cur = last;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "LRUDequeLinkedList is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LRUDequeLinkedList: [");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.item).append(", ");
            cur = cur.next;
        }
        String s = sb.toString();
        String result = s.substring(0, s.length() - 2);
        return result + "] size=" + size + ", head=" + head.item + ", last=" + last.item;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
    }

    private void removeFrontIfNecessary(int size) {
        if (size -1 == capacity) {
            remove(0);
        }
    }

    private void removeToLast(Node<E> node) {
        if (node == head) {
            head = node.next;
            node.next.prev = null;
            node.next = null;
            last.next = node;
            node.prev = last;
            last = node;
        } else if (node == last) {

        } else {
            node.prev.next = node.next;
            node.next = null;
            last.next = node;
            last = node;
        }
    }
}
