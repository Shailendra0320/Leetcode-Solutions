Profiles

GitHub

⭐ GitHub Repository:

https://github.com/Shailendra0320

LeetCode Profiles

🔥 Main Profile:

https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:

https://leetcode.com/u/Shailu03/

3622. Check Divisibility by Digit Sum and Product

Intuition

We are given an integer num.

We need to calculate two values from its digits:

Digit Sum = sum of all digits

Digit Product = product of all digits

Then calculate:

total = Digit Sum + Digit Product

Finally, check whether:

num % total == 0

If the remainder is 0, return true; otherwise, return false.

The key observation is that we do not need to store the digits. We can extract them mathematically using % 10 and remove them using / 10.

Approaches

We can solve this problem using two approaches:

Arithmetic Digit Extraction

String-Based Digit Processing

The first approach is preferred because it uses constant extra space.

Approach 1 — Arithmetic Digit Extraction

Idea

For every digit, extract the last digit using:

digit = current % 10

Add it to the digit sum:

digitSum += digit

Multiply it into the digit product:

digitProduct \*= digit

Then remove the last digit:

current /= 10

After processing all digits:

total = digitSum + digitProduct

and:

return num % total == 0

Visualization

                         num
                          │
                          ▼
                   current = num
                          │
                          ▼
                    current > 0?
                     /          \
                   Yes           No
                    │             │
                    ▼             ▼
              digit = current   Calculate
                     % 10        total
                    │             │
            ┌───────┴────────┐    │
            │                │    │
            ▼                ▼    │
       digitSum +=       digitProduct *=
          digit              digit
            │                │
            └───────┬────────┘
                    │
                    ▼
               current /= 10
                    │
                    └───────────────►
                                    │
                                    ▼
                         total = digitSum +
                                 digitProduct
                                    │
                                    ▼
                           num % total == 0?
                              /          \
                            Yes           No
                             │             │
                             ▼             ▼
                           true          false

Dry Run

Consider:

num = 123

Initially:

current = 123
digitSum = 0
digitProduct = 1

Step 1

digit = 123 % 10 = 3

Update:

digitSum = 0 + 3 = 3
digitProduct = 1 \* 3 = 3
current = 123 / 10 = 12

Step 2

digit = 12 % 10 = 2

Update:

digitSum = 3 + 2 = 5
digitProduct = 3 \* 2 = 6
current = 12 / 10 = 1

Step 3

digit = 1 % 10 = 1

Update:

digitSum = 5 + 1 = 6
digitProduct = 6 \* 1 = 6
current = 1 / 10 = 0

Now:

total = 6 + 6 = 12

Check:

123 % 12 != 0

Therefore:

false

Correctness

The algorithm processes every digit exactly once.

For every digit d:

digitSum += d

correctly calculates the sum of all digits, while:

digitProduct \*= d

correctly calculates the product of all digits.

Therefore:

total = digitSum + digitProduct

is exactly the required divisor.

The final condition:

num % total == 0

directly checks whether num is divisible by that value.

Hence, the algorithm is correct.

Complexity

Let d be the number of digits in num.

Time Complexity: O(d)
= O(log10(num))

Space Complexity: O(1)

Java Code

// Approach-1 (Arithmetic Digit Extraction)
// T.C : O(log10(num))
// S.C : O(1)

class Solution {
public boolean checkDivisibility(int num) {
int current = num;
int dSum = 0;
int dProd = 1;

        while (current > 0) {
            int val = current % 10;

            dSum += val;
            dProd *= val;

            current /= 10;
        }

        int total = dSum + dProd;

        return num % total == 0;
    }

}

C++ Code

// Approach-1 (Arithmetic Digit Extraction)
// T.C : O(log10(num))
// S.C : O(1)

class Solution {
public:
bool checkDivisibility(int num) {
int current = num;
int dSum = 0;
int dProd = 1;

        while (current > 0) {
            int digit = current % 10;

            dSum += digit;
            dProd *= digit;

            current /= 10;
        }

        int total = dSum + dProd;

        return num % total == 0;
    }

};

Approach 2 — String-Based Digit Processing

Idea

Convert the number into a string.

For example:

1234 → "1234"

Then process every character.

A character can be converted into its numeric digit using:

digit = ch - '0'

For every digit:

digitSum += digit
digitProduct \*= digit

Finally:

total = digitSum + digitProduct

and check:

num % total == 0

Visualization

                         num
                          │
                          ▼
                    Convert to String
                          │
                          ▼
                       "1234"
                          │
                          ▼
                    Process characters
                          │
                          ▼
                    Convert char to digit
                          │
                 ┌────────┴────────┐
                 │                 │
                 ▼                 ▼
             digitSum +=       digitProduct *=
                digit              digit
                 │                 │
                 └────────┬────────┘
                          │
                          ▼
                    More characters?
                       /        \
                     Yes         No
                      │           │
                      └───────────┤
                                  ▼
                         total = sum + product
                                  │
                                  ▼
                          num % total == 0

Dry Run

Consider:

num = 1234

Convert:

"1234"

Process each character.

Character 1

digit = '1' - '0' = 1

digitSum = 1
digitProduct = 1

Character 2

digit = 2

digitSum = 3
digitProduct = 2

Character 3

digit = 3

digitSum = 6
digitProduct = 6

Character 4

digit = 4

digitSum = 10
digitProduct = 24

Therefore:

total = 10 + 24 = 34

Check:

1234 % 34 != 0

Therefore:

false

Correctness

Converting num into a string preserves every decimal digit.

For every character, ch - '0' gives its corresponding numeric digit.

Thus every digit is included exactly once in:

digitSum

and:

digitProduct

After calculating:

total = digitSum + digitProduct

the algorithm checks the exact divisibility condition required by the problem.

Therefore, the algorithm is correct.

Complexity

Let d be the number of digits in num.

Converting and processing the string takes:

Time Complexity: O(d)
= O(log10(num))

The string requires space proportional to the number of digits:

Space Complexity: O(d)
= O(log10(num))

Java Code

// Approach-2 (String-Based Digit Processing)
// T.C : O(log10(num))
// S.C : O(log10(num))

class Solution {
public boolean checkDivisibility(int num) {
String str = Integer.toString(num);

        int digitSum = 0;
        int digitProduct = 1;

        for (char ch : str.toCharArray()) {
            int digit = ch - '0';

            digitSum += digit;
            digitProduct *= digit;
        }

        int total = digitSum + digitProduct;

        return num % total == 0;
    }

}

C++ Code

// Approach-2 (String-Based Digit Processing)
// T.C : O(log10(num))
// S.C : O(log10(num))

class Solution {
public:
bool checkDivisibility(int num) {
string str = to_string(num);

        int digitSum = 0;
        int digitProduct = 1;

        for (char ch : str) {
            int digit = ch - '0';

            digitSum += digit;
            digitProduct *= digit;
        }

        int total = digitSum + digitProduct;

        return num % total == 0;
    }

};

Approach Comparison

Feature

Approach 1

Approach 2

Technique

Arithmetic Digit Extraction

String Digit Processing

Digit Extraction

% 10 and / 10

Character conversion

Time Complexity

O(log10(num))

O(log10(num))

Space Complexity

O(1)

O(log10(num))

String Conversion

No

Yes

Extra Storage

Constant

String

Memory Efficient

Yes

No

Recommended

Yes

Good Alternative

Why Approach 1 Is Better

Both approaches have the same time complexity.

However, Approach 1 does not require:

String conversion
Character processing
Additional string storage

It works directly with the integer using arithmetic operations.

Therefore, Approach 1 is more memory efficient:

Approach 1
↓
O(1) extra space

For this problem, Approach 1 is the preferred solution.

Important Edge Cases

Case 1 — Single Digit

num = 5

Digit sum:

5

Digit product:

5

Total:

10

Since:

5 % 10 != 0

answer:

false

Case 2 — Number Contains Zero

Consider:

num = 101

Digit sum:

1 + 0 + 1 = 2

Digit product:

1 _ 0 _ 1 = 0

Total:

2 + 0 = 2

Since:

101 % 2 != 0

answer:

false

If any digit is zero, the digit product becomes zero. No special handling is required.

Case 3 — Product Becomes Zero

For:

num = 204

we have:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0

Therefore:

total = 6

The algorithm handles this normally.

Important Formula

The complete problem can be represented as:

S = Σ digit

P = Π digit

D = S + P

Answer = (num % D == 0)

In simple terms:

Digit Sum +
Digit Product
↓
Divisor
↓
Check num % divisor

Overall Algorithm

1. Store num in a temporary variable.

2. Initialize:

   digitSum = 0
   digitProduct = 1

3. Extract every digit.

4. Add each digit to digitSum.

5. Multiply each digit into digitProduct.

6. Calculate:

   total = digitSum + digitProduct

7. Check:

   num % total == 0

8. Return the result.

Complete Algorithm Diagram

                         LeetCode 3622
                              │
                              ▼
                 Check Divisibility by
              Digit Sum and Product
                              │
                              ▼
                        Extract Digits
                              │
                              ▼
                    ┌─────────┴─────────┐
                    │                   │
                    ▼                   ▼
                Digit Sum          Digit Product
                    │                   │
                    └─────────┬─────────┘
                              │
                              ▼
                    Sum + Product
                              │
                              ▼
                    num % total == 0?
                       /                                Yes            No
                      │              │
                      ▼              ▼
                    true           false

Complexity Diagram

                         Number
                            │
                            ▼
                         d digits
                            │
                            ▼
                  Process every digit
                            │
                   ┌────────┴────────┐
                   │                 │
                   ▼                 ▼
                Digit Sum       Digit Product
                   │                 │
                   └────────┬────────┘
                            │
                            ▼
                     Calculate Total
                            │
                            ▼
                    Divisibility Check

Where:

d = number of digits in num

Therefore:

Approach 1:

Time → O(d) = O(log10(num))
Space → O(1)

and:

Approach 2:

Time → O(d) = O(log10(num))
Space → O(d) = O(log10(num))

Final Complexity Comparison

Approach

Time Complexity

Space Complexity

Arithmetic Digit Extraction

O(log10(num))

O(1)

String-Based Digit Processing

O(log10(num))

O(log10(num))

Key Takeaways

The main concepts used in this problem are:

1. Digit Extraction
2. Modulo Operator
3. Integer Division
4. Digit Sum
5. Digit Product
6. Arithmetic Simulation
7. String Processing

The two most important operations for the arithmetic approach are:

current % 10

to extract the last digit, and:

current / 10

to remove the last digit.

Final Summary

                    LeetCode 3622
                          │
                          ▼
                Extract Every Digit
                          │
                          ▼
                 ┌────────┴────────┐
                 │                 │
                 ▼                 ▼
             Digit Sum        Digit Product
                 │                 │
                 └────────┬────────┘
                          │
                          ▼
                  Sum + Product
                          │
                          ▼
                  num % total
                          │
                    ┌─────┴─────┐
                    │           │
                  == 0        != 0
                    │           │
                    ▼           ▼
                  true        false

The preferred solution combines:

Arithmetic Digit Extraction +
Digit Sum +
Digit Product +
Modulo

with:

Time Complexity → O(log10(num))
Space Complexity → O(1)

Title

LeetCode 3622 - Check Divisibility by Digit Sum and Product

Tags

Math Simulation Number Theory Digit Manipulation Arithmetic String Implementation LeetCode

Revision Section 1

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 2

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 3

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 4

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 5

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 6

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 7

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 8

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 9

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 10

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 11

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 12

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10

The modulo operator extracts the last decimal digit.

Remove Digit

current /= 10

Integer division removes the processed last digit.

Sum Update

digitSum += digit

Product Update

digitProduct \*= digit

Final Calculation

total = digitSum + digitProduct

Divisibility Check

num % total == 0

Complexity

Time: O(log10(num))
Space: O(1) for arithmetic approach

Revision Example

For num = 204:

digitSum = 2 + 0 + 4 = 6
digitProduct = 2 _ 0 _ 4 = 0
total = 6
204 % 6 = 0
answer = true

Takeaway

The reusable pattern is:

while (current > 0) {
int digit = current % 10;
// process digit
current /= 10;
}

Revision Section 13

Core Idea

Process every digit of num, calculate the digit sum and digit product, add them, and check whether the original number is divisible by the result.

Digit Extraction

digit = current % 10
