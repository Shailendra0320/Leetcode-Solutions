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

# 2144. Minimum Cost of Buying Candies With Discount

# Intuition

For every purchase of:

```text
3 candies
```

the cheapest candy among those three becomes free.

To maximize the discount:

```text
Take the most expensive candies first.
```

This ensures that the free candy is as expensive as possible.

---

# Key Observation

After sorting:

```text
Largest → Smallest
```

Every:

```text
3rd candy
```

becomes free.

So:

```text
Pay
Pay
Free

Pay
Pay
Free
```

---

# Greedy Approach

## Step 1

Sort the array.

```text
Ascending Order
```

---

## Step 2

Traverse from the end.

```text
Largest → Smallest
```

---

## Step 3

For every group of 3 candies:

```text
1st → Pay
2nd → Pay
3rd → Free
```

---

# Flow Diagram

```text
Sort Prices

        ↓

Start From Largest

        ↓

Candy Count % 3 == 2 ?

        ↓

YES → Free Candy

NO  → Add Cost

        ↓

Continue
```

---

# Example

Input:

```text
prices = [6,5,7,9,2,2]
```

After Sorting:

```text
[2,2,5,6,7,9]
```

Traverse Backward:

```text
9 → Pay
7 → Pay
6 → Free

5 → Pay
2 → Pay
2 → Free
```

Total:

```text
9 + 7 + 5 + 2 = 23
```

Answer:

```text
23
```

---

# Detailed Dry Run

Input:

```text
prices = [1,2,3]
```

Sorted:

```text
[1,2,3]
```

Process:

```text
3 → Pay
2 → Pay
1 → Free
```

Cost:

```text
5
```

Answer:

```text
5
```

---

# Visualization

```text
Sorted Descending

9   7   6
↑   ↑   ↑
Pay Pay Free


5   2   2
↑   ↑   ↑
Pay Pay Free
```

---

# Complexity Analysis

## Time Complexity

Sorting:

```text
O(n log n)
```

Traversal:

```text
O(n)
```

Total:

```text
O(n log n)
```

---

## Space Complexity

```text
O(1)
```

Ignoring sorting space.

---

# Java Solution

```java
class Solution {

    public int minimumCost(int[] prices) {

        Arrays.sort(prices);

        int totalCost = 0;

        int candyCount = 0;

        for (int position = prices.length - 1; position >= 0; position--) {

            if (candyCount % 3 != 2) {

                totalCost += prices[position];
            }

            candyCount++;
        }

        return totalCost;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int minimumCost(vector<int>& prices) {

        sort(prices.begin(), prices.end());

        int totalCost = 0;

        int candyCount = 0;

        for (int position = prices.size() - 1; position >= 0; position--) {

            if (candyCount % 3 != 2) {

                totalCost += prices[position];
            }

            candyCount++;
        }

        return totalCost;
    }
};
```
