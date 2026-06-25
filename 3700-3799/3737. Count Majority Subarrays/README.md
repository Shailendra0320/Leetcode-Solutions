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

# 3737. Count Majority Subarrays

## Tags

```text
Array
Brute Force
Counting
Prefix Sum
```

---

# Intuition

For every possible subarray, count how many times the target appears.

If:

```text
target frequency
>

half of subarray length
```

then the target is the majority element of that subarray.

---

# Majority Condition

For a subarray:

```text
Length = L
```

Target appears:

```text
Count = C
```

It is valid only if:

```text
C > L / 2
```

---

# Approach

For every starting index:

```text
start
```

extend the ending index:

```text
end
```

Maintain:

```text
targetCount
```

For every new element:

```text
if(nums[end]==target)

targetCount++
```

Compute:

```text
length = end-start+1
```

If

```text
targetCount > length/2
```

increase answer.

---

# Flowchart

```text
Choose Start

      │
      ▼

Extend End

      │
      ▼

Update Target Count

      │
      ▼

Compute Length

      │
      ▼

Target Count >

Length/2 ?

      │

 YES ─────► answer++

 NO

      │

Continue
```

---

# Example

Input

```text
nums = [2,1,2]

target = 2
```

---

Subarrays

```text
[2] ✔

[2,1] ✔

[2,1,2] ✔

[1]

[1,2]

[2] ✔
```

Answer

```text
4
```

---

# Dry Run

Input

```text
nums = [2,1,2]

target = 2
```

---

Start = 0

```text
[2]

count = 1

length = 1

1 > 0

YES
```

---

```text
[2,1]

count = 1

length = 2

1 > 1 ?

NO
```

---

```text
[2,1,2]

count = 2

length = 3

2 > 1

YES
```

---

Start = 1

```text
[1]

count = 0

NO
```

---

```text
[1,2]

count = 1

length = 2

1 > 1 ?

NO
```

---

Start = 2

```text
[2]

YES
```

---

Answer

```text
4
```

---

# Visualization

```text
nums

2 1 2

│

├── [2] ✔

├── [2,1]

├── [2,1,2] ✔

│

├── [1]

├── [1,2]

│

└── [2] ✔
```

---

# Complexity Analysis

## Time Complexity

```text
O(n²)
```

Two nested loops.

---

## Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# Java Solution

```java
//Approach-1 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {

    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int answer = 0;

        for (int start = 0; start < n; start++) {

            int targetCount = 0;

            for (int end = start; end < n; end++) {

                if (nums[end] == target) {
                    targetCount++;
                }

                int length = end - start + 1;

                if (targetCount > length / 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
```

---

# C++ Solution

```cpp
//Approach-1 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {
public:

    int countMajoritySubarrays(
        vector<int>& nums,
        int target
    ) {

        int n = nums.size();

        int answer = 0;

        for (int start = 0; start < n; start++) {

            int targetCount = 0;

            for (int end = start; end < n; end++) {

                if (nums[end] == target) {
                    targetCount++;
                }

                int length = end - start + 1;

                if (targetCount > length / 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
};
```