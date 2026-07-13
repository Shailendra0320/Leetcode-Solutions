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

# 1291. Sequential Digits

## Tags

```text
Enumeration
Simulation
String
Math
Sorting
Array
Java
C++
```

---

# Intuition

A sequential digit number is a number where every digit is exactly

```text
Previous Digit + 1
```

Examples

```text
12

123

4567

6789
```

Instead of checking every number in the range

```text
[low, high]
```

we can directly generate all possible sequential numbers.

Since there are only

```text
123456789
```

digits available,

the total number of sequential numbers is very small.

---

# Key Observation

Every sequential number is simply a

```text
Substring
```

of

```text
"123456789"
```

Examples

```text
12

123

2345

56789

6789
```

Therefore,

generate every possible substring,

convert it into an integer,

and keep only those lying in the required range.

---

# Approaches

1. String Enumeration (Optimal)

---

# Approach 1 — String Enumeration

## Idea

Take the string

```text
123456789
```

Generate every possible substring.

Convert every substring into

```text
Integer
```

If

```text
low ≤ number ≤ high
```

store it in the answer.

Finally,

sort the answer.

---

# Algorithm

### Step 1

Create

```text
digits = "123456789"
```

---

### Step 2

Try every possible length

```text
1

↓

9
```

---

### Step 3

Generate every substring.

---

### Step 4

Convert substring into

```text
Integer
```

---

### Step 5

If

```text
low ≤ number ≤ high
```

add it to the result.

---

### Step 6

Sort the result.

---

### Step 7

Return the answer.

---

# Flowchart

```text
             Start

               │

               ▼

    Create "123456789"

               │

               ▼

 Choose Substring Length

               │

               ▼

 Generate Substring

               │

               ▼

 Convert To Integer

               │

               ▼

 Number In Range ?

        ┌──────┴───────┐

       No             Yes

        │              │

        ▼              ▼

     Ignore       Store Number

                       │

                       ▼

            More Substrings ?

                │

                ▼

           Sort Answer

                │

                ▼

          Return Result
```

---

# Example

Input

```text
low = 100

high = 300
```

Generated Numbers

```text
12

23

34

...

123

234

345

456
```

Numbers inside range

```text
123

234
```

Answer

```text
[123,234]
```

---

# Dry Run

String

```text
123456789
```

Length

```text
3
```

Generated

```text
123

234

345

456

567

678

789
```

Check Range

```text
123 ✔

234 ✔

345 ✘

456 ✘
```

Answer

```text
[123,234]
```

---

# Memory Visualization

```text
"123456789"

        │

        ▼

Generate Substrings

        │

        ▼

Convert To Integer

        │

        ▼

Check Range

        │

        ▼

Store Answer

        │

        ▼

Sort Result

        │

        ▼

Return
```

---

# Why Enumeration Works?

Every sequential digit number is uniquely represented as a substring of

```text
123456789
```

There are only

```text
45
```

possible substrings.

Hence,

checking every candidate is efficient and guarantees that no valid sequential number is missed.

---

# Complexity Analysis

## Approach 1 — String Enumeration

### Time Complexity

```text
O(1)
```

There are at most

```text
45
```

possible sequential numbers.

Sorting is also performed on a constant-sized list.

---

### Space Complexity

```text
O(1)
```

The answer list contains at most

```text
45
```

numbers.

---

# Java Solution

## Approach 1 — String Enumeration (Optimal)

```java
//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

import java.util.*;

class Solution {

    public List<Integer> sequentialDigits(
        int low,
        int high
    ) {

        List<Integer> answer =
            new ArrayList<>();

        String digits =
            "123456789";

        int n =
            digits.length();

        for (
            int length = 1;
            length <= n;
            length++
        ) {

            for (
                int start = 0;
                start + length <= n;
                start++
            ) {

                String current =
                    digits.substring(
                        start,
                        start + length
                    );

                int number =
                    Integer.parseInt(
                        current
                    );

                if (
                    number >= low &&
                    number <= high
                ) {

                    answer.add(
                        number
                    );
                }
            }
        }

        Collections.sort(
            answer
        );

        return answer;
    }
}
```

---

## Approach 2 — BFS Generation

```java
/*
//Approach-2 (Breadth-First Search)
//T.C : O(1)
//S.C : O(1)

import java.util.*;

class Solution {

    public List<Integer> sequentialDigits(
        int low,
        int high
    ) {

        Queue<Integer> queue =
            new LinkedList<>();

        List<Integer> answer =
            new ArrayList<>();

        for (
            int i = 1;
            i <= 9;
            i++
        ) {

            queue.offer(i);
        }

        while (
            !queue.isEmpty()
        ) {

            int current =
                queue.poll();

            if (
                current >= low &&
                current <= high
            ) {

                answer.add(
                    current
                );
            }

            int lastDigit =
                current % 10;

            if (lastDigit < 9) {

                int next =
                    current * 10
                    + lastDigit + 1;

                if (next <= high) {

                    queue.offer(
                        next
                    );
                }
            }
        }

        Collections.sort(
            answer
        );

        return answer;
    }
}
*/
```

---

# C++ Solution

## Approach 1 — String Enumeration (Optimal)

```cpp
//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        vector<int> answer;

        string digits =
            "123456789";

        int n =
            digits.size();

        for (
            int length = 1;
            length <= n;
            length++
        ) {

            for (
                int start = 0;
                start + length <= n;
                start++
            ) {

                string current =
                    digits.substr(
                        start,
                        length
                    );

                int number =
                    stoi(current);

                if (
                    number >= low &&
                    number <= high
                ) {

                    answer.push_back(
                        number
                    );
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
```

---

## Approach 2 — BFS Generation

```cpp
/*
//Approach-2 (Breadth-First Search)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        queue<int> queue;

        vector<int> answer;

        for (
            int i = 1;
            i <= 9;
            i++
        ) {

            queue.push(i);
        }

        while (
            !queue.empty()
        ) {

            int current =
                queue.front();

            queue.pop();

            if (
                current >= low &&
                current <= high
            ) {

                answer.push_back(
                    current
                );
            }

            int lastDigit =
                current % 10;

            if (lastDigit < 9) {

                int next =
                    current * 10
                    + lastDigit + 1;

                if (next <= high) {

                    queue.push(
                        next
                    );
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
*/
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| String Enumeration | Enumeration | **O(1)** | **O(1)** |
| BFS Generation | Breadth-First Search | **O(1)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (String Enumeration)

Time Complexity  : O(1)

Space Complexity : O(1)

----------------------------------------

Approach 2 (Breadth-First Search)

Time Complexity  : O(1)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Every sequential digit number is a substring of `"123456789"`.
- ✅ String Enumeration generates all possible candidates directly and filters those within the given range.
- ✅ BFS starts with digits `1` to `9` and constructs valid sequential numbers level by level.
- ✅ Since there are only **45 possible sequential numbers**, both approaches run in constant time.
- ✅ The String Enumeration approach is simple, concise, and serves as an optimal solution.# Profiles

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

# 1291. Sequential Digits

## Tags

```text
Enumeration
Simulation
String
Math
Sorting
Array
Java
C++
```

---

# Intuition

A sequential digit number is a number where every digit is exactly

```text
Previous Digit + 1
```

Examples

```text
12

123

4567

6789
```

Instead of checking every number in the range

```text
[low, high]
```

we can directly generate all possible sequential numbers.

Since there are only

```text
123456789
```

digits available,

the total number of sequential numbers is very small.

---

# Key Observation

Every sequential number is simply a

```text
Substring
```

of

```text
"123456789"
```

Examples

```text
12

123

2345

56789

6789
```

Therefore,

generate every possible substring,

convert it into an integer,

and keep only those lying in the required range.

---

# Approaches

1. String Enumeration (Optimal)

---

# Approach 1 — String Enumeration

## Idea

Take the string

```text
123456789
```

Generate every possible substring.

Convert every substring into

```text
Integer
```

If

```text
low ≤ number ≤ high
```

store it in the answer.

Finally,

sort the answer.

---

# Algorithm

### Step 1

Create

```text
digits = "123456789"
```

---

### Step 2

Try every possible length

```text
1

↓

9
```

---

### Step 3

Generate every substring.

---

### Step 4

Convert substring into

```text
Integer
```

---

### Step 5

If

```text
low ≤ number ≤ high
```

add it to the result.

---

### Step 6

Sort the result.

---

### Step 7

Return the answer.

---

# Flowchart

```text
             Start

               │

               ▼

    Create "123456789"

               │

               ▼

 Choose Substring Length

               │

               ▼

 Generate Substring

               │

               ▼

 Convert To Integer

               │

               ▼

 Number In Range ?

        ┌──────┴───────┐

       No             Yes

        │              │

        ▼              ▼

     Ignore       Store Number

                       │

                       ▼

            More Substrings ?

                │

                ▼

           Sort Answer

                │

                ▼

          Return Result
```

---

# Example

Input

```text
low = 100

high = 300
```

Generated Numbers

```text
12

23

34

...

123

234

345

456
```

Numbers inside range

```text
123

234
```

Answer

```text
[123,234]
```

---

# Dry Run

String

```text
123456789
```

Length

```text
3
```

Generated

```text
123

234

345

456

567

678

789
```

Check Range

```text
123 ✔

234 ✔

345 ✘

456 ✘
```

Answer

```text
[123,234]
```

---

# Memory Visualization

```text
"123456789"

        │

        ▼

Generate Substrings

        │

        ▼

Convert To Integer

        │

        ▼

Check Range

        │

        ▼

Store Answer

        │

        ▼

Sort Result

        │

        ▼

Return
```

---

# Why Enumeration Works?

Every sequential digit number is uniquely represented as a substring of

```text
123456789
```

There are only

```text
45
```

possible substrings.

Hence,

checking every candidate is efficient and guarantees that no valid sequential number is missed.

---

# Complexity Analysis

## Approach 1 — String Enumeration

### Time Complexity

```text
O(1)
```

There are at most

```text
45
```

possible sequential numbers.

Sorting is also performed on a constant-sized list.

---

### Space Complexity

```text
O(1)
```

The answer list contains at most

```text
45
```

numbers.

---

# Java Solution

## Approach 1 — String Enumeration (Optimal)

```java
//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

import java.util.*;

class Solution {

    public List<Integer> sequentialDigits(
        int low,
        int high
    ) {

        List<Integer> answer =
            new ArrayList<>();

        String digits =
            "123456789";

        int n =
            digits.length();

        for (
            int length = 1;
            length <= n;
            length++
        ) {

            for (
                int start = 0;
                start + length <= n;
                start++
            ) {

                String current =
                    digits.substring(
                        start,
                        start + length
                    );

                int number =
                    Integer.parseInt(
                        current
                    );

                if (
                    number >= low &&
                    number <= high
                ) {

                    answer.add(
                        number
                    );
                }
            }
        }

        Collections.sort(
            answer
        );

        return answer;
    }
}
```

---

## Approach 2 — BFS Generation

```java
/*
//Approach-2 (Breadth-First Search)
//T.C : O(1)
//S.C : O(1)

import java.util.*;

class Solution {

    public List<Integer> sequentialDigits(
        int low,
        int high
    ) {

        Queue<Integer> queue =
            new LinkedList<>();

        List<Integer> answer =
            new ArrayList<>();

        for (
            int i = 1;
            i <= 9;
            i++
        ) {

            queue.offer(i);
        }

        while (
            !queue.isEmpty()
        ) {

            int current =
                queue.poll();

            if (
                current >= low &&
                current <= high
            ) {

                answer.add(
                    current
                );
            }

            int lastDigit =
                current % 10;

            if (lastDigit < 9) {

                int next =
                    current * 10
                    + lastDigit + 1;

                if (next <= high) {

                    queue.offer(
                        next
                    );
                }
            }
        }

        Collections.sort(
            answer
        );

        return answer;
    }
}
*/
```

---

# C++ Solution

## Approach 1 — String Enumeration (Optimal)

```cpp
//Approach-1 (String Enumeration)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        vector<int> answer;

        string digits =
            "123456789";

        int n =
            digits.size();

        for (
            int length = 1;
            length <= n;
            length++
        ) {

            for (
                int start = 0;
                start + length <= n;
                start++
            ) {

                string current =
                    digits.substr(
                        start,
                        length
                    );

                int number =
                    stoi(current);

                if (
                    number >= low &&
                    number <= high
                ) {

                    answer.push_back(
                        number
                    );
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
```

---

## Approach 2 — BFS Generation

```cpp
/*
//Approach-2 (Breadth-First Search)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    vector<int> sequentialDigits(
        int low,
        int high
    ) {

        queue<int> queue;

        vector<int> answer;

        for (
            int i = 1;
            i <= 9;
            i++
        ) {

            queue.push(i);
        }

        while (
            !queue.empty()
        ) {

            int current =
                queue.front();

            queue.pop();

            if (
                current >= low &&
                current <= high
            ) {

                answer.push_back(
                    current
                );
            }

            int lastDigit =
                current % 10;

            if (lastDigit < 9) {

                int next =
                    current * 10
                    + lastDigit + 1;

                if (next <= high) {

                    queue.push(
                        next
                    );
                }
            }
        }

        sort(
            answer.begin(),
            answer.end()
        );

        return answer;
    }
};
*/
```

---

# Complexity Comparison

| Approach | Algorithm | Time | Space |
|:---------|:----------|:----:|:-----:|
| String Enumeration | Enumeration | **O(1)** | **O(1)** |
| BFS Generation | Breadth-First Search | **O(1)** | **O(1)** |

---

# Final Complexity

```text
Approach 1 (String Enumeration)

Time Complexity  : O(1)

Space Complexity : O(1)

----------------------------------------

Approach 2 (Breadth-First Search)

Time Complexity  : O(1)

Space Complexity : O(1)
```

---

# Conclusion

- ✅ Every sequential digit number is a substring of `"123456789"`.
- ✅ String Enumeration generates all possible candidates directly and filters those within the given range.
- ✅ BFS starts with digits `1` to `9` and constructs valid sequential numbers level by level.
- ✅ Since there are only **45 possible sequential numbers**, both approaches run in constant time.
- ✅ The String Enumeration approach is simple, concise, and serves as an optimal solution.