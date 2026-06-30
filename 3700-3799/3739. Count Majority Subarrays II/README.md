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

# 3739. Count Majority Subarrays II

## Tags

```text
Array
Prefix Sum
HashMap
Counting
Greedy
```

---

# Intuition

A subarray is **valid** if the target element appears **more than half** of the subarray length.

Instead of checking every subarray individually, we transform the problem using a prefix sum.

Replace every element by:

```text
target      → +1

other value → -1
```

Now every valid subarray satisfies:

```text
Prefix Difference > 0
```

Instead of checking all subarrays, we count them efficiently using a HashMap.

---

# Observation

Suppose

```text
target = 5
```

Array

```text
5 2 5 1
```

Transform

```text
+1 -1 +1 -1
```

Prefix Sum

```text
0

1

0

1

0
```

Positive prefix differences correspond to majority subarrays.

---

# Prefix Sum Visualization

```text
Original Array

5 2 5 1

        │

        ▼

Convert

+1 -1 +1 -1

        │

        ▼

Prefix Sum

0

1

0

1

0
```

---

# HashMap Idea

Maintain

```text
prefixSum
```

HashMap stores

```text
frequency of previous prefix sums
```

Whenever

```text
target
```

appears

```text
prefixSum++
```

Otherwise

```text
prefixSum--
```

Using the stored frequencies, we immediately know how many previous prefixes produce a valid majority subarray.

---

# Flowchart

```text
Start

   │

   ▼

Initialize

HashMap

prefix = 0

   │

   ▼

Traverse Array

   │

   ▼

Target ?

 ┌───────────────┐

 │               │

YES             NO

 │               │

 ▼               ▼

prefix++      prefix--

 │               │

 └───────┬───────┘

         ▼

Update Valid Prefix Count

         │

         ▼

Store Current Prefix

         │

         ▼

Add To Answer

         │

         ▼

Finish
```

---

# Dry Run

Input

```text
nums = [2,1,2]

target = 2
```

Convert

```text
+1 -1 +1
```

Prefix

```text
0

1

0

1
```

HashMap

```text
0 → 1

1 → 2
```

Valid Majority Subarrays

```text
[2]

[2,1,2]

[2]
```

Answer

```text
3
```

---

# Memory Visualization

```text
HashMap

Prefix

0 → 1

1 → 2

-1 → 0

Current Prefix

↓

Used to count

all previous

valid prefixes
```

---

# Why This Works

Suppose

```text
Target Count = T

Others = O
```

A majority means

```text
T > O
```

Move everything to one side

```text
T - O > 0
```

After replacing

```text
Target = +1

Others = -1
```

the subarray sum becomes

```text
T - O
```

So counting majority subarrays becomes equivalent to counting positive prefix differences.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Every element is processed exactly once.

---

## Space Complexity

```text
O(n)
```

HashMap stores prefix sums.

---

# Java Solution

```java
//Approach-1 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        Map<Integer, Integer> frequency = new HashMap<>();

        int prefixSum = 0;

        frequency.put(0, 1);

        long validPrefixes = 0;

        long answer = 0;

        for (int value : nums) {

            if (value == target) {

                validPrefixes +=
                    frequency.getOrDefault(
                        prefixSum,
                        0
                    );

                prefixSum++;

            } else {

                prefixSum--;

                validPrefixes -=
                    frequency.getOrDefault(
                        prefixSum,
                        0
                    );
            }

            frequency.merge(
                prefixSum,
                1,
                Integer::sum
            );

            answer += validPrefixes;
        }

        return answer;
    }
}
```

---

# C++ Solution

```cpp
//Approach-1 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    long long countMajoritySubarrays(
        vector<int>& nums,
        int target
    ) {

        unordered_map<int,int> frequency;

        int prefixSum = 0;

        frequency[0] = 1;

        long long validPrefixes = 0;

        long long answer = 0;

        for (int value : nums) {

            if (value == target) {

                validPrefixes +=
                    frequency[prefixSum];

                prefixSum++;

            } else {

                prefixSum--;

                validPrefixes -=
                    frequency[prefixSum];
            }

            frequency[prefixSum]++;

            answer += validPrefixes;
        }

        return answer;
    }
};
```
