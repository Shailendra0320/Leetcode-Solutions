# 2784. Check if Array is Good

# Intuition

A good array should contain:

```text
[1, 2, 3, ..., n-1, n, n]
```

where:

- every number from `1` to `n-1` appears exactly once
- the number `n` appears exactly twice

We can verify this using:

1. Frequency counting
2. Sorting

---

# Approach 1 — Frequency Array

## Idea

1. Let:

```text
n = nums.length - 1
```

2. Count frequency of every element
3. Check:
   - numbers `1 → n-1` appear exactly once
   - number `n` appears exactly twice

If all conditions satisfy:

- return `true`
- otherwise `false`

---

# Dry Run

Input:

```text
nums = [1,3,3,2]
```

Length:

```text
n = 3
```

Frequency:

```text
1 → 1
2 → 1
3 → 2
```

Valid good array.

Answer:

```text
true
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

# Java Solution (Frequency Array)

```java
class Solution {

    public boolean isGood(int[] nums) {

        int n = nums.length - 1;

        int[] freq = new int[n + 2];

        for (int num : nums) {

            if (num > n) {
                return false;
            }

            freq[num]++;
        }

        for (int i = 1; i < n; i++) {

            if (freq[i] != 1) {
                return false;
            }
        }

        return freq[n] == 2;
    }
}
```

---

# C++ Solution (Frequency Array)

```cpp
class Solution {
public:

    bool isGood(vector<int>& nums) {

        int n = nums.size() - 1;

        vector<int> freq(n + 2, 0);

        for (int num : nums) {

            if (num > n) {
                return false;
            }

            freq[num]++;
        }

        for (int i = 1; i < n; i++) {

            if (freq[i] != 1) {
                return false;
            }
        }

        return freq[n] == 2;
    }
};
```

---

# Approach 2 — Sorting

## Idea

1. Sort the array
2. The last two elements should both be `n-1`
3. Remaining elements should be:

```text
1,2,3,...,n-2
```

If yes:

- array is good

---

# Complexity

### Time Complexity

```text
O(n log n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution (Sorting)

```java
class Solution {

    public boolean isGood(int[] nums) {

        if (nums.length <= 1) {
            return false;
        }

        Arrays.sort(nums);

        if (nums[nums.length - 1] != nums.length - 1 ||
            nums[nums.length - 2] != nums.length - 1) {

            return false;
        }

        for (int i = 1; i < nums.length - 1; i++) {

            if (nums[i - 1] != i) {
                return false;
            }
        }

        return true;
    }
}
```

---

# C++ Solution (Sorting)

```cpp
class Solution {
public:

    bool isGood(vector<int>& nums) {

        if (nums.size() <= 1) {
            return false;
        }

        sort(nums.begin(), nums.end());

        int n = nums.size();

        if (nums[n - 1] != n - 1 ||
            nums[n - 2] != n - 1) {

            return false;
        }

        for (int i = 1; i < n - 1; i++) {

            if (nums[i - 1] != i) {
                return false;
            }
        }

        return true;
    }
};
```
