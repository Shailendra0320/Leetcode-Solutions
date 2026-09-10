# 2265. Count Nodes Equal to Average of Subtree

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# Problem Statement

Given the root of a binary tree, count the number of nodes whose value is equal to the average of all values in the subtree rooted at that node.

The average is rounded down to the nearest integer.

For a node:

```text
subtreeSum   = sum of all values in its subtree
subtreeCount = number of nodes in its subtree

average = subtreeSum / subtreeCount
```

If:

```text
node.val == average
```

then this node is counted.

Return the total count.

---

# What Is the Question Really Asking?

For every node, we need information about its complete subtree:

```text
             node
            /    \
          left   right
```

To calculate the average, we only need two things:

```text
1. Subtree Sum
2. Subtree Node Count
```

So the real question becomes:

> Can we calculate `(sum, count)` for each subtree once and pass that information to its parent?

Yes.

That naturally leads to **postorder DFS**.

---

# Key Observation

For a node:

```text
       X
      / \
     L   R
```

suppose:

```text
L returns (leftSum, leftCount)
R returns (rightSum, rightCount)
```

Then:

```text
sum = X.val + leftSum + rightSum
```

and:

```text
count = 1 + leftCount + rightCount
```

Therefore:

```text
average = sum / count
```

and we check:

```text
X.val == average
```

This is a classic **bottom-up Tree DP** pattern.

---

# Why Postorder Traversal?

Postorder traversal is:

```text
LEFT -> RIGHT -> ROOT
```

We need the children solved before the current node.

Example:

```text
          4
        /   \
       2     6
      / \     \
     1   3     8
```

Postorder:

```text
1 -> 3 -> 2 -> 8 -> 6 -> 4
```

When processing `2`, we already know the results for `1` and `3`.

When processing `4`, we already know the results for `2` and `6`.

---

# Tree DP Diagram

```text
                       ROOT
                         |
                         v
                       DFS
                         |
              +----------+----------+
              |                     |
              v                     v
          DFS(left)             DFS(right)
              |                     |
              v                     v
       (leftSum,leftCount)  (rightSum,rightCount)
              \                     /
               \                   /
                +--------+---------+
                         |
                         v
             sum = node.val
                 + leftSum
                 + rightSum
                         |
                         v
             count = 1
                  + leftCount
                  + rightCount
                         |
                         v
              average = sum / count
                         |
                         v
             node.val == average ?
                    /         \
                  YES          NO
                   |            |
                answer++      nothing
                         |
                         v
                  return (sum,count)
```

---

# Base Case

If:

```text
node == null
```

there are:

```text
0 nodes
sum = 0
```

So return:

```text
(0, 0)
```

For a leaf node:

```text
left  = (0,0)
right = (0,0)
```

Therefore:

```text
sum = node.val
count = 1
average = node.val
```

So every leaf is automatically counted.

---

# Example 1

Consider:

```text
        4
       / \
      8   5
     / \   \
    0   1   6
```

We calculate bottom-up.

### Node 0

```text
sum = 0
count = 1
average = 0

0 == 0
```

Count it.

### Node 1

```text
sum = 1
count = 1
average = 1

1 == 1
```

Count it.

### Node 8

```text
sum = 8 + 0 + 1
    = 9

count = 1 + 1 + 1
      = 3

average = 9 / 3
        = 3

8 != 3
```

Do not count.

### Node 6

Leaf:

```text
sum = 6
count = 1
average = 6
```

Count it.

### Node 5

```text
sum = 5 + 6
    = 11

count = 1 + 1
      = 2

average = 11 / 2
        = 5
```

Because integer division rounds down:

```text
5 == 5
```

Count it.

### Node 4

```text
sum = 4 + 9 + 11
    = 24

count = 1 + 3 + 2
      = 6

average = 24 / 6
        = 4
```

Count it.

So the final answer is:

```text
5
```

---

# Detailed Dry-Run Table

| Node | Subtree Sum | Subtree Count | Floor Average | Counted? |
| ---: | ----------: | ------------: | ------------: | :------: |
|    0 |           0 |             1 |             0 |   Yes    |
|    1 |           1 |             1 |             1 |   Yes    |
|    8 |           9 |             3 |             3 |    No    |
|    6 |           6 |             1 |             6 |   Yes    |
|    5 |          11 |             2 |             5 |   Yes    |
|    4 |          24 |             6 |             4 |   Yes    |

Therefore:

```text
Answer = 5
```

---

# Approach 1: Brute Force Recalculation

## Idea

For every node:

1. Traverse the complete subtree.
2. Calculate its sum.
3. Calculate its size.
4. Compute the average.
5. Compare with the node value.

This approach is easy to understand, but it repeats the same work.

---

# Brute-Force Diagram

```text
For every node:

          NODE
         /    \
        /      \
   traverse   traverse
      whole      whole
    subtree    subtree
         \      /
          \    /
        calculate
        sum + count
             |
             v
          average
```

In a skewed tree:

```text
1
 \
  2
   \
    3
     \
      4
       \
        5
```

the subtree is traversed repeatedly:

```text
node 1 -> 5 nodes
node 2 -> 4 nodes
node 3 -> 3 nodes
node 4 -> 2 nodes
node 5 -> 1 node
```

Total:

```text
5 + 4 + 3 + 2 + 1
= O(n^2)
```

---

# Approach 1: Complexity

```text
T.C : O(n^2) worst case
S.C : O(h)
```

This is mainly useful for understanding why the optimized solution is necessary.

---

# Approach 2: Postorder DFS + Return Sum and Count

## Idea

Calculate each subtree only once.

Every DFS call returns:

```text
(sum, count)
```

for its subtree.

Then the parent combines the child results.

---

# Approach 2 Recurrence

For node `x`:

```text
left  = dfs(x.left)
right = dfs(x.right)

sum =
    x.val
    + left.sum
    + right.sum

count =
    1
    + left.count
    + right.count
```

Then:

```text
if sum / count == x.val:
    answer++
```

Finally:

```text
return (sum, count)
```

---

# Approach 2 Flowchart

```text
                    dfs(node)
                        |
                Is node == null?
                   /          \
                 YES           NO
                  |             |
                  v             v
              return       dfs(left)
               (0,0)            |
                                v
                           dfs(right)
                                |
                                v
                       combine child data
                                |
                +---------------+---------------+
                |                               |
                v                               v
             subtreeSum                    subtreeCount
                |                               |
                +---------------+---------------+
                                |
                                v
                         average = sum/count
                                |
                                v
                     node.val == average?
                          /             \
                        YES              NO
                         |                |
                      answer++           skip
                         \                /
                          +------return---+
```

---

# Approach 2: Java

```java
//Approach-2 (Postorder DFS + Sum and Count)
//T.C : O(n)
//S.C : O(h)

class Solution {

    private int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);

        return answer;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        if (node.val == sum / count) {
            answer++;
        }

        return new int[]{sum, count};
    }
}
```

---

# Approach 2: C++

```cpp
//Approach-2 (Postorder DFS + Sum and Count)
//T.C : O(n)
//S.C : O(h)

class Solution {
public:

    int answer = 0;

    int averageOfSubtree(TreeNode* root) {
        dfs(root);

        return answer;
    }

    pair<int, int> dfs(TreeNode* node) {
        if (node == nullptr) {
            return {0, 0};
        }

        auto left = dfs(node->left);
        auto right = dfs(node->right);

        int sum = node->val + left.first + right.first;
        int count = 1 + left.second + right.second;

        if (node->val == sum / count) {
            answer++;
        }

        return {sum, count};
    }
};
```

---

# Why the Optimized Approach Is O(n)

There are `n` nodes.

For every node we perform only constant work:

```text
- get left result
- get right result
- calculate sum
- calculate count
- calculate average
- compare
```

Therefore:

```text
n × O(1)
=
O(n)
```

No subtree is recalculated.

---

# Space Complexity

The recursive DFS uses stack space proportional to tree height.

Let:

```text
h = height of tree
```

Then:

```text
S.C : O(h)
```

For a balanced tree:

```text
h = O(log n)
```

For a completely skewed tree:

```text
h = O(n)
```

So the worst-case recursion space is:

```text
O(n)
```

---

# Why Integer Division Is Used

The problem asks for the average rounded down.

Since the node values are non-negative, normal integer division gives exactly the floor.

For example:

```text
sum = 11
count = 2

11 / 2 = 5
```

So:

```text
average = 5
```

We do not need floating point.

Use:

```java
sum / count
```

directly.

---

# Why Every Leaf Is Counted

For every leaf:

```text
subtreeSum = node.val
subtreeCount = 1
```

Therefore:

```text
subtreeSum / subtreeCount
=
node.val
```

So:

```text
node.val == average
```

is always true.

This is an excellent sanity check while solving or debugging.

---

# Correctness Proof

## Lemma 1

`dfs(node)` returns the correct sum and node count for the subtree rooted at `node`.

### Base Case

If:

```text
node == null
```

then:

```text
sum = 0
count = 0
```

so the returned pair `(0,0)` is correct.

### Inductive Step

Assume the left and right recursive calls return correct results:

```text
(leftSum, leftCount)
(rightSum, rightCount)
```

Then the current subtree contains exactly:

```text
node.val + leftSum + rightSum
```

as its sum, and:

```text
1 + leftCount + rightCount
```

nodes.

Therefore the current returned pair is correct.

---

## Lemma 2

The calculated:

```text
sum / count
```

is exactly the floor average of the current subtree.

This follows because `sum` and `count` are correct by Lemma 1, and integer division on non-negative values gives the floor.

---

## Lemma 3

The algorithm increments `answer` exactly for valid nodes.

At every node it checks:

```text
node.val == sum / count
```

which is precisely the condition from the problem.

Therefore a node is counted if and only if it satisfies the required condition.

---

## Theorem

Every node is visited exactly once.

For each node, the algorithm correctly computes:

```text
subtree sum
subtree count
floor average
```

and counts the node exactly when its value equals that average.

Therefore the algorithm returns the correct total number of nodes.

---

# Edge Cases

## 1. Single Node

```text
    5
```

```text
sum = 5
count = 1
average = 5
```

Answer:

```text
1
```

---

## 2. All Nodes Have the Same Value

```text
       4
      / \
     4   4
```

Every subtree average is `4`.

Therefore all three nodes are counted.

---

## 3. Leaf Nodes

Every leaf is always counted because its subtree contains only itself.

---

## 4. Empty Tree

If the tree is:

```text
null
```

there are no nodes.

Answer:

```text
0
```

---

## 5. Non-Integer Average

Consider a subtree with:

```text
values = [5,6]
```

Then:

```text
sum = 11
count = 2
average = 5
```

because:

```text
floor(11 / 2) = 5
```

So a node with value `5` can be counted.

---

# Common Mistakes

## Mistake 1: Using the Entire Tree Average

The problem asks for the average of the **subtree rooted at each node**, not the entire tree.

---

## Mistake 2: Forgetting the Current Node

Use:

```text
sum = node.val + leftSum + rightSum
```

not just the children's sums.

Similarly:

```text
count = 1 + leftCount + rightCount
```

---

## Mistake 3: Using Floating Point

No need for:

```text
double average
```

Use:

```text
sum / count
```

with integer arithmetic.

---

## Mistake 4: Computing Every Subtree From Scratch

That can become:

```text
O(n^2)
```

on a skewed tree.

---

## Mistake 5: Using Preorder

The parent needs child information first.

Therefore:

```text
postorder
```

is the natural traversal.

---

# Interview Thought Process

When you hear:

> Count nodes equal to the average of their subtree.

Think:

```text
What does average need?
```

Answer:

```text
sum + count
```

Then:

```text
Who can calculate those values?
```

The children.

Then:

```text
When are child values available?
```

After recursive calls.

Therefore:

```text
POSTORDER DFS
```

Then define the return value:

```text
(sum, count)
```

And the complete idea becomes:

```text
DFS children
      ↓
combine sum/count
      ↓
calculate average
      ↓
check current node
      ↓
return sum/count to parent
```

---

# Pattern Recognition

This is a standard:

```text
Tree DP
+
Postorder Traversal
+
Subtree Aggregation
```

Whenever a tree problem asks about:

```text
subtree sum
subtree size
subtree minimum
subtree maximum
subtree height
```

consider a DFS that returns the required information from each child.

The generic pattern is:

```text
child information
        |
        v
combine
        |
        v
current node information
        |
        v
return to parent
```

---

# Mental Model

Imagine every child sends a report to its parent:

```text
LEFT CHILD REPORT:
(sum = X, count = Y)

RIGHT CHILD REPORT:
(sum = A, count = B)
```

The parent adds its own value:

```text
sum =
node.val + X + A

count =
1 + Y + B
```

Then it calculates:

```text
average = sum / count
```

and sends its own report upward.

---

# Full Example Architecture

```text
                    4
                  /   \
                 2     6
                / \     \
               1   3     8

Postorder:

1 -> report (1,1)
3 -> report (3,1)

2 receives:
left  = (1,1)
right = (3,1)

2 reports:
sum   = 2 + 1 + 3 = 6
count = 1 + 1 + 1 = 3

6 -> report (6,1)
8 -> report (8,1)

6 reports:
sum   = 6 + 8 = 14
count = 1 + 1 = 2

4 receives:
left  = (6,3)
right = (14,2)

4 reports:
sum   = 4 + 6 + 14 = 24
count = 1 + 3 + 2 = 6
```

This is exactly how the information flows from leaves to root.

---

# Complexity Comparison

| Approach                |             Time | Space | Recommendation        |
| ----------------------- | ---------------: | ----: | --------------------- |
| Recompute every subtree | O(n²) worst case |  O(h) | ❌ Only for intuition |
| Postorder Tree DP       |             O(n) |  O(h) | ⭐ Best               |

---

# Best Approach

Use:

```text
Postorder DFS
+
return (sum, count)
```

At each node:

```text
sum = node.val + leftSum + rightSum

count = 1 + leftCount + rightCount

average = sum / count
```

Then:

```text
if node.val == average:
    answer++
```

---

# Final Code to Remember

## Java

```java
//Approach-1 (Postorder DFS + Sum and Count)
//T.C : O(n)
//S.C : O(h)

class Solution {

    private int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);

        return answer;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        if (node.val == sum / count) {
            answer++;
        }

        return new int[]{sum, count};
    }
}
```

## C++

```cpp
//Approach-1 (Postorder DFS + Sum and Count)
//T.C : O(n)
//S.C : O(h)

class Solution {
public:

    int answer = 0;

    int averageOfSubtree(TreeNode* root) {
        dfs(root);

        return answer;
    }

    pair<int, int> dfs(TreeNode* node) {
        if (node == nullptr) {
            return {0, 0};
        }

        auto left = dfs(node->left);
        auto right = dfs(node->right);

        int sum = node->val + left.first + right.first;
        int count = 1 + left.second + right.second;

        if (node->val == sum / count) {
            answer++;
        }

        return {sum, count};
    }
};
```

---

# Final Summary

The problem is a perfect example of bottom-up tree processing.

For every node, the only information we need from its children is:

```text
subtree sum
subtree count
```

Using postorder DFS:

```text
LEFT
RIGHT
ROOT
```

we calculate:

```text
sum = node.val + leftSum + rightSum

count = 1 + leftCount + rightCount

average = sum / count
```

If:

```text
node.val == average
```

we increment the answer.

Because every node is processed exactly once:

```text
Time  = O(n)
Space = O(h)
```

where `h` is the tree height.

The main interview insight is:

> **When a tree problem asks for information about every subtree, try to return an aggregated state from each child and combine those states at the parent.**

---

# One-Line Insight

> **Use postorder DFS to return `(subtreeSum, subtreeCount)` for every node, then check whether `node.val == subtreeSum / subtreeCount`.**

---

# Tags

`Binary Tree` `Depth First Search` `Postorder Traversal` `Tree DP` `Subtree` `Recursion` `Math` `Average` `LeetCode` `Easy`
