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

# 3633. Earliest Finish Time for Land and Water Rides

# Intuition

We must complete:

```text
One Land Ride
+
One Water Ride
```

The rides can be taken in any order:

```text
Land → Water
```

or

```text
Water → Land
```

We need the earliest possible finishing time.

---

# Key Observation

For:

```text
Land → Water
```

The only thing that matters is:

```text
Earliest possible completion of any land ride
```

Similarly for:

```text
Water → Land
```

Only the earliest completion of any water ride matters.

Therefore:

```text
Find minimum land finish time
Find minimum water finish time
```

Then evaluate both ride orders.

---

# Approach

## Case 1

Take:

```text
Land → Water
```

Find:

```text
quickestLand
```

Then for every water ride:

```text
finish =
max(quickestLand, waterStart)
+
waterDuration
```

---

## Case 2

Take:

```text
Water → Land
```

Find:

```text
quickestWater
```

Then for every land ride:

```text
finish =
max(quickestWater, landStart)
+
landDuration
```

---

## Answer

```text
Minimum finish time
among all possibilities
```

---

# Flow Diagram

```text
Find Earliest Land Finish

            ↓

Try Every Water Ride

            ↓

Calculate Finish Time

            ↓

Store Minimum

────────────────────

Find Earliest Water Finish

            ↓

Try Every Land Ride

            ↓

Calculate Finish Time

            ↓

Store Minimum

            ↓

Return Answer
```

---

# Example

Input

```text
landStart    = [2,8]
landDuration = [4,1]

waterStart    = [3,10]
waterDuration = [2,3]
```

---

# Step 1

Earliest Land Finish

```text
Ride 1 = 2 + 4 = 6
Ride 2 = 8 + 1 = 9

quickestLand = 6
```

---

# Step 2

Try Water Rides

```text
Water 1

max(6,3)+2
= 8
```

```text
Water 2

max(6,10)+3
= 13
```

Best:

```text
8
```

---

# Step 3

Earliest Water Finish

```text
3 + 2 = 5
10 + 3 = 13

quickestWater = 5
```

---

# Step 4

Try Land Rides

```text
Land 1

max(5,2)+4
= 9
```

```text
Land 2

max(5,8)+1
= 9
```

Best:

```text
9
```

---

# Final Answer

```text
min(8,9)
=
8
```

---

# Complexity Analysis

## Time Complexity

```text
O(n + m)
```

where

```text
n = land rides
m = water rides
```

---

## Space Complexity

```text
O(1)
```

Only variables are used.

---

# Java Solution

```java
class Solution {
    public int earliestFinishTime(
        int[] landStart,
        int[] landDuration,
        int[] waterStart,
        int[] waterDuration) {

        int result = Integer.MAX_VALUE;

        int quickestLand = Integer.MAX_VALUE;

        for (int idx = 0; idx < landStart.length; idx++) {

            quickestLand = Math.min(
                quickestLand,
                landStart[idx] + landDuration[idx]
            );
        }

        for (int idx = 0; idx < waterStart.length; idx++) {

            int totalFinish =
                Math.max(quickestLand, waterStart[idx])
                + waterDuration[idx];

            result = Math.min(result, totalFinish);
        }

        int quickestWater = Integer.MAX_VALUE;

        for (int idx = 0; idx < waterStart.length; idx++) {

            quickestWater = Math.min(
                quickestWater,
                waterStart[idx] + waterDuration[idx]
            );
        }

        for (int idx = 0; idx < landStart.length; idx++) {

            int totalFinish =
                Math.max(quickestWater, landStart[idx])
                + landDuration[idx];

            result = Math.min(result, totalFinish);
        }

        return result;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int earliestFinishTime(
        vector<int>& landStart,
        vector<int>& landDuration,
        vector<int>& waterStart,
        vector<int>& waterDuration) {

        int result = INT_MAX;

        int quickestLand = INT_MAX;

        for (int idx = 0; idx < landStart.size(); idx++) {

            quickestLand = min(
                quickestLand,
                landStart[idx] + landDuration[idx]
            );
        }

        for (int idx = 0; idx < waterStart.size(); idx++) {

            int totalFinish =
                max(quickestLand, waterStart[idx])
                + waterDuration[idx];

            result = min(result, totalFinish);
        }

        int quickestWater = INT_MAX;

        for (int idx = 0; idx < waterStart.size(); idx++) {

            quickestWater = min(
                quickestWater,
                waterStart[idx] + waterDuration[idx]
            );
        }

        for (int idx = 0; idx < landStart.size(); idx++) {

            int totalFinish =
                max(quickestWater, landStart[idx])
                + landDuration[idx];

            result = min(result, totalFinish);
        }

        return result;
    }
};
```