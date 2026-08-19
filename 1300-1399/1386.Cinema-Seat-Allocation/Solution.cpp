#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
  int maxNumberOfFamilies(int n, vector<vector<int>> &reservedSeats)
  {
    const int LEFT_BLOCK =
        (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);

    const int MID_BLOCK =
        (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

    const int RIGHT_BLOCK =
        (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

    unordered_map<int, int> rowToReservedMask;

    for (auto &reservation : reservedSeats)
    {
      int row = reservation[0];
      int seat = reservation[1];

      rowToReservedMask[row] |= (1 << seat);
    }

    long long totalGroups =
        1LL * (n - rowToReservedMask.size()) * 2;

    for (auto &[row, reservedMask] : rowToReservedMask)
    {
      bool isLeftFree =
          (reservedMask & LEFT_BLOCK) == 0;

      bool isMidFree =
          (reservedMask & MID_BLOCK) == 0;

      bool isRightFree =
          (reservedMask & RIGHT_BLOCK) == 0;

      if (isLeftFree && isRightFree)
      {
        totalGroups += 2;
      }
      else if (isLeftFree || isMidFree || isRightFree)
      {
        totalGroups += 1;
      }
    }

    return (int)totalGroups;
  }
};