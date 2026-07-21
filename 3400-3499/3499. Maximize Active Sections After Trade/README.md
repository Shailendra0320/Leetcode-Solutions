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

# 3499. Maximize Active Sections After Trade

## Tags

```text
Greedy
String
Simulation
Array
Counting
Run Length Encoding
Java
C++
```

---

# Intuition

We are allowed to perform

```text
Exactly One Trade.
```

A trade consists of

- Removing one contiguous block of active sections (`1`s).
- Activating the neighboring inactive sections (`0`s).

The goal is to maximize the total number of active sections after the trade.

Instead of trying every possible trade,

we only need to examine

```text
Blocks of Consecutive Characters.
```

---

# Key Observation

A trade is beneficial only when

```text
A Block of 1's

is Surrounded by

0's on Both Sides.
```

If such a block exists,

removing it merges the two neighboring zero blocks,

which then become active.

Therefore,

the additional active sections gained are simply

```text
Left Zero Block Length

+

Right Zero Block Length.
```

The removed `1`s are replaced by the merged activation,

so we only need the

```text
Maximum Gain.
```

---

# Approaches

1. Greedy + Run Length Encoding (Optimal)

---

# Approach 1 — Greedy + Run Length Encoding

## Idea

First,

count the total number of active sections.

Next,

compress the string into

```text
(Character, Length)

Blocks.
```

Then,

for every block of `1`s,

check whether

- Previous block is `0`
- Next block is `0`

If true,

the gain equals

```text
Left Zero Length

+

Right Zero Length
```

Take the maximum gain among all valid trades.

Finally,

return

```text
Total Active Sections

+

Maximum Gain.
```

---

# Algorithm

### Step 1

Count the total number of

```text
1's.
```

---

### Step 2

Add

```text
'1'

at both ends
```

to simplify boundary handling.

---

### Step 3

Convert the string into

```text
Run Length Encoded Blocks.
```

---

### Step 4

Traverse every block.

---

### Step 5

If a

```text
1 Block

is surrounded by

0 Blocks
```

calculate

```text
Left Zero Length

+

Right Zero Length.
```

---

### Step 6

Keep the maximum gain.

---

### Step 7

Return

```text
Total Ones

+

Maximum Gain.
```

---

# Flowchart

```text
                Start

                  │

                  ▼

          Count Total 1's

                  │

                  ▼

      Add Boundary 1's to String

                  │

                  ▼

      Build Consecutive Blocks

                  │

                  ▼

     Traverse Every 1 Block

                  │

                  ▼

 Surrounded by Zero Blocks ?

        ┌────────┴────────┐

       No                Yes

        │                 │

        ▼                 ▼

     Continue      Gain = Left + Right

                          │

                          ▼

                 Update Maximum Gain

                          │

                          ▼

        Return Total Ones + Gain
```

---

# Example

Input

```text
s = "1001001"
```

Blocks

```text
1

00

1

00

1
```

Possible Gain

```text
2 + 2 = 4
```

Answer

```text
Original Ones + 4
```

---

# Dry Run

Input

```text
s = "1001001"
```

Total Ones

```text
3
```

Blocks

```text
(1,1)

(0,2)

(1,1)

(0,2)

(1,1)
```

Middle block satisfies the condition.

Gain

```text
2 + 2 = 4
```

Answer

```text
3 + 4 = 7
```

---

# Memory Visualization

```text
Original String

        │

        ▼

Boundary Added

        │

        ▼

Run Length Blocks

        │

        ▼

Find Valid 1 Block

        │

        ▼

Compute Gain

        │

        ▼

Maximum Active Sections
```

---

# Why Greedy Works?

Each valid trade depends only on

```text
Adjacent Zero Blocks.
```

No interaction exists between different trades because

only

```text
One Trade
```

is allowed.

Therefore,

checking every eligible `1` block independently guarantees the optimal answer.

---

# Complexity Analysis

## Approach 1 — Greedy + Run Length Encoding

### Time Complexity

```text
O(n)
```

Each character is visited a constant number of times.

---

### Space Complexity

```text
O(n)
```

The block representation stores at most `n` segments.

---

# Java Solution

## Approach 1 — Greedy + Run Length Encoding (Optimal)

```java
//Approach-1 (Greedy + Run Length Encoding)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int maxActiveSectionsAfterTrade(
        String s
    ) {

        int n =
            s.length();

        int totalOnes = 0;

        for (
            char ch : s.toCharArray()
        ) {

            if (
                ch == '1'
            ) {

                totalOnes++;
            }
        }

        String augmented =
            "1" + s + "1";

        List<int[]> blocks =
            new ArrayList<>();

        int index = 0;

        while (
            index < augmented.length()
        ) {

            char current =
                augmented.charAt(index);

            int next =
                index;

            while (
                next < augmented.length() &&
                augmented.charAt(next) == current
            ) {

                next++;
            }

            blocks.add(
                new int[] {
                    current - '0',
                    next - index
                }
            );

            index = next;
        }

        int bestGain = 0;

        for (
            int i = 1;
            i + 1 < blocks.size();
            i++
        ) {

            int[] previous =
                blocks.get(i - 1);

            int[] current =
                blocks.get(i);

            int[] next =
                blocks.get(i + 1);

            if (
                current[0] == 1 &&
                previous[0] == 0 &&
                next[0] == 0
            ) {

                bestGain =
                    Math.max(
                        bestGain,
                        previous[1] + next[1]
                    );
            }
        }

        return totalOnes + bestGain;
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + Run Length Encoding (Optimal)

```cpp
//Approach-1 (Greedy + Run Length Encoding)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int maxActiveSectionsAfterTrade(
        string s
    ) {

        int totalOnes = 0;

        for (
            char ch : s
        ) {

            if (
                ch == '1'
            ) {

                totalOnes++;
            }
        }

        string augmented =
            "1" + s + "1";

        vector<pair<int, int>> blocks;

        int index = 0;

        while (
            index < augmented.size()
        ) {

            char current =
                augmented[index];

            int next =
                index;

            while (
                next < augmented.size() &&
                augmented[next] == current
            ) {

                next++;
            }

            blocks.push_back(
                {
                    current - '0',
                    next - index
                }
            );

            index = next;
        }

        int bestGain = 0;

        for (
            int i = 1;
            i + 1 < blocks.size();
            i++
        ) {

            auto previous =
                blocks[i - 1];

            auto current =
                blocks[i];

            auto next =
                blocks[i + 1];

            if (
                current.first == 1 &&
                previous.first == 0 &&
                next.first == 0
            ) {

                bestGain =
                    max(
                        bestGain,
                        previous.second + next.second
                    );
            }
        }

        return totalOnes + bestGain;
    }
};
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| Greedy + Run Length Encoding | Block Compression | **O(n)** | **O(n)** |

---

# Final Complexity

```text
Approach 1 (Greedy + Run Length Encoding)

Time Complexity  : O(n)

Space Complexity : O(n)
```

---

# Conclusion

- ✅ Count the initial number of active (`1`) sections.
- ✅ Compress the string into consecutive `(character, length)` blocks using **Run Length Encoding**.
- ✅ Only consider `1` blocks that are surrounded by `0` blocks on both sides.
- ✅ The gain from a valid trade equals the sum of the lengths of the adjacent zero blocks.
- ✅ Choose the maximum possible gain and add it to the original count of active sections.
- ✅ Since each character is processed only a constant number of times, the solution runs efficiently in **O(n)** time.