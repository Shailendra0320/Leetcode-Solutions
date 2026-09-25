# 1096. Brace Expansion II — Recursive Parsing and Stack-Based Set Evaluation

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03/](https://leetcode.com/u/ShailendraLeetcode03/)

---

# 🧩 Problem Statement

Given an expression containing lowercase letters, `{`, `}`, and `,`, return all words represented by the expression in sorted order.

The grammar has three important rules:

### 1. Letter → singleton set

```text
R("a") = {"a"}
```

### 2. Comma → union

```text
R("{a,b,c}") = {"a","b","c"}
```

Duplicate words are kept only once.

### 3. Adjacent expressions → Cartesian-product concatenation

```text
R("{a,b}{c,d}")
```

produces:

```text
ac
ad
bc
bd
```

The official constraints give `1 <= expression.length <= 60`, with only braces, commas, and lowercase English letters. citeturn763124view0

---

# 🎯 What Is the Problem Really Asking?

The expression can look complicated, but it only uses two set operations:

```text
,  → UNION

adjacent expressions
    → CARTESIAN PRODUCT + CONCATENATION
```

For example:

```text
{a,b}{c,d}

First set:
{a,b}

Second set:
{c,d}

Cartesian product:

a + c = ac
a + d = ad
b + c = bc
b + d = bd
```

So this is fundamentally:

```text
Expression Parsing
        +
Set Algebra
        +
String Concatenation
```

---

# 🧠 Grammar → Algorithm Mapping

| Syntax         | Meaning                     | Implementation                 |
| -------------- | --------------------------- | ------------------------------ |
| `a`            | One word                    | `{"a"}`                        |
| `{a,b}`        | Alternatives                | Set union                      |
| `ab`           | Consecutive characters      | String concatenation           |
| `{a,b}{c,d}`   | Two sets next to each other | Cartesian product              |
| Nested `{...}` | Sub-expression              | Recursive parser / stack frame |

The two core helper operations are:

```text
Union(A, B)
```

and:

```text
Concat(A, B)
= { a + b | a ∈ A, b ∈ B }
```

---

# 🚀 Approach 1 — Recursive Descent Parser

## 💡 Main Idea

The grammar naturally maps to three parser functions:

```text
Expression
    ↓
Term (',' Term)*

Term
    ↓
Factor Factor Factor ...

Factor
    ↓
letter
OR
'{' Expression '}'
```

This gives a very clean separation:

```text
parseExpression()
    → handles UNION

parseTerm()
    → handles CONCATENATION

parseFactor()
    → handles letters and nested braces
```

---

# 🏗️ Approach 1 — Architecture Diagram

```text
                     INPUT EXPRESSION
                            │
                            ▼
                ┌──────────────────────┐
                │   parseExpression()  │
                └──────────┬───────────┘
                           │
                    comma-separated
                        expressions
                           │
                           ▼
                ┌──────────────────────┐
                │      parseTerm()     │
                └──────────┬───────────┘
                           │
                    consecutive factors
                           │
                           ▼
                ┌──────────────────────┐
                │     parseFactor()    │
                └────────┬───────┬─────┘
                         │       │
                     letter     '{'
                         │       │
                         ▼       ▼
                    {"letter"} parseExpression()
                                  │
                                  ▼
                                 '}'
                                  │
                                  ▼
                              Set<String>
```

---

## 🔄 Approach 1 — Data Flow

```text
Expression
    │
    ├── comma ───────────────→ UNION
    │
    └── adjacent factors ────→ CONCATENATION
                                    │
                                    ▼
                            Cartesian Product
                                    │
                                    ▼
                               Set<String>
                                    │
                                    ▼
                              Sorted List
```

---

# 🔍 Approach 1 — How Each Function Works

## `parseExpression()`

It handles comma-separated alternatives:

```text
A,B,C
```

so:

```text
result = A ∪ B ∪ C
```

---

## `parseTerm()`

It handles adjacent factors:

```text
A B C
```

so:

```text
result = A × B × C
```

where multiplication means Cartesian-product concatenation.

---

## `parseFactor()`

A factor is either:

```text
letter
```

or:

```text
{ expression }
```

For example:

```text
a
```

returns:

```text
{"a"}
```

while:

```text
{a,b}
```

recursively returns:

```text
{"a","b"}
```

---

# 🧪 Approach 1 — Dry Run

Consider:

```text
expression = "{a,b}{c,{d,e}}"
```

### First factor

```text
{a,b}
```

becomes:

```text
{"a","b"}
```

### Second factor

```text
{c,{d,e}}
```

The nested expression:

```text
{d,e}
```

becomes:

```text
{"d","e"}
```

Then:

```text
{"c"} ∪ {"d","e"}
=
{"c","d","e"}
```

### Concatenate

Now:

```text
A = {"a","b"}
B = {"c","d","e"}
```

Generate:

```text
ac
ad
ae
bc
bd
be
```

Final result:

```text
["ac","ad","ae","bc","bd","be"]
```

This matches the official example. citeturn763124view0

---

# 🧮 Approach 1 — Set Operations

### Union

```text
A = {a,b}
B = {b,c}

A ∪ B
= {a,b,c}
```

### Concatenation

```text
A = {a,b}
B = {c,d}

A × B

= {ac,ad,bc,bd}
```

A `Set<String>` automatically removes duplicates.

---

# ✅ Approach 1 — Correctness

The parser follows the grammar directly.

### Letter

A letter represents exactly one word, so:

```text
letter → {letter}
```

is correct.

### Comma

A comma represents union, so:

```text
A,B → A ∪ B
```

is correct.

### Adjacency

Adjacent expressions represent all possible concatenations:

```text
A B
→ {a+b | a∈A, b∈B}
```

which is exactly the required Cartesian product.

### Braces

Braces group a complete expression. Recursively parsing their contents returns exactly the represented set.

Therefore, the recursive parser generates exactly the words represented by the input expression.

---

# 💻 Approach 1 — Java

```java
import java.util.*;

//Approach-1 (Recursive Descent Parser)
//T.C : Output-sensitive; worst-case exponential
//S.C : Output-sensitive + O(n) recursion

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parseExpression() {
        Set<String> result = new HashSet<>(parseTerm());

        while (index < expression.length() && expression.charAt(index) == ',') {
            index++;
            result.addAll(parseTerm());
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()) {
            char ch = expression.charAt(index);

            if (ch == '}' || ch == ',') {
                break;
            }

            Set<String> factor = parseFactor();
            result = concatenate(result, factor);
        }

        return result;
    }

    private Set<String> parseFactor() {
        char ch = expression.charAt(index);

        if (ch == '{') {
            index++;
            Set<String> result = parseExpression();
            index++;
            return result;
        }

        index++;

        Set<String> result = new HashSet<>();
        result.add(String.valueOf(ch));

        return result;
    }

    private Set<String> concatenate(
        Set<String> first,
        Set<String> second
    ) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}
```

---

# 💻 Approach 1 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-1 (Recursive Descent Parser)
//T.C : Output-sensitive; worst-case exponential
//S.C : Output-sensitive + O(n) recursion

class Solution {
private:
    string expression;
    int index = 0;

    set<string> parseExpression() {
        set<string> result = parseTerm();

        while (index < expression.size() && expression[index] == ',') {
            index++;
            set<string> next = parseTerm();
            result.insert(next.begin(), next.end());
        }

        return result;
    }

    set<string> parseTerm() {
        set<string> result = {""};

        while (index < expression.size()) {
            char ch = expression[index];

            if (ch == '}' || ch == ',') {
                break;
            }

            set<string> factor = parseFactor();
            result = concatenate(result, factor);
        }

        return result;
    }

    set<string> parseFactor() {
        char ch = expression[index];

        if (ch == '{') {
            index++;
            set<string> result = parseExpression();
            index++;
            return result;
        }

        index++;
        return {string(1, ch)};
    }

    set<string> concatenate(
        const set<string>& first,
        const set<string>& second
    ) {
        set<string> result;

        for (const string& a : first) {
            for (const string& b : second) {
                result.insert(a + b);
            }
        }

        return result;
    }

public:
    vector<string> braceExpansionII(string expression) {
        this->expression = expression;
        index = 0;

        set<string> result = parseExpression();

        return vector<string>(result.begin(), result.end());
    }
};
```

---

# ⏱️ Approach 1 — Complexity

Let:

```text
W = number of generated words
L = maximum generated word length
```

The actual work is determined by the generated sets and Cartesian products.

```text
Time  : Output-sensitive, worst-case exponential
Space : Output-sensitive + O(n) recursion depth
```

Exponential growth is inherent because expressions can represent many combinations.

---

# ⚡ Approach 2 — Stack-Based Set Evaluation

## 💡 Main Idea

Instead of using recursive calls, we can evaluate the same grammar using an explicit stack.

Each stack frame represents one brace level and stores:

```text
union
product
```

### `product`

The current consecutive expression.

Example:

```text
ab{c,d}
```

is gradually built by concatenation.

### `union`

Stores branches already completed by commas.

This allows us to process the expression from left to right.

---

# 🏗️ Approach 2 — Architecture Diagram

```text
                      CURRENT CHARACTER
                              │
                              ▼
                   ┌─────────────────────┐
                   │ Inspect character   │
                   └─────────┬───────────┘
                             │
           ┌─────────────────┼─────────────────┐
           │                 │                 │
           ▼                 ▼                 ▼
        Letter              '{'               ','
           │                 │                 │
           ▼                 ▼                 ▼
      Add to product     Push Frame       product → union
                                             reset product
           │                 │                 │
           └─────────────────┼─────────────────┘
                             │
                             ▼
                            '}'
                             │
                             ▼
                      Finish current frame
                             │
                             ▼
                       Pop the frame
                             │
                             ▼
                Concatenate child result
                    with parent product
                             │
                             ▼
                        Continue scan
                             │
                             ▼
                       Final root set
                             │
                             ▼
                            Sort
```

---

# 🧱 Approach 2 — Frame Architecture

Each frame is:

```text
┌────────────────────────────┐
│ Frame                      │
│                            │
│ union   = completed paths  │
│ product = current path     │
│                            │
└────────────────────────────┘
```

Initially:

```text
product = {""}
```

because the empty string is the identity for concatenation:

```text
"" + "abc" = "abc"
```

---

# 🔄 Approach 2 — Character Rules

### Letter

Convert:

```text
a
```

into:

```text
{"a"}
```

and concatenate with the current product.

### `{`

Push a new frame.

### `,`

The current branch ends:

```text
union += product
product = {""}
```

### `}`

Finish the current frame:

```text
union += product
```

Pop it.

Then concatenate the child set into the parent's current product.

---

# 🧪 Approach 2 — Dry Run

Consider:

```text
expression = "{a,b}{c,d}"
```

### Start

```text
Root:
product = {""}
union   = {}
```

### First `{`

Push a new frame:

```text
product = {""}
union   = {}
```

### Read `a`

```text
product = {"a"}
```

### Read `,`

Move current branch to union:

```text
union   = {"a"}
product = {""}
```

### Read `b`

```text
product = {"b"}
```

### Read `}`

Finalize:

```text
union = {"a","b"}
```

Pop frame.

Parent becomes:

```text
product = {"a","b"}
```

### Second `{c,d}`

It produces:

```text
{"c","d"}
```

Now concatenate:

```text
{"a","b"} × {"c","d"}
```

Result:

```text
{"ac","ad","bc","bd"}
```

---

# 🔄 Approach 2 — Data Flow

```text
Input
  │
  ▼
Read Character
  │
  ├── letter ─────────→ concatenate into product
  │
  ├── '{' ────────────→ push new frame
  │
  ├── ',' ────────────→ product → union
  │                     reset product
  │
  └── '}' ────────────→ finalize frame
                         │
                         ▼
                       pop
                         │
                         ▼
                  child → parent product
                         │
                         ▼
                      continue
                         │
                         ▼
                    final set
                         │
                         ▼
                       sort
```

---

# ✅ Approach 2 — Correctness

At each brace level, the frame maintains:

```text
completed alternatives
+
current concatenation
```

A comma ends the current alternative, so its product is merged into the union.

A closing brace completes the whole group. That group becomes a single set and is concatenated with the surrounding expression.

Therefore the stack maintains exactly the same semantics as the grammar:

```text
comma      → union
adjacency  → Cartesian-product concatenation
braces     → nested group
```

So the final root frame represents exactly the original expression.

---

# 💻 Approach 2 — Java

```java
import java.util.*;

//Approach-2 (Stack-Based Set Evaluation)
//T.C : Output-sensitive; worst-case exponential
//S.C : Output-sensitive + O(n) stack

class Solution2 {
    static class Frame {
        Set<String> union = new HashSet<>();
        Set<String> product = new HashSet<>(Collections.singleton(""));
    }

    public List<String> braceExpansionII(String expression) {
        Deque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame());

        for (char ch : expression.toCharArray()) {
            if (ch == '{') {
                stack.push(new Frame());
            }
            else if (ch == ',') {
                Frame frame = stack.peek();

                frame.union.addAll(frame.product);
                frame.product = new HashSet<>();
                frame.product.add("");
            }
            else if (ch == '}') {
                Frame frame = stack.pop();

                frame.union.addAll(frame.product);

                Frame parent = stack.peek();
                parent.product = concatenate(
                    parent.product,
                    frame.union
                );
            }
            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                Frame frame = stack.peek();
                frame.product = concatenate(
                    frame.product,
                    letter
                );
            }
        }

        Frame root = stack.pop();
        root.union.addAll(root.product);

        List<String> answer = new ArrayList<>(root.union);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> concatenate(
        Set<String> first,
        Set<String> second
    ) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}
```

---

# 💻 Approach 2 — C++

```cpp
#include <bits/stdc++.h>
using namespace std;

//Approach-2 (Stack-Based Set Evaluation)
//T.C : Output-sensitive; worst-case exponential
//S.C : Output-sensitive + O(n) stack

class Solution2 {
    struct Frame {
        set<string> unionSet;
        set<string> product = {""};
    };

    set<string> concatenate(
        const set<string>& first,
        const set<string>& second
    ) {
        set<string> result;

        for (const string& a : first) {
            for (const string& b : second) {
                result.insert(a + b);
            }
        }

        return result;
    }

public:
    vector<string> braceExpansionII(string expression) {
        stack<Frame> st;
        st.push(Frame());

        for (char ch : expression) {
            if (ch == '{') {
                st.push(Frame());
            }
            else if (ch == ',') {
                Frame& frame = st.top();

                frame.unionSet.insert(
                    frame.product.begin(),
                    frame.product.end()
                );

                frame.product.clear();
                frame.product.insert("");
            }
            else if (ch == '}') {
                Frame frame = st.top();
                st.pop();

                frame.unionSet.insert(
                    frame.product.begin(),
                    frame.product.end()
                );

                st.top().product = concatenate(
                    st.top().product,
                    frame.unionSet
                );
            }
            else {
                set<string> letter = {string(1, ch)};

                st.top().product = concatenate(
                    st.top().product,
                    letter
                );
            }
        }

        Frame root = st.top();

        root.unionSet.insert(
            root.product.begin(),
            root.product.end()
        );

        return vector<string>(
            root.unionSet.begin(),
            root.unionSet.end()
        );
    }
};
```

---

# ⏱️ Approach 2 — Complexity

```text
Time  : Output-sensitive, worst-case exponential
Space : Output-sensitive + O(n) stack depth
```

The number of generated words can itself grow exponentially because concatenation creates Cartesian products.

---

# 🆚 Approach 1 vs Approach 2

| Feature            | Recursive Descent        | Stack Evaluation              |
| ------------------ | ------------------------ | ----------------------------- |
| Parsing style      | Recursive                | Iterative                     |
| Nested braces      | Call stack               | Explicit stack                |
| Union              | `Set.union`              | Frame union                   |
| Concatenation      | Cartesian product        | Cartesian product             |
| Duplicate handling | `Set`                    | `Set`                         |
| Sorting            | Final step               | Final step                    |
| Code style         | Grammar-oriented         | State-machine-oriented        |
| Main advantage     | Directly mirrors grammar | Avoids recursive parser calls |

---

# ⚠️ Common Mistakes

### 1. Treating adjacency as union

Wrong:

```text
{a,b}{c,d}
→ {a,b,c,d}
```

Correct:

```text
{a,b}{c,d}
→ {ac,ad,bc,bd}
```

### 2. Splitting every comma blindly

For:

```text
{a,{b,c},d}
```

the comma inside `{b,c}` belongs to the nested expression.

The parser must respect brace depth.

### 3. Forgetting duplicate removal

```text
{{a,b},{b,c}}
```

must produce:

```text
{a,b,c}
```

not:

```text
{a,b,b,c}
```

### 4. Forgetting the Cartesian product

If:

```text
A = {a,b}
B = {x,y,z}
```

then:

```text
A + B
```

means:

```text
ax
ay
az
bx
by
bz
```

### 5. Sorting after every operation

Sorting intermediate sets is unnecessary.

Use a set for uniqueness and sort once at the end.

---

# 🎯 Pattern Recognition

This problem combines three important interview concepts:

```text
1. Expression Parsing
2. Set Union
3. Cartesian Product
```

A useful mental model is:

```text
LETTER
   ↓
Singleton

COMMA
   ↓
UNION

ADJACENCY
   ↓
CARTESIAN PRODUCT + CONCATENATION

BRACES
   ↓
Nested Expression
```

---

# 🗣️ Interview Explanation

A concise explanation:

> "I model the expression as set algebra. A letter produces a singleton set, a comma represents union, and adjacent expressions represent every possible concatenation between the two result sets. My first solution uses recursive descent to mirror the grammar directly: expression handles union, term handles concatenation, and factor handles letters or nested braces. The second solution evaluates the same rules iteratively with a stack of frames. I use sets to remove duplicates and sort only the final result."

---

# 🧾 Quick Revision

```text
R(letter)
    = {letter}

R(A,B)
    = R(A) ∪ R(B)

R(AB)
    = {x + y | x ∈ R(A), y ∈ R(B)}
```

### Core Operations

```text
Union:
A ∪ B

Concatenation:
A × B
```

### Core Data Structure

```text
Set<String>
```

because:

```text
1. duplicates are removed
2. generated words can be combined naturally
```

---

# ⭐ One-Line Insight

> **Treat the expression as set algebra: commas perform union, adjacent expressions perform Cartesian-product concatenation, and braces create nested expressions.**

---

# 🏷️ Tags

`String, Parsing, Recursion, Stack, Hash Set, Set, Expression Parsing, Grammar, Cartesian Product, Backtracking, Simulation, LeetCode, Hard`

---

# 📚 Final Takeaway

The expression looks complicated because of nested braces, but the actual rules are simple:

```text
                 Expression
                     │
          ┌──────────┴──────────┐
          │                     │
        Comma                Adjacency
          │                     │
          ▼                     ▼
        UNION          CARTESIAN PRODUCT
                                │
                                ▼
                         CONCATENATE WORDS
                                │
                                ▼
                           Set<String>
                                │
                                ▼
                              Sort
```

Remember:

```text
Comma      = Union
Adjacent   = Cartesian Product + Concatenation
Braces     = Nested Group
Set        = Remove Duplicates
Sort       = Final Output Order
```

Once these rules are recognized, the problem becomes a clean expression-parsing and set-evaluation problem.
