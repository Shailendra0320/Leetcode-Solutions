// Approach-1 (Binary Search + On-Demand Inclusion-Exclusion)
// T.C : O(n * 2^n * log(k * minCoin))
// S.C : O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
  long long findKthSmallest(vector<int> &coins, int k)
  {
    int n = coins.size();
    int numSubsets = 1 << n;

    long long minCoin = coins[0];

    for (int coin : coins)
    {
      minCoin = min(minCoin, (long long)coin);
    }

    long long low = 1;
    long long high = minCoin * k;

    while (low < high)
    {
      long long mid = low + (high - low) / 2;

      if (countMultiples(mid, coins, n, numSubsets) >= k)
      {
        high = mid;
      }
      else
      {
        low = mid + 1;
      }
    }

    return low;
  }

private:
  long long countMultiples(
      long long target,
      vector<int> &coins,
      int n,
      int numSubsets)
  {
    long long totalCount = 0;

    for (int mask = 1; mask < numSubsets; mask++)
    {
      long long currentLCM = 1;
      int selectedCoins = 0;

      for (int i = 0; i < n; i++)
      {
        if (mask & (1 << i))
        {
          currentLCM =
              lcm(currentLCM, (long long)coins[i]);

          selectedCoins++;
        }
      }

      long long contribution =
          target / currentLCM;

      if (selectedCoins & 1)
      {
        totalCount += contribution;
      }
      else
      {
        totalCount -= contribution;
      }
    }

    return totalCount;
  }

  long long gcd(long long a, long long b)
  {
    while (b != 0)
    {
      long long temp = b;
      b = a % b;
      a = temp;
    }

    return a;
  }

  long long lcm(long long a, long long b)
  {
    return (a / gcd(a, b)) * b;
  }
};

// Approach-2 (Binary Search + Precomputed Subset LCM)
// T.C : O(2^n * log(k * minCoin))
// S.C : O(2^n)

class Solution
{
public:
  long long findKthSmallest(vector<int> &coins, int k)
  {
    int n = coins.size();
    int numSubsets = 1 << n;

    vector<long long> lcms(numSubsets);
    vector<int> signs(numSubsets);

    lcms[0] = 1;
    signs[0] = -1;

    for (int mask = 1; mask < numSubsets; mask++)
    {
      int leastSetBit = __builtin_ctz(mask);

      int prevMask =
          mask ^ (1 << leastSetBit);

      if (prevMask == 0)
      {
        lcms[mask] =
            coins[leastSetBit];

        signs[mask] = 1;
      }
      else
      {
        lcms[mask] =
            lcm(
                lcms[prevMask],
                (long long)coins[leastSetBit]);

        signs[mask] =
            -signs[prevMask];
      }
    }

    long long minCoin = coins[0];

    for (int coin : coins)
    {
      minCoin = min(minCoin, (long long)coin);
    }

    long long low = 1;
    long long high = minCoin * k;

    while (low <= high)
    {
      long long mid =
          low + (high - low) / 2;

      if (countMultiples(
              mid,
              numSubsets,
              lcms,
              signs) >= k)
      {

        high = mid - 1;
      }
      else
      {
        low = mid + 1;
      }
    }

    return low;
  }

private:
  long long gcd(long long a, long long b)
  {
    while (b != 0)
    {
      long long temp = b;
      b = a % b;
      a = temp;
    }

    return a;
  }

  long long lcm(long long a, long long b)
  {
    return (a / gcd(a, b)) * b;
  }

  long long countMultiples(
      long long target,
      int numSubsets,
      const vector<long long> &lcms,
      const vector<int> &signs)
  {
    long long totalCount = 0;

    for (int mask = 1; mask < numSubsets; mask++)
    {
      totalCount +=
          signs[mask] *
          (target / lcms[mask]);
    }

    return totalCount;
  }
};