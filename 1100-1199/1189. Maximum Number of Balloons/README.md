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
Frequency Array
Counting
Greedy
Java
C++
```

---

# Intuition

We need to form the word:

```text
balloon
```

from the given string.

The word:

```text
balloon
```

contains:

```text
b → 1

a → 1

l → 2

o → 2

n → 1
```

Therefore:

```text
The character that runs out first
determines the answer.
```

---

# Character Requirement Table

| Character | Needed |
| --------- | ------ |
| b         | 1      |
| a         | 1      |
| l         | 2      |
| o         | 2      |
| n         | 1      |

---

# Approach 1 — Frequency Array (Optimal)

## Idea

Count frequency of all characters using:

```text
int[26]
```

Then compute:

```text
b

a

l/2

o/2

n
```

The minimum value is the answer.

---

# Flowchart

```text
Traverse String

        │
        ▼

Build Frequency Array

        │
        ▼

Count

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

# Visualization

Input

```text
loonbalxballpoon
```

---

Count Characters

```text
b = 2

a = 2

l = 4

o = 4

n = 2
```

---

Possible Balloons

```text
b = 2

a = 2

l = 4/2 = 2

o = 4/2 = 2

n = 2
```

---

Answer

```text
2
```

---

# Approach 2 — HashMap

## Idea

Instead of:

```text
int[26]
```

store frequencies inside:

```text
HashMap<Character,Integer>
```

or

```text
unordered_map<char,int>
```

Then calculate:

```text
b

a

l/2

o/2

n
```

and return the minimum.

---

# HashMap Flow

```text
Traverse String

        │
        ▼

Store Frequency
Inside HashMap

        │
        ▼

Extract

b
a
l
o
n

        │
        ▼

Apply

l/2
o/2

        │
        ▼

Minimum Value

        │
        ▼

Answer
```

---

# Detailed Dry Run

Input

```text
balloonballoon
```

Frequency

```text
b = 2

a = 2

l = 4

o = 4

n = 2
```

---

Requirement

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
2
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

To build:

```text
balloon
```

we need:

```text
b

a

l

l

o

o

n
```

Every balloon consumes:

```text
1 b
1 a
2 l
2 o
1 n
```

Hence:

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

# Approaches Comparison

| Approach        | Data Structure          | Time | Space  |
| --------------- | ----------------------- | ---- | ------ |
| Frequency Array | int[26]                 | O(n) | O(1)   |
| HashMap         | HashMap / unordered_map | O(n) | O(1)\* |

```text
* At most 26 lowercase letters.
```

---

# Complexity Analysis

## Approach 1 — Frequency Array

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2 — HashMap

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution 1 (Frequency Array)

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

# Java Solution 2 (HashMap)

```java
class Solution {

    public int maxNumberOfBalloons(String text) {

        Map<Character,Integer> frequency =
            new HashMap<>();

        for(char ch : text.toCharArray()){

            frequency.put(
                ch,
                frequency.getOrDefault(ch,0)+1
            );
        }

        int b =
            frequency.getOrDefault('b',0);

        int a =
            frequency.getOrDefault('a',0);

        int l =
            frequency.getOrDefault('l',0)/2;

        int o =
            frequency.getOrDefault('o',0)/2;

        int n =
            frequency.getOrDefault('n',0);

        return Math.min(
            Math.min(b,a),
            Math.min(
                l,
                Math.min(o,n)
            )
        );
    }
}
```

---

# C++ Solution 1 (Frequency Array)

```cpp
class Solution {
public:

    int maxNumberOfBalloons(string text) {

        vector<int> frequency(26,0);

        for(char ch : text){

            frequency[ch-'a']++;
        }

        return min(
            min(
                frequency['b'-'a'],
                frequency['a'-'a']
            ),
            min(
                frequency['l'-'a']/2,
                min(
                    frequency['o'-'a']/2,
                    frequency['n'-'a']
                )
            )
        );
    }
};
```

---

# C++ Solution 2 (unordered_map)

```cpp
class Solution {
public:

    int maxNumberOfBalloons(string text) {

        unordered_map<char,int> frequency;

        for(char ch : text){

            frequency[ch]++;
        }

        int b = frequency['b'];

        int a = frequency['a'];

        int l = frequency['l']/2;

        int o = frequency['o']/2;

        int n = frequency['n'];

        return min(
            min(b,a),
            min(
                l,
                min(o,n)
            )
        );
    }
};
```
