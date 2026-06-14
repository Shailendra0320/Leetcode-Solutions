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

# 2130. Maximum Twin Sum of a Linked List

# Intuition

For a linked list:

```text
a0 → a1 → a2 → ... → an
```

Twin nodes are:

```text
First  + Last
Second + Second Last
Third  + Third Last
```

Example:

```text
1 → 2 → 3 → 4
```

Twin pairs:

```text
1 + 4 = 5
2 + 3 = 5
```

Answer:

```text
5
```

---

# Key Observation

Accessing the last node repeatedly in a linked list is expensive.

Instead:

```text
Find Middle
        ↓
Reverse Second Half
        ↓
Compare Both Halves
```

This allows:

```text
O(n)
```

time complexity.

---

# Approach

## Step 1

Find the middle node using:

```text
Slow Pointer
Fast Pointer
```

---

## Step 2

Reverse the second half of the linked list.

---

## Step 3

Traverse:

```text
First Half
Second Half
```

simultaneously.

---

## Step 4

Calculate:

```text
left.val + right.val
```

and keep the maximum.

---

# Flowchart

```text
Start

   │
   ▼

Find Middle Node

   │
   ▼

Reverse Second Half

   │
   ▼

Traverse Both Halves

   │
   ▼

Compute Twin Sum

   │
   ▼

Update Maximum

   │
   ▼

Return Answer
```

---

# Visualization

Input

```text
1 → 2 → 3 → 4
```

---

Find Middle

```text
slow = 3
```

---

Reverse Second Half

```text
3 → 4

becomes

4 → 3
```

---

Now

```text
Left Side

1 → 2

Right Side

4 → 3
```

---

Twin Sums

```text
1 + 4 = 5

2 + 3 = 5
```

Answer:

```text
5
```

---

# Detailed Dry Run

Input

```text
5 → 4 → 2 → 1
```

---

## Find Middle

```text
slow = 2
```

---

## Reverse

Before

```text
2 → 1
```

After

```text
1 → 2
```

---

## Compare

Pair 1

```text
5 + 1 = 6
```

Maximum

```text
6
```

---

Pair 2

```text
4 + 2 = 6
```

Maximum

```text
6
```

---

Final Answer

```text
6
```

---

# Linked List Transformation

Before Reverse

```text
5 → 4 → 2 → 1
          ↑
        slow
```

---

After Reverse

```text
First Half

5 → 4

Second Half

1 → 2
```

---

Comparison

```text
5 + 1

4 + 2
```

---

# Memory Visualization

```text
Head

5 → 4 → 2 → 1

      slow
             fast

         │
         ▼

Reverse

2 → 1

         ▼

1 → 2

         │
         ▼

Compare

5 ↔ 1

4 ↔ 2
```

---

# Why This Works

The reversed second half aligns:

```text
First Node
with
Last Node

Second Node
with
Second Last Node
```

making twin sum computation straightforward.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Finding middle:

```text
O(n)
```

Reversing:

```text
O(n)
```

Comparing:

```text
O(n)
```

Total:

```text
O(n)
```

---

## Space Complexity

```text
O(1)
```

No extra data structures are used.

---

# Java Solution

```java
class Solution {

    public int pairSum(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {

            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;

            curr = next;
        }

        int maxTwinSum = 0;

        ListNode left = head;
        ListNode right = prev;

        while (right != null) {

            maxTwinSum =
                Math.max(
                    maxTwinSum,
                    left.val + right.val
                );

            left = left.next;
            right = right.next;
        }

        return maxTwinSum;
    }
}
```

---

# C++ Solution

```cpp
class Solution {
public:

    int pairSum(ListNode* head) {

        ListNode* slow = head;
        ListNode* fast = head;

        while (fast != nullptr && fast->next != nullptr) {

            slow = slow->next;
            fast = fast->next->next;
        }

        ListNode* prev = nullptr;
        ListNode* curr = slow;

        while (curr != nullptr) {

            ListNode* nextNode = curr->next;

            curr->next = prev;

            prev = curr;

            curr = nextNode;
        }

        int maximumTwinSum = 0;

        ListNode* left = head;
        ListNode* right = prev;

        while (right != nullptr) {

            maximumTwinSum =
                max(
                    maximumTwinSum,
                    left->val + right->val
                );

            left = left->next;
            right = right->next;
        }

        return maximumTwinSum;
    }
};
```
