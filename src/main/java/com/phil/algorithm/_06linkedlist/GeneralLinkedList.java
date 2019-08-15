package com.phil.algorithm._06linkedlist;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 头插法链表实现
 *
 * @author mingwei.xia
 * @date 2019-08-05 17:17
 */
public class GeneralLinkedList<T> {

    private Node<T> head;
    private int size;

    public GeneralLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public static void main(String[] args) {
        GeneralLinkedList<Integer> list = new GeneralLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);

        System.out.println(list.get(2));

        System.out.println(list.contains(4));

        list.remove(0);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);


        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(4);
        System.out.println(linkedList);
    }

    public int size() {
        return this.size;
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            head = new Node<>(value, head);
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);

        Node node = getNode(index);
        return node != null ? (T) node.value : null;
    }

    public boolean contains(T t) {
        Node cur = head;
        while (cur != null) {
            if (Objects.equals(cur.value, t)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void set(int index, T value) {
        Node node = getNode(index);
        if (node != null) {
            node.value = value;
        }
    }

    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        int i = 0;
        Node cur = head;
        while (cur != null) {
            if (i == index - 1) {
                cur.next = cur.next.next;
                size--;
                return;
            }
            i++;
            cur = cur.next;
        }
    }

    private Node getNode(int index) {
        int i = 0;
        Node<T> cur = head;
        while (cur != null) {
            if (i == index) {
                return cur;
            }
            i++;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "linkedList is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("linkedList: [");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.value).append(", ");
            cur = cur.next;
        }
        String s = sb.toString();
        String result = s.substring(0, s.length() - 2);
        return result + "] size=" + size;
    }

    private class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException();
        }
    }
}
