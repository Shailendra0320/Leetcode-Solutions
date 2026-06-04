leetcode 3751 class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWavinessSum = 0;
        for (int i = num1; i <= num2; i++) {
            totalWavinessSum += calculateWaviness(i);
        }
        return totalWavinessSum;
    }
    
    private int calculateWaviness(int num) {
        if (num < 100) {
            return 0;
        }
        
        String s = Integer.toString(num);
        int wavinessCount = 0;
        
        for (int i = 1; i < s.length() - 1; i++) {
            char current = s.charAt(i);
            char left = s.charAt(i - 1);
            char right = s.charAt(i + 1);
            
            if (current > left && current > right) {
                wavinessCount++;
            } else if (current < left && current < right) {
                wavinessCount++;
            }
        }
        
        return wavinessCount;
    }
} 