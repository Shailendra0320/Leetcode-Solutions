//Approach-1 (String Concatenation)
//T.C : O(n²) in worst case
//S.C : O(n)

class Solution {

  public boolean rotateString(String s, String goal) {

    if (s.length() != goal.length()) {
      return false;
    }

    String doubled = s + s;

    return doubled.contains(goal);
  }
}