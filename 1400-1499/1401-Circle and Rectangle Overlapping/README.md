# 1401. Circle and Rectangle Overlapping | Geometry | Math | Java & C++

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

# 🔗 Problem Link

[LeetCode 1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping/)

---

# 📝 Problem Statement

You are given:

- A circle represented by:
  ```text
  radius, xCenter, yCenter
  ```
- An axis-aligned rectangle represented by:
  ```text
  x1, y1, x2, y2
  ```

where:

```text
(x1, y1) = bottom-left corner
(x2, y2) = top-right corner
```

Return `true` if the circle and rectangle have at least one point in common.

Otherwise, return `false`.

In other words:

```text
Circle ∩ Rectangle ≠ ∅
        ↓
      true
```

and:

```text
Circle ∩ Rectangle = ∅
        ↓
      false
```

---

# 📌 Constraints

```text
1 <= radius <= 2000

-10^4 <= xCenter, yCenter <= 10^4

-10^4 <= x1 < x2 <= 10^4
-10^4 <= y1 < y2 <= 10^4
```

The rectangle is axis-aligned, meaning its sides are parallel to the x-axis and y-axis.

---

# 🧠 Core Idea

The main difficulty is that the circle and rectangle are two different geometric shapes.

A direct approach might try to check:

```text
Does a rectangle corner lie inside the circle?
Does the circle center lie inside the rectangle?
Does an edge intersect the circle?
...
```

This leads to many cases.

Instead, we can reduce the entire problem to **one point**.

## The Key Observation

Find the point inside the rectangle that is **closest to the circle center**.

Let:

```text
Circle center = (cx, cy)
Closest rectangle point = (px, py)
```

Then:

```text
Circle and rectangle overlap
        ⇕
Distance from center to closest rectangle point <= radius
```

So the problem becomes:

```text
Find closest point
        ↓
Calculate squared distance
        ↓
Compare with radius²
```

---

# 🎯 Why the Closest Point Is Enough

A circle contains exactly all points whose distance from its center is at most the radius.

So for the rectangle:

- If its closest point is outside the circle, then every other rectangle point is even farther away.
- If its closest point is on or inside the circle, the rectangle and circle share that point.

Therefore:

```text
minimum distance from circle center to rectangle
                <=
              radius
```

is the exact condition for overlap.

---

# 📐 Geometry Diagram

Consider this situation:

```text
                    Rectangle
              +----------------------+
              |                      |
              |          ● P         |
              |          |           |
              |          |           |
              |        ● C            |
              |      Circle Center    |
              |                      |
              +----------------------+

C = circle center
P = closest point in rectangle
```

The important distance is:

```text
distance(C, P)
```

If:

```text
distance(C, P) <= radius
```

then the circle reaches the rectangle.

---

# 🔍 The Closest Point Using Clamping

For the x-coordinate, the rectangle allows:

```text
x1 <= x <= x2
```

Therefore the closest valid x-coordinate to `xCenter` is:

```text
closestX = clamp(xCenter, x1, x2)
```

Similarly:

```text
closestY = clamp(yCenter, y1, y2)
```

where:

```text
clamp(value, low, high)
```

means:

```text
if value < low:
    return low

if value > high:
    return high

otherwise:
    return value
```

So:

```text
closestX = max(x1, min(xCenter, x2))
closestY = max(y1, min(yCenter, y2))
```

---

# 🧩 What Does Clamping Mean?

Suppose:

```text
x1 = 2
x2 = 8
```

### Case 1

```text
xCenter = 5
```

The center is already inside the x-range:

```text
2 <= 5 <= 8
```

Therefore:

```text
closestX = 5
```

---

### Case 2

```text
xCenter = 1
```

The center is left of the rectangle:

```text
1 < 2
```

The closest x-coordinate inside the rectangle is:

```text
closestX = 2
```

---

### Case 3

```text
xCenter = 10
```

The center is right of the rectangle:

```text
10 > 8
```

The closest x-coordinate is:

```text
closestX = 8
```

---

# 📊 Clamping Visualization

```text
x-axis:

        rectangle
        |--------|
        2        8

Case 1:
             C
             |
             5
closestX = 5

Case 2:
      C
      |
      1       |--------|
              2        8
closestX = 2

Case 3:
                  |--------|       C
                  2        8       |
closestX = 8
```

Exactly the same idea is applied to the y-coordinate.

---

# 🏗️ Solution Architecture

The complete solution can be visualized as:

```text
                    Input
                      |
          +-----------+-----------+
          |                       |
          v                       v
     Circle data             Rectangle data
  (cx, cy, r)             (x1, y1, x2, y2)
          |                       |
          +-----------+-----------+
                      |
                      v
             Find closest point
              inside rectangle
                      |
              +-------+-------+
              |               |
              v               v
          closestX        closestY
              |               |
              +-------+-------+
                      |
                      v
              dx = cx - closestX
              dy = cy - closestY
                      |
                      v
          distance² = dx² + dy²
                      |
                      v
             distance² <= r² ?
                 /         \
               YES          NO
                |            |
                v            v
              true         false
```

---

# 🔥 Approach 1 — Closest Point + Clamping

This is the cleanest and most general approach.

## Step 1 — Find the closest x-coordinate

```java
int closestX = Math.max(x1, Math.min(xCenter, x2));
```

---

## Step 2 — Find the closest y-coordinate

```java
int closestY = Math.max(y1, Math.min(yCenter, y2));
```

---

## Step 3 — Calculate squared distance

Normally:

```text
distance = sqrt(dx² + dy²)
```

But we do not need the square root.

We can compare:

```text
dx² + dy²
```

directly with:

```text
radius²
```

So:

```text
dx² + dy² <= radius²
```

means overlap.

This is better because:

- it is simpler,
- it avoids floating-point calculations,
- it is more precise,
- it is O(1).

---

# 📐 Mathematical Derivation

Circle equation:

```text
(x - xCenter)² + (y - yCenter)² <= radius²
```

For the closest rectangle point `(closestX, closestY)`:

```text
(dx)² + (dy)² <= radius²
```

where:

```text
dx = xCenter - closestX
dy = yCenter - closestY
```

Therefore:

```text
(xCenter - closestX)²
+
(yCenter - closestY)²
<=
radius²
```

This is the complete overlap test.

---

# ✅ Java — Approach 1

```java
//Approach-1 (Closest Point + Clamping)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int closestX = Math.max(
            x1,
            Math.min(xCenter, x2)
        );

        int closestY = Math.max(
            y1,
            Math.min(yCenter, y2)
        );

        long dx = xCenter - closestX;
        long dy = yCenter - closestY;

        long distanceSquared = dx * dx + dy * dy;
        long radiusSquared = (long) radius * radius;

        return distanceSquared <= radiusSquared;
    }
}
```

---

# ✅ C++ — Approach 1

```cpp
//Approach-1 (Closest Point + Clamping)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    bool checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        int closestX = max(
            x1,
            min(xCenter, x2)
        );

        int closestY = max(
            y1,
            min(yCenter, y2)
        );

        long long dx = xCenter - closestX;
        long long dy = yCenter - closestY;

        long long distanceSquared =
            dx * dx + dy * dy;

        long long radiusSquared =
            1LL * radius * radius;

        return distanceSquared <= radiusSquared;
    }
};
```

---

# 🔍 Java Code Explanation

## 1. Find `closestX`

```java
int closestX = Math.max(
    x1,
    Math.min(xCenter, x2)
);
```

Let's break it down.

First:

```java
Math.min(xCenter, x2)
```

prevents the value from going beyond the right boundary.

Then:

```java
Math.max(x1, ...)
```

prevents the value from going below the left boundary.

Therefore:

```text
closestX ∈ [x1, x2]
```

---

## 2. Find `closestY`

```java
int closestY = Math.max(
    y1,
    Math.min(yCenter, y2)
);
```

Therefore:

```text
closestY ∈ [y1, y2]
```

So:

```text
(closestX, closestY)
```

is guaranteed to be inside the rectangle.

---

## 3. Calculate horizontal and vertical distance

```java
long dx = xCenter - closestX;
long dy = yCenter - closestY;
```

These represent:

```text
horizontal distance
vertical distance
```

---

## 4. Calculate squared distance

```java
long distanceSquared = dx * dx + dy * dy;
```

By the Pythagorean theorem:

```text
distance² = dx² + dy²
```

---

## 5. Compare with radius

```java
return distanceSquared <= radiusSquared;
```

If the closest point is:

```text
inside circle
```

or:

```text
on circle boundary
```

then the shapes overlap.

---

# 🧠 Why We Use `<=` Instead of `<`

Suppose the closest point is exactly on the circle boundary:

```text
distance = radius
```

Then:

```text
distance² = radius²
```

The circle and rectangle share that boundary point.

The problem asks whether there is **any point** belonging to both shapes.

So touching counts as overlap.

Therefore:

```java
distanceSquared <= radiusSquared
```

is correct.

---

# 🧪 Dry Run — Example 1

Input:

```text
radius = 1
xCenter = 0
yCenter = 0

x1 = 1
y1 = -1
x2 = 3
y2 = 1
```

Rectangle:

```text
x ∈ [1,3]
y ∈ [-1,1]
```

---

## Step 1 — Closest X

```text
xCenter = 0
```

but:

```text
x1 = 1
```

So:

```text
closestX = 1
```

---

## Step 2 — Closest Y

```text
yCenter = 0
```

is already inside:

```text
[-1,1]
```

Therefore:

```text
closestY = 0
```

Closest point:

```text
(1,0)
```

---

## Step 3 — Distance

```text
dx = 0 - 1 = -1
dy = 0 - 0 = 0
```

Therefore:

```text
distance² = (-1)² + 0²
           = 1
```

Radius:

```text
r = 1
r² = 1
```

Compare:

```text
1 <= 1
```

So:

```text
true
```

The common point is:

```text
(1,0)
```

---

# 🧪 Dry Run — Example 2

Input:

```text
radius = 1
xCenter = 1
yCenter = 1

x1 = 1
y1 = -3
x2 = 2
y2 = -1
```

Rectangle:

```text
x ∈ [1,2]
y ∈ [-3,-1]
```

---

## Closest X

```text
xCenter = 1
```

Already inside:

```text
[1,2]
```

So:

```text
closestX = 1
```

---

## Closest Y

```text
yCenter = 1
```

is above:

```text
[-3,-1]
```

So:

```text
closestY = -1
```

---

## Distance

```text
dx = 1 - 1 = 0
dy = 1 - (-1) = 2
```

Therefore:

```text
distance² = 0² + 2²
           = 4
```

Radius squared:

```text
1² = 1
```

Compare:

```text
4 <= 1
```

False.

Therefore:

```text
false
```

---

# 🧪 Dry Run — Example 3

Input:

```text
radius = 1
xCenter = 0
yCenter = 0

x1 = -1
y1 = 0
x2 = 0
y2 = 1
```

Closest point:

```text
(0,0)
```

because the circle center itself lies inside the rectangle.

Therefore:

```text
distance² = 0
```

and:

```text
0 <= 1
```

So:

```text
true
```

---

# 🔥 Approach 2 — Distance From Center to Rectangle

We can implement the same geometry without explicitly constructing the closest point.

For the x-axis:

```text
if xCenter < x1:
    dx = x1 - xCenter

else if xCenter > x2:
    dx = xCenter - x2

else:
    dx = 0
```

Similarly for the y-axis:

```text
if yCenter < y1:
    dy = y1 - yCenter

else if yCenter > y2:
    dy = yCenter - y2

else:
    dy = 0
```

Then:

```text
dx² + dy² <= radius²
```

This is mathematically equivalent to clamping.

---

# 🔄 Approach 2 Diagram

```text
                 Circle Center
                       ●
                       |
                       | dy
                       |
              +--------+--------+
              |                 |
              |    Rectangle    |
              |                 |
              +-----------------+
                 <- dx ->
```

There can be distance in:

```text
x-direction
y-direction
```

or both can be zero.

---

# ✅ Java — Approach 2

```java
//Approach-2 (Direct Distance to Rectangle)
//T.C : O(1)
//S.C : O(1)

class Solution {

    public boolean checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        long dx = 0;
        long dy = 0;

        if (xCenter < x1) {
            dx = x1 - xCenter;
        } else if (xCenter > x2) {
            dx = xCenter - x2;
        }

        if (yCenter < y1) {
            dy = y1 - yCenter;
        } else if (yCenter > y2) {
            dy = yCenter - y2;
        }

        return dx * dx + dy * dy <= (long) radius * radius;
    }
}
```

---

# ✅ C++ — Approach 2

```cpp
//Approach-2 (Direct Distance to Rectangle)
//T.C : O(1)
//S.C : O(1)

class Solution {
public:

    bool checkOverlap(
        int radius,
        int xCenter,
        int yCenter,
        int x1,
        int y1,
        int x2,
        int y2
    ) {

        long long dx = 0;
        long long dy = 0;

        if (xCenter < x1) {
            dx = x1 - xCenter;
        } else if (xCenter > x2) {
            dx = xCenter - x2;
        }

        if (yCenter < y1) {
            dy = y1 - yCenter;
        } else if (yCenter > y2) {
            dy = yCenter - y2;
        }

        return dx * dx + dy * dy
            <= 1LL * radius * radius;
    }
};
```

---

# ⚖️ Approach Comparison

| Feature | Approach 1: Clamping | Approach 2: Direct Distance |
|---|---|---|
| Main idea | Find closest point in rectangle | Find x/y distance separately |
| Time | `O(1)` | `O(1)` |
| Space | `O(1)` | `O(1)` |
| Uses closest point | Yes | Implicitly |
| Readability | Very clean | Also clean |
| Geometry intuition | Excellent | Excellent |
| Floating point | No | No |
| Recommended | ✅ Yes | Good alternative |

---

# 🏆 Recommended Approach

Use:

```text
Closest Point + Clamping
```

because it expresses the geometric idea most directly:

```text
Find the rectangle point nearest to the circle center.
Then check whether that point is inside the circle.
```

This is easier to remember and generalizes well to other geometry problems.

---

# 📐 Complete Architecture Diagram

```text
                         +----------------------+
                         |       INPUT          |
                         +----------+-----------+
                                    |
                   +----------------+----------------+
                   |                                 |
                   v                                 v
          +----------------+                +----------------+
          |     CIRCLE     |                |   RECTANGLE    |
          |                |                |                |
          | (cx, cy, r)    |                | (x1,y1,x2,y2) |
          +-------+--------+                +--------+-------+
                  |                                  |
                  +----------------+-----------------+
                                   |
                                   v
                    +----------------------------+
                    | Find Closest Rectangle     |
                    | Point to Circle Center     |
                    +-------------+--------------+
                                  |
                         +--------+--------+
                         |                 |
                         v                 v
                     closestX          closestY
                         |                 |
                         +--------+--------+
                                  |
                                  v
                    +----------------------------+
                    | dx = cx - closestX        |
                    | dy = cy - closestY         |
                    +-------------+--------------+
                                  |
                                  v
                    +----------------------------+
                    | distance² = dx² + dy²     |
                    +-------------+--------------+
                                  |
                                  v
                    +----------------------------+
                    | distance² <= radius² ?     |
                    +-------------+--------------+
                                  |
                         +--------+--------+
                         |                 |
                        YES                NO
                         |                 |
                         v                 v
                      return             return
                       true              false
```

---

# 🔬 Mathematical Architecture

The geometry can also be viewed as a distance optimization problem.

```text
                  Rectangle
             +----------------+
             |                |
             |       P        |
             |       ●        |
             |                |
             +----------------+
                     ^
                     |
                     | minimum distance
                     |
                     ● C
                 Circle Center
```

We are effectively solving:

```text
Find P inside rectangle such that:

distance(C, P)

is minimum.
```

Then:

```text
minimumDistance <= radius
```

means intersection exists.

---

# 🧠 Important Geometry Pattern

This problem teaches a very reusable concept:

## Point-to-Axis-Aligned-Rectangle Distance

For one dimension, if a coordinate `c` must lie in:

```text
[L, R]
```

then the closest coordinate is:

```text
clamp(c, L, R)
```

Therefore:

```text
closestX = clamp(xCenter, x1, x2)
closestY = clamp(yCenter, y1, y2)
```

This same technique is useful in:

```text
Point vs Rectangle
Circle vs Rectangle
Closest Point Problems
Collision Detection
Bounding Box Problems
Computational Geometry
```

---

# ⚠️ Common Mistakes

## Mistake 1 — Checking Only Rectangle Corners

A circle can overlap the rectangle through an edge or an interior point without containing a rectangle corner.

Therefore checking corners alone is not sufficient.

---

## Mistake 2 — Checking Only Whether the Circle Center Is Inside

The circle can overlap the rectangle even when its center is outside.

Example:

```text
      Circle
       ****
     **    **
    *        *  +---------+
     **    **   | Rectangle
       ****     +---------+
```

The center can be outside while the circle still touches the rectangle.

---

## Mistake 3 — Forgetting the Case Where the Center Is Inside

If the circle center lies inside the rectangle:

```text
closestX = xCenter
closestY = yCenter
```

so:

```text
distance² = 0
```

and the answer is immediately true.

---

## Mistake 4 — Using Square Root

Avoid:

```java
Math.sqrt(dx * dx + dy * dy)
```

There is no need for it.

Compare squared values:

```text
dx² + dy² <= radius²
```

This is both simpler and safer.

---

## Mistake 5 — Using `<` Instead of `<=`

Touching at exactly one point counts as overlap.

Therefore:

```text
distance² <= radius²
```

not:

```text
distance² < radius²
```

---

# 🧪 Edge Cases

## 1. Circle Completely Inside Rectangle

```text
+-----------------------+
|                       |
|        *****          |
|       *  C  *         |
|        *****          |
|                       |
+-----------------------+
```

The closest point is the center itself.

Answer:

```text
true
```

---

## 2. Rectangle Completely Inside Circle

```text
          *********
       ***         ***
      **   +-----+   **
     **    |     |    **
      **   +-----+   **
       ***         ***
          *********
```

Answer:

```text
true
```

---

## 3. Circle Touches Rectangle at One Point

```text
       ***
      *   *
     *     *
      *   *
       ***
         ●────────────
         |
      rectangle
```

The distance is exactly the radius.

Therefore:

```text
true
```

---

## 4. Circle and Rectangle Are Separate

```text
  *****                 +-------+
 *     *                |       |
*   C   *               |       |
 *     *                |       |
  *****                 +-------+
```

Closest-point distance is greater than radius.

Therefore:

```text
false
```

---

# ⏱️ Complexity Analysis

We perform only a constant number of arithmetic operations.

Therefore:

```text
Time Complexity:
O(1)
```

and:

```text
Space Complexity:
O(1)
```

---

# ✅ Correctness Proof

We prove that the closest-point method is correct.

### Lemma 1

The point:

```text
(closestX, closestY)
```

is the closest point in the rectangle to the circle center.

For the x-coordinate:

```text
closestX = clamp(xCenter, x1, x2)
```

is the closest valid x-coordinate in the interval `[x1, x2]`.

Similarly:

```text
closestY = clamp(yCenter, y1, y2)
```

is the closest valid y-coordinate in `[y1, y2]`.

Combining these two independent minimum-distance coordinates gives the closest point in the rectangle.

---

### Lemma 2

If:

```text
distance(center, closestPoint) > radius
```

then no rectangle point lies inside the circle.

The closest rectangle point is already farther than the circle radius.

Every other rectangle point is at least as far away.

Therefore no intersection exists.

---

### Lemma 3

If:

```text
distance(center, closestPoint) <= radius
```

then the closest point belongs to the circle and rectangle.

Therefore the two shapes share at least one point.

So they overlap.

---

### Conclusion

The condition:

```text
distance² <= radius²
```

is true exactly when the circle and rectangle overlap.

Therefore the algorithm is correct.

---

# 🎯 Interview Explanation

A concise interview explanation:

> Because the rectangle is axis-aligned, I can find the point inside the rectangle that is closest to the circle center independently on the x and y axes using clamping. If `xCenter` is outside `[x1,x2]`, I move it to the nearest boundary; otherwise I keep it unchanged. I do the same for y. Then I calculate the squared Euclidean distance between the circle center and this closest point. If that squared distance is less than or equal to `radius²`, the circle and rectangle overlap. This takes O(1) time and O(1) space.

---

# 📝 Quick Formula Sheet

```text
closestX = max(x1, min(xCenter, x2))

closestY = max(y1, min(yCenter, y2))

dx = xCenter - closestX
dy = yCenter - closestY

distance² = dx² + dy²

overlap iff:

distance² <= radius²
```

---

# 🔄 Complete Algorithm

```text
1. Read circle center and radius.
2. Read rectangle boundaries.
3. Clamp xCenter into [x1, x2].
4. Clamp yCenter into [y1, y2].
5. This gives the closest rectangle point.
6. Compute squared distance to the circle center.
7. Compare distance² with radius².
8. Return true if distance² <= radius².
```

---

# 🏷️ Tags

```text
Math
Geometry
Coordinate Geometry
Circle
Rectangle
Distance
Clamping
Computational Geometry
```

---

# 🏆 Recommended GitHub Title

```text
1401. Circle and Rectangle Overlapping | Geometry | Math | Java & C++
```

---

# 📁 Recommended Folder Structure

```text
C:\Leetcode_Solutions
│
└── 1400-1499
    │
    └── 1401 Circle and Rectangle Overlapping
        │
        ├── README.md
        ├── Solution.java
        └── Solution.cpp
```

---

# 💻 VS Code Folder Creation

```powershell
cd C:\Leetcode_Solutions

cd "1400-1499"

mkdir "1401 Circle and Rectangle Overlapping"

cd "1401 Circle and Rectangle Overlapping"

ni README.md
ni Solution.java
ni Solution.cpp
```

---

# 🚀 Git Commands

```powershell
cd C:\Leetcode_Solutions

git status

git add "1400-1499/1401 Circle and Rectangle Overlapping"

git status

git commit -m "Add Geometry solution for LeetCode 1401"
git pull origin main --rebase
git push origin main
```

---

# 📌 Repository Structure

```text
Leetcode_Solutions/
│
├── 1400-1499/
│   │
│   └── 1401 Circle and Rectangle Overlapping/
│       ├── README.md
│       ├── Solution.java
│       └── Solution.cpp
│
└── ...
```

---

# ⭐ Final Takeaway

The entire problem reduces to one simple geometric idea:

```text
Circle + Rectangle
        |
        v
Find closest point of rectangle
 to circle center
        |
        v
Calculate squared distance
        |
        v
Compare with radius²
        |
   +----+----+
   |         |
  <=         >
   |         |
 true      false
```

The key code is:

```java
int closestX = Math.max(
    x1,
    Math.min(xCenter, x2)
);

int closestY = Math.max(
    y1,
    Math.min(yCenter, y2)
);

long dx = xCenter - closestX;
long dy = yCenter - closestY;

return dx * dx + dy * dy <= (long) radius * radius;
```

The reusable pattern is:

```text
POINT → CLAMP → CLOSEST POINT → SQUARED DISTANCE → COMPARE
```
