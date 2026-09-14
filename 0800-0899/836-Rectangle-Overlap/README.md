# 836. Rectangle Overlap

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

# 📌 Problem Overview

You are given two axis-aligned rectangles:

```text
rec1 = [x1, y1, x2, y2]
rec2 = [x3, y3, x4, y4]
```

For every rectangle:

```text
(x1, y1) = bottom-left corner
(x2, y2) = top-right corner
```

The rectangles are guaranteed to be axis-aligned, meaning their sides are parallel to the x-axis and y-axis.

The task is to determine whether the two rectangles overlap with a **strictly positive area**.

### Important Rule

Touching is **not** considered overlap.

That means these cases return `false`:

```text
Edge touching
Corner touching
No intersection
```

Only this counts:

```text
Positive width
        AND
Positive height
```

### LeetCode

[835? No — LeetCode 836: Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap/)

---

# 🧠 What Is the Question Really Asking?

At first glance, this looks like a 2D computational geometry problem.

But the rectangles are axis-aligned.

That gives us a huge simplification:

> A rectangle can be viewed as two independent 1D intervals.

For each rectangle:

```text
Rectangle
   |
   +---------------- X-axis interval
   |
   +---------------- Y-axis interval
```

Therefore, two rectangles have a positive-area intersection only when:

```text
X intervals overlap positively
                AND
Y intervals overlap positively
```

This is the central idea behind the entire problem.

---

# 🗺️ High-Level Architecture

```text
                 +------------------------+
                 |      Input Rectangles  |
                 |  rec1 = [x1,y1,x2,y2] |
                 |  rec2 = [x3,y3,x4,y4] |
                 +-----------+------------+
                             |
                             v
                 +------------------------+
                 |  Separate by Axis     |
                 +-----------+------------+
                             |
                  +----------+----------+
                  |                     |
                  v                     v
        +----------------+    +----------------+
        |  X-axis Check  |    |  Y-axis Check  |
        |   intervals    |    |   intervals    |
        +--------+-------+    +--------+-------+
                 |                     |
                 +----------+----------+
                            |
                            v
                 +------------------------+
                 | Positive overlap on    |
                 | both dimensions?       |
                 +-----------+------------+
                             |
                     +-------+-------+
                     |               |
                    YES              NO
                     |               |
                     v               v
                  `true`           `false`
```

---

# 🔍 Core Observation

Suppose the x-ranges are:

```text
Rectangle 1: [x1, x2]
Rectangle 2: [x3, x4]
```

The common horizontal segment starts at:

```text
max(x1, x3)
```

and ends at:

```text
min(x2, x4)
```

Therefore the overlap width is:

```text
min(x2, x4) - max(x1, x3)
```

For positive overlap:

```text
min(x2, x4) - max(x1, x3) > 0
```

which is equivalent to:

```text
max(x1, x3) < min(x2, x4)
```

Similarly, the overlap height is:

```text
min(y2, y4) - max(y1, y3)
```

and we need:

```text
max(y1, y3) < min(y2, y4)
```

Therefore:

```text
Rectangle Overlap
=
Positive X overlap
AND
Positive Y overlap
```

---

# 📐 Visual Intuition

## Case 1 — Positive Overlap

```text
             Rectangle 2
          +-------------+
          |             |
          |     +-------|------+
          |     |       |      |
          +-----|-------+      |
                |              |
                +--------------+

             Rectangle 1
```

There is a region having:

```text
width > 0
height > 0
```

So:

```text
true
```

---

## Case 2 — Touching at an Edge

```text
+----------+----------+
| Rect 1   | Rect 2   |
|          |          |
+----------+----------+
           ^
        only edge
```

The intersection width is:

```text
0
```

So:

```text
false
```

---

## Case 3 — Touching at a Corner

```text
+---------+
|         |
|   R1    |
+---------+
          +
          |
          |   R2
          |
```

The intersection area is:

```text
0
```

So:

```text
false
```

---

## Case 4 — Completely Separate

```text
+---------+             +---------+
|   R1    |             |   R2    |
+---------+             +---------+
```

No overlap exists.

Therefore:

```text
false
```

---

# 🧩 Two Main Ways to Think About the Problem

There are two equivalent viewpoints.

### Viewpoint A — Calculate the intersection

Find:

```text
left
right
bottom
top
```

Then check whether:

```text
width > 0
AND
height > 0
```

### Viewpoint B — Find a separating condition

The rectangles cannot overlap if one is:

```text
completely left
completely right
completely above
completely below
```

If none of those four situations occurs, the rectangles must overlap positively.

Both lead to an `O(1)` solution.

---

# 1️⃣ Approach 1 — Intersection Boundaries

This approach explicitly constructs the dimensions of the intersection.

## Step 1 — Find the intersection boundaries

```text
left   = max(rec1[0], rec2[0])
right  = min(rec1[2], rec2[2])

bottom = max(rec1[1], rec2[1])
top    = min(rec1[3], rec2[3])
```

Why?

The intersection can start no earlier than the larger left boundary:

```text
max(left1, left2)
```

and cannot continue beyond the smaller right boundary:

```text
min(right1, right2)
```

The same reasoning works vertically.

---

## Step 2 — Calculate intersection dimensions

```text
width  = right - left
height = top - bottom
```

---

## Step 3 — Check whether the area is positive

Positive area requires:

```text
width > 0
AND
height > 0
```

We do **not** use:

```text
>= 0
```

because:

```text
width = 0
```

or:

```text
height = 0
```

means the rectangles only touch.

---

## 🔄 Approach 1 Flowchart

```text
              Start
                |
                v
        Read both rectangles
                |
                v
   left   = max(left boundaries)
   right  = min(right boundaries)
   bottom = max(bottom boundaries)
   top    = min(top boundaries)
                |
                v
      width  = right - left
      height = top - bottom
                |
                v
      width > 0 && height > 0 ?
            /             \
          YES             NO
           |               |
           v               v
        return true     return false
```

---

## ✅ Java — Approach 1

```java
//Approach-1 (Intersection Boundaries)
//T.C : O(1)
//S.C : O(1)

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        int left = Math.max(rec1[0], rec2[0]);
        int bottom = Math.max(rec1[1], rec2[1]);

        int right = Math.min(rec1[2], rec2[2]);
        int top = Math.min(rec1[3], rec2[3]);

        int width = right - left;
        int height = top - bottom;

        return width > 0 && height > 0;
    }
}
```

---

## ✅ C++ — Approach 1

```cpp
//Approach-1 (Intersection Boundaries)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:
    bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {

        int left = max(rec1[0], rec2[0]);
        int bottom = max(rec1[1], rec2[1]);

        int right = min(rec1[2], rec2[2]);
        int top = min(rec1[3], rec2[3]);

        int width = right - left;
        int height = top - bottom;

        return width > 0 && height > 0;
    }
};
```

---

# 2️⃣ Approach 2 — Separating Conditions

This approach asks a different question:

> Instead of proving that they overlap, when can I prove that they **do not** overlap?

There are exactly four ways for two axis-aligned rectangles to have no positive-area intersection.

---

## Case A — Rectangle 1 is Completely Left of Rectangle 2

```text
+---------+      +---------+
|   R1    |      |   R2    |
+---------+      +---------+
          gap
```

Condition:

```text
rec1[2] <= rec2[0]
```

---

## Case B — Rectangle 2 is Completely Left of Rectangle 1

```text
+---------+      +---------+
|   R2    |      |   R1    |
+---------+      +---------+
```

Condition:

```text
rec2[2] <= rec1[0]
```

---

## Case C — Rectangle 1 is Completely Below Rectangle 2

```text
+---------+
|   R2    |
+---------+

+---------+
|   R1    |
+---------+
```

Condition:

```text
rec1[3] <= rec2[1]
```

---

## Case D — Rectangle 2 is Completely Below Rectangle 1

Condition:

```text
rec2[3] <= rec1[1]
```

---

## Combine the Conditions

No positive overlap if:

```text
rec1[2] <= rec2[0]
OR
rec2[2] <= rec1[0]
OR
rec1[3] <= rec2[1]
OR
rec2[3] <= rec1[1]
```

Therefore the answer is:

```text
NOT(separated)
```

---

## 🔄 Approach 2 Flowchart

```text
                  Start
                    |
                    v
          Check horizontal separation
                    |
             +------+------+
             |             |
            Yes            No
             |             |
             v             v
          false     Check vertical separation
                           |
                    +------+------+
                    |             |
                   Yes            No
                    |             |
                    v             v
                 false          true
```

---

## ✅ Java — Approach 2

```java
//Approach-2 (Separating Conditions)
//T.C : O(1)
//S.C : O(1)

class Solution2 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        // Completely separated horizontally
        if (rec1[2] <= rec2[0] || rec2[2] <= rec1[0]) {
            return false;
        }

        // Completely separated vertically
        if (rec1[3] <= rec2[1] || rec2[3] <= rec1[1]) {
            return false;
        }

        return true;
    }
}
```

---

## ✅ C++ — Approach 2

```cpp
//Approach-2 (Separating Conditions)
//T.C : O(1)
//S.C : O(1)

class Solution2 {
public:
    bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {

        // Completely separated horizontally
        if (rec1[2] <= rec2[0] || rec2[2] <= rec1[0]) {
            return false;
        }

        // Completely separated vertically
        if (rec1[3] <= rec2[1] || rec2[3] <= rec1[1]) {
            return false;
        }

        return true;
    }
};
```

---

# 🧪 Detailed Dry Runs

## Dry Run 1 — Positive Overlap

```text
rec1 = [0, 0, 2, 2]
rec2 = [1, 1, 3, 3]
```

### Intersection

```text
left   = max(0, 1) = 1
right  = min(2, 3) = 2

bottom = max(0, 1) = 1
top    = min(2, 3) = 2
```

Therefore:

```text
width  = 2 - 1 = 1
height = 2 - 1 = 1
```

Area:

```text
1 × 1 = 1
```

Since:

```text
width > 0
height > 0
```

answer:

```text
true
```

---

## Dry Run 2 — Touching at an Edge

```text
rec1 = [0, 0, 2, 2]
rec2 = [2, 0, 4, 2]
```

Calculate:

```text
left  = max(0, 2) = 2
right = min(2, 4) = 2
```

Thus:

```text
width = 2 - 2 = 0
```

They share only a boundary.

Answer:

```text
false
```

---

## Dry Run 3 — Touching at a Corner

```text
rec1 = [0, 0, 2, 2]
rec2 = [2, 2, 4, 4]
```

Intersection:

```text
left   = 2
right  = 2
bottom = 2
top    = 2
```

Therefore:

```text
width  = 0
height = 0
```

Answer:

```text
false
```

---

## Dry Run 4 — Complete Separation

```text
rec1 = [0, 0, 1, 1]
rec2 = [2, 2, 4, 4]
```

Check:

```text
rec1[2] <= rec2[0]
1 <= 2
```

This is true.

So `rec1` is completely to the left of `rec2`.

Answer:

```text
false
```

---

# 🧠 Correctness Proof

## Approach 1

The intersection rectangle, if it exists, has:

```text
left   = max(rec1.left, rec2.left)
right  = min(rec1.right, rec2.right)

bottom = max(rec1.bottom, rec2.bottom)
top    = min(rec1.top, rec2.top)
```

Therefore its dimensions are:

```text
width  = right - left
height = top - bottom
```

A rectangle has positive area exactly when:

```text
width > 0
AND
height > 0
```

Thus the algorithm returns `true` exactly when the rectangles share a positive-area region.

---

## Approach 2

Assume the rectangles do not have positive-area overlap.

Then there must be a separating axis because the rectangles are axis-aligned.

That separation must be one of:

```text
R1 left of R2
R2 left of R1
R1 below R2
R2 below R1
```

These are exactly the four conditions:

```text
rec1[2] <= rec2[0]
rec2[2] <= rec1[0]
rec1[3] <= rec2[1]
rec2[3] <= rec1[1]
```

If any is true, returning `false` is correct.

If none is true, the rectangles overlap positively on both x and y axes, so returning `true` is correct.

Therefore Approach 2 is also correct.

---

# ⚠️ Why `<=` Matters

This is one of the most important details in this problem.

Suppose:

```text
rec1.right == rec2.left
```

Then the rectangles touch exactly at an edge.

The horizontal overlap is:

```text
0
```

Therefore:

```text
false
```

That is why we must write:

```text
rec1[2] <= rec2[0]
```

rather than:

```text
rec1[2] < rec2[0]
```

The same idea applies to vertical boundaries.

---

# ❌ Common Mistakes

## Mistake 1 — Treating touching as overlap

Incorrect thinking:

```text
They have a common boundary,
so they overlap.
```

Correct:

```text
Positive area only.
```

---

## Mistake 2 — Checking only one dimension

Having horizontal overlap is not sufficient.

Example:

```text
X overlaps
Y does not
```

The total intersection area is still `0`.

---

## Mistake 3 — Mixing corner definitions

Remember:

```text
[x1, y1, x2, y2]
```

means:

```text
(x1, y1) → bottom-left
(x2, y2) → top-right
```

So:

```text
x1 < x2
y1 < y2
```

describe the rectangle's width and height.

---

## Mistake 4 — Writing unnecessary simulation

There is no need to:

```text
loop over coordinates
create a grid
calculate points
```

The entire answer can be derived using four coordinates.

---

# 🎯 Interview Thought Process

A strong interview solution can be derived in under a minute:

```text
What does positive-area overlap mean?

        ↓

They must overlap horizontally.

        AND

They must overlap vertically.

        ↓

How can I check horizontal overlap?

max(left1, left2) < min(right1, right2)

        ↓

How can I check vertical overlap?

max(bottom1, bottom2) < min(top1, top2)

        ↓

Both true → overlap.
```

An even cleaner reasoning path is:

```text
When can they NOT overlap?

Left?
Right?
Above?
Below?

If any → false
Else → true
```

---

# 🧩 Pattern Recognition

This problem belongs to a reusable family:

```text
Interval Overlap
+
Coordinate Geometry
+
Axis-Aligned Shapes
```

The 1D pattern:

```text
[a, b]
[c, d]
```

has positive overlap if:

```text
max(a, c) < min(b, d)
```

For 2D rectangles:

```text
X intervals overlap
        AND
Y intervals overlap
```

This idea generalizes to many problems involving:

- rectangles
- bounding boxes
- grids
- scheduling intervals
- collision detection
- computational geometry

---

# 🔥 Mental Model

Think:

```text
Rectangle
   |
   +---- X interval
   |
   +---- Y interval
```

Then:

```text
Does X overlap?
        |
       AND
        |
Does Y overlap?
        |
       YES
        ↓
Rectangle overlap
```

This removes most of the complexity.

---

# 📌 Formula Sheet

## Intersection Formula

```text
left   = max(x1, x3)
right  = min(x2, x4)

bottom = max(y1, y3)
top    = min(y2, y4)
```

Then:

```text
width  = right - left
height = top - bottom
```

Positive overlap:

```text
width > 0 && height > 0
```

---

## Separation Formula

No positive overlap if:

```text
x2 <= x3
OR
x4 <= x1
OR
y2 <= y3
OR
y4 <= y1
```

Therefore:

```text
answer = NOT(separated)
```

---

# 🏗️ Solution Architecture

```text
                   Rectangle 1
              [x1, y1, x2, y2]
                       |
                       |
                       v
               +---------------+
               | Normalize View|
               +-------+-------+
                       |
                       v
              +-----------------+
              | Split Into Axes|
              +---+---------+---+
                  |         |
                  v         v
               X-axis     Y-axis
               interval   interval
                  |         |
                  v         v
             overlap > 0  overlap > 0
                  |         |
                  +----+----+
                       |
                       v
                    AND
                       |
                       v
                  Boolean Result
```

The algorithm does not build any explicit geometric object. It works entirely with boundary coordinates.

---

# 📊 Complexity Analysis

| Approach                |   Time |  Space |
| ----------------------- | -----: | -----: |
| Intersection Boundaries | `O(1)` | `O(1)` |
| Separating Conditions   | `O(1)` | `O(1)` |

There are only a constant number of arithmetic operations and comparisons.

---

# 🏆 Best Approach

For learning geometry:

```text
Approach 1 — Intersection Boundaries
```

is very intuitive because you explicitly calculate the intersection.

For production/interview code:

```text
Approach 2 — Separating Conditions
```

is extremely concise and directly captures the four ways overlap can fail.

Both are:

```text
O(1) time
O(1) space
```

---

# 🔄 Why No Loop Is Needed

The input contains only four coordinates per rectangle:

```text
x1, y1, x2, y2
```

There is no need to inspect:

```text
pixels
grid cells
points
individual coordinates
```

The geometry is completely determined by the boundaries.

So the optimal solution naturally runs in:

```text
O(1)
```

---

# 🧠 Deeper Mathematical View

The area of the intersection can be written as:

```text
max(0, min(x2, x4) - max(x1, x3))
×
max(0, min(y2, y4) - max(y1, y3))
```

The rectangles overlap with positive area exactly when this product is greater than zero.

Because both factors are non-negative, that is equivalent to:

```text
min(x2, x4) > max(x1, x3)
AND
min(y2, y4) > max(y1, y3)
```

This is another derivation of the same solution.

---

# 📝 Final Summary

The most important idea is:

> **Reduce 2D rectangle overlap to two 1D interval-overlap checks.**

For the x-axis:

```text
max(left boundaries) < min(right boundaries)
```

For the y-axis:

```text
max(bottom boundaries) < min(top boundaries)
```

Both must be true.

Alternatively, detect the four separating cases:

```text
left
right
above
below
```

The final complexity is:

```text
Time  : O(1)
Space : O(1)
```

---

# 💎 One-Line Insight

> **Two axis-aligned rectangles overlap with positive area iff their projections on both the x-axis and y-axis overlap by a strictly positive length.**

---

# 📚 What To Remember

When you see an axis-aligned rectangle problem, immediately ask:

```text
Can I reduce this to intervals?
```

If yes, check:

```text
X dimension
+
Y dimension
```

And always pay attention to whether the problem requires:

```text
positive overlap
```

or allows:

```text
touching boundaries
```

That single distinction determines whether you use:

```text
<
```

or:

```text
<=
```

---

## 🏷️ Tags

`Math, Geometry, Rectangle, Intervals, Coordinate Geometry, Computational Geometry, Simulation, Bounding Box, Overlap Detection, LeetCode, Easy`

---

## 📁 Recommended Folder Structure

```text
C:\Leetcode_Solutions
│
└── 0800-0899
    │
    └── 836-Rectangle-Overlap
        │
        ├── README.md
        ├── Solution.java
        └── Solution.cpp
```

---

## 📌 GitHub Note

Both approaches are included in the source files for learning and comparison.

On LeetCode, submit one `Solution` class at a time.
