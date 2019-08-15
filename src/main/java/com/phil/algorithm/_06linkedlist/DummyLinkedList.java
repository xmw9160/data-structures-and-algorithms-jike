package com.phil.algorithm._06linkedlist;

import java.util.Objects;

/**
 * 头插法链表实现
 *
 * @author mingwei.xia
 * @date 2019-08-05 17:17
 */
public class DummyLinkedList<T> {

    private Node<T> dummyHead;
    private int size;

    public DummyLinkedList() {
        this.dummyHead = new Node<>(null, null);
        this.size = 0;
    }

    public static void main(String[] args) {
        DummyLinkedList<Integer> list = new DummyLinkedList<>();
//        list.add(4);
//        list.add(3);
//        System.out.println(list);
//
//        System.out.println("index 2: " + list.get(1));
//
//        System.out.println("contains(4): " + list.contains(4));
//
//        list.remove(0);
//        System.out.println("remove(0) " + list);
//
//        list.remove(0);
//        System.out.println("remove(2) " + list);
//
//        list.add(88);
//        System.out.println(list);

        String text = "noon";
        System.out.println(text + " 是否是回文串: " + list.isPalindrome(text));
    }

    public int size() {
        return this.size;
    }

    public void add(T value) {
        dummyHead.next = new Node<>(value, dummyHead.next);
        size++;
    }

    public T get(int index) {
        checkIndex(index);

        return getNode(index).value;
    }

    public boolean contains(T t) {
        Node<T> cur = dummyHead;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(cur.value, t)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void set(int index, T value) {
        getNode(index).value = value;
    }

    public void remove(int index) {
        checkIndex(index);

        Node<T> cur = dummyHead;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                cur.next = cur.next.next;

                size--;
                return;
            }
            cur = cur.next;
        }
    }

    private Node<T> getNode(int index) {
        checkIndex(index);

        Node<T> cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "DummyLinkedList is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DummyLinkedList: [");
        Node<T> cur = dummyHead.next;
        for (int i = 0; i < size; i++) {
            sb.append(cur.value).append(", ");
            cur = cur.next;
        }
        String s = sb.toString();
        String result = s.substring(0, s.length() - 2);
        return result + "] size=" + size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 判断字符串是否是回文串
     * [图] https://blog.csdn.net/xingzhemoluo/article/details/40897053
     */
    public boolean isPalindrome(String text) {
        char[] chars = text.toCharArray();
        Node<Character> head = new Node<>(null, null);
        for (char c : chars) {
            head.next = new Node<>(c, head.next);
            // 尾节点的next为null
//            head = new Node<>(c, head);
        }

        print(head);

        // 对前半部分逆序
        Node<Character> prev = null;
        // 慢指针,每次走一步
        Node<Character> slow = head;
        // 快指针,每次走两步
        Node<Character> fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            //反序
            Node<Character> next = slow.next;

            slow.next = prev;
            prev = slow;

            slow = next;
        }

        print(head);

        return true;
    }

    private void print(Node head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node cur = head.next;
        while (cur != null) {
            sb.append(cur.value).append(" ");
            cur = cur.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    private class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
