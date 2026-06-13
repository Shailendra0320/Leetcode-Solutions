class Solution {
  public String mapWordWeights(String[] words, int[] weights) {
    StringBuilder ans = new StringBuilder();

    for (String w : words) {
      int s = 0;
      for (char c : w.toCharArray()) {
        s = (s + weights[c - 'a']) % 26;
      }
      ans.append((char) ('a' + (25 - s)));
    }

    return ans.toString();
  }
}