# 2914. Minimum Number of Changes to Make Binary String Beautiful

## Profiles

- **GitHub:** https://github.com/Shailendra0320
- **Main LeetCode:** https://leetcode.com/u/Shailu03/
- **Alternate LeetCode:** https://leetcode.com/u/ShailendraLeetcode03/

## Problem Details

- **Platform:** LeetCode
- **Problem Number:** 2914
- **Difficulty:** Medium
- **Topics:** String, Greedy, Counting
- **Problem Link:** https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/

---

## Problem Statement

You are given a 0-indexed binary string `s` having an even length.

A string is **beautiful** if it is possible to partition it into one or more substrings such that:

1. Each substring has an even length.
2. Each substring contains only `1`s or only `0`s.

You can change any character in `s` to `0` or `1`.

Return the **minimum number of changes** required to make the string beautiful.

## Examples

### Example 1

**Input:**
```text
s = "1001"
```

**Output:**
```text
2
```

**Explanation:**

Change `s[1]` from `0` to `1` and `s[3]` from `1` to `0`. The resulting string is `"1100"`, which can be partitioned as `"11" | "00"`.

### Example 2

**Input:**
```text
s = "10"
```

**Output:**
```text
1
```

**Explanation:**

Change either character to make the pair `"00"` or `"11"`.

### Example 3

**Input:**
```text
s = "0000"
```

**Output:**
```text
0
```

**Explanation:**

The string is already beautiful. It can be partitioned as `"00" | "00"` or kept as one even-length substring.

## Constraints

- `2 <= s.length <= 10^5`
- `s.length` is even.
- `s[i]` is either `'0'` or `'1'`.

---

## Key Observation

Every valid substring has:
- an even length, and
- identical characters throughout that substring.

Therefore, any valid substring can be split into adjacent pairs of equal characters:

- `"0000"` can be split into `"00" | "00"`.
- `"1111"` can be split into `"11" | "11"`.

So, the entire string is beautiful exactly when every fixed pair `(s[0], s[1])`, `(s[2], s[3])`, `(s[4], s[5])`, and so on contains equal characters.

For each pair:

| Pair | Already valid? | Minimum changes |
|---|---:|---:|
| `"00"` | Yes | 0 |
| `"11"` | Yes | 0 |
| `"01"` | No | 1 |
| `"10"` | No | 1 |

A mismatched pair needs exactly one change: change either character to match the other. Since the pairs do not overlap, the decisions are independent.

**Final idea:** Count the number of pairs whose two characters are different.

---

## Approach 1: Greedy Pair-by-Pair Counting (Recommended)

### Intuition

Process the string in blocks of exactly two characters. If both characters in a pair are equal, that pair is already valid. If they differ, exactly one character must be changed, so increment the answer.

We do not need to construct the modified string because only the minimum number of changes is requested.

### Algorithm

1. Initialize `changes = 0`.
2. Iterate through the string using indices `0, 2, 4, ...`.
3. Compare `s[i]` and `s[i + 1]`.
4. If they are different, increment `changes`.
5. Return `changes`.

### Dry Run

**Input:** `s = "1001"`

| Pair index | Pair | Comparison | Changes so far |
|---|---|---|---:|
| 0–1 | `"10"` | Different | 1 |
| 2–3 | `"01"` | Different | 2 |

**Output:** `2`

One possible beautiful result is `"1100"`:

```text
Original:  1 0 | 0 1
Changes:   1 1 | 0 0
Result:    1 1 | 0 0
```

### Complexity Analysis

- **Time Complexity:** `O(n)` — each character is visited as part of one pair.
- **Space Complexity:** `O(1)` — only one counter is used.

---

## Approach 2: Count Mismatches at Odd Indices

### Intuition

Instead of explicitly moving through pairs by their first indices, inspect the second character of every pair.

The pairs are:

```text
(s[0], s[1]), (s[2], s[3]), (s[4], s[5]), ...
```

Thus, the second positions are `1, 3, 5, ...`. If `s[i] != s[i - 1]` at any of these positions, that pair requires exactly one change.

This is the same greedy observation expressed by checking the second index of each pair.

### Algorithm

1. Initialize `changes = 0`.
2. Start at index `1`.
3. Move forward by `2` each time.
4. If `s[i]` differs from `s[i - 1]`, increment `changes`.
5. Return the counter.

### Dry Run

**Input:** `s = "101010"`

| Index checked | Pair | Mismatch? | Changes so far |
|---:|---|---|---:|
| 1 | `"10"` | Yes | 1 |
| 3 | `"10"` | Yes | 2 |
| 5 | `"10"` | Yes | 3 |

**Output:** `3`

### Complexity Analysis

- **Time Complexity:** `O(n)` — every pair is checked once.
- **Space Complexity:** `O(1)` — constant extra space.

---

## Approach Comparison

| Feature | Approach 1: Pair-by-Pair | Approach 2: Odd-Index Check |
|---|---|---|
| Main check | `s[i] != s[i + 1]` | `s[i] != s[i - 1]` |
| Starting index | 0 | 1 |
| Increment | 2 | 2 |
| Time | `O(n)` | `O(n)` |
| Extra space | `O(1)` | `O(1)` |
| Readability | Very intuitive | Compact and direct |

**Recommendation:** Approach 1 is the clearest for interviews because it directly represents the required pairs.

---

## Correctness Proof

Let the string length be `n`, where `n` is even. Divide the string into the fixed adjacent pairs:

`(s[0], s[1]), (s[2], s[3]), ..., (s[n-2], s[n-1])`.

**1. Every beautiful string must have equal characters in each fixed pair.**

A valid partition consists of even-length uniform substrings. Each such substring starts at an even index because all preceding substrings have even lengths. Every fixed pair therefore lies completely inside one uniform substring, so its two characters must be equal.

**2. Every mismatched pair requires at least one change.**

If a pair is `"01"` or `"10"`, its characters are different. At least one of them must change to make the pair equal.

**3. One change is sufficient for each mismatched pair.**

Changing either character in `"01"` or `"10"` makes it `"00"` or `"11"`. An already equal pair requires no change.

**4. The pairwise choices do not interfere.**

Every character belongs to exactly one pair. Changing a character in one pair cannot affect any other pair.

Therefore, changing exactly one character for every mismatched pair and zero characters for every equal pair is both necessary and sufficient. The algorithm counts precisely those mismatched pairs, so it returns the minimum possible number of changes.

---

## Edge Cases

| Input | Pair breakdown | Output | Reason |
|---|---|---:|---|
| `"00"` | `"00"` | 0 | Already equal |
| `"11"` | `"11"` | 0 | Already equal |
| `"01"` | `"01"` | 1 | One mismatch |
| `"10"` | `"10"` | 1 | One mismatch |
| `"0000"` | `"00"`, `"00"` | 0 | All pairs equal |
| `"1111"` | `"11"`, `"11"` | 0 | All pairs equal |
| `"1010"` | `"10"`, `"10"` | 2 | Both pairs mismatch |
| `"1001"` | `"10"`, `"01"` | 2 | Both pairs mismatch |

## Common Mistakes

1. **Checking every adjacent pair with a step of one.**  
   The required blocks are fixed pairs starting at indices `0, 2, 4, ...`, not every overlapping pair.

2. **Trying to count runs of zeros and ones.**  
   The string can be partitioned into valid even-length uniform substrings if and only if each fixed pair is equal. Run-length processing is unnecessary.

3. **Adding two changes for a mismatched pair.**  
   Only one character needs to change: `"01"` can become `"00"` or `"11"`.

4. **Building a new string.**  
   The answer only requires a count, so modifying or copying the input is unnecessary.

5. **Using an unnecessary dynamic programming solution.**  
   Each pair is independent, so a single linear scan is enough.

---

## Interview Explanation

"Because every valid substring must have an even length and contain only one type of character, it can be divided into adjacent equal pairs. Therefore, I only need to inspect the fixed pairs starting at indices 0, 2, 4, and so on. If the two characters in a pair differ, I can make them equal by changing exactly one character. I count these mismatched pairs and return the count. This takes `O(n)` time and `O(1)` extra space."

## Quick Revision

- Split the string conceptually into pairs of size two.
- `"00"` and `"11"` need zero changes.
- `"01"` and `"10"` need one change.
- Count all mismatched pairs.
- **Optimal complexity:** `O(n)` time and `O(1)` space.

---

# Implementations

Both approaches are included in each language. The method signature matches LeetCode 2914.

## Java

```java
//Approach-1 (Greedy Pair-by-Pair Counting)
//T.C: O(n)
//S.C: O(1)
class Solution {
    public int minChanges(String s) {
        int changes = 0;

        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }

        return changes;
    }
}

//Approach-2 (Count Mismatches at Odd Indices)
//T.C: O(n)
//S.C: O(1)
class Solution2 {
    public int minChanges(String s) {
        int changes = 0;

        for (int i = 1; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                changes++;
            }
        }

        return changes;
    }
}
```

## C++

```cpp
//Approach-1 (Greedy Pair-by-Pair Counting)
//T.C: O(n)
//S.C: O(1)
class Solution {
public:
    int minChanges(string s) {
        int changes = 0;

        for (int i = 0; i < (int)s.size(); i += 2) {
            if (s[i] != s[i + 1]) {
                changes++;
            }
        }

        return changes;
    }
};

//Approach-2 (Count Mismatches at Odd Indices)
//T.C: O(n)
//S.C: O(1)
class Solution2 {
public:
    int minChanges(string s) {
        int changes = 0;

        for (int i = 1; i < (int)s.size(); i += 2) {
            if (s[i] != s[i - 1]) {
                changes++;
            }
        }

        return changes;
    }
};
```

## Python

```python
#Approach-1 (Greedy Pair-by-Pair Counting)
#T.C: O(n)
#S.C: O(1)
class Solution:
    def minChanges(self, s: str) -> int:
        changes = 0

        for i in range(0, len(s), 2):
            if s[i] != s[i + 1]:
                changes += 1

        return changes


#Approach-2 (Count Mismatches at Odd Indices)
#T.C: O(n)
#S.C: O(1)
class Solution2:
    def minChanges(self, s: str) -> int:
        changes = 0

        for i in range(1, len(s), 2):
            if s[i] != s[i - 1]:
                changes += 1

        return changes
```

---

## Final Takeaway

The key is to recognize that every valid even-length uniform substring can be divided into equal adjacent pairs. Count the pairs containing different characters; each such pair contributes exactly one required change.

**Best approach:** Greedy Pair-by-Pair Counting — `O(n)` time and `O(1)` space.

## References

- [LeetCode Problem 2914](https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/)
- [LeetCode official hints](https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/)
