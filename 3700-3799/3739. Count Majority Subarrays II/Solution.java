class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();  

        int currSum = 0;

        mp.put(0, 1); 
        long validLeftPoints = 0;
        long result = 0;
        for (int x : nums) {
            if (x == target) {
                validLeftPoints += mp.getOrDefault(currSum, 0);
                currSum++;
            } else {
                currSum--;
                validLeftPoints -= mp.getOrDefault(currSum, 0);
            }
            mp.merge(currSum, 1, Integer::sum);
            result += validLeftPoints;
        }
        return result;
    }
}

/*
//Approach-1 (Prefix Sum + HashMap)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        Map<Integer, Integer> frequency = new HashMap<>();

        int prefixSum = 0;

        frequency.put(0, 1);

        long validPrefixes = 0;

        long answer = 0;

        for (int value : nums) {

            if (value == target) {

                validPrefixes +=
                    frequency.getOrDefault(
                        prefixSum,
                        0
                    );

                prefixSum++;

            } else {

                prefixSum--;

                validPrefixes -=
                    frequency.getOrDefault(
                        prefixSum,
                        0
                    );
            }

            frequency.merge(
                prefixSum,
                1,
                Integer::sum
            );

            answer += validPrefixes;
        }

        return answer;
    }
}
*/