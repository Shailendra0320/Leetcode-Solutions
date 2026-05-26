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

# 3120. Count the Number of Special Characters I

# Intuition

A character is called special if:

- both lowercase and uppercase versions exist in the string

Example:

```text
word = "aaAbcBC"

Special characters:
a/A
b/B
c/C
```

Answer:

```text
3
```

---

# Approaches

This problem can be solved using:

1. Frequency Arrays
2. HashSet
3. Brute Force

---

# Approach 1 — Frequency Arrays

## Idea

Maintain:

- one array for lowercase letters
- one array for uppercase letters

Mark characters while traversing.

Finally:

- count indices where both arrays are true

---

# Frequency Array Flow

```text
Traverse string

        ↓

Mark lowercase letters

Mark uppercase letters

        ↓

Check common indices

        ↓

Count answer
```

---

# Diagram

```text
word = "aAbB"

lowercase:
[a,b]

uppercase:
[A,B]

Both exist:
a/A
b/B
```

Answer:

```text
2
```

---

# Approach 2 — HashSet

## Idea

Store:

- lowercase letters in one set
- uppercase letters converted to lowercase in another set

Then:

- count common characters

---

# HashSet Flow

```text
Insert lowercase chars

        ↓

Insert uppercase chars as lowercase

        ↓

Find intersection

        ↓

Count answer
```

---

# Diagram

```text
lower:
[a,b,c]

upper:
[a,b]

Intersection:
[a,b]
```

Answer:

```text
2
```

---

# Approach 3 — Brute Force

## Idea

For every lowercase character:

- search entire string for uppercase version

Avoid duplicate counting using boolean array.

---

# Brute Force Flow

```text
Take lowercase char

        ↓

Find uppercase version

        ↓

Exists ?

        ↓

YES → count++
```

---

# Diagram

```text
word = "aaAb"

Check:
a → A exists ✅
b → B not exists ❌
```

Answer:

```text
1
```

---

# Dry Run

Input:

```text
word = "aaAbcBC"
```

---

## Frequency Array Approach

### Step 1

Traverse string.

Mark:

```text
lowercase:
a,b,c

uppercase:
A,B,C
```

---

### Step 2

Check common characters.

```text
a/A ✅
b/B ✅
c/C ✅
```

Answer:

```text
3
```

---

# Complexity

## Approach 1 — Frequency Arrays

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2 — HashSet

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

## Approach 3 — Brute Force

### Time Complexity

```text
O(n^2)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution 1 — Frequency Arrays

```java
//Approach-1 (Using Frequency Arrays)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int numberOfSpecialChars(String word) {

        boolean[] lowercase = new boolean[26];

        boolean[] uppercase = new boolean[26];

        for (char c : word.toCharArray()) {

            if (c >= 'a' && c <= 'z') {

                lowercase[c - 'a'] = true;
            }
            else if (c >= 'A' && c <= 'Z') {

                uppercase[c - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            if (lowercase[i] && uppercase[i]) {

                count++;
            }
        }

        return count;
    }
}
```

---

# Java Solution 2 — HashSet

```java
//Approach-2 (Using HashSet)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int numberOfSpecialChars(String word) {

        Set<Character> lower = new HashSet<>();

        Set<Character> upper = new HashSet<>();

        for (char ch : word.toCharArray()) {

            if (Character.isLowerCase(ch)) {

                lower.add(ch);
            }
            else if (Character.isUpperCase(ch)) {

                upper.add(Character.toLowerCase(ch));
            }
        }

        int count = 0;

        for (char ch : lower) {

            if (upper.contains(ch)) {

                count++;
            }
        }

        return count;
    }
}
```

---

# Java Solution 3 — Brute Force

```java
//Approach-3 (Brute Force)
//T.C : O(n^2)
//S.C : O(1)

class Solution {

    public int numberOfSpecialChars(String word) {

        int count = 0;

        boolean[] arr = new boolean[26];

        Arrays.fill(arr, false);

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (Character.isUpperCase(ch)) {

                continue;
            }
            else {

                char upper = Character.toUpperCase(ch);

                for (int j = 0; j < word.length(); j++) {

                    if (word.charAt(j) == upper && !arr[ch - 'a']) {

                        count++;

                        arr[ch - 'a'] = true;
                    }
                }
            }
        }

        return count;
    }
}
```

---

# C++ Solution 1 — Frequency Arrays

```cpp
//Approach-1 (Using Frequency Arrays)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        vector<bool> lowercase(26, false);

        vector<bool> uppercase(26, false);

        for (char c : word) {

            if (c >= 'a' && c <= 'z') {

                lowercase[c - 'a'] = true;
            }
            else if (c >= 'A' && c <= 'Z') {

                uppercase[c - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            if (lowercase[i] && uppercase[i]) {

                count++;
            }
        }

        return count;
    }
};
```

---

# C++ Solution 2 — HashSet

```cpp
//Approach-2 (Using HashSet)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        unordered_set<char> lower;

        unordered_set<char> upper;

        for (char ch : word) {

            if (islower(ch)) {

                lower.insert(ch);
            }
            else if (isupper(ch)) {

                upper.insert(tolower(ch));
            }
        }

        int count = 0;

        for (char ch : lower) {

            if (upper.count(ch)) {

                count++;
            }
        }

        return count;
    }
};
```

---

# C++ Solution 3 — Brute Force

```cpp
//Approach-3 (Brute Force)
//T.C : O(n^2)
//S.C : O(1)

class Solution {
public:

    int numberOfSpecialChars(string word) {

        int count = 0;

        vector<bool> arr(26, false);

        for (int i = 0; i < word.length(); i++) {

            char ch = word[i];

            if (isupper(ch)) {

                continue;
            }
            else {

                char upper = toupper(ch);

                for (int j = 0; j < word.length(); j++) {

                    if (word[j] == upper && !arr[ch - 'a']) {

                        count++;

                        arr[ch - 'a'] = true;
                    }
                }
            }
        }

        return count;
    }
};
```
