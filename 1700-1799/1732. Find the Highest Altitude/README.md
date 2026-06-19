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

# 1732. Find the Highest Altitude

# Intuition

A biker starts at:

```text
Altitude = 0
```

Each element in the gain array represents:

```text
Change in altitude
```

We need to find:

```text
Maximum altitude reached
```

during the entire trip.

---

# Approaches

1. Running Prefix Sum (Optimal)
2. Prefix Sum Array

---

# Approach 1 — Running Prefix Sum

## Idea

Instead of storing every altitude:

```text
Keep track of:

Current Altitude

and

Maximum Altitude
```

Update altitude while traversing.

---

# Flowchart

```text
Start

   │
   ▼

Current Altitude = 0

Maximum Altitude = 0

   │
   ▼

Traverse gain[]

   │
   ▼

current += gain[i]

   │
   ▼

maxAltitude

=

max(
maxAltitude,
current
)

   │
   ▼

Continue

   │
   ▼

Return maxAltitude
```

---

# Visualization

Input

```text
gain =

[-5,1,5,0,-7]
```

---

Starting Altitude

```text
0
```

---

Move 1

```text
0 + (-5)

= -5
```

Highest:

```text
0
```

---

Move 2

```text
-5 + 1

= -4
```

Highest:

```text
0
```

---

Move 3

```text
-4 + 5

= 1
```

Highest:

```text
1
```

---

Move 4

```text
1 + 0

= 1
```

Highest:

```text
1
```

---

Move 5

```text
1 + (-7)

= -6
```

Highest:

```text
1
```

Answer:

```text
1
```

---

# Altitude Diagram

```text
Altitude

  1 ──────────────●─────●
                  │     │
  0 ●─────────────┘     │
                        │
 -1                     │
 -2                     │
 -3                     │
 -4           ●─────────┘
 -5      ●
 -6                         ●

      Start
```

---

# Detailed Dry Run

Input

```text
gain = [-5,1,5,0,-7]
```

---

Initial

```text
currentAltitude = 0

maximumAltitude = 0
```

---

After -5

```text
currentAltitude = -5

maximumAltitude = 0
```

---

After +1

```text
currentAltitude = -4

maximumAltitude = 0
```

---

After +5

```text
currentAltitude = 1

maximumAltitude = 1
```

---

After 0

```text
currentAltitude = 1

maximumAltitude = 1
```

---

After -7

```text
currentAltitude = -6

maximumAltitude = 1
```

Answer:

```text
1
```

---

# Memory Visualization

```text
gain

[-5,1,5,0,-7]

      │
      ▼

Current Altitude

0

↓

-5

↓

-4

↓

1

↓

1

↓

-6


Maximum Altitude

0

↓

0

↓

0

↓

1

↓

1

↓

1
```

---

# Approach 2 — Prefix Sum Array

## Idea

Create:

```text
altitude[]
```

Store every altitude.

Example:

```text
gain

[-5,1,5,0,-7]
```

Altitude Array:

```text
[0,-5,-4,1,1,-6]
```

Maximum:

```text
1
```

---

# Prefix Sum Diagram

```text
gain

[-5,1,5,0,-7]

      │

      ▼

Altitude

[0,-5,-4,1,1,-6]
```

---

# Comparison

| Approach | Time | Space |
|-----------|--------|--------|
| Running Prefix Sum | O(n) | O(1) |
| Prefix Sum Array | O(n) | O(n) |

---

# Why This Works

Each gain value modifies the altitude.

Therefore:

```text
Altitude

=

Prefix Sum of gain[]
```

The highest altitude is simply:

```text
Maximum Prefix Sum
```

encountered during traversal.

---

# Complexity Analysis

## Approach 1

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

## Approach 2

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

---

# Java Solution 1

```java
class Solution {

    public int largestAltitude(int[] gain) {

        int currentAltitude = 0;

        int maximumAltitude = 0;

        for (int change : gain) {

            currentAltitude += change;

            maximumAltitude =
                Math.max(
                    maximumAltitude,
                    currentAltitude
                );
        }

        return maximumAltitude;
    }
}
```

---

# Java Solution 2

```java
class Solution {

    public int largestAltitude(int[] gain) {

        int n = gain.length;

        int[] altitude = new int[n + 1];

        int maximumAltitude = 0;

        for (int i = 0; i < n; i++) {

            altitude[i + 1] =
                altitude[i] + gain[i];

            maximumAltitude =
                Math.max(
                    maximumAltitude,
                    altitude[i + 1]
                );
        }

        return maximumAltitude;
    }
}
```

---

# C++ Solution 1

```cpp
class Solution {
public:

    int largestAltitude(vector<int>& gain) {

        int currentAltitude = 0;

        int maximumAltitude = 0;

        for (int change : gain) {

            currentAltitude += change;

            maximumAltitude =
                max(
                    maximumAltitude,
                    currentAltitude
                );
        }

        return maximumAltitude;
    }
};
```

---

# C++ Solution 2

```cpp
class Solution {
public:

    int largestAltitude(vector<int>& gain) {

        int n = gain.size();

        vector<int> altitude(n + 1, 0);

        int maximumAltitude = 0;

        for (int i = 0; i < n; i++) {

            altitude[i + 1] =
                altitude[i] + gain[i];

            maximumAltitude =
                max(
                    maximumAltitude,
                    altitude[i + 1]
                );
        }

        return maximumAltitude;
    }
};
```