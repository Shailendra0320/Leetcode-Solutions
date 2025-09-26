129. Sum Root to Leaf Numbers

Description
You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:

Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
Solutions
Solution 1: DFS
We can design a function
d
f
s
(
r
o
o
t
,
s
)
, which represents the sum of all path numbers from the current node
r
o
o
t
to the leaf nodes, given that the current path number is
s
. The answer is
d
f
s
(
r
o
o
t
,
0
)
.

The calculation of the function
d
f
s
(
r
o
o
t
,
s
)
is as follows:

If the current node
r
o
o
t
is null, return
0
.
Otherwise, add the value of the current node to
s
, i.e.,
s
=
s
×
10

- r
  o
  o
  t
  .
  v
  a
  l
  .
  If the current node is a leaf node, return
  s
  .
  Otherwise, return
  d
  f
  s
  (
  r
  o
  o
  t
  .
  l
  e
  f
  t
  ,
  s
  )
- d
  f
  s
  (
  r
  o
  o
  t
  .
  r
  i
  g
  h
  t
  ,
  s
  )
  .
  The time complexity is
  O
  (
  n
  )
  , and the space complexity is
  O
  (
  log
  ⁡
  n
  )
  . Here,
  n
  is the number of nodes in the binary tree.

Java
/\*\*

- Definition for a binary tree node.
- public class TreeNode {
-     int val;
-     TreeNode left;
-     TreeNode right;
-     TreeNode() {}
-     TreeNode(int val) { this.val = val; }
-     TreeNode(int val, TreeNode left, TreeNode right) {
-         this.val = val;
-         this.left = left;
-         this.right = right;
-     }
- }
  \*/
  class Solution {
  public int sumNumbers(TreeNode root) {
  return dfs(root, 0);
  }

      private int dfs(TreeNode root, int s) {
          if (root == null) {
              return 0;
          }
          s = s * 10 + root.val;
          if (root.left == null && root.right == null) {
              return s;
          }
          return dfs(root.left, s) + dfs(root.right, s);
      }

  }
