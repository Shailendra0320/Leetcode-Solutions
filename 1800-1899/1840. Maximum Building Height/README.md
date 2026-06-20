# 1840. Maximum Building Height

## Profiles

### GitHub

⭐ GitHub Repository:
https://github.com/Shailendra0320

---

### LeetCode Profiles

🔥 Main Profile:
https://leetcode.com/u/ShailendraLeetcode03/

🚀 Alternate Profile:
https://leetcode.com/u/Shailu03/

---

# Tags

```text
Greedy
Sorting
Math
Array
```

---

# Intuition

We have:

```text
n buildings
```

Building 1 must have:

```text
height = 0
```

and

```text
|height[i] - height[i+1]| <= 1
```

Some buildings have restrictions:

```text
[id, maxHeight]
```

We must find the highest possible building.

---

# Approach 1 (Peak Formula)

## Idea

Add:

```text
(1,0)

(n,n-1)
```

Sort restrictions.

Perform:

```text
Forward Pass
```

and

```text
Backward Pass
```

to make all restrictions valid.

Then calculate peak:

```text
Peak =
(distance + h1 + h2)/2
```

---

# Visualization

Example

```text
n = 5

restrictions:

[2,1]
[4,1]
```

After adding:

```text
(1,0)

(2,1)

(4,1)

(5,4)
```

Forward pass:

```text
(5,4)

↓

(5,2)
```

Final:

```text
(1,0)

(2,1)

(4,1)

(5,2)
```

---

# Mountain Diagram

```text
Height

2          *
          / \
1      * /   \ *
      /         \
0  *-------------*

   1  2  3  4  5
```

Maximum height:

```text
2
```

---

# Approach 2 (Alternative Formula)

Same Forward Pass and Backward Pass.

Peak formula:

```text
Peak

=

max(h1,h2)

+

(distance-|h1-h2|)/2
```

Both approaches produce the same answer.

---

# Complexity

## Time Complexity

```text
O(m log m)
```

Sorting dominates.

---

## Space Complexity

```text
O(m)
```

---

# Java Solution 1

```java
class Solution {

    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        for (int[] r : restrictions) {
            list.add(r);
        }

        list.add(new int[]{1, 0});
        list.add(new int[]{n, n - 1});

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int size = list.size();

        for (int i = 1; i < size; i++) {

            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);

            curr[1] = Math.min(
                curr[1],
                prev[1] + (curr[0] - prev[0])
            );
        }

        for (int i = size - 2; i >= 0; i--) {

            int[] curr = list.get(i);
            int[] next = list.get(i + 1);

            curr[1] = Math.min(
                curr[1],
                next[1] + (next[0] - curr[0])
            );
        }

        int maxHeight = 0;

        for (int i = 1; i < size; i++) {

            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);

            int distance = curr[0] - prev[0];

            int peak =
                (distance + prev[1] + curr[1]) / 2;

            maxHeight = Math.max(maxHeight, peak);
        }

        return maxHeight;
    }
}
```

---

# Java Solution 2

```java
class Solution {

    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list=new ArrayList<>();

        list.add(new int[]{1,0});

        for(int[] restriction:restrictions){
            list.add(restriction);
        }

        Collections.sort(list,(a,b)->a[0]-b[0]);

        if(list.get(list.size()-1)[0]!=n){
            list.add(new int[]{n,n-1});
        }

        for(int i=1;i<list.size();i++){

            list.get(i)[1]=Math.min(
                list.get(i)[1],
                list.get(i-1)[1]
                +
                list.get(i)[0]
                -
                list.get(i-1)[0]
            );
        }

        for(int i=list.size()-2;i>=0;i--){

            list.get(i)[1]=Math.min(
                list.get(i)[1],
                list.get(i+1)[1]
                +
                list.get(i+1)[0]
                -
                list.get(i)[0]
            );
        }

        int result=0;

        for(int i=1;i<list.size();i++){

            int h1=list.get(i-1)[1];
            int h2=list.get(i)[1];

            int x=list.get(i-1)[0];
            int y=list.get(i)[0];

            result=Math.max(
                result,
                Math.max(h1,h2)
                +
                (
                    y-x-Math.abs(h1-h2)
                )/2
            );
        }

        return result;
    }
}
```

---

# C++ Solution 1

```cpp
class Solution {
public:

    int maxBuilding(
        int n,
        vector<vector<int>>& restrictions
    ) {

        vector<vector<int>> list;

        for(auto &r : restrictions){
            list.push_back(r);
        }

        list.push_back({1,0});
        list.push_back({n,n-1});

        sort(list.begin(), list.end());

        int size = list.size();

        for(int i=1;i<size;i++){

            list[i][1] =
                min(
                    list[i][1],
                    list[i-1][1]
                    +
                    list[i][0]
                    -
                    list[i-1][0]
                );
        }

        for(int i=size-2;i>=0;i--){

            list[i][1] =
                min(
                    list[i][1],
                    list[i+1][1]
                    +
                    list[i+1][0]
                    -
                    list[i][0]
                );
        }

        int answer = 0;

        for(int i=1;i<size;i++){

            int distance =
                list[i][0]
                -
                list[i-1][0];

            int peak =
                (
                    distance
                    +
                    list[i][1]
                    +
                    list[i-1][1]
                ) / 2;

            answer =
                max(
                    answer,
                    peak
                );
        }

        return answer;
    }
};
```

---

# C++ Solution 2

```cpp
class Solution {
public:

    int maxBuilding(
        int n,
        vector<vector<int>>& restrictions
    ) {

        vector<vector<int>> list;

        list.push_back({1,0});

        for(auto &restriction : restrictions){
            list.push_back(restriction);
        }

        sort(list.begin(), list.end());

        if(list.back()[0] != n){
            list.push_back({n,n-1});
        }

        for(int i=1;i<list.size();i++){

            list[i][1] =
                min(
                    list[i][1],
                    list[i-1][1]
                    +
                    list[i][0]
                    -
                    list[i-1][0]
                );
        }

        for(int i=list.size()-2;i>=0;i--){

            list[i][1] =
                min(
                    list[i][1],
                    list[i+1][1]
                    +
                    list[i+1][0]
                    -
                    list[i][0]
                );
        }

        int answer = 0;

        for(int i=1;i<list.size();i++){

            int h1 = list[i-1][1];
            int h2 = list[i][1];

            int x = list[i-1][0];
            int y = list[i][0];

            answer =
                max(
                    answer,
                    max(h1,h2)
                    +
                    (
                        y-x-
                        abs(h1-h2)
                    )/2
                );
        }

        return answer;
    }
};
```
