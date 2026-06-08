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

# 2161. Partition Array According to Given Pivot

# Intuition

We need to rearrange the array such that:

```text
All elements < pivot

then

All elements == pivot

then

All elements > pivot
```

while preserving the relative ordering of elements.

---

# Approaches

1. Three Lists / Three Vectors
2. Result Array Traversal

---

# Approach 1 — Three Lists

## Idea

Maintain three separate containers:

```text
smaller
equal
larger
```

Store elements based on their relation with the pivot.

Finally combine:

```text
smaller + equal + larger
```

to obtain the answer.

---

# Approach 1 Visualization

```text
Input

nums  = [9,12,5,10,14,3,10]
pivot = 10


                    Traverse Array
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼

     < pivot           == pivot           > pivot

        │                  │                  │

        ▼                  ▼                  ▼

    [9,5,3]            [10,10]           [12,14]

        └──────────────────┼──────────────────┘
                           │
                           ▼

                 Merge All Three Lists

                           │
                           ▼

        [9,5,3] + [10,10] + [12,14]

                           │
                           ▼

              [9,5,3,10,10,12,14]
```

---

# Detailed Dry Run

### Input

```text
nums  = [9,12,5,10,14,3,10]
pivot = 10
```

---

### Initial State

```text
smaller = []
equal   = []
larger  = []
```

---

### Process 9

```text
9 < 10

smaller = [9]
equal   = []
larger  = []
```

---

### Process 12

```text
12 > 10

smaller = [9]
equal   = []
larger  = [12]
```

---

### Process 5

```text
5 < 10

smaller = [9,5]
equal   = []
larger  = [12]
```

---

### Process 10

```text
10 == 10

smaller = [9,5]
equal   = [10]
larger  = [12]
```

---

### Process 14

```text
14 > 10

smaller = [9,5]
equal   = [10]
larger  = [12,14]
```

---

### Process 3

```text
3 < 10

smaller = [9,5,3]
equal   = [10]
larger  = [12,14]
```

---

### Process 10

```text
10 == 10

smaller = [9,5,3]
equal   = [10,10]
larger  = [12,14]
```

---

### Final Merge

```text
smaller + equal + larger

[9,5,3]
+
[10,10]
+
[12,14]
```

Result:

```text
[9,5,3,10,10,12,14]
```

---

# Memory Visualization

```text
                    Input Array

           [9,12,5,10,14,3,10]

                           │
                           ▼

       ┌─────────────────────────────────┐
       │       Classification Step       │
       └─────────────────────────────────┘

                           │

        ┌──────────────────┼──────────────────┐
        │                  │                  │

        ▼                  ▼                  ▼

   smaller[]           equal[]           larger[]

    [9,5,3]            [10,10]           [12,14]

        │                  │                  │
        └──────────────────┼──────────────────┘
                           │
                           ▼

                Concatenate Lists

                           │
                           ▼

              [9,5,3,10,10,12,14]
```

---

# Approach 2 — Result Array

## Idea

Create a result array.

Perform three passes:

### Pass 1

Insert all elements smaller than pivot.

### Pass 2

Insert all elements equal to pivot.

### Pass 3

Insert all elements greater than pivot.

---

# Approach 2 Flowchart

```text
Input Array

[9,12,5,10,14,3,10]

            │
            ▼

 ┌──────────────────────┐
 │ Pass 1 : < pivot     │
 └──────────────────────┘

            │

            ▼

        [9,5,3]

            │
            ▼

 ┌──────────────────────┐
 │ Pass 2 : == pivot    │
 └──────────────────────┘

            │

            ▼

      [9,5,3,10,10]

            │
            ▼

 ┌──────────────────────┐
 │ Pass 3 : > pivot     │
 └──────────────────────┘

            │

            ▼

   [9,5,3,10,10,12,14]

            │
            ▼

       Return Answer
```

---

# Example

Input

```text
nums = [9,12,5,10,14,3,10]
pivot = 10
```

Output

```text
[9,5,3,10,10,12,14]
```

---

# Why This Works

```text
All elements smaller than pivot
            ↓
      appear first

All pivot elements
            ↓
      appear next

All larger elements
            ↓
      appear last
```

Because elements are inserted in traversal order:

```text
Relative ordering is preserved.
```

---

# Comparison of Approaches

| Approach     | Idea                                           | Time | Space |
| ------------ | ---------------------------------------------- | ---- | ----- |
| Three Lists  | Store elements into three containers and merge | O(n) | O(n)  |
| Result Array | Three passes over array and fill result        | O(n) | O(n)  |

---

# Complexity Analysis

## Approach 1

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## Approach 2

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 1

```java
//Approach-1 (Using Three Lists)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int[] pivotArray(int[] nums, int pivot) {

        List<Integer> smaller = new ArrayList<>();

        List<Integer> larger = new ArrayList<>();

        List<Integer> equal = new ArrayList<>();

        for (int num : nums) {

            if (num < pivot) {

                smaller.add(num);
            }
            else if (num > pivot) {

                larger.add(num);
            }
            else {

                equal.add(num);
            }
        }

        smaller.addAll(equal);

        smaller.addAll(larger);

        for (int i = 0; i < nums.length; i++) {

            nums[i] = smaller.get(i);
        }

        return nums;
    }
}
```

---

# Java Solution 2

```java
//Approach-2 (Using Result Array)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int[] pivotArray(int[] nums, int pivot) {

        int n = nums.length;

        int[] result = new int[n];

        int index = 0;

        for (int num : nums) {

            if (num < pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num == pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num > pivot) {

                result[index++] = num;
            }
        }

        return result;
    }
}
```

---

# C++ Solution 1

```cpp
//Approach-1 (Using Three Vectors)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> pivotArray(vector<int>& nums, int pivot) {

        vector<int> smaller;

        vector<int> larger;

        vector<int> equal;

        for (int num : nums) {

            if (num < pivot) {

                smaller.push_back(num);
            }
            else if (num > pivot) {

                larger.push_back(num);
            }
            else {

                equal.push_back(num);
            }
        }

        smaller.insert(
            smaller.end(),
            equal.begin(),
            equal.end()
        );

        smaller.insert(
            smaller.end(),
            larger.begin(),
            larger.end()
        );

        return smaller;
    }
};
```

---

# C++ Solution 2

```cpp
//Approach-2 (Using Result Array)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> pivotArray(vector<int>& nums, int pivot) {

        int n = nums.size();

        vector<int> result(n);

        int index = 0;

        for (int num : nums) {

            if (num < pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num == pivot) {

                result[index++] = num;
            }
        }

        for (int num : nums) {

            if (num > pivot) {

                result[index++] = num;
            }
        }

        return result;
    }
};
```
