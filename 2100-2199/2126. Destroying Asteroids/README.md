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

# 2126. Destroying Asteroids

# Intuition

We are given:

- Initial planet mass
- Array of asteroid masses

Rule:

```text
If planet mass >= asteroid mass
```

Then:

```text
Planet destroys asteroid
Planet mass += asteroid mass
```

Otherwise:

```text
Planet is destroyed
```

We must determine whether all asteroids can be destroyed.

---

# Key Observation

To maximize growth:

```text
Destroy smaller asteroids first
```

Because:

- Smaller asteroids are easier to absorb
- Each absorbed asteroid increases mass
- Larger asteroids become easier later

Therefore:

```text
Sort asteroids in ascending order
```

---

# Greedy Approach

## Step 1

Sort asteroids.

```text
Smallest → Largest
```

---

## Step 2

Maintain:

```text
currentMass
```

Initially:

```text
currentMass = mass
```

---

## Step 3

Traverse sorted asteroids.

If:

```text
currentMass < asteroid
```

Return:

```text
false
```

Otherwise:

```text
currentMass += asteroid
```

---

# Flow Diagram

```text
Sort asteroids

        ↓

Take smallest asteroid

        ↓

currentMass >= asteroid ?

        ↓

NO → return false

YES

        ↓

currentMass += asteroid

        ↓

Continue

        ↓

All destroyed ?

        ↓

return true
```

---

# Example

Input:

```text
mass = 10

asteroids = [3,9,19,5,21]
```

After sorting:

```text
[3,5,9,19,21]
```

---

### Step 1

```text
10 >= 3

Mass = 13
```

---

### Step 2

```text
13 >= 5

Mass = 18
```

---

### Step 3

```text
18 >= 9

Mass = 27
```

---

### Step 4

```text
27 >= 19

Mass = 46
```

---

### Step 5

```text
46 >= 21

Mass = 67
```

All destroyed ✅

Answer:

```text
true
```

---

# Dry Run

Input:

```text
mass = 5

asteroids = [4,9,23]
```

Sorted:

```text
[4,9,23]
```

---

Destroy 4

```text
Mass = 9
```

Destroy 9

```text
Mass = 18
```

Next:

```text
18 < 23
```

Cannot destroy.

Answer:

```text
false
```

---

# Complexity Analysis

## Time Complexity

Sorting:

```text
O(n log n)
```

Traversal:

```text
O(n)
```

Total:

```text
O(n log n)
```

---

## Space Complexity

```text
O(1)
```

Ignoring sorting space.

---

# Java Solution

```java
class Solution {

    public boolean asteroidsDestroyed(int mass, int[] asteroids) {

        Arrays.sort(asteroids);

        long currentMass = mass;

        for (int asteroid : asteroids) {

            if (currentMass < asteroid) {

                return false;
            }

            currentMass += asteroid;
        }

        return true;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    bool asteroidsDestroyed(int mass, vector<int>& asteroids) {

        sort(asteroids.begin(), asteroids.end());

        long long currentMass = mass;

        for (int asteroid : asteroids) {

            if (currentMass < asteroid) {

                return false;
            }

            currentMass += asteroid;
        }

        return true;
    }
};
```
