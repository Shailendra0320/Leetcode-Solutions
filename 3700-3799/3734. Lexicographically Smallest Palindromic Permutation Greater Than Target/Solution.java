//Approach-1 (Backtracking + Greedy + Frequency Counting)
//T.C : O(26 * n^2)
//S.C : O(n)

class Solution {

  public String lexPalindromicPermutation(
      String s,
      String target) {

    int n = s.length();

    String calendrix = s;

    int[] freq = new int[26];

    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++) {

      if (freq[i] % 2 != 0) {
        oddCount++;
        middle = (char) ('a' + i);
      }
    }

    if (oddCount > 1) {
      return "";
    }

    int halfLen = n / 2;

    int[] halfFreq = new int[26];

    for (int i = 0; i < 26; i++) {

      halfFreq[i] = freq[i] / 2;
    }

    StringBuilder left = new StringBuilder();

    for (int pos = 0; pos < halfLen; pos++) {

      boolean found = false;

      for (int c = 0; c < 26; c++) {

        if (halfFreq[c] == 0) {
          continue;
        }

        halfFreq[c]--;

        left.append(
            (char) ('a' + c));

        if (canMakeGreater(
            left,
            halfFreq,
            middle,
            target)) {

          found = true;

          break;
        }

        left.deleteCharAt(
            left.length() - 1);

        halfFreq[c]++;
      }

      if (!found) {
        return "";
      }
    }

    StringBuilder answer = new StringBuilder(left);

    if (oddCount == 1) {
      answer.append(middle);
    }

    answer.append(
        new StringBuilder(left)
            .reverse());

    return answer.toString();
  }

  private boolean canMakeGreater(
      StringBuilder left,
      int[] halfFreq,
      char middle,
      String target) {

    int halfLen = target.length() / 2;

    StringBuilder suffix = new StringBuilder();

    for (int c = 25; c >= 0; c--) {

      for (int count = 0; count < halfFreq[c]; count++) {

        suffix.append(
            (char) ('a' + c));
      }
    }

    StringBuilder candidate = new StringBuilder();

    candidate.append(left);
    candidate.append(suffix);

    if (target.length() % 2 == 1) {
      candidate.append(middle);
    }

    candidate.append(
        new StringBuilder(candidate)
            .reverse());

    return candidate.compareTo(target) > 0;
  }
}

// Approach-2 (Two Pointers + Greedy + Frequency Counting)
// T.C : O(26 * n^2)
// S.C : O(n)

class Solution {

  public String lexPalindromicPermutation(
      String s,
      String target) {

    int n = s.length();

    String calendrix = s;

    int[] freq = new int[26];

    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int oddCount = 0;
    char middle = 0;

    for (int i = 0; i < 26; i++) {

      if (freq[i] % 2 != 0) {

        oddCount++;

        middle = (char) ('a' + i);
      }
    }

    if (oddCount > 1) {
      return "";
    }

    int halfLen = n / 2;

    int[] halfFreq = new int[26];

    for (int i = 0; i < 26; i++) {

      halfFreq[i] = freq[i] / 2;
    }

    StringBuilder left = new StringBuilder();

    for (int i = 0; i < halfLen; i++) {

      int targetChar = target.charAt(i) - 'a';

      boolean used = false;

      for (int c = 0; c <= targetChar; c++) {

        if (halfFreq[c] == 0) {
          continue;
        }

        halfFreq[c]--;
        left.append(
            (char) ('a' + c));

        if (c == targetChar &&
            canComplete(
                left,
                halfFreq,
                middle,
                target)) {

          used = true;

          break;
        }

        if (c > targetChar) {

          return build(
              left,
              halfFreq,
              middle);
        }

        left.deleteCharAt(
            left.length() - 1);

        halfFreq[c]++;
      }

      if (!used) {
        break;
      }
    }

    for (int pos = left.length() - 1; pos >= 0; pos--) {

      int original = left.charAt(pos) - 'a';

      halfFreq[original]++;

      for (int c = original + 1; c < 26; c++) {

        if (halfFreq[c] == 0) {
          continue;
        }

        left.setCharAt(
            pos,
            (char) ('a' + c));

        halfFreq[c]--;

        return build(
            left,
            halfFreq,
            middle,
            pos + 1);
      }

      left.setCharAt(
          pos,
          (char) ('a' + original));
    }

    return "";
  }

  private boolean canComplete(
      StringBuilder left,
      int[] freq,
      char middle,
      String target) {

    String result = build(
        left,
        freq,
        middle);

    return result.compareTo(target) > 0;
  }

  private String build(
      StringBuilder left,
      int[] freq,
      char middle) {

    return build(
        left,
        freq,
        middle,
        left.length());
  }

  private String build(
      StringBuilder left,
      int[] freq,
      char middle,
      int start) {

    StringBuilder firstHalf = new StringBuilder();

    for (int i = 0; i < left.length(); i++) {
      firstHalf.append(
          left.charAt(i));
    }

    for (int c = 0; c < 26; c++) {

      while (freq[c] > 0) {

        firstHalf.append(
            (char) ('a' + c));

        freq[c]--;
      }
    }

    StringBuilder result = new StringBuilder(
        firstHalf);

    if (middle != 0) {
      result.append(middle);
    }

    result.append(
        new StringBuilder(firstHalf)
            .reverse());

    return result.toString();
  }
}