import java.util.*;

//Approach-1 (Direct Generation)
//T.C : O(1)
//S.C : O(1)

class Solution {
  public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> ans = new ArrayList<>();
    String digits = "123456789";

    for (int len = 2; len <= 9; len++) {
      for (int i = 0; i + len <= 9; i++) {
        int num = Integer.parseInt(digits.substring(i, i + len));

        if (num >= low && num <= high) {
          ans.add(num);
        }
      }
    }

    return ans;
  }
}

// Approach-2 (Recursive Construction)
// T.C : O(1)
// S.C : O(1)

class Solution2 {
  public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> ans = new ArrayList<>();

    for (int start = 1; start <= 8; start++) {
      generate(start, start, low, high, ans);
    }

    Collections.sort(ans);
    return ans;
  }

  private void generate(int lastDigit, int num, int low, int high, List<Integer> ans) {
    if (lastDigit == 9) {
      return;
    }

    int next = num * 10 + (lastDigit + 1);

    if (next > high) {
      return;
    }

    if (next >= low) {
      ans.add(next);
    }

    generate(lastDigit + 1, next, low, high, ans);
  }
}