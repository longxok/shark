package com.cloudwalk.shark.interview.algorithm.TreeTest;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm.TreeTest
 * @date:2019/7/18
 */
public class TreeNode {
    private String data;

    private TreeNode left;

    private TreeNode right;

    TreeNode(String val, TreeNode left, TreeNode right){
        this.data = val;
        this.left = left;
        this.right = right;
    }
    TreeNode(){

    }
    TreeNode(String val){
        this.data = val;
        this.left = null;
        this.right =null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public static void crateTree(TreeNode treeNode,String[] value){
        if(value.length==0) {
            return;
        }
        else{

        }

    }
}
