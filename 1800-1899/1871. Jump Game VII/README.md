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

# 1871. Jump Game VII

# Intuition

We are given:

- a binary string
- jump range:
  - `minJump`
  - `maxJump`

We can only jump on:

```text
'0'
```

Goal:

- determine whether we can reach the last index

---

# Approaches

This problem can be solved using:

1. Recursion + Memoization
2. Bottom Up DP
3. Sliding Window + DP (Optimal)

---

# Approach 1 — Recursion + Memoization

## Idea

From every index:

- try all jumps from:

```text
[minJump, maxJump]
```

If any jump reaches the last index:

- return true

Memoization avoids recalculating states.

---

# Recursion Flow

```text
Current index

        ↓

Try every jump

        ↓

Reach valid '0' ?

        ↓

YES → recurse
NO  → skip
```

---

# Diagram

```text
s = "011010"

Index:
0 1 2 3 4 5

Possible jumps:
0 → 3
3 → 5

Reached end ✅
```

---

# Approach 2 — Bottom Up DP

## Idea

Build DP from back to front.

If:

- any reachable future position exists
- current position is '0'

then:

- current position becomes reachable

---

# Bottom Up Flow

```text
Start from end

        ↓

Check future jumps

        ↓

Reachable future exists ?

        ↓

YES → mark current reachable
```

---

# Diagram

```text
Index:
0 1 2 3 4 5

DP:
T F F T F T
```

---

# Approach 3 — Sliding Window + DP (Optimal)

## Idea

Instead of checking all previous positions repeatedly:

- maintain a sliding window count

Window:

```text
[j - maxJump , j - minJump]
```

If:

- reachable count > 0
- current character == '0'

then:

- current position is reachable

---

# Sliding Window Diagram

```text
s = "011010"

Reachable:
1 0 0 1 0 1
```

---

# Sliding Window Flow

```text
Expand window

        ↓

Add new reachable positions

        ↓

Remove old positions

        ↓

count > 0 ?

        ↓

YES → reachable
```

---

# Dry Run

Input:

```text
s = "011010"
minJump = 2
maxJump = 3
```

---

## Step 1

```text
t[0] = 1
```

---

## Step 2

At:

```text
j = 3
```

Window contains reachable index:

```text
0
```

So:

```text
t[3] = 1
```

---

## Step 3

At:

```text
j = 5
```

Window contains reachable index:

```text
3
```

So:

```text
t[5] = 1
```

Reached end ✅

---

# Complexity

## Approach 1 — Recursion + Memoization

### Time Complexity

```text
O(n * (maxJump - minJump))
```

### Space Complexity

```text
O(n)
```

---

## Approach 2 — Bottom Up DP

### Time Complexity

```text
O(n * (maxJump - minJump))
```

### Space Complexity

```text
O(n)
```

---

## Approach 3 — Sliding Window + DP

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
//Approach-1 (Recursion + Memoization)
class Solution {

    int n;
    int[] t;

    int solve(int idx, String s, int minJump, int maxJump) {

        if (idx == n - 1) {
            return 1;
        }

        if (t[idx] != -1) {
            return t[idx];
        }

        for (int jump = minJump; jump <= maxJump; jump++) {

            int j = idx + jump;

            if (j >= n) {
                break;
            }

            if (s.charAt(j) == '0') {

                if (solve(j, s, minJump, maxJump) == 1) {
                    return t[idx] = 1;
                }
            }
        }

        return t[idx] = 0;
    }

    public boolean canReach(String s, int minJump, int maxJump) {

        n = s.length();

        t = new int[n];

        Arrays.fill(t, -1);

        return solve(0, s, minJump, maxJump) == 1;
    }
}
```

---

# C++ Solution

```cpp
//Approach-1 (Recursion + Memoization)
class Solution {
public:

    int n;

    int solve(int idx, string& s, int minJump, int maxJump, vector<int>& t) {

        if (idx == n - 1) {
            return true;
        }

        if (t[idx] != -1) {
            return t[idx];
        }

        for (int jump = minJump; jump <= maxJump; jump++) {

            int j = idx + jump;

            if (j >= n) {
                break;
            }

            if (s[j] == '0') {

                if (solve(j, s, minJump, maxJump, t)) {
                    return t[idx] = true;
                }
            }
        }

        return t[idx] = false;
    }

    bool canReach(string s, int minJump, int maxJump) {

        n = s.length();

        vector<int> t(n, -1);

        return solve(0, s, minJump, maxJump, t);
    }
};
```
