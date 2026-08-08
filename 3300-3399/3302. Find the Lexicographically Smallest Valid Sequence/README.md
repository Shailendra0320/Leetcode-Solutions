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

# 3302. Find the Lexicographically Smallest Valid Sequence

## Tags

```text
Greedy
String
Two Pointers
Subsequence
Prefix and Suffix
Java
C++
```

---

# 📌 Problem Statement

You are given two strings:

```text
word1
word2
```

We need to find a sequence of indices from `word1` such that:

- The selected indices are strictly increasing.
- The characters at those indices form `word2`.
- We are allowed to change **at most one character** of the selected sequence.
- Among all valid sequences, return the **lexicographically smallest** sequence of indices.

If no valid sequence exists, return an empty array.

---

# 💡 Intuition

The main challenge is not simply finding a subsequence.

We need to find the **lexicographically smallest sequence of indices** while allowing at most one character mismatch.

For every character of `word2`, we try to choose the earliest possible index from `word1`.

However, choosing an index too early can make it impossible to match the remaining characters of `word2`.

Therefore, while selecting indices from left to right, we need information about whether the remaining suffix of `word2` can still be matched.

This is where the `last` array becomes important.

---

# 🔍 Key Observation

We divide the problem into two parts:

### 1. Suffix Matching

We calculate the earliest possible positions from the right side of `word1` that can match the remaining characters of `word2`.

This information is stored in:

```text
last[]
```

---

### 2. Greedy Selection

We then scan `word1` from left to right.

For every character of `word2`, we try to select the earliest valid index.

There are two possibilities:

```text
Character matches
```

or

```text
Character does not match
```

If the character does not match, we can use our **one allowed change**.

But before using the change, we must make sure that the remaining characters of `word2` can still be matched.

This is checked using:

```text
last[j + 1] >= i + 1
```

---

# 🚀 Approach — Greedy + Suffix Matching

The algorithm works in two phases.

### Phase 1 — Build `last[]`

Traverse `word1` from right to left.

For every character in `word2`, find the corresponding position in `word1`.

This tells us how far to the right we can match the remaining suffix.

---

### Phase 2 — Greedy Construction

Traverse `word1` from left to right.

For each character `word2[j]`:

- If the current character matches, select the index.
- If it does not match and the modification has not been used, check whether the remaining suffix can still be matched.
- If valid, use the modification at the current index.
- Once the modification is used, every remaining character must match normally.

Because we always select the earliest possible valid index, the resulting sequence is lexicographically smallest.

---

# 🧩 Understanding the `last` Array

Consider:

```text
word1 = "abcde"

word2 = "ace"
```

We process `word2` from right to left.

The purpose of `last[j]` is to store a position in `word1` from which the suffix beginning at `word2[j]` can be matched.

Conceptually:

```text
word2[j...m-1]
        │
        ▼
Can this suffix still be matched
after the current index?
```

This allows the greedy algorithm to safely decide whether it can use the one allowed modification.

---

# 📝 Algorithm

### Step 1

Let

```text
n = word1.length()

m = word2.length()
```

---

### Step 2

Create:

```text
last[m + 1]
```

Set:

```text
last[m] = n
```

This represents the empty suffix after all characters of `word2` have been processed.

---

### Step 3

Traverse `word2` from right to left.

For each character, move backward through `word1` until the required character is found.

Store its position in:

```text
last[j]
```

---

### Step 4

Create the result array:

```text
res[m]
```

and maintain:

```text
changed = false
```

This indicates whether the one allowed modification has already been used.

---

### Step 5

Traverse `word2` from left to right.

For every character:

```text
word2[j]
```

scan `word1` from the current position.

---

### Step 6

If the characters match:

```text
word1[i] == word2[j]
```

select the current index.

---

### Step 7

If they do not match and the modification has not been used, check:

```text
last[j + 1] >= i + 1
```

If true, use the modification at index `i`.

---

### Step 8

If the current choice cannot lead to a valid remaining sequence, continue searching for another index.

---

### Step 9

If every character of `word2` is successfully selected, return `res`.

Otherwise, return:

```text
new int[0]
```

---

# 🌳 Flowchart

```text
                         Start
                           │
                           ▼
                  Build Suffix Array
                       last[]
                           │
                           ▼
              Start Greedy Traversal
                           │
                           ▼
                Match word2[j]?
                  ┌────────┴────────┐
                 Yes                No
                  │                  │
                  ▼                  ▼
             Select i       Modification Available?
                                  │
                           ┌──────┴──────┐
                          Yes            No
                           │              │
                           ▼              ▼
                   Check Remaining    Skip Index
                   Suffix Possible?
                     │
                 ┌───┴───┐
                Yes      No
                 │        │
                 ▼        ▼
          Use Modification
                 │
                 ▼
           Continue Greedy
                 │
                 ▼
         All Characters Found?
            ┌────┴────┐
           Yes        No
            │          │
            ▼          ▼
       Return Result  Return []
```

---

# 📖 Example

```text
word1 = "abcde"

word2 = "ace"
```

We can select:

```text
a → c → e
```

Their indices are:

```text
[0, 2, 4]
```

Since the sequence is built by choosing the earliest valid index for every character, this gives the lexicographically smallest valid sequence.

---

# 🔄 Greedy Example With One Modification

Suppose the current character of `word1` does not match the required character of `word2`.

We may use our one allowed modification:

```text
word1[i] != word2[j]
```

Before accepting this index, we check whether the remaining suffix can still be matched:

```text
last[j + 1] >= i + 1
```

If this condition is satisfied, using the modification at the current index is safe.

This is important because using the modification too early without checking the suffix could make the remaining sequence impossible.

---

# 🧠 Why Greedy Works

The result is compared **lexicographically**, meaning the earliest index has the highest priority.

For example:

```text
[1, 5, 8]
```

is lexicographically smaller than:

```text
[2, 3, 4]
```

because:

```text
1 < 2
```

Therefore, for every position in the answer, we want to choose the smallest possible index.

The `last[]` suffix information ensures that choosing the current earliest index does not destroy the possibility of completing the remaining sequence.

Thus:

```text
Earliest Valid Index
+
Suffix Feasibility
+
At Most One Modification
```

produces the lexicographically smallest valid sequence.

---

# ⏱️ Complexity Analysis

Let:

```text
n = word1.length()

m = word2.length()
```

### Time Complexity

The suffix preprocessing scans `word1` from right to left, while the greedy phase scans `word1` forward.

Therefore:

```text
O(n + m)
```

---

### Space Complexity

The algorithm uses:

```text
last[m + 1]

res[m]
```

Therefore:

```text
O(m)
```

extra space.

---

# Key Takeaways

- ✅ Use a **suffix matching array** to know whether the remaining characters can be matched.
- ✅ Build the answer using a **left-to-right greedy strategy**.
- ✅ Always prefer the earliest possible index.
- ✅ Use the one allowed modification only when the remaining suffix remains feasible.
- ✅ Once the modification is used, all remaining characters must match normally.
- ✅ This produces the lexicographically smallest valid sequence.
- # Java Solution

## Approach 1 — Greedy + Suffix Matching

```java
//Approach-1 (Greedy + Suffix Matching)
//T.C : O(n + m)
//S.C : O(m)

class Solution {

    public int[] validSequence(
        String word1,
        String word2
    ) {

        int n =
            word1.length();

        int m =
            word2.length();

        int[] last =
            new int[m + 1];

        last[m] =
            n;

        int p =
            n - 1;

        for (
            int j = m - 1;
            j >= 0;
            j--
        ) {

            while (
                p >= 0 &&
                word1.charAt(p) !=
                word2.charAt(j)
            ) {

                p--;
            }

            last[j] =
                p;

            if (
                p >= 0
            ) {

                p--;
            }
        }

        int[] res =
            new int[m];

        boolean changed =
            false;

        int i = 0;

        for (
            int j = 0;
            j < m;
            j++
        ) {

            boolean found =
                false;

            while (
                i < n
            ) {

                boolean match =
                    word1.charAt(i) ==
                    word2.charAt(j);

                if (
                    match
                ) {

                    if (
                        changed
                    ) {

                        if (
                            last[j + 1] >=
                            i + 1
                        ) {

                            res[j] =
                                i;

                            i++;

                            found =
                                true;

                            break;
                        }

                    } else {

                        res[j] =
                            i;

                        i++;

                        found =
                            true;

                        break;
                    }

                } else {

                    if (
                        !changed &&
                        last[j + 1] >=
                        i + 1
                    ) {

                        res[j] =
                            i;

                        changed =
                            true;

                        i++;

                        found =
                            true;

                        break;
                    }
                }

                i++;
            }

            if (
                !found
            ) {

                return new int[0];
            }
        }

        return res;
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + Suffix Matching

```cpp
//Approach-1 (Greedy + Suffix Matching)
//T.C : O(n + m)
//S.C : O(m)

class Solution {
public:

    vector<int> validSequence(
        string word1,
        string word2
    ) {

        int n =
            word1.length();

        int m =
            word2.length();

        vector<int> last(
            m + 1
        );

        last[m] =
            n;

        int p =
            n - 1;

        for (
            int j = m - 1;
            j >= 0;
            j--
        ) {

            while (
                p >= 0 &&
                word1[p] !=
                word2[j]
            ) {

                p--;
            }

            last[j] =
                p;

            if (
                p >= 0
            ) {

                p--;
            }
        }

        vector<int> res(m);

        bool changed =
            false;

        int i = 0;

        for (
            int j = 0;
            j < m;
            j++
        ) {

            bool found =
                false;

            while (
                i < n
            ) {

                bool match =
                    word1[i] ==
                    word2[j];

                if (
                    match
                ) {

                    if (
                        changed
                    ) {

                        if (
                            last[j + 1] >=
                            i + 1
                        ) {

                            res[j] =
                                i;

                            i++;

                            found =
                                true;

                            break;
                        }

                    } else {

                        res[j] =
                            i;

                        i++;

                        found =
                            true;

                        break;
                    }

                } else {

                    if (
                        !changed &&
                        last[j + 1] >=
                        i + 1
                    ) {

                        res[j] =
                            i;

                        changed =
                            true;

                        i++;

                        found =
                            true;

                        break;
                    }
                }

                i++;
            }

            if (
                !found
            ) {

                return {};
            }
        }

        return res;
    }
};
```

---

# 🔎 Detailed Working

The algorithm combines two important ideas:

```text
Suffix Feasibility
        +
Greedy Selection
```

The suffix array answers:

```text
"Can I still construct the remaining part of word2
after choosing this index?"
```

The greedy scan answers:

```text
"What is the earliest index I can choose?"
```

Together, they allow us to construct the lexicographically smallest valid sequence.

---

# 🧩 Role of `changed`

The variable

```text
changed
```

represents whether the one allowed modification has already been used.

Initially:

```text
changed = false
```

When a mismatching character is selected:

```text
word1[i] != word2[j]
```

we use the modification:

```text
changed = true
```

After that, every future selected character must match exactly.

This guarantees that we never use more than one modification.

---

# 🧩 Role of `last[j + 1]`

When we want to use a modification at index `i`, we cannot blindly select it.

We first verify:

```text
last[j + 1] >= i + 1
```

This means the remaining suffix of `word2` can still be matched using positions after `i`.

Therefore, the current index is safe to choose.

---

# 📊 Complexity

Let:

```text
n = word1.length()

m = word2.length()
```

### Time

```text
O(n + m)
```

The suffix preprocessing and greedy traversal are linear.

### Space

```text
O(m)
```

for the `last` array and result array.

---

# ⚠️ Important Cases

### 1. Exact Subsequence

If `word2` already exists as a subsequence of `word1`, the algorithm simply chooses the earliest possible matching indices.

---

### 2. One Mismatch Required

If one character does not match, the algorithm can replace that character using the one allowed modification, provided the remaining suffix is still feasible.

---

### 3. Modification Already Used

Once:

```text
changed = true
```

no additional mismatch can be selected.

---

### 4. No Valid Sequence

If the algorithm cannot find a valid index for any character of `word2`, it returns:

```text
[]
```

---

# 🎯 Why the Result Is Lexicographically Smallest

Lexicographical comparison prioritizes the earliest differing index.

For example:

```text
[0, 4, 7]
```

is smaller than:

```text
[1, 2, 3]
```

because:

```text
0 < 1
```

Therefore, we always try to select the smallest possible current index.

The suffix feasibility check prevents us from making a greedy choice that would make the remaining sequence impossible.

So the algorithm follows:

```text
Choose Earliest Possible Index
            ↓
Check Remaining Suffix
            ↓
If Feasible → Keep Choice
            ↓
Otherwise → Continue Searching
```

This is the key reason the greedy strategy produces the lexicographically smallest valid sequence.

---

# 🏆 Final Summary

```text
1. Precompute suffix matching positions.
2. Traverse word2 from left to right.
3. Always try the earliest possible index.
4. If characters differ, use the one allowed modification.
5. Before using the modification, verify suffix feasibility.
6. Once modification is used, require exact matches.
7. Return the constructed index sequence.
8. If construction fails, return an empty array.
```

### Final Complexity

```text
Time Complexity  : O(n + m)

Space Complexity : O(m)
```

The combination of **suffix matching + greedy selection** provides an efficient way to construct the lexicographically smallest valid sequence.
