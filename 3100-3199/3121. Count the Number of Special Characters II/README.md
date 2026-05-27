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

# 3121. Count the Number of Special Characters II

# Intuition

A character is called special if:

- lowercase character appears before uppercase character

Example:

```text
word = "aaAbcBC"

a appears before A ✅
b appears before B ✅
c appears before C ✅
```

Answer:

```text
3
```

---

# Approaches

This problem can be solved using:

1. Array Index Tracking
2. HashMap Based Tracking

---

# Approach 1 — Array Index Tracking

## Idea

Store:

- last occurrence of lowercase letters
- first occurrence of uppercase letters

Then check:

```text
last lowercase index < first uppercase index
```

If true:

- character is special

---

# Flow Diagram

```text
Traverse String

        ↓

Store:
Last lowercase index

Store:
First uppercase index

        ↓

Compare indices

        ↓

lowerLast < upperFirst ?

        ↓

YES → special character
```

---

# Detailed Diagram

Input:

```text
word = "aaAbcBC"
```

---

## Lowercase Last Occurrence

```text
a → 1
b → 3
c → 4
```

---

## Uppercase First Occurrence

```text
A → 2
B → 5
C → 6
```

---

## Comparison

```text
a : 1 < 2 ✅
b : 3 < 5 ✅
c : 4 < 6 ✅
```

Answer:

```text
3
```

---

# Approach 2 — HashMap Based Tracking

## Idea

Instead of arrays:

- use HashMaps

Store:

- last lowercase position
- first uppercase position

Then compare positions for every character.

---

# HashMap Flow

```text
Traverse String

        ↓

Store lowercase positions

Store uppercase positions

        ↓

Check:
lastLower < firstUpper

        ↓

YES → count++
```

---

# HashMap Diagram

```text
word = "AbBCab"

Lowercase:
a → 4
b → 5

Uppercase:
A → 0
B → 1
C → 3
```

Check:

```text
a : 4 < 0 ❌
b : 5 < 1 ❌
```

Answer:

```text
0
```

---

# Dry Run

Input:

```text
word = "aaAbcBC"
```

---

## Step 1

Store lowercase last positions.

```text
a → 1
b → 3
c → 4
```

---

## Step 2

Store uppercase first positions.

```text
A → 2
B → 5
C → 6
```

---

## Step 3

Compare:

```text
a : 1 < 2 ✅
b : 3 < 5 ✅
c : 4 < 6 ✅
```

Answer:

```text
3
```

---

# Complexity

## Approach 1 — Array Tracking

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2 — HashMap Tracking

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 1 — Array Tracking

```java
class Solution {

    public int numberOfSpecialChars(String word) {

        int[] lowerLast = new int[26];

        int[] upperFirst = new int[26];

        Arrays.fill(lowerLast, -1);

        Arrays.fill(upperFirst, -1);

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {

                lowerLast[ch - 'a'] = i;
            }
            else {

                if (upperFirst[ch - 'A'] == -1) {

                    upperFirst[ch - 'A'] = i;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            if (lowerLast[i] != -1 &&
                upperFirst[i] != -1 &&
                lowerLast[i] < upperFirst[i]) {

                count++;
            }
        }

        return count;
    }
}
```

---

# Java Solution 2 — HashMap Tracking

```java
class Solution {

    public int numberOfSpecialChars(String word) {

        HashMap<Character, Integer> firstUpper = new HashMap<>();

        HashMap<Character, Integer> lastLower = new HashMap<>();

        int c = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {

                lastLower.put(ch, i);
            }
            else {

                if (!firstUpper.containsKey(ch)) {

                    firstUpper.put(ch, i);
                }
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {

            char upper = Character.toUpperCase(ch);

            if (lastLower.containsKey(ch) &&
                firstUpper.containsKey(upper)) {

                if (lastLower.get(ch) < firstUpper.get(upper)) {

                    c++;
                }
            }
        }

        return c;
    }
}
```

---

# C++ Solution 1 — Array Tracking

```cpp
class Solution {
public:

    int numberOfSpecialChars(string word) {

        vector<int> lowerLast(26, -1);

        vector<int> upperFirst(26, -1);

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (islower(ch)) {

                lowerLast[ch - 'a'] = i;
            }
            else {

                if (upperFirst[ch - 'A'] == -1) {

                    upperFirst[ch - 'A'] = i;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            if (lowerLast[i] != -1 &&
                upperFirst[i] != -1 &&
                lowerLast[i] < upperFirst[i]) {

                count++;
            }
        }

        return count;
    }
};
```

---

# C++ Solution 2 — HashMap Tracking

```cpp
class Solution {
public:

    int numberOfSpecialChars(string word) {

        unordered_map<char, int> firstUpper;

        unordered_map<char, int> lastLower;

        int c = 0;

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (islower(ch)) {

                lastLower[ch] = i;
            }
            else {

                if (!firstUpper.count(ch)) {

                    firstUpper[ch] = i;
                }
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {

            char upper = toupper(ch);

            if (lastLower.count(ch) &&
                firstUpper.count(upper)) {

                if (lastLower[ch] < firstUpper[upper]) {

                    c++;
                }
            }
        }

        return c;
    }
};
```
