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

# 520. Detect Capital

# Intuition

A word uses capitals correctly if:

1. All letters are uppercase

```text
"USA"
```

2. All letters are lowercase

```text
"leetcode"
```

3. Only first letter is uppercase

```text
"Google"
```

Otherwise:

```text
"FlaG" ❌
```

Return:
- true if capitalization is valid
- false otherwise

---

# Approaches

This problem can be solved using:

1. Built-in String Functions
2. Manual Uppercase Counting

---

# Approach 1 — Built-in Functions

## Idea

Check three conditions:

- word == uppercase version
- word == lowercase version
- first letter uppercase and remaining lowercase

If any condition is true:
- return true

---

# Flow Diagram

```text
Check ALL uppercase

        ↓

Check ALL lowercase

        ↓

Check First uppercase
and remaining lowercase

        ↓

Any valid ?

        ↓

YES → true
NO  → false
```

---

# Diagram

```text
word = "Google"

G → uppercase ✅
oogle → lowercase ✅

Valid capitalization
```

---

# Approach 2 — Manual Counting

## Idea

Count:
- number of uppercase letters

Valid cases:
- uppercase count == length
- uppercase count == 0
- uppercase count == 1 and first letter uppercase

---

# Counting Flow

```text
Traverse word

        ↓

Count uppercase letters

        ↓

Check valid cases
```

---

# Diagram

```text
word = "USA"

Uppercase count = 3

length = 3

Valid ✅
```

---

# Dry Run

Input:

```text
word = "FlaG"
```

---

## Counting Uppercase

```text
F → uppercase
l → lowercase
a → lowercase
G → uppercase
```

Uppercase count:

```text
2
```

---

## Check Conditions

```text
upper == length ? ❌
upper == 0 ? ❌
upper == 1 and first uppercase ? ❌
```

Return:

```text
false
```

---

# Complexity

## Approach 1 — Built-in Functions

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2 — Manual Counting

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Java Solution 1 — Built-in Functions

```java
class Solution {

    public boolean detectCapitalUse(String word) {

        if (word.equals(word.toUpperCase())) {

            return true;
        }

        if (word.equals(word.toLowerCase())) {

            return true;
        }

        if (Character.isUpperCase(word.charAt(0)) &&
            word.substring(1).equals(word.substring(1).toLowerCase())) {

            return true;
        }

        return false;
    }
}
```

---

# Java Solution 2 — Manual Counting

```java
class Solution {

    public boolean detectCapitalUse(String word) {

        int upper = 0;

        for (char ch : word.toCharArray()) {

            if (Character.isUpperCase(ch)) {

                upper++;
            }
        }

        if (upper == word.length()) {

            return true;
        }

        if (upper == 0) {

            return true;
        }

        if (upper == 1 && Character.isUpperCase(word.charAt(0))) {

            return true;
        }

        return false;
    }
}
```

---

# C++ Solution 1 — Built-in Functions

```cpp
class Solution {
public:

    bool detectCapitalUse(string word) {

        string upper = word;

        string lower = word;

        transform(upper.begin(), upper.end(), upper.begin(), ::toupper);

        transform(lower.begin(), lower.end(), lower.begin(), ::tolower);

        if (word == upper) {

            return true;
        }

        if (word == lower) {

            return true;
        }

        if (isupper(word[0])) {

            bool valid = true;

            for (int i = 1; i < word.length(); i++) {

                if (!islower(word[i])) {

                    valid = false;

                    break;
                }
            }

            return valid;
        }

        return false;
    }
};
```

---

# C++ Solution 2 — Manual Counting

```cpp
class Solution {
public:

    bool detectCapitalUse(string word) {

        int upper = 0;

        for (char ch : word) {

            if (isupper(ch)) {

                upper++;
            }
        }

        if (upper == word.length()) {

            return true;
        }

        if (upper == 0) {

            return true;
        }

        if (upper == 1 && isupper(word[0])) {

            return true;
        }

        return false;
    }
};
```