//Approach-1 (Iterative Simulation)
//T.C: O(n)
//S.C: O(n)

class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder next = new StringBuilder();

            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;

                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }

                next.append(sum);
            }

            s = next.toString();
        }

        return s;
    }
}


//Approach-2 (Recursive Simulation)
//T.C: O(n)
//S.C: O(n)

class Solution2 {
    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }

        StringBuilder next = new StringBuilder();

        for (int i = 0; i < s.length(); i += k) {
            int sum = 0;

            for (int j = i; j < Math.min(i + k, s.length()); j++) {
                sum += s.charAt(j) - '0';
            }

            next.append(sum);
        }

        return digitSum(next.toString(), k);
    }
}