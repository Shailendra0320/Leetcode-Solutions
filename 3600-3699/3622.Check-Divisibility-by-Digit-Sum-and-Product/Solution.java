// Approach-1 (Arithmetic Digit Extraction)
// T.C : O(log10(num))
// S.C : O(1)

class Solution {
  public boolean checkDivisibility(int num) {
    int current = num;
    int dSum = 0;
    int dProd = 1;

    while (current > 0) {
      int val = current % 10;

      dSum += val;
      dProd *= val;

      current /= 10;
    }

    int total = dSum + dProd;

    return num % total == 0;
  }
}

// Approach-2 (String-Based Digit Processing)
// T.C : O(log10(num))
// S.C : O(log10(num))

class Solution {
  public boolean checkDivisibility(int num) {
    String str = Integer.toString(num);

    int digitSum = 0;
    int digitProduct = 1;

    for (char ch : str.toCharArray()) {
      int digit = ch - '0';

      digitSum += digit;
      digitProduct *= digit;
    }

    int total = digitSum + digitProduct;

    return num % total == 0;
  }
}