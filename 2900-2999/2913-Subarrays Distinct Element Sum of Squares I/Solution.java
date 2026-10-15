//Approach-1 (HashSet Enumeration)
//T.C : O(n^2) expected
//S.C : O(n)

import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < n; j++) {
                seen.add(nums.get(j));

                int distinct = seen.size();
                answer += distinct * distinct;
            }
        }

        return answer;
    }
}

//Approach-2 (Fixed Boolean Array)
//T.C : O(n^2)
//S.C : O(1)

class Solution2 {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[101];
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int value = nums.get(j);

                if (!seen[value]) {
                    seen[value] = true;
                    distinct++;
                }

                answer += distinct * distinct;
            }
        }

        return answer;
    }
}//Approach-1 (HashSet Enumeration)
//T.C : O(n^2) expected
//S.C : O(n)

import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < n; j++) {
                seen.add(nums.get(j));

                int distinct = seen.size();
                answer += distinct * distinct;
            }
        }

        return answer;
    }
}

//Approach-2 (Fixed Boolean Array)
//T.C : O(n^2)
//S.C : O(1)

class Solution2 {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[101];
            int distinct = 0;

            for (int j = i; j < n; j++) {
                int value = nums.get(j);

                if (!seen[value]) {
                    seen[value] = true;
                    distinct++;
                }

                answer += distinct * distinct;
            }
        }

        return answer;
    }
}