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

# 3612. Process String with Special Operations I

# Intuition

We are given a string containing lowercase letters and special characters.

Each special character performs a unique operation on the currently formed string.

```text
'*' → Remove last character

'#' → Duplicate current string

'%' → Reverse current string
```

Normal lowercase letters are simply appended.

The goal is to simulate all operations and return the final string.

---

# Operations Table

| Character | Operation                |
| --------- | ------------------------ |
| a-z       | Append character         |
| \*        | Delete last character    |
| #         | Duplicate current string |
| %         | Reverse current string   |

---

# Approach 1 — StringBuilder Simulation

## Idea

Maintain a StringBuilder.

For every character:

```text
letter → append

* → remove last character

# → duplicate current string

% → reverse current string
```

This directly simulates the operations.

---

# Flowchart

```text
Read Character

       │
       ▼

 Is Lowercase ?

   ┌───┴───┐
   │       │
 YES      NO
   │       │
   ▼       ▼

Append    Special Character

           │
           ▼

    ┌──────┼──────┐
    │      │      │
    ▼      ▼      ▼

   *      #      %

Delete Duplicate Reverse
 Last
```

---

# Example Visualization

Input

```text
ab#c%
```

---

Step 1

```text
a

Output = a
```

---

Step 2

```text
b

Output = ab
```

---

Step 3

```text
#

Output = abab
```

---

Step 4

```text
c

Output = ababc
```

---

Step 5

```text
%

Output = cbaba
```

---

Final Answer

```text
cbaba
```

---

# Detailed Dry Run

Input

```text
a#b*
```

---

Process a

```text
a
```

---

Process #

```text
aa
```

---

Process b

```text
aab
```

---

Process \*

```text
aa
```

---

Answer

```text
aa
```

---

# Memory Visualization

```text
Input

a # b %

      │

      ▼

a

      ▼

aa

      ▼

aab

      ▼

baa
```

---

# Approach 2 — ArrayList Simulation

## Idea

Store all characters inside:

```text
ArrayList<Character>
```

Perform operations directly on the list.

Benefits:

```text
Easy insertion

Easy deletion

Easy reversal
```

---

# Flow Diagram

```text
Input Character

        │
        ▼

Store In ArrayList

        │
        ▼

Operation ?

 ┌──────┼───────┐
 │      │       │
 ▼      ▼       ▼

 *      #       %

Delete Duplicate Reverse

        │
        ▼

Build Final String
```

---

# Approach 3 — Optimized StringBuilder

## Idea

Use StringBuilder only.

Instead of creating another structure:

```text
Append letters

Delete using deleteCharAt()

Duplicate using append(toString())

Reverse using reverse()
```

This keeps implementation concise.

---

# Internal State Visualization

Input

```text
abc#%
```

---

After abc

```text
abc
```

---

After #

```text
abcabc
```

---

After %

```text
cbacba
```

---

Final

```text
cbacba
```

---

# Complexity Analysis

## Approach 1 — StringBuilder

### Time Complexity

```text
O(n²)
```

Because:

```text
# → duplicates current string

% → reverses current string
```

Both may process the entire string.

### Space Complexity

```text
O(n)
```

---

## Approach 2 — ArrayList

### Time Complexity

```text
O(n²)
```

### Space Complexity

```text
O(n)
```

---

## Approach 3 — Optimized StringBuilder

### Time Complexity

```text
O(n²)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 1

```java
//Approach-1 (StringBuilder Simulation)
//T.C : O(n²)
//S.C : O(n)

class Solution {

    public String processStr(String s) {

        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '*') {

                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            else if (ch == '#') {

                StringBuilder duplicate = new StringBuilder(sb);

                sb.append(duplicate);
            }
            else if (ch == '%') {

                sb.reverse();
            }
            else {

                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
```

---

# Java Solution 2

```java
//Approach-2 (ArrayList Simulation)
//T.C : O(n²)
//S.C : O(n)

import java.util.*;

class Solution {

    public String processStr(String input) {

        List<Character> chars = new ArrayList<>();

        for (char current : input.toCharArray()) {

            if (current >= 'a' && current <= 'z') {

                chars.add(current);
            }
            else if (current == '*') {

                if (!chars.isEmpty()) {

                    chars.remove(chars.size() - 1);
                }
            }
            else if (current == '#') {

                int size = chars.size();

                for (int i = 0; i < size; i++) {

                    chars.add(chars.get(i));
                }
            }
            else {

                Collections.reverse(chars);
            }
        }

        StringBuilder answer = new StringBuilder();

        for (char ch : chars) {

            answer.append(ch);
        }

        return answer.toString();
    }
}
```

---

# Java Solution 3

```java
//Approach-3 (Optimized StringBuilder)
//T.C : O(n²)
//S.C : O(n)

class Solution {

    public String processStr(String data) {

        StringBuilder output = new StringBuilder();

        for (char current : data.toCharArray()) {

            if (Character.isLowerCase(current)) {

                output.append(current);
            }
            else if (current == '*') {

                if (output.length() > 0) {

                    output.deleteCharAt(output.length() - 1);
                }
            }
            else if (current == '#') {

                output.append(output.toString());
            }
            else {

                output.reverse();
            }
        }

        return output.toString();
    }
}
```

---

# C++ Solution 1

```cpp
//Approach-1 (String Simulation)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    string processStr(string s) {

        string result;

        for (char ch : s) {

            if (ch == '*') {

                if (!result.empty()) {

                    result.pop_back();
                }
            }
            else if (ch == '#') {

                result += result;
            }
            else if (ch == '%') {

                reverse(result.begin(), result.end());
            }
            else {

                result.push_back(ch);
            }
        }

        return result;
    }
};
```

---

# C++ Solution 2

```cpp
//Approach-2 (Vector Simulation)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    string processStr(string input) {

        vector<char> characters;

        for (char current : input) {

            if (current >= 'a' && current <= 'z') {

                characters.push_back(current);
            }
            else if (current == '*') {

                if (!characters.empty()) {

                    characters.pop_back();
                }
            }
            else if (current == '#') {

                int size = characters.size();

                for (int i = 0; i < size; i++) {

                    characters.push_back(characters[i]);
                }
            }
            else {

                reverse(
                    characters.begin(),
                    characters.end()
                );
            }
        }

        string answer;

        for (char ch : characters) {

            answer += ch;
        }

        return answer;
    }
};
```

---

# C++ Solution 3

```cpp
//Approach-3 (StringBuilder Equivalent)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    string processStr(string input) {

        string output;

        for (char current : input) {

            if (islower(current)) {

                output.push_back(current);
            }
            else if (current == '*') {

                if (!output.empty()) {

                    output.pop_back();
                }
            }
            else if (current == '#') {

                output += output;
            }
            else {

                reverse(
                    output.begin(),
                    output.end()
                );
            }
        }

        return output;
    }
};
```
