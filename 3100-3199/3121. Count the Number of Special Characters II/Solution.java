//Approach-1 (Array Tracking)
//T.C : O(n)
//S.C : O(1)

class Solution {

  public int numberOfSpecialChars(String word) {

    int[] lowerLast = new int[26];

    int[] upperFirst = new int[26];

    Arrays.fill(lowerLast, -1);

    Arrays.fill(upperFirst, -1);

    for (int i = 0; i < word.length(); i++) {

      char ch = word.charAt(i);

      if (Character.isLowerCase(ch)) {

        lowerLast[ch - 'a'] = i;
      } else {

        if (upperFirst[ch - 'A'] == -1) {

          upperFirst[ch - 'A'] = i;
        }
      }
    }

    int count = 0;

    for (int i = 0; i < 26; i++) {

      if (lowerLast[i] != -1 &&
          upperFirst[i] != -1 &&
          lowerLast[i] < upperFirst[i]) {

        count++;
      }
    }

    return count;
  }
}

/*
 * //Approach-2 (HashMap Tracking)
 * //T.C : O(n)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public int numberOfSpecialChars(String word) {
 * 
 * HashMap<Character, Integer> firstUpper = new HashMap<>();
 * 
 * HashMap<Character, Integer> lastLower = new HashMap<>();
 * 
 * int c = 0;
 * 
 * for (int i = 0; i < word.length(); i++) {
 * 
 * char ch = word.charAt(i);
 * 
 * if (Character.isLowerCase(ch)) {
 * 
 * lastLower.put(ch, i);
 * }
 * else {
 * 
 * if (!firstUpper.containsKey(ch)) {
 * 
 * firstUpper.put(ch, i);
 * }
 * }
 * }
 * 
 * for (char ch = 'a'; ch <= 'z'; ch++) {
 * 
 * char upper = Character.toUpperCase(ch);
 * 
 * if (lastLower.containsKey(ch) &&
 * firstUpper.containsKey(upper)) {
 * 
 * if (lastLower.get(ch) < firstUpper.get(upper)) {
 * 
 * c++;
 * }
 * }
 * }
 * 
 * return c;
 * }
 * }
 */