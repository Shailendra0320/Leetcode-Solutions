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

# 3020. Find the Maximum Number of Elements in Subset

## Tags

HashMap
Greedy
Math
Simulation
Array
Java
C++

---

# Intuition

We need to construct the longest valid subset having the form

x, x, x², x⁴, x⁸, ..., center, ..., x⁸, x⁴, x², x, x

Notice that:
- every element before the center needs two copies
- the center needs one copy
- each next value is the square of the previous value

Therefore the problem becomes building the longest square-chain.

---

# Key Observation

Suppose x = 2

Then the chain becomes:
2, 4, 16, 256, ...

If every value exists at least twice:
2, 2, 4, 4, 16, 16

we may continue. Otherwise, the chain stops.

---

# Special Case

The number 1 is unique because 1² = 1

Therefore 1, 1, 1, 1 can never move to another value.

Only an odd number of ones can form a palindrome.

Hence, answer = largest odd frequency of 1

---

# Approaches

1. Frequency HashMap (Optimal) - MAIN
2. Chain Simulation (Commented)
3. Greedy Frequency Expansion (Commented)

---

# Approach 1 — Frequency HashMap (Optimal)

## Idea

Store number → frequency inside a HashMap.

For every unique value x, try building:
x → x² → x⁴ → ...

If a value occurs at least 2 times, it contributes +2
Otherwise, the chain stops.

The last value contributes +1 because it becomes the center.

---

# Flowchart

Build Frequency Map
          │
          ▼
Take One Number
          │
          ▼
Build Square Chain: x → x² → x⁴ → ...
          │
          ▼
Frequency >=2 ?
      YES │ NO
          ▼
Add 2, Continue
          │
          ▼
Stop
          │
          ▼
Add Center (+1)
          │
          ▼
Update Answer

---

# Visualization

Example: nums = [2, 2, 4, 4, 16, 16, 256]

Chain: 2 → 4 → 16 → 256

Contribution:
2 (pair) + 4 (pair) + 16 (pair) + 256 (center) = 7

---

# Approach 2 — Chain Simulation

## Idea

Instead of counting while traversing, first explicitly build chain = [x, x², x⁴, ...]

Store every value inside a list. Then traverse the list and calculate:
+2 for every pair, +1 for center

This approach is easier to understand, although slightly longer.

---

# Approach 3 — Greedy Frequency Expansion

## Idea

We don't explicitly store the chain. Instead, start from x and greedily continue while frequency >= 2

Immediately update length += 2, move to x², and repeat.

This avoids storing any extra chain.

---

# Dry Run

Input: nums = [2, 2, 4, 4, 16, 16, 256]

Frequency:
2 → 2
4 → 2
16 → 2
256 → 1

Chain: 2 → 4 → 16 → 256

Contribution: 2 + 2 + 2 + 1 = 7

Answer: 7

---

# Complexity Analysis

---

## Approach 1 — Frequency HashMap

### Time Complexity: O(n × log log M)

where:
n = Number of elements
M = Maximum value in the array

### Explanation

1. Traverse the array once to build the frequency map: O(n)

2. For every distinct number, construct its square chain:
x → x² → x⁴ → x⁸ → x¹⁶ → ...

Since every squaring operation grows extremely fast, the chain length is only O(log log M)

Therefore, Overall Time = O(n × log log M)

### Space Complexity: O(n)

because the frequency of every distinct number is stored in a HashMap.

---

## Approach 2 — Chain Simulation

### Time Complexity: O(n × log log M)

### Explanation

- Build the frequency map
- Explicitly create the square chain

Example: 2 → 4 → 16 → 256 → 65536

Each chain is very short because numbers increase exponentially.

Hence, Time = O(n × log log M)

### Space Complexity: O(n)

Extra memory is used for:
- Frequency HashMap
- Temporary chain list

---

## Approach 3 — Greedy Frequency Expansion

### Time Complexity: O(n × log log M)

### Explanation

Instead of storing the complete chain, we greedily move:
x → x² → x⁴ → ...

until the chain breaks.

Since the chain length is bounded by log log M, the complexity remains O(n × log log M)

### Space Complexity: O(n)

Only the frequency HashMap is maintained.

---

# Complexity Comparison

| Approach | Time Complexity | Space Complexity |
|:---------:|:---------------:|:----------------:|
| Approach 1 — Frequency HashMap | O(n × log log M) | O(n) |
| Approach 2 — Chain Simulation | O(n × log log M) | O(n) |
| Approach 3 — Greedy Frequency Expansion | O(n × log log M) | O(n) |

---

# Why is the Chain Length only log log M?

Suppose x = 2

The square chain becomes:
2 → 4 → 16 → 256 → 65536 → 4294967296

Notice that exponents: 1 → 2 → 4 → 8 → 16 → 32

The exponent doubles every step.

Therefore, after only a few squaring operations, the value becomes extremely large.

Mathematically, Chain Length ≈ log₂(log₂(M)), which is commonly written as O(log log M)

---

# Final Complexity

Time: O(n × log log M)
Space: O(n)

This makes the solution highly efficient, even for very large input values, because each square chain contains only a handful of elements.

---

# Java Solution (with all approaches)

```java
//Approach-1 (Frequency HashMap) - MAIN SOLUTION
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> count = new HashMap<>();

        for (int num : nums) {

            count.put(
                (long) num,
                count.getOrDefault((long) num, 0) + 1
            );
        }

        int answer = 1;

        if (count.containsKey(1L)) {

            int ones = count.get(1L);

            answer = Math.max(
                answer,
                (ones % 2 == 1) ? ones : ones - 1
            );
        }

        for (long value : count.keySet()) {

            if (value == 1) {
                continue;
            }

            int length = 0;

            long current = value;

            while (
                count.containsKey(current)
                &&
                count.get(current) >= 2
            ) {

                length += 2;

                current *= current;
            }

            if (count.containsKey(current)) {

                length++;

            } else {

                length--;
            }

            answer = Math.max(answer, length);
        }

        return answer;
    }
}

/*
//Approach-2 (Chain Simulation)
//T.C : O(n log log M)
//S.C : O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> frequency = new HashMap<>();

        Set<Long> values = new HashSet<>();

        for (int num : nums) {

            long value = num;

            frequency.put(
                value,
                frequency.getOrDefault(value, 0) + 1
            );

            values.add(value);
        }

        int answer = 1;

        if (frequency.containsKey(1L)) {

            int ones = frequency.get(1L);

            answer = Math.max(
                answer,
                (ones % 2 == 1) ? ones : ones - 1
            );
        }

        for (long start : values) {

            if (start == 1) {
                continue;
            }

            List<Long> chain =
                new ArrayList<>();

            long current = start;

            while (values.contains(current)) {

                chain.add(current);

                current *= current;
            }

            int length = 0;

            for (
                int index = 0;
                index < chain.size();
                index++
            ) {

                long value =
                    chain.get(index);

                if (
                    index < chain.size() - 1
                ) {

                    if (
                        frequency.get(value) >= 2
                    ) {

                        length += 2;

                    } else {

                        break;
                    }

                } else {

                    length++;
                }
            }

            answer =
                Math.max(answer, length);
        }

        return answer;
    }
}

//Approach-3 (Greedy Frequency Expansion)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

    public int maximumLength(int[] nums) {

        Map<Long, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.merge((long) num, 1, Integer::sum);
        }

        int oneCount = count.getOrDefault(1L, 0);

        int answer =
            (oneCount & 1) == 1
            ? oneCount
            : oneCount - 1;

        count.remove(1L);

        for (long start : count.keySet()) {

            int length = 0;

            long current = start;

            while (
                count.containsKey(current)
                &&
                count.get(current) > 1
            ) {

                length += 2;

                current *= current;
            }

            answer = Math.max(
                answer,
                length +
                (
                    count.containsKey(current)
                    ? 1
                    : -1
                )
            );
        }

        return answer;
    }
}
*/