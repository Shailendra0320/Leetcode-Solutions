# 1541. Minimum Insertions to Balance a Parentheses String — Greedy Counter and Pairing

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

## 🏷️ Tags

`String, Greedy, Stack, Parentheses, Counting, Simulation, String Parsing, Balance, Matching, Linear Scan, Minimum Insertions, LeetCode, Medium`

---

# 📌 Problem

Given a string `s` containing only `'('` and `')'`, return the **minimum number of insertions** required to make it balanced.

The special rule is that **every opening parenthesis `'('` must be matched with two consecutive closing parentheses `'))'`**. The opening parenthesis must appear before its matching pair.

This is different from ordinary parentheses validation: one opening parenthesis needs two closing parentheses, not one.

---

# 🧪 Examples

### Example 1

```text
Input: s = "(()))"
Output: 1
```

The second `'('` has the two closes it needs, but the first `'('` has only one remaining close. Insert one `')'` at the end to make `"(())))"`.

### Example 2

```text
Input: s = "())"
Output: 0
```

The only opening parenthesis already has the required consecutive `'))'` pair.

### Example 3

```text
Input: s = "))())("
Output: 3
```

Insert one `'('` to match the first `'))'`, then insert two `')'` characters to complete the final `'('`.

### Example 4

```text
Input: s = "(((((('
Output: 12
```

There are six opening parentheses. Each needs two closing parentheses, so twelve `')'` characters must be inserted.

---

# 🔍 What Is the Problem Really Asking?

The required unit is not a single `')'`; it is a **consecutive pair**:

```text
'('  ->  '))'
```

For example:

```text
())   -> balanced
()    -> needs one more ')'
))    -> needs an opening '(' before the pair
```

We need to handle three defects:

1. A required pair of closing parentheses is interrupted by a new `'('`.
2. A closing parenthesis appears when no opening requirement is available.
3. The input ends while some opening parentheses still need closing parentheses.

A linear greedy scan can repair each defect immediately without constructing the final string.

---

# 💡 Core Insight

There are two useful `O(n)` approaches.

## Approach 1 — Required-Closing Counter

Track:

```text
need   = number of closing parentheses still required
answer = insertions already made
```

When `'('` appears, add two to `need`. If `need` is odd, a previous pair has one closing parenthesis still outstanding. The new opening would interrupt the required consecutive pair, so insert one `')'` first and reduce `need` by one.

When `')'` appears, reduce `need`. If it becomes negative, this close has no available opening requirement. Insert `'('` before it and set `need = 1`, because the current close satisfies one of the inserted opening's two required closes.

At the end, add any remaining `need`:

```text
answer + need
```

## Approach 2 — Pair the Closing Parentheses

Track:

```text
open   = unmatched opening parentheses
answer = insertions already made
```

When we see `')'`, consume the next character too if it is also `')'`; the pair satisfies the special closing requirement. If the next character is not `')'`, insert one missing close. Then match the close-pair with an unmatched opening. If none exists, insert `'('`.

At the end, each unmatched opening needs two closing parentheses:

```text
answer + 2 * open
```

Both approaches run in `O(n)` time and use `O(1)` auxiliary space.

---

# 🌳 Approach 1 — Greedy Required-Closing Counter

## State Definition

```text
need   = outstanding ')' characters required
answer = insertions already made
```

### When the character is `'('

One opening requires two consecutive closing parentheses:

```text
need += 2
```

If `need` is odd, the outstanding requirement for a previous opening would be interrupted by the new `'('`. Insert one close to finish that previous pair:

```text
answer++
need--
```

Checking parity after adding two is equivalent to checking it before, because adding two does not change odd/even parity.

### When the character is `')'`

The current closing parenthesis satisfies one outstanding requirement:

```text
need--
```

If `need < 0`, it has no opening requirement to satisfy. Insert an opening parenthesis before it:

```text
answer++
need = 1
```

The inserted opening needs two closes; the current `')'` is already one, so one more is left outstanding.

### At the end

Insert all remaining required closes:

```text
answer += need
```

---

# 🏗️ Architecture / Flow Diagram — Approach 1

```text
                       Start
                         |
                         v
                  need = 0, answer = 0
                         |
                         v
                     Read s[i]
                         |
                 +-------+-------+
                 |               |
                '('             ')'
                 |               |
                 v               v
              need += 2        need--
                 |               |
            need is odd?      need < 0?
             /       \         /     \
           Yes        No      Yes     No
            |          |       |       |
            v          |       v       |
     answer++, need--  |  answer++,    |
            |          |  need = 1     |
            +-----+----+-------+-------+
                  |
                  v
               Next char
                  |
                  v
              End of string
                  |
                  v
             answer + need
```

# 🔄 Data Flow — Approach 1

```text
Input -> need=0, answer=0

'(' -> need += 2
       if need is odd: answer++, need--

')' -> need--
       if need < 0: answer++, need=1

End -> answer + need
```

---

# 🧪 Dry Run — Approach 1

Take:

```text
s = "(()))"
```

Initially:

```text
need = 0
answer = 0
```

| Character | Action                     | `need` | `answer` |
| --------- | -------------------------- | -----: | -------: |
| `(`       | Requires two closes        |      2 |        0 |
| `(`       | Requires two more closes   |      4 |        0 |
| `)`       | Satisfies one requirement  |      3 |        0 |
| `)`       | Satisfies one requirement  |      2 |        0 |
| `)`       | Satisfies one requirement  |      1 |        0 |
| End       | Insert one remaining close |      0 |        1 |

Answer:

```text
1
```

## Why the odd-`need` correction matters

Consider `s = "(()())"`. After the first two opening parentheses, `need = 4`. The next close reduces it to `3`, which is odd. The next character is another `'('`, which would interrupt the previous opening's consecutive closing pair. One `')'` must be inserted before the new opening. The algorithm records this with `answer++` and `need--`.

Without this parity correction, a solution could count enough closing parentheses overall but still violate the requirement that the pair be consecutive.

---

# ✅ Correctness — Approach 1

`need` records the closing parentheses still required by the openings already seen.

- Every `'('` increases that requirement by two.
- If a new opening arrives while `need` is odd, a previous pair would be interrupted. At least one `')'` must be inserted before the new opening, and the algorithm inserts exactly one.
- Every `')'` satisfies one outstanding close by decreasing `need`.
- If `need` becomes negative, the close is unmatched. Inserting `'('` is necessary; the current close satisfies one required close for that inserted opening, so `need` becomes `1`.
- At the end, every remaining unit of `need` must be supplied by an inserted `')'`.

Each insertion repairs a forced defect, and no insertion is made when an existing character can satisfy the same requirement. Thus the greedy count is minimal.

---

# 🌳 Approach 2 — Greedy Pairing of Consecutive `')'`

## Idea

This method explicitly treats `'))'` as one required closing unit.

Maintain:

```text
open   = unmatched '(' already seen
answer = insertions
```

For each `'('`, increment `open`.

For each `')'`:

1. If the next character is also `')'`, consume both as one complete close-pair.
2. Otherwise, insert one `')'` to complete the pair and increment `answer`.
3. Match this pair to a previous `'('`. If `open > 0`, decrement `open`; otherwise insert `'('` and increment `answer`.

At the end, add two closers for every unmatched opening:

```text
answer += 2 * open
```

## Important Detail: Skipping the Second Close

When the next character is also `')'`, both have now been consumed as one pair, so the loop index advances past the second one. This prevents using that character twice.

---

# 🏗️ Architecture / Flow Diagram — Approach 2

```text
                         Start
                           |
                           v
                    open = 0, answer = 0
                           |
                           v
                        Read s[i]
                           |
                    +------+------+
                    |             |
                   '('           ')'
                    |             |
                    v             v
                  open++      Next char is ')'?
                                 /       \
                               Yes       No
                                |         |
                                v         v
                         consume pair  insert one ')'
                                \         /
                                 \       /
                                  v     v
                           Match pair to open
                                  |
                           +------+------+
                           |             |
                        open > 0       open == 0
                           |             |
                           v             v
                         open--      insert '('; answer++
                           |
                           v
                       End of string
                           |
                           v
                     answer + 2 * open
```

# 🔄 Data Flow — Approach 2

```text
'(' -> open++

')' -> consume the next ')' if present;
      otherwise insert a missing ')'

      if open > 0: open--
      else: insert '(' and answer++

End -> answer + 2 * open
```

---

# 🧪 Dry Run — Approach 2

Take:

```text
s = "))())("
```

Initially:

```text
open = 0
answer = 0
```

### First pair `'))'`

The pair is complete, but no opening is available. Insert one `'('`:

```text
answer = 1
open = 0
```

### Next character `'('

```text
open = 1
```

### Next pair `'))'`

The pair matches the waiting opening:

```text
open = 0
answer = 1
```

### Final character `'('

```text
open = 1
```

At the end, this opening needs two closes:

```text
answer + 2 * open = 1 + 2 = 3
```

Answer:

```text
3
```

---

# ✅ Correctness — Approach 2

Every processed closing pair is either already present or made complete by inserting the missing `')'`. Each such pair must match an opening parenthesis that appears earlier.

If an unmatched opening exists, matching the new pair to it avoids an unnecessary insertion. If none exists, inserting `'('` is unavoidable because the pair cannot be matched to a future opening.

After the scan, each unmatched opening needs exactly two closing parentheses, so adding `2 * open` completes the string. Every insertion repairs a required pair or a missing opening that cannot be supplied by existing characters. Therefore the algorithm returns the minimum number of insertions.

---

# ☕ Java — Both Approaches

```java
import java.util.*;

//Approach-1 (Greedy Required-Closing Counter)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int minInsertions(String s) {
        int answer = 0;
        int need = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                need += 2;
                if (need % 2 == 1) {
                    answer++;
                    need--;
                }
            } else {
                need--;
                if (need < 0) {
                    answer++;
                    need = 1;
                }
            }
        }

        return answer + need;
    }
}

//Approach-2 (Greedy Pairing of Consecutive ')')
//T.C : O(n)
//S.C : O(1)

class Solution2 {

    public int minInsertions(String s) {
        int answer = 0;
        int open = 0;
        int i = 0;
        int n = s.length();

        while (i < n) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    answer++;
                }

                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
            i++;
        }

        return answer + 2 * open;
    }
}
```

---

# 💻 C++ — Both Approaches

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Greedy Required-Closing Counter)
//T.C : O(n)
//S.C : O(1)

class Solution {

public:
    int minInsertions(string s) {
        int answer = 0;
        int need = 0;

        for (char ch : s) {
            if (ch == '(') {
                need += 2;
                if (need % 2 == 1) {
                    answer++;
                    need--;
                }
            } else {
                need--;
                if (need < 0) {
                    answer++;
                    need = 1;
                }
            }
        }

        return answer + need;
    }
};

//Approach-2 (Greedy Pairing of Consecutive ')')
//T.C : O(n)
//S.C : O(1)

class Solution2 {

public:
    int minInsertions(string s) {
        int answer = 0;
        int open = 0;
        int n = (int)s.size();

        for (int i = 0; i < n; i++) {
            if (s[i] == '(') {
                open++;
            } else {
                if (i + 1 < n && s[i + 1] == ')') {
                    i++;
                } else {
                    answer++;
                }

                if (open > 0) {
                    open--;
                } else {
                    answer++;
                }
            }
        }

        return answer + 2 * open;
    }
};
```

---

# 🐍 Python — Both Approaches

```python
#Approach-1 (Greedy Required-Closing Counter)
#T.C : O(n)
#S.C : O(1)

class Solution:

    def minInsertions(self, s: str) -> int:
        answer = 0
        need = 0

        for ch in s:
            if ch == '(':
                need += 2
                if need % 2 == 1:
                    answer += 1
                    need -= 1
            else:
                need -= 1
                if need < 0:
                    answer += 1
                    need = 1

        return answer + need


#Approach-2 (Greedy Pairing of Consecutive ')')
#T.C : O(n)
#S.C : O(1)

class Solution2:

    def minInsertions(self, s: str) -> int:
        answer = 0
        open_count = 0
        i = 0
        n = len(s)

        while i < n:
            if s[i] == '(':
                open_count += 1
            else:
                if i + 1 < n and s[i + 1] == ')':
                    i += 1
                else:
                    answer += 1

                if open_count > 0:
                    open_count -= 1
                else:
                    answer += 1
            i += 1

        return answer + 2 * open_count
```

---

# ⏱️ Complexity Comparison

| Approach                        |   Time | Auxiliary Space | Main Idea                                     |
| ------------------------------- | -----: | --------------: | --------------------------------------------- |
| Greedy required-closing counter | `O(n)` |          `O(1)` | Track outstanding close requirements          |
| Greedy close-pair scan          | `O(n)` |          `O(1)` | Consume each `'))'` pair and match an opening |

Both methods scan the input once and are optimal for the constraints.

---

# 🧠 Pattern Recognition

When each opening parenthesis needs **two consecutive closing parentheses**, do not apply the ordinary one-open/one-close balance rule.

Think about:

```text
1. How many closing parentheses are still required?
2. Is the outstanding closing requirement odd?
3. Is there an unmatched closing pair with no earlier opening?
4. How many closes are needed by openings left at the end?
```

The first approach compresses all outstanding work into `need`. The second approach makes each `'))'` token explicit.

---

# ❌ Common Mistakes

### 1. Treating this like ordinary valid parentheses

Here, `()` is missing one closing parenthesis. The valid one-opening structure is:

```text
())
```

### 2. Forgetting the consecutive requirement

When `need` is odd and a new `'('` arrives, insert one `')'` before the new opening. Otherwise, the previous opening's required pair is interrupted.

### 3. Setting `need = 0` after an unmatched `')'`

If `need` becomes negative, insert `'('`. The existing close fulfills one of the inserted opening's two required closes, so set:

```text
need = 1
```

### 4. Forgetting the remaining requirements

At the end, return:

```text
answer + need
```

For the pair-based approach, each unmatched opening needs two closing parentheses:

```text
answer + 2 * open
```

### 5. Counting only the total number of each character

Equal counts do not guarantee validity. Order and the consecutive `'))'` requirement matter.

---

# 🎯 Interview Explanation

> Every `'('` needs two consecutive `')'` characters, so I track `need`, the number of closes still required. For `'('`, I add two. If `need` is odd, the previous closing pair would be interrupted by this new opening, so I insert one `')'` and reduce `need` by one. For `')'`, I decrease `need`; if it becomes negative, I insert `'('` and set `need` to one because the current close satisfies one of the inserted opening's two required closes. At the end, I add the remaining `need`. This is `O(n)` time and `O(1)` auxiliary space.

---

# 📝 Quick Revision

```text
answer = 0
need = 0

For '(':
    need += 2
    if need is odd:
        answer++
        need--

For ')':
    need--
    if need < 0:
        answer++
        need = 1

Return answer + need
```

Alternative close-pair scan:

```text
'(' -> open++
')' -> consume the next ')' if present;
      otherwise insert one ')'
      if open > 0: open--
      else: insert '('
End -> answer + 2 * open
```

---

# 🚀 One-Line Insight

> Greedily repair interrupted closing pairs, unmatched closing parentheses, and any closing requirements left at the end.

---

# ✅ Final Takeaway

The special rule is:

```text
'(' must be matched by consecutive '))'
```

The required-closing approach tracks only `answer` and `need`; the pair-based approach tracks how many unmatched openings remain. Both run in:

```text
Time:  O(n)
Space: O(1)
```

For LeetCode submission, **Approach 1 — Greedy Required-Closing Counter** is the preferred solution because it is concise and directly represents the outstanding closing requirement.
