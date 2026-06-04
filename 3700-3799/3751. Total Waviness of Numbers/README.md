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

# 3751. Total Waviness of Numbers

# Intuition

A digit contributes to waviness if it is:

```text
Strictly greater than both neighbors
```

or

```text
Strictly smaller than both neighbors
```

Such digits are called:

```text
Peak
or
Valley
```

For every number in:

```text
[num1, num2]
```

we calculate its waviness and add it to the answer.

---

# Definition of Waviness

For a digit:

```text
left current right
```

Peak:

```text
left < current > right
```

Valley:

```text
left > current < right
```

---

# Example

Number:

```text
252
```

Check middle digit:

```text
2 < 5 > 2
```

Peak

Waviness:

```text
1
```

---

# Approach

## Step 1

Traverse every number from:

```text
num1 → num2
```

---

## Step 2

Convert number into string.

---

## Step 3

Check every middle digit.

```text
current > left
AND
current > right
```

or

```text
current < left
AND
current < right
```

---

## Step 4

Count waviness.

---

## Step 5

Add to final answer.

---

# Flow Diagram

```text
Take Number

      ↓

Convert To String

      ↓

Check Every Middle Digit

      ↓

Peak ?

Valley ?

      ↓

Count Waviness

      ↓

Add To Answer
```

---

# Example Dry Run

Input

```text
num1 = 250
num2 = 252
```

---

## Number 250

```text
2 5 0

2 < 5 > 0
```

Waviness:

```text
1
```

---

## Number 251

```text
2 5 1

2 < 5 > 1
```

Waviness:

```text
1
```

---

## Number 252

```text
2 5 2

2 < 5 > 2
```

Waviness:

```text
1
```

---

Total

```text
1 + 1 + 1 = 3
```

Answer:

```text
3
```

---

# Complexity Analysis

Let

```text
N = num2 - num1 + 1
```

and

```text
D = number of digits
```

---

## Time Complexity

```text
O(N × D)
```

---

## Space Complexity

```text
O(D)
```

Used for string conversion.

---

# Java Solution

```java
class Solution {

    public int totalWaviness(int num1, int num2) {

        int totalWavinessSum = 0;

        for (int i = num1; i <= num2; i++) {

            totalWavinessSum += calculateWaviness(i);
        }

        return totalWavinessSum;
    }

    private int calculateWaviness(int num) {

        if (num < 100) {
            return 0;
        }

        String s = Integer.toString(num);

        int wavinessCount = 0;

        for (int i = 1; i < s.length() - 1; i++) {

            char current = s.charAt(i);
            char left = s.charAt(i - 1);
            char right = s.charAt(i + 1);

            if (current > left && current > right) {

                wavinessCount++;
            }
            else if (current < left && current < right) {

                wavinessCount++;
            }
        }

        return wavinessCount;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int calculateWaviness(int num) {

        if (num < 100) {
            return 0;
        }

        string s = to_string(num);

        int wavinessCount = 0;

        for (int i = 1; i < s.length() - 1; i++) {

            char current = s[i];
            char left = s[i - 1];
            char right = s[i + 1];

            if (current > left && current > right) {

                wavinessCount++;
            }
            else if (current < left && current < right) {

                wavinessCount++;
            }
        }

        return wavinessCount;
    }

    int totalWaviness(int num1, int num2) {

        int totalWavinessSum = 0;

        for (int i = num1; i <= num2; i++) {

            totalWavinessSum += calculateWaviness(i);
        }

        return totalWavinessSum;
    }
};
```
