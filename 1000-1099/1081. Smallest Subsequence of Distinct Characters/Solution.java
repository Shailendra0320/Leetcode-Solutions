//Approach-1 (Greedy + Monotonic Stack)
//T.C : O(n)
//S.C : O(1)

class Solution {

  public String smallestSubsequence(
      String s) {

    int[] lastIndex = new int[26];

    boolean[] inStack = new boolean[26];

    StringBuilder stack = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {

      lastIndex[s.charAt(i) - 'a'] = i;
    }

    for (int i = 0; i < s.length(); i++) {

      int current = s.charAt(i) - 'a';

      if (inStack[current]) {

        continue;
      }

      while (stack.length() > 0) {

        int top = stack.charAt(
            stack.length() - 1) - 'a';

        if (top > current &&
            lastIndex[top] > i) {

          stack.deleteCharAt(
              stack.length() - 1);

          inStack[top] = false;

        } else {

          break;
        }
      }

      stack.append(
          s.charAt(i));

      inStack[current] = true;
    }

    return stack.toString();
  }
}

/*
 * //Approach-2 (Using Stack)
 * //T.C : O(n)
 * //S.C : O(n)
 * 
 * import java.util.Stack;
 * 
 * class Solution {
 * 
 * public String smallestSubsequence(
 * String s
 * ) {
 * 
 * int[] lastIndex =
 * new int[26];
 * 
 * boolean[] visited =
 * new boolean[26];
 * 
 * Stack<Character> stack =
 * new Stack<>();
 * 
 * for (
 * int i = 0;
 * i < s.length();
 * i++
 * ) {
 * 
 * lastIndex[
 * s.charAt(i) - 'a'
 * ] = i;
 * }
 * 
 * for (
 * int i = 0;
 * i < s.length();
 * i++
 * ) {
 * 
 * char current =
 * s.charAt(i);
 * 
 * if (
 * visited[current - 'a']
 * ) {
 * 
 * continue;
 * }
 * 
 * while (
 * !stack.isEmpty() &&
 * stack.peek() > current &&
 * lastIndex[
 * stack.peek() - 'a'
 * ] > i
 * ) {
 * 
 * visited[
 * stack.pop() - 'a'
 * ] = false;
 * }
 * 
 * stack.push(
 * current
 * );
 * 
 * visited[
 * current - 'a'
 * ] = true;
 * }
 * 
 * StringBuilder answer =
 * new StringBuilder();
 * 
 * while (
 * !stack.isEmpty()
 * ) {
 * 
 * answer.append(
 * stack.pop()
 * );
 * }
 * 
 * return answer
 * .reverse()
 * .toString();
 * }
 * }
 */