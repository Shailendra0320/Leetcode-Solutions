import java.util.*;

//Approach-1 (Single-Pass Parsing with HashMap)
//T.C : O(n + total knowledge size)
//S.C : O(k)

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
                continue;
            }

            int j = i + 1;

            while (s.charAt(j) != ')') {
                j++;
            }

            String key = s.substring(i + 1, j);

            ans.append(map.getOrDefault(key, "?"));

            i = j + 1;
        }

        return ans.toString();
    }
}


//Approach-2 (Two-Pointer Parsing with Helper)
//T.C : O(n + total knowledge size)
//S.C : O(k)

class Solution2 {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
                continue;
            }

            int end = i + 1;

            while (s.charAt(end) != ')') {
                end++;
            }

            String key = getKey(s, i + 1, end);

            ans.append(map.getOrDefault(key, "?"));

            i = end + 1;
        }

        return ans.toString();
    }

    private String getKey(String s, int left, int right) {
        StringBuilder key = new StringBuilder();

        while (left < right) {
            key.append(s.charAt(left));
            left++;
        }

        return key.toString();
    }
}