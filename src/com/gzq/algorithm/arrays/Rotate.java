package com.gzq.algorithm.arrays;

//48题
public class Rotate {
    //方法1:先转置再翻转每一行
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        //1.转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //2.翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];//要交换的两个元素下标加起来等于n-1
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    //方法2:分治思想，分成四个子矩阵分别旋转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //遍历四分之一矩阵
        for (int i = 0; i < n / 2 + n % 2; i++) {//奇数+1，偶数不+1
            for (int j = 0; j < n / 2; j++) {
                //对于matrix[i][j],需要找到不同的四个矩阵中对应的另外三个位置和元素
                //定义一个临时数组，保存对应的四个元素
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                //行列转换规律:row + newCol = n - 1,col = newRow
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                //再次遍历要处理的四个位置，将旋转之后的数据填入
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];//第二个矩阵要放第一个矩阵的对应的值，也就是下标1要对应下标0
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] image1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] image2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        Rotate rotate = new Rotate();
        rotate.rotate(image1);
        System.out.println("image1");
        for (int[] line : image1) {
            for (int point : line) {
                System.out.print(point + "\t");
            }
            System.out.println();
        }
    }
}
