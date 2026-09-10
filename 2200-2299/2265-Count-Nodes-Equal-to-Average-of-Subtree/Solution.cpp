/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right)
 *         : val(x), left(left), right(right) {}
 * };
 */

// Approach-1 (Postorder DFS + Sum and Count)
// T.C : O(n)
// S.C : O(h)

class Solution
{
public:
  int answer = 0;

  int averageOfSubtree(TreeNode *root)
  {
    dfs(root);

    return answer;
  }

  pair<int, int> dfs(TreeNode *node)
  {
    if (node == nullptr)
    {
      return {0, 0};
    }

    auto left = dfs(node->left);
    auto right = dfs(node->right);

    int sum = node->val + left.first + right.first;
    int count = 1 + left.second + right.second;

    if (node->val == sum / count)
    {
      answer++;
    }

    return {sum, count};
  }
};

// Approach-2 (Postorder DFS + Cross Multiplication)
// T.C : O(n)
// S.C : O(h)

class Solution2
{
public:
  int answer = 0;

  int averageOfSubtree(TreeNode *root)
  {
    dfs(root);

    return answer;
  }

  pair<int, int> dfs(TreeNode *node)
  {
    if (node == nullptr)
    {
      return {0, 0};
    }

    auto left = dfs(node->left);
    auto right = dfs(node->right);

    int sum = node->val + left.first + right.first;
    int count = 1 + left.second + right.second;

    if (node->val * count == sum)
    {
      answer++;
    }

    return {sum, count};
  }
};