/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

// Approach-1 (Postorder DFS + Sum and Count)
// T.C : O(n)
// S.C : O(h)

class Solution {

  private int answer = 0;

  public int averageOfSubtree(TreeNode root) {
    dfs(root);

    return answer;
  }

  private int[] dfs(TreeNode node) {
    if (node == null) {
      return new int[] { 0, 0 };
    }

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    int sum = node.val + left[0] + right[0];
    int count = 1 + left[1] + right[1];

    if (node.val == sum / count) {
      answer++;
    }

    return new int[] { sum, count };
  }
}

// Approach-2 (Postorder DFS + Cross Multiplication)
// T.C : O(n)
// S.C : O(h)

class Solution2 {

  private int answer = 0;

  public int averageOfSubtree(TreeNode root) {
    dfs(root);

    return answer;
  }

  private int[] dfs(TreeNode node) {
    if (node == null) {
      return new int[] { 0, 0 };
    }

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    int sum = node.val + left[0] + right[0];
    int count = 1 + left[1] + right[1];

    if (node.val * count == sum) {
      answer++;
    }

    return new int[] { sum, count };
  }
}