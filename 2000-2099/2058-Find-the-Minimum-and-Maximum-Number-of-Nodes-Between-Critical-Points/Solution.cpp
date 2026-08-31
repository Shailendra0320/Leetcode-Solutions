// Approach-1 (One Pass + Constant Space)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  vector<int> nodesBetweenCriticalPoints(
      ListNode *head)
  {

    vector<int> answer = {
        -1,
        -1};

    if (
        head == nullptr ||
        head->next == nullptr ||
        head->next->next == nullptr)
    {
      return answer;
    }

    ListNode *prev =
        head;

    ListNode *curr =
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
        curr->next != nullptr)
    {

      ListNode *next =
          curr->next;

      bool isCritical =
          (curr->val > prev->val &&
           curr->val > next->val) ||
          (curr->val < prev->val &&
           curr->val < next->val);

      if (isCritical)
      {

        if (
            firstCritical == -1)
        {

          firstCritical =
              index;
        }
        else
        {

          minDistance =
              min(
                  minDistance,
                  index -
                      previousCritical);
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
        firstCritical == lastCritical)
    {
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

// Approach-2 (Store Critical Positions)
// T.C : O(n)
// S.C : O(n)

class Solution
{
public:
  vector<int> nodesBetweenCriticalPoints(
      ListNode *head)
  {

    vector<int> critical;

    ListNode *prev =
        head;

    ListNode *curr =
        head->next;

    int index = 1;

    while (
        curr->next != nullptr)
    {

      if (
          (
              curr->val > prev->val &&
              curr->val > curr->next->val) ||
          (curr->val < prev->val &&
           curr->val < curr->next->val))
      {

        critical.push_back(
            index);
      }

      prev =
          curr;

      curr =
          curr->next;

      index++;
    }

    if (
        critical.size() < 2)
    {

      return {
          -1,
          -1};
    }

    int minDistance =
        INT_MAX;

    for (
        int i = 1;
        i < critical.size();
        i++)
    {

      minDistance =
          min(
              minDistance,
              critical[i] -
                  critical[i - 1]);
    }

    int maxDistance =
        critical.back() -
        critical.front();

    return {
        minDistance,
        maxDistance};
  }
};