package com.cloudwalk.shark.interview.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.tree
 * @date:2019/9/4
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class TreeSolution {
    class Solution {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        public void helper(TreeNode node, int level) {
            // start the current level
            if (levels.size() == level) {
                levels.add(new ArrayList<Integer>());
            }

            // fulfil the current level
            levels.get(level).add(node.val);

            // process child nodes for the next level
            if (node.left != null) {
                helper(node.left, level + 1);
            }
            if (node.right != null) {
                helper(node.right, level + 1);
            }
        }

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return levels;
            }
            helper(root, 0);
            return levels;
        }
    }

}
