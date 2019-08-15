package com.phil.algorithm;

/**
 * 数据结构和算法
 *
 * @author mingwei.xia
 * @date 2019-08-03 11:21
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("hello world");

        int[] ints = {2, 1, 4, 5, 3, 0};
//        insertSort(ints);
        bubbleSort(ints);
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }


    public static void insertSort(int[] ints) {

        for (int i = 1; i < ints.length; i++) {
            int value = ints[i];

            int j = i - 1;
            for (; j >= 0; j--) {
                if (ints[j] > value) {
                    ints[j + 1] = ints[j];
                } else {
                    break;
                }
            }

            ints[j + 1] = value;
        }
    }

    public static void bubbleSort(int[] ints) {

        int length = ints.length;

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    int tmp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = tmp;
                }
            }
        }
    }
}
