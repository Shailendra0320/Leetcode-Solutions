class Solution {
  public String smallestPalindrome(String s, int k) {
    int n = s.length();
    int[] freq = new int[26];
    for (char c : s.toCharArray())
      freq[c - 'a']++;

    int midChar = -1;
    for (int i = 0; i < 26; i++) {
      if (freq[i] % 2 != 0)
        midChar = i;
    }

    int[] halfFreq = new int[26];
    int halfLen = 0;
    for (int i = 0; i < 26; i++) {
      halfFreq[i] = freq[i] / 2;
      halfLen += halfFreq[i];
    }

    long CAP = 3_000_000_000L;

    long total = countWays(halfFreq, halfLen, CAP);
    if (total < k)
      return "";

    StringBuilder firstHalf = new StringBuilder();
    long leftK = k;
    int leftLen = halfLen;

    for (int pos = 0; pos < halfLen; pos++) {
      for (int c = 0; c < 26; c++) {
        if (halfFreq[c] == 0)
          continue;

        halfFreq[c]--;
        long ways = countWays(halfFreq, leftLen - 1, CAP);

        if (leftK <= ways) {
          firstHalf.append((char) ('a' + c));
          break;
        } else {
          leftK -= ways;
          halfFreq[c]++;
        }
      }
      leftLen--;
    }

    StringBuilder result = new StringBuilder(firstHalf);
    if (midChar != -1)
      result.append((char) ('a' + midChar));
    result.append(firstHalf.reverse());

    return result.toString();
  }

  private long countWays(int[] freq, int total, long cap) {
    long ways = 1;
    int rem = total;
    for (int i = 0; i < 26 && ways <= cap; i++) {
      int f = freq[i];
      long comb = 1;
      for (int j = 1; j <= f; j++) {
        comb = comb * (rem - f + j) / j;
        if (comb > cap) {
          comb = cap + 1;
          break;
        }
      }
      ways *= comb;
      if (ways > cap) {
        ways = cap + 1;
        break;
      }
      rem -= f;
    }
    return ways;
  }
}