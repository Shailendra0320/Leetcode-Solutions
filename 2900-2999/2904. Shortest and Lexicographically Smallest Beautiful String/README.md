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

# 2904. Shortest and Lexicographically Smallest Beautiful String

## Problem Statement

You are given a binary string `s` and an integer `k`.

A substring is called **beautiful** if it contains exactly `k` occurrences of the character `'1'`.

Return the **shortest beautiful substring** of `s`.

If there are multiple beautiful substrings with the same minimum length, return the **lexicographically smallest** one.

If no beautiful substring exists, return an empty string.

---

# Examples

## Example 1

```text
Input:
s = "100011001"
k = 3

Output:
"11001"
```

The substring `"11001"` contains exactly three `1`s:

```text
1 1 0 0 1
```

Therefore, it is a beautiful substring.

---

## Example 2

```text
Input:
s = "1011"
k = 2

Output:
"11"
```

Possible beautiful substrings include:

```text
"101"
"011"
"11"
```

Their lengths are:

```text
"101" → 3
"011" → 3
"11"  → 2
```

Therefore, the shortest beautiful substring is:

```text
"11"
```

---

## Example 3

```text
Input:
s = "000"
k = 1

Output:
""
```

There is no `1` in the string, so no substring can contain exactly one `1`.

Therefore, the answer is an empty string.

---

# Constraints

```text
1 <= s.length <= 100
1 <= k <= s.length
s[i] is either '0' or '1'
```

---

# Intuition

The problem has **two levels of optimization**.

First, the substring must contain exactly:

```text
k ones
```

Among all such substrings, we want:

```text
1. Minimum length
2. Lexicographically smallest if lengths are equal
```

For example:

```text
"101"
"011"
"11"
```

If all three contain exactly `2` ones, we cannot simply return the first valid substring.

We must compare:

```text
Length first
       ↓
Lexicographical order second
```

The key observation is that we can maintain the number of `1`s inside a moving window instead of recounting them for every substring.

This leads naturally to a **Sliding Window / Two Pointer** solution.

---

# Important Observation

Suppose a current window is:

```text
001101
```

and it contains exactly `3` ones.

The leading zeroes do not contribute anything to the number of ones.

Therefore, we can safely remove them:

```text
001101
   ↓
01101
   ↓
1101
```

while keeping the number of ones unchanged.

This means:

> When a window already contains exactly `k` ones, we should remove all unnecessary leading zeroes to make it as short as possible.

However, we cannot remove a leading `1`, because doing so would reduce the number of ones below `k`.

---

# Approach 1 — Brute Force

## Intuition

The most straightforward approach is to generate every possible substring.

For every substring:

```text
s[i...j]
```

we count the number of `1`s.

If the substring contains exactly `k` ones, it is beautiful.

We then compare it with the current answer.

The new substring becomes the answer if:

```text
1. The current answer is empty.
2. The new substring is shorter.
3. Both have the same length and the new substring
   is lexicographically smaller.
```

---

## Algorithm

```text
1. Initialize answer as an empty string.

2. Iterate over every possible starting index i.

3. Iterate over every possible ending index j.

4. Create the substring s[i...j].

5. Count the number of '1's in the substring.

6. If the number of ones is exactly k:
       - Compare its length with the current answer.
       - If lengths are equal, compare lexicographically.

7. Return the best answer.
```

---

## Dry Run

Consider:

```text
s = "1011"
k = 2
```

Some candidate substrings are:

```text
"1"    → 1 one
"10"   → 1 one
"101"  → 2 ones
"1011" → 3 ones
"01"   → 1 one
"011"  → 2 ones
"11"   → 2 ones
```

The beautiful substrings are:

```text
"101"
"011"
"11"
```

Their lengths are:

```text
"101" → 3
"011" → 3
"11"  → 2
```

Therefore:

```text
Answer = "11"
```

---

## Complexity Analysis

Let:

```text
n = s.length()
```

There are:

```text
O(n²)
```

possible substrings.

For each substring, we may scan up to `O(n)` characters to count the number of ones.

Therefore:

```text
Time Complexity  → O(n³)
Space Complexity → O(n)
```

---

## Java Code

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {

                String current = s.substring(i, j);

                int ones = 0;

                for (char ch : current.toCharArray()) {
                    if (ch == '1') {
                        ones++;
                    }
                }

                if (ones == k) {
                    if (ans.isEmpty()
                            || current.length() < ans.length()
                            || (current.length() == ans.length()
                            && current.compareTo(ans) < 0)) {

                        ans = current;
                    }
                }
            }
        }

        return ans;
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
    string shortestBeautifulSubstring(string s, int k) {
        int n = s.size();
        string ans = "";

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {

                string current = s.substr(i, j - i);

                int ones = 0;

                for (char ch : current) {
                    if (ch == '1') {
                        ones++;
                    }
                }

                if (ones == k) {
                    if (ans.empty()
                            || current.size() < ans.size()
                            || (current.size() == ans.size()
                            && current < ans)) {

                        ans = current;
                    }
                }
            }
        }

        return ans;
    }
};
```

---

# Approach 2 — Sliding Window / Two Pointers

## Intuition

The brute-force approach repeatedly scans overlapping substrings.

For example:

```text
101
1011
011
0110
...
```

Many characters are counted repeatedly.

We can avoid this using a **sliding window**.

We maintain:

```text
left
right
ones
```

where:

```text
left  → left boundary of the current window
right → right boundary of the current window
ones  → number of '1's inside the window
```

As `right` moves forward, we update `ones`.

If the window contains more than `k` ones, we move `left` forward.

When the window contains exactly `k` ones, we remove unnecessary leading zeroes.

The resulting window is the shortest beautiful substring ending at the current `right`.

---

# Sliding Window Structure

```text
        left                    right
          ↓                       ↓
          [  current window  ]
          └───────────────────────┘
                    │
                    ▼
             Count of ones
                    │
             ┌──────┴──────┐
             │             │
          ones > k       ones == k
             │             │
             ▼             ▼
        Move left      Remove leading
                       zeroes
```

---

# Algorithm

```text
1. Set:
       left = 0
       ones = 0

2. Move right from 0 to n - 1.

3. If s[right] == '1':
       increment ones.

4. While ones > k:
       - If s[left] == '1', decrement ones.
       - Move left forward.

5. While:
       ones == k
       AND s[left] == '0'

       move left forward.

6. If ones == k:
       current window is beautiful.

7. Compare it with the current answer:
       - shorter length → better
       - same length + lexicographically smaller → better

8. Return the final answer.
```

---

# Detailed Dry Run

Consider:

```text
s = "100011001"
k = 3
```

We start with:

```text
left = 0
ones = 0
```

---

## Step 1

```text
right = 0
s[right] = '1'
```

Therefore:

```text
ones = 1
```

Current window:

```text
"1"
```

---

## Step 2

```text
right = 1
s[right] = '0'
```

The number of ones remains:

```text
ones = 1
```

Window:

```text
"10"
```

---

## Step 3

```text
right = 2
s[right] = '0'
```

Still:

```text
ones = 1
```

Window:

```text
"100"
```

---

## Step 4

```text
right = 3
s[right] = '0'
```

Still:

```text
ones = 1
```

Window:

```text
"1000"
```

---

## Step 5

```text
right = 4
s[right] = '1'
```

Now:

```text
ones = 2
```

Window:

```text
"10001"
```

---

## Step 6

```text
right = 5
s[right] = '1'
```

Now:

```text
ones = 3
```

The window:

```text
"100011"
```

contains exactly `3` ones.

We cannot remove the leading `1`, because we would lose one required `1`.

So this is a valid candidate.

---

## Continue Scanning

As `right` moves further, the window changes.

When we eventually reach:

```text
"11001"
```

it contains exactly:

```text
3 ones
```

and has length:

```text
5
```

This becomes the best candidate.

Therefore:

```text
Answer = "11001"
```

---

# Lexicographical Comparison

Suppose two candidates have the same length:

```text
"1011"
"1100"
```

Compare character by character:

```text
1 == 1
0 < 1
```

Therefore:

```text
"1011" < "1100"
```

So `"1011"` is lexicographically smaller.

### Java

```java
current.compareTo(ans) < 0
```

### C++

```cpp
current < ans
```

---

# Why Removing Leading Zeroes Works

Suppose:

```text
current = "001110"
```

and:

```text
ones = 3
```

The leading zeroes do not affect the count.

Therefore:

```text
001110
  ↓
01110
  ↓
1110
```

Each removal makes the substring shorter while preserving exactly `3` ones.

Therefore, we should always remove leading zeroes when:

```text
ones == k
```

---

# Why We Cannot Remove a Leading One

Suppose:

```text
current = "1110"
k = 3
```

The window contains exactly `3` ones.

If we remove the first `1`:

```text
"1110"
  ↓
"110"
```

Now there are only:

```text
2 ones
```

So the substring is no longer beautiful.

Therefore, when `ones == k`, we remove only leading zeroes.

---

## Complexity Analysis

The two pointers move only from left to right.

Therefore, maintaining the window requires:

```text
O(n)
```

time.

However, the implementation creates candidate strings and performs lexicographical comparisons.

A candidate string can have length `O(n)`.

Therefore, for this direct implementation:

```text
Time Complexity  → O(n²)
Space Complexity → O(n)
```

This is still a major improvement over the brute-force `O(n³)` approach.

---

## Java Code

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // More than k ones → shrink from the left.
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            // Remove unnecessary leading zeroes.
            while (left < right
                    && ones == k
                    && s.charAt(left) == '0') {

                left++;
            }

            // Current window is beautiful.
            if (ones == k) {

                String current = s.substring(left, right + 1);

                if (ans.isEmpty()
                        || current.length() < ans.length()
                        || (current.length() == ans.length()
                        && current.compareTo(ans) < 0)) {

                    ans = current;
                }
            }
        }

        return ans;
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
    string shortestBeautifulSubstring(string s, int k) {
        int left = 0;
        int ones = 0;

        string ans = "";

        for (int right = 0; right < s.size(); right++) {

            if (s[right] == '1') {
                ones++;
            }

            // More than k ones → shrink from the left.
            while (ones > k) {
                if (s[left] == '1') {
                    ones--;
                }

                left++;
            }

            // Remove unnecessary leading zeroes.
            while (left < right
                    && ones == k
                    && s[left] == '0') {

                left++;
            }

            // Current window is beautiful.
            if (ones == k) {

                string current =
                    s.substr(left, right - left + 1);

                if (ans.empty()
                        || current.size() < ans.size()
                        || (current.size() == ans.size()
                        && current < ans)) {

                    ans = current;
                }
            }
        }

        return ans;
    }
};
```

---

# Approach Comparison

| Approach                    | Time Complexity | Space Complexity | Main Idea                           |
| --------------------------- | --------------: | ---------------: | ----------------------------------- |
| Approach 1 — Brute Force    |         `O(n³)` |           `O(n)` | Check every substring               |
| Approach 2 — Sliding Window |         `O(n²)` |           `O(n)` | Maintain number of ones dynamically |

### Preferred Approach

```text
Approach 2 — Sliding Window / Two Pointers
```

is the better approach because it avoids repeatedly counting the same characters.

---

# Correctness Proof

## Lemma 1 — `ones` Is Always Correct

Whenever `right` moves over a `1`:

```text
ones++
```

Whenever `left` removes a `1`:

```text
ones--
```

Therefore, `ones` always represents the exact number of ones in the current window.

---

## Lemma 2 — The Window Is Never Kept With More Than k Ones

Whenever:

```text
ones > k
```

we move `left` forward.

If the removed character is a `1`, we decrement `ones`.

Eventually:

```text
ones <= k
```

Therefore, the window never remains with more than `k` ones.

---

## Lemma 3 — Leading Zeroes Can Be Removed Safely

When:

```text
ones == k
```

a leading zero does not contribute to the number of ones.

Therefore, removing it keeps the substring beautiful while making it shorter.

---

## Lemma 4 — Every Considered Candidate Is Beautiful

We only create a candidate when:

```text
ones == k
```

Therefore, every candidate contains exactly `k` ones.

---

## Lemma 5 — The Best Candidate Is Preserved

Whenever a candidate is found, it is compared using:

```text
1. Smaller length
2. Lexicographically smaller if lengths are equal
```

Therefore, the stored answer is always the best candidate found so far.

---

## Theorem

The algorithm examines the string using a sliding window, maintains exactly the required number of ones, removes all unnecessary leading zeroes, and compares every resulting candidate according to the problem's ordering rules.

Therefore, the final result is the shortest beautiful substring, and among all shortest beautiful substrings, it is lexicographically smallest.

---

# Edge Cases

## Case 1 — No Beautiful Substring

```text
s = "0000"
k = 1
```

There is no `1`.

Therefore:

```text
Answer = ""
```

---

## Case 2 — k = 1

```text
s = "0001000"
k = 1
```

The substring:

```text
"1"
```

contains exactly one `1`.

Therefore:

```text
Answer = "1"
```

---

## Case 3 — Consecutive Ones

```text
s = "0011100"
k = 3
```

The shortest beautiful substring is:

```text
"111"
```

---

## Case 4 — Entire String Is Beautiful

```text
s = "111"
k = 3
```

The entire string contains exactly three ones.

Therefore:

```text
Answer = "111"
```

---

## Case 5 — Multiple Candidates Have the Same Length

Suppose:

```text
"101"
"110"
```

are both beautiful and have the same length.

Since:

```text
"101" < "110"
```

the answer is:

```text
"101"
```

---

# Common Mistakes

## Mistake 1 — Returning the First Valid Substring

The first beautiful substring is not necessarily the shortest.

Always compare its length with the current answer.

---

## Mistake 2 — Forgetting Lexicographical Order

If two beautiful substrings have the same length, the lexicographically smaller one must be returned.

---

## Mistake 3 — Removing Leading Ones

Only leading zeroes can be removed when the window already contains exactly `k` ones.

Removing a `1` would make the window invalid.

---

## Mistake 4 — Counting Ones From Scratch

In the optimized solution, do not recount the entire window every time.

Maintain:

```text
ones
```

incrementally.

---

## Mistake 5 — Ignoring Leading Zeroes

Leading zeroes can make a valid substring unnecessarily long.

Always remove them when:

```text
ones == k
```

---

# Why Sliding Window Works So Well

The string is processed from left to right.

Instead of repeatedly asking:

```text
How many ones are inside this substring?
```

we maintain the answer dynamically.

When a character enters:

```text
right moves → update ones
```

When a character leaves:

```text
left moves → update ones
```

This is the fundamental sliding-window pattern:

```text
Add from right
      ↓
Maintain condition
      ↓
Remove from left
      ↓
Evaluate window
```

---

# Key Takeaways

```text
1. A beautiful substring contains exactly k ones.

2. We first minimize the substring length.

3. If multiple substrings have the same length,
   choose the lexicographically smallest one.

4. Sliding Window avoids recounting ones repeatedly.

5. Leading zeroes can be removed safely.

6. A leading one cannot be removed when it is required
   to maintain exactly k ones.

7. Two pointers move only forward.

8. The optimized implementation is significantly better
   than checking every substring from scratch.
```

---

# Final Summary

```text
                    Binary String
                         │
                         ▼
              Sliding Window
                         │
                         ▼
                 Count the ones
                         │
            ┌────────────┴────────────┐
            │                         │
         ones < k                  ones > k
            │                         │
            ▼                         ▼
       Expand right              Move left
            │                         │
            └────────────┬────────────┘
                         │
                         ▼
                     ones == k
                         │
                         ▼
              Remove leading zeroes
                         │
                         ▼
                Beautiful substring
                         │
                         ▼
               Compare with answer
                         │
                         ▼
                Shortest + smallest
```

The final solution uses:

```text
Sliding Window
      +
Two Pointers
      +
Running Count of Ones
      +
Lexicographical Comparison
```

---

# Final Complexity

```text
Approach 1 — Brute Force

Time Complexity  → O(n³)
Space Complexity → O(n)


Approach 2 — Sliding Window

Time Complexity  → O(n²)
Space Complexity → O(n)
```

---

# Final Java Solution

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;
        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }

            while (left < right
                    && ones == k
                    && s.charAt(left) == '0') {
                left++;
            }

            if (ones == k) {
                String current = s.substring(left, right + 1);

                if (ans.isEmpty()
                        || current.length() < ans.length()
                        || (current.length() == ans.length()
                        && current.compareTo(ans) < 0)) {

                    ans = current;
                }
            }
        }

        return ans;
    }
}
```

---

# Final C++ Solution

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        int left = 0;
        int ones = 0;
        string ans = "";

        for (int right = 0; right < s.size(); right++) {

            if (s[right] == '1') {
                ones++;
            }

            while (ones > k) {
                if (s[left] == '1') {
                    ones--;
                }

                left++;
            }

            while (left < right
                    && ones == k
                    && s[left] == '0') {
                left++;
            }

            if (ones == k) {
                string current =
                    s.substr(left, right - left + 1);

                if (ans.empty()
                        || current.size() < ans.size()
                        || (current.size() == ans.size()
                        && current < ans)) {

                    ans = current;
                }
            }
        }

        return ans;
    }
};
```

---

# Tags

`String` `Sliding Window` `Two Pointers` `Lexicographical Order` `Enumeration` `LeetCode` `Medium`
