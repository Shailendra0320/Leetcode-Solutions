# 3090. Maximum Length Substring With Two Occurrences

> **LeetCode Medium | Sliding Window | Two Pointers | Frequency Array | String**

---

# 🔗 Problem Links

- **LeetCode:** https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
- **GitHub:** https://github.com/Shailendra0320
- **LeetCode Profile:** https://leetcode.com/u/ShailendraLeetcode03/
- **Alternate LeetCode Profile:** https://leetcode.com/u/Shailu03/

---

# 📌 Problem Statement

You are given a string `s`.

Return the length of the **longest substring** in which every character appears **at most twice**.

In other words, for the selected substring:

```text
frequency of every character <= 2
```

---

## Example

```text
Input:
s = "bcbbbcba"

Output:
4
```

The longest valid substring has length `4`.

---

# 🎯 What Are We Looking For?

We need:

```text
Longest
   ↓
Contiguous
   ↓
Substring
   ↓
Every character appears at most 2 times
```

For example:

```text
"bcbb"
```

has:

```text
b → 3
c → 1
```

So it is **invalid**.

But:

```text
"cbbb"
```

has:

```text
c → 1
b → 3
```

so it is also invalid.

A valid substring could contain:

```text
b → 2
c → 1
```

or:

```text
a → 2
b → 2
c → 2
```

but never:

```text
any character → 3+
```

---

# 🧠 Key Observation

The important condition is:

```text
Every character must appear <= 2 times
```

If a character appears for the third time, the current window becomes invalid.

For example:

```text
Window:

b c b b
```

Frequency:

```text
b → 3 ❌
c → 1
```

Therefore:

```text
Window = INVALID
```

We need to remove characters from the left until the frequency becomes valid again.

This strongly suggests:

```text
Sliding Window
```

---

# 💡 Main Idea

Instead of generating every possible substring, maintain a dynamic window:

```text
[left ........ right]
```

The window always represents the current candidate substring.

We expand the window using `right`.

When the window becomes invalid:

```text
frequency > 2
```

we move `left` forward.

Therefore:

```text
Expand → Check → Shrink if needed → Update Answer
```

---

# 🚀 Approaches

We can solve this problem using two approaches:

```text
1. Brute Force + Frequency Array
2. Sliding Window + Frequency Array
```

The second approach is optimal.

---

# ❌ Approach 1 — Brute Force + Frequency Array

## Intuition

The simplest solution is to try every possible starting position.

For each starting index:

```text
i = 0
i = 1
i = 2
...
i = n - 1
```

we expand the substring toward the right.

While expanding, we maintain character frequencies.

If any character appears more than twice:

```text
frequency > 2
```

the current substring is invalid.

Because adding more characters cannot decrease an existing frequency, we can stop expanding from that starting position.

---

# 🔍 Brute Force Algorithm

For every `i`:

```text
1. Create freq[26]

2. Set j = i

3. Add s[j] to frequency

4. Check whether any frequency > 2

5. If valid:
       update maximum length

6. If invalid:
       stop this inner loop

7. Move to next i
```

---

# 📊 Brute Force Diagram

```text
                    Start
                      │
                      ▼
              Choose start index i
                      │
                      ▼
              Create freq[26]
                      │
                      ▼
              Choose end index j
                      │
                      ▼
              Add s[j] frequency
                      │
                      ▼
              Is any frequency > 2?
                 /            \
               NO              YES
               │                │
               ▼                ▼
        Update maxLen       Stop current
               │              substring
               │                │
               └───────┬────────┘
                       ▼
                 Move to next
                    index
```

---

# 🧪 Brute Force Example

Consider:

```text
s = "bcbbbcba"
```

Starting from index `0`:

```text
b
bc
bcb
bcbb
bcbbb ❌
```

At:

```text
"bcbbb"
```

frequency is:

```text
b → 3
c → 1
```

Therefore the substring is invalid.

---

Starting from index `1`:

```text
c
cb
cbb
cbbb ❌
```

Again:

```text
b → 3
```

so we stop.

---

# 🧮 Brute Force Complexity

Let:

```text
n = s.length()
```

There can be `O(n²)` substrings.

For every substring, we scan the frequency array of size `26`.

Therefore:

```text
O(26 × n²)
```

Since `26` is constant:

```text
Time Complexity = O(n²)
```

### Space Complexity

We only use:

```text
freq[26]
```

Therefore:

```text
Space Complexity = O(26)
                 = O(1)
```

---

# 💻 Brute Force — Java

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLen = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                boolean valid = true;

                for (int f : freq) {
                    if (f > 2) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }

        return maxLen;
    }
}
```

---

# 💻 Brute Force — C++

```cpp
class Solution {
public:
    int maximumLengthSubstring(string s) {
        int maxLen = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int freq[26] = {};

            for (int j = i; j < n; j++) {
                freq[s[j] - 'a']++;

                bool valid = true;

                for (int f : freq) {
                    if (f > 2) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    maxLen = max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }

        return maxLen;
    }
};
```

---

# 🚀 Approach 2 — Sliding Window

## Intuition

The brute-force solution repeatedly checks overlapping substrings.

For example:

```text
b c b b
  c b b
    b b
```

A lot of information is repeated.

Instead, we can maintain a single window:

```text
[left ........ right]
```

and modify it as we traverse the string.

The important observation is:

> If the current window contains a character more than twice, we only need to remove characters from the left until that character's frequency becomes valid.

We don't need to start from scratch.

---

# 🧠 Sliding Window Concept

Maintain:

```text
left
right
freq[26]
maxLen
```

Initially:

```text
left = 0
maxLen = 0
```

Then move `right` through the string.

Whenever we encounter:

```text
s[right]
```

increase its frequency:

```text
freq[s[right]]++
```

If:

```text
freq[s[right]] > 2
```

the window is invalid.

So:

```text
while frequency > 2:
    remove s[left]
    left++
```

After the window becomes valid:

```text
maxLen = max(maxLen, right - left + 1)
```

---

# 🔄 Sliding Window Flowchart

```text
                         START
                           │
                           ▼
                  left = 0, maxLen = 0
                           │
                           ▼
                    Move right →
                           │
                           ▼
                Add s[right] to freq
                           │
                           ▼
              Is freq[s[right]] > 2?
                     /           \
                   NO             YES
                   │               │
                   │               ▼
                   │        Remove s[left]
                   │               │
                   │               ▼
                   │        left = left + 1
                   │               │
                   │               ▼
                   │        Still invalid?
                   │             /    \
                   │           YES     NO
                   │            │       │
                   │            └───┐   │
                   │                │   │
                   └────────────────┘   │
                                        ▼
                              Calculate window length
                                        │
                                        ▼
                              Update maxLen
                                        │
                                        ▼
                                Move right →
```

---

# 🌊 Sliding Window Visualization

Suppose:

```text
s = "bcbbbcba"
```

Start:

```text
b c b b b c b a
↑
L
↑
R
```

Current window:

```text
"b"
```

Frequency:

```text
b = 1
```

Valid.

---

Move `right`:

```text
b c b b b c b a
↑   ↑
L   R
```

Window:

```text
"bc"
```

Frequency:

```text
b = 1
c = 1
```

Valid.

---

Continue:

```text
b c b b b c b a
↑     ↑
L     R
```

Window:

```text
"bcb"
```

Frequency:

```text
b = 2
c = 1
```

Valid.

Length:

```text
3
```

---

Add another `b`:

```text
b c b b b c b a
↑       ↑
L       R
```

Window:

```text
"bcbb"
```

Frequency:

```text
b = 3 ❌
c = 1
```

Invalid.

---

# 🔥 Shrinking the Window

Because:

```text
b = 3
```

we move `left`.

Before:

```text
b c b b
↑     ↑
L     R
```

Remove:

```text
s[left] = 'b'
```

Now:

```text
  c b b
  ↑   ↑
  L   R
```

Frequency:

```text
b = 2
c = 1
```

The window is valid again.

Therefore:

```text
current length = 3
```

---

# 🎯 Important Sliding Window Property

The window always tries to maintain:

```text
Every character frequency <= 2
```

So we can think of the algorithm as:

```text
                 VALID WINDOW
                     │
                     ▼
             Add right character
                     │
                     ▼
              ┌──────────────┐
              │   Valid?     │
              └──────┬───────┘
                     │
             ┌───────┴───────┐
             │               │
            YES              NO
             │               │
             ▼               ▼
        Update answer    Move left
                             │
                             ▼
                       Restore validity
                             │
                             ▼
                       Update answer
```

---

# 🧩 Why `while` Instead of `if`?

This is an important point.

We use:

```java
while (freq[s.charAt(right) - 'a'] > 2)
```

instead of:

```java
if (freq[s.charAt(right) - 'a'] > 2)
```

because the window may need to remove multiple characters before becoming valid.

The general sliding-window rule is:

```text
Expand → while invalid → shrink → valid → calculate answer
```

This guarantees that the window is valid before calculating its length.

---

# 🧠 Why Do We Only Check `freq[s[right]]`?

When we add a new character:

```text
s[right]
```

only its frequency changes.

All other character frequencies remain unchanged.

Therefore, if the window was previously valid, the only possible violation is:

```text
freq[s[right]] > 2
```

This allows us to avoid scanning all `26` frequencies after every insertion.

That is one of the reasons the optimized solution achieves:

```text
O(n)
```

instead of:

```text
O(26 × n)
```

or worse.

---

# 🔍 Complete Example

Input:

```text
s = "bcbbbcba"
```

We maintain:

```text
left
right
freq
maxLen
```

A simplified progression:

```text
right = 0
window = "b"
length = 1
maxLen = 1
```

```text
right = 1
window = "bc"
length = 2
maxLen = 2
```

```text
right = 2
window = "bcb"
length = 3
maxLen = 3
```

```text
right = 3
window = "bcbb"
length = 4
maxLen = 4
```

Next:

```text
right = 4
window = "bcbbb"
```

Frequency:

```text
b = 3
```

Invalid.

Shrink:

```text
"bcbbb"
 ↓
"cbbb"
 ↓
"bbb"
```

Now:

```text
b = 3
```

still invalid.

Shrink again:

```text
"bbb"
 ↓
"bb"
```

Now:

```text
b = 2
```

Valid.

The window continues from there.

The important point is that `left` never moves backward.

---

# 📈 Two Pointer Movement

This is the reason the solution is linear.

```text
right:
0 → 1 → 2 → 3 → 4 → 5 → ... → n-1

left:
0 → 0 → 0 → 0 → 1 → 2 → ... → n-1
```

Both pointers only move forward.

Therefore:

```text
right moves at most n times
left moves at most n times
```

Total pointer movement:

```text
O(n + n)
```

which simplifies to:

```text
O(n)
```

---

# 💡 Why Sliding Window Works

The key property is **monotonicity**.

If a window contains:

```text
character frequency > 2
```

then extending that window further cannot make it valid.

For example:

```text
"bcbbb"
```

already has:

```text
b = 3
```

Adding another character:

```text
"bcbbba"
```

does not fix the problem.

Therefore, we must shrink from the left.

This makes the sliding-window technique applicable.

---

# 🏆 Why This Is Better Than Brute Force

### Brute Force

```text
Choose start
     ↓
Try every end
     ↓
Check frequency
     ↓
Repeat
```

This repeatedly processes the same characters.

Complexity:

```text
O(n²)
```

---

### Sliding Window

```text
Expand right
     ↓
Maintain frequency
     ↓
Shrink left only when necessary
     ↓
Continue
```

Each character is processed a limited number of times.

Complexity:

```text
O(n)
```

---

# 📊 Approach Comparison

| Feature             | Brute Force | Sliding Window |
| ------------------- | ----------: | -------------: |
| Technique           | Enumeration |   Two Pointers |
| Frequency Array     |         Yes |            Yes |
| Time                |     `O(n²)` |         `O(n)` |
| Space               |      `O(1)` |         `O(1)` |
| Repeated Work       |        High |       Very Low |
| Scalable            |          ❌ |             ✅ |
| Interview Preferred | Alternative |     ✅ Optimal |
| Recommended         |    Learning |     Production |

---

# 🏆 Optimal Algorithm

```text
1. Create frequency array of size 26.

2. Initialize:
       left = 0
       maxLen = 0

3. Traverse the string using right.

4. Increase frequency of s[right].

5. While frequency of s[right] > 2:
       decrease frequency of s[left]
       move left forward

6. Calculate:
       right - left + 1

7. Update maxLen.

8. Return maxLen.
```

---

# 💻 Optimal Java Solution

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;

            while (freq[s.charAt(right) - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
```

---

# 💻 Optimal C++ Solution

```cpp
class Solution {
public:
    int maximumLengthSubstring(string s) {
        int freq[26] = {};
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s[right] - 'a']++;

            while (freq[s[right] - 'a'] > 2) {
                freq[s[left] - 'a']--;
                left++;
            }

            maxLen = max(maxLen, right - left + 1);
        }

        return maxLen;
    }
};
```

---

# ⏱️ Complexity Analysis

Let:

```text
n = length of s
```

## Approach 1 — Brute Force

### Time Complexity

```text
O(n²)
```

There can be `O(n²)` candidate substrings.

The frequency array contains only `26` elements, which is constant.

Therefore:

```text
O(26 × n²) = O(n²)
```

### Space Complexity

```text
O(26) = O(1)
```

---

# Approach 2 — Sliding Window

### Time Complexity

```text
O(n)
```

Why?

`right` moves from:

```text
0 → n - 1
```

and `left` also moves only forward.

Therefore each character is added and removed at most once.

```text
O(n + n)
= O(n)
```

### Space Complexity

```text
O(26) = O(1)
```

The frequency array has a fixed size.

---

# 📊 Final Complexity Summary

```text
┌──────────────────────┬────────────┬────────────┐
│ Approach             │ Time       │ Space      │
├──────────────────────┼────────────┼────────────┤
│ Brute Force          │ O(n²)      │ O(1)       │
│ Sliding Window       │ O(n)       │ O(1)       │
└──────────────────────┴────────────┴────────────┘
```

---

# 🧠 Core Pattern

This problem belongs to the:

```text
STRING
   │
   ▼
SUBSTRING
   │
   ▼
LONGEST / SHORTEST
   │
   ▼
FREQUENCY CONSTRAINT
   │
   ▼
SLIDING WINDOW
   │
   ▼
TWO POINTERS + FREQUENCY ARRAY
```

Whenever you see:

```text
Longest substring
+
At most K occurrences
```

think:

```text
Sliding Window
```

---

# 🔥 General Sliding Window Template

The pattern used here can be generalized.

```java
int left = 0;

for (int right = 0; right < n; right++) {

    // Add current element
    add(s[right]);

    // Restore validity
    while (windowIsInvalid()) {
        remove(s[left]);
        left++;
    }

    // Current window is valid
    answer = Math.max(answer, right - left + 1);
}
```

This pattern appears in many problems involving:

```text
Longest substring
Longest subarray
At most K distinct characters
At most K frequency
Minimum window
Maximum window
Frequency constraints
```

---

# 🎯 Interview Explanation

If asked to explain the solution in an interview:

> We need the longest substring where every character occurs at most twice. I use a sliding window with two pointers and a frequency array. I expand the right pointer and increment the frequency of the new character. If that character occurs more than twice, the window becomes invalid, so I move the left pointer forward while decreasing frequencies until the window becomes valid again. After every valid window, I update the maximum length. Since both pointers only move forward and every character enters and leaves the window at most once, the time complexity is O(n) and the space complexity is O(1).

---

# ⚠️ Common Mistakes

## 1. Forgetting to Decrease Frequency

When moving:

```text
left++
```

we must also remove the corresponding character:

```java
freq[s.charAt(left) - 'a']--;
```

Otherwise the frequency array will not represent the current window.

---

## 2. Using `if` Instead of `while`

Incorrect:

```java
if (freq[s.charAt(right) - 'a'] > 2) {
    left++;
}
```

Better:

```java
while (freq[s.charAt(right) - 'a'] > 2) {
    freq[s.charAt(left) - 'a']--;
    left++;
}
```

The window must remain invalid until enough characters have been removed.

---

## 3. Updating the Answer Before Restoring Validity

Do not calculate the maximum while the window is invalid.

Correct order:

```text
Add right
    ↓
Check validity
    ↓
Shrink if invalid
    ↓
Window becomes valid
    ↓
Update answer
```

---

## 4. Using a HashMap Unnecessarily

A `HashMap<Character, Integer>` would also work.

However, because the problem uses lowercase English characters, a:

```text
int[26]
```

is simpler and has constant space.

---

# 🧪 Edge Cases

### Single Character

```text
s = "a"
```

Answer:

```text
1
```

---

### Two Same Characters

```text
s = "aa"
```

Answer:

```text
2
```

---

### Three Same Characters

```text
s = "aaa"
```

The entire string is invalid.

The longest valid substring is:

```text
"aa"
```

Answer:

```text
2
```

---

### All Characters Unique

```text
s = "abcdef"
```

Every character appears once.

Therefore:

```text
answer = 6
```

---

### All Characters Same

```text
s = "aaaaaaa"
```

Only two copies can exist in a valid substring.

Therefore:

```text
answer = 2
```

---

# 🧩 Memory Visualization

```text
                 String
                   │
                   ▼
          ┌─────────────────┐
          │ Sliding Window  │
          └────────┬────────┘
                   │
          ┌────────┴────────┐
          ▼                 ▼
       left               right
          │                 │
          └────────┬────────┘
                   ▼
             freq[26]
                   │
                   ▼
        Is frequency > 2 ?
              /          \
            YES           NO
             │             │
             ▼             ▼
        Move left      Update answer
             │             │
             └──────┬──────┘
                    ▼
                 Continue
```

---

# ⭐ Final Takeaway

The most important idea is not simply:

```text
Use a frequency array.
```

The real optimization is:

```text
Don't check every substring independently.
```

Instead:

```text
Maintain one valid window.
```

The complete transformation is:

```text
Brute Force
    │
    │ repeated substring checking
    ▼
O(n²)
    │
    │ recognize contiguous window
    ▼
Sliding Window
    │
    │ maintain frequency
    ▼
Two Pointers + Frequency Array
    │
    ▼
O(n)
```

Therefore, the preferred solution is:

```text
╔══════════════════════════════════════╗
║          OPTIMAL SOLUTION            ║
╠══════════════════════════════════════╣
║ Technique : Sliding Window           ║
║ Pattern   : Two Pointers             ║
║ Data      : Frequency Array          ║
║ Time      : O(n)                     ║
║ Space     : O(1)                     ║
╚══════════════════════════════════════╝
```

---

# 📚 What To Remember

```text
Longest substring
        +
Frequency restriction
        ↓
Sliding Window
```

More specifically:

```text
At most 2 occurrences
        ↓
freq[26]
        ↓
Expand right
        ↓
If frequency > 2
        ↓
Move left
        ↓
Restore validity
        ↓
Update maximum
```

This is the reusable pattern to remember for similar **substring + frequency constraint** problems.
