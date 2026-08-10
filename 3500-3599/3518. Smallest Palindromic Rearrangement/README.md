````md
# 3518. Smallest Palindromic Rearrangement

## 🔗 Problem Link

**LeetCode 3518:** https://leetcode.com/problems/smallest-palindromic-rearrangement/

---

## 👨‍💻 Profiles

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Second):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

Given a string `s` and an integer `k`, construct the **k-th lexicographically smallest palindromic string** that can be formed by rearranging the characters of `s`.

If fewer than `k` distinct palindromic rearrangements can be formed, return:

```text
""
```

The goal is to construct the answer directly without generating every possible palindrome.

---

# 💡 Intuition

A palindrome has a very specific structure.

For example:

```text
a b c b a
```

The left half determines the right half because the right half must be its reverse.

Therefore, instead of generating complete palindromes, we only need to determine the correct ordering of the characters in the **first half**.

Once the first half is known:

```text
firstHalf + middleCharacter + reverse(firstHalf)
```

uniquely determines the complete palindrome.

---

# 🔍 Key Observation

For a palindrome:

- Every character must have an even frequency.
- If the length is odd, exactly one character can have an odd frequency.
- Each character contributes half of its frequency to the first half.

For example:

```text
s = "aabbc"
```

Frequencies:

```text
a → 2
b → 2
c → 1
```

The first-half frequencies become:

```text
a → 1
b → 1
c → 0
```

The odd character is:

```text
c
```

So every palindrome has the form:

```text
[first half] + c + [reverse of first half]
```

---

# 🚀 Approach — Frequency + Combinatorics + Greedy

The solution uses three main ideas:

```text
Frequency Counting
        +
Permutation Counting
        +
Greedy Construction
```

### Step 1 — Count Character Frequencies

Count how many times every character appears.

---

### Step 2 — Build the First-Half Frequencies

For every character:

```text
halfFreq[i] = freq[i] / 2
```

If a character has an odd frequency, save it as the middle character.

---

### Step 3 — Count Total Possible Palindromes

The number of distinct permutations of the first half is:

```text
halfLen!
----------------
f1! × f2! × ... × f26!
```

where `fi` is the frequency of each character in the first half.

Because every first-half arrangement creates exactly one palindrome, this is also the number of distinct palindromic rearrangements.

If:

```text
k > total
```

then the requested palindrome does not exist.

Return:

```text
""
```

---

# 🧮 Counting Distinct Permutations

Suppose the first half contains:

```text
a a b c
```

There are four positions, but the two `a`s are identical.

The number of distinct arrangements is:

```text
4! / 2!
```

which gives:

```text
12
```

In general:

```text
Total Permutations

=

L!

-----------------------------
f1! × f2! × ... × f26!
```

where:

- `L` = length of the first half.
- `fi` = frequency of character `i`.

The implementation uses Java's `BigInteger` so that the intermediate permutation count can safely exceed normal integer ranges.

---

# 🧠 Greedy Construction of the k-th Palindrome

After determining the total number of possible palindromes, construct the first half from left to right.

At every position:

```text
pos
```

try characters from:

```text
'a' → 'z'
```

For every candidate character:

1. Temporarily place the character.
2. Decrease its frequency.
3. Count how many permutations can be formed from the remaining characters.
4. Compare that count with `k`.

---

# 🔎 How the Greedy Choice Works

Suppose the current `k` is:

```text
k = 7
```

and choosing `'a'` would create:

```text
10
```

possible completions.

Since:

```text
7 <= 10
```

the k-th palindrome must start with `'a'`.

So we keep `'a'`.

---

If choosing `'a'` gives only:

```text
5
```

possible completions:

```text
7 > 5
```

then the desired palindrome cannot belong to the `'a'` group.

Therefore:

```text
k = k - 5
```

and we restore the frequency of `'a'`.

Then we try the next character.

---

# 📊 Lexicographical Groups

The possible palindromes can be viewed as groups according to their first character.

For example:

```text
'a' → several palindromes
'b' → several palindromes
'c' → several palindromes
...
```

Because characters are tested in alphabetical order, these groups are already arranged lexicographically.

We skip entire groups when their number of permutations is smaller than `k`.

This allows us to directly locate the k-th palindrome.

---

# 📝 Algorithm

1. Count the frequency of every character in `s`.
2. Find the possible odd-frequency character.
3. Build `halfFreq` using `freq[i] / 2`.
4. Calculate the length of the first half.
5. Count the total number of distinct permutations.
6. If `k` is larger than the total number of permutations, return `""`.
7. Construct the first half from left to right.
8. At every position, try characters from `'a'` to `'z'`.
9. Temporarily decrease the selected character's frequency.
10. Calculate the number of possible remaining permutations.
11. If the remaining count contains `k`, keep the character.
12. Otherwise subtract that count from `k` and restore the frequency.
13. Once the first half is complete, construct:

```text
firstHalf + middleCharacter + reverse(firstHalf)
```

14. Return the resulting palindrome.

---

# 🌳 Flowchart

```text
                         Start
                           │
                           ▼
                  Count Character Frequency
                           │
                           ▼
                  Build Half Frequencies
                           │
                           ▼
                  Find Middle Character
                           │
                           ▼
              Count Total Half Permutations
                           │
                           ▼
                    Is k > total?
                    ┌──────┴──────┐
                   Yes            No
                    │              │
                    ▼              ▼
                Return ""    Build First Half
                                   │
                                   ▼
                         Try 'a' → 'z'
                                   │
                                   ▼
                       Count Remaining Ways
                                   │
                            ┌──────┴──────┐
                       k <= count       k > count
                            │              │
                            ▼              ▼
                       Keep Character   k -= count
                            │              │
                            └──────┬───────┘
                                   ▼
                         Continue Next Position
                                   │
                                   ▼
                         First Half Completed
                                   │
                                   ▼
                First Half + Middle + Reverse
                                   │
                                   ▼
                              Return Answer
```

---

# 📖 Example

Consider:

```text
s = "aabb"
```

Frequencies:

```text
a → 2
b → 2
```

First-half frequencies:

```text
a → 1
b → 1
```

Possible first halves:

```text
ab
ba
```

Therefore, the two palindromes are:

```text
abba
baab
```

For:

```text
k = 1
```

the answer is:

```text
abba
```

For:

```text
k = 2
```

the answer is:

```text
baab
```

For:

```text
k = 3
```

there are only two possible palindromes, so:

```text
""
```

is returned.

---

# 🔄 Dry Run

Suppose:

```text
s = "aabbc"
```

Frequencies:

```text
a → 2
b → 2
c → 1
```

Half frequencies:

```text
a → 1
b → 1
c → 0
```

Middle character:

```text
c
```

First-half length:

```text
2
```

Possible first halves:

```text
ab
ba
```

Corresponding palindromes:

```text
abcba
bacab
```

Therefore:

```text
k = 1 → abcba

k = 2 → bacab

k > 2 → ""
```

---

# 🧩 Why We Only Build Half of the Palindrome

Once we choose:

```text
firstHalf = "abc"
```

and the middle character is:

```text
d
```

the complete palindrome is automatically:

```text
abc + d + cba
```

which gives:

```text
abcdcba
```

There is no need to independently choose the second half.

This reduces the problem from arranging all `n` characters to arranging only:

```text
n / 2
```

characters.

---

# 🧠 Why the Greedy Strategy Works

The first half completely determines the palindrome.

Since we construct the first half from left to right and always try characters in alphabetical order:

```text
'a' → 'b' → 'c' → ...
```

the resulting groups are ordered lexicographically.

For each candidate character, we know exactly how many valid completions remain.

Therefore, we can determine whether the k-th palindrome belongs to that group.

This allows us to skip entire groups instead of generating them individually.

---

# ⚠️ Important Edge Cases

### 1. `k` is larger than the number of possible palindromes

Return:

```text
""
```

---

### 2. String has odd length

Exactly one character can occupy the center.

Example:

```text
"aabbc"

       c
       ↓
abcba
```

---

### 3. String has even length

There is no middle character.

Example:

```text
"aabb"

abba
```

---

### 4. Repeated Characters

Repeated characters must not create duplicate permutations.

Therefore, we divide by each character's factorial:

```text
L! / (f1! × f2! × ...)
```

---

# ⏱️ Complexity Analysis

Let:

```text
n = s.length()
```

and:

```text
L = n / 2
```

The algorithm constructs `L` characters.

For every position, it may try up to `26` characters and calculate permutation counts.

The implementation uses `BigInteger` for exact combinatorial calculations.

The practical complexity is dominated by the repeated permutation-count calculations and factorial operations.

### Space Complexity

```text
O(26 + L)
```

for frequency arrays, the constructed first half, and supporting `BigInteger` values.

---

# 🎯 Key Takeaways

- ✅ A palindrome is completely determined by its first half and optional middle character.
- ✅ Count character frequencies first.
- ✅ Use half of each character's frequency to construct the first half.
- ✅ Count distinct permutations using multinomial combinations.
- ✅ Use `BigInteger` to avoid overflow during permutation counting.
- ✅ Construct the answer greedily from `'a'` to `'z'`.
- ✅ Skip groups of permutations when they contain fewer than `k` possibilities.
- ✅ Reflect the first half to construct the complete palindrome.
- ✅ If fewer than `k` palindromes exist, return `""`.
````

# Java Solution

## Approach 1 — Greedy + Combinatorics + Frequency Counting

```java
//Approach-1 (Greedy + Combinatorics + Frequency Counting)
//T.C : O(26 * n * n)
//S.C : O(n)

class Solution {

    public String smallestPalindrome(
        String s,
        int k
    ) {

        int n =
            s.length();

        int[] freq =
            new int[26];

        for (
            char c : s.toCharArray()
        ) {

            freq[c - 'a']++;
        }

        int midChar =
            -1;

        for (
            int i = 0;
            i < 26;
            i++
        ) {

            if (
                freq[i] % 2 != 0
            ) {

                midChar =
                    i;
            }
        }

        int[] halfFreq =
            new int[26];

        int halfLen =
            0;

        for (
            int i = 0;
            i < 26;
            i++
        ) {

            halfFreq[i] =
                freq[i] / 2;

            halfLen +=
                halfFreq[i];
        }

        long CAP =
            3_000_000_000L;

        long total =
            countWays(
                halfFreq,
                halfLen,
                CAP
            );

        if (
            total < k
        ) {

            return "";
        }

        StringBuilder firstHalf =
            new StringBuilder();

        long leftK =
            k;

        int leftLen =
            halfLen;

        for (
            int pos = 0;
            pos < halfLen;
            pos++
        ) {

            for (
                int c = 0;
                c < 26;
                c++
            ) {

                if (
                    halfFreq[c] == 0
                ) {

                    continue;
                }

                halfFreq[c]--;

                long ways =
                    countWays(
                        halfFreq,
                        leftLen - 1,
                        CAP
                    );

                if (
                    leftK <= ways
                ) {

                    firstHalf.append(
                        (char)('a' + c)
                    );

                    break;

                } else {

                    leftK -=
                        ways;

                    halfFreq[c]++;
                }
            }

            leftLen--;
        }

        StringBuilder result =
            new StringBuilder(
                firstHalf
            );

        if (
            midChar != -1
        ) {

            result.append(
                (char)('a' + midChar)
            );
        }

        result.append(
            firstHalf.reverse()
        );

        return result.toString();
    }

    private long countWays(
        int[] freq,
        int total,
        long cap
    ) {

        long ways =
            1;

        int rem =
            total;

        for (
            int i = 0;
            i < 26 &&
            ways <= cap;
            i++
        ) {

            int f =
                freq[i];

            long comb =
                1;

            for (
                int j = 1;
                j <= f;
                j++
            ) {

                comb =
                    comb *
                    (rem - f + j) /
                    j;

                if (
                    comb > cap
                ) {

                    comb =
                        cap + 1;

                    break;
                }
            }

            ways *=
                comb;

            if (
                ways > cap
            ) {

                ways =
                    cap + 1;

                break;
            }

            rem -=
                f;
        }

        return ways;
    }
}
```

---

# C++ Solution

## Approach 1 — Greedy + Combinatorics + Frequency Counting

```cpp
//Approach-1 (Greedy + Combinatorics + Frequency Counting)
//T.C : O(26 * n * n)
//S.C : O(n)

class Solution {
public:

    string smallestPalindrome(
        string s,
        int k
    ) {

        int n =
            s.length();

        vector<int> freq(26, 0);

        for (
            char c : s
        ) {

            freq[c - 'a']++;
        }

        int midChar =
            -1;

        for (
            int i = 0;
            i < 26;
            i++
        ) {

            if (
                freq[i] % 2 != 0
            ) {

                midChar =
                    i;
            }
        }

        vector<int> halfFreq(
            26,
            0
        );

        int halfLen =
            0;

        for (
            int i = 0;
            i < 26;
            i++
        ) {

            halfFreq[i] =
                freq[i] / 2;

            halfLen +=
                halfFreq[i];
        }

        long long CAP =
            3000000000LL;

        long long total =
            countWays(
                halfFreq,
                halfLen,
                CAP
            );

        if (
            total < k
        ) {

            return "";
        }

        string firstHalf;

        long long leftK =
            k;

        int leftLen =
            halfLen;

        for (
            int pos = 0;
            pos < halfLen;
            pos++
        ) {

            for (
                int c = 0;
                c < 26;
                c++
            ) {

                if (
                    halfFreq[c] == 0
                ) {

                    continue;
                }

                halfFreq[c]--;

                long long ways =
                    countWays(
                        halfFreq,
                        leftLen - 1,
                        CAP
                    );

                if (
                    leftK <= ways
                ) {

                    firstHalf +=
                        char('a' + c);

                    break;

                } else {

                    leftK -=
                        ways;

                    halfFreq[c]++;
                }
            }

            leftLen--;
        }

        string result =
            firstHalf;

        if (
            midChar != -1
        ) {

            result +=
                char('a' + midChar);
        }

        reverse(
            firstHalf.begin(),
            firstHalf.end()
        );

        result +=
            firstHalf;

        return result;
    }

private:

    long long countWays(
        vector<int>& freq,
        int total,
        long long cap
    ) {

        long long ways =
            1;

        int rem =
            total;

        for (
            int i = 0;
            i < 26 &&
            ways <= cap;
            i++
        ) {

            int f =
                freq[i];

            long long comb =
                1;

            for (
                int j = 1;
                j <= f;
                j++
            ) {

                comb =
                    comb *
                    (rem - f + j) /
                    j;

                if (
                    comb > cap
                ) {

                    comb =
                        cap + 1;

                    break;
                }
            }

            ways *=
                comb;

            if (
                ways > cap
            ) {

                ways =
                    cap + 1;

                break;
            }

            rem -=
                f;
        }

        return ways;
    }
};
```

---

# 🔎 How the Greedy Construction Works

At every position of the first half, characters are tested in alphabetical order:

```text
'a' → 'b' → 'c' → ... → 'z'
```

For every candidate character, we temporarily decrease its frequency and calculate:

```text
ways = number of possible completions
```

Then:

### If

```text
k <= ways
```

the required palindrome belongs to this group.

We keep the character.

### Otherwise

```text
k > ways
```

the entire group can be skipped.

So:

```text
k = k - ways
```

and the character frequency is restored.

This lets us jump directly to the k-th palindrome instead of generating all palindromes.

---

# 🧮 Why `CAP` Is Used

The number of permutations can become extremely large.

We only need to know whether the number of possibilities is:

```text
less than k
```

or:

```text
at least k
```

Therefore, there is no need to calculate extremely large values exactly.

The implementation uses:

```text
CAP = 3,000,000,000
```

and stops counting once the number of possibilities exceeds this cap.

This keeps the combinatorial calculations bounded while preserving the information required for selecting the k-th result.

---

# 🧩 Building the Final Palindrome

After constructing:

```text
firstHalf
```

the palindrome is formed as:

```text
firstHalf
+
middle character
+
reverse(firstHalf)
```

For example:

```text
firstHalf = "abc"

middle = "d"
```

then:

```text
abc + d + cba
```

produces:

```text
abcdcba
```

If the original string has even length, there is no middle character.

---

# ⏱️ Complexity

Let:

```text
n = s.length()
```

The first half has approximately:

```text
n / 2
```

characters.

For every position, up to `26` characters may be tested, and `countWays()` processes the frequency array.

```text
Time Complexity  : O(26 × n × n)

Space Complexity : O(n)
```

The frequency arrays use constant `26` space, while the constructed palindrome requires `O(n)` space.

---

# 🎯 Final Takeaways

- ✅ Count the frequency of every character.
- ✅ Use half of every frequency to construct the first half.
- ✅ Identify the possible middle character.
- ✅ Count the number of possible half permutations.
- ✅ Use a greedy lexicographical construction.
- ✅ Calculate the number of completions for every candidate character.
- ✅ Skip entire groups when their size is smaller than `k`.
- ✅ Use a cap to prevent unnecessarily large combinatorial values.
- ✅ Mirror the first half to obtain the complete palindrome.
- ✅ Return `""` when fewer than `k` valid palindromes exist.

### Final Complexity

```text
Time Complexity  : O(26 × n²)

Space Complexity : O(n)
```
