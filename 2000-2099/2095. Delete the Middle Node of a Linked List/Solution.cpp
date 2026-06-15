/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

// Approach-1 (Fast & Slow Pointer)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  ListNode *deleteMiddle(ListNode *head)
  {

    if (head == nullptr || head->next == nullptr)
    {
      return nullptr;
    }

    ListNode *fast = head->next->next;
    ListNode *slow = head;

    while (fast != nullptr && fast->next != nullptr)
    {

      fast = fast->next->next;
      slow = slow->next;
    }

    slow->next = slow->next->next;

    return head;
  }
};

/*
//Approach-2 (Count Length)
//T.C : O(n)
//S.C : O(1)

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
*/