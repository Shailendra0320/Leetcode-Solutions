# 3483. Unique 3-Digit Even Numbers

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# Problem Statement

You are given an integer array `digits`.

Count the number of **distinct 3-digit even numbers** that can be formed using these digits.

Rules:

- The number must have exactly 3 digits.
- The first digit cannot be `0`.
- The last digit must be even.
- Each input position can be used at most once for one number.
- Repeated digit values can be used multiple times if multiple copies exist.
- The final answer counts **distinct numbers**, not distinct index selections.

Constraints:

```text
3 <= digits.length <= 10
0 <= digits[i] <= 9
```

These constraints make direct enumeration practical. citeturn780718search0turn780718search4

---

# What Is the Question Really Asking?

We need to fill:

```text
+----------+-------+-------+
| Hundreds | Tens  | Units |
+----------+-------+-------+
```

with three different input positions.

The rules become:

```text
Hundreds:
    digit != 0

Tens:
    any available digit

Units:
    digit must be even
    -> 0, 2, 4, 6, 8
```

Then:

```text
number = hundreds * 100
       + tens * 10
       + units
```

Finally, store the number in a `HashSet` so duplicate constructions count only once.

---

# Examples

## Example 1

```text
digits = [1,2,3,4]
answer = 12
```

The valid numbers are:

```text
124 132 134 142
214 234
312 314 324 342
412 432
```

`222` cannot be formed because there is only one copy of `2`. citeturn780718search0

---

## Example 2

```text
digits = [0,2,2]
answer = 2
```

Only:

```text
202
220
```

can be formed.

The two copies of `2` can both be used because the input contains two separate positions. citeturn780718search0

---

## Example 3

```text
digits = [6,6,6]
answer = 1
```

Many index permutations produce `666`, but only one **distinct number** exists.

```text
666
```

citeturn780718search0

---

## Example 4

```text
digits = [1,3,5]
answer = 0
```

There is no even digit for the units position. citeturn780718search0

---

# Core Observation

The input size is tiny:

```text
n <= 10
```

There are at most:

```text
n^3 <= 10^3 = 1000
```

ordered triples of positions.

That is extremely small.

Therefore, instead of searching for a clever combinatorial formula, we can simply enumerate all triples:

```text
i = hundreds
j = tens
k = units
```

and validate them.

This is the key reason the brute-force-looking solution is actually the intended practical approach. citeturn780718search0turn780718search3

---

# Position Constraints

For:

```text
digits = [1,2,3,4]
          0 1 2 3
```

choose:

```text
i, j, k
```

with:

```text
i != j
i != k
j != k
```

Then:

```text
digits[i] != 0
digits[k] % 2 == 0
```

Valid candidate:

```text
digits[i] * 100
+
digits[j] * 10
+
digits[k]
```

---

# Main Architecture

```text
                         digits[]
                            |
                            v
                    Choose index i
                    (hundreds digit)
                            |
                    digits[i] != 0
                            |
                            v
                    Choose index j
                      (tens digit)
                            |
                         j != i
                            |
                            v
                    Choose index k
                      (units digit)
                            |
                  k != i && k != j
                            |
                            v
                   digits[k] is even?
                       /          \
                     NO            YES
                     |              |
                   skip        Build number
                                     |
                                     v
                                  HashSet
                                     |
                                     v
                               final set.size()
```

---

# Why a HashSet Is Necessary

Suppose:

```text
digits = [6,6,6]
```

These different position selections all create:

```text
666
```

For example:

```text
(0,1,2)
(0,2,1)
(1,0,2)
(1,2,0)
(2,0,1)
(2,1,0)
```

But the answer should be:

```text
1
```

not:

```text
6
```

So we store the constructed integer:

```text
set.add(number)
```

and repeated insertions are ignored.

---

# Approach 1: Triple Enumeration + HashSet

## Steps

For each `i`:

```text
if digits[i] == 0:
    skip
```

For each `j`:

```text
if j == i:
    skip
```

For each `k`:

```text
if k == i or k == j:
    skip

if digits[k] is odd:
    skip
```

Then:

```text
number = digits[i] * 100
       + digits[j] * 10
       + digits[k]
```

Insert it into the set.

Finally:

```text
return set.size()
```

---

# Dry Run: [0,2,2]

Input:

```text
index: 0 1 2
digit: 0 2 2
```

### Construct 202

Choose:

```text
hundreds = index 1 -> 2
tens     = index 0 -> 0
units    = index 2 -> 2
```

So:

```text
202
```

Set:

```text
{202}
```

### Construct 220

Choose:

```text
hundreds = index 1 -> 2
tens     = index 2 -> 2
units    = index 0 -> 0
```

So:

```text
220
```

Set:

```text
{202, 220}
```

Final:

```text
answer = 2
```

---

# Dry Run: [1,2,3,4]

Even digits available for the units position:

```text
2, 4
```

### Units = 2

Possible numbers:

```text
132
142
312
342
412
432
```

Count:

```text
6
```

### Units = 4

Possible numbers:

```text
124
134
214
234
314
324
```

Count:

```text
6
```

Total:

```text
12
```

---

# Java Code

```java
//Approach-1 (Three-Level Enumeration + HashSet)
//T.C : O(n^3)
//S.C : O(n^3) worst case

class Solution {

    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {

            // Hundreds digit cannot be zero.
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) {
                        continue;
                    }

                    // Units digit must be even.
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number =
                        digits[i] * 100 +
                        digits[j] * 10 +
                        digits[k];

                    set.add(number);
                }
            }
        }

        return set.size();
    }
}
```

---

# C++ Code

```cpp
//Approach-1 (Three-Level Enumeration + HashSet)
//T.C : O(n^3)
//S.C : O(n^3) worst case

class Solution {
public:

    int totalNumbers(vector<int>& digits) {
        unordered_set<int> set;

        int n = digits.size();

        for (int i = 0; i < n; i++) {

            // Hundreds digit cannot be zero.
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) {
                        continue;
                    }

                    // Units digit must be even.
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number =
                        digits[i] * 100 +
                        digits[j] * 10 +
                        digits[k];

                    set.insert(number);
                }
            }
        }

        return set.size();
    }
};
```

This enumeration + set approach is the standard reference solution pattern for the stated constraints. citeturn780718search0turn780718search4

---

# Approach 1 Complexity

Let:

```text
n = digits.length
```

We have three nested loops:

```text
O(n) × O(n) × O(n)
```

Therefore:

```text
T.C : O(n^3)
```

Since:

```text
n <= 10
```

at most around:

```text
1000
```

position triples are considered.

The set can store only a small number of distinct 3-digit values; `O(n^3)` is a safe loose bound for the generated candidates. citeturn780718search0

---

# Approach 2: Backtracking + Frequency Array

A second clean way is to build the number recursively.

First create:

```text
freq[10]
```

where:

```text
freq[d] = number of copies of digit d
```

For each position:

```text
position 0 -> hundreds
position 1 -> tens
position 2 -> units
```

choose an available digit, decrease its frequency, recurse, then restore it.

---

# Backtracking State

```text
(position, currentNumber)
```

The recursive tree looks like:

```text
                         Start
                           |
                  Choose hundreds
                    /     |     \
                   1      2      3 ...
                  /       |       \
             choose     choose    choose
               tens       tens      tens
                |           |         |
            choose units ...        ...
                |
             complete
                |
             count/set
```

Because there are only 10 possible digit values and exactly 3 positions, the search is tiny.

---

# Position Rules in Backtracking

### Position 0

```text
digit != 0
```

### Position 1

```text
any available digit
```

### Position 2

```text
digit % 2 == 0
```

The frequency array ensures that no copy is used more times than it exists.

---

# Java Code

```java
//Approach-2 (Backtracking + Frequency Array)
//T.C : O(10^3)
//S.C : O(10)

class Solution2 {

    private final int[] freq = new int[10];
    private final Set<Integer> set = new HashSet<>();

    public int totalNumbers(int[] digits) {

        for (int digit : digits) {
            freq[digit]++;
        }

        backtrack(0, 0);

        return set.size();
    }

    private void backtrack(int position, int number) {

        if (position == 3) {
            set.add(number);
            return;
        }

        for (int digit = 0; digit <= 9; digit++) {

            if (freq[digit] == 0) {
                continue;
            }

            // Hundreds digit cannot be zero.
            if (position == 0 && digit == 0) {
                continue;
            }

            // Units digit must be even.
            if (position == 2 && digit % 2 != 0) {
                continue;
            }

            freq[digit]--;

            backtrack(
                position + 1,
                number * 10 + digit
            );

            freq[digit]++;
        }
    }
}
```

---

# C++ Code

```cpp
//Approach-2 (Backtracking + Frequency Array)
//T.C : O(10^3)
//S.C : O(10)

class Solution2 {
public:

    int freq[10] = {};
    unordered_set<int> set;

    int totalNumbers(vector<int>& digits) {

        for (int digit : digits) {
            freq[digit]++;
        }

        backtrack(0, 0);

        return set.size();
    }

    void backtrack(int position, int number) {

        if (position == 3) {
            set.insert(number);
            return;
        }

        for (int digit = 0; digit <= 9; digit++) {

            if (freq[digit] == 0) {
                continue;
            }

            // Hundreds digit cannot be zero.
            if (position == 0 && digit == 0) {
                continue;
            }

            // Units digit must be even.
            if (position == 2 && digit % 2 != 0) {
                continue;
            }

            freq[digit]--;

            backtrack(
                position + 1,
                number * 10 + digit
            );

            freq[digit]++;
        }
    }
};
```

---

# Approach 2 Dry Run: [0,2,2]

Frequency table:

```text
0 -> 1
2 -> 2
```

### Position 0

Cannot use `0`.

Choose:

```text
2
```

Remaining:

```text
0 -> 1
2 -> 1
```

### Position 1

Choose `0`:

```text
20
```

### Position 2

Only available even digit:

```text
2
```

Complete:

```text
202
```

Backtrack.

Choose `2` for position 1:

```text
22
```

Position 2:

```text
0
```

Complete:

```text
220
```

Final distinct numbers:

```text
{202,220}
```

Answer:

```text
2
```

---

# Why We Cannot Use the Same Index Twice

Suppose:

```text
digits = [2,3,4]
```

This does not allow:

```text
222
```

because there is only one `2`.

The nested-loop solution tracks:

```text
i, j, k
```

and requires them to be pairwise different.

The backtracking solution decreases:

```text
freq[2]
```

when it uses a `2`, so that copy cannot immediately be reused.

Both methods correctly model the "use each input copy at most once" rule.

---

# Why Equal Values Can Be Used Twice

Now consider:

```text
digits = [2,2,4]
```

There are two copies of `2`.

Therefore:

```text
224
```

is valid.

The important distinction is:

```text
same VALUE
```

does not mean:

```text
same INPUT POSITION
```

Repeated values are allowed when enough copies exist.

---

# Correctness Proof

## Lemma 1: Every Generated Number Is Three-Digit

The hundreds digit is explicitly checked:

```text
digits[i] != 0
```

or, in backtracking:

```text
position == 0 && digit == 0
```

is rejected.

Therefore every generated number begins with `1..9`.

---

## Lemma 2: Every Generated Number Is Even

The units position is checked:

```text
digits[k] % 2 == 0
```

or:

```text
position == 2 && digit % 2 != 0
```

is rejected.

Therefore every generated number ends in:

```text
0,2,4,6,8
```

and is even.

---

## Lemma 3: No Input Copy Is Reused

In enumeration:

```text
i != j
i != k
j != k
```

ensures three distinct input positions.

In backtracking, `freq[d]` is decremented when a copy is used and restored afterward.

Therefore the number never uses more copies of a digit than are available.

---

## Lemma 4: Every Valid Number Is Generated

Take any valid 3-digit even number.

Its digits come from three valid input positions.

The enumeration loops eventually choose exactly those three positions.

The backtracking procedure eventually chooses exactly those three digit values while respecting their frequencies.

Therefore every valid number is generated.

---

## Lemma 5: Duplicate Numbers Are Counted Once

Multiple index choices can produce the same numeric value.

The `HashSet`/`unordered_set` stores the value itself, so duplicate insertions do not increase its size.

Therefore the final set contains exactly the distinct valid numbers.

---

## Theorem

The returned set contains every valid distinct 3-digit even number and no invalid or duplicate number.

Therefore:

```text
set.size()
```

is exactly the required answer.

---

# Complexity Comparison

| Approach                     |   Time |             Space | Main Idea                    |
| ---------------------------- | -----: | ----------------: | ---------------------------- |
| Triple Enumeration + HashSet |  O(n³) | O(n³) loose bound | ⭐ Simplest                  |
| Backtracking + Frequency     | O(10³) |       O(10) + set | ⭐ General recursion pattern |

Since:

```text
n <= 10
```

both approaches are easily fast enough. citeturn780718search0turn780718search4

---

# Edge Cases

## Case 1: No Even Digit

```text
[1,3,5]
```

No valid units digit.

```text
answer = 0
```

---

## Case 2: Only Repeated Even Digit

```text
[6,6,6]
```

Only:

```text
666
```

exists.

```text
answer = 1
```

---

## Case 3: Zero Present

```text
[0,2,4]
```

Valid:

```text
204
240
402
420
```

Invalid:

```text
024
042
```

because of leading zero.

---

## Case 4: Repeated Digit Copies

```text
[2,2,4]
```

Valid:

```text
224
242
422
```

because two separate copies of `2` exist.

---

# Common Mistakes

### Mistake 1: Allowing Leading Zero

`024` is not a 3-digit number.

### Mistake 2: Checking the First Digit for Evenness

Only the **last** digit determines parity.

### Mistake 3: Reusing an Input Position

Each position can be used only once.

### Mistake 4: Treating Equal Values as Equal Positions

Two copies of `2` can be used twice if the input contains both.

### Mistake 5: Counting Index Permutations

The answer counts distinct numerical values, so use a set or another deduplication mechanism.

---

# Interview Thought Process

When solving this in an interview:

```text
1. How long is the constructed number?
```

Exactly 3 digits.

```text
2. What makes it even?
```

Last digit must be even.

```text
3. What makes it three-digit?
```

First digit cannot be zero.

```text
4. Can an input position be reused?
```

No.

```text
5. Can equal digit values be reused?
```

Yes, if multiple copies exist.

```text
6. Do we count index arrangements or numbers?
```

Numbers.

Therefore:

```text
enumerate
+
validate
+
deduplicate
```

is the cleanest solution.

---

# Pattern Recognition

This is a classic **bounded enumeration** problem.

Do not automatically search for an advanced combinatorial formula.

The constraints say:

```text
n <= 10
```

and we only need three positions.

So:

```text
O(n³)
```

means at most about:

```text
1000
```

candidate triples.

That is tiny.

The important interview skill is recognizing when brute force is actually the optimal engineering choice because the search space is bounded and small.

---

# Mental Model

Think of three slots:

```text
+-----------+---------+---------+
| Hundreds  |  Tens   |  Units  |
+-----------+---------+---------+
      |                    |
      |                    |
  1 to 9 only       0,2,4,6,8 only
```

Then select three distinct input positions:

```text
i -> hundreds
j -> tens
k -> units
```

Finally:

```text
number = 100*digits[i]
       + 10*digits[j]
       + digits[k]
```

and store it.

---

# Final Recommended Solution

For this particular problem, the most readable solution is:

```text
Three nested loops
+
HashSet
```

because the constraints are tiny and every requirement can be expressed directly in the loops.

The complete reasoning is:

```text
choose hundreds
      |
      v
reject zero
      |
      v
choose tens
      |
      v
choose units
      |
      v
reject odd
      |
      v
ensure positions differ
      |
      v
build number
      |
      v
HashSet
```

---

# Final Code to Remember

## Java

```java
//Approach-1 (Three-Level Enumeration + HashSet)
//T.C : O(n^3)
//S.C : O(n^3) worst case

class Solution {

    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {

            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) {
                        continue;
                    }

                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number =
                        digits[i] * 100 +
                        digits[j] * 10 +
                        digits[k];

                    set.add(number);
                }
            }
        }

        return set.size();
    }
}
```

## C++

```cpp
//Approach-1 (Three-Level Enumeration + HashSet)
//T.C : O(n^3)
//S.C : O(n^3) worst case

class Solution {
public:

    int totalNumbers(vector<int>& digits) {
        unordered_set<int> set;

        int n = digits.size();

        for (int i = 0; i < n; i++) {

            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < n; k++) {

                    if (k == i || k == j) {
                        continue;
                    }

                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number =
                        digits[i] * 100 +
                        digits[j] * 10 +
                        digits[k];

                    set.insert(number);
                }
            }
        }

        return set.size();
    }
};
```

---

# Final Summary

The problem asks us to count distinct 3-digit even numbers formed from a small array of digits.

A valid number must satisfy:

```text
1. Hundreds digit != 0
2. Units digit is even
3. Three input positions are distinct
4. Repeated digit values are allowed when enough copies exist
5. Duplicate numerical results count only once
```

Because:

```text
digits.length <= 10
```

we can safely enumerate all triples of positions.

The best practical solution is:

```text
Three nested loops
+
validation
+
HashSet
```

The backtracking/frequency-array solution is an equally valid alternative and is useful as a general reusable pattern.

Overall:

```text
Time  = O(n³)
Space = O(n³) loose bound
```

for the direct enumeration approach, with `n <= 10`. citeturn780718search0turn780718search4

---

# One-Line Insight

> **Enumerate three distinct positions, require a non-zero hundreds digit and an even units digit, then insert every valid number into a HashSet to count only distinct values.**

---

# Tags

`Array, Hash Table, Enumeration, Backtracking, Combinatorics, Permutation, Math, LeetCode, Easy`
