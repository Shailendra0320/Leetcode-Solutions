# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# 1674. Minimum Moves to Make Array Complementary

## Intuition

For every pair:

```text
nums[i] + nums[n - 1 - i]
```

we want all pair sums to become equal using the minimum number of moves.

For a pair:

- `0 moves` → if current sum already equals target
- `1 move` → if target can be achieved by changing one element
- `2 moves` → otherwise

Instead of checking every pair for every target sum directly, we use a prefix/difference-array style optimization.

We track:

- where 1 move becomes possible
- where 2 moves are required
- where 0 move exists

Then we sweep all possible sums and calculate minimum operations efficiently.

---

# Approach

## Key Observation

For a pair `(a, b)`:

### Current Sum

```text
a + b
```

### One Move Range

```text
[min(a, b) + 1, max(a, b) + limit]
```

Inside this range:

- only 1 move is needed

Outside:

- 2 moves are needed

Exactly at:

```text
a + b
```

0 moves are needed.

---

# Steps

1. Traverse all pairs
2. Store:
   - exact target sums
   - lower bounds
   - upper bounds
3. Sweep all possible sums
4. Dynamically update current cost
5. Track minimum answer

---

# Dry Run

Input:

```text
nums = [1,2,4,3]
limit = 4
```

Pairs:

```text
(1,3) → sum = 4
(2,4) → sum = 6
```

Possible target sums:

```text
2 → 8
```

We calculate:

- 0 move zones
- 1 move ranges
- 2 move ranges

Then compute minimum operations.

Answer:

```text
1
```

---

# Diagram

```text
Pair = (a, b)

Current Sum:
a + b

One Move Range:
[min(a,b)+1  →  max(a,b)+limit]

Outside Range:
2 moves required

Exact Sum:
0 moves required
```

---

# Complexity

### Time Complexity

```text
O(n + limit)
```

### Space Complexity

```text
O(limit)
```

---

# Java Solution

```java
class Solution {

    public int minMoves(int[] nums, int limit) {

        int len = limit + limit + 2;

        int[] lower = new int[len];
        int[] upper = new int[len];
        int[] target = new int[len];

        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {

            int sum = nums[i] + nums[n - 1 - i];

            int min = Math.min(nums[i], nums[n - 1 - i]) + 1;

            int max = Math.max(nums[i], nums[n - 1 - i]) + limit;

            if (sum < len) {
                target[sum] += 1;
            }

            if (min < len) {
                lower[min] += 1;
            }

            if (max < len) {
                upper[max] += 1;
            }
        }

        int minCost = n;
        int cost = n;

        for (int i = 2; i < len; i++) {

            cost = cost
                 - lower[i]
                 - target[i]
                 + target[i - 1]
                 + upper[i - 1];

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int minMoves(vector<int>& nums, int limit) {

        int len = limit + limit + 2;

        vector<int> lower(len, 0);
        vector<int> upper(len, 0);
        vector<int> target(len, 0);

        int n = nums.size();

        for (int i = 0; i < n / 2; i++) {

            int sum = nums[i] + nums[n - 1 - i];

            int mn = min(nums[i], nums[n - 1 - i]) + 1;

            int mx = max(nums[i], nums[n - 1 - i]) + limit;

            if (sum < len) {
                target[sum] += 1;
            }

            if (mn < len) {
                lower[mn] += 1;
            }

            if (mx < len) {
                upper[mx] += 1;
            }
        }

        int minCost = nums.size();
        int cost = nums.size();

        for (int i = 2; i < len; i++) {

            cost = cost
                 - lower[i]
                 - target[i]
                 + target[i - 1]
                 + upper[i - 1];

            minCost = min(minCost, cost);
        }

        return minCost;
    }
};
```
