package com.gzq.algorithm.binary_tree;

import java.util.ArrayList;

//98
public class ValidateBST {
    //方法1:先序遍历
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        return validator(root, null, null);
    }

    //定义一个辅助校验器，用来传入上下界递归调用
    public boolean validator(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) return true;
        //判断当前节点的值是否在上下界范围内，如果超出直接返回false
        if (lowerBound != null && root.val <= lowerBound)
            return false;
        if (upperBound != null && root.val >= upperBound)
            return false;
        //2.递归判断左右子树
        return validator(root.left, lowerBound, root.val) && validator(root.right, root.val, upperBound);
    }

    //定义一个列表
    ArrayList<Integer> inOrderArray = new ArrayList<>();

    //方法1:中序遍历
    public boolean isValidBST(TreeNode root) {
        //1.中序遍历，得到升序数组
        inOrder(root);
        //2.遍历数组，判断是否升序
        for (int i = 0; i < inOrderArray.size(); i++) {
            if (i > 0 && inOrderArray.get(i) <= inOrderArray.get(i - 1))
                return false;
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        inOrderArray.add(root.val);
        inOrder(root.right);
    }
}
