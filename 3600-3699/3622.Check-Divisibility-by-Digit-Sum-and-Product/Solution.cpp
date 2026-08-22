// Approach-1 (Arithmetic Digit Extraction)
// T.C : O(log10(num))
// S.C : O(1)

class Solution
{
public:
  bool checkDivisibility(int num)
  {
    int current = num;
    int dSum = 0;
    int dProd = 1;

    while (current > 0)
    {
      int digit = current % 10;

      dSum += digit;
      dProd *= digit;

      current /= 10;
    }

    int total = dSum + dProd;

    return num % total == 0;
  }
};

// Approach-2 (String-Based Digit Processing)
// T.C : O(log10(num))
// S.C : O(log10(num))

class Solution
{
public:
  bool checkDivisibility(int num)
  {
    string str = to_string(num);

    int digitSum = 0;
    int digitProduct = 1;

    for (char ch : str)
    {
      int digit = ch - '0';

      digitSum += digit;
      digitProduct *= digit;
    }

    int total = digitSum + digitProduct;

    return num % total == 0;
  }
};