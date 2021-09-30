package com.gzq.algorithm.binary_search;

//74
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        //二分查找
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            //假装把它展开为一维数组
            int mid = (left + right) / 2;
            //计算二维矩阵中对应的行列号，取出对应元素
            int midElement = matrix[mid / n][mid % n];//精髓！！！！！！！！！！！！！
            //判断中间元素与target的大小关系
            if (midElement < target) {
                left = mid + 1;
            } else if (midElement > target) {
                right = mid - 1;
            } else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 6},
                {10, 11, 23, 33},
                {45, 56, 78, 89}
        };
        int target = 3;
        SearchMatrix searchMatrix = new SearchMatrix();
        System.out.println(searchMatrix.searchMatrix(matrix, target));
    }
}
