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

# 3635. Earliest Finish Time for Land and Water Attractions II

# Intuition

We must complete:

```text
One Land Attraction
+
One Water Attraction
```

Order can be:

```text
Land → Water
```

or

```text
Water → Land
```

We need the minimum possible finishing time.

---

# Key Observation

For:

```text
Land → Water
```

Only the earliest land completion matters.

For:

```text
Water → Land
```

Only the earliest water completion matters.

So:

```text
Find earliest land finish.

Find earliest water finish.

Evaluate both orders.
```

---

# Approach 1 - Greedy

## Step 1

Find:

```text
bestLandEnd
=
minimum(landStart + landDuration)
```

---

## Step 2

Try every water attraction.

```text
finish =
max(bestLandEnd, waterStart)
+
waterDuration
```

---

## Step 3

Find:

```text
bestWaterEnd
=
minimum(waterStart + waterDuration)
```

---

## Step 4

Try every land attraction.

```text
finish =
max(bestWaterEnd, landStart)
+
landDuration
```

---

## Step 5

Return minimum finish time.

---

# Flow Diagram

```text
Find Earliest Land Finish

            ↓

Try Every Water Ride

            ↓

Update Answer

────────────────────

Find Earliest Water Finish

            ↓

Try Every Land Ride

            ↓

Update Answer

            ↓

Return Minimum
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

## Earliest Land Finish

```text
2 + 4 = 6
8 + 1 = 9

bestLandEnd = 6
```

---

## Land → Water

```text
max(6,3)+2 = 8

max(6,10)+3 = 13
```

Best:

```text
8
```

---

## Earliest Water Finish

```text
3 + 2 = 5
10 + 3 = 13

bestWaterEnd = 5
```

---

## Water → Land

```text
max(5,2)+4 = 9

max(5,8)+1 = 9
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
n = land attractions
m = water attractions
```

---

## Space Complexity

```text
O(1)
```

---

# Java Solution

```java
//Approach-1 (Greedy)
//T.C : O(n + m)
//S.C : O(1)

class Solution {

    public int earliestFinishTime(
            int[] landStartTime,
            int[] landDuration,
            int[] waterStartTime,
            int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        int bestLandEnd = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {

            bestLandEnd = Math.min(
                bestLandEnd,
                landStartTime[i] + landDuration[i]
            );
        }

        for (int j = 0; j < waterStartTime.length; j++) {

            ans = Math.min(
                ans,
                Math.max(bestLandEnd, waterStartTime[j])
                + waterDuration[j]
            );
        }

        int bestWaterEnd = Integer.MAX_VALUE;

        for (int j = 0; j < waterStartTime.length; j++) {

            bestWaterEnd = Math.min(
                bestWaterEnd,
                waterStartTime[j] + waterDuration[j]
            );
        }

        for (int i = 0; i < landStartTime.length; i++) {

            ans = Math.min(
                ans,
                Math.max(bestWaterEnd, landStartTime[i])
                + landDuration[i]
            );
        }

        return ans;
    }
}


/*
//Approach-2 (Equivalent Greedy Form)
//T.C : O(n + m)
//S.C : O(1)

class Solution {

    public int earliestFinishTime(
            int[] landStartTime,
            int[] landDuration,
            int[] waterStartTime,
            int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        int earliestLandFinishTime = Integer.MAX_VALUE;
        int earliestWaterFinishTime = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {

            earliestLandFinishTime = Math.min(
                earliestLandFinishTime,
                landStartTime[i] + landDuration[i]
            );
        }

        for (int i = 0; i < waterStartTime.length; i++) {

            if (waterStartTime[i] >= earliestLandFinishTime) {

                ans = Math.min(
                    ans,
                    waterStartTime[i] + waterDuration[i]
                );
            }
            else {

                ans = Math.min(
                    ans,
                    earliestLandFinishTime + waterDuration[i]
                );
            }
        }

        for (int i = 0; i < waterStartTime.length; i++) {

            earliestWaterFinishTime = Math.min(
                earliestWaterFinishTime,
                waterStartTime[i] + waterDuration[i]
            );
        }

        for (int i = 0; i < landStartTime.length; i++) {

            if (landStartTime[i] >= earliestWaterFinishTime) {

                ans = Math.min(
                    ans,
                    landStartTime[i] + landDuration[i]
                );
            }
            else {

                ans = Math.min(
                    ans,
                    earliestWaterFinishTime + landDuration[i]
                );
            }
        }

        return ans;
    }
}
*/
```

---

# C++ Solution

```cpp
//Approach-1 (Greedy)
//T.C : O(n + m)
//S.C : O(1)

class Solution {
public:

    int earliestFinishTime(
        vector<int>& landStartTime,
        vector<int>& landDuration,
        vector<int>& waterStartTime,
        vector<int>& waterDuration) {

        int ans = INT_MAX;

        int bestLandEnd = INT_MAX;

        for (int i = 0; i < landStartTime.size(); i++) {

            bestLandEnd = min(
                bestLandEnd,
                landStartTime[i] + landDuration[i]
            );
        }

        for (int j = 0; j < waterStartTime.size(); j++) {

            ans = min(
                ans,
                max(bestLandEnd, waterStartTime[j])
                + waterDuration[j]
            );
        }

        int bestWaterEnd = INT_MAX;

        for (int j = 0; j < waterStartTime.size(); j++) {

            bestWaterEnd = min(
                bestWaterEnd,
                waterStartTime[j] + waterDuration[j]
            );
        }

        for (int i = 0; i < landStartTime.size(); i++) {

            ans = min(
                ans,
                max(bestWaterEnd, landStartTime[i])
                + landDuration[i]
            );
        }

        return ans;
    }
};


/*
//Approach-2 (Equivalent Greedy Form)
//T.C : O(n + m)
//S.C : O(1)

class Solution {
public:

    int earliestFinishTime(
        vector<int>& landStartTime,
        vector<int>& landDuration,
        vector<int>& waterStartTime,
        vector<int>& waterDuration) {

        int ans = INT_MAX;

        int earliestLandFinishTime = INT_MAX;
        int earliestWaterFinishTime = INT_MAX;

        for (int i = 0; i < landStartTime.size(); i++) {

            earliestLandFinishTime = min(
                earliestLandFinishTime,
                landStartTime[i] + landDuration[i]
            );
        }

        for (int i = 0; i < waterStartTime.size(); i++) {

            if (waterStartTime[i] >= earliestLandFinishTime) {

                ans = min(
                    ans,
                    waterStartTime[i] + waterDuration[i]
                );
            }
            else {

                ans = min(
                    ans,
                    earliestLandFinishTime + waterDuration[i]
                );
            }
        }

        for (int i = 0; i < waterStartTime.size(); i++) {

            earliestWaterFinishTime = min(
                earliestWaterFinishTime,
                waterStartTime[i] + waterDuration[i]
            );
        }

        for (int i = 0; i < landStartTime.size(); i++) {

            if (landStartTime[i] >= earliestWaterFinishTime) {

                ans = min(
                    ans,
                    landStartTime[i] + landDuration[i]
                );
            }
            else {

                ans = min(
                    ans,
                    earliestWaterFinishTime + landDuration[i]
                );
            }
        }

        return ans;
    }
};
*/
```
