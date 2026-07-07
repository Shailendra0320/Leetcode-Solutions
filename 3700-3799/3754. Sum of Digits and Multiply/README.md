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

# 3754. Sum of Digits and Multiply

## Tags

```text
Math
Simulation
Digits
Number Theory
String
Java
C++
```

---

# Intuition

We need to perform two operations on the given integer.

1. Remove every digit

```text
0
```

2. Compute

```text
(Number after removing zeros)

×

(Sum of all remaining digits)
```

Instead of storing all digits separately,

we can directly build the new number while calculating the digit sum.

---

# Key Observation

While traversing digits,

every non-zero digit contributes to

```text
Digit Sum
```

and also becomes part of the new number.

Therefore,

both values can be computed

```text
In One Traversal
```

without using any extra data structure.

---

# Approaches

1. Simulation using Digits (Optimal)
2. Simulation using List
3. String Simulation

---

# Approach 1 — One Pass Digit Simulation

## Idea

Traverse every digit from right to left.

Whenever the digit is

```text
Non-Zero
```

update

```text
Digit Sum
```

and

```text
New Number
```

simultaneously.

Finally,

```text
Answer

=

New Number

×

Digit Sum
```

---

# Algorithm

### Step 1

Initialize

```text
Digit Sum = 0

New Number = 0

Place = 1
```

---

### Step 2

Extract the last digit

```text
digit = n % 10
```

---

### Step 3

If

```text
digit ≠ 0
```

update

```text
Digit Sum

+= digit
```

and

```text
New Number

+= digit × place
```

Then

```text
place ×= 10
```

---

### Step 4

Remove last digit

```text
n /= 10
```

---

### Step 5

Return

```text
New Number × Digit Sum
```

---

# Flowchart

```text
           Start

             │

             ▼

 Read Last Digit

             │

             ▼

 Is Digit Zero ?

      ┌──────┴───────┐

     Yes            No

      │              │

      ▼              ▼

 Ignore        Add To Sum

                    │

                    ▼

           Append To Number

                    │

                    ▼

             Remove Digit

                    │

                    ▼

           Number Finished ?

          ┌─────────┴─────────┐

         No                  Yes

          │                   │

          ▼                   ▼

     Continue        Return Product
```

---

# Example

Input

```text
n = 105203
```

Digits

```text
1

0

5

2

0

3
```

After removing zeros

```text
1523
```

Digit Sum

```text
1 + 5 + 2 + 3

=

11
```

Answer

```text
1523 × 11

=

16753
```

---

# Dry Run

Input

```text
105203
```

Processing

```text
Digit = 3

Sum = 3

Number = 3
```

↓

```text
Digit = 0

Ignore
```

↓

```text
Digit = 2

Sum = 5

Number = 23
```

↓

```text
Digit = 5

Sum = 10

Number = 523
```

↓

```text
Digit = 0

Ignore
```

↓

```text
Digit = 1

Sum = 11

Number = 1523
```

Answer

```text
1523 × 11

=

16753
```

---

# Memory Visualization

```text
Input Number

        │

        ▼

Extract Digits

        │

        ▼

Ignore Zeros

        │

        ▼

Digit Sum

        │

        ▼

New Number

        │

        ▼

Multiply

        │

        ▼

Answer
```

---

# Approach 2 — List Simulation

Store every non-zero digit inside a list.

After traversal,

compute

```text
Digit Sum
```

and

```text
New Number
```

using the stored digits.

---

# Approach 3 — String Simulation

Convert the integer into

```text
String
```

Skip every

```text
'0'
```

Append remaining digits into a

```text
StringBuilder
```

Calculate

```text
Digit Sum
```

Convert the final string into a number.

Return

```text
Number × Digit Sum
```

---

# Complexity Analysis

## Approach 1 — One Pass Simulation

### Time Complexity

```text
O(d)
```

where

```text
d = Number of Digits
```

---

### Space Complexity

```text
O(1)
```

---

## Approach 2 — List Simulation

### Time Complexity

```text
O(d)
```

### Space Complexity

```text
O(d)
```

---

## Approach 3 — String Simulation

### Time Complexity

```text
O(d)
```

### Space Complexity

```text
O(d)
```

---

# Java Solution

## Approach 1 — One Pass Digit Simulation (Optimal)

```java
//Approach-1 (One Pass Digit Simulation)
//T.C : O(d)
//S.C : O(1)

class Solution {

    public long sumAndMultiply(int n) {

        int digitSum = 0;

        int place = 1;

        int newNumber = 0;

        while (n > 0) {

            int digit = n % 10;

            if (digit != 0) {

                digitSum += digit;

                newNumber += digit * place;

                place *= 10;
            }

            n /= 10;
        }

        return 1L * newNumber * digitSum;
    }
}
```

---

## Approach 2 — List Simulation

```java
/*
//Approach-2 (List Simulation)
//T.C : O(d)
//S.C : O(d)

import java.util.*;

class Solution {

    public long sumAndMultiply(int n) {

        List<Integer> digits =
            new ArrayList<>();

        while (n > 0) {

            if (n % 10 != 0) {

                digits.add(
                    n % 10
                );
            }

            n /= 10;
        }

        long digitSum = 0;

        for (int digit : digits) {

            digitSum += digit;
        }

        long newNumber = 0;

        for (
            int i = digits.size() - 1;
            i >= 0;
            i--
        ) {

            newNumber =
                newNumber * 10 +
                digits.get(i);
        }

        return newNumber * digitSum;
    }
}
*/
```

---

## Approach 3 — String Simulation

```java
/*
//Approach-3 (String Simulation)
//T.C : O(d)
//S.C : O(d)

class Solution {

    public long sumAndMultiply(int n) {

        String number =
            String.valueOf(n);

        StringBuilder builder =
            new StringBuilder();

        int digitSum = 0;

        for (
            int i = 0;
            i < number.length();
            i++
        ) {

            char current =
                number.charAt(i);

            if (current == '0') {

                continue;
            }

            builder.append(current);

            digitSum +=
                current - '0';
        }

        long newNumber =
            Long.parseLong(
                builder.toString()
            );

        return newNumber * digitSum;
    }
}
*/
```

---

# C++ Solution

## Approach 1 — One Pass Digit Simulation (Optimal)

```cpp
//Approach-1 (One Pass Digit Simulation)
//T.C : O(d)
//S.C : O(1)

class Solution {
public:

    long long sumAndMultiply(int n) {

        int digitSum = 0;

        long long newNumber = 0;

        long long place = 1;

        while (n > 0) {

            int digit = n % 10;

            if (digit != 0) {

                digitSum += digit;

                newNumber +=
                    1LL * digit * place;

                place *= 10;
            }

            n /= 10;
        }

        return newNumber * digitSum;
    }
};
```

---

## Approach 2 — List Simulation

```cpp
/*
//Approach-2 (List Simulation)
//T.C : O(d)
//S.C : O(d)

class Solution {
public:

    long long sumAndMultiply(int n) {

        vector<int> digits;

        while (n > 0) {

            if (n % 10 != 0) {

                digits.push_back(
                    n % 10
                );
            }

            n /= 10;
        }

        long long digitSum = 0;

        for (int digit : digits) {

            digitSum += digit;
        }

        long long newNumber = 0;

        for (
            int i = digits.size() - 1;
            i >= 0;
            i--
        ) {

            newNumber =
                newNumber * 10 +
                digits[i];
        }

        return newNumber * digitSum;
    }
};
*/
```

---

## Approach 3 — String Simulation

```cpp
/*
//Approach-3 (String Simulation)
//T.C : O(d)
//S.C : O(d)

class Solution {
public:

    long long sumAndMultiply(int n) {

        string number =
            to_string(n);

        string result = "";

        long long digitSum = 0;

        for (char current : number) {

            if (current == '0') {

                continue;
            }

            result += current;

            digitSum +=
                current - '0';
        }

        long long newNumber =
            stoll(result);

        return newNumber * digitSum;
    }
};
*/
```

---

# Complexity Comparison

| Approach            | Algorithm     |   Time   |  Space   |
| :------------------ | :------------ | :------: | :------: |
| One Pass Simulation | Math / Digits | **O(d)** | **O(1)** |
| List Simulation     | Simulation    | **O(d)** | **O(d)** |
| String Simulation   | String        | **O(d)** | **O(d)** |

---

# Final Complexity

```text
Approach 1 (One Pass Digit Simulation)

Time Complexity  : O(d)

Space Complexity : O(1)

----------------------------------------

Approach 2 (List Simulation)

Time Complexity  : O(d)

Space Complexity : O(d)

----------------------------------------

Approach 3 (String Simulation)

Time Complexity  : O(d)

Space Complexity : O(d)
```

---

# Conclusion

- ✅ Traverse the number digit by digit.
- ✅ Ignore every `0`.
- ✅ Simultaneously compute the digit sum and construct the new number.
- ✅ The one-pass simulation is the most efficient because it avoids extra storage.
- ✅ List and String approaches are intuitive alternatives but require additional memory.
