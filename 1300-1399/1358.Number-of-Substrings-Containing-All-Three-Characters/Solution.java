# Java Solution

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int left = 0;
        int answer = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                answer += n - right;
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return answer;
    }
}

/*

Alternative Approach (Last Seen Indices)

// T.C : O(n)
// S.C : O(1)

class Solution {
    public int numberOfSubstrings(String s) {

        int[] lastSeen = {-1, -1, -1};

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            lastSeen[s.charAt(i) - 'a'] = i;

            int minIndex = Math.min(
                lastSeen[0],
                Math.min(lastSeen[1], lastSeen[2])
            );

            if (minIndex != -1) {

                answer += minIndex + 1;
            }
        }

        return answer;
    }
}

*/
```