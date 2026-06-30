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

# 1358. Number of Substrings Containing All Three Characters

## Tags

```text
String
Sliding Window
Two Pointers
Hash Table
Array
Counting
Java
C++
```

---

# Intuition

We are given a string consisting of only three characters:

```text
'a'
'b'
'c'
```

We need to count every substring that contains at least one occurrence of all three characters.

A brute force solution generates every possible substring and checks whether it contains all three characters.

Since there are O(n²) substrings and checking each substring takes O(n), the total complexity becomes

```text
O(n³)
```

which is far too slow.

Instead, we can use a **Sliding Window** to efficiently count all valid substrings in linear time.

---

# Approach — Sliding Window

Maintain a window

```text
[left ........ right]
```

and store the frequency of

```text
a
b
c
```

inside the current window.

Expand the window by moving the right pointer.

Whenever the window contains all three characters,

```text
count[a] > 0
count[b] > 0
count[c] > 0
```

the window becomes valid.

Instead of counting only this substring,

notice that every larger substring obtained by extending the right boundary is also valid.

Therefore,

```text
answer += n - right
```

After counting these substrings,

move the left pointer until the window becomes invalid again.

Repeat until the entire string has been processed.

---

# Flowchart

```text
Start

   │

   ▼

Initialize

left = 0

right = 0

count[3]

answer = 0

   │

   ▼

Move Right Pointer

   │

   ▼

Update Frequency

   │

   ▼

Window Contains

a,b,c ?

   │

No ─────────────► Continue

   │

Yes

   │

   ▼

answer += n-right

   │

   ▼

Remove Left Character

   │

   ▼

Move Left Pointer

   │

   ▼

Repeat
```

---

# Why do we add `n - right`?

Suppose

```text
Window = abc
```

Current position

```text
right = 4
```

Remaining characters

```text
abcXYZ
```

Every substring

```text
abc

abcX

abcXY

abcXYZ
```

still contains

```text
a
b
c
```

Hence,

instead of counting one by one,

we directly add

```text
n-right
```

to the answer.

This is the key optimization.

---

# Diagram

Example

```text
s = abcabc
```

Window Expansion

```text
a
```

Invalid

↓

```text
ab
```

Invalid

↓

```text
abc
```

Valid

Count

```text
6-2 = 4
```

Substrings

```text
abc

abca

abcab

abcabc
```

Shrink Window

↓

```text
bca
```

Again Valid

Count

```text
6-3 = 3
```

Continue until the end.

---

# Dry Run

Input

```text
abcabc
```

Step 1

```text
Window

a

Answer = 0
```

---

Step 2

```text
Window

ab

Answer = 0
```

---

Step 3

```text
Window

abc
```

Valid

```text
answer += 6-2

= 4
```

---

Step 4

Shrink

```text
bc
```

Invalid

Expand

```text
bca
```

Valid

```text
answer += 6-3

= 7
```

---

Step 5

```text
cab
```

Valid

```text
answer += 6-4

= 9
```

---

Step 6

```text
abc
```

Valid

```text
answer += 6-5

= 10
```

Final Answer

```text
10
```

---

---

# Complexity Analysis

## Approach 1 — Sliding Window

### Time Complexity

```text
O(n)
```

- The **right pointer** traverses the string exactly once.
- The **left pointer** also moves forward at most `n` times.
- Each character enters and leaves the sliding window at most once.

Therefore, the overall time complexity is:

```text
O(n)
```

---

### Space Complexity

```text
O(1)
```

We only use a frequency array of size **3** to store the count of characters:

```text
'a'
'b'
'c'
```

No extra space proportional to the input size is required.

---

## Approach 2 — Last Seen Indices

### Time Complexity

```text
O(n)
```

- Traverse the string once.
- Update the last occurrence of the current character.
- Find the minimum of three indices in constant time.

Hence, the overall complexity is:

```text
O(n)
```

---

### Space Complexity

```text
O(1)
```

We store only the last seen indices of the three characters:

```text
lastSeen['a']
lastSeen['b']
lastSeen['c']
```

Thus, the extra space used is constant.

---

# Complexity Comparison

| Approach          | Time Complexity | Space Complexity |
| ----------------- | --------------- | ---------------- |
| Sliding Window    | **O(n)**        | **O(1)**         |
| Last Seen Indices | **O(n)**        | **O(1)**         |

---

# Java Solution

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int left = 0;
        int answer = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                answer += n - right;
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return answer;
    }
}

/*

Alternative Approach (Last Seen Indices)

// T.C : O(n)
// S.C : O(1)

class Solution {
    public int numberOfSubstrings(String s) {

        int[] lastSeen = {-1, -1, -1};

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            lastSeen[s.charAt(i) - 'a'] = i;

            int minIndex = Math.min(
                lastSeen[0],
                Math.min(lastSeen[1], lastSeen[2])
            );

            if (minIndex != -1) {

                answer += minIndex + 1;
            }
        }

        return answer;
    }
}

*/
```

---

# C++ Solution

```cpp
class Solution {
public:
    int numberOfSubstrings(string s) {

        vector<int> count(3, 0);

        int left = 0;

        int answer = 0;

        int n = s.size();

        for (int right = 0; right < n; right++) {

            count[s[right] - 'a']++;

            while (count[0] > 0 &&
                   count[1] > 0 &&
                   count[2] > 0) {

                answer += n - right;

                count[s[left] - 'a']--;

                left++;
            }
        }

        return answer;
    }
};

/*

Alternative Approach (Last Seen Indices)

// T.C : O(n)
// S.C : O(1)

class Solution {
public:
    int numberOfSubstrings(string s) {

        vector<int> lastSeen(3, -1);

        int answer = 0;

        for (int i = 0; i < s.size(); i++) {

            lastSeen[s[i] - 'a'] = i;

            int minIndex = min(
                lastSeen[0],
                min(lastSeen[1], lastSeen[2])
            );

            if (minIndex != -1) {

                answer += minIndex + 1;
            }
        }

        return answer;
    }
};

*/
```
