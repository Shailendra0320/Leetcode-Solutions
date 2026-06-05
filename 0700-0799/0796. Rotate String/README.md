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

# 796. Rotate String

# Intuition

A string can be rotated by moving characters from the front to the back.

Example:

```text
abcde
```

Rotate once:

```text
bcdea
```

Rotate twice:

```text
cdeab
```

We need to determine whether `goal` can be obtained by rotating `s`.

---

# Key Observation

If:

```text
s = abcde
```

Then:

```text
s + s = abcdeabcde
```

All possible rotations of `s` will appear as a substring inside:

```text
s + s
```

Therefore:

```text
goal must be a substring of (s + s)
```

---

# Approach

## Step 1

Check lengths.

```text
Different lengths
=
Impossible
```

Return:

```text
false
```

---

## Step 2

Create:

```text
doubled = s + s
```

---

## Step 3

Search:

```text
goal
```

inside:

```text
doubled
```

---

## Step 4

If found:

```text
true
```

Otherwise:

```text
false
```

---

# Flow Diagram

```text
Check Lengths

       ↓

Create s + s

       ↓

goal exists in s+s ?

       ↓

YES → true

NO  → false
```

---

# Example

Input

```text
s = "abcde"
goal = "cdeab"
```

---

Create:

```text
abcdeabcde
```

Search:

```text
cdeab
```

Found ✅

Answer:

```text
true
```

---

# Dry Run

Input

```text
s = "abcde"
goal = "abced"
```

Create:

```text
abcdeabcde
```

Search:

```text
abced
```

Not Found ❌

Answer:

```text
false
```

---

# Visualization

```text
s = abcde

Rotations

abcde
bcdea
cdeab
deabc
eabcd
```

All are contained in:

```text
abcdeabcde
```

---

# Complexity Analysis

## Time Complexity

```text
O(n²)
```

Substring search in worst case.

---

## Space Complexity

```text
O(n)
```

For storing:

```text
s + s
```

---

# Java Solution

```java
class Solution {

    public boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        String doubled = s + s;

        return doubled.contains(goal);
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    bool rotateString(string source, string target) {

        if (source.length() != target.length()) {
            return false;
        }

        string doubledString = source + source;

        return doubledString.find(target) != string::npos;
    }
};
```