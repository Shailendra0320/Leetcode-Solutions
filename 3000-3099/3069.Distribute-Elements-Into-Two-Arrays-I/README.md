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

# 1386. Cinema Seat Allocation

## Intuition

We have a cinema with `n` rows, and every row contains exactly **10 seats** numbered from `1` to `10`.

A family needs **4 consecutive seats**.

The important observation is that there are only **three possible positions** where a family can sit:

```text
Left Block   → seats 2, 3, 4, 5
Middle Block → seats 4, 5, 6, 7
Right Block  → seats 6, 7, 8, 9
```

Visual representation:

```text
Seats:

1 2 3 4 5 6 7 8 9 10
  └─────┘
    Left

1 2 3 4 5 6 7 8 9 10
      └─────┘
       Middle

1 2 3 4 5 6 7 8 9 10
          └─────┘
           Right
```

The most important observation is:

```text
Left + Right
```

can both be used at the same time because they do not overlap.

Therefore, a completely empty row can accommodate:

```text
2 families
```

For example:

```text
1 2 3 4 5 6 7 8 9 10
  └─────┘   └─────┘
   Family    Family
```

The middle block overlaps with both the left and right blocks.

Therefore, if both left and right are available, we can always place **2 families**.

Otherwise, if at least one of the three blocks is available, we can place **1 family**.

If none of the three blocks is available, we can place **0 families**.

---

The next important observation is that `n` can be extremely large, but only the rows mentioned in `reservedSeats` actually have reservations.

So instead of processing all `n` rows, we:

```text
1. Store only rows having reservations.
2. Represent reserved seats using a bitmask.
3. Give every completely untouched row 2 families.
4. Check only the rows containing reservations.
```

This gives an efficient solution.

---

# Approaches

1. **Bitmask + HashMap**
2. **Greedy Row Evaluation**

---

# Approach 1 — Bitmask + HashMap

## Idea

For every row that contains reserved seats, we create a bitmask.

Each seat is represented by one bit:

```text
seat 1 → bit 1
seat 2 → bit 2
seat 3 → bit 3
...
seat 10 → bit 10
```

If a seat is reserved:

```text
bit = 1
```

If a seat is free:

```text
bit = 0
```

For example, if seats `2` and `6` are reserved:

```text
Seat:

1 2 3 4 5 6 7 8 9 10
  X       X
```

We create the mask using:

```java
(1 << 2) | (1 << 6)
```

---

# Seat Block Masks

We create three masks.

## Left Block

Seats:

```text
2, 3, 4, 5
```

Code:

```java
final int LEFT_BLOCK =
        (1 << 2) |
        (1 << 3) |
        (1 << 4) |
        (1 << 5);
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
  █ █ █ █
```

---

## Middle Block

Seats:

```text
4, 5, 6, 7
```

Code:

```java
final int MID_BLOCK =
        (1 << 4) |
        (1 << 5) |
        (1 << 6) |
        (1 << 7);
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
      █ █ █ █
```

---

## Right Block

Seats:

```text
6, 7, 8, 9
```

Code:

```java
final int RIGHT_BLOCK =
        (1 << 6) |
        (1 << 7) |
        (1 << 8) |
        (1 << 9);
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
          █ █ █ █
```

---

# Why Seats 1 and 10 Are Not Included

A family needs 4 consecutive seats.

The valid family blocks are:

```text
2 3 4 5
4 5 6 7
6 7 8 9
```

Seats `1` and `10` cannot form another valid group of four under the problem's seating arrangement.

Therefore, we only need to check these three blocks.

---

# Step 1 — Build the Reservation Map

We use:

```java
Map<Integer, Integer> rowToReservedMask
```

The map stores:

```text
row number → reserved seats bitmask
```

For every reservation:

```java
int row = reservation[0];
int seat = reservation[1];
```

we update the corresponding row mask:

```java
int currentMask = rowToReservedMask.getOrDefault(row, 0);
currentMask |= (1 << seat);
rowToReservedMask.put(row, currentMask);
```

---

# Example — Building the Bitmask

Suppose:

```text
reservedSeats = [
    [1, 2],
    [1, 6],
    [3, 5]
]
```

Then:

```text
Row 1 → seats 2 and 6 reserved
Row 3 → seat 5 reserved
```

Conceptually:

```text
rowToReservedMask

1 → reserved seats {2, 6}
3 → reserved seats {5}
```

Rows without reservations do not need to be stored.

---

# Step 2 — Count Completely Empty Rows

Suppose:

```text
n = 5
```

and reservations occur only in:

```text
row 1
row 3
```

Then:

```text
Total rows       = 5
Reserved rows   = 2
Empty rows       = 3
```

Every completely empty row can accommodate 2 families.

Therefore:

```java
(long) (n - rowToReservedMask.size()) * 2
```

gives the contribution of all untouched rows.

This is extremely important because `n` can be much larger than the number of reservations.

---

# Step 3 — Check Each Reserved Row

For every reserved row, we check:

```text
Left
Middle
Right
```

using bitwise AND.

---

## Check Left Block

```java
boolean isLeftFree =
        (reservedMask & LEFT_BLOCK) == 0;
```

If the result is `0`, none of the seats in the left block are reserved.

Therefore:

```text
Left Block = Free
```

---

## Check Middle Block

```java
boolean isMidFree =
        (reservedMask & MID_BLOCK) == 0;
```

If the result is `0`:

```text
Middle Block = Free
```

---

## Check Right Block

```java
boolean isRightFree =
        (reservedMask & RIGHT_BLOCK) == 0;
```

If the result is `0`:

```text
Right Block = Free
```

---

# Step 4 — Determine Number of Families

Now there are three possibilities.

## Case 1 — Left and Right Are Both Free

```java
if (isLeftFree && isRightFree) {
    totalGroups += 2;
}
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
  └─────┘   └─────┘
   Family    Family
```

Therefore:

```text
+2 families
```

---

## Case 2 — At Least One Block Is Free

```java
else if (isLeftFree || isMidFree || isRightFree) {
    totalGroups += 1;
}
```

If we cannot place two families but one valid block is available:

```text
+1 family
```

---

## Case 3 — No Block Is Free

If:

```text
Left   = blocked
Middle = blocked
Right  = blocked
```

then:

```text
+0 families
```

---

# Approach 1 Visualization

```text
                         Cinema
                           │
                           ▼
                    n rows × 10 seats
                           │
                           ▼
                Read reservedSeats
                           │
                           ▼
             Build row → bitmask map
                           │
                           ▼
             Count completely empty rows
                           │
                           ▼
                    Empty Row?
                           │
                           ▼
                      +2 families
                           │
                           ▼
              Process reserved rows
                           │
                           ▼
              ┌───────────────────────┐
              │ Check three blocks    │
              │                       │
              │ Left  : 2 3 4 5       │
              │ Middle: 4 5 6 7       │
              │ Right : 6 7 8 9       │
              └───────────────────────┘
                           │
            ┌──────────────┼──────────────┐
            │              │              │
            ▼              ▼              ▼
       Left + Right     Any one free    None free
          free              │              │
            │               │              │
            ▼               ▼              ▼
         +2 families     +1 family      +0 families
            │               │              │
            └───────────────┼──────────────┘
                            ▼
                      Return answer
```

---

# Detailed Dry Run

## Input

Consider:

```text
n = 3

reservedSeats = [
    [1, 2],
    [1, 6],
    [2, 5],
    [2, 8]
]
```

We have:

```text
3 rows
```

Reserved rows:

```text
1
2
```

Therefore:

```text
row 3 is completely empty
```

---

# Step 1 — Empty Rows

Number of reserved rows:

```text
2
```

Total rows:

```text
3
```

Therefore:

```text
empty rows = 3 - 2
           = 1
```

Every empty row contributes:

```text
2 families
```

So:

```text
totalGroups = 2
```

---

# Step 2 — Process Row 1

Reservations:

```text
[1, 2]
[1, 6]
```

So row 1 has:

```text
seat 2 reserved
seat 6 reserved
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
  X       X
```

---

## Check Left Block

Left block:

```text
2 3 4 5
```

Seat `2` is reserved.

Therefore:

```text
Left = Blocked
```

---

## Check Middle Block

Middle block:

```text
4 5 6 7
```

Seat `6` is reserved.

Therefore:

```text
Middle = Blocked
```

---

## Check Right Block

Right block:

```text
6 7 8 9
```

Seat `6` is reserved.

Therefore:

```text
Right = Blocked
```

All three blocks are blocked:

```text
Left   → false
Middle → false
Right  → false
```

Therefore:

```text
Row 1 → +0 families
```

---

# Step 3 — Process Row 2

Reservations:

```text
[2, 5]
[2, 8]
```

So:

```text
seat 5 reserved
seat 8 reserved
```

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
        X       X
```

---

## Check Left Block

```text
2 3 4 5
```

Seat `5` is reserved.

Therefore:

```text
Left = Blocked
```

---

## Check Middle Block

```text
4 5 6 7
```

Seat `5` is reserved.

Therefore:

```text
Middle = Blocked
```

---

## Check Right Block

```text
6 7 8 9
```

Seat `8` is reserved.

Therefore:

```text
Right = Blocked
```

All three blocks are blocked.

Therefore:

```text
Row 2 → +0 families
```

---

# Step 4 — Process Row 3

Row 3 has no reservations.

Therefore:

```text
Left  = Free
Right = Free
```

We can place two families:

```text
1 2 3 4 5 6 7 8 9 10
  └─────┘   └─────┘
   Family    Family
```

Therefore:

```text
Row 3 → +2 families
```

---

# Final Result

Total:

```text
Row 1 → 0
Row 2 → 0
Row 3 → 2
```

Therefore:

```text
Answer = 2
```

---

# Another Dry Run — Two Families in a Reserved Row

Consider:

```text
n = 1

reservedSeats = [
    [1, 1],
    [1, 10]
]
```

Seats `1` and `10` are reserved.

Visualization:

```text
1 2 3 4 5 6 7 8 9 10
X                     X
```

The three blocks are:

```text
Left   → 2 3 4 5
Middle → 4 5 6 7
Right  → 6 7 8 9
```

None of these seats are reserved.

Therefore:

```text
Left  = Free
Right = Free
```

So:

```text
+2 families
```

Even though the row contains reservations, both families can still sit.

---

# Bitmask Visualization

Suppose a row has reserved seats:

```text
2, 6
```

Then:

```text
Seat:

1 2 3 4 5 6 7 8 9 10
  X       X
```

The mask is created using:

```java
(1 << 2) | (1 << 6)
```

Now check the left block:

```java
reservedMask & LEFT_BLOCK
```

Since seat `2` overlaps with the left block:

```text
result != 0
```

Therefore:

```text
Left Block = Blocked
```

Similarly, seat `6` blocks both:

```text
Middle Block
Right Block
```

So the row cannot accommodate a family.

---

# How Bitwise AND Works Here

The expression:

```java
reservedMask & LEFT_BLOCK
```

checks whether the reserved seats overlap with the left block.

There are two possibilities.

### Result = 0

```text
No overlap
```

Therefore:

```text
Block is free
```

### Result != 0

```text
At least one seat is reserved
```

Therefore:

```text
Block is blocked
```

This allows us to check four seats at once.

---

# Approach 1 Flowchart

```text
                         Start
                           │
                           ▼
                 Define three masks
                           │
                           ▼
                Create HashMap
                           │
                           ▼
             Process reservedSeats
                           │
                           ▼
             Store row → reservedMask
                           │
                           ▼
        Calculate completely empty rows
                           │
                           ▼
          emptyRows × 2 families
                           │
                           ▼
          Process each reserved row
                           │
                           ▼
             Check Left Block
                           │
                           ▼
            Check Middle Block
                           │
                           ▼
             Check Right Block
                           │
                           ▼
               Left && Right?
                  /        \
                Yes         No
                 │           │
                 ▼           ▼
               +2       Any block free?
                              /      \
                            Yes       No
                             │         │
                             ▼         ▼
                            +1        +0
                             │         │
                             └────┬────┘
                                  ▼
                         Process next row
                                  │
                                  ▼
                           More rows?
                            /      \
                          Yes       No
                           │         │
                           └─────────┤
                                     ▼
                               Return answer
```

---

# Why This Works

Every valid family requires four consecutive seats.

For each row, only these three possible blocks matter:

```text
Left   → 2,3,4,5
Middle → 4,5,6,7
Right  → 6,7,8,9
```

There is only one way to place two families:

```text
Left + Right
```

because:

```text
Left  = 2,3,4,5
Right = 6,7,8,9
```

are disjoint.

The middle block:

```text
4,5,6,7
```

overlaps with both.

Therefore:

```text
Left + Right free
        ↓
      2 families
```

If both are not available but at least one block is free:

```text
Any valid block free
        ↓
      1 family
```

If all three blocks are blocked:

```text
No valid block
        ↓
      0 families
```

This covers every possible state of a row.

---

# Correctness Proof

We can prove the algorithm by considering every row independently.

### Case 1 — Completely Empty Row

A completely empty row has no reserved seats.

Therefore both:

```text
2,3,4,5
```

and:

```text
6,7,8,9
```

are available.

Since they do not overlap, two families can be seated.

The algorithm adds:

```text
2
```

families.

---

### Case 2 — Left and Right Blocks Are Free

If:

```text
isLeftFree = true
isRightFree = true
```

then two disjoint groups of four can be placed.

Therefore the maximum is:

```text
2
```

The algorithm adds exactly two.

---

### Case 3 — At Least One Block Is Free

Suppose:

```text
isLeftFree || isMidFree || isRightFree
```

is true.

Then at least one valid group of four exists.

If both left and right are not free, two families cannot be placed.

Therefore the maximum is:

```text
1
```

The algorithm adds exactly one.

---

### Case 4 — No Block Is Free

If all three blocks are blocked:

```text
isLeftFree  = false
isMidFree   = false
isRightFree = false
```

then no valid group of four consecutive seats is available.

Therefore:

```text
0 families
```

can be seated.

The algorithm adds nothing.

---

Since every row falls into exactly one of these cases and each case is handled optimally, the final answer is the maximum possible number of families.

---

# Approach 1 — Complexity

Let:

```text
r = reservedSeats.length
u = number of unique rows containing reservations
```

Building the row-to-mask map requires processing every reservation once:

```text
O(r)
```

Processing the reserved rows requires:

```text
O(u)
```

Since:

```text
u <= r
```

the total time complexity is:

```text
O(r)
```

---

## Space Complexity

The HashMap stores one entry per row that has reservations.

Therefore:

```text
O(u)
```

Since:

```text
u <= r
```

we can write:

```text
O(r)
```

---

# Complexity Analysis

## Time Complexity

```text
Build HashMap
    ↓
O(r)

Process reserved rows
    ↓
O(u)

u <= r

Therefore:

O(r)
```

where:

```text
r = reservedSeats.length
```

---

## Space Complexity

```text
HashMap
   ↓
One entry per reserved row
   ↓
O(u)
   ↓
O(r)
```

Therefore:

```text
Time  → O(r)
Space → O(r)
```

---

# Comparison of Approaches

| Approach              | Main Idea                  | Time | Space |
| --------------------- | -------------------------- | ---: | ----: |
| Row-by-Row Simulation | Process every cinema row   | O(n) |  O(1) |
| HashMap + Bitmask     | Process only reserved rows | O(r) |  O(r) |

Here:

```text
n = number of cinema rows
r = reservedSeats.length
```

The optimized solution is preferable when:

```text
n >> r
```

because we avoid processing the huge number of untouched rows.

---

# Why HashMap Is Necessary

Suppose:

```text
n = 1,000,000,000
```

but:

```text
reservedSeats.length = 100
```

Only a tiny number of rows contain reservations.

If we process every row:

```text
1,000,000,000 rows
```

that would be wasteful.

Instead, the HashMap stores only:

```text
100 reserved rows
```

and all other rows are handled together:

```java
(n - rowToReservedMask.size()) * 2
```

This is the key optimization.

---

# Important Edge Cases

## Case 1 — No Reservations

Input:

```text
n = 5

reservedSeats = []
```

Every row is empty.

Each row can accommodate:

```text
2 families
```

Therefore:

```text
5 × 2 = 10
```

Answer:

```text
10
```

---

## Case 2 — One Reserved Seat Outside All Useful Blocks

Suppose:

```text
reservedSeats = [
    [1, 1]
]
```

Seat `1` is reserved.

But seat `1` does not belong to any of the three family blocks.

Therefore:

```text
Left  = Free
Right = Free
```

So the row can still accommodate:

```text
2 families
```

---

## Case 3 — Seat 10 Reserved

Suppose:

```text
reservedSeats = [
    [1, 10]
]
```

Seat `10` does not affect the three possible blocks.

Therefore:

```text
2 families
```

can still be seated.

---

## Case 4 — Left Block Blocked

Suppose:

```text
reservedSeats = [
    [1, 2]
]
```

Seat `2` blocks the left block.

But the right block remains available.

Therefore:

```text
+1 family
```

---

## Case 5 — Right Block Blocked

Suppose:

```text
reservedSeats = [
    [1, 9]
]
```

Seat `9` blocks the right block.

But the left block remains available.

Therefore:

```text
+1 family
```

---

## Case 6 — Middle Block Blocked

Suppose:

```text
reservedSeats = [
    [1, 5]
]
```

Seat `5` blocks:

```text
Left
Middle
```

But:

```text
Right
```

is still available.

Therefore:

```text
+1 family
```

---

## Case 7 — Both Left and Right Blocked

Suppose:

```text
reservedSeats = [
    [1, 2],
    [1, 9]
]
```

Then:

```text
Left  = Blocked
Right = Blocked
```

The middle block may or may not be available, but two families cannot be seated.

If middle is available:

```text
+1 family
```

---

## Case 8 — All Three Blocks Blocked

Suppose:

```text
reservedSeats = [
    [1, 2],
    [1, 5],
    [1, 8]
]
```

Then:

```text
Left   = Blocked
Middle = Blocked
Right  = Blocked
```

Therefore:

```text
+0 families
```

---

# Important Bit Manipulation Concepts

This problem is a good example of using bit manipulation to represent a small fixed-size state.

## Setting a Bit

```java
1 << seat
```

creates a mask where the bit corresponding to `seat` is set.

---

## Combining Reserved Seats

```java
currentMask |= (1 << seat);
```

adds the new reserved seat to the existing mask.

---

## Checking Overlap

```java
reservedMask & LEFT_BLOCK
```

checks whether any reserved seat belongs to the left block.

---

## Checking if a Block Is Completely Free

```java
(reservedMask & LEFT_BLOCK) == 0
```

means:

```text
No reserved seat overlaps with the block.
```

Therefore:

```text
Block is free.
```

---

# Memory Visualization

```text
                  Reserved Seats
                        │
                        ▼
              ┌──────────────────┐
              │     HashMap       │
              └──────────────────┘
                        │
                        ▼
             row → reservedMask
                        │
           ┌────────────┼────────────┐
           │            │            │
           ▼            ▼            ▼
        Row 1         Row 4        Row 9
           │            │            │
           ▼            ▼            ▼
        Bitmask       Bitmask      Bitmask
           │            │            │
           └────────────┼────────────┘
                        ▼
               Check three blocks
                        │
            ┌───────────┼───────────┐
            ▼           ▼           ▼
          Left        Middle       Right
            │           │           │
            └───────────┼───────────┘
                        ▼
                  Count families
```

---

# Overall Algorithm

```text
1. Define masks for:
       Left   = seats 2,3,4,5
       Middle = seats 4,5,6,7
       Right  = seats 6,7,8,9

2. Create:
       rowToReservedMask

3. For every reservation:
       store the seat in the row's bitmask.

4. Count rows without reservations:
       emptyRows = n - numberOfReservedRows

5. Add:
       emptyRows × 2

6. For every reserved row:
       check Left
       check Middle
       check Right

7. If Left and Right are free:
       +2

8. Else if any block is free:
       +1

9. Otherwise:
       +0

10. Return totalGroups.
```

---

# Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        final int LEFT_BLOCK  = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        final int MID_BLOCK   = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        final int RIGHT_BLOCK = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        Map<Integer, Integer> rowToReservedMask = new HashMap<>();

        for (int[] reservation : reservedSeats) {
            int row = reservation[0];
            int seat = reservation[1];

            int currentMask = rowToReservedMask.getOrDefault(row, 0);
            currentMask |= (1 << seat);
            rowToReservedMask.put(row, currentMask);
        }

        long totalGroups = (long) (n - rowToReservedMask.size()) * 2;

        for (int reservedMask : rowToReservedMask.values()) {
            boolean isLeftFree = (reservedMask & LEFT_BLOCK) == 0;
            boolean isMidFree = (reservedMask & MID_BLOCK) == 0;
            boolean isRightFree = (reservedMask & RIGHT_BLOCK) == 0;

            if (isLeftFree && isRightFree) {
                totalGroups += 2;
            } else if (isLeftFree || isMidFree || isRightFree) {
                totalGroups += 1;
            }
        }

        return (int) totalGroups;
    }
}
```

---

# C++ Solution

```cpp
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        const int LEFT_BLOCK =
            (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);

        const int MID_BLOCK =
            (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        const int RIGHT_BLOCK =
            (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        unordered_map<int, int> rowToReservedMask;

        for (auto& reservation : reservedSeats) {
            int row = reservation[0];
            int seat = reservation[1];

            rowToReservedMask[row] |= (1 << seat);
        }

        long long totalGroups =
            1LL * (n - rowToReservedMask.size()) * 2;

        for (auto& [row, reservedMask] : rowToReservedMask) {
            bool isLeftFree =
                (reservedMask & LEFT_BLOCK) == 0;

            bool isMidFree =
                (reservedMask & MID_BLOCK) == 0;

            bool isRightFree =
                (reservedMask & RIGHT_BLOCK) == 0;

            if (isLeftFree && isRightFree) {
                totalGroups += 2;
            } else if (isLeftFree || isMidFree || isRightFree) {
                totalGroups += 1;
            }
        }

        return (int)totalGroups;
    }
};
```

---

# Java Code Explanation

## `LEFT_BLOCK`

```java
final int LEFT_BLOCK =
        (1 << 2) |
        (1 << 3) |
        (1 << 4) |
        (1 << 5);
```

Represents:

```text
2, 3, 4, 5
```

---

## `MID_BLOCK`

```java
final int MID_BLOCK =
        (1 << 4) |
        (1 << 5) |
        (1 << 6) |
        (1 << 7);
```

Represents:

```text
4, 5, 6, 7
```

---

## `RIGHT_BLOCK`

```java
final int RIGHT_BLOCK =
        (1 << 6) |
        (1 << 7) |
        (1 << 8) |
        (1 << 9);
```

Represents:

```text
6, 7, 8, 9
```

---

## `rowToReservedMask`

```java
Map<Integer, Integer> rowToReservedMask =
        new HashMap<>();
```

Stores:

```text
row → reserved seat bitmask
```

---

## Updating the Mask

```java
int currentMask =
        rowToReservedMask.getOrDefault(row, 0);

currentMask |= (1 << seat);
```

This sets the bit for the reserved seat.

---

## Counting Empty Rows

```java
long totalGroups =
        (long) (n - rowToReservedMask.size()) * 2;
```

Every untouched row contributes exactly two families.

---

## Checking the Blocks

```java
boolean isLeftFree =
        (reservedMask & LEFT_BLOCK) == 0;
```

The same logic is used for middle and right.

---

## Adding Families

```java
if (isLeftFree && isRightFree) {
    totalGroups += 2;
}
```

Otherwise:

```java
else if (isLeftFree || isMidFree || isRightFree) {
    totalGroups += 1;
}
```

---

# Final Complexity Comparison

```text
┌────────────────────────────┬──────────────┬──────────────┐
│ Approach                   │ Time         │ Space        │
├────────────────────────────┼──────────────┼──────────────┤
│ Process Every Row          │ O(n)         │ O(1)         │
│ HashMap + Bitmask          │ O(r)         │ O(r)         │
└────────────────────────────┴──────────────┴──────────────┘
```

Where:

```text
n = number of cinema rows
r = reservedSeats.length
```

The HashMap + Bitmask solution is especially useful when:

```text
n is very large
r is relatively small
```

---

# Key Takeaways

The core idea of this problem is:

```text
Cinema Row
     │
     ▼
Three possible blocks
     │
     ├── Left   → 2,3,4,5
     ├── Middle → 4,5,6,7
     └── Right  → 6,7,8,9
```

The important rule is:

```text
Left + Right
     ↓
2 families
```

Otherwise:

```text
Any one block
     ↓
1 family
```

And:

```text
No block
     ↓
0 families
```

The major optimization is:

```text
Do NOT iterate through all n rows.
```

Instead:

```text
Reserved rows
     ↓
HashMap
     ↓
Bitmask
     ↓
Check three blocks
```

while:

```text
Completely empty rows
     ↓
2 families each
```

The most important bit manipulation operation is:

```java
(reservedMask & BLOCK) == 0
```

which tells us whether all seats of a particular family block are available.

---

# Final Summary

```text
                 LeetCode 1386
                       │
                       ▼
              Cinema Seat Allocation
                       │
                       ▼
              Store reservations
                       │
                       ▼
                HashMap + Bitmask
                       │
                       ▼
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
    Empty rows                 Reserved rows
          │                         │
          ▼                         ▼
       +2 each             Check Left/Middle/Right
                                    │
                         ┌──────────┼──────────┐
                         │          │          │
                         ▼          ▼          ▼
                     L + R free   Any free   None
                         │          │          │
                         ▼          ▼          ▼
                        +2         +1         +0
                         │          │          │
                         └──────────┼──────────┘
                                    ▼
                              Final Answer
```

The solution uses:

```text
HashMap
+
Bit Manipulation
+
Greedy Case Analysis
```

to efficiently calculate the maximum number of families.

```text
Time Complexity  → O(reservedSeats.length)
Space Complexity → O(reservedSeats.length)
```

---

# Tags

`Array` `Hash Table` `Bit Manipulation` `Bitmask` `Greedy` `Simulation` `HashMap` `LeetCode`
