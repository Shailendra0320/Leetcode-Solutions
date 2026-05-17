# 1306. Jump Game III

# Intuition

From any index `i`, we can jump:

```text
i + arr[i]
```

or

```text
i - arr[i]
```

We need to determine whether we can reach any index having value:

```text
0
```

This problem can be viewed as:

- Graph Traversal
- DFS / BFS

Each index behaves like a node, and jumps create edges between nodes.

To avoid infinite loops, visited indices must be marked.

---

# Approach 1 — DFS

## Idea

Use Depth First Search recursively.

At every index:

1. Check bounds
2. Check if already visited
3. If value becomes `0`
   → return `true`

Otherwise:

- mark current index visited
- recursively explore:
  - left jump
  - right jump

---

# DFS Steps

For index `i`:

```text
Left  = i - arr[i]
Right = i + arr[i]
```

Mark visited using:

```text
arr[i] *= -1
```

Negative value means:

- already visited

---

# Approach 2 — BFS

## Idea

Use Queue for level-order traversal.

1. Push starting index
2. Pop current index
3. If value becomes `0`
   → return `true`
4. Push valid left/right jumps
5. Mark visited

If queue becomes empty:

- return `false`

---

# Dry Run

Input:

```text
arr = [4,2,3,0,3,1,2]
start = 5
```

---

## DFS Traversal

```text
Index 5 → value 1

Possible jumps:
5 + 1 = 6
5 - 1 = 4
```

From index 4:

```text
4 - 3 = 1
4 + 3 = 7 (invalid)
```

From index 1:

```text
1 + 2 = 3
```

At index 3:

```text
arr[3] = 0
```

Answer:

```text
true
```

---

# Diagram

```text
arr = [4,2,3,0,3,1,2]

start = 5

        5
       / \
      6   4
         /
        1
         \
          3

arr[3] = 0

Answer = true
```

---

# Complexity

## DFS Approach

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## BFS Approach

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution (DFS)

```java
class Solution {

    int n;

    private boolean dfs(int[] arr, int i) {

        if (i < 0 || i >= n || arr[i] < 0) {
            return false;
        }

        if (arr[i] == 0)
            return true;

        arr[i] *= -1;

        boolean left  = dfs(arr, i - arr[i]);

        boolean right = dfs(arr, i + arr[i]);

        return left || right;
    }

    public boolean canReach(int[] arr, int start) {

        n = arr.length;

        return dfs(arr, start);
    }
}
```

---

# Java Solution (BFS)

```java
class Solution {

    public boolean canReach(int[] arr, int start) {

        int n = arr.length;

        Queue<Integer> que = new LinkedList<>();

        que.add(start);

        while (!que.isEmpty()) {

            int curr = que.poll();

            if (arr[curr] == 0)
                return true;

            if (arr[curr] < 0)
                continue;

            int left  = curr + arr[curr];

            int right = curr - arr[curr];

            if (left >= 0 && left < n)
                que.add(left);

            if (right >= 0 && right < n)
                que.add(right);

            arr[curr] = -arr[curr];
        }

        return false;
    }
}
```

---

# C++ Solution (DFS)

```cpp
class Solution {

    int n;

    bool dfs(vector<int>& arr, int i) {

        if (i < 0 || i >= n || arr[i] < 0) {
            return false;
        }

        if (arr[i] == 0)
            return true;

        arr[i] *= -1;

        bool left = dfs(arr, i - arr[i]);

        bool right = dfs(arr, i + arr[i]);

        return left || right;
    }

public:

    bool canReach(vector<int>& arr, int start) {

        n = arr.size();

        return dfs(arr, start);
    }
};
```

---

# C++ Solution (BFS)

```cpp
class Solution {
public:

    bool canReach(vector<int>& arr, int start) {

        int n = arr.size();

        queue<int> que;

        que.push(start);

        while (!que.empty()) {

            int curr = que.front();

            que.pop();

            if (arr[curr] == 0)
                return true;

            if (arr[curr] < 0)
                continue;

            int left  = curr + arr[curr];

            int right = curr - arr[curr];

            if (left >= 0 && left < n)
                que.push(left);

            if (right >= 0 && right < n)
                que.push(right);

            arr[curr] = -arr[curr];
        }

        return false;
    }
};
```
