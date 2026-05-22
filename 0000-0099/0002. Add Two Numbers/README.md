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

# 2. Add Two Numbers

# Intuition

We are given two linked lists representing two non-negative integers.

Each node contains:

- a single digit
- digits are stored in reverse order

We need to return:

- the sum as a linked list

Example:

```text
l1 = [2,4,3]
l2 = [5,6,4]

342 + 465 = 807

Answer:
[7,0,8]
```

---

# Approach 1 — Iterative Linked List Traversal

## Idea

Traverse both linked lists simultaneously.

At every step:

- add digits
- add carry
- create new node
- move forward

Continue until:

- both lists end
- carry becomes zero

---

# Iterative Flow

```text
Take digits

        ↓

Add carry

        ↓

Create node

        ↓

Update carry

        ↓

Move forward
```

---

# Diagram

```text
l1 = 2 → 4 → 3
l2 = 5 → 6 → 4

Step 1:
2 + 5 = 7

Result:
7

Step 2:
4 + 6 = 10

Node = 0
Carry = 1

Result:
7 → 0

Step 3:
3 + 4 + 1 = 8

Result:
7 → 0 → 8
```

---

# Approach 2 — Recursive Solution

## Idea

Use recursion to:

- process one digit at a time
- pass carry recursively

Each recursive call:

- creates one node
- moves to next digits

---

# Recursive Flow

```text
Add digits

        ↓

Create node

        ↓

Recursive call for next nodes
```

---

# Dry Run

Input:

```text
l1 = [2,4,3]
l2 = [5,6,4]
```

---

### Step 1

```text
2 + 5 = 7
carry = 0
```

Result:

```text
7
```

---

### Step 2

```text
4 + 6 = 10
carry = 1
```

Result:

```text
7 → 0
```

---

### Step 3

```text
3 + 4 + 1 = 8
```

Result:

```text
7 → 0 → 8
```

---

# Complexity

## Iterative Approach

### Time Complexity

```text
O(max(n,m))
```

### Space Complexity

```text
O(max(n,m))
```

---

## Recursive Approach

### Time Complexity

```text
O(max(n,m))
```

### Space Complexity

```text
O(max(n,m))
```

Recursion stack is used.

---

# Java Solution — Iterative

```java
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);

        ListNode temp = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            if (l1 != null) {

                sum += l1.val;

                l1 = l1.next;
            }

            if (l2 != null) {

                sum += l2.val;

                l2 = l2.next;
            }

            carry = sum / 10;

            temp.next = new ListNode(sum % 10);

            temp = temp.next;
        }

        return dummy.next;
    }
}
```

---

# Java Solution — Recursive

```java
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return solve(l1, l2, 0);
    }

    private ListNode solve(ListNode l1, ListNode l2, int carry) {

        if (l1 == null && l2 == null && carry == 0) {

            return null;
        }

        int sum = carry;

        if (l1 != null) {

            sum += l1.val;
        }

        if (l2 != null) {

            sum += l2.val;
        }

        ListNode node = new ListNode(sum % 10);

        node.next = solve(
            l1 != null ? l1.next : null,
            l2 != null ? l2.next : null,
            sum / 10
        );

        return node;
    }
}
```

---

# C++ Solution — Iterative

```cpp
class Solution {
public:

    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {

        ListNode* dummy = new ListNode(0);

        ListNode* temp = dummy;

        int carry = 0;

        while (l1 != NULL || l2 != NULL || carry != 0) {

            int sum = carry;

            if (l1 != NULL) {

                sum += l1->val;

                l1 = l1->next;
            }

            if (l2 != NULL) {

                sum += l2->val;

                l2 = l2->next;
            }

            carry = sum / 10;

            temp->next = new ListNode(sum % 10);

            temp = temp->next;
        }

        return dummy->next;
    }
};
```

---

# C++ Solution — Recursive

```cpp
class Solution {
public:

    ListNode* solve(ListNode* l1, ListNode* l2, int carry) {

        if (l1 == NULL && l2 == NULL && carry == 0) {

            return NULL;
        }

        int sum = carry;

        if (l1 != NULL) {

            sum += l1->val;
        }

        if (l2 != NULL) {

            sum += l2->val;
        }

        ListNode* node = new ListNode(sum % 10);

        node->next = solve(
            l1 != NULL ? l1->next : NULL,
            l2 != NULL ? l2->next : NULL,
            sum / 10
        );

        return node;
    }

    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {

        return solve(l1, l2, 0);
    }
};
```
