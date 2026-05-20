# GitHub Repository

⭐ Full LeetCode Solutions Repository:  
https://github.com/Shailendra0320/Leetcode-Solutions

---

# 2657. Find the Prefix Common Array of Two Arrays

# Intuition

For every index `i`, we need to count how many numbers are common in:

```text
A[0...i]
and
B[0...i]
```

The answer for every prefix becomes part of the result array.

Since arrays contain permutations:

- every number appears once
- we can efficiently track frequencies

We can solve this problem using:

1. HashMap Frequency Counting (Optimal)
2. Brute Force Comparison

---

# Approach 1 — HashMap Frequency Counting

## Idea

Traverse both arrays simultaneously.

For every index:

- insert `A[i]`
- insert `B[i]`

Whenever frequency becomes:

```text
2
```

it means:

- the element appeared in both arrays
- so it contributes to prefix common count

Store this count into the answer array.

---

# HashMap Flow

```text
Traverse A and B together

        ↓

Store frequencies in HashMap

        ↓

Frequency becomes 2 ?

        ↓

Increase common count

        ↓

Store count in result array
```

---

# Diagram (HashMap Approach)

```text
A = [1,3,2,4]
B = [3,1,2,4]

Step 1:

A[0] = 1
B[0] = 3

Map:
1 -> 1
3 -> 1

Common = 0
```

↓

```text
Step 2:

A[1] = 3
B[1] = 1

Map:
1 -> 2
3 -> 2

Common = 2
```

↓

```text
Result:
[0,2,3,4]
```

---

# Approach 2 — Brute Force

## Idea

For every prefix:

- compare all elements of `A`
  with all elements of `B`

If values match:

- increase count

Store count into result array.

This approach is straightforward but slower.

---

# Brute Force Flow

```text
For every i

        ↓

Traverse A[0...i]

        ↓

Traverse B[0...i]

        ↓

Compare every pair

        ↓

Count common elements
```

---

# Diagram (Brute Force)

```text
Prefix:

A = [1,3,2]
B = [3,1,2]

Compare:

1 with 3,1,2
3 with 3,1,2
2 with 3,1,2

Common Count = 3
```

---

# Dry Run

Input:

```text
A = [1,3,2,4]
B = [3,1,2,4]
```

### Prefix 0

```text
A = [1]
B = [3]

Common = 0
```

---

### Prefix 1

```text
A = [1,3]
B = [3,1]

Common = 2
```

---

### Prefix 2

```text
A = [1,3,2]
B = [3,1,2]

Common = 3
```

---

### Prefix 3

```text
A = [1,3,2,4]
B = [3,1,2,4]

Common = 4
```

Final Answer:

```text
[0,2,3,4]
```

---

# Complexity

## HashMap Approach

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## Brute Force Approach

### Time Complexity

```text
O(n^3)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution (HashMap)

```java
class Solution {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {

        int n = A.length;

        int[] result = new int[n];

        HashMap<Integer, Integer> mp = new HashMap<>();

        int count = 0;

        for (int i = 0; i < n; i++) {

            mp.put(A[i], mp.getOrDefault(A[i], 0) + 1);

            if (mp.get(A[i]) == 2) {

                count++;
            }

            mp.put(B[i], mp.getOrDefault(B[i], 0) + 1);

            if (mp.get(B[i]) == 2) {

                count++;
            }

            result[i] = count;
        }

        return result;
    }
}
```

---

# Java Solution (Brute Force)

```java
class Solution {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {

        int n = A.length;

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int x = 0; x <= i; x++) {

                for (int y = 0; y <= i; y++) {

                    if (B[y] == A[x]) {

                        count++;

                        break;
                    }
                }
            }

            result[i] = count;
        }

        return result;
    }
}
```

---

# C++ Solution (HashMap)

```cpp
class Solution {
public:

    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {

        int n = A.size();

        vector<int> result(n);

        unordered_map<int, int> mp;

        int count = 0;

        for (int i = 0; i < n; i++) {

            mp[A[i]]++;

            if (mp[A[i]] == 2) {

                count++;
            }

            mp[B[i]]++;

            if (mp[B[i]] == 2) {

                count++;
            }

            result[i] = count;
        }

        return result;
    }
};
```

---

# C++ Solution (Brute Force)

```cpp
class Solution {
public:

    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {

        int n = A.size();

        vector<int> result(n);

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int x = 0; x <= i; x++) {

                for (int y = 0; y <= i; y++) {

                    if (B[y] == A[x]) {

                        count++;

                        break;
                    }
                }
            }

            result[i] = count;
        }

        return result;
    }
};
```
