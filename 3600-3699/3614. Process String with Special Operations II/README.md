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

# 3614. Process String with Special Operations II

# Intuition

In Part-I (3612), we generated the entire string.

But here:

```text
String length can become extremely large.
```

because:

```text
'#' duplicates the string.
```

Generating the final string directly is impossible.

Instead:

```text
Track only lengths.
```

Then:

```text
Work backwards.
```

to find the k-th character.

---

# Operations

| Character | Meaning |
|------------|------------|
| a-z | Append character |
| * | Remove last character |
| # | Duplicate current string |
| % | Reverse current string |

---

# Key Observation

We do NOT need:

```text
Final String
```

We only need:

```text
Length after each operation.
```

Store:

```text
len[i]
```

where:

```text
len[i]

=

length after processing s[0...i]
```

---

# Approach

## Forward Pass

Compute:

```text
Length after every operation
```

without building the string.

---

### Letter

```text
Length + 1
```

---

### *

```text
Length - 1
```

---

### #

```text
Length × 2
```

---

### %

```text
Length unchanged
```

---

# Reverse Pass

Start from:

```text
k-th position
```

and move backwards.

---

### #

If string became:

```text
A + A
```

then:

```text
k belongs to first half

or

second half
```

Convert it back.

---

### %

If string was reversed:

```text
abcdef

↓

fedcba
```

then:

```text
newIndex

=

length-1-k
```

---

### Letter

If current character created index:

```text
k
```

return it.

---

# Flowchart

```text
Forward Pass

Compute Lengths

        │
        ▼

Final Length

        │
        ▼

k Valid ?

   ┌────┴────┐
   │         │
  No        Yes
   │         │
   ▼         ▼

Return .   Reverse Pass

                 │
                 ▼

          Undo Operations

                 │
                 ▼

         Find Character

                 │
                 ▼

             Return
```

---

# Example

Input

```text
s = "ab#"

k = 2
```

---

Forward

```text
a

length = 1


ab

length = 2


abab

length = 4
```

---

Reverse

```text
k = 2

abab

↓

second half

↓

k = 0
```

Now:

```text
index 0

=

'a'
```

Answer:

```text
a
```

---

# Detailed Dry Run

Input

```text
s = "abc#"

k = 4
```

---

Forward Lengths

```text
a  -> 1

b  -> 2

c  -> 3

#  -> 6
```

Store

```text
[1,2,3,6]
```

---

Reverse

```text
k = 4

Inside duplicated half

4 >= 3

k = 4 - 3

k = 1
```

---

Now

```text
index 1

= b
```

Answer

```text
b
```

---

# Visualization

```text
Original

abc

        │

        ▼

Duplicate

abcabc

        │

        ▼

k = 4

        │

        ▼

Maps To

k = 1

        │

        ▼

Character = b
```

---

# Memory Diagram

```text
Index

0 1 2 3

Chars

a b c #

Lengths

1 2 3 6
```

---

# Why Reverse Processing Works

Every operation can be undone:

```text
#  -> map to first half

%  -> reverse index

letter -> identify answer
```

Thus:

```text
We never build the string.
```

Only:

```text
Track lengths
```

and

```text
trace k backwards.
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Forward Pass:

```text
O(n)
```

Reverse Pass:

```text
O(n)
```

Total:

```text
O(n)
```

---

## Space Complexity

```text
O(n)
```

Length array.

---

# Java Solution

```java
class Solution {

    public char processStr(String s, long k) {

        int n = s.length();

        long[] len = new long[n];

        long currentLength = 0;

        for (int i = 0; i < n; i++) {

            char current = s.charAt(i);

            if (current == '*') {

                currentLength =
                    Math.max(
                        0,
                        currentLength - 1
                    );
            }
            else if (current == '#') {

                currentLength =
                    Math.min(
                        currentLength * 2,
                        (long)2e15
                    );
            }
            else if (current != '%') {

                currentLength++;
            }

            len[i] = currentLength;
        }

        if (k >= len[n - 1]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char current = s.charAt(i);

            if (current == '#') {

                long previousLength =
                    (i > 0 ? len[i - 1] : 0);

                if (k >= previousLength) {

                    k -= previousLength;
                }
            }
            else if (current == '%') {

                long previousLength =
                    (i > 0 ? len[i - 1] : 0);

                k = previousLength - 1 - k;
            }
            else if (current != '*') {

                if (len[i] - 1 == k) {
                    return current;
                }
            }
        }

        return '.';
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    char processStr(string s, long long k) {

        int n = s.size();

        vector<long long> length(n);

        long long currentLength = 0;

        for (int i = 0; i < n; i++) {

            char current = s[i];

            if (current == '*') {

                currentLength = max(
                    0LL,
                    currentLength - 1
                );
            }
            else if (current == '#') {

                currentLength =
                    min(
                        currentLength * 2,
                        (long long)2e15
                    );
            }
            else if (current != '%') {

                currentLength++;
            }

            length[i] = currentLength;
        }

        if (k >= length[n - 1]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char current = s[i];

            if (current == '#') {

                long long previousLength =
                    (i > 0 ? length[i - 1] : 0);

                if (k >= previousLength) {

                    k -= previousLength;
                }
            }
            else if (current == '%') {

                long long previousLength =
                    (i > 0 ? length[i - 1] : 0);

                k = previousLength - 1 - k;
            }
            else if (current != '*') {

                if (length[i] - 1 == k) {
                    return current;
                }
            }
        }

        return '.';
    }
};
```