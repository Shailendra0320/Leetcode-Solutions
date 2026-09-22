# 1291. Sequential Digits

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 🧩 Problem Statement

Given two integers `low` and `high`, return all integers in the inclusive range:

```text
[low, high]
```

whose digits are **sequential**.

An integer has sequential digits when every digit is exactly one greater than the previous digit.

Examples:

```text
123
234
3456
56789
123456789
```

Non-examples:

```text
122
135
321
1235
```

The answer must be sorted.

The official constraints are:

```text
10 <= low <= high <= 10^9
```

[LeetCode 1291 — Sequential Digits](https://leetcode.com/problems/sequential-digits/description/) citeturn273141view0

---

# 💡 What Is the Question Really Asking?

Instead of checking every number from `low` to `high`, generate only numbers that can possibly be valid.

Every sequential number is a contiguous sequence of digits from:

```text
123456789
```

Examples:

```text
1
12
123
1234
...

2
23
234
2345
...

3
34
345
3456
...
```

So the real problem is:

```text
Generate valid sequential numbers
        ↓
Keep numbers inside [low, high]
        ↓
Sort and return
```

---

# 🧠 Core Observation

Every sequential number is uniquely determined by:

```text
starting digit
+
length
```

For example:

```text
start = 4
length = 4

=> 4567
```

The largest possible sequential number is:

```text
123456789
```

Therefore the total number of possible candidates is:

```text
9 + 8 + 7 + ... + 1
= 45
```

So we only ever need to examine **45 candidates**.

That is the main optimization.

---

# 🏗️ Solution Architecture

```text
                         low, high
                            |
                            v
                 +---------------------+
                 | Generate only      |
                 | valid candidates   |
                 +----------+----------+
                            |
                +-----------+-----------+
                |                       |
                v                       v
       Start + Length          Recursive Generation
                |                       |
                +-----------+-----------+
                            |
                            v
                 low <= num <= high
                            |
                            v
                       Add answer
                            |
                            v
                           Sort
                            |
                            v
                         Return
```

---

# 1️⃣ Approach 1 — Start Digit + Length Enumeration

For each starting digit:

```text
1 ... 9
```

keep extending the number by the next digit.

If:

```text
start = 3
```

we generate:

```text
3
34
345
3456
34567
...
3456789
```

Numerically:

```text
num = num * 10 + digit
```

This avoids string conversion.

---

# 🔄 Approach 1 Flowchart

```text
                  Start
                    |
                    v
               start = 1..9
                    |
                    v
              digit = start..9
                    |
                    v
        num = num * 10 + digit
                    |
                    v
          low <= num <= high?
               /                      Yes          No
              |            |
              v            |
          Add num          |
              |            |
              +------+-----+
                     |
                     v
                  Next digit
                     |
                     v
                    Sort
                     |
                     v
                  Return
```

---

## ✅ Java — Approach 1

```java
//Approach-1 (Start Digit + Length Enumeration)
//T.C : O(45 log 45) = O(1)
//S.C : O(45)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {

        List<Integer> answer = new ArrayList<>();

        for (int start = 1; start <= 9; start++) {

            int num = 0;

            for (int digit = start; digit <= 9; digit++) {

                num = num * 10 + digit;

                if (num >= low && num <= high) {
                    answer.add(num);
                }

                if (num > high) {
                    break;
                }
            }
        }

        Collections.sort(answer);

        return answer;
    }
}
```

---

## ✅ C++ — Approach 1

```cpp
//Approach-1 (Start Digit + Length Enumeration)
//T.C : O(45 log 45) = O(1)
//S.C : O(45)

class Solution {
public:
    vector<int> sequentialDigits(int low, int high) {

        vector<int> answer;

        for (int start = 1; start <= 9; start++) {

            int num = 0;

            for (int digit = start; digit <= 9; digit++) {

                num = num * 10 + digit;

                if (num >= low && num <= high) {
                    answer.push_back(num);
                }

                if (num > high) {
                    break;
                }
            }
        }

        sort(answer.begin(), answer.end());

        return answer;
    }
};
```

---

# 🧪 Detailed Dry Run

Consider:

```text
low = 100
high = 300
```

For:

```text
start = 1
```

generate:

```text
1
12
123
```

Only:

```text
123
```

is inside the range.

For:

```text
start = 2
```

generate:

```text
2
23
234
```

Only:

```text
234
```

is inside the range.

For:

```text
start = 3
```

we get:

```text
3
34
345
```

but:

```text
345 > 300
```

So we stop.

Final:

```text
[123, 234]
```

which matches the official example. citeturn273141view0

---

# 2️⃣ Approach 2 — Recursive Generation

Instead of using nested loops, generate each sequential number recursively.

For example:

```text
generate(123)
```

can only extend to:

```text
1234
```

then:

```text
12345
```

and so on.

The next digit is always:

```text
lastDigit + 1
```

so there is no branching choice.

---

# 🔄 Approach 2 Flowchart

```text
               start digit
                    |
                    v
             current number
                    |
                    v
        Is number inside range?
             /                       Yes             No
            |               |
            v               |
          Add               |
            |               |
            +-------+-------+
                    |
                    v
        nextDigit = lastDigit + 1
                    |
                    v
            nextDigit <= 9?
               /                    Yes        No
              |          |
              v          v
           Recurse      Stop
```

---

## ✅ Java — Approach 2

```java
//Approach-2 (Recursive Sequential Number Generation)
//T.C : O(45 log 45) = O(1)
//S.C : O(9)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution2 {

    private int low;
    private int high;
    private final List<Integer> answer = new ArrayList<>();

    public List<Integer> sequentialDigits(int low, int high) {

        this.low = low;
        this.high = high;

        for (int start = 1; start <= 9; start++) {
            generate(start);
        }

        Collections.sort(answer);

        return answer;
    }

    private void generate(int num) {

        if (num >= low && num <= high) {
            answer.add(num);
        }

        int lastDigit = num % 10;
        int nextDigit = lastDigit + 1;

        if (nextDigit > 9) {
            return;
        }

        long next = (long) num * 10 + nextDigit;

        if (next <= high) {
            generate((int) next);
        }
    }
}
```

---

## ✅ C++ — Approach 2

```cpp
//Approach-2 (Recursive Sequential Number Generation)
//T.C : O(45 log 45) = O(1)
//S.C : O(9)

class Solution2 {
public:
    vector<int> answer;
    int low;
    int high;

    vector<int> sequentialDigits(int low, int high) {

        this->low = low;
        this->high = high;

        for (int start = 1; start <= 9; start++) {
            generate(start);
        }

        sort(answer.begin(), answer.end());

        return answer;
    }

private:
    void generate(int num) {

        if (num >= low && num <= high) {
            answer.push_back(num);
        }

        int lastDigit = num % 10;
        int nextDigit = lastDigit + 1;

        if (nextDigit > 9) {
            return;
        }

        long long next = 1LL * num * 10 + nextDigit;

        if (next <= high) {
            generate((int) next);
        }
    }
};
```

---

# 🧠 Why Recursive Generation Works

Starting from:

```text
4
```

there is exactly one possible extension:

```text
45
```

then:

```text
456
```

then:

```text
4567
```

then:

```text
45678
```

until:

```text
456789
```

After `9`, there is no next digit.

So every starting digit produces one short chain:

```text
1 → 12 → 123 → ... → 123456789

2 → 23 → 234 → ... → 23456789

3 → 34 → 345 → ... → 3456789

...
```

This is why the total candidate count is exactly 45.

---

# ⚖️ Approach Comparison

| Feature        | Approach 1                      | Approach 2                         |
| -------------- | ------------------------------- | ---------------------------------- |
| Main idea      | Enumerate starts and extensions | Recursive generation               |
| Candidates     | At most 45                      | At most 45                         |
| Time           | `O(45 log 45)`                  | `O(45 log 45)`                     |
| Space          | `O(45)`                         | `O(45)` including output/recursion |
| Implementation | Simpler                         | More reusable                      |
| Best for       | Interview / clean code          | Generation pattern                 |

---

# 🚫 Why Not Check Every Number?

A naive solution might do:

```text
for x = low to high:
    check whether x has sequential digits
```

But:

```text
high <= 10^9
```

so the range may contain nearly a billion integers. citeturn273141view0

That is unnecessary.

The valid-answer space itself contains only:

```text
45 candidates
```

So we should generate valid candidates directly.

---

# 🔥 Generate Instead of Filter

Two possible strategies:

### Filtering

```text
All numbers in range
        ↓
Check each number
        ↓
Keep sequential ones
```

Potentially enormous.

### Generation

```text
Sequential digit rule
        ↓
Generate only valid numbers
        ↓
Keep numbers in range
```

Only 45 candidates.

This is the main algorithmic insight.

---

# 🎯 Interview Thought Process

```text
What is a sequential number?
        ↓
Every next digit = previous digit + 1
        ↓
Where can such numbers come from?
        ↓
Contiguous parts of 123456789
        ↓
How many are there?
        ↓
45
        ↓
Generate directly
        ↓
Filter by [low, high]
        ↓
Sort
```

---

# 🧩 Pattern Recognition

Whenever a problem asks for numbers satisfying a very restrictive digit pattern, ask:

> Can I generate valid candidates directly instead of checking the whole range?

Useful examples of this pattern include:

```text
Sequential digits
Palindromic numbers
Numbers with restricted digits
Monotonic digits
Special digit constructions
```

The general idea is:

```text
Small valid search space
        >
Huge numeric range
```

Generate the small space.

---

# 📌 Mathematical Structure

A sequential number has the form:

```text
start, start+1, start+2, ..., start+L-1
```

with:

```text
1 <= start <= 9
```

and:

```text
start + L - 1 <= 9
```

Therefore the maximum number of lengths for a fixed start is:

```text
10 - start
```

Total candidates:

```text
Σ(10-start), start=1...9
```

which gives:

```text
9 + 8 + 7 + ... + 1
= 45
```

---

# 🧪 More Examples

## Example 1

```text
low = 100
high = 300
```

Output:

```text
[123,234]
```

citeturn273141view0

---

## Example 2

```text
low = 1000
high = 13000
```

Output:

```text
[1234,2345,3456,4567,5678,6789,12345]
```

citeturn273141view0

---

# ⚠️ Common Mistakes

## 1. Scanning the entire range

Do not iterate:

```text
low → high
```

Generate candidates instead.

---

## 2. Allowing `9 → 10`

`10` is not a sequential-digit number.

The digits themselves must increase by exactly `1`.

Therefore:

```text
123456789
```

is the longest valid number.

---

## 3. Forgetting to sort

Generating by start digit does not guarantee global numeric order.

Always sort before returning.

---

## 4. Using strings unnecessarily

Integer construction is enough:

```text
num = num * 10 + digit
```

---

## 5. Forgetting the range boundaries

The interval is inclusive:

```text
low <= num <= high
```

---

# 🧠 Correctness Proof

## Lemma 1 — Every generated number is sequential

We start with a digit:

```text
start
```

and append:

```text
start + 1
start + 2
...
```

Therefore every adjacent pair of digits differs by exactly `1`.

So every generated candidate has sequential digits.

---

## Lemma 2 — Every sequential number is generated

Consider any sequential number.

Its first digit is some:

```text
start ∈ [1,9]
```

Every next digit is forced to be:

```text
start + 1
start + 2
...
```

Therefore the enumeration/recursion starting from that `start` will generate exactly that number.

So no valid sequential number is missed.

---

## Lemma 3 — Range filtering is exact

We add a generated number only when:

```text
low <= num <= high
```

Therefore every returned number belongs to the requested range.

---

## Conclusion

The algorithm generates every and only valid sequential-digit numbers, keeps exactly those inside the requested range, and sorts them.

Therefore it returns the correct answer.

---

# 🏆 Why This Is Optimal

The number of valid candidates is fixed at:

```text
45
```

regardless of how large the numeric range becomes.

Therefore the algorithm avoids unnecessary work and effectively runs in constant time with respect to the input value range.

---

# 📊 Complexity Analysis

There are at most:

```text
45
```

sequential-digit numbers.

Sorting them costs:

```text
O(45 log 45)
```

which is effectively:

```text
O(1)
```

### Approach 1

```text
Time  : O(45 log 45) = O(1)
Space : O(45) = O(1)
```

### Approach 2

```text
Time  : O(45 log 45) = O(1)
Space : O(45) = O(1)
```

The output itself can contain at most 45 numbers.

---

# 📝 Final Summary

Sequential digits mean:

```text
every digit is exactly one greater
than the previous digit
```

Therefore every valid number is simply a contiguous sequence from:

```text
123456789
```

There are only:

```text
45
```

possible candidates.

So the best strategy is:

```text
Generate valid candidates
        ↓
Filter by [low, high]
        ↓
Sort
        ↓
Return
```

No range scan is necessary.

---

# 💎 One-Line Insight

> **Every sequential-digit number is a contiguous sequence from `123456789`, so generate the at-most-45 valid candidates directly instead of scanning the entire `[low, high]` range.**

---

# 🧠 Interview Cheat Sheet

```text
Sequential digits?
        ↓
Next digit = previous digit + 1
        ↓
All candidates come from:
123456789
        ↓
Only 45 candidates exist
        ↓
Generate directly
        ↓
Keep low <= num <= high
        ↓
Sort
        ↓
Return

Complexity:
Effectively O(1) time
O(1) space
```

---

## 🏷️ Tags

`Math, Enumeration, Simulation, Recursion, Backtracking, Number Generation, Digits, String, Sorting, Combinatorics, LeetCode, Medium`
