# Profiles

## GitHub

⭐ GitHub Repository:
https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:
https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:
https://leetcode.com/u/Shailu03/

---

# 2196. Create Binary Tree From Descriptions

# Intuition

Each description contains:

```text
[parent, child, isLeft]
```

Example:

```text
[20,15,1]
```

means:

```text
20
/
15
```

We need to build the complete binary tree and return its root.

---

# Key Observation

Every child appears as:

```text
child
```

in some description.

The root is the only node that:

```text
Never appears as a child
```

Therefore:

1. Create all nodes.
2. Connect parent-child relationships.
3. Store all child nodes.
4. Find the node that never appeared as a child.

That node is the root.

---

# Approach

## Step 1

Create:

```text
HashMap
```

to store:

```text
value → TreeNode
```

---

## Step 2

Create:

```text
HashSet
```

to store all children.

---

## Step 3

Traverse descriptions.

Create parent node if absent.

Create child node if absent.

Connect:

```text
left child
or
right child
```

---

## Step 4

Store child values.

---

## Step 5

Find parent which never appears in child set.

Return that node.

---

# Flow Diagram

```text
Traverse Descriptions

        ↓

Create Parent Node

        ↓

Create Child Node

        ↓

Connect Nodes

        ↓

Store Child Values

        ↓

Find Non-Child Node

        ↓

Return Root
```

---

# Example

Input

```text
descriptions =
[
 [20,15,1],
 [20,17,0],
 [15,10,1]
]
```

---

# Build Tree

```text
        20
       /  \
     15    17
    /
   10
```

Children:

```text
15
17
10
```

Only:

```text
20
```

never appears as child.

Root:

```text
20
```

---

# Dry Run

Description:

```text
[20,15,1]
```

Create:

```text
20
15
```

Connect:

```text
20.left = 15
```

Store:

```text
15
```

as child.

---

Description:

```text
[20,17,0]
```

Connect:

```text
20.right = 17
```

Store:

```text
17
```

as child.

---

Description:

```text
[15,10,1]
```

Connect:

```text
15.left = 10
```

Store:

```text
10
```

as child.

---

Root:

```text
20
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Single traversal.

---

## Space Complexity

```text
O(n)
```

HashMap + HashSet.

---

# Java Solution

```java
class Solution {

    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> nodeMap = new HashMap<>();

        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {

            int parentVal = desc[0];
            int childVal = desc[1];
            int isLeft = desc[2];

            nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
            nodeMap.putIfAbsent(childVal, new TreeNode(childVal));

            children.add(childVal);

            if (isLeft == 1) {
                nodeMap.get(parentVal).left = nodeMap.get(childVal);
            }
            else {
                nodeMap.get(parentVal).right = nodeMap.get(childVal);
            }
        }

        int rootVal = -1;

        for (int[] desc : descriptions) {

            int parentVal = desc[0];

            if (!children.contains(parentVal)) {

                rootVal = parentVal;
                break;
            }
        }

        return nodeMap.get(rootVal);
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {

        unordered_map<int, TreeNode*> nodeMap;

        unordered_set<int> children;

        for (auto& desc : descriptions) {

            int parentVal = desc[0];
            int childVal  = desc[1];
            int isLeft    = desc[2];

            if (!nodeMap.count(parentVal)) {
                nodeMap[parentVal] = new TreeNode(parentVal);
            }

            if (!nodeMap.count(childVal)) {
                nodeMap[childVal] = new TreeNode(childVal);
            }

            children.insert(childVal);

            if (isLeft == 1) {
                nodeMap[parentVal]->left = nodeMap[childVal];
            }
            else {
                nodeMap[parentVal]->right = nodeMap[childVal];
            }
        }

        int rootVal = -1;

        for (auto& desc : descriptions) {

            int parentVal = desc[0];

            if (!children.count(parentVal)) {

                rootVal = parentVal;
                break;
            }
        }

        return nodeMap[rootVal];
    }
};
```
