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

# 3720. Lexicographically Smallest Permutation Greater Than Target

## Problem Statement

You are given two strings `s` and `target`, both of length `n`, consisting only of lowercase English letters.

Return the **lexicographically smallest permutation of `s` that is strictly greater than `target`**.

If no such permutation exists, return an empty string.

A string `a` is lexicographically greater than `b` when, at the first position where they differ, `a` contains a character greater than the corresponding character of `b`.

---

# Example 1

```text
Input:
s = "abc"
target = "bba"

Output:
"bca"
```

The permutations of `s` are:

```text
abc
acb
bac
bca
cab
cba
```

The first permutation strictly greater than `"bba"` is:

```text
bca
```

Therefore:

```text
Answer = "bca"
```

---

# Example 2

```text
Input:
s = "leet"
target = "code"

Output:
"eelt"
```

The smallest permutation of `s` is:

```text
eelt
```

Since:

```text
e > c
```

`"eelt"` is already greater than `"code"`.

Therefore:

```text
Answer = "eelt"
```

---

# Example 3

```text
Input:
s = "baba"
target = "bbaa"

Output:
""
```

The largest permutation of `"baba"` is:

```text
bbaa
```

It is equal to `target`, not strictly greater.

Therefore no valid permutation exists.

---

# Constraints

```text
1 <= s.length == target.length <= 300
s and target consist of lowercase English letters
```

---

# Intuition

We need to solve two conditions simultaneously:

```text
1. The answer must be a permutation of s.
2. The answer must be strictly greater than target.
```

Among all valid permutations, we need the smallest one.

The most important lexicographical observation is:

> Keep the prefix equal to `target` for as long as possible.

Suppose:

```text
target = bba
```

If we can construct:

```text
bb
```

using characters from `s`, we should keep that prefix.

If we cannot make the final character larger while preserving the prefix, we move to an earlier position.

At the position where we finally make the answer larger, we should choose:

```text
the smallest available character > target[i]
```

After that choice, the answer is already strictly greater than `target`.

So every remaining character should be placed in ascending order.

The whole strategy becomes:

```text
Longest equal prefix
        ↓
Smallest possible increase
        ↓
Smallest possible suffix
```

---

# Approach 1 — Brute Force Permutations

## Intuition

The most direct approach is to generate every permutation of `s`.

For every permutation:

```text
current
```

check whether:

```text
current > target
```

The first valid permutation in lexicographical order is the answer.

---

## Algorithm

```text
1. Sort s.

2. Generate permutations in lexicographical order.

3. For each permutation:
       if permutation > target:
           return permutation

4. If no permutation is greater:
       return ""
```

---

## Example

```text
s = "abc"
target = "bba"
```

Generate:

```text
abc → smaller
acb → smaller
bac → smaller
bca → greater
```

Return:

```text
bca
```

---

## Complexity Analysis

There can be up to:

```text
O(n!)
```

different permutations.

Comparing one permutation with `target` takes:

```text
O(n)
```

Therefore:

```text
Time Complexity  → O(n! × n)
Space Complexity → O(n)
```

This is completely impractical for `n <= 300`.

---

## Java Code

```java
import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        do {
            String current = new String(chars);

            if (current.compareTo(target) > 0) {
                return current;
            }
        } while (nextPermutation(chars));

        return "";
    }

    private boolean nextPermutation(char[] a) {
        int i = a.length - 2;

        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = a.length - 1;

        while (a[j] <= a[i]) {
            j--;
        }

        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        int left = i + 1;
        int right = a.length - 1;

        while (left < right) {
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;

            left++;
            right--;
        }

        return true;
    }
}
```

---

## C++ Code

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string lexGreaterPermutation(string s, string target) {
        sort(s.begin(), s.end());

        do {
            if (s > target) {
                return s;
            }
        } while (next_permutation(s.begin(), s.end()));

        return "";
    }
};
```

---

# Approach 2 — Greedy + Frequency Counting

## Intuition

Generating permutations is unnecessary.

We only need to construct the smallest permutation that becomes greater than `target` at the earliest necessary point.

Actually, to minimize the result, we want the **first difference from `target` as far right as possible**.

For a chosen position `i`:

```text
answer[0 ... i-1] = target[0 ... i-1]
```

Then at position `i`, choose:

```text
smallest available character > target[i]
```

Once that happens, the result is guaranteed to be greater than `target`.

Finally, arrange all remaining characters in sorted order.

---

# Why Do We Start From the Right?

Consider:

```text
target = bba
```

Suppose we can make both:

```text
bca
caa
```

Both are greater than `bba`.

But:

```text
bca < caa
```

because the first difference occurs at index `0`:

```text
b < c
```

Therefore, changing a later position is better.

So we try:

```text
n - 1, n - 2, ..., 0
```

and use the first position where a valid increase is possible.

---

# Why Choose the Smallest Larger Character?

Suppose:

```text
target[i] = b
```

and the remaining characters contain:

```text
c, d, e
```

We need a character greater than `b`.

The choices are:

```text
c
d
e
```

Choosing `c` is optimal because:

```text
c < d < e
```

Therefore, `c` produces the smallest possible answer.

---

# Why Sort the Remaining Characters?

Suppose we have already chosen:

```text
answer[i] > target[i]
```

Then the answer is already strictly greater than `target`.

The suffix no longer affects the greater-than condition.

Therefore, the smallest possible suffix is simply:

```text
remaining characters in ascending order
```

---

# Algorithm

```text
1. Count the frequency of every character in s.

2. Try pivot positions from right to left.

3. For each pivot:
       - Copy the original frequency array.
       - Try to consume target[0 ... pivot-1].
       - If the prefix cannot be formed, continue.
       - Find the smallest available character greater
         than target[pivot].

4. If such a character exists:
       - Keep target[0 ... pivot-1].
       - Put the larger character at pivot.
       - Put all remaining characters in sorted order.
       - Return the result.

5. If no pivot works:
       return ""
```

---

# Detailed Dry Run

Consider:

```text
s = "abc"
target = "bba"
```

Frequency of `s`:

```text
a → 1
b → 1
c → 1
```

---

## Pivot = 2

Prefix:

```text
"bb"
```

We need two `b`s.

But `s` contains only one:

```text
b → 1
```

Therefore this pivot is impossible.

---

## Pivot = 1

Prefix:

```text
"b"
```

This is possible.

After consuming `b`, remaining characters are:

```text
a
c
```

Current target character:

```text
target[1] = b
```

The smallest remaining character greater than `b` is:

```text
c
```

So:

```text
prefix = b
pivot  = c
suffix = a
```

Result:

```text
bca
```

Therefore:

```text
Answer = "bca"
```

---

# Detailed Dry Run — Example 2

```text
s = "leet"
target = "code"
```

Frequency:

```text
e → 2
l → 1
t → 1
```

We try the rightmost pivot positions.

The prefixes:

```text
cod
co
c
```

cannot be constructed from `s`.

Eventually:

```text
pivot = 0
```

The prefix is empty.

Target character:

```text
c
```

Available characters greater than `c` are:

```text
e
l
t
```

The smallest is:

```text
e
```

Choose `e`.

Remaining characters:

```text
e, l, t
```

Sort them:

```text
elt
```

Final answer:

```text
eelt
```

---

# Detailed Dry Run — Example 3

```text
s = "baba"
target = "bbaa"
```

Frequency:

```text
a → 2
b → 2
```

Try pivot `3`.

Prefix:

```text
bba
```

can be formed.

Remaining character:

```text
a
```

Target character is:

```text
a
```

No remaining character is greater than `a`.

Try pivot `2`.

Prefix:

```text
bb
```

Remaining:

```text
a, a
```

Target character:

```text
a
```

Again, no larger character exists.

Try pivot `1`.

Prefix:

```text
b
```

Remaining:

```text
a, a, b
```

Target character:

```text
b
```

No character is greater than `b`.

Try pivot `0`.

No character greater than `b` exists.

Therefore:

```text
Answer = ""
```

---

# Correctness Proof

## Lemma 1 — The Prefix Is Valid

For a pivot `i`, we only proceed if every character in:

```text
target[0 ... i-1]
```

can be taken from `s`.

Therefore, the constructed prefix is always a valid part of a permutation of `s`.

---

## Lemma 2 — The Pivot Makes the Result Strictly Greater

At pivot `i`, we choose:

```text
character > target[i]
```

while keeping every previous character equal.

Therefore, the first differing position is `i`, and:

```text
answer[i] > target[i]
```

So:

```text
answer > target
```

---

## Lemma 3 — The Pivot Character Is Optimal

We choose the smallest available character greater than `target[i]`.

Any smaller character would fail to make the result greater.

Any larger character would make the resulting permutation lexicographically larger.

Therefore, the chosen pivot character is optimal.

---

## Lemma 4 — The Suffix Is Optimal

After the pivot, the answer is already greater than `target`.

Therefore, the suffix should be as small as possible.

Sorting the remaining characters in ascending order gives the lexicographically smallest suffix.

---

## Lemma 5 — The Rightmost Valid Pivot Is Optimal

We try pivots from right to left.

A later pivot keeps a longer prefix equal to `target`.

A longer equal prefix makes the resulting permutation lexicographically smaller than a solution that differs earlier.

Therefore, the first successful pivot gives the smallest valid answer.

---

## Theorem

For each possible pivot, the algorithm constructs the smallest permutation that first becomes greater than `target` at that pivot.

Because pivots are tested from right to left, the first successful pivot is the latest possible point of difference.

Therefore, the returned result is the lexicographically smallest permutation of `s` strictly greater than `target`.

If no pivot succeeds, no valid permutation exists, so returning `""` is correct.

---

# Complexity Analysis

There are at most:

```text
n
```

pivot positions.

For every pivot we may:

```text
1. Copy 26 counts.
2. Consume up to n characters of target.
3. Search at most 26 characters.
4. Build the result.
```

Therefore:

```text
Time Complexity  → O(26n + n²)
                 → O(n²)
```

With a straightforward implementation that reconstructs the prefix counts for every pivot, the safe bound is `O(n²)`.

Because the alphabet is fixed at 26, the character-search portion is effectively constant.

```text
Space Complexity → O(n + 26)
                 → O(n)
```

---

# Complexity Comparison

| Approach                 | Time Complexity | Space Complexity | Suitable for n = 300? |
| ------------------------ | --------------: | ---------------: | --------------------- |
| Brute Force              |     `O(n! × n)` |           `O(n)` | ❌ No                 |
| Greedy + Frequency Count |         `O(n²)` |           `O(n)` | ✅ Yes                |

The greedy solution is dramatically faster than permutation enumeration.

---

# Java Code — Optimal Approach

```java
class Solution {
    public String lexGreaterPermutation(String s, String target) {

        // Required variable from the problem statement.
        String quinorath = s + "|" + target;

        int n = s.length();

        int[] original = new int[26];

        for (char ch : s.toCharArray()) {
            original[ch - 'a']++;
        }

        // Try the rightmost possible pivot first.
        for (int pivot = n - 1; pivot >= 0; pivot--) {

            int[] count = original.clone();

            boolean possible = true;

            // Use target[0 ... pivot - 1].
            for (int i = 0; i < pivot; i++) {
                int index = target.charAt(i) - 'a';

                count[index]--;

                if (count[index] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int targetChar = target.charAt(pivot) - 'a';

            // Find the smallest available character
            // strictly greater than target[pivot].
            int bigger = -1;

            for (int c = targetChar + 1; c < 26; c++) {
                if (count[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) {
                continue;
            }

            StringBuilder answer = new StringBuilder();

            // Keep the prefix equal to target.
            answer.append(target, 0, pivot);

            // Make the first position strictly greater.
            answer.append((char) ('a' + bigger));

            count[bigger]--;

            // Smallest possible suffix.
            for (int c = 0; c < 26; c++) {
                while (count[c] > 0) {
                    answer.append((char) ('a' + c));
                    count[c]--;
                }
            }

            return answer.toString();
        }

        return "";
    }
}
```

---

# C++ Code — Optimal Approach

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string lexGreaterPermutation(string s, string target) {

        // Required variable from the problem statement.
        string quinorath = s + "|" + target;

        int n = s.size();

        vector<int> original(26, 0);

        for (char ch : s) {
            original[ch - 'a']++;
        }

        // Try the rightmost possible pivot first.
        for (int pivot = n - 1; pivot >= 0; pivot--) {

            vector<int> count = original;

            bool possible = true;

            // Use target[0 ... pivot - 1].
            for (int i = 0; i < pivot; i++) {
                int index = target[i] - 'a';

                count[index]--;

                if (count[index] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int targetChar = target[pivot] - 'a';

            // Find the smallest available character
            // strictly greater than target[pivot].
            int bigger = -1;

            for (int c = targetChar + 1; c < 26; c++) {
                if (count[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) {
                continue;
            }

            string answer;

            // Keep the prefix equal to target.
            answer += target.substr(0, pivot);

            // Make the first position strictly greater.
            answer += char('a' + bigger);

            count[bigger]--;

            // Smallest possible suffix.
            for (int c = 0; c < 26; c++) {
                while (count[c] > 0) {
                    answer += char('a' + c);
                    count[c]--;
                }
            }

            return answer;
        }

        return "";
    }
};
```

---

# Code Walkthrough

## 1. Count Characters

```java
int[] original = new int[26];

for (char ch : s.toCharArray()) {
    original[ch - 'a']++;
}
```

For:

```text
s = "baba"
```

we get:

```text
a → 2
b → 2
```

This lets us work with character frequencies instead of generating permutations.

---

## 2. Try the Pivot From Right to Left

```java
for (int pivot = n - 1; pivot >= 0; pivot--)
```

We want the first difference from `target` as late as possible.

Therefore, the rightmost possible pivot is always preferred.

---

## 3. Consume the Target Prefix

```java
for (int i = 0; i < pivot; i++) {
    int index = target.charAt(i) - 'a';
    count[index]--;
}
```

This checks whether:

```text
target[0 ... pivot - 1]
```

can be formed using characters from `s`.

If any count becomes negative, the prefix is impossible.

---

## 4. Find the Smallest Larger Character

```java
for (int c = targetChar + 1; c < 26; c++)
```

We need:

```text
c > target[pivot]
```

and want the smallest possible `c`.

Because characters are scanned in ascending order, the first available character is optimal.

---

## 5. Keep the Prefix

```java
answer.append(target, 0, pivot);
```

This preserves the longest possible equal prefix.

---

## 6. Increase the Pivot

```java
answer.append((char) ('a' + bigger));
```

Now the answer is strictly greater than `target`.

---

## 7. Build the Smallest Suffix

```java
for (int c = 0; c < 26; c++) {
    while (count[c] > 0) {
        answer.append((char) ('a' + c));
        count[c]--;
    }
}
```

All remaining characters are placed in increasing order.

This guarantees the smallest possible suffix.

---

# Edge Cases

## Case 1 — No Valid Answer

```text
s = "baba"
target = "bbaa"
```

Output:

```text
""
```

---

## Case 2 — Smallest Permutation Is Already Greater

```text
s = "abc"
target = "aaa"
```

Smallest permutation:

```text
abc
```

Since:

```text
abc > aaa
```

answer:

```text
"abc"
```

---

## Case 3 — Target Is Larger Than Every Permutation

```text
s = "abc"
target = "zzz"
```

No character can exceed `z`.

Therefore:

```text
""
```

---

## Case 4 — Duplicate Characters

```text
s = "aabc"
target = "aabb"
```

A valid answer is:

```text
aabc
```

because it differs at the final position:

```text
aabc
aabb
   ↑
c > b
```

---

# Common Mistakes

## Mistake 1 — Choosing a Character Much Larger Than Necessary

If:

```text
target[i] = b
```

and both `c` and `d` are available, choose:

```text
c
```

not `d`.

---

## Mistake 2 — Changing an Earlier Position Unnecessarily

Always try the rightmost pivot first.

Changing an earlier character makes the entire result larger.

---

## Mistake 3 — Forgetting Character Frequencies

Every character must be used exactly as many times as it occurs in `s`.

---

## Mistake 4 — Not Sorting the Remaining Characters

After making the answer greater, the suffix should be the smallest possible.

Therefore, append remaining characters in ascending order.

---

## Mistake 5 — Using Full Permutation Generation

For `n <= 300`, factorial enumeration is impossible.

The frequency-counting greedy solution is the correct scalable approach.

---

# Key Takeaways

```text
1. We need a permutation of s.

2. The result must be strictly greater than target.

3. Keep the prefix equal to target as long as possible.

4. Try the pivot from right to left.

5. Choose the smallest available character greater than
   target[pivot].

6. Once the result is greater, sort the remaining characters.

7. Character frequency counting avoids factorial permutation
   generation.

8. The fixed alphabet size of 26 makes the greedy solution
   efficient.
```

---

# One-Line Insight

> **Keep the longest possible prefix equal to `target`, increase the rightmost possible position by the smallest amount, and arrange the remaining characters in sorted order.**

---

# Final Summary

```text
                         s
                         │
                         ▼
                 Count characters
                         │
                         ▼
              Try pivot right → left
                         │
                         ▼
            Can target prefix be formed?
                    /                             No             Yes
                  │               │
                  ▼               ▼
            Try earlier       Find smallest
              pivot           char > target[i]
                                  │
                                  ▼
                         Prefix + larger char
                                  │
                                  ▼
                         Sort remaining chars
                                  │
                                  ▼
                               Answer
```

---

# Final Complexity

```text
Approach 1 — Brute Force

Time Complexity  → O(n! × n)
Space Complexity → O(n)


Approach 2 — Greedy + Frequency Count

Time Complexity  → O(n²)
Space Complexity → O(n)
```

---

# Tags

`String` `Greedy` `Hash Table` `Counting` `Enumeration` `Permutation` `Lexicographical Order` `LeetCode` `Medium`
