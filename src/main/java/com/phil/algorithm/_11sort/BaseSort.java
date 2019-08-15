package com.phil.algorithm._11sort;

/**
 * 冒泡排序, 插入排序, 选择排序.
 *
 * @author mingwei.xia
 * @date 2019-08-08 10:44
 */
@SuppressWarnings("Duplicates")
public class BaseSort {

    public static void main(String[] args) {
        Integer[] ints = {1, 3, 5, 2, 7, 0};
//        Integer[] ints = {1, 2, 3, 4, 6, 5};

        print("排序前: ", ints);

//        bubbleSort(ints);
        insertionSort(ints);
//        selectionSort(ints);
//        selectionSort2(ints);

        print("排序后: ", ints);
    }

    /**
     * 冒泡排序(O(n^2))
     * 1.原地排序算法
     * 2.稳定的排序算法
     */
    public static void bubbleSort(Integer[] ints) {
        checkArguments(ints);

        int length = ints.length;
        for (int i = 0; i < length; i++) {
            // -1 防止移除
            boolean flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    int tmp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = tmp;
                    flag = true;
                }
            }

            // 如果一次冒泡过程中,没有交换的过程,说明已完全有序,不再需要进行循环
            if (!flag) {
                System.out.println("break... 循环次数: " + (i + 1));
                break;
            }
        }
    }

    /**
     * 插入排序(O(n^2))
     * 1. 原地排序算法
     * 2. 稳定的排序算法
     */
    public static void insertionSort(Integer[] ints) {
        checkArguments(ints);

        for (int i = 1; i < ints.length; i++) {
            int value = ints[i];

            int j = i - 1;
            // 下标i对应的值跟前面的排序的值以此比较,如果前面的值大于i对应的值,
            // 就将j+1和j的值从后前互换(i前面的数据已经有序)
            for (; j >= 0; j--) {
                // 移动数据
                if (ints[j] > value) {
                    ints[j + 1] = ints[j];
                } else {
                    break;
                }
            }

            ints[j + 1] = value;
        }
    }

    /**
     * 选择排序
     * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
     * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾
     * <p>
     * 不稳定的排序算法: 相对于冒泡排序和插入排序，选择排序就稍微逊色了
     */
    public static void selectionSort(Integer[] ints) {
        checkArguments(ints);

        for (int i = 0; i < ints.length; i++) {
            int index = i;
            int minValue = ints[i];
            // i前面为已排序, 从i后面为排序的数据中找到最小值
            for (int j = i; j < ints.length; j++) {
                if (ints[j] < minValue) {
                    minValue = ints[j];
                    index = j;
                }
            }

            // 交换
            if (index != i) {
                int value = ints[i];
                ints[i] = ints[index];
                ints[index] = value;
            }
        }
    }

    public static void selectionSort2(Integer[] arr) {
        int min;
        int max;
        int count = arr.length;
        for (int i = 0; i < count; i++) {
            // 初始化未排序序列中最小数据数组下标
            min = i;
            // 初始化未排序序列中最大数据数组下标
            max = count - 1;
            for (int j = i; j < count; j++) {
                // 在未排序元素中继续寻找最小元素，并保存其下标
                if (arr[min] >= arr[j]) {
                    min = j;
                }
                // 在未排序元素中继续寻找最大元素，并保存其下标
                if (arr[max] <= arr[j]) {
                    max = j;
                }
            }
            // 将未排序列中最小元素放到已排序列头
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
            // 将未排序列中最大元素放到已排序列尾
            if (max != count - 1) {
                if (max == i) {
                    int temp = arr[min];
                    arr[min] = arr[count - 1];
                    arr[count - 1] = temp;
                } else {
                    int temp = arr[max];
                    arr[max] = arr[count - 1];
                    arr[count - 1] = temp;
                }
            }

            count--;
        }
    }

    private static void checkArguments(Integer[] ints) {
        if (ints == null || ints.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void print(String message, Integer[] ints) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : ints) {
            sb.append(integer).append(", ");
        }
        System.out.println(message + sb.toString().substring(0, sb.toString().length() - 2));
    }
}
