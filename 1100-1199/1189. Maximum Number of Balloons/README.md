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

# 1189. Maximum Number of Balloons

## Tags

```text
String
Hash Table
Counting
Frequency Array
Greedy
```

---

# Intuition

We need to form the word:

```text
balloon
```

from the given string.

The word contains:

```text
b -> 1 time

a -> 1 time

l -> 2 times

o -> 2 times

n -> 1 time
```

The answer depends on the character that runs out first.

---

# Character Requirement Table

| Character | Required |
| --------- | -------- |
| b         | 1        |
| a         | 1        |
| l         | 2        |
| o         | 2        |
| n         | 1        |

---

# Approach

Count frequency of all characters.

Then calculate:

```text
b count

a count

l count / 2

o count / 2

n count
```

The minimum among them is the answer.

---

# Flowchart

```text
Traverse String

        │
        ▼

Build Frequency Array

        │
        ▼

Count:

b
a
l
o
n

        │
        ▼

l = l/2

o = o/2

        │
        ▼

Take Minimum

        │
        ▼

Return Answer
```

---

# Example

Input

```text
text = "loonbalxballpoon"
```

---

Frequency

```text
b = 2

a = 2

l = 4

o = 4

n = 2
```

---

Possible balloons

```text
b = 2

a = 2

l = 4/2 = 2

o = 4/2 = 2

n = 2
```

Answer

```text
2
```

---

# Visualization

```text
loonbalxballpoon

↓

Count Characters

↓

b = 2
a = 2
l = 4
o = 4
n = 2

↓

min(2,2,2,2,2)

↓

2
```

---

# Detailed Dry Run

Input

```text
text = "balloonballoon"
```

Frequency:

```text
b = 2

a = 2

l = 4

o = 4

n = 2
```

---

Required

```text
b = 1

a = 1

l = 2

o = 2

n = 1
```

---

Possible Words

```text
2 balloons
```

Answer

```text
2
```

---

# Memory Visualization

```text
Frequency Array

a → 2

b → 2

l → 4

o → 4

n → 2


Required

a → 1

b → 1

l → 2

o → 2

n → 1


Answer

min(
2,
2,
4/2,
4/2,
2
)

=

2
```

---

# Why This Works

To create:

```text
balloon
```

every required character must be available.

The limiting character determines how many complete words can be formed.

Therefore:

```text
Answer

=

minimum(
b,
a,
l/2,
o/2,
n
)
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Single traversal of string.

---

## Space Complexity

```text
O(1)
```

Frequency array size is fixed:

```text
26
```

---

# Java Solution

```java
class Solution {

    public int maxNumberOfBalloons(String text) {

        int[] frequency = new int[26];

        for (char ch : text.toCharArray()) {
            frequency[ch - 'a']++;
        }

        return Math.min(
            Math.min(
                frequency['b' - 'a'],
                frequency['a' - 'a']
            ),
            Math.min(
                frequency['l' - 'a'] / 2,
                Math.min(
                    frequency['o' - 'a'] / 2,
                    frequency['n' - 'a']
                )
            )
        );
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int maxNumberOfBalloons(string text) {

        vector<int> frequency(26, 0);

        for(char ch : text) {
            frequency[ch - 'a']++;
        }

        return min(
            min(
                frequency['b' - 'a'],
                frequency['a' - 'a']
            ),
            min(
                frequency['l' - 'a'] / 2,
                min(
                    frequency['o' - 'a'] / 2,
                    frequency['n' - 'a']
                )
            )
        );
    }
};
```
