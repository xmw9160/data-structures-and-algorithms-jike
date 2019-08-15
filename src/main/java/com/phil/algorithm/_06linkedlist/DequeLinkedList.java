package com.phil.algorithm._06linkedlist;

import java.util.Objects;

/**
 * 尾插法双端队列
 *
 * @author mingwei.xia
 * @date 2019-08-06 09:18
 */
public class DequeLinkedList<E> {

    private Node<E> head;
    private Node<E> last;
    private int size;

    public DequeLinkedList() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    public static void main(String[] args) {
//        DequeLinkedList<Integer> list = new DequeLinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        System.out.println(list);
//
//        list.remove(0);
//        System.out.println(list);
//
//        list.remove(3);
//        System.out.println(list);
//
//        list.remove(0);
//        System.out.println(list);
//
//        list.remove(0);
//        System.out.println(list);
//
//        list.remove(0);
//        System.out.println(list);
//
//        list.add(1);
//        list.add(2);
//        System.out.println(list);

        DequeLinkedList<String> list = new DequeLinkedList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        list.remove("abc");
        System.out.println(list);

        list.remove("ghi");
        System.out.println(list);

        list.set(0, "xxxxx");
        System.out.println(list);

        System.out.println(list.get(0));
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
    }

    public void addFirst(E e) {
        if (head == null) {
            head = new Node<>(null, e, null);
            last = head;
        } else {
            head = new Node<>(null, e, head);
        }
    }

    public void addLast(E e) {
        add(e);
    }

    public E remove(int index) {
        checkIndex(index);

        if (index < size / 2) {
            int i = 0;
            Node<E> cur = head;
            while (cur != null) {
                if (i == index) {
                    if (cur == head) {
                        head = head.next;
                        head.prev = null;
                    } else {
                        cur.prev.next = cur.next;
                    }
                    size--;
                    return cur.item;
                }
                i++;
                cur = cur.next;
            }
        } else {
            int i = size - 1;
            Node<E> cur = last;
            while (last != null) {
                if (i == index) {
                    if (size == 1) {
                        head = null;
                    } else {
                        if (cur == last) {
                            last.prev.next = null;
                            last = last.prev;
                        } else {
                            cur.prev.next = cur.next;
                        }
                    }
                    size--;
                    return cur.item;
                }
                i--;
                cur = cur.prev;
            }
        }
        return null;
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
                    } else if (cur == head) {
                        head = head.next;
                    } else {
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
            return true;
        }
        return false;
    }

    public E get(int index) {
        Node<E> node = getNode(index);
        return node == null ? null : node.item;
    }

    public E getFirst() {
        return head != null ? head.item : null;
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
            int i = size - 1;
            Node<E> cur = last;
            while (last != null) {
                if (i == index) {
                    return cur;
                }
                i--;
                cur = cur.prev;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "DequeLinkedList is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DequeLinkedList: [");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.item).append(", ");
            cur = cur.next;
        }
        String s = sb.toString();
        String result = s.substring(0, s.length() - 2);
        return result + "] size=" + size + ", head=" + head.item + ", last=" + last.item;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
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
}
