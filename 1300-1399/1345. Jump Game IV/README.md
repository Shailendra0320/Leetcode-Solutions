# 1345. Jump Game IV

# Intuition

From index `i`, we can jump to:

1. `i + 1`
2. `i - 1`
3. Any index `j` where:

```text
arr[i] == arr[j]
```

We need to find the minimum number of jumps required to reach the last index.

This problem behaves like a graph:

- every index = node
- every jump = edge

Since every jump costs:

```text
1 step
```

Breadth First Search (BFS) guarantees the shortest path.

---

# Approach

## Step 1 — Store Same Value Indices

Use:

```text
HashMap<Integer, List<Integer>>
```

to group all indices having the same value.

Example:

```text
arr = [7,6,9,6,9,6,9,7]

Map:

7 -> [0,7]
6 -> [1,3,5]
9 -> [2,4,6]
```

This allows direct jumps between equal values.

---

## Step 2 — BFS Traversal

Start BFS from:

```text
index = 0
```

At every index:

- visit:
  - `idx + 1`
  - `idx - 1`
  - all same-value indices

Use:

```text
visited[]
```

to avoid revisiting nodes.

---

## Step 3 — Optimization

After processing all indices for a value:

```text
groups.get(arr[idx]).clear();
```

This prevents repeated traversals and avoids TLE.

---

# Structure / Flow

```text
Start BFS from index 0

        ↓

Visit:
- left index
- right index
- same value indices

        ↓

Mark visited nodes

        ↓

Push valid nodes into queue

        ↓

Reach last index

        ↓

Return minimum steps
```

---

# Dry Run

Input:

```text
arr = [100,-23,-23,404,100,23,23,23,3,404]
```

Start:

```text
index = 0
steps = 0
```

Possible jumps:

- index `1`
- index `4` (same value)

From index `4`:

- index `3`

From index `3`:

- index `9`

Reached last index.

Answer:

```text
3
```

---

# Diagram

```text
Array Indices

0 ---> 1 ---> 2
|               |
|               |
v               v
4 ------------> 9

arr[0] = arr[4]

Same values create direct edges

BFS explores level by level
```

---

# BFS Visualization

```text
Queue:

[0]

↓

Visit 0

Queue:
[1,4]

↓

Visit 1

Queue:
[4,2]

↓

Visit 4

Queue:
[2,3]

↓

Visit 3

Queue:
[9]

↓

Reached last index
```

---

# Complexity

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution

```java
class Solution {

    public int minJumps(int[] nums) {

        int len = nums.length;

        HashMap<Integer, List<Integer>> groups = new HashMap<>();

        for (int pos = 0; pos < len; pos++) {

            if (!groups.containsKey(nums[pos])) {

                groups.put(nums[pos], new ArrayList<>());
            }

            groups.get(nums[pos]).add(pos);
        }

        boolean[] seen = new boolean[len];

        Queue<State> bfs = new LinkedList<>();

        bfs.add(new State(nums[0], 0, 0));

        seen[0] = true;

        while (!bfs.isEmpty()) {

            State cur = bfs.poll();

            int value = cur.value;
            int steps = cur.steps;
            int idx = cur.idx;

            if (idx + 1 < len) {

                if (!seen[idx + 1]) {

                    bfs.add(new State(nums[idx] + 1, steps + 1, idx + 1));
                }

                seen[idx + 1] = true;
            }

            if (idx - 1 >= 0) {

                if (!seen[idx - 1]) {

                    bfs.add(new State(nums[idx] - 1, steps + 1, idx - 1));
                }

                seen[idx - 1] = true;
            }

            for (int next : groups.get(nums[idx])) {

                if (next != idx && seen[next] == false) {

                    bfs.add(new State(nums[next], steps + 1, next));
                }

                seen[next] = true;
            }

            groups.get(nums[idx]).clear();

            if (idx == len - 1) {

                return steps;
            }
        }

        return 0;
    }
}

class State {

    int value;
    int steps;
    int idx;

    State(int value, int steps, int idx) {

        this.value = value;
        this.steps = steps;
        this.idx = idx;
    }
}
```

---

# C++ Solution

```cpp
class State {
public:

    int value;
    int steps;
    int idx;

    State(int value, int steps, int idx) {

        this->value = value;
        this->steps = steps;
        this->idx = idx;
    }
};

class Solution {
public:

    int minJumps(vector<int>& nums) {

        int len = nums.size();

        unordered_map<int, vector<int>> groups;

        for (int pos = 0; pos < len; pos++) {

            groups[nums[pos]].push_back(pos);
        }

        vector<bool> seen(len, false);

        queue<State> bfs;

        bfs.push(State(nums[0], 0, 0));

        seen[0] = true;

        while (!bfs.empty()) {

            State cur = bfs.front();

            bfs.pop();

            int value = cur.value;
            int steps = cur.steps;
            int idx = cur.idx;

            if (idx + 1 < len) {

                if (!seen[idx + 1]) {

                    bfs.push(State(nums[idx] + 1, steps + 1, idx + 1));
                }

                seen[idx + 1] = true;
            }

            if (idx - 1 >= 0) {

                if (!seen[idx - 1]) {

                    bfs.push(State(nums[idx] - 1, steps + 1, idx - 1));
                }

                seen[idx - 1] = true;
            }

            for (int next : groups[nums[idx]]) {

                if (next != idx && seen[next] == false) {

                    bfs.push(State(nums[next], steps + 1, next));
                }

                seen[next] = true;
            }

            groups[nums[idx]].clear();

            if (idx == len - 1) {

                return steps;
            }
        }

        return 0;
    }
};
```
