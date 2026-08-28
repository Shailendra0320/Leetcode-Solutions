# Profiles

## GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

---

## LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/Shailu03/

🚀 Alternate Profile:

https://leetcode.com/u/ShailendraLeetcode03/

---

# 3734. Lexicographically Smallest Palindromic Permutation Greater Than Target

## Problem Statement

You are given two strings `s` and `target`, both of length `n`, consisting of lowercase English letters.

Return the **lexicographically smallest string** that:

1. Is a palindromic permutation of `s`.
2. Is strictly greater than `target`.

If no such permutation exists, return:

```text
""
```

A palindrome reads the same from left to right and right to left.

The constraints are:

```text
1 <= n == s.length == target.length <= 300
```

Both strings contain only lowercase English letters. citeturn0search0

---

# Examples

## Example 1

```text
Input:
s = "baba"
target = "abba"

Output:
"baab"
```

The palindromic permutations are:

```text
abba
baab
```

`"abba"` is equal to `target`, so it is not strictly greater.

Therefore:

```text
baab > abba
```

Answer:

```text
"baab"
```

## Example 2

```text
Input:
s = "baba"
target = "bbaa"

Output:
""
```

Both possible palindromes:

```text
abba
baab
```

are smaller than `"bbaa"`.

Therefore no valid answer exists.

## Example 3

```text
Input:
s = "abc"
target = "abb"

Output:
""
```

Every character occurs once:

```text
a → 1
b → 1
c → 1
```

There are three odd frequencies, so no palindromic permutation exists.

## Example 4

```text
Input:
s = "aac"
target = "abb"

Output:
"aca"
```

The only palindromic permutation is:

```text
aca
```

and:

```text
aca > abb
```

The examples and constraints above match the official LeetCode problem. citeturn0search0

---

# Key Observation

A string can be rearranged into a palindrome only when **at most one character has an odd frequency**.

For a valid palindrome:

```text
Left Half + Middle + Reverse(Left Half)
```

So instead of arranging all `n` characters, we only need to construct the left half.

For example:

```text
s = "baba"
```

Counts:

```text
a → 2
b → 2
```

Left half:

```text
"ab"
```

Possible palindromes are created by arranging that half:

```text
ab + ba = abba
ba + ab = baab
```

The right half is completely determined.

---

# Approach 1 — Brute Force

## Intuition

The most direct solution is to generate every permutation of `s`.

For each permutation:

```text
1. Check whether it is a palindrome.
2. Check whether it is greater than target.
3. Keep the smallest valid permutation.
```

This is easy to understand, but it is not feasible for the given constraints.

---

## Algorithm

```text
1. Generate every permutation of s.

2. For each permutation:
       if it is a palindrome
       AND it is greater than target:
           update the answer.

3. Return the smallest valid permutation.

4. If no valid permutation exists:
       return "".
```

---

## Complexity Analysis

There can be up to:

```text
O(n!)
```

permutations.

Checking a permutation takes:

```text
O(n)
```

time.

Therefore:

```text
Time Complexity  → O(n! × n)
Space Complexity → O(n)
```

This is impossible for:

```text
n <= 300
```

---

## Java Code

```java
class Solution {
    private String answer = "";

    public String lexPalindromicPermutation(String s, String target) {
        boolean[] used = new boolean[s.length()];
        backtrack(s, target, used, new StringBuilder());
        return answer;
    }

    private void backtrack(
            String s,
            String target,
            boolean[] used,
            StringBuilder current) {

        if (current.length() == s.length()) {
            String candidate = current.toString();

            if (isPalindrome(candidate)
                    && candidate.compareTo(target) > 0
                    && (answer.isEmpty()
                    || candidate.compareTo(answer) < 0)) {
                answer = candidate;
            }

            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            current.append(s.charAt(i));

            backtrack(s, target, used, current);

            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

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
    string answer = "";

public:
    string lexPalindromicPermutation(string s, string target) {
        vector<bool> used(s.size(), false);
        string current;

        backtrack(s, target, used, current);

        return answer;
    }

private:
    void backtrack(
        const string& s,
        const string& target,
        vector<bool>& used,
        string& current) {

        if (current.size() == s.size()) {
            if (isPalindrome(current)
                && current > target
                && (answer.empty() || current < answer)) {
                answer = current;
            }

            return;
        }

        for (int i = 0; i < s.size(); i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            current.push_back(s[i]);

            backtrack(s, target, used, current);

            current.pop_back();
            used[i] = false;
        }
    }

    bool isPalindrome(const string& s) {
        int left = 0;
        int right = s.size() - 1;

        while (left < right) {
            if (s[left] != s[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
};
```

---

# Approach 2 — Greedy + Frequency Counting

## Intuition

We can avoid generating permutations completely.

A palindrome is determined by:

```text
Left Half
+
Middle Character
+
Reverse(Left Half)
```

Therefore, we only need to decide the left half.

The goal is to construct the smallest palindrome that is strictly greater than `target`.

The greedy rules are:

```text
1. Keep the prefix equal to target as long as possible.
2. Make the first difference as far right as possible.
3. At that position, choose the smallest character
   strictly greater than target[i].
4. Fill the remaining left half with the smallest
   available characters.
5. Mirror the left half.
```

This is similar to finding the next lexicographically greater permutation, but with the additional palindrome constraint.

---

# Why We Try the Rightmost Position First

Suppose two valid answers are:

```text
baab
caaa
```

Both may be greater than the target.

But:

```text
baab < caaa
```

because the first character is smaller.

Therefore, we should delay the first difference from `target` as much as possible.

So we try the pivot from:

```text
right → left
```

---

# Why Choose the Smallest Larger Character?

Suppose:

```text
target[i] = b
```

and available characters are:

```text
c, d, e
```

All of them make the result larger.

But the smallest choice is:

```text
c
```

Therefore:

```text
choose smallest character > target[i]
```

---

# Why Sort the Remaining Half?

Once we choose:

```text
answer[i] > target[i]
```

the final palindrome is already strictly greater than `target`.

The remaining characters no longer affect whether the result is greater.

Therefore, to minimize the answer, we place the remaining characters in ascending order.

---

# Algorithm

```text
1. Count the frequency of every character in s.

2. Count how many characters have odd frequency.

3. If more than one character has an odd frequency:
       return "".

4. Build halfCount[c] = count[c] / 2.

5. Find the middle character if n is odd.

6. Try every pivot in the left half from right to left.

7. For a pivot i:
       - Try to use target[0 ... i-1] as the prefix.
       - If the prefix cannot be formed, skip this pivot.
       - Find the smallest available character > target[i].

8. If such a character exists:
       - Keep target[0 ... i-1].
       - Place the larger character at i.
       - Fill the rest of the left half in ascending order.
       - Add the middle character if needed.
       - Mirror the left half.
       - Return the result.

9. If no pivot works:
       return "".
```

---

# Detailed Dry Run — Example 1

```text
s = "baba"
target = "abba"
```

Character frequencies:

```text
a → 2
b → 2
```

Therefore:

```text
halfCount:
a → 1
b → 1
```

There is no middle character.

Possible palindromes:

```text
abba
baab
```

---

## Try to Keep Target

Start with:

```text
target = abba
```

The first half is:

```text
ab
```

Using this half gives:

```text
abba
```

But:

```text
abba == target
```

so it is invalid.

---

## Make the Rightmost Possible Increase

At the left-half position containing:

```text
a
```

the smallest available character greater than `a` is:

```text
b
```

Choose:

```text
b
```

The remaining character is:

```text
a
```

So:

```text
left half = ba
```

Mirror it:

```text
ba + ab
```

Final palindrome:

```text
baab
```

Since:

```text
baab > abba
```

the answer is:

```text
"baab"
```

---

# Detailed Dry Run — Example 2

```text
s = "baba"
target = "bbaa"
```

Possible palindromes:

```text
abba
baab
```

Compare them with target:

```text
abba < bbaa
baab < bbaa
```

There is no valid palindrome greater than the target.

Therefore:

```text
Answer = ""
```

---

# Detailed Dry Run — Example 3

```text
s = "abc"
target = "abb"
```

Frequency:

```text
a → 1
b → 1
c → 1
```

Odd-frequency characters:

```text
a
b
c
```

There are three.

A palindrome can have at most one odd-frequency character.

Therefore:

```text
Answer = ""
```

---

# Detailed Dry Run — Example 4

```text
s = "aac"
target = "abb"
```

Frequency:

```text
a → 2
c → 1
```

Left half:

```text
a
```

Middle:

```text
c
```

Construct:

```text
a + c + a
```

which gives:

```text
aca
```

Compare:

```text
aca
abb
```

At index `1`:

```text
c > b
```

Therefore:

```text
aca > abb
```

Answer:

```text
"aca"
```

---

# Correctness Proof

## Lemma 1 — Palindrome Feasibility

A palindrome can have at most one character with an odd frequency.

Therefore, if more than one character has an odd frequency, no valid palindrome exists.

---

## Lemma 2 — Every Constructed Result Is a Permutation of `s`

For every character, exactly half of its copies are placed in the left half.

The right half is the mirror of the left half.

If the length is odd, the one remaining odd-frequency character is placed in the middle.

Therefore, every constructed result uses exactly the characters of `s`.

---

## Lemma 3 — Every Constructed Result Is a Palindrome

The result is constructed as:

```text
leftHalf + middle + reverse(leftHalf)
```

Therefore, it is always a palindrome.

---

## Lemma 4 — The Pivot Makes the Result Greater

At the pivot, we choose:

```text
character > target[pivot]
```

while keeping all earlier positions equal.

Therefore, the first differing position is the pivot, and:

```text
answer[pivot] > target[pivot]
```

So:

```text
answer > target
```

---

## Lemma 5 — The Pivot Character Is Minimal

We choose the smallest available character greater than:

```text
target[pivot]
```

A smaller character would not make the result greater.

A larger character would produce a larger answer.

Therefore, the pivot choice is optimal.

---

## Lemma 6 — The Rightmost Pivot Is Optimal

A later pivot preserves a longer prefix equal to `target`.

A longer equal prefix always produces a lexicographically smaller result than changing an earlier position.

Therefore, the rightmost valid pivot gives the smallest possible answer.

---

## Lemma 7 — The Remaining Half Is Minimal

After the pivot, the answer is already greater than `target`.

Therefore, the remaining characters should be placed in ascending order.

This produces the smallest possible suffix.

---

## Theorem

The algorithm constructs only valid palindromic permutations of `s`.

Among all valid answers, it:

```text
1. Delays the first difference as far right as possible.
2. Makes the smallest possible increase at that position.
3. Uses the smallest possible remaining suffix.
```

Therefore, the returned result is the **lexicographically smallest palindromic permutation of `s` strictly greater than `target`**.

If no pivot works, no valid answer exists.

---

# Complexity Analysis

Let:

```text
n = s.length()
```

There are at most:

```text
n / 2
```

positions in the left half.

For each position, we may test up to:

```text
26
```

characters.

Constructing a candidate palindrome takes:

```text
O(n)
```

time.

Therefore:

```text
Time Complexity  → O(26 × n²)
                 → O(n²)

Space Complexity → O(n)
```

The alphabet contains only 26 lowercase English letters, so 26 is a constant.

---

# Complexity Comparison

| Approach                 | Time Complexity | Space Complexity | Practical |
| ------------------------ | --------------: | ---------------: | --------- |
| Brute Force              |     `O(n! × n)` |           `O(n)` | ❌        |
| Greedy + Frequency Count |    `O(26 × n²)` |           `O(n)` | ✅        |

---

# Java Code — Optimal Approach

```java
class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        // Required variable from the problem.
        String calendrix = s + "|" + target;

        int n = s.length();

        int[] count = new int[26];

        // Count all characters.
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // Check whether a palindrome is possible.
        int odd = 0;
        int middle = -1;

        for (int c = 0; c < 26; c++) {
            if ((count[c] & 1) == 1) {
                odd++;
                middle = c;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLength = n / 2;

        /*
         * Try the pivot from right to left.
         */
        for (int pivot = halfLength - 1;
             pivot >= 0;
             pivot--) {

            int[] available = new int[26];

            for (int c = 0; c < 26; c++) {
                available[c] = count[c] / 2;
            }

            // Match target[0 ... pivot - 1].
            boolean possible = true;

            for (int i = 0; i < pivot; i++) {
                int idx = target.charAt(i) - 'a';

                available[idx]--;

                if (available[idx] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int targetChar = target.charAt(pivot) - 'a';

            // Find the smallest character > target[pivot].
            for (int bigger = targetChar + 1;
                 bigger < 26;
                 bigger++) {

                if (available[bigger] == 0) {
                    continue;
                }

                StringBuilder left = new StringBuilder();

                // Keep the prefix equal to target.
                left.append(target, 0, pivot);

                // First position where we become greater.
                left.append((char) ('a' + bigger));

                available[bigger]--;

                // Fill remaining half in ascending order.
                for (int c = 0; c < 26; c++) {
                    while (available[c] > 0) {
                        left.append((char) ('a' + c));
                        available[c]--;
                    }
                }

                // Build the palindrome.
                StringBuilder answer = new StringBuilder();

                answer.append(left);

                if (middle != -1) {
                    answer.append((char) ('a' + middle));
                }

                for (int i = left.length() - 1; i >= 0; i--) {
                    answer.append(left.charAt(i));
                }

                String result = answer.toString();

                if (result.compareTo(target) > 0) {
                    return result;
                }

                available[bigger]++;
            }
        }

        /*
         * Special case:
         * The entire left half may match target's left half.
         * Then the middle/right side decides the comparison.
         */
        int[] exact = new int[26];

        for (int c = 0; c < 26; c++) {
            exact[c] = count[c] / 2;
        }

        boolean possible = true;

        for (int i = 0; i < halfLength; i++) {
            int idx = target.charAt(i) - 'a';

            exact[idx]--;

            if (exact[idx] < 0) {
                possible = false;
                break;
            }
        }

        if (possible) {
            StringBuilder left = new StringBuilder(
                    target.substring(0, halfLength)
            );

            StringBuilder answer = new StringBuilder(left);

            if (middle != -1) {
                answer.append((char) ('a' + middle));
            }

            for (int i = left.length() - 1; i >= 0; i--) {
                answer.append(left.charAt(i));
            }

            String result = answer.toString();

            if (result.compareTo(target) > 0) {
                return result;
            }
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
    string lexPalindromicPermutation(string s, string target) {

        // Required variable from the problem.
        string calendrix = s + "|" + target;

        int n = s.size();

        vector<int> count(26, 0);

        // Count all characters.
        for (char ch : s) {
            count[ch - 'a']++;
        }

        // Check palindrome feasibility.
        int odd = 0;
        int middle = -1;

        for (int c = 0; c < 26; c++) {
            if (count[c] % 2 == 1) {
                odd++;
                middle = c;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLength = n / 2;

        // Try the pivot from right to left.
        for (int pivot = halfLength - 1;
             pivot >= 0;
             pivot--) {

            vector<int> available(26);

            for (int c = 0; c < 26; c++) {
                available[c] = count[c] / 2;
            }

            // Match target[0 ... pivot - 1].
            bool possible = true;

            for (int i = 0; i < pivot; i++) {
                int idx = target[i] - 'a';

                available[idx]--;

                if (available[idx] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int targetChar = target[pivot] - 'a';

            // Find the smallest character > target[pivot].
            for (int bigger = targetChar + 1;
                 bigger < 26;
                 bigger++) {

                if (available[bigger] == 0) {
                    continue;
                }

                string left = target.substr(0, pivot);

                // Make the first difference larger.
                left += char('a' + bigger);

                available[bigger]--;

                // Fill remaining half in ascending order.
                for (int c = 0; c < 26; c++) {
                    while (available[c] > 0) {
                        left += char('a' + c);
                        available[c]--;
                    }
                }

                // Construct palindrome.
                string answer = left;

                if (middle != -1) {
                    answer += char('a' + middle);
                }

                for (int i = (int)left.size() - 1;
                     i >= 0;
                     i--) {
                    answer += left[i];
                }

                if (answer > target) {
                    return answer;
                }

                available[bigger]++;
            }
        }

        // Check whether the entire left half can equal target.
        vector<int> exact(26);

        for (int c = 0; c < 26; c++) {
            exact[c] = count[c] / 2;
        }

        bool possible = true;

        for (int i = 0; i < halfLength; i++) {
            int idx = target[i] - 'a';

            exact[idx]--;

            if (exact[idx] < 0) {
                possible = false;
                break;
            }
        }

        if (possible) {
            string left = target.substr(0, halfLength);

            string answer = left;

            if (middle != -1) {
                answer += char('a' + middle);
            }

            for (int i = (int)left.size() - 1;
                 i >= 0;
                 i--) {
                answer += left[i];
            }

            if (answer > target) {
                return answer;
            }
        }

        return "";
    }
};
```

---

# Code Walkthrough

## 1. Count Character Frequencies

```java
int[] count = new int[26];

for (char ch : s.toCharArray()) {
    count[ch - 'a']++;
}
```

This gives us the number of copies of every character.

---

## 2. Check Whether a Palindrome Is Possible

```java
if ((count[c] & 1) == 1) {
    odd++;
}
```

If more than one character has an odd frequency:

```text
odd > 1
```

then no palindrome can be formed.

---

## 3. Build the Left-Half Inventory

```java
available[c] = count[c] / 2;
```

Only half of every even count is needed on the left.

The other half is automatically used on the right.

---

## 4. Try the Pivot From Right to Left

```java
for (int pivot = halfLength - 1; pivot >= 0; pivot--)
```

This maximizes the common prefix with `target`.

A later difference produces a smaller lexicographical answer.

---

## 5. Match the Prefix

```java
target[0 ... pivot - 1]
```

is copied as long as the required characters exist.

If a character is unavailable, that pivot cannot work.

---

## 6. Choose the Smallest Larger Character

```java
for (int bigger = targetChar + 1; bigger < 26; bigger++)
```

The first available character is the smallest possible increase.

---

## 7. Fill the Remaining Half

All unused characters are placed in ascending order.

This gives the smallest possible suffix.

---

## 8. Mirror the Half

The final palindrome is:

```text
leftHalf
+
middle
+
reverse(leftHalf)
```

---

# Edge Cases

## Case 1 — No Palindromic Permutation

```text
s = "abc"
target = "abb"
```

Answer:

```text
""
```

---

## Case 2 — Target Itself Is a Palindrome

```text
s = "baba"
target = "abba"
```

We cannot return `"abba"` because the answer must be **strictly greater**.

The next valid palindrome is:

```text
"baab"
```

---

## Case 3 — No Greater Palindrome Exists

```text
s = "baba"
target = "bbaa"
```

Answer:

```text
""
```

---

## Case 4 — Odd Length

```text
s = "aac"
target = "abb"
```

Middle character:

```text
c
```

Result:

```text
aca
```

---

# Common Mistakes

## Mistake 1 — Forgetting the Palindrome Condition

Not every permutation of `s` is valid.

The final string must read the same in both directions.

---

## Mistake 2 — Ignoring Odd Frequencies

More than one odd frequency means a palindrome is impossible.

---

## Mistake 3 — Changing the Prefix Too Early

The first difference from `target` should be as far right as possible.

---

## Mistake 4 — Choosing a Larger Character Than Necessary

Always choose:

```text
smallest available character > target[i]
```

---

## Mistake 5 — Forgetting the Middle Character

For odd-length strings, the unpaired character belongs in the center.

---

## Mistake 6 — Forgetting to Mirror the Left Half

Once the left half is selected, the right half is forced.

---

# Key Takeaways

```text
1. Check palindrome feasibility using character frequencies.

2. A palindrome is completely determined by its left half
   and optional middle character.

3. Try to preserve target's prefix for as long as possible.

4. Search the pivot from right to left.

5. At the pivot, choose the smallest available character
   strictly greater than target[pivot].

6. Fill the remaining half in ascending order.

7. Add the middle character when necessary.

8. Mirror the left half.

9. The result must be strictly greater than target.
```

---

# One-Line Insight

> **Keep the longest possible prefix equal to `target`, increase the rightmost possible position by the smallest amount, fill the remaining half minimally, and mirror it to form the palindrome.**

---

# Final Complexity

```text
Approach 1 — Brute Force

Time Complexity  → O(n! × n)
Space Complexity → O(n)


Approach 2 — Greedy + Frequency Counting

Time Complexity  → O(26 × n²)
Space Complexity → O(n)
```

Since the alphabet contains only 26 lowercase English letters:

```text
O(26 × n²) = O(n²)
```

---

# Tags

`String` `Greedy` `Backtracking` `Frequency Count` `Palindrome` `Permutation` `Lexicographical Order` `Two Pointers` `LeetCode` `Hard`
