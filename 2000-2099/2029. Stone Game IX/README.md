# 2029. Stone Game IX

## 🔗 Problem Link

**LeetCode:** https://leetcode.com/problems/stone-game-ix/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Second):** https://leetcode.com/Shailu03/

---

# 📌 Problem Statement

Alice and Bob are playing a game with an array of stones.

Alice starts the game.

On each turn, the current player chooses one remaining stone and adds its value to a running sum.

If the running sum becomes divisible by `3`, the player who made that move loses immediately.

If all stones are removed without anyone losing, Alice loses.

Return `true` if Alice can win when both players play optimally. Otherwise, return `false`.

---

# 💡 Intuition

At first glance, this looks like a game where we need to simulate every possible sequence of moves.

However, the actual value of a stone is not important.

Only its remainder when divided by `3` matters.

Every stone belongs to exactly one of these three groups:

```text
stone % 3 == 0
stone % 3 == 1
stone % 3 == 2

Therefore, instead of working with the original values, we only need three counts:

countZero = number of stones with remainder 0
countOne  = number of stones with remainder 1
countTwo  = number of stones with remainder 2

This reduces the problem from a game involving potentially many different values to a small game involving only three remainder groups.

🔍 Why Modulo 3 Matters

The running sum only matters modulo 3.

The possible states are:

0
1
2

A move using a stone with remainder 0 does not change the current remainder.

A move using a stone with remainder 1 increases the current remainder by 1.

A move using a stone with remainder 2 increases the current remainder by 2.

The player loses whenever the new sum becomes:

0 (mod 3)

So the entire game can be analyzed using remainder counts.

🧮 Remainder Groups
Remainder 0

A stone belongs to this group when:

stone % 3 == 0

Examples:

3, 6, 9, 12, ...

Adding such a stone does not change the current sum modulo 3.

For example:

1 + 0 ≡ 1 (mod 3)
2 + 0 ≡ 2 (mod 3)

Therefore, remainder-0 stones mainly affect the number of turns.

Remainder 1

A stone belongs to this group when:

stone % 3 == 1

Examples:

1, 4, 7, 10, ...

The modulo state changes as:

0 → 1
1 → 2
2 → 0
Remainder 2

A stone belongs to this group when:

stone % 3 == 2

Examples:

2, 5, 8, 11, ...

The modulo state changes as:

0 → 2
2 → 1
1 → 0
🧠 Key Observation

The two non-zero remainder groups are opposites:

1 + 2 = 3

Therefore:

1 + 2 ≡ 0 (mod 3)

This is the central observation of the problem.

When the current sum has remainder 1, choosing a remainder-2 stone would make the sum divisible by 3.

Similarly, when the current sum has remainder 2, choosing a remainder-1 stone would make the sum divisible by 3.

This creates a forced interaction between the remainder-1 and remainder-2 groups.

🎯 Main Mathematical Result

Let:

z = countZero
o = countOne
t = countTwo

There are two important cases depending on the parity of countZero.

🟢 Case 1 — countZero Is Even

If:

countZero % 2 == 0

then Alice can win if and only if both non-zero remainder groups contain at least one stone.

That means:

countOne != 0

and:

countTwo != 0

Therefore:

Alice wins if:


countOne > 0 && countTwo > 0
🔴 Case 2 — countZero Is Odd

If:

countZero % 2 == 1

the parity of the remainder-0 stones changes the outcome.

In this case, Alice wins only when the two non-zero groups are sufficiently unbalanced:

abs(countOne - countTwo) > 2

Otherwise, Bob can force a winning strategy.

🌳 Decision Diagram
                         Start
                           |
                           v
                 Count stones % 3
                           |
             +-------------+-------------+
             |                           |
             v                           v
     countZero is even          countZero is odd
             |                           |
             v                           v
     countOne > 0 AND          abs(countOne -
     countTwo > 0              countTwo) > 2
             |                           |
             v                           v
        Alice Wins?                Alice Wins?
             |                           |
             +-------------+-------------+
                           |
                           v
                    true / false
🚀 Approach 1 — Frequency Array

The first and recommended solution uses a frequency array of size 3.

cnt[0] → number of remainder-0 stones
cnt[1] → number of remainder-1 stones
cnt[2] → number of remainder-2 stones

We scan the array once:

for every stone:
    cnt[stone % 3]++

After counting the three groups, we directly apply the mathematical conditions.

🥇 Java Solution — Approach 1
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];


        for (int stone : stones) {
            cnt[stone % 3]++;
        }


        if (cnt[0] % 2 == 0) {
            return cnt[1] != 0 && cnt[2] != 0;
        }


        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
🔎 Approach 1 Explanation

First, we count the three remainder groups:

int[] cnt = new int[3];


for (int stone : stones) {
    cnt[stone % 3]++;
}

For example:

stones = [3, 4, 5, 6, 7]


3 % 3 = 0
4 % 3 = 1
5 % 3 = 2
6 % 3 = 0
7 % 3 = 1

Therefore:

cnt[0] = 2
cnt[1] = 2
cnt[2] = 1

Then we check the parity of cnt[0].

If it is even:

return cnt[1] != 0 && cnt[2] != 0;

If it is odd:

return Math.abs(cnt[1] - cnt[2]) > 2;
🥈 Approach 2 — Explicit Counters

The second solution uses three separate variables instead of an array:

countZero
countOne
countTwo

The logic is exactly the same.

The main difference is readability.

For beginners, this version can sometimes be easier to understand because every variable explicitly describes the remainder group it represents.

🥈 Java Solution — Approach 2
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;


        for (int stone : stones) {
            int remainder = stone % 3;


            if (remainder == 0) {
                countZero++;
            } else if (remainder == 1) {
                countOne++;
            } else {
                countTwo++;
            }
        }


        if (countZero % 2 == 0) {
            return countOne != 0 && countTwo != 0;
        }


        return Math.abs(countOne - countTwo) > 2;
    }
}
🔍 Approach 1 vs Approach 2
Feature	Approach 1	Approach 2
Counting	Frequency Array	Three Variables
Code Length	Shorter	Slightly Longer
Readability	Compact	Very Explicit
Time Complexity	O(n)	O(n)
Space Complexity	O(1)	O(1)
Recommended	⭐ Best	⭐ Second Best

Both approaches have the same asymptotic complexity.

🏆 Why Approach 1 Is Better

The frequency-array approach is slightly cleaner:

cnt[stone % 3]++;

This single line automatically places the stone into the correct remainder group.

Instead of writing separate conditions, we directly use the remainder as the array index.

Because the array always contains only three elements, the extra space remains constant:

O(1)
📖 Example 1
Input
stones = [2, 1]

Calculate remainders:

2 % 3 = 2
1 % 3 = 1

Counts:

countZero = 0
countOne  = 1
countTwo  = 1

Since:

countZero % 2 == 0

we check:

countOne != 0
AND
countTwo != 0

Both are true.

Therefore:

Output = true
📖 Example 2
Input
stones = [2]

Counts:

countZero = 0
countOne  = 0
countTwo  = 1

countZero is even.

But:

countOne == 0

Therefore:

Output = false
📖 Example 3
Input
stones = [2, 2, 2, 1, 1]

Counts:

countZero = 0
countOne  = 2
countTwo  = 3

Since countZero is even:

countOne != 0
AND
countTwo != 0

Both conditions are true.

Therefore:

Output = true
🔄 Dry Run

Consider:

stones = [3, 6, 7, 8, 10]

Calculate each remainder:

3  % 3 = 0
6  % 3 = 0
7  % 3 = 1
8  % 3 = 2
10 % 3 = 1

Therefore:

countZero = 2
countOne  = 2
countTwo  = 1

Now check:

countZero % 2
= 2 % 2
= 0

So countZero is even.

Now:

countOne != 0

is true.

And:

countTwo != 0

is true.

Therefore:

Alice can win.


Answer = true
🧩 Why We Do Not Simulate the Game

A brute-force game simulation would try to consider the different stones that players could select.

This is unnecessary.

The important property of every stone is only:

stone % 3

For example:

4 % 3 = 1
7 % 3 = 1
10 % 3 = 1

All three stones behave identically with respect to the modulo game.

Similarly:

2 % 3 = 2
5 % 3 = 2
8 % 3 = 2

These stones are also equivalent from the perspective of the game.

Therefore, we can compress the entire input into three counts.

🧠 Important Insight

This problem is a good example of reducing a game problem using modular arithmetic.

Instead of thinking about:

Actual Stone Values

think about:

              stone
                |
                v
             stone % 3
                |
       +--------+--------+
       |        |        |
       v        v        v
      0         1        2

Once the stones are grouped by remainder, the exact values disappear from consideration.

⚠️ Edge Cases
Only Remainder-0 Stones

If every stone is divisible by 3, Alice loses immediately when she chooses the first stone.

Therefore:

false
No Remainder-1 Stones

If:

countOne = 0

and countZero is even, Alice cannot win because she needs both non-zero remainder groups.

No Remainder-2 Stones

Similarly, if:

countTwo = 0

and countZero is even, Alice cannot win.

Large Input

The solution does not use recursion, sorting, dynamic programming, or extra arrays proportional to n.

Only three counters are maintained.

Therefore the space complexity remains:

O(1)
⏱️ Complexity Analysis

Let:

n = stones.length
Approach 1 — Frequency Array

We scan the array once:

Time Complexity = O(n)

The frequency array has only three elements:

cnt[0], cnt[1], cnt[2]

Therefore:

Space Complexity = O(1)
Approach 2 — Explicit Counters

We again scan the array once:

Time Complexity = O(n)

Only three integer variables are used:

countZero
countOne
countTwo

Therefore:

Space Complexity = O(1)
📊 Complexity Comparison
Approach	Technique	Time	Space	Rating
Approach 1	Frequency Array	O(n)	O(1)	⭐ Best
Approach 2	Explicit Counters	O(n)	O(1)	⭐ Second Best
💻 C++ Solution
Approach 1 — Frequency Array
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        int cnt[3] = {0, 0, 0};


        for (int stone : stones) {
            cnt[stone % 3]++;
        }


        if (cnt[0] % 2 == 0) {
            return cnt[1] != 0 && cnt[2] != 0;
        }


        return abs(cnt[1] - cnt[2]) > 2;
    }
};
Approach 2 — Explicit Counters
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;


        for (int stone : stones) {
            int remainder = stone % 3;


            if (remainder == 0) {
                countZero++;
            } else if (remainder == 1) {
                countOne++;
            } else {
                countTwo++;
            }
        }


        if (countZero % 2 == 0) {
            return countOne != 0 && countTwo != 0;
        }


        return abs(countOne - countTwo) > 2;
    }
};
🌳 Complete Logic Diagram
                         STONES
                            |
                            v
                     Calculate % 3
                            |
          +-----------------+-----------------+
          |                 |                 |
          v                 v                 v
     Remainder 0       Remainder 1       Remainder 2
          |                 |                 |
          v                 v                 v
     countZero          countOne          countTwo
          |                 |                 |
          +-----------------+-----------------+
                            |
                            v
                  Is countZero even?
                     /           \
                   YES            NO
                    |              |
                    v              v
          countOne > 0 AND    abs(countOne -
          countTwo > 0         countTwo) > 2
                    |              |
                    v              v
               Alice Wins?     Alice Wins?
                    |              |
                    +------+-------+
                           |
                           v
                      true / false
🎯 Key Takeaways
✅ The actual stone values are not important.
✅ Only stone % 3 matters.
✅ Group all stones into three remainder categories.
✅ Remainder 0 stones affect the parity of the game.
✅ Remainders 1 and 2 are the two opposing groups.
✅ If countZero is even, both countOne and countTwo must be non-zero.
✅ If countZero is odd, we check whether abs(countOne - countTwo) > 2.
✅ No game simulation is required.
✅ No sorting is required.
✅ No dynamic programming is required.
✅ Time Complexity: O(n).
✅ Space Complexity: O(1).
🏁 Final Summary

The key to solving Stone Game IX is recognizing that the game depends only on the sum modulo 3.

Therefore, every stone can be reduced to one of three categories:

0, 1, or 2

We count how many stones belong to each category and use the parity of the remainder-0 group along with the difference between the remainder-1 and remainder-2 groups to determine whether Alice has a winning strategy.

The final solution is extremely efficient:

Time Complexity  : O(n)
Space Complexity : O(1)
```
