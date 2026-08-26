//Approach-1 (Positions of Ones)
//T.C : O(n^2)
//S.C : O(n)

class Solution {

  public String shortestBeautifulSubstring(
      String s,
      int k) {

    int n = s.length();

    int[] ones = new int[n];

    int count = 0;

    for (int i = 0; i < n; i++) {

      if (s.charAt(i) == '1') {
        ones[count++] = i;
      }
    }

    if (count < k) {
      return "";
    }

    String answer = "";

    for (int i = 0; i + k - 1 < count; i++) {

      int start = ones[i];

      int end = ones[i + k - 1];

      String candidate = s.substring(
          start,
          end + 1);

      if (answer.isEmpty() ||
          candidate.length() < answer.length() ||
          (candidate.length() == answer.length() &&
              candidate.compareTo(answer) < 0)) {

        answer = candidate;
      }
    }

    return answer;
  }
}

// Approach-2 (Sliding Window)
// T.C : O(n^2)
// S.C : O(1)

class Solution {

  public String shortestBeautifulSubstring(
      String s,
      int k) {

    int n = s.length();

    int left = 0;
    int ones = 0;

    String answer = "";

    for (int right = 0; right < n; right++) {

      if (s.charAt(right) == '1') {
        ones++;
      }

      while (ones > k) {

        if (s.charAt(left) == '1') {
          ones--;
        }

        left++;
      }

      if (ones == k) {

        while (left < right &&
            s.charAt(left) == '0') {

          left++;
        }

        String candidate = s.substring(
            left,
            right + 1);

        if (answer.isEmpty() ||
            candidate.length() < answer.length() ||
            (candidate.length() == answer.length() &&
                candidate.compareTo(answer) < 0)) {

          answer = candidate;
        }
      }
    }

    return answer;
  }
}