# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# 3043. Find the Length of the Longest Common Prefix

# Intuition

We are given two arrays of integers.

We need to find the maximum length of the common prefix between any number from:

- `arr1`
- `arr2`

Example:

```text
arr1 = [123, 456]
arr2 = [12, 789]
```

The number:

```text
123
```

and

```text
12
```

share prefix:

```text
12
```

Length:

```text
2
```

To efficiently compare prefixes:

- Trie (Prefix Tree) is the best data structure.

---

# Approach — Trie

## Step 1 — Insert arr2 Numbers into Trie

Convert every number into a string and insert digit-by-digit into the Trie.

Example:

```text
123

1
 \
  2
   \
    3
```

---

## Step 2 — Search arr1 Numbers

For every number in `arr1`:

- traverse the Trie
- count how many digits match continuously

This gives:

- common prefix length

Track the maximum answer.

---

# Structure / Flow

```text
Insert all arr2 numbers into Trie

        ↓

Traverse every arr1 number

        ↓

Match digits in Trie

        ↓

Count matched digits

        ↓

Update maximum prefix length
```

---

# Diagram

```text
Trie Structure

Insert:

123
129
45

Trie:

(root)
 ├── 1
 │    └── 2
 │         ├── 3
 │         └── 9
 │
 └── 4
      └── 5
```

---

# Dry Run

Input:

```text
arr1 = [123,456]
arr2 = [12,789]
```

Insert:

```text
12
789
```

Trie:

```text
(root)
 ├── 1
 │    └── 2
 │
 └── 7
      └── 8
           └── 9
```

Search:

```text
123
```

Matched:

```text
1 -> 2
```

Prefix Length:

```text
2
```

Answer:

```text
2
```

---

# Complexity

### Time Complexity

```text
O(N * L + M * L)
```

Where:

- `N` = size of arr1
- `M` = size of arr2
- `L` = average digit length

---

### Space Complexity

```text
O(M * L)
```

Used for Trie storage.

---

# Java Solution

```java
class Solution {

    private static Node root;

    Solution() {

        root = new Node();
    }

    class Node {

        Node[] children = new Node[10];

        boolean isEnd = false;

        Node() {}

        boolean containsKey(char ch) {

            return (children[ch - '0'] != null);
        }

        Node getNode(char ch) {

            return children[ch - '0'];
        }

        void putNode(char ch, Node node) {

            children[ch - '0'] = node;
        }

        void setEnd() {

            isEnd = true;
        }

        boolean isLast() {

            return isEnd;
        }

        void insert(String word) {

            Node node = root;

            for (int i = 0; i < word.length(); i++) {

                char ch = word.charAt(i);

                if (!node.containsKey(ch)) {

                    node.putNode(ch, new Node());
                }

                node = node.getNode(ch);
            }

            node.setEnd();
        }

        int findCommonPrefixLength(String word) {

            Node node = root;

            int count = 0;

            for (int i = 0; i < word.length(); i++) {

                char ch = word.charAt(i);

                if (node.containsKey(ch)) {

                    node = node.getNode(ch);

                    count++;
                }
                else {

                    break;
                }
            }

            return count;
        }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        root = new Node();

        Node trie = new Node();

        int maxLength = 0;

        for (int num : arr2) {

            trie.insert(String.valueOf(num));
        }

        for (int num : arr1) {

            int length = trie.findCommonPrefixLength(String.valueOf(num));

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
```

---

# C++ Solution

```cpp
class Node {
public:

    Node* children[10];
    bool isEnd;

    Node() {

        isEnd = false;

        for (int i = 0; i < 10; i++) {

            children[i] = NULL;
        }
    }

    bool containsKey(char ch) {

        return children[ch - '0'] != NULL;
    }

    Node* getNode(char ch) {

        return children[ch - '0'];
    }

    void putNode(char ch, Node* node) {

        children[ch - '0'] = node;
    }

    void setEnd() {

        isEnd = true;
    }
};

class Solution {

    Node* root;

public:

    Solution() {

        root = new Node();
    }

    void insert(string word) {

        Node* node = root;

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (!node->containsKey(ch)) {

                node->putNode(ch, new Node());
            }

            node = node->getNode(ch);
        }

        node->setEnd();
    }

    int findCommonPrefixLength(string word) {

        Node* node = root;

        int count = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (node->containsKey(ch)) {

                node = node->getNode(ch);

                count++;
            }
            else {

                break;
            }
        }

        return count;
    }

    int longestCommonPrefix(vector<int>& arr1, vector<int>& arr2) {

        int maxLength = 0;

        for (int num : arr2) {

            insert(to_string(num));
        }

        for (int num : arr1) {

            int length = findCommonPrefixLength(to_string(num));

            maxLength = max(maxLength, length);
        }

        return maxLength;
    }
};
```
