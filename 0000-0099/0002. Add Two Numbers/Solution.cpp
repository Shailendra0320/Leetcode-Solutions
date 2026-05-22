class Solution
{
public:
  ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)
  {

    ListNode *dummy = new ListNode(0);

    ListNode *temp = dummy;

    int carry = 0;

    while (l1 != NULL || l2 != NULL || carry != 0)
    {

      int sum = carry;

      if (l1 != NULL)
      {

        sum += l1->val;

        l1 = l1->next;
      }

      if (l2 != NULL)
      {

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

/*
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
*/