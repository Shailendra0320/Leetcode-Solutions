//Approach-1 (Frequency Counting)
//T.C : O(n)
//S.C : O(1)

class Solution {

  public int maxNumberOfBalloons(String text) {

    int[] frequency = new int[26];

    for (char ch : text.toCharArray()) {
      frequency[ch - 'a']++;
    }

    return Math.min(
        Math.min(
            frequency['b' - 'a'],
            frequency['a' - 'a']),
        Math.min(
            frequency['l' - 'a'] / 2,
            Math.min(
                frequency['o' - 'a'] / 2,
                frequency['n' - 'a'])));
  }
}

/*
 * //Approach-2 (HashMap)
 * //T.C : O(n)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public int maxNumberOfBalloons(String text) {
 * 
 * Map<Character, Integer> frequency =
 * new HashMap<>();
 * 
 * for (char ch : text.toCharArray()) {
 * 
 * frequency.put(
 * ch,
 * frequency.getOrDefault(ch, 0) + 1
 * );
 * }
 * 
 * int b =
 * frequency.getOrDefault('b', 0);
 * 
 * int a =
 * frequency.getOrDefault('a', 0);
 * 
 * int l =
 * frequency.getOrDefault('l', 0) / 2;
 * 
 * int o =
 * frequency.getOrDefault('o', 0) / 2;
 * 
 * int n =
 * frequency.getOrDefault('n', 0);
 * 
 * return Math.min(
 * Math.min(b, a),
 * Math.min(
 * l,
 * Math.min(o, n)
 * )
 * );
 * }
 * }
 */