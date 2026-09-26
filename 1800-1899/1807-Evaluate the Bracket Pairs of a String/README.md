# 1807. Evaluate the Bracket Pairs of a String

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

Given a string `s` and a `knowledge` list of key-value pairs, replace every bracket pair `(key)` in the string with its corresponding value.

If the key does not exist in `knowledge`, replace the whole bracket pair with:

```text
?
```

There are no nested bracket pairs.

### Example

```text
s = "(name)is(age)yearsold"

knowledge = [
    ["name", "bob"],
    ["age", "two"]
]
```

Evaluation:

```text
(name) → bob
(age)  → two
```

Result:

```text
"bobistwoyearsold"
```

Another example:

```text
s = "hi(name)"
knowledge = [["a","b"]]
```

Since `name` is unknown:

```text
(name) → ?
```

Result:

```text
"hi?"
```

The official constraints allow `s.length` and `knowledge.length` up to `10^5`, keys and values up to length `10`, and guarantee that bracket pairs are not nested. citeturn552842view0

---

# 🎯 What Is the Problem Really Asking?

There are only two operations:

```text
1. Store key → value
2. Scan s and evaluate every (key)
```

Because there are **no nested brackets**, every bracket pair can be processed independently from left to right.

```text
Build HashMap
     ↓
Scan String
     ↓
Normal character?
 ┌───────┴────────┐
Yes              No
 │                 │
 ▼                 ▼
append char    find closing ')'
                   │
                   ▼
                extract key
                   │
                   ▼
              HashMap lookup
                /        \
             found      missing
               │           │
               ▼           ▼
             value          ?
               │           │
               └─────┬─────┘
                     ▼
                  append
                     │
                     ▼
                  continue
```

---

# 💡 Core Insight

The problem is a combination of:

```text
HashMap Lookup
+
Linear String Parsing
```

Convert:

```text
knowledge
```

into:

```text
key → value
```

Then process the string once.

Everything outside brackets is copied unchanged.

Everything inside brackets becomes a lookup:

```text
(key)
  ↓
map[key] if present
  ↓
? if absent
```

---

# 🚀 Approach 1 — Single-Pass Parsing with HashMap

## 💡 Idea

First build a `HashMap` from `knowledge`.

Example:

```text
name → bob
age  → two
```

Then scan `s`.

### Normal character

If:

```text
s[i] != '('
```

append it directly.

### Opening bracket

If:

```text
s[i] == '('
```

find the next `)`.

The characters between them are the key:

```text
(name)
 ↑  ↑
key = "name"
```

Look up the key:

```text
key exists → append value
key missing → append '?'
```

Then jump to the character after `)`.

---

## 🏗️ Approach 1 — Architecture Diagram

```text
                         INPUT
                ┌─────────────────────┐
                │ s + knowledge[][]   │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Build HashMap        │
                │ key → value          │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Start i = 0         │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │      s[i] == '(' ? │
                └───────┬──────┬──────┘
                        │ No    │ Yes
                        ▼       ▼
             ┌──────────────┐  ┌──────────────────┐
             │ append s[i]  │  │ find next ')'     │
             │ i++          │  │ extract key      │
             └──────┬───────┘  └─────────┬────────┘
                    │                    │
                    │                    ▼
                    │          ┌──────────────────┐
                    │          │ key in HashMap ? │
                    │          └───────┬─────┬────┘
                    │                  │ Yes │ No
                    │                  ▼     ▼
                    │               value    '?'
                    │                  │       │
                    │                  └───┬───┘
                    │                      ▼
                    │                append result
                    │                      │
                    │                      ▼
                    │                 jump after ')'
                    │
                    └──────────────┐
                                   ▼
                          i < s.length() ?
                              /                                    Yes        No
                             │          │
                             └─ repeat  ▼
                                      Result
```

---

## 🔄 Approach 1 — Data Flow

```text
knowledge
    │
    ▼
HashMap<String, String>
    │
    │
s ──┴──→ Left-to-Right Scan
             │
             ├── normal char ─────→ append directly
             │
             └── '(' ─────────────→ find ')'
                                      │
                                      ▼
                                    key
                                      │
                                      ▼
                               HashMap lookup
                                  /                                       found      missing
                                 │           │
                                 ▼           ▼
                               value         ?
                                 │           │
                                 └─────┬─────┘
                                       ▼
                                  append result
```

---

## 🧪 Approach 1 — Dry Run

```text
s = "(name)is(age)yearsold"

knowledge:
name → bob
age  → two
```

Start at index `0`:

```text
(name)
```

Extract:

```text
key = "name"
```

Lookup:

```text
name → bob
```

Result:

```text
"bob"
```

Continue scanning:

```text
is
```

These are normal characters:

```text
"bobis"
```

Next bracket:

```text
(age)
```

Extract:

```text
key = "age"
```

Lookup:

```text
age → two
```

Result:

```text
"bobistwo"
```

Append the remaining:

```text
yearsold
```

Final:

```text
"bobistwoyearsold"
```

---

## ✅ Approach 1 — Why It Works

Every character belongs to one of two cases:

```text
Outside a bracket pair
OR
Inside a bracket pair
```

Outside brackets:

```text
copy unchanged
```

Inside brackets:

```text
extract key
→ lookup value
→ use '?' if missing
```

Because there are no nested brackets, the next `)` always finishes the current key.

Therefore every part of the string is evaluated exactly once according to the problem rules.

---

## 💻 Approach 1 — Java

```java
import java.util.*;

//Approach-1 (Single-Pass Parsing with HashMap)
//T.C : O(n + k)
//S.C : O(k)

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
                continue;
            }

            int j = i + 1;

            while (s.charAt(j) != ')') {
                j++;
            }

            String key = s.substring(i + 1, j);

            ans.append(map.getOrDefault(key, "?"));

            i = j + 1;
        }

        return ans.toString();
    }
}
```

---

## 💻 Approach 1 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Single-Pass Parsing with HashMap)
//T.C : O(n + k)
//S.C : O(k)

class Solution {
public:
    string evaluate(
        string s,
        vector<vector<string>>& knowledge
    ) {
        unordered_map<string, string> mp;

        for (auto& pair : knowledge) {
            mp[pair[0]] = pair[1];
        }

        string ans;

        for (int i = 0; i < s.size(); ) {
            if (s[i] != '(') {
                ans += s[i];
                i++;
                continue;
            }

            int j = i + 1;

            while (s[j] != ')') {
                j++;
            }

            string key = s.substr(i + 1, j - i - 1);

            if (mp.count(key)) {
                ans += mp[key];
            } else {
                ans += '?';
            }

            i = j + 1;
        }

        return ans;
    }
};
```

---

## ⏱️ Approach 1 — Complexity

Let:

```text
n = length of s
k = number of knowledge entries
```

With average `O(1)` HashMap lookup:

```text
Time Complexity  : O(n + total knowledge size)
Space Complexity : O(k)
```

Since keys and values have bounded length, this is effectively linear in the input size. citeturn552842view0

---

# ⚡ Approach 2 — Two-Pointer Parsing with Helper

## 💡 Idea

The second solution keeps the same optimal `HashMap`, but separates bracket parsing into a helper.

The main loop handles only:

```text
normal character
OR
bracket expression
```

When a bracket begins, the helper:

```text
1. starts after '('
2. moves until ')'
3. builds the key
4. returns the key
```

The main loop then performs the dictionary lookup.

This creates a cleaner parser structure while keeping the same linear-time behavior.

---

## 🏗️ Approach 2 — Architecture Diagram

```text
                         INPUT STRING
                              │
                              ▼
                    ┌───────────────────┐
                    │   Scan position i │
                    └─────────┬─────────┘
                              │
                              ▼
                    ┌───────────────────┐
                    │    '(' found?     │
                    └──────┬───────┬────┘
                           │ No    │ Yes
                           ▼       ▼
                    ┌───────────┐ ┌───────────────────┐
                    │append char│ │ parseBracketPair()│
                    └─────┬─────┘ └─────────┬─────────┘
                          │                 │
                          │                 ▼
                          │          ┌──────────────┐
                          │          │ Extract key  │
                          │          │ Find next i  │
                          │          └──────┬───────┘
                          │                 │
                          │                 ▼
                          │          ┌──────────────┐
                          │          │ HashMap      │
                          │          │ lookup       │
                          │          └──────┬───────┘
                          │                 │
                          │          ┌──────┴──────┐
                          │          ▼             ▼
                          │        value           ?
                          │          │             │
                          │          └──────┬──────┘
                          │                 ▼
                          │            append result
                          │                 │
                          └─────────────────┘
                                    │
                                    ▼
                              continue scan
```

---

## 🔄 Approach 2 — Data Flow

```text
Current Index
     │
     ▼
Is s[i] '(' ?
     │
 ┌───┴───┐
 No      Yes
 │         │
 ▼         ▼
append   bracket parser
char        │
            ▼
           key
            │
            ▼
       HashMap lookup
            │
       ┌────┴────┐
       ▼         ▼
    found      missing
       │         │
       ▼         ▼
     value        ?
       │         │
       └────┬────┘
            ▼
       append result
            │
            ▼
        update i
```

---

## 🧪 Approach 2 — Dry Run

Consider:

```text
s = "hi(name)!"
knowledge = [
    ["name","bob"]
]
```

Build:

```text
name → bob
```

### Position `0`

```text
h
```

Append:

```text
"h"
```

### Position `1`

```text
i
```

Append:

```text
"hi"
```

### Position `2`

```text
(
```

Call the bracket parser.

It moves until:

```text
)
```

and extracts:

```text
name
```

Lookup:

```text
name → bob
```

Append:

```text
"hibob"
```

Move to the character after `)`.

### Final character

```text
!
```

Append:

```text
"hibob!"
```

Final answer:

```text
"hibob!"
```

---

## ✅ Approach 2 — Why It Works

The helper treats each bracket pair as a single logical token:

```text
(name)
```

becomes:

```text
key = "name"
```

and the main pointer jumps directly after the closing bracket.

Because the problem guarantees no nested brackets, the helper can always find the correct ending `)` with a simple forward scan.

The main loop therefore processes the complete string exactly once.

---

## 💻 Approach 2 — Java

```java
import java.util.*;

//Approach-2 (Two-Pointer Parsing with Helper)
//T.C : O(n + k)
//S.C : O(k)

class Solution2 {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
                continue;
            }

            int end = i + 1;

            while (s.charAt(end) != ')') {
                end++;
            }

            String key = getKey(s, i + 1, end);

            ans.append(map.getOrDefault(key, "?"));

            i = end + 1;
        }

        return ans.toString();
    }

    private String getKey(String s, int left, int right) {
        StringBuilder key = new StringBuilder();

        while (left < right) {
            key.append(s.charAt(left));
            left++;
        }

        return key.toString();
    }
}
```

---

## 💻 Approach 2 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-2 (Two-Pointer Parsing with Helper)
//T.C : O(n + k)
//S.C : O(k)

class Solution2 {
public:
    string evaluate(
        string s,
        vector<vector<string>>& knowledge
    ) {
        unordered_map<string, string> mp;

        for (auto& pair : knowledge) {
            mp[pair[0]] = pair[1];
        }

        string ans;

        int i = 0;

        while (i < s.size()) {
            if (s[i] != '(') {
                ans += s[i];
                i++;
                continue;
            }

            int end = i + 1;

            while (s[end] != ')') {
                end++;
            }

            string key = getKey(s, i + 1, end);

            if (mp.count(key)) {
                ans += mp[key];
            } else {
                ans += '?';
            }

            i = end + 1;
        }

        return ans;
    }

private:
    string getKey(
        const string& s,
        int left,
        int right
    ) {
        string key;

        while (left < right) {
            key += s[left];
            left++;
        }

        return key;
    }
};
```

---

## ⏱️ Approach 2 — Complexity

```text
Time Complexity  : O(n + total knowledge size)
Space Complexity : O(k)
```

The string is processed from left to right and every key uses average `O(1)` HashMap lookup. citeturn552842view0

---

# 🆚 Approach 1 vs Approach 2

| Feature          | Approach 1                | Approach 2                  |
| ---------------- | ------------------------- | --------------------------- |
| Main idea        | Direct single-pass parser | Helper-based bracket parser |
| HashMap          | ✅                        | ✅                          |
| Time             | `O(n + k)`                | `O(n + k)`                  |
| Extra Space      | `O(k)`                    | `O(k)`                      |
| Bracket handling | Inline                    | Separate helper             |
| Code style       | Compact                   | More modular                |
| Best for         | Simple implementation     | Cleaner parsing structure   |

Both are optimal for the given constraints.

---

# 🧠 Why HashMap Is the Right Data Structure

Suppose:

```text
knowledge = [
    ["name","bob"],
    ["age","two"],
    ["city","delhi"]
]
```

We need fast answers to:

```text
What is the value for this key?
```

A HashMap gives:

```text
name → bob
age  → two
city → delhi
```

with average:

```text
O(1)
```

lookup.

Without a HashMap, searching the complete `knowledge` list for every bracket pair could make the algorithm much slower.

---

# 🎯 Pattern Recognition

This problem belongs to:

```text
String Parsing
      +
HashMap Lookup
      +
Two Pointers
```

Whenever you see:

```text
string contains tokens
+
dictionary stores token values
+
replace tokens while scanning
```

think:

```text
Build Map
   ↓
Scan String
   ↓
Detect Token
   ↓
Lookup
   ↓
Append Replacement
```

---

# ⚠️ Common Mistakes

### 1. Searching `knowledge` for every key

Do this once:

```text
knowledge → HashMap
```

not:

```text
for every bracket → scan knowledge
```

---

### 2. Evaluating letters outside brackets

For:

```text
(a)aaa
```

only:

```text
(a)
```

is replaced.

The standalone:

```text
aaa
```

must remain unchanged. citeturn552842view0

---

### 3. Forgetting unknown keys

For:

```text
(name)
```

if `name` is absent:

```text
(name) → ?
```

---

### 4. Not jumping after `)`

After processing:

```text
(name)
```

set:

```text
i = positionAfterClosingBracket
```

Otherwise the bracket contents may be processed again.

---

### 5. Using repeated global replacement

Repeatedly modifying the whole string for every key can create unnecessary rescans.

A single left-to-right parser is cleaner and linear.

---

# 🗣️ Interview Explanation

> "I first convert the knowledge list into a HashMap from key to value. Then I scan the string from left to right. Normal characters are appended directly. When I see an opening bracket, I find the matching closing bracket, extract the key, perform a HashMap lookup, and append either the corresponding value or `?`. Since the problem guarantees there are no nested brackets, a simple linear scan is sufficient."

---

# 🧾 Quick Revision

```text
knowledge
    ↓
HashMap<key, value>
    ↓
Scan s
    ↓
Normal character?
 ┌───────┴───────┐
Yes             No
 │                │
 ▼                ▼
copy char      find ')'
                 │
                 ▼
               key
                 │
                 ▼
            HashMap lookup
              /                 found     missing
             │          │
             ▼          ▼
           value         ?
             │           │
             └─────┬─────┘
                   ▼
                append
                   │
                   ▼
               continue
```

---

# ⭐ One-Line Insight

> **Build a `key → value` HashMap, scan the string once, and replace each `(key)` with its value or `?`.**

---

# 📊 Complexity Summary

Let:

```text
n = s.length()
k = knowledge.length
```

Both approaches use:

```text
Time  : O(n + total knowledge size) average
Space : O(k)
```

because the string is scanned once and dictionary lookups are average `O(1)`. citeturn552842view0

---

# 🏷️ Tags

`String, Hash Table, HashMap, String Parsing, Two Pointers, Simulation, StringBuilder, Dictionary, Linear Scan, LeetCode, Medium`

---

# 📚 Final Takeaway

The complete problem can be reduced to:

```text
             knowledge
                 │
                 ▼
          key → value Map
                 │
                 ▼
              Scan s
                 │
        ┌────────┴────────┐
        │                 │
   normal char          '('
        │                 │
        ▼                 ▼
     copy it          find ')'
                          │
                          ▼
                         key
                          │
                          ▼
                    map lookup
                     /                         found      missing
                    │           │
                    ▼           ▼
                  value         ?
                    │           │
                    └─────┬─────┘
                          ▼
                       append
                          │
                          ▼
                       continue
```

The key implementation idea is simple:

```text
HashMap + Left-to-Right Scan
```

No stack is needed because the input guarantees that bracket pairs are not nested. citeturn552842view0
