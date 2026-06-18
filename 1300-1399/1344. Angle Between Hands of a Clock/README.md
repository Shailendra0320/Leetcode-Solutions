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

# 1344. Angle Between Hands of a Clock

# Intuition

A clock contains:

```text
Hour Hand

Minute Hand
```

We need to calculate:

```text
Smallest angle
```

between these two hands.

---

# Clock Facts

Complete Circle:

```text
360°
```

---

Minute Hand

```text
360 / 60

=

6° per minute
```

---

Hour Hand

```text
360 / 12

=

30° per hour
```

But:

```text
Hour hand also moves continuously
```

For every minute:

```text
30 / 60

=

0.5°
```

---

# Formula

## Minute Hand

```text
Minute Angle

=

minutes × 6
```

---

## Hour Hand

```text
Hour Angle

=

hour × 30

+

minutes × 0.5
```

---

## Difference

```text
|hourAngle - minuteAngle|
```

---

## Smallest Angle

```text
min(
difference,
360 - difference
)
```

---

# Approach

## Step 1

Calculate minute hand angle.

```text
minutes × 6
```

---

## Step 2

Calculate hour hand angle.

```text
hour × 30

+

minutes × 0.5
```

---

## Step 3

Take absolute difference.

---

## Step 4

Return:

```text
min(
difference,
360-difference
)
```

---

# Flowchart

```text
Start

   │
   ▼

Read Hour

Read Minutes

   │
   ▼

Minute Angle

minutes × 6

   │
   ▼

Hour Angle

hour × 30
+
minutes × 0.5

   │
   ▼

Difference

|hour-minute|

   │
   ▼

Minimum

min(
diff,
360-diff
)

   │
   ▼

Return Answer
```

---

# Visualization

Input

```text
hour = 3

minutes = 15
```

---

Minute Hand

```text
15 × 6

=

90°
```

---

Hour Hand

```text
3 × 30

+

15 × 0.5

=

97.5°
```

---

Difference

```text
|97.5 - 90|

=

7.5°
```

Answer

```text
7.5°
```

---

# Detailed Dry Run

Input

```text
hour = 12

minutes = 30
```

---

Minute Angle

```text
30 × 6

=

180°
```

---

Hour Angle

```text
12 × 30

+

30 × 0.5

=

375°
```

Clock equivalent:

```text
15°
```

---

Difference

```text
|15 - 180|

=

165°
```

---

Answer

```text
165°
```

---

# Clock Diagram

```text
           12

      11         1

   10               2

   9        ●        3

    8              4

      7         5

           6
```

Example:

```text
3:15
```

Minute Hand:

```text
Points to 3
```

Hour Hand:

```text
Slightly ahead of 3
```

Angle:

```text
7.5°
```

---

# Memory Visualization

```text
hour = 3

minutes = 15


Minute Angle

90°


Hour Angle

97.5°


Difference

7.5°


Answer

7.5°
```

---

# Why This Works

Minute hand moves:

```text
6°
```

per minute.

Hour hand moves:

```text
30°
```

per hour and:

```text
0.5°
```

per minute.

Using these exact movements gives the precise angle.

---

# Complexity Analysis

## Time Complexity

```text
O(1)
```

Only mathematical calculations.

---

## Space Complexity

```text
O(1)
```

No extra data structure.

---

# Java Solution

```java
class Solution {

    public double angleClock(int hour, int minutes) {

        double minuteAngle = 6.0 * minutes;

        double hourAngle =
            30.0 * hour + 0.5 * minutes;

        double angle =
            Math.abs(hourAngle - minuteAngle);

        return Math.min(
            angle,
            360.0 - angle
        );
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    double angleClock(
        int hour,
        int minutes
    ) {

        double minuteAngle =
            6.0 * minutes;

        double hourAngle =
            30.0 * hour +
            0.5 * minutes;

        double angle =
            abs(hourAngle - minuteAngle);

        return min(
            angle,
            360.0 - angle
        );
    }
};
```