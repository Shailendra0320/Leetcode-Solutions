# 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points

## 🔗 Problem Link

**LeetCode:** https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/

---

## 👨‍💻 Profiles

**GitHub:** https://github.com/Shailendra0320

**LeetCode (Main):** https://leetcode.com/u/Shailu03/

**LeetCode (Alternate):** https://leetcode.com/u/ShailendraLeetcode03/

---

# 📌 Problem Statement

You are given the head of a singly linked list.

A node is called a **critical point** if it is either:

- a local maximum, or
- a local minimum.

A node is a **local maximum** when its value is strictly greater than both its previous and next nodes.

A node is a **local minimum** when its value is strictly smaller than both its previous and next nodes.

A node can be a critical point only when both a previous node and a next node exist.

Therefore:

```text
First node  → never a critical point
Last node   → never a critical point
```

For all distinct pairs of critical points, define their distance as the difference between their positions in the linked list.

Return:

```text
[minDistance, maxDistance]
```

where:

- `minDistance` is the minimum distance between any two critical points.
- `maxDistance` is the maximum distance between any two critical points.

If there are fewer than two critical points, return:

```text
[-1, -1]
```

---

# 🧾 Constraints

```text
2 <= number of nodes <= 10^5

1 <= Node.val <= 10^5
```

Because the linked list can contain up to `10^5` nodes, an `O(n^2)` solution is unnecessary and inefficient.

We can solve the problem in:

```text
O(n)
```

time.

---

# 💡 Intuition

The first thing to notice is that we do **not** care about the actual distance between every possible pair of nodes.

We only care about the positions of the **critical points**.

Suppose the critical points are located at:

```text
p1 < p2 < p3 < p4
```

The distances between consecutive critical points are:

```text
p2 - p1
p3 - p2
p4 - p3
```

The minimum distance must be one of these consecutive gaps.

Why?

For example:

```text
p3 - p1
=
(p2 - p1) + (p3 - p2)
```

Since both terms are positive:

```text
p3 - p1 > p2 - p1
p3 - p1 > p3 - p2
```

So a non-consecutive pair can never produce a smaller distance than all consecutive pairs.

For the maximum distance, we only need:

```text
lastCritical - firstCritical
```

because the farthest two critical points are always the first and last ones.

Therefore, while traversing the linked list, we only need to maintain:

```text
firstCritical
previousCritical
lastCritical
minDistance
```

This gives an optimal constant-space solution.

---

# 🔍 What Is a Critical Point?

For three consecutive nodes:

```text
previous → current → next
```

the current node is critical if:

### Local Maximum

```text
current > previous
AND
current > next
```

Example:

```text
3 → 7 → 5
```

Since:

```text
7 > 3
7 > 5
```

`7` is a local maximum.

---

### Local Minimum

```text
current < previous
AND
current < next
```

Example:

```text
7 → 3 → 5
```

Since:

```text
3 < 7
3 < 5
```

`3` is a local minimum.

---

# 🚫 Why the First and Last Nodes Are Not Critical

A critical point requires:

```text
previous + current + next
```

The first node has no previous node.

The last node has no next node.

Therefore:

```text
First node  → Not critical
Last node   → Not critical
```

This is why our traversal checks only nodes that have both neighbors.

---

# 🌳 Critical Point Visualization

Consider:

```text
5 → 3 → 1 → 2 → 5 → 1 → 2
        ↑             ↑   ↑
       Min           Max Min
```

Using 1-based positions:

```text
1  2  3  4  5  6  7
```

Critical points are:

```text
3, 5, 6
```

Distances:

```text
5 - 3 = 2
6 - 5 = 1
```

Therefore:

```text
minDistance = 1
```

And the maximum distance is:

```text
6 - 3 = 3
```

So:

```text
[1, 3]
```

---

# 🔑 Key Observation 1 — Minimum Distance

Suppose the critical positions are:

```text
[2, 5, 7, 11]
```

Consecutive distances:

```text
5 - 2  = 3
7 - 5  = 2
11 - 7 = 4
```

Therefore:

```text
minDistance = 2
```

Now consider non-consecutive pairs:

```text
7 - 2  = 5
11 - 5 = 6
11 - 2 = 9
```

None can be smaller than the smallest consecutive gap.

So:

```text
minimum distance
=
minimum consecutive critical-point gap
```

---

# 🔑 Key Observation 2 — Maximum Distance

For:

```text
critical points = [2, 5, 7, 11]
```

the farthest pair is:

```text
11 - 2 = 9
```

Any internal pair is smaller.

Therefore:

```text
maxDistance
=
lastCritical - firstCritical
```

---

# 🚀 Approach 1 — One Pass + Constant Extra Space

This is the **best approach**.

Instead of storing all critical-point positions, we process them as they appear.

For every critical point:

### First critical point

Store:

```text
firstCritical = currentIndex
previousCritical = currentIndex
lastCritical = currentIndex
```

### Every later critical point

Calculate:

```text
currentIndex - previousCritical
```

and update:

```text
minDistance
```

Then move:

```text
previousCritical = currentIndex
lastCritical = currentIndex
```

At the end:

```text
maxDistance =
lastCritical - firstCritical
```

---

# 🌳 Approach 1 Flowchart

```text
                         Start
                           |
                           v
                    Traverse list
                           |
                           v
             Read previous/current/next
                           |
                           v
                Is current critical?
                    /           \
                  No             Yes
                  |               |
                  v               v
              Continue       First critical?
                                /        \
                              Yes         No
                               |           |
                               v           v
                         Store first    Calculate gap
                         position       from previous
                                           |
                                           v
                                   Update minDistance
                                           |
                                           v
                                   Update previous
                                           |
                                           v
                                      Continue
                                           |
                                           v
                              Fewer than 2 critical?
                                  /          \
                                Yes           No
                                 |             |
                                 v             v
                           Return [-1,-1]   last - first
                                               |
                                               v
                                            Return
```

---

# 📝 Approach 1 Algorithm

```text
1. Set:
       prev = head
       curr = head.next

2. Maintain a zero-based index.

3. While curr has a next node:
       next = curr.next

4. Check whether curr is:
       curr > prev && curr > next
       OR
       curr < prev && curr < next

5. If curr is critical:
       If this is the first critical point:
           save its position.

       Otherwise:
           calculate the distance from
           the previous critical point.
           update minimum distance.

       update previousCritical
       update lastCritical

6. Move the three pointers forward.

7. If fewer than two critical points exist:
       return [-1, -1]

8. Otherwise:
       maxDistance = lastCritical - firstCritical

9. Return [minDistance, maxDistance]
```

---

# 📖 Example 1

```text
Input:

head = [5,3,1,2,5,1,2]
```

Using 1-based positions:

```text
Position:  1  2  3  4  5  6  7
Value:     5  3  1  2  5  1  2
```

Critical points:

```text
position 3 → value 1 → local minimum
position 5 → value 5 → local maximum
position 6 → value 1 → local minimum
```

Consecutive distances:

```text
5 - 3 = 2
6 - 5 = 1
```

So:

```text
minDistance = 1
```

Maximum:

```text
6 - 3 = 3
```

Answer:

```text
[1, 3]
```

---

# 📖 Example 2

```text
Input:

head = [3,1]
```

There are only two nodes.

The first node has no previous node.

The second node has no next node.

Therefore, there are no critical points.

Answer:

```text
[-1, -1]
```

---

# 📖 Example 3

```text
Input:

head = [1,3,2,2,3,2,2,2,7]
```

Critical points:

```text
position 2 → value 3 → local maximum
position 5 → value 3 → local maximum
```

Distance:

```text
5 - 2 = 3
```

Thus:

```text
minDistance = 3
maxDistance = 3
```

Answer:

```text
[3, 3]
```

The last node is not critical because it has no next node.

---

# 🔄 Detailed Dry Run

Consider:

```text
head = [5,3,1,2,5,1,2]
```

Use zero-based indices:

```text
index:  0  1  2  3  4  5  6
value:  5  3  1  2  5  1  2
```

---

## Index 1

```text
previous = 5
current  = 3
next     = 1
```

Check:

```text
3 > 5 → false
3 < 5 → true
3 < 1 → false
```

Not critical.

---

## Index 2

```text
previous = 3
current  = 1
next     = 2
```

Check:

```text
1 < 3
1 < 2
```

So index `2` is critical.

This is the first critical point:

```text
firstCritical = 2
previousCritical = 2
```

---

## Index 3

```text
previous = 1
current  = 2
next     = 5
```

Not critical.

---

## Index 4

```text
previous = 2
current  = 5
next     = 1
```

Check:

```text
5 > 2
5 > 1
```

Critical.

Distance from previous critical point:

```text
4 - 2 = 2
```

Update:

```text
minDistance = 2
previousCritical = 4
lastCritical = 4
```

---

## Index 5

```text
previous = 5
current  = 1
next     = 2
```

Check:

```text
1 < 5
1 < 2
```

Critical.

Distance:

```text
5 - 4 = 1
```

Update:

```text
minDistance = 1
previousCritical = 5
lastCritical = 5
```

---

## Final Maximum Distance

First critical point:

```text
2
```

Last critical point:

```text
5
```

Therefore:

```text
maxDistance = 5 - 2 = 3
```

Final result:

```text
[1, 3]
```

---

# 🧠 Why We Only Need the Previous Critical Point

Suppose critical points appear at:

```text
2, 6, 8, 13
```

When we reach `8`, the only new distance that can potentially become the minimum is:

```text
8 - 6 = 2
```

We do not need:

```text
8 - 2
```

because it is larger than:

```text
6 - 2
```

and cannot improve the minimum.

Therefore, one previous critical position is enough.

---

# 🧠 Why the First Critical Point Is Needed

For the maximum distance, we need:

```text
lastCritical - firstCritical
```

So we must preserve the very first critical point discovered.

This means the complete constant-space state is:

```text
firstCritical
previousCritical
lastCritical
minDistance
```

---

# 🥈 Approach 2 — Store All Critical Positions

A simpler-to-visualize alternative is to store every critical-point position in an array/list.

For example:

```text
critical = [2,4,5]
```

Then:

### Minimum

Calculate consecutive differences:

```text
4 - 2 = 2
5 - 4 = 1
```

So:

```text
minDistance = 1
```

### Maximum

Use:

```text
5 - 2 = 3
```

So:

```text
maxDistance = 3
```

This approach is straightforward, but it uses `O(n)` extra memory.

---

# 🌳 Approach 2 Flow

```text
                  Traverse Linked List
                           |
                           v
                  Detect Critical Points
                           |
                           v
                    Store Positions
                           |
                           v
                At least 2 critical points?
                       /          \
                     No            Yes
                     |              |
                     v              v
                Return [-1,-1]   Calculate
                                  consecutive
                                  differences
                                      |
                                      v
                              Calculate first/last
                                      |
                                      v
                                   Return
```

---

# 📊 Approach Comparison

| Approach    | Technique                 |   Time    |    Space     | Recommendation |
| :---------- | :------------------------ | :-------: | :----------: | :------------: |
| Approach 1  | One Pass + Track Previous | **O(n)**  |   **O(1)**   |  ⭐⭐⭐ Best   |
| Approach 2  | Store Critical Positions  | **O(n)**  |   **O(n)**   | ⭐ Alternative |
| Brute Force | Compare Every Pair        | **O(n²)** | O(n) or O(1) |       ❌       |

---

# 💻 Java — Approach 1

```java
//Approach-1 (One Pass + Constant Space)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int[] nodesBetweenCriticalPoints(
        ListNode head
    ) {

        int[] answer =
            {-1, -1};

        if (
            head == null ||
            head.next == null ||
            head.next.next == null
        ) {
            return answer;
        }

        ListNode prev =
            head;

        ListNode curr =
            head.next;

        int index = 1;

        int firstCritical =
            -1;

        int previousCritical =
            -1;

        int lastCritical =
            -1;

        int minDistance =
            Integer.MAX_VALUE;

        while (
            curr.next != null
        ) {

            ListNode next =
                curr.next;

            boolean isCritical =
                (
                    curr.val > prev.val &&
                    curr.val > next.val
                ) ||
                (
                    curr.val < prev.val &&
                    curr.val < next.val
                );

            if (isCritical) {

                if (
                    firstCritical == -1
                ) {

                    firstCritical =
                        index;

                } else {

                    minDistance =
                        Math.min(
                            minDistance,
                            index -
                            previousCritical
                        );
                }

                previousCritical =
                    index;

                lastCritical =
                    index;
            }

            prev =
                curr;

            curr =
                next;

            index++;
        }

        if (
            firstCritical == -1 ||
            firstCritical == lastCritical
        ) {
            return answer;
        }

        answer[0] =
            minDistance;

        answer[1] =
            lastCritical -
            firstCritical;

        return answer;
    }
}
```

---

# 💻 C++ — Approach 1

```cpp
//Approach-1 (One Pass + Constant Space)
//T.C : O(n)
//S.C : O(1)

class Solution {
public:

    vector<int> nodesBetweenCriticalPoints(
        ListNode* head
    ) {

        vector<int> answer = {
            -1,
            -1
        };

        if (
            head == nullptr ||
            head->next == nullptr ||
            head->next->next == nullptr
        ) {
            return answer;
        }

        ListNode* prev =
            head;

        ListNode* curr =
            head->next;

        int index = 1;

        int firstCritical =
            -1;

        int previousCritical =
            -1;

        int lastCritical =
            -1;

        int minDistance =
            INT_MAX;

        while (
            curr->next != nullptr
        ) {

            ListNode* next =
                curr->next;

            bool isCritical =
                (
                    curr->val > prev->val &&
                    curr->val > next->val
                ) ||
                (
                    curr->val < prev->val &&
                    curr->val < next->val
                );

            if (isCritical) {

                if (
                    firstCritical == -1
                ) {

                    firstCritical =
                        index;

                } else {

                    minDistance =
                        min(
                            minDistance,
                            index -
                            previousCritical
                        );
                }

                previousCritical =
                    index;

                lastCritical =
                    index;
            }

            prev =
                curr;

            curr =
                next;

            index++;
        }

        if (
            firstCritical == -1 ||
            firstCritical == lastCritical
        ) {
            return answer;
        }

        answer[0] =
            minDistance;

        answer[1] =
            lastCritical -
            firstCritical;

        return answer;
    }
};
```

---

# 💻 Java — Approach 2

```java
//Approach-2 (Store Critical Positions)
//T.C : O(n)
//S.C : O(n)

import java.util.*;

class Solution {

    public int[] nodesBetweenCriticalPoints(
        ListNode head
    ) {

        List<Integer> critical =
            new ArrayList<>();

        ListNode prev =
            head;

        ListNode curr =
            head.next;

        int index = 1;

        while (
            curr.next != null
        ) {

            if (
                (
                    curr.val > prev.val &&
                    curr.val > curr.next.val
                ) ||
                (
                    curr.val < prev.val &&
                    curr.val < curr.next.val
                )
            ) {

                critical.add(
                    index
                );
            }

            prev =
                curr;

            curr =
                curr.next;

            index++;
        }

        if (
            critical.size() < 2
        ) {

            return new int[]{
                -1,
                -1
            };
        }

        int minDistance =
            Integer.MAX_VALUE;

        for (
            int i = 1;
            i < critical.size();
            i++
        ) {

            minDistance =
                Math.min(
                    minDistance,
                    critical.get(i) -
                    critical.get(i - 1)
                );
        }

        int maxDistance =
            critical.get(
                critical.size() - 1
            ) -
            critical.get(0);

        return new int[]{
            minDistance,
            maxDistance
        };
    }
}
```

---

# 💻 C++ — Approach 2

```cpp
//Approach-2 (Store Critical Positions)
//T.C : O(n)
//S.C : O(n)

class Solution {
public:

    vector<int> nodesBetweenCriticalPoints(
        ListNode* head
    ) {

        vector<int> critical;

        ListNode* prev =
            head;

        ListNode* curr =
            head->next;

        int index = 1;

        while (
            curr->next != nullptr
        ) {

            if (
                (
                    curr->val > prev->val &&
                    curr->val > curr->next->val
                ) ||
                (
                    curr->val < prev->val &&
                    curr->val < curr->next->val
                )
            ) {

                critical.push_back(
                    index
                );
            }

            prev =
                curr;

            curr =
                curr->next;

            index++;
        }

        if (
            critical.size() < 2
        ) {

            return {
                -1,
                -1
            };
        }

        int minDistance =
            INT_MAX;

        for (
            int i = 1;
            i < critical.size();
            i++
        ) {

            minDistance =
                min(
                    minDistance,
                    critical[i] -
                    critical[i - 1]
                );
        }

        int maxDistance =
            critical.back() -
            critical.front();

        return {
            minDistance,
            maxDistance
        };
    }
};
```

---

# 🔬 Critical Point Detection

For:

```text
previous = P
current  = C
next     = N
```

the node is critical when:

```text
(C > P && C > N)
```

or:

```text
(C < P && C < N)
```

Combined:

```text
(C > P && C > N)
||
(C < P && C < N)
```

The comparisons are strict.

---

# ⚠️ Equal Values

Equal neighboring values do not create a critical point.

For example:

```text
1 → 3 → 3
```

The middle `3` is not a local maximum because:

```text
3 > 3
```

is false.

Similarly:

```text
3 → 1 → 1
```

does not make the middle `1` a local minimum.

---

# 🔄 Example With Equal Values

```text
1 → 3 → 2 → 2 → 3 → 2 → 2 → 2 → 7
```

Critical points:

```text
3 at position 2
3 at position 5
```

The repeated `2`s do not create additional critical points.

Distance:

```text
5 - 2 = 3
```

So:

```text
[3, 3]
```

---

# 🧠 Why No Sorting Is Required

This is a linked-list traversal problem.

Sorting the values would destroy the original positions.

The distances are based on where critical points appear in the linked list, so we should preserve the traversal order.

A single pass gives exactly the information we need.

---

# 🏆 Why the One-Pass Solution Is Optimal

The list can contain up to:

```text
10^5
```

nodes.

Approach 1:

```text
- Visits every node exactly once.
- Checks each node using its two neighbors.
- Stores only a constant number of variables.
- Does not modify the list.
- Does not store every critical position.
```

Therefore:

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

This is asymptotically optimal because every node must be inspected at least once to know whether it is a critical point.

---

# 📊 Complexity Comparison

| Approach                    | Time Complexity | Space Complexity |
| :-------------------------- | :-------------: | :--------------: |
| One Pass                    |    **O(n)**     |     **O(1)**     |
| Store Critical Positions    |    **O(n)**     |     **O(n)**     |
| Brute Force Pair Comparison |    **O(n²)**    |   O(1) / O(n)    |

---

# 🎯 Key Takeaways

- ✅ A critical point is a strict local minimum or maximum.
- ✅ The first and last nodes can never be critical points.
- ✅ Check every middle node using its previous and next nodes.
- ✅ Minimum distance only requires consecutive critical points.
- ✅ Maximum distance only requires the first and last critical points.
- ✅ No sorting is required.
- ✅ No nested loops are required.
- ✅ No need to store all critical positions in the optimal solution.
- ✅ A single traversal is sufficient.
- ✅ Time Complexity: **O(n)**.
- ✅ Space Complexity: **O(1)**.

---

# 🏁 Final Summary

The problem becomes simple once we realize that we do not need to compare every pair of critical points.

During one traversal:

```text
Detect Critical Point
        ↓
First Critical?
   /          \
 Yes           No
  |             |
  v             v
Store First   Compare With Previous
Position       Critical Point
                  |
                  v
             Update Minimum
                  |
                  v
            Update Last Point
```

At the end:

```text
minimum distance =
minimum gap between consecutive critical points
```

and:

```text
maximum distance =
last critical position - first critical position
```

Therefore, the optimal solution is:

```text
Time Complexity  : O(n)

Space Complexity : O(1)
```

---

# 🏷️ Tags

`Linked List` `Two Pointers` `Traversal` `Greedy` `Local Maximum` `Local Minimum` `Array` `Simulation`
