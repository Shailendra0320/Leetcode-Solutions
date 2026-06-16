//Approach-1 (StringBuilder Simulation)
//T.C : O(n²) Worst Case
//S.C : O(n)

class Solution {

  public String processStr(String s) {

    StringBuilder sb = new StringBuilder();

    for (char ch : s.toCharArray()) {

      if (ch == '*') {

        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length() - 1);
        }
      } else if (ch == '#') {

        StringBuilder duplicate = new StringBuilder(sb);

        sb.append(duplicate);
      } else if (ch == '%') {

        sb.reverse();
      } else {

        sb.append(ch);
      }
    }

    return sb.toString();
  }
}

/*
 * //Approach-2 (ArrayList Simulation)
 * //T.C : O(n²)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public String processStr(String input) {
 * 
 * List<Character> chars = new ArrayList<>();
 * 
 * for (char current : input.toCharArray()) {
 * 
 * if (current >= 'a' && current <= 'z') {
 * 
 * chars.add(current);
 * }
 * else if (current == '*') {
 * 
 * if (!chars.isEmpty()) {
 * 
 * chars.remove(chars.size() - 1);
 * }
 * }
 * else if (current == '#') {
 * 
 * int size = chars.size();
 * 
 * for (int i = 0; i < size; i++) {
 * 
 * chars.add(chars.get(i));
 * }
 * }
 * else {
 * 
 * Collections.reverse(chars);
 * }
 * }
 * 
 * StringBuilder answer = new StringBuilder();
 * 
 * for (char ch : chars) {
 * 
 * answer.append(ch);
 * }
 * 
 * return answer.toString();
 * }
 * }
 */

/*
 * //Approach-3 (Optimized StringBuilder)
 * //T.C : O(n²)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public String processStr(String data) {
 * 
 * StringBuilder output = new StringBuilder();
 * 
 * for (char current : data.toCharArray()) {
 * 
 * if (Character.isLowerCase(current)) {
 * 
 * output.append(current);
 * }
 * else if (current == '*') {
 * 
 * if (output.length() > 0) {
 * 
 * output.deleteCharAt(output.length() - 1);
 * }
 * }
 * else if (current == '#') {
 * 
 * output.append(output.toString());
 * }
 * else {
 * 
 * output.reverse();
 * }
 * }
 * 
 * return output.toString();
 * }
 * }
 */