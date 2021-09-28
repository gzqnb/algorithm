package com.gzq.algorithm.binary_search;

public class BinarySearch {
    public static int binarySearch(int[] a, int key) {
        //定义初始查找范围,双指针
        int low = 0;
        int high = a.length - 1;

        //排除特殊情况
        if (key < a[low] && key > a[high]) {
            return -1;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;


    }

    //递归实现二分查找
    public static int binarySearch(int[] a, int key, int fromIndex, int toIndex) {
        //基本判断,当起始位置大于结束位置时，直接返回-1
        if (key < a[fromIndex] || key > a[toIndex] || fromIndex > toIndex) {
            return -1;
        }
        //计算中间位置
        int mid = (fromIndex + toIndex) / 2;
        if (key == a[mid]) {
            return mid;
        } else if (key > a[mid]) {
            return binarySearch(a, key, mid + 1, toIndex);
        } else {
            return binarySearch(a, key, fromIndex, mid - 1);
        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6,7};
        int key = 1;
        System.out.println(binarySearch(arr, key));
        System.out.println(binarySearch(arr, key, 0, arr.length - 1));
    }
}
