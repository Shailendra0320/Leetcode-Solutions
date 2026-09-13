# 835. Image Overlap

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320  
**LeetCode (Main):** [https://leetcode.com/u/Shailu03/](https://leetcode.com/u/Shailu03/)  
**LeetCode (Alternate):** [https://leetcode.com/u/ShailendraLeetcode03](https://leetcode.com/u/ShailendraLeetcode03)

---

## 🧩 Problem Statement

You are given two square binary matrices `img1` and `img2` of size `n x n`.

Each cell contains either:

- `1` → an active/filled pixel
- `0` → an empty pixel

You may translate one image horizontally and/or vertically.

After shifting, the **overlap** is the number of positions where:

```text
img1[i][j] == 1
AND
img2[i][j] == 1
```

Return the maximum possible overlap after choosing the best translation.

### Link

[LeetCode 835 — Image Overlap](https://leetcode.com/problems/image-overlap/)

---

# 💡 What Is the Question Really Asking?

Think of every `1` in the matrix as a point.

We want to shift one set of points until as many points as possible land on top of points in the other image.

So the problem can be viewed as:

> **Find the translation vector that makes the largest number of `1`-pixels coincide.**

Instead of repeatedly thinking about complete matrices, we can focus on the coordinates of the `1`s.

---

# 🔍 Core Observation

Suppose:

```text
A contains a 1 at (r1, c1)
B contains a 1 at (r2, c2)
```

For these two pixels to overlap after translating `A`, the required translation is:

```text
dr = r2 - r1
dc = c2 - c1
```

Therefore every pair of `1`s suggests one possible translation.

If the **same translation vector** appears many times, it means many pairs of pixels can overlap under that shift.

```text
Most frequent translation vector
                ↓
Maximum number of overlapping 1s
```

This turns the problem into a frequency-counting problem on displacement vectors.

---

# 📐 Visual Intuition

Suppose:

```text
Image A:

1 0 0
0 1 0
0 0 0
```

Coordinates of `1`s:

```text
(0,0)
(1,1)
```

And:

```text
Image B:

0 1 0
0 0 0
0 1 0
```

Coordinates:

```text
(0,1)
(2,1)
```

For example:

```text
A(0,0) → B(0,1)
translation = (0,1)
```

The required translation is determined entirely by the difference between the two coordinates.

---

# 🧠 Approach 1 — Direct Translation / Simulation

The most straightforward solution is to try every possible shift.

A translation can move one image:

```text
up / down
left / right
```

There are:

```text
2n - 1
```

possible row displacements and:

```text
2n - 1
```

possible column displacements.

So there are:

```text
O(n²)
```

possible translations.

For every translation, we may inspect all:

```text
O(n²)
```

cells.

Therefore:

```text
O(n²) × O(n²)
= O(n⁴)
```

---

## 🔄 Approach 1 Flow

```text
                Start
                  |
                  v
       Try every row shift
                  |
                  v
      Try every column shift
                  |
                  v
       Compare overlapping
              cells
                  |
                  v
        Count overlapping 1s
                  |
                  v
       Update maximum answer
                  |
                  v
                 End
```

---

## ✅ Approach 1 — Java

```java
//Approach-1 (Brute Force Translation / Simulation)
//T.C : O(n^4)
//S.C : O(1)

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;
        int answer = 0;

        // Try every possible row displacement
        for (int dr = -(n - 1); dr <= n - 1; dr++) {

            // Try every possible column displacement
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {

                        int nr = r + dr;
                        int nc = c + dc;

                        if (nr < 0 || nr >= n ||
                            nc < 0 || nc >= n) {
                            continue;
                        }

                        if (img1[r][c] == 1 && img2[nr][nc] == 1) {
                            overlap++;
                        }
                    }
                }

                answer = Math.max(answer, overlap);
            }
        }

        return answer;
    }
}
```

---

## ✅ Approach 1 — C++

```cpp
//Approach-1 (Brute Force Translation / Simulation)
//T.C : O(n^4)
//S.C : O(1)

class Solution {
public:
    int largestOverlap(vector<vector<int>>& img1,
                       vector<vector<int>>& img2) {

        int n = img1.size();
        int answer = 0;

        // Try every possible row displacement
        for (int dr = -(n - 1); dr <= n - 1; dr++) {

            // Try every possible column displacement
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {

                        int nr = r + dr;
                        int nc = c + dc;

                        if (nr < 0 || nr >= n ||
                            nc < 0 || nc >= n) {
                            continue;
                        }

                        if (img1[r][c] == 1 &&
                            img2[nr][nc] == 1) {
                            overlap++;
                        }
                    }
                }

                answer = max(answer, overlap);
            }
        }

        return answer;
    }
};
```

---

# 🚀 Approach 2 — Coordinates + Frequency Map

The direct simulation scans the complete matrix for every translation.

But zero-cells do not matter.

Only the coordinates containing `1` can participate in an overlap.

Let:

```text
A = coordinates of 1s in img1
B = coordinates of 1s in img2
```

For every:

```text
a ∈ A
b ∈ B
```

calculate:

```text
dr = b.row - a.row
dc = b.col - a.col
```

This pair tells us:

> "If we translate `img1` by `(dr, dc)`, these two pixels overlap."

Store every displacement vector in a frequency map.

Example:

```text
Shift (0, 1) → 4 times
Shift (1, 0) → 2 times
Shift (-1, 2) → 1 time
```

Then:

```text
answer = 4
```

---

# 🎯 Why Does This Work?

Fix one translation:

```text
(dr, dc)
```

Every pair:

```text
img1(r1, c1)
img2(r2, c2)
```

satisfying:

```text
r2 - r1 = dr
c2 - c1 = dc
```

overlaps under exactly that translation.

Therefore:

```text
frequency(dr, dc)
=
number of overlapping 1-pairs
```

So the largest frequency is the maximum overlap.

---

# 📊 Example

Suppose:

```text
A = {(0,0), (1,1), (2,2)}

B = {(0,1), (1,2), (2,3)}
```

Some displacement vectors are:

```text
A(0,0) → B(0,1) = (0,1)
A(0,0) → B(1,2) = (1,2)

A(1,1) → B(0,1) = (-1,0)
A(1,1) → B(1,2) = (0,1)

A(2,2) → B(1,2) = (-1,0)
A(2,2) → B(2,3) = (0,1)
```

Frequencies include:

```text
(0,1)  → 3
(-1,0) → 2
(1,2)  → 1
```

Therefore:

```text
maximum overlap = 3
```

---

## ✅ Approach 2 — Java

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Approach-2 (Coordinates + Frequency Map)
//T.C : O(n^2 + k1 * k2)
//S.C : O(k1 * k2)

class Solution2 {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Store coordinates of 1s from img1
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones1.add(new int[]{r, c});
                }
            }
        }

        // Store coordinates of 1s from img2
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }

        Map<String, Integer> frequency = new HashMap<>();

        int answer = 0;

        // Try every pair of 1s
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {

                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];

                String key = dr + "," + dc;

                int count = frequency.getOrDefault(key, 0) + 1;

                frequency.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}
```

---

## ✅ Approach 2 — C++

```cpp
//Approach-2 (Coordinates + Frequency Map)
//T.C : O(n^2 + k1 * k2)
//S.C : O(k1 * k2)

class Solution2 {
public:
    int largestOverlap(vector<vector<int>>& img1,
                       vector<vector<int>>& img2) {

        int n = img1.size();

        vector<pair<int, int>> ones1;
        vector<pair<int, int>> ones2;

        // Store coordinates of 1s from img1
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones1.push_back({r, c});
                }
            }
        }

        // Store coordinates of 1s from img2
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img2[r][c] == 1) {
                    ones2.push_back({r, c});
                }
            }
        }

        map<pair<int, int>, int> frequency;

        int answer = 0;

        // Try every pair of 1s
        for (auto& p1 : ones1) {
            for (auto& p2 : ones2) {

                int dr = p2.first - p1.first;
                int dc = p2.second - p1.second;

                int count = ++frequency[{dr, dc}];

                answer = max(answer, count);
            }
        }

        return answer;
    }
};
```

---

# 📈 Complexity Comparison

Let:

```text
n  = matrix dimension
k1 = number of 1s in img1
k2 = number of 1s in img2
```

| Approach                    |   Time Complexity | Space Complexity |
| --------------------------- | ----------------: | ---------------: |
| Brute Force Translation     |           `O(n⁴)` |           `O(1)` |
| Coordinates + Frequency Map | `O(n² + k1 × k2)` |     `O(k1 × k2)` |

Since:

```text
k1 ≤ n²
k2 ≤ n²
```

the coordinate solution can still reach `O(n⁴)` in the theoretical worst case, but it is often much faster when the matrices are sparse.

---

# 🧪 Dry Run

Consider:

```text
img1 =

1 0 0
0 1 0
0 0 0
```

and:

```text
img2 =

0 1 0
0 0 0
0 1 0
```

Coordinates:

```text
img1:
(0,0), (1,1)

img2:
(0,1), (2,1)
```

Generate displacement vectors:

```text
(0,0) → (0,1) = (0,1)
(0,0) → (2,1) = (2,1)
(1,1) → (0,1) = (-1,0)
(1,1) → (2,1) = (1,0)
```

Every shift appears once.

Therefore:

```text
maximum overlap = 1
```

---

# 🧠 Correctness Proof

### Step 1 — Every overlap has a displacement

If:

```text
(r1, c1)
```

from `img1` overlaps with:

```text
(r2, c2)
```

from `img2`, then the translation must be:

```text
(r2 - r1, c2 - c1)
```

So every actual overlap contributes to the frequency of its translation vector.

### Step 2 — Same displacement means same translation

If multiple pairs generate the same:

```text
(dr, dc)
```

then all of those pairs are aligned by the same translation.

Therefore all those overlaps can happen simultaneously.

### Step 3 — Frequency equals overlap count

For any fixed translation:

```text
frequency(dr, dc)
```

is exactly the number of `1`-pixels that overlap.

### Step 4 — Pick the largest frequency

The best translation is the one with the most overlapping pixels.

Thus:

```text
max frequency
=
maximum possible overlap
```

Hence the algorithm is correct.

---

# ⚠️ Common Mistakes

### 1. Only checking the original position

Checking:

```java
img1[i][j] == 1 && img2[i][j] == 1
```

only measures the overlap when there is no translation.

The problem allows arbitrary horizontal and vertical shifts.

### 2. Forgetting negative shifts

The image can move:

```text
up
left
```

as well as:

```text
down
right
```

So displacement values may be negative.

### 3. Mixing row and column differences

Use:

```text
dr = r2 - r1
dc = c2 - c1
```

not the other way around.

### 4. Ignoring duplicate displacement vectors

Multiple pixel pairs producing the same vector is exactly what we need to count.

---

# 🎯 Interview Thought Process

```text
What do I need to maximize?
        ↓
Number of overlapping 1s
        ↓
Can I try every translation?
        ↓
Yes → O(n⁴)
        ↓
Can I ignore zero cells?
        ↓
Yes, only 1s matter
        ↓
Represent 1s using coordinates
        ↓
A pair of coordinates determines a translation
        ↓
Count equal translations
        ↓
Largest frequency = answer
```

---

# 🧩 Pattern Recognition

This problem is a classic example of:

```text
Coordinate Transformation
        +
Displacement Vector
        +
Frequency Counting
```

Whenever a problem says:

> Move/translate one set of points and maximize matching positions.

Think about:

```text
difference between coordinates
```

For:

```text
P1 = (x1, y1)
P2 = (x2, y2)
```

the translation vector is:

```text
(x2 - x1, y2 - y1)
```

Equal vectors correspond to equal translations.

---

# 🔥 Mental Model

Instead of:

```text
Try a shift
    ↓
Scan everything
    ↓
Count
```

reverse the perspective:

```text
Pick two 1-pixels
       ↓
Ask which shift aligns them
       ↓
Record that shift
       ↓
Repeat
       ↓
Most common shift wins
```

This change in viewpoint is the main trick.

---

# 📌 Important Formula

For:

```text
(r1, c1) ∈ img1
(r2, c2) ∈ img2
```

the required translation is:

```text
Δrow = r2 - r1
Δcol = c2 - c1
```

Therefore:

```text
answer
=
maximum frequency of (Δrow, Δcol)
```

---

# 🏆 Final Summary

LeetCode 835 may initially look like a matrix simulation problem.

The deeper insight is that only the `1`s matter.

Represent every `1` as a coordinate.

For every pair of `1`s from the two images:

```text
translation = target coordinate - source coordinate
```

Count how often each translation appears.

The translation occurring most frequently produces the maximum overlap.

```text
Coordinates
     ↓
Displacement vectors
     ↓
Frequency map
     ↓
Maximum frequency
     ↓
Maximum overlap
```

---

# 💎 One-Line Insight

> **Each pair of `1`-pixels defines a translation; the translation occurring most frequently gives the maximum image overlap.**

---

## 🏷️ Tags

`Array, Matrix, Hash Table, Geometry, Coordinate Geometry, Simulation, Counting, Frequency Map, Mathematical Optimization, LeetCode, Medium`

---

## 📁 Recommended Folder Structure

```text
C:\Leetcode_Solutions
│
└── 0800-0899
    │
    └── 835-Image-Overlap
        │
        ├── README.md
        ├── Solution.java
        └── Solution.cpp
```

---

## 📌 GitHub Note

Both approaches are included in the source files for learning and comparison.

On LeetCode, submit one `Solution` class at a time.
