//Approach-1 (One Pass Digit Simulation)
//T.C : O(d)
//S.C : O(1)

class Solution {

  public long sumAndMultiply(int n) {

    int digitSum = 0;

    int place = 1;

    int newNumber = 0;

    while (n > 0) {

      int digit = n % 10;

      if (digit != 0) {

        digitSum += digit;

        newNumber += digit * place;

        place *= 10;
      }

      n /= 10;
    }

    return 1L * newNumber * digitSum;
  }
}

/*
 * //Approach-2 (List Simulation)
 * //T.C : O(d)
 * //S.C : O(d)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * public long sumAndMultiply(int n) {
 * 
 * List<Integer> digits =
 * new ArrayList<>();
 * 
 * while (n > 0) {
 * 
 * if (n % 10 != 0) {
 * 
 * digits.add(
 * n % 10
 * );
 * }
 * 
 * n /= 10;
 * }
 * 
 * long digitSum = 0;
 * 
 * for (int digit : digits) {
 * 
 * digitSum += digit;
 * }
 * 
 * long newNumber = 0;
 * 
 * for (
 * int i = digits.size() - 1;
 * i >= 0;
 * i--
 * ) {
 * 
 * newNumber =
 * newNumber * 10 +
 * digits.get(i);
 * }
 * 
 * return newNumber * digitSum;
 * }
 * }
 */

/*
 * //Approach-3 (String Simulation)
 * //T.C : O(d)
 * //S.C : O(d)
 * 
 * class Solution {
 * 
 * public long sumAndMultiply(int n) {
 * 
 * String number =
 * String.valueOf(n);
 * 
 * StringBuilder builder =
 * new StringBuilder();
 * 
 * int digitSum = 0;
 * 
 * for (
 * int i = 0;
 * i < number.length();
 * i++
 * ) {
 * 
 * char current =
 * number.charAt(i);
 * 
 * if (current == '0') {
 * 
 * continue;
 * }
 * 
 * builder.append(current);
 * 
 * digitSum +=
 * current - '0';
 * }
 * 
 * long newNumber =
 * Long.parseLong(
 * builder.toString()
 * );
 * 
 * return newNumber * digitSum;
 * }
 * }
 */