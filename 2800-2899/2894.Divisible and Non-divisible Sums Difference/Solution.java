
class Solution {
  public int differenceOfSums(int n, int m) {
    int num1 = 0, num2 = 0;
    for (int i = 1; i <= n; i++) {
      if (i % m != 0) {
        num1 += i;
      } else {
        num2 += i;
      }
    }
    return num1 - num2;
  }
}

// class Solution {
// public int differenceOfSums(int n, int m) {
// int ans = 0;
// for (int i = 1; i <= n; ++i) {
// ans += i % m == 0 ? -i : i;
// }
// return ans;
// }
// }

// class Solution {
// public int differenceOfSums(int b, int v) {
// int k = b / v;
// return (b *(b + 1))/ 2 - k * (k+ 1)* v;
// }
// }
