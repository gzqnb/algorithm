package com.gzq.algorithm.sort;

import java.util.Random;

public class KthLargestElement {
    //基于快速排序
    public int findKthLargest1(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    //基于堆排序的选择
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        //保存堆的大小，初始就是n
        int heapSize = n;
        //1.构建大顶堆
        buildMaxHeap(nums, heapSize);
        //2.执行k-1次删除堆顶元素操作
        for (int i = n - 1; i > n - k; i--) {
            //将堆顶元素交换到当前堆的末尾
            QuickSort.swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        //3.返回当前堆顶元素
        return nums[0];
    }

    private void maxHeapify(int[] nums, int top, int heapSize) {
        //定义左右子节点
        int left = top * 2 + 1;
        int right = top * 2 + 2;
        //保存当前元素最大索引的位置
        int largest = top;
        //比较左右子节点，记录最大索引位置
        if (right < heapSize && nums[right] > nums[largest])
            largest = right;
        if (left < heapSize && nums[left] > nums[largest])
            largest = left;
        //将最大元素换到堆顶
        if (largest != top) {
            QuickSort.swap(nums, top, largest);
            //递归调用处理继续下沉
            maxHeapify(nums, largest, heapSize);
        }
    }

    //定义一个调整成大顶堆的方法
    public void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    //实现快速选择方法
    public int quickSelect(int[] nums, int start, int end, int index) {
        //找到pivot的位置返回
        int position = randomPartition(nums, start, end);
        //判断当前pivot位置是否为index
        if (position == index) {
            return nums[position];
        } else
            return position > index ? quickSelect(nums, start, position - 1, index) : quickSelect(nums, position + 1, end, index);
    }

    private int randomPartition(int[] nums, int start, int end) {
        Random random = new Random();
        int randIndex = start + random.nextInt(end + start + 1);//随机生成pivot的位置
        QuickSort.swap(nums, start, randIndex);
        return QuickSort.partition(nums, start, end);
    }
    //实现一个随机分区的方法


    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 1, 4, 5};
        KthLargestElement kthLargestElement = new KthLargestElement();
        int kthLargest = kthLargestElement.findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }
}
