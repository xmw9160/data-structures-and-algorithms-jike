package com.phil.algorithm._05array;

import java.util.Objects;

/**
 * 数组实现
 *
 * @author mingwei.xia
 * @date 2019-08-05 15:22
 */
public class GeneralArray<T> {

    private T[] array;
    private int size;

    public GeneralArray() {
        this(10);
    }

    public GeneralArray(int size) {
        this.array = (T[]) new Object[size];
        this.size = 0;
    }

    public static void main(String[] args) {
        GeneralArray<Integer> array = new GeneralArray<>(4);
        for (int i = 0; i < 4; i++) {
            array.add(i);
        }
        System.out.println(array);

        array.remove(1);
        System.out.println(array);
        array.remove(1);
        System.out.println(array);
        array.remove(3);
        System.out.println(array);

        array.add(88);
        array.add(99);
        array.add(100);
        array.add(101);
        array.add(102);
        array.add(103);
        System.out.println(array);

        System.out.println("array contains 103: " + array.contains(103));

        System.out.println(array);
        array.addFirst(8888);
        System.out.println(array);
        array.addLast(9999);
        array.addLast(9999);
        System.out.println(array);

        array.addFirst(10011);
        System.out.println(array);
    }

    public int size() {
        return size;
    }

    public int length() {
        return array.length;
    }

    public void set(int index, T t) {
        checkIndex(index);

        array[index] = t;
    }

    public T remove(int index) {
        checkIndex(index);

        T t = array[index];

//        for (int i = index; i < array.length; i++) {
//            if (i == array.length - 1) {
//                array[i] = null;
//            } else {
//                array[i] = array[i + 1];
//            }
//        }

        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }

        array[size - 1] = null;
        size--;

        // 缩容操作, 数据为数组长度的四分之一时, 将数组长度缩小为原来的二分之一
        if (size == array.length / 4 && array.length / 2 != 0) {
            resize(array.length / 2);
            System.out.println("数组缩容啦: " + this.toString());
        }

        return t;
    }

    public void add(T t) {
        ensureCapacityInternal();

        this.array[size++] = t;
    }

    public void addFirst(T t) {
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = t;
        size++;
    }

    public void addLast(T t) {
        add(t);
    }

    public T get(int index) {
        checkIndex(index);

        return array[index];
    }

    public boolean contains(T t) {
        for (T t1 : array) {
            if (Objects.equals(t1, t)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("array: [");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }

        sb.append("] size=").append(size)
                .append(" length=").append(length());
        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("");
        }
    }

    private void ensureCapacityInternal() {
        if (size > array.length - 1) {
            // throw new IllegalStateException("array has full");

            // 进行1.5倍扩容操作
            int newCapacity = array.length + (array.length >> 1);
            resize(newCapacity);
            System.out.println("数组扩容啦: " + this.toString());
        }
    }

    private void resize(int newCapacity) {
        // 进行1.5倍扩容操作
        T[] newArray = (T[]) new Object[newCapacity];
        // 数组内容复制
        System.arraycopy(array, 0, newArray, 0, size);

        this.array = newArray;
    }
}
