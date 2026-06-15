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

# 2095. Delete the Middle Node of a Linked List

# Intuition

Given a linked list:

```text
1 → 3 → 4 → 7 → 1 → 2 → 6
```

We need to delete:

```text
Middle Node
```

The middle node index is:

```text
⌊ n / 2 ⌋
```

For:

```text
n = 7
```

Middle:

```text
Index = 3
Value = 7
```

Result:

```text
1 → 3 → 4 → 1 → 2 → 6
```

---

# Approaches

1. Fast & Slow Pointer
2. Length Counting

---

# Approach 1 — Fast & Slow Pointer

## Idea

Use:

```text
Slow Pointer
```

moves:

```text
1 step
```

Use:

```text
Fast Pointer
```

moves:

```text
2 steps
```

When fast reaches the end:

```text
Slow reaches middle
```

---

# Visualization

```text
Initial

1 → 3 → 4 → 7 → 1 → 2 → 6

S
F


Step 1

1 → 3 → 4 → 7 → 1 → 2 → 6

    S
        F


Step 2

1 → 3 → 4 → 7 → 1 → 2 → 6

        S
                F


Step 3

1 → 3 → 4 → 7 → 1 → 2 → 6

            S
                      F(end)
```

Middle Node:

```text
7
```

Delete:

```text
1 → 3 → 4 → 1 → 2 → 6
```

---

# Detailed Dry Run

Input

```text
1 → 3 → 4 → 7 → 1 → 2 → 6
```

---

Initialize

```text
slow = head

fast = head.next.next
```

---

Iteration 1

```text
slow = 3

fast = 7
```

---

Iteration 2

```text
slow = 4

fast = 2
```

---

Loop Ends

```text
slow = 4
```

Node to delete:

```text
slow.next = 7
```

---

Delete

```text
slow.next = slow.next.next
```

Becomes:

```text
1 → 3 → 4 → 1 → 2 → 6
```

---

# Memory Diagram

```text
Before

Head

 │
 ▼

1 → 3 → 4 → 7 → 1 → 2 → 6
        ↑
       slow


Delete

slow.next

        │
        ▼

1 → 3 → 4 ─────→ 1 → 2 → 6
```

---

# Approach 2 — Count Length

## Idea

First calculate:

```text
Length of Linked List
```

Then find:

```text
middle = length / 2
```

Move to:

```text
middle - 1
```

Delete middle node.

---

# Flowchart

```text
Start

   │
   ▼

Find Length

   │
   ▼

middle = n/2

   │
   ▼

Move To

middle - 1

   │
   ▼

Delete Next Node

   │
   ▼

Return Head
```

---

# Length Approach Example

```text
1 → 3 → 4 → 7 → 1 → 2 → 6

Length = 7

Middle = 7/2

       = 3
```

Delete:

```text
7
```

Result:

```text
1 → 3 → 4 → 1 → 2 → 6
```

---

# Comparison

| Approach            | Time | Space |
| ------------------- | ---- | ----- |
| Fast & Slow Pointer | O(n) | O(1)  |
| Count Length        | O(n) | O(1)  |

---

# Why Fast & Slow Works

```text
Fast moves twice as fast.

When Fast reaches the end,

Slow reaches the middle.
```

This avoids:

```text
Extra traversal for counting.
```

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
O(1)
```

---

# Java Solution 1

```java
class Solution {

    public ListNode deleteMiddle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }
}
```

---

# Java Solution 2

```java
class Solution {

    public ListNode deleteMiddle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        int size = 0;

        ListNode temp = head;

        while (temp != null) {
            size++;
            temp = temp.next;
        }

        int middle = size / 2;

        temp = head;

        for (int i = 1; i < middle; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    }
}
```

---

# C++ Solution 1

```cpp
class Solution {
public:

    ListNode* deleteMiddle(ListNode* head) {

        if (head == nullptr || head->next == nullptr) {
            return nullptr;
        }

        ListNode* fast = head->next->next;
        ListNode* slow = head;

        while (fast != nullptr && fast->next != nullptr) {

            fast = fast->next->next;
            slow = slow->next;
        }

        slow->next = slow->next->next;

        return head;
    }
};
```

---

# C++ Solution 2

```cpp
class Solution {
public:

    ListNode* deleteMiddle(ListNode* head) {

        if (head == nullptr || head->next == nullptr) {
            return nullptr;
        }

        int size = 0;

        ListNode* temp = head;

        while (temp != nullptr) {
            size++;
            temp = temp->next;
        }

        int middle = size / 2;

        temp = head;

        for (int i = 1; i < middle; i++) {
            temp = temp->next;
        }

        temp->next = temp->next->next;

        return head;
    }
};
```
