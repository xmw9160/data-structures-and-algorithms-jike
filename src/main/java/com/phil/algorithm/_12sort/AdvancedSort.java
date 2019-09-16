package com.phil.algorithm._12sort;

import java.util.Arrays;

/**
 * 归并排序, 快速排序.
 *
 * @author mingwei.xia
 * @date 2019-08-08 16:18
 */
public class AdvancedSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 4, 1, 9, 7, 3};
        System.out.println(Arrays.toString(arr));
        System.out.println("-------------");
        mergeSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序(O(nLogN))
     */
    public static void mergeSort(int[] arr, int len) {
        doMergeSort(arr, 0, len - 1);
    }

    /**
     * 递归调用函数
     */
    private static void doMergeSort(int[] arr, int p, int r) {
        // 递归终止条件
        if (p >= r) {
            return;
        }

        // 取p到r之间的中间位置q, 防止（p+r）的和超过int类型最大值
        int q = p + (r - p) / 2;
        // 分治递归
        doMergeSort(arr, p, q);
        doMergeSort(arr, q + 1, r);

        // 将arr[p, q]和arr[q+1, r]合并为a[p, r]
        merge(arr, p, q, r);
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;   // 临时数组的小标

        int[] temp = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        int start = i;
        int end = q;

        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            temp[k++] = arr[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            arr[p+i] = temp[i];
        }
    }

    /**
     * 快速排序(O(nLogN))
     */
    public static void quickSort(int[] arr, int len) {

    }

    // 快速排序递归函数，p,r为下标
    private static void doQuickSort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        // 获取分区点
//        int q = partition()
        int q = 0;
        doQuickSort(arr, p, q - 1);
        doQuickSort(arr, q + 1, r);
    }
}
