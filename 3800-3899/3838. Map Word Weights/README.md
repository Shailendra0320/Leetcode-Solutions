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

# 3838. Map Word Weights

# Intuition

Each character has a custom weight:

```text
weights['a']
weights['b']
weights['c']
...
weights['z']
```

For every word:

```text
Calculate Total Weight
```

Then:

```text
Take modulo 26
```

Finally:

```text
Map it to a character
```

using:

```text
'a' + (25 - sum)
```

The generated character is appended to the answer string.

---

# Key Observation

For each word:

```text
wordWeight =
Σ weights[character]
```

After that:

```text
wordWeight %= 26
```

Then:

```text
mappedCharacter

=

'a' + (25 - wordWeight)
```

---

# Approach

## Step 1

Traverse every word.

---

## Step 2

Calculate:

```text
sum of character weights
```

---

## Step 3

Apply:

```text
sum %= 26
```

---

## Step 4

Generate:

```text
(char)('a' + (25 - sum))
```

---

## Step 5

Append character to answer.

---

# Flowchart

```text
Start

   │
   ▼

Take One Word

   │
   ▼

Traverse Characters

   │
   ▼

Add Character Weights

   │
   ▼

Compute

sum % 26

   │
   ▼

Convert To Character

'a' + (25 - sum)

   │
   ▼

Append To Answer

   │
   ▼

More Words ?

 ┌───────┴───────┐
 │               │
Yes             No
 │               │
 ▼               ▼

Continue     Return Answer
```

---

# Visualization

Input

```text
words = ["abc","de"]

weights:

a = 1
b = 2
c = 3
d = 4
e = 5
```

---

Word 1

```text
abc

1 + 2 + 3

=

6
```

Modulo:

```text
6 % 26 = 6
```

Character:

```text
'a' + (25 - 6)

= 't'
```

---

Word 2

```text
de

4 + 5

=

9
```

Modulo:

```text
9 % 26 = 9
```

Character:

```text
'a' + (25 - 9)

= 'q'
```

---

Final Answer

```text
"tq"
```

---

# Detailed Dry Run

Input

```text
words = ["abc"]
```

---

Initial

```text
sum = 0
```

---

Process 'a'

```text
sum += weights[a]
```

---

Process 'b'

```text
sum += weights[b]
```

---

Process 'c'

```text
sum += weights[c]
```

---

Suppose

```text
sum = 6
```

Then

```text
sum %= 26

= 6
```

---

Mapped Character

```text
'a' + (25 - 6)

= 't'
```

---

Answer

```text
"t"
```

---

# Memory Visualization

```text
Word

"abc"

   │
   ▼

 a + b + c

   │
   ▼

 Total Weight

   │
   ▼

 Mod 26

   │
   ▼

 25 - sum

   │
   ▼

 Character

   │
   ▼

 Append To Answer
```

---

# Complexity Analysis

## Time Complexity

```text
O(T)
```

where:

```text
T = Total characters in all words
```

---

## Space Complexity

```text
O(1)
```

Ignoring output string.

---

# Java Solution

```java
class Solution {

    public String mapWordWeights(String[] words, int[] weights) {

        StringBuilder ans = new StringBuilder();

        for (String w : words) {

            int sum = 0;

            for (char c : w.toCharArray()) {

                sum = (sum + weights[c - 'a']) % 26;
            }

            ans.append((char) ('a' + (25 - sum)));
        }

        return ans.toString();
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    string mapWordWeights(vector<string>& words, vector<int>& weights) {

        string answer;

        for (string& word : words) {

            int sum = 0;

            for (char ch : word) {

                sum = (sum + weights[ch - 'a']) % 26;
            }

            answer.push_back('a' + (25 - sum));
        }

        return answer;
    }
};
```
