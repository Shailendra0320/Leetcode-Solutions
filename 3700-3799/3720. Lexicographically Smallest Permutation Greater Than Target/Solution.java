//Approach-1 (Greedy + Frequency Counting + Backtracking)
//T.C : O(26 * n)
//S.C : O(n)

class Solution {

  public String lexGreaterPermutation(
      String s,
      String target) {

    int n = s.length();

    String quinorath = s;

    int[] freq = new int[26];

    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int i = 0;

    while (i < n &&
        freq[target.charAt(i) - 'a'] > 0) {

      freq[target.charAt(i) - 'a']--;
      i++;
    }

    for (int pos = i - 1; pos >= 0; pos--) {

      freq[target.charAt(pos) - 'a']++;

      int current = target.charAt(pos) - 'a';

      for (int c = current + 1; c < 26; c++) {

        if (freq[c] == 0) {
          continue;
        }

        StringBuilder ans = new StringBuilder();

        for (int j = 0; j < pos; j++) {

          ans.append(
              target.charAt(j));
        }

        ans.append(
            (char) ('a' + c));

        freq[c]--;

        for (int ch = 0; ch < 26; ch++) {

          while (freq[ch] > 0) {

            ans.append(
                (char) ('a' + ch));

            freq[ch]--;
          }
        }

        return ans.toString();
      }
    }

    return "";
  }
}

// Approach-2 (Greedy + Next Permutation Style)
// T.C : O(26 * n)
// S.C : O(n)

class Solution {

  public String lexGreaterPermutation(
      String s,
      String target) {

    int n = s.length();

    int[] freq = new int[26];

    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    StringBuilder prefix = new StringBuilder();

    for (int i = 0; i < n; i++) {

      int targetChar = target.charAt(i) - 'a';

      if (freq[targetChar] > 0) {

        freq[targetChar]--;

        prefix.append(
            target.charAt(i));

      } else {

        for (int c = targetChar + 1; c < 26; c++) {

          if (freq[c] == 0) {
            continue;
          }

          StringBuilder ans = new StringBuilder(
              prefix);

          ans.append(
              (char) ('a' + c));

          freq[c]--;

          for (int ch = 0; ch < 26; ch++) {

            while (freq[ch] > 0) {

              ans.append(
                  (char) ('a' + ch));

              freq[ch]--;
            }
          }

          return ans.toString();
        }

        break;
      }
    }

    for (int pos = n - 1; pos >= 0; pos--) {

      int current = prefix.charAt(pos) - 'a';

      freq[current]++;

      for (int c = current + 1; c < 26; c++) {

        if (freq[c] == 0) {
          continue;
        }

        StringBuilder ans = new StringBuilder();

        for (int j = 0; j < pos; j++) {

          ans.append(
              prefix.charAt(j));
        }

        ans.append(
            (char) ('a' + c));

        freq[c]--;

        for (int ch = 0; ch < 26; ch++) {

          while (freq[ch] > 0) {

            ans.append(
                (char) ('a' + ch));

            freq[ch]--;
          }
        }

        return ans.toString();
      }
    }

    return "";
  }
}